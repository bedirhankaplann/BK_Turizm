package com.bedirhankaplan.bkturizm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class BiletlerimActivity extends AppCompatActivity {

    private Button btnBiletAl, btnHesabim;
    private ListView VeriListele;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biletlerim_main);

        btnBiletAl = findViewById(R.id.btnBiletAl);
        btnHesabim = findViewById(R.id.btnHesabim);
        VeriListele = findViewById(R.id.VeriListele);

        Listele();

        btnBiletAl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBiletAl = new Intent(BiletlerimActivity.this, MainActivity.class);
                startActivity(intentBiletAl);
            }
        });

        btnHesabim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHesabim = new Intent(BiletlerimActivity.this, GirisActivity.class);
                startActivity(intentHesabim);
            }
        });
    }
    public void Listele(){
        Veritabani vt = new Veritabani(BiletlerimActivity.this);
        List<String> list = vt.VeriListele();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(BiletlerimActivity.this, android.R.layout.simple_list_item_1,android.R.id.text1,list);
        VeriListele.setAdapter(adapter);
    }
}
