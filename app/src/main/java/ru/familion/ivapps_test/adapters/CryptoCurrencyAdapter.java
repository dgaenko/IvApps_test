package ru.familion.ivapps_test.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ru.familion.ivapps_test.R;
import ru.familion.ivapps_test.models.entity.Data;

import java.util.List;


/**
 * Data adapter for currency list
 */
public class CryptoCurrencyAdapter extends ArrayAdapter<Data> {
    private Context ctx;

    /**
     * Adapter constructor
     * @param context       context
     * @param currencyList  list with data
     */
    public CryptoCurrencyAdapter(Context context, List<Data> currencyList) {
        super(context, 0, currencyList);
        ctx = context;
    }

    /**
     * Generated row view by lyaour
     * @param position      row position
     * @param convertView   content view
     * @param parent        row parent
     * @return              generated view
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Data currencyData = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.currency_item, parent, false);
        }

        ImageView imgCurrencyIcon = (ImageView)convertView.findViewById(R.id.imgCurrencyIcon);
        TextView txtCurrencySymbol = (TextView)convertView.findViewById(R.id.txtCurrencySymbol);
        TextView txtCurrencyName = (TextView)convertView.findViewById(R.id.txtCurrencyName);
        TextView txtCurrencyPrice = (TextView)convertView.findViewById(R.id.txtCurrencyPrice);
        TextView txt24hValue = (TextView)convertView.findViewById(R.id.txt24hValue);
        TextView txt7dValue = (TextView)convertView.findViewById(R.id.txt7dValue);

        int iconResourceId = getDrawableResourceId(currencyData.getSymbol().toLowerCase());
        if (iconResourceId==0) {
            iconResourceId = R.drawable.fail;
        }
        imgCurrencyIcon.setImageDrawable(ctx.getResources().getDrawable(iconResourceId));

        txtCurrencySymbol.setText(currencyData.getSymbol());
        txtCurrencyName.setText(currencyData.getName());
        txtCurrencyPrice.setText(currencyData.getQuotes().getUSD().getPrice().toString().substring(0,7) + " $");
        txt24hValue.setText(String.format("%.2f", currencyData.getQuotes().getUSD().getPercentChange24h()) + " %");
        txt24hValue.setTextColor(currencyData.getQuotes().getUSD().getPercentChange24h()<0 ? Color.RED : Color.CYAN);
        txt7dValue.setText(String.format("%.2f", currencyData.getQuotes().getUSD().getPercentChange7d()) + " %");
        txt7dValue.setTextColor(currencyData.getQuotes().getUSD().getPercentChange7d()<0 ? Color.RED : Color.CYAN);

        return convertView;
    }

    /**
     * Get drawable resource id by image name for make currency icon
     * @param resourceName  image name
     * @return              drawable resource id
     */
    private int getDrawableResourceId(String resourceName) {
        return ctx.getResources().getIdentifier(resourceName, "drawable", ctx.getPackageName());
    }

}