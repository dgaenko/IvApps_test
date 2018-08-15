package ru.familion.ivapps_test.models;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.familion.ivapps_test.models.entity.СryptoСurrencyData;
import rx.Observable;

/**
 * Remote API repository
 */
public class СryptoСurrencyAPI implements IСryptoСurrencyAPI {

    /**
     * Request remote data from API
     * @param apiEndpoint   API endpoint
     * @return              Observable data object
     */
    @Override
    public Observable<СryptoСurrencyData> getСryptoСurrencyData(String apiEndpoint) {

        Retrofit retrofit = new Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(apiEndpoint)
            .build();

        IСryptoСurrencyService currencyDataService = retrofit.create(IСryptoСurrencyService.class);

        return currencyDataService.getCurrencyData();
    }

}
