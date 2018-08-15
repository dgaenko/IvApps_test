package ru.familion.ivapps_test.views;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import ru.familion.ivapps_test.R;
import ru.familion.ivapps_test.models.entity.Data;
import ru.familion.ivapps_test.presenters.IMainPresenter;
import ru.familion.ivapps_test.presenters.MainPresenter;


/**
 * App main activity
 */
public class MainActivity extends AppCompatActivity implements IMainView {

    /**
     * Activity presenter
     */
    private IMainPresenter _presenter;

    // fragment management fields
    private FragmentManager fragmentManager;
    /**
     * Current visible fragment
     */
    private BaseFragment currentFragment;
    /**
     * Activity fragments list
     */
    private ArrayList<BaseFragment> fragments;

    /**
     * Data list from remote API
     */
    private List<Data> _currencyData;

    // UI elements
    private TextView _oopsTextView;
    private ProgressBar _loadingIndicator;


    /**
     * Activity create handler
     * @param savedInstanceState    saved activity state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragmentsManagement();

        // Caching UI elements
        _oopsTextView = (TextView) findViewById(R.id.oopsTextView);
        _loadingIndicator = (ProgressBar) findViewById(R.id.loading_spinner);
        _loadingIndicator.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(this, R.color.colorPrimaryDark), PorterDuff.Mode.MULTIPLY);

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // check connection state
        if ( networkInfo != null && networkInfo.isConnected() ) {
            // connected
            if (_presenter == null) _presenter = new MainPresenter(this);
            _presenter.setApiEndpoint(getString(R.string.api_endpoint));
            _presenter.getCryptoCurrecyData(false);
        } else {
            // not connected
            showNoConnectionMessage();
        }

    }

    /**
     * initialize fragment management
     */
    private void initFragmentsManagement() {
        fragmentManager = getSupportFragmentManager();
        fragments = new ArrayList<>(2);
        fragments.add(BaseFragment.CURRENCY_LIST_FRAGMENT, CurrencyListFragment.getInstance());
        fragments.add(BaseFragment.CURRENCY_INFO_FRAGMENT, CurrencyInfoFragment.getInstance());
        changeVisibleFragment(BaseFragment.CURRENCY_LIST_FRAGMENT);
    }

    /**
     * Android back press evant handler
     */
    @Override
    public void onBackPressed() {
        if (!currentFragment.onBackPressed()) {
            super.onBackPressed();
        }
    }

    /**
     * Change visible activity fragment
     * @param fragment_id   fragment id (see BaseFragment consts)
     * @return              visible fragment instance
     */
    public BaseFragment changeVisibleFragment(int fragment_id) {

        currentFragment = fragments.get(fragment_id);
        currentFragment.fragment_id = fragment_id;
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.replace(R.id.flFragmentContainer, currentFragment);
        ft.commit();

        return currentFragment;
    }

    /**
     * Set fragment data list
     * @param currencyList
     */
    @Override
    public void setCurrencyListViewData(List<Data> currencyList) {
        _currencyData = currencyList;
        currentFragment.setCurrencyListViewData(currencyList);
    }

    /**
     * Update fragment data list
     * @param currencyList
     */
    @Override
    public void updateCurrencyListView(List<Data> currencyList) {
        _currencyData = currencyList;
        currentFragment.updateCurrencyListView(currencyList);
    }

    /**
     * Hide loading data indcator
     */
    @Override
    public void hideLoadingIndicator() {
        _loadingIndicator.setVisibility(View.GONE);
    }

    /**
     * Show error message for empty data
     */
    @Override
    public void showEmptyDatasourceMessage() {
        hideLoadingIndicator();
        _oopsTextView.setText(R.string.datasource_is_empty);
    }

    /**
     * Show no connection error message
     */
    @Override
    public void showNoConnectionMessage() {
        hideLoadingIndicator();
        _oopsTextView.setText(R.string.no_internet_connection);
    }

    /**
     * Show error API request error message
     */
    @Override
    public void showRequestErrorMessage() {
        hideLoadingIndicator();
        _oopsTextView.setText(R.string.request_error);
    }
}
