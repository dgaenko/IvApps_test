package ru.familion.ivapps_test.views;

import java.util.List;

import ru.familion.ivapps_test.models.entity.Data;

public interface IMainView extends ICurrencyFragment {

    void hideLoadingIndicator();
    void showEmptyDatasourceMessage();
    void showNoConnectionMessage();
    void showRequestErrorMessage();

}
