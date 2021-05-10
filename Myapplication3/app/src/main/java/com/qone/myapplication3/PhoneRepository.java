package com.qone.myapplication3;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PhoneRepository {
    private PhoneDao phoneDao;
    private LiveData<List<Phone>> allPhones;

    PhoneRepository(Application application){
        PhoneRoomDatabase phoneRoomDatabase = PhoneRoomDatabase.getDatabase(application);
        phoneDao = phoneRoomDatabase.phoneDao();
        allPhones = phoneDao.getAlphabetizedElements();
    }
    LiveData<List<Phone>> getAllPhones(){
        return allPhones;
    }

    void deleteAll(){
        PhoneRoomDatabase.databaseWriteExecutor.execute(()->{
            phoneDao.deletaAll();
        });
    }
}
