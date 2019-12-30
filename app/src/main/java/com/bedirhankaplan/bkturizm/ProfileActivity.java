package com.bedirhankaplan.bkturizm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private TextView kisiad;
    private Button listele;
    private  String ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil);

        kisiad = findViewById(R.id.kisiAd);
        listele = findViewById(R.id.Listele);
        listele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentKisiListesi = new Intent(ProfileActivity.this, Kisiler.class);
                startActivity(intentKisiListesi);
            }
        });



    }



}
