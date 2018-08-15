package ru.familion.ivapps_test.models.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class СryptoСurrencyData {

    @SerializedName("data")
    @Expose
    private Map<String, Data> data;
    @SerializedName("metadata")
    @Expose
    private Metadata metadata;

    public Map<String, Data> getData() {
        return data;
    }

    public void setData(Map<String, Data> data) {
        this.data = data;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

}
