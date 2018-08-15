package ru.familion.ivapps_test.models;

import retrofit2.http.GET;
import rx.Observable;
import ru.familion.ivapps_test.models.entity.СryptoСurrencyData;

public interface IСryptoСurrencyService {

    @GET("./")
    Observable<СryptoСurrencyData> getCurrencyData();

}
