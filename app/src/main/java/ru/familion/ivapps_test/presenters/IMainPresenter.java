package ru.familion.ivapps_test.presenters;

public interface IMainPresenter {

    public String getApiEndpoint();
    public void setApiEndpoint(String apiEndpoint);
    void getCryptoCurrecyData(boolean isUpdate);

}
