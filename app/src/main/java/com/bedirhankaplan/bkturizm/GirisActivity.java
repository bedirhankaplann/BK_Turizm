package com.bedirhankaplan.bkturizm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.SignInButton;

import java.util.HashMap;
import java.util.List;

public class GirisActivity extends AppCompatActivity {

    private TextView btnKayit;
    private SignInButton btnGoogle;
    private ImageView profileImage;
    private EditText etAd, etSoyad, etPosta, etDogum, etSifre, etTc;
    private Button btnGiris,btnBiletAl;
    private GoogleSignInClient googleSignInClient;
    private static final String TAG = "AndroidClarified";
    private static final int RC_SIGN_IN = 9001;
    public static final String GOOGLE_ACCOUNT = "google_account";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giris_main);



        btnGoogle = findViewById(R.id.googleBtn);
        profileImage = findViewById(R.id.profileImage);
        etAd = findViewById(R.id.etAd);
        etPosta = findViewById(R.id.etPosta);
        etSoyad = findViewById(R.id.etSoyad);
        etSifre = findViewById(R.id.etSifre);
        etTc = findViewById(R.id.etTc);
        etDogum = findViewById(R.id.etDogum);
        btnBiletAl = findViewById(R.id.btnBiletAl);
        btnKayit = findViewById(R.id.btnKayit);
        btnGiris =  findViewById(R.id.btnGiris);

        btnBiletAl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBiletAl = new Intent(GirisActivity.this, MainActivity.class);
                startActivity(intentBiletAl);
            }
        });

        btnKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentKayitOl = new Intent(GirisActivity.this, KayitActivity.class);
                startActivity(intentKayitOl);
            }
        });

        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Veritabani vt = new Veritabani(GirisActivity.this);
                List<String> kisiler = vt.kisiSorgu(etPosta.getText().toString(), etSifre.getText().toString());
                String eposta = etPosta.getText().toString();
                String sifre = etSifre.getText().toString();

                if (eposta == kisiler.get(0) && sifre == kisiler.get(1))
                {
                    Toast.makeText(GirisActivity.this, "Başarıyla Giriş Yapıldı", Toast.LENGTH_LONG).show();
                    Intent intentGiris = new Intent(GirisActivity.this, MainActivity.class);
                    startActivity(intentGiris);
                } else {
                    Toast.makeText(GirisActivity.this, "Hesap Bulunamadı", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public List<String> Listele() {
        Veritabani vt = new Veritabani(GirisActivity.this);
        List<String> list = vt.VeriListele();
        return list;
    }
}
