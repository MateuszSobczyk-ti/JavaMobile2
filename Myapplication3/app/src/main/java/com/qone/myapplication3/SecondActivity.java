package com.qone.myapplication3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private Button cancel,save,openWeb;
    private EditText producer, model, version, site;
    private String producerS, modelS, versionS, siteS;
    private List<Boolean> errorList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        errorList.add(false);
        errorList.add(false);
        errorList.add(false);
        errorList.add(false);

        producer = findViewById(R.id.editTextTextPersonName);
        model = findViewById(R.id.editTextTextPersonName2);
        version = findViewById(R.id.editTextNumberDecimal);
        site = findViewById(R.id.editTextTextPersonName3);

        cancel = findViewById(R.id.button2);
        save = findViewById(R.id.button3);
        openWeb = findViewById(R.id.button);

        long id = 0;
        Bundle pack = getIntent().getExtras();
        if(pack!=null){
            errorList.set(0,true);
            errorList.set(1,true);
            errorList.set(2,true);
            errorList.set(3,true);
            id = pack.getLong("id");
            producer.setText(pack.getString("producer"));
            model.setText(pack.getString("model"));
            version.setText(pack.getString("version"));
            site.setText(pack.getString("site"));
            save.setText("Update");
        }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle pack=new Bundle();
                Intent intent = new Intent();
                setResult(RESULT_CANCELED,intent);
                finish();
            }
        });


        long finalId = id;
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!errorList.contains(false)) {
                    producerS = producer.getText().toString();
                    modelS = model.getText().toString();
                    versionS = version.getText().toString();
                    siteS = site.getText().toString();

                    //finish this activity and go to MainActivity
                    Bundle pack = new Bundle();
                    //load data to pack
                    if(finalId !=0){
                        pack.putLong("id", finalId);
                    }
                    pack.putString("producer", producerS);
                    pack.putString("model", modelS);
                    pack.putString("version", versionS);
                    pack.putString("site", siteS);
                    Intent intent = new Intent();
                    intent.putExtras(pack);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                else{
                    Toast.makeText(SecondActivity.this, "Popraw wprowadzone dane", Toast.LENGTH_LONG).show();
                }
            }
        });


        openWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siteS = site.getText().toString();
                if(siteS.startsWith("http:")) {
                    Intent przegladarka = new Intent("android.intent.action.VIEW", Uri.parse(siteS));
                    startActivity(przegladarka);
                }
                else
                    site.setError("Field must start with 'http:'");
            }
        });

        producer.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                producerS = producer.getText().toString();
                if (!hasFocus) {
                    if (producerS.length() == 0) {
                        producer.setError("Field cannot be empty");
                        errorList.set(0,false);
                    }
                    else
                        errorList.set(0,true);
                }
            }
        });

        model.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                modelS = model.getText().toString();
                if (!hasFocus) {
                    if (modelS.length() == 0) {
                        model.setError("Field cannot be empty");
                        errorList.set(1,false);
                    }
                    else
                        errorList.set(1,true);
                }
            }
        });

        version.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                versionS = version.getText().toString();
                if (!hasFocus) {
                    if (versionS.length() == 0) {
                        version.setError("Field cannot be empty");
                        errorList.set(2,false);
                    }
                    else
                        errorList.set(2,true);
                }
            }
        });

        site.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                siteS = site.getText().toString();
                if (!hasFocus) {
                    if (!siteS.startsWith("http:")) {
                        site.setError("Field must start with 'http:'");
                        errorList.set(3,false);
                    }
                    else
                        errorList.set(3,true);
                }
            }
        });

    }
}
