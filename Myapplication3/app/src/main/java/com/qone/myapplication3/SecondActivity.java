package com.qone.myapplication3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private Button cancel,save;
    private EditText producer, model, version, site;
    private String producerS, modelS, versionS, siteS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        Bundle pack = getIntent().getExtras();

        producer = findViewById(R.id.editTextTextPersonName);
        model = findViewById(R.id.editTextTextPersonName2);
        version = findViewById(R.id.editTextNumberDecimal);
        site = findViewById(R.id.editTextTextPersonName3);

        cancel = findViewById(R.id.button2);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle pack=new Bundle();
                Intent intent = new Intent();
                setResult(RESULT_CANCELED,intent);
                finish();
            }
        });

        save = findViewById(R.id.button3);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                producerS = producer.getText().toString();
                modelS = model.getText().toString();
                versionS = version.getText().toString();
                siteS = site.getText().toString();

                //finish this activity and go to MainActivity
                Bundle pack=new Bundle();
                //load data to pack
                pack.putString("producer",producerS);
                pack.putString("model",modelS);
                pack.putString("version",versionS);
                pack.putString("site",siteS);
                Intent intent = new Intent();
                intent.putExtras(pack);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }
}
