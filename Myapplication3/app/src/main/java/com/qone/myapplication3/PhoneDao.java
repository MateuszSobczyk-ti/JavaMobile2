package com.qone.myapplication3;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PhoneDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Phone phone);

    @Query("DELETE FROM phone")
    void deletaAll();

    @Query("SELECT * FROM phone ORDER BY producer ASC")
    LiveData<List<Phone>> getAlphabetizedElements();
}
