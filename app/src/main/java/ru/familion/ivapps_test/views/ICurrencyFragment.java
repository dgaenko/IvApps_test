package ru.familion.ivapps_test.views;

import java.util.List;

import ru.familion.ivapps_test.models.entity.Data;

public interface ICurrencyFragment {

    public void setCurrencyListViewData(List<Data> cryptoСurrencyData);
    public void updateCurrencyListView(List<Data> cryptoСurrencyData);

}
