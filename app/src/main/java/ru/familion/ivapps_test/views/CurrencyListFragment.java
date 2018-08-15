package ru.familion.ivapps_test.views;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.familion.ivapps_test.R;
import ru.familion.ivapps_test.adapters.CryptoCurrencyRecyclerAdapter;
import ru.familion.ivapps_test.models.entity.Data;

/**
 * Currency list fragment class
 */
public class CurrencyListFragment extends BaseFragment {

    // fragment layout resource id
    private static final int layout_res_id = R.layout.currency_list_fragment;

    // fragment UI views
    private View rootView;
    private RecyclerView _cryptoCurrencyListView;

    // fragment data list
    private List<Data> _currencyData;
    private CryptoCurrencyRecyclerAdapter _adapter;


    /**
     * Constructor
     */
    public CurrencyListFragment() {
    }

    /**
     * Make currency list fragment instance
     * @return  fragment instance
     */
    public static CurrencyListFragment getInstance() {
        Bundle args = new Bundle();
        CurrencyListFragment fragment = new CurrencyListFragment();
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

        _cryptoCurrencyListView = (RecyclerView)rootView.findViewById(R.id.currency_list_view);

        return rootView;
    }

    /**
     * Fragment back press handler
     * @return
     */
    public boolean onBackPressed() {
        return false;
    }

    /**
     * Set fragment data list
     * @param cryptoСurrencyData
     */
    @Override
    public void setCurrencyListViewData(List<Data> cryptoСurrencyData) {
        _currencyData = cryptoСurrencyData;
        _adapter = new CryptoCurrencyRecyclerAdapter(getActivity(), cryptoСurrencyData);
        // set list row click listener
        _adapter.setOnItemClickListener( (position, view) -> {
            BaseFragment fragment = ((MainActivity)getActivity()).changeVisibleFragment(BaseFragment.CURRENCY_INFO_FRAGMENT);
            fragment.showCurrencyInfo(_currencyData.get(position));
        });
        _cryptoCurrencyListView.setAdapter(_adapter);
    }

    /**
     * Update fragment data list
     * @param cryptoСurrencyData
     */
    @Override
    public void updateCurrencyListView(List<Data> cryptoСurrencyData) {
        _currencyData = cryptoСurrencyData;
        _adapter.notifyDataSetChanged();
    }
}
