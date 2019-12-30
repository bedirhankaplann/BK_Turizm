package com.bedirhankaplan.bkturizm;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KayitActivity extends AppCompatActivity {

    private TextView btnGiris,btnKayit;
    private SignInButton btnGoogle;
    private ImageView profileImage;
    private EditText etAd, etSoyad, etPosta, etDogum, etSifre, etTc;
    private Button btnKaydol,btnBiletAl;
    private GoogleSignInClient googleSignInClient;
    private static final String TAG = "AndroidClarified";
    private static final int RC_SIGN_IN = 9001;
    public static final String GOOGLE_ACCOUNT = "google_account";
    private FirebaseAuth mAuth;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kaydol_main);


        mAuth = FirebaseAuth.getInstance();
        //btnGiris = findViewById(R.id.btnKayit);
        btnGoogle = findViewById(R.id.googleBtn);
        profileImage = findViewById(R.id.profileImage);
        etAd = findViewById(R.id.etAd);
        etPosta = findViewById(R.id.etPosta);
        etSoyad = findViewById(R.id.etSoyad);
        etSifre = findViewById(R.id.etSifre);
        etTc = findViewById(R.id.etTc);
        etDogum = findViewById(R.id.etDogum);
        btnBiletAl = findViewById(R.id.btnBiletAl);
        //btnKayit = findViewById(R.id.btnKayit);
        btnKaydol = findViewById(R.id.btnGiris);

        btnBiletAl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBiletAl = new Intent(KayitActivity.this, MainActivity.class);
                startActivity(intentBiletAl);
            }
        });

        btnKaydol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gelenAd = etAd.getText().toString();
                String gelenSoyad = etAd.getText().toString();
                String gelenPosta = etAd.getText().toString();
                String gelenTc = etAd.getText().toString();
                String gelenSifre = etAd.getText().toString();
                String gelenDogum = etAd.getText().toString();

                if(mailDogrulama(etPosta.getText().toString().trim()) )
                {

                    try
                    {
                        Veritabani vt = new Veritabani(KayitActivity.this);
                        vt.KisiEkle(gelenAd, gelenSoyad, gelenPosta, gelenTc, gelenSifre,gelenDogum);

                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }else{
                    Toast.makeText(getApplicationContext(), "Yanlış Format.", Toast.LENGTH_SHORT).show();
                }

                Intent intentProfiles = new Intent(KayitActivity.this, ProfileActivity.class);
                startActivity(intentProfiles);
            }
        });




    }
    public boolean sifreDogrulama(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    private boolean mailDogrulama(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }


}