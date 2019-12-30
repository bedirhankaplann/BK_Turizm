package com.bedirhankaplan.bkturizm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class Kisiler extends AppCompatActivity {

    private ListView kisiListesi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kisiler);

        kisiListesi = findViewById(R.id.kisiListesi);
        Listele();

    }

    public void Listele(){
        Veritabani vt = new Veritabani(Kisiler.this);
        List<String> list = vt.KisiListele();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(Kisiler.this, android.R.layout.simple_list_item_1,android.R.id.text1,list);
        kisiListesi.setAdapter(adapter);
        System.out.println();
        System.out.println();
    }
}
