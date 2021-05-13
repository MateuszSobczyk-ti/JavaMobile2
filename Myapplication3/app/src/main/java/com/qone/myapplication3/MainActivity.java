package com.qone.myapplication3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private PhoneViewModel phoneViewModel;
    private MyRecyclerViewAdapter adapter;
    private FloatingActionButton fab;
    private String producer, model, version, site;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.rvPhones);
        adapter = new MyRecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        phoneViewModel = new ViewModelProvider(this).get(PhoneViewModel.class);

        phoneViewModel.getAllPhones().observe(this, phones -> {
            adapter.setPhoneList(phones);
        });

        fab = findViewById(R.id.fabMain);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencja = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intencja,1);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id  = item.getItemId();
        if(id == R.id.first_option){
            phoneViewModel.deleteAll();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int kodZadania, int kodZakonczenia, Intent wyniki) {
        super.onActivityResult(kodZadania, kodZakonczenia, wyniki);

        if (kodZakonczenia == RESULT_CANCELED) {
            System.out.println("++++++++++++++++++++++   canceled    ++++++++++++++++++++++++");
        }

        if (kodZakonczenia == RESULT_OK) {
            Bundle pakunek = wyniki.getExtras();
            producer = pakunek.getString("producer");
            model = pakunek.getString("model");
            version = pakunek.getString("version");
            site = pakunek.getString("site");
            System.out.println("+++++++++++++   data :  ++++++++++++++++");
            System.out.println(producer+model+version+site);

            Phone p1 = new Phone(producer,model);
            p1.setAndroidVersion(version);
            p1.setUrl(site);
            phoneViewModel.insert(p1);

        }
    }
}