package com.qone.myapplication3;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PhoneViewModel extends AndroidViewModel {
    private final PhoneRepository repository;
    private final LiveData<List<Phone>> allPhones;

    public PhoneViewModel(@NonNull Application application){
        super(application);
        repository = new PhoneRepository(application);
        allPhones = repository.getAllPhones();
    }

    LiveData<List<Phone>> getAllPhones(){
        return allPhones;
    }
    public void deleteAll(){
        repository.deleteAll();
    }
    public void insert(Phone phone){repository.insert(phone);}
}
