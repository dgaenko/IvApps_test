package ru.familion.ivapps_test.views;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ru.familion.ivapps_test.R;
import ru.familion.ivapps_test.models.entity.Data;

/**
 * Currency additional info fragment class
 */
public class CurrencyInfoFragment extends BaseFragment {

    // fragment layout resource id
    private static final int layout_res_id = R.layout.currency_info_fragment;

    // fragment UI views
    private View rootView;
    private ImageView imgCurrencyIcon;
    private TextView txtCurrencyTitle;
    private TextView txtCurrencyInfoSymbol;
    private TextView txtCurrencyInfoPrice;
    private TextView txtCurrencyInfo1h;
    private TextView txtCurrencyInfo24h;
    private TextView txtCurrencyInfo7d;
    private TextView txtCurrencyInfoLastUpdated;

    // fragment data list
    private Data currencyData;


    /**
     * Constructor
     */
    public CurrencyInfoFragment() {
    }

    /**
     * Make currency info fragment instance
     * @return  fragment instance
     */
    public static CurrencyInfoFragment getInstance() {
        Bundle args = new Bundle();
        CurrencyInfoFragment fragment = new CurrencyInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Make fragment view by layout
     * @param inflater              inflater
     * @param container             container
     * @param savedInstanceState    saved statebundle
     * @return                      fragment view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(layout_res_id, container, false);
        }

        // get UI elements
        ImageView imgBackButton = (ImageView)rootView.findViewById(R.id.imgBackButton);

        imgCurrencyIcon = (ImageView)rootView.findViewById(R.id.imgCurrencyIcon);
        txtCurrencyTitle = (TextView) rootView.findViewById(R.id.txtCurrencyTitle);
        txtCurrencyInfoSymbol = (TextView) rootView.findViewById(R.id.txtCurrencyInfoSymbol);
        txtCurrencyInfoPrice = (TextView) rootView.findViewById(R.id.txtCurrencyInfoPrice);
        txtCurrencyInfo1h = (TextView) rootView.findViewById(R.id.txtCurrencyInfo1h);
        txtCurrencyInfo24h = (TextView) rootView.findViewById(R.id.txtCurrencyInfo24h);
        txtCurrencyInfo7d = (TextView) rootView.findViewById(R.id.txtCurrencyInfo7d);
        txtCurrencyInfoLastUpdated = (TextView) rootView.findViewById(R.id.txtCurrencyInfoLastUpdated);

        imgBackButton.setOnClickListener( (view) -> onBackPressed() );

        // fill UI elements from currency data
        int iconResourceId = getDrawableResourceId(currencyData.getSymbol().toLowerCase());
        if (iconResourceId==0) {
            iconResourceId = R.drawable.fail;
        }
        imgCurrencyIcon.setImageDrawable(getResources().getDrawable(iconResourceId));

        txtCurrencyTitle.setText(currencyData.getName());
        txtCurrencyInfoSymbol.setText(currencyData.getSymbol());
        txtCurrencyInfoPrice.setText(currencyData.getQuotes().getUSD().getPrice().toString().substring(0, 7) + " $");
        txtCurrencyInfo1h.setText(String.format("%.2f", currencyData.getQuotes().getUSD().getPercentChange1h()) + " %");
        txtCurrencyInfo1h.setTextColor(currencyData.getQuotes().getUSD().getPercentChange1h()<0 ? Color.RED : Color.CYAN);
        txtCurrencyInfo24h.setText(String.format("%.2f", currencyData.getQuotes().getUSD().getPercentChange24h()) + " %");
        txtCurrencyInfo24h.setTextColor(currencyData.getQuotes().getUSD().getPercentChange24h()<0 ? Color.RED : Color.CYAN);
        txtCurrencyInfo7d.setText(String.format("%.2f", currencyData.getQuotes().getUSD().getPercentChange7d()) + " %");
        txtCurrencyInfo7d.setTextColor(currencyData.getQuotes().getUSD().getPercentChange7d()<0 ? Color.RED : Color.CYAN);
        // Convetr date timestamp
        Date dateObject = new Date(currencyData.getLastUpdated()*1000L);
        txtCurrencyInfoLastUpdated.setText(formatDate(dateObject));

        return rootView;
    }

    /**
     * Fragment back press handler
     * @return
     */
    public boolean onBackPressed() {
        ((MainActivity)getActivity()).changeVisibleFragment(BaseFragment.CURRENCY_LIST_FRAGMENT);
        return true;
    }

    /**
     * Set fragment data list
     * @param cryptoСurrencyData
     */
    @Override
    public void setCurrencyListViewData(List<Data> cryptoСurrencyData) {
    }

    /**
     * Update fragment data list
     * @param cryptoСurrencyData
     */
    @Override
    public void updateCurrencyListView(List<Data> cryptoСurrencyData) {
    }

    /**
     * Show currency additional info
     * @param currencyData currency data
     */
    public void showCurrencyInfo(Data currencyData) {
        this.currencyData = currencyData;
    }

    /**
     * Get drawable resource id by image name
     * @param resourceName  image name
     * @return              icon drawable resource id
     */
    private int getDrawableResourceId(String resourceName) {
        return getResources().getIdentifier(resourceName, "drawable", getActivity().getPackageName());
    }

    /**
     * Format date to DD.MM.YYYY
     * @param dateObject    date object
     * @return              formated string
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format(dateObject);
    }
}
