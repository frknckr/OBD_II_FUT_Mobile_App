package com.andapp.futcorp.obdfut;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class SettingsPage extends AppCompatActivity {
    String[] settings = {"Veri Sıfırla", "Hata Kodları", "OBD Hakkında", "Çıkış"};
    Integer[] imgid = {
            R.drawable.ic_restart,
            R.drawable.ic_error,
            R.drawable.ic_info,
            R.drawable.ic_exit
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        CustomListAdapter adapter = new CustomListAdapter(this, settings, imgid);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        DBHelper dbHelper = new DBHelper(getApplicationContext());
                        dbHelper.deleteData();
                        SingleToast.show(getApplicationContext(), "Araç verileriniz sıfırlandı",
                                Toast.LENGTH_SHORT);
                        break;
                    case 1:
                        Intent intent = new Intent(SettingsPage.this, FirstFaultCodes.class);
                        SettingsPage.this.startActivity(intent);
                        break;
                    case 2:
                        new AlertDialog.Builder(SettingsPage.this)
                                .setTitle("OBD II FUT")
                                .setMessage(getResources().getString(R.string.obdinfo))
                                .setIcon(R.mipmap.ic_launcher)
                                .show();
                        break;
                    case 3:
                        Intent intent2 = new Intent(Intent.ACTION_MAIN);
                        intent2.addCategory(Intent.CATEGORY_HOME);
                        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent2);
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
