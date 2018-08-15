package ru.familion.ivapps_test.presenters;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import ru.familion.ivapps_test.models.entity.Data;
import ru.familion.ivapps_test.models.entity.СryptoСurrencyData;
import ru.familion.ivapps_test.models.СryptoСurrencyAPI;
import ru.familion.ivapps_test.views.IMainView;

/**
 * Main activity presenter
 */
public class MainPresenter implements IMainPresenter {

    /**
     * API endpoint
     */
    private String apiEndpoint;

    /**
     * API repository
     */
    private final СryptoСurrencyAPI _cryptoCurrencyAPI;
    /**
     * Main view
     */
    private final IMainView _view;


    /**
     * Get API endpoint
     * @return  API endpoint
     */
    public String getApiEndpoint() {
        return apiEndpoint;
    }

    /**
     * Set API endpoint
     * @param apiEndpoint   API endpoint
     */
    public void setApiEndpoint(String apiEndpoint) {
        this.apiEndpoint = apiEndpoint;
    }


    /**
     * Main presenter constructor
     * @param view
     */
    public MainPresenter(IMainView view) {
        _view = view;
        _cryptoCurrencyAPI = new СryptoСurrencyAPI();
    }

    /**
     * Request data from remote API
     * @param isUpdate  data is updated
     */
    @Override
    public void getCryptoCurrecyData(boolean isUpdate) {
        Observable<СryptoСurrencyData> dataObservable = _cryptoCurrencyAPI.getСryptoСurrencyData(apiEndpoint);

        dataObservable.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                // success requesr handler
                cryptoCurrencyData -> {
                    _view.hideLoadingIndicator();
                    List<Data> datas = new ArrayList<>();
                    datas.addAll(cryptoCurrencyData.getData().values());
                    // data is empty
                    if (datas.isEmpty())
                        _view.showEmptyDatasourceMessage();
                    else
                    // data is updated
                    if (isUpdate)
                        _view.updateCurrencyListView(datas);
                    // initialize list view
                    else
                        _view.setCurrencyListViewData(datas);
                },
                // request error handler
                error -> {
                    _view.showRequestErrorMessage();
                }
            );
    }
}
