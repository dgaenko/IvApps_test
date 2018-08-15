package ru.familion.ivapps_test.views;

import android.support.v4.app.Fragment;

import java.util.List;

import ru.familion.ivapps_test.models.entity.Data;

/**
 * Base abstract fragment class
 */
public abstract class BaseFragment extends Fragment implements ICurrencyFragment {

    // fragments ID for replacing visible fragment
    public static final int CURRENCY_LIST_FRAGMENT = 0;
    public static final int CURRENCY_INFO_FRAGMENT = 1;

    /**
     * Fragment ID
     */
    public int fragment_id;

    /**
     * Fragment back press handler
     * @return  return true if back press was handled
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
     * @param data  currency data
     */
    public void showCurrencyInfo(Data data) {

    }

}
