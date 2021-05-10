package com.qone.myapplication3;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "phone")
public class Phone {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id_phone")
    private long id_phone;
    @NonNull
    private String producer;
    @NonNull
    private String model;
    @NonNull
    @ColumnInfo(name="android_version")
    private double androidVersion;
    private String url;

    public Phone(@NonNull String producer, @NonNull String model) {
        this.producer = producer;
        this.model = model;
    }

    public long getId_phone() {
        return id_phone;
    }

    @NonNull
    public String getProducer() {
        return producer;
    }

    @NonNull
    public String getModel() {
        return model;
    }

    public double getAndroidVersion() {
        return androidVersion;
    }

    public String getUrl() {
        return url;
    }

    public void setId_phone(long id_phone) {
        this.id_phone = id_phone;
    }

    public void setProducer(@NonNull String producer) {
        this.producer = producer;
    }

    public void setModel(@NonNull String model) {
        this.model = model;
    }

    public void setAndroidVersion(double androidVersion) {
        this.androidVersion = androidVersion;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
