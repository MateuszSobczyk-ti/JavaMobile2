package com.qone.myapplication3;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Phone.class}, version = 1,exportSchema = false)
public abstract class PhoneRoomDatabase extends RoomDatabase {
    public abstract PhoneDao phoneDao();

    private static volatile PhoneRoomDatabase INSTANCE;

    static PhoneRoomDatabase getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (PhoneRoomDatabase.class){
                if(INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),PhoneRoomDatabase.class,"baza2").addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
    private static final int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            databaseWriteExecutor.execute(()->{
                PhoneDao dao = INSTANCE.phoneDao();
                Phone p1 = new Phone("Oppo","A512");
                Phone p2 = new Phone("Realme", "7i");
                Phone p3 = new Phone("Motorola", "g9 play");
                dao.insert(p1);
                dao.insert(p2);
                dao.insert(p3);


            });
        }
    };
}
