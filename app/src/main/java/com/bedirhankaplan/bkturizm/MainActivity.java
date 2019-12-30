package com.bedirhankaplan.bkturizm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private AutoCompleteTextView completeNereden, completeNereye;
    private Button  btnBiletAl, btnBilet, btnHesap,btnGidis, btnGidisDonus,btnBiletAra;
    private String[] sehirler;
    private TextView tvTarih, tvTarih2, btnTarihSec, btnTarihSec2, gidisTarihi, donusTarihi,btnDonus;
    private Switch biletTipi;
    private SignInButton googleBtn;
    Context context = this;
    private boolean touched = true;
    private ViewGroup container;
    private AutoCompleteTextView spinnerNereden, spinnerNereye;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        completeNereden = findViewById(R.id.spinnerNereden);
        sehirler = getResources().getStringArray(R.array.sehirler);
        ArrayAdapter<String> adapterNereden = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, sehirler);
        completeNereden.setAdapter(adapterNereden);

        completeNereye = findViewById(R.id.spinnerNereye);
        sehirler = getResources().getStringArray(R.array.sehirler);
        ArrayAdapter<String> adapterNereye = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, sehirler);
        completeNereye.setAdapter(adapterNereden);

        spinnerNereden = findViewById(R.id.spinnerNereden);
        spinnerNereye = findViewById(R.id.spinnerNereye);
        btnBiletAl = findViewById(R.id.biletAl);
        btnBilet = findViewById(R.id.biletlerim);
        btnHesap = findViewById(R.id.hesabim);
        btnTarihSec =  findViewById(R.id.btnTarihSec);
        tvTarih =  findViewById(R.id.tvTarih);
        btnTarihSec2 =  findViewById(R.id.btnTarihSec2);
        tvTarih2 =  findViewById(R.id.tvTarih2);
        gidisTarihi = findViewById(R.id.tvDonusTarihi);
        donusTarihi = findViewById(R.id.tvDonusTarihi);
        btnDonus = findViewById(R.id.btnTarihSec2);
        container = findViewById(R.id.relativeLayout);
        btnGidis = findViewById(R.id.btnGidis);
        btnGidisDonus = findViewById(R.id.btnGidisDonus);
        btnBiletAra = findViewById(R.id.btnBiletAra);

        btnGidis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDonus.setVisibility(View.INVISIBLE);
                donusTarihi.setVisibility(View.INVISIBLE);
            }
        });
        btnGidisDonus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDonus.setVisibility(View.VISIBLE);
                donusTarihi.setVisibility(View.VISIBLE);
            }
        });

        btnBilet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenBiletlerim = new Intent(MainActivity.this,BiletlerimActivity.class);
                startActivity(intenBiletlerim);
            }
        });

        btnBiletAra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        String gelenNereden = spinnerNereden.getText().toString();
                        String gelenNereye = spinnerNereye.getText().toString();
                        String gelenGidis = tvTarih.getText().toString();


                        Veritabani vt = new Veritabani(MainActivity.this);
                        vt.BiletEkle(gelenNereden, gelenNereye, gelenGidis);
                    }
                });


        btnHesap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHesabim = new Intent(MainActivity.this, GirisActivity.class);
                startActivity(intentHesabim);
            }
        });

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        btnBiletAl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Bilet Al Ekranı",Toast.LENGTH_LONG ).show();
            }
        });



        btnTarihSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Şimdiki zaman bilgilerini alıyoruz. güncel yıl, güncel ay, güncel gün.
                final Calendar takvim = Calendar.getInstance();
                int yil = takvim.get(Calendar.YEAR);
                int ay = takvim.get(Calendar.MONTH);
                int gun = takvim.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(context,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // ay değeri 0 dan başladığı için (Ocak=0, Şubat=1,..,Aralık=11)
                                // değeri 1 artırarak gösteriyoruz.
                                month += 1;
                                // year, month ve dayOfMonth değerleri seçilen tarihin değerleridir.
                                // Edittextte bu değerleri gösteriyoruz.
                                tvTarih.setText(dayOfMonth + "/" + month + "/" + year);
                            }
                        }, yil, ay, gun);
                // datepicker açıldığında set edilecek değerleri buraya yazıyoruz.
                // şimdiki zamanı göstermesi için yukarda tanmladığımz değşkenleri kullanyoruz.

                // dialog penceresinin button bilgilerini ayarlıyoruz ve ekranda gösteriyoruz.
                dpd.setButton(DatePickerDialog.BUTTON_POSITIVE, "Seç", dpd);
                dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", dpd);
                dpd.show();

            }
        });


        btnTarihSec2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Şimdiki zaman bilgilerini alıyoruz. güncel yıl, güncel ay, güncel gün.
                final Calendar takvim = Calendar.getInstance();
                int yil = takvim.get(Calendar.YEAR);
                int ay = takvim.get(Calendar.MONTH);
                int gun = takvim.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(context,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // ay değeri 0 dan başladığı için (Ocak=0,7, Şubat=1,..,Aralık=11)
                                // değeri 1 artırarak gösteriyoruz.
                                month += 1;
                                // year, month ve dayOfMonth değerleri seçilen tarihin değerleridir.
                                // Edittextte bu değerleri gösteriyoruz.
                                tvTarih2.setText(dayOfMonth + "/" + month + "/" + year);
                            }
                        }, yil, ay, gun);
                // datepicker açıldığında set edilecek değerleri buraya yazıyoruz.
                // şimdiki zamanı göstermesi için yukarda tanmladığımz değşkenleri kullanyoruz.

                // dialog penceresinin button bilgilerini ayarlıyoruz ve ekranda gösteriyoruz.
                dpd.setButton(DatePickerDialog.BUTTON_POSITIVE, "Seç", dpd);
                dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", dpd);
                dpd.show();

            }
        });

    }
    public List<String> listele()
    {
        List<String> veriler = new ArrayList<>();

        veriler.add((String) tvTarih.getText());
        veriler.add(String.valueOf(spinnerNereden.getText()));
        veriler.add(String.valueOf(spinnerNereye.getText()));

        return veriler;
    }


}
