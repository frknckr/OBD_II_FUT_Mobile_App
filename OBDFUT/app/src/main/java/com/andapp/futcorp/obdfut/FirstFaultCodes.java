package com.andapp.futcorp.obdfut;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class FirstFaultCodes extends AppCompatActivity {
    ImageView imageView, imageView1, imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_fault_codes);
        imageView = (ImageView) findViewById(R.id.imageView8);
        imageView1 = (ImageView) findViewById(R.id.imageView18);
        imageView2 = (ImageView)findViewById(R.id.imageView10);
        DBHelper dbHelper = new DBHelper(getApplicationContext());
        dbHelper.deleteFaultCodes();
        dbHelper.insertFaultCode(new Fault(1, "Düşük Yakıt Kapasitesi Düzenleyici Devresi", "P0003", "Aktarma Organları"));
        dbHelper.insertFaultCode(new Fault(1, "Kontak Bobini A Birincil/İkincil Devrelerinde Çalışma Bozukluğu", "P0350", "Aktarma Organları"));
        dbHelper.insertFaultCode(new Fault(0, "Sistem Voltajı Sabit Değil", "P0561", "Aktarma Organları"));
        dbHelper.insertFaultCode(new Fault(0, "Yüksek Hız Kontrolü Devresi", "P0577", "Aktarma Organları"));
        dbHelper.insertFaultCode(new Fault(0, "Düşük Motor Soğutma Suyu Sıcaklığı Devresi", "P2184", "Aktarma Organları"));
        dbHelper.insertFaultCode(new Fault(0, "Yüksek Vites Değişim Zamanı", "P0896", "Aktarma Organları"));
        dbHelper.insertFaultCode(new Fault(0, "Hidrolik Gün Ünitesi Kaçağı", "P2712", "Aktarma Organları"));
        dbHelper.insertFaultCode(new Fault(0, "Yanal İvme Sensöründe Çalşma Bozukluğu", "C1284", "Şasi"));
        dbHelper.insertFaultCode(new Fault(0, "Konum Sensörü Sağlanamıyor", "C2712", "Şasi"));
        dbHelper.insertFaultCode(new Fault(0, "ECU Etkin Değil", "B1342", "Body"));
        dbHelper.insertFaultCode(new Fault(0, "Antene Bağlanılamadı", "B2103", "Body"));



        if (dbHelper.isThereCurrentFault("Aktarma Organları")) {
            imageView.setImageResource(0);
            imageView.setImageResource(R.drawable.motor_notif);
        } else {
            imageView.setImageResource(0);
            imageView.setImageResource(R.drawable.motor_notif_green);
        }
        if (dbHelper.isThereCurrentFault("Body")) {
            imageView1.setImageResource(0);
            imageView1.setImageResource(R.drawable.yakit_notif);
        } else {
            imageView1.setImageResource(0);
            imageView1.setImageResource(R.drawable.yakit_notif_green);
        }
        if (dbHelper.isThereCurrentFault("Şasi")) {
            imageView2.setImageResource(0);
            imageView2.setImageResource(R.drawable.sasi_red);
        } else {
            imageView2.setImageResource(0);
            imageView2.setImageResource(R.drawable.sasi_green);
        }
    }

    public void goToFaultCodesActivity(View view) {
        Intent intent = new Intent(FirstFaultCodes.this, FaultCodesActivity.class);
        FirstFaultCodes.this.startActivity(intent);
    }

    public void goToMotorFaultCodes(View view) {
        Intent intent = new Intent(FirstFaultCodes.this, FaultCodesActivity.class);
        intent.putExtra("part", "Aktarma Organları");
        FirstFaultCodes.this.startActivity(intent);
    }

    public void goToYakitFaultCodes(View view) {
        Intent intent = new Intent(FirstFaultCodes.this, FaultCodesActivity.class);
        intent.putExtra("part", "part1");
        FirstFaultCodes.this.startActivity(intent);
    }

    public void goToFirstPartFaultCodes(View view) {
        Intent intent = new Intent(FirstFaultCodes.this, FaultCodesActivity.class);
        intent.putExtra("part", "Şasi");
        FirstFaultCodes.this.startActivity(intent);
    }

    public void goToSecondPartFaultCodes(View view) {
        Intent intent = new Intent(FirstFaultCodes.this, FaultCodesActivity.class);
        intent.putExtra("part", "Body");
        FirstFaultCodes.this.startActivity(intent);
    }

}
