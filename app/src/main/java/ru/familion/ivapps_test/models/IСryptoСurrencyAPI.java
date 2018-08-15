package ru.familion.ivapps_test.models;

import rx.Observable;

import ru.familion.ivapps_test.models.entity.СryptoСurrencyData;

public interface IСryptoСurrencyAPI {

    Observable<СryptoСurrencyData> getСryptoСurrencyData(String apiEndpoint);

}
