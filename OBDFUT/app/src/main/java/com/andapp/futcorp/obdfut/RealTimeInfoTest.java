package com.andapp.futcorp.obdfut;

import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import me.relex.circleindicator.CircleIndicator;


public class RealTimeInfoTest extends AppCompatActivity {
    FragmentPagerAdapter fragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_time_info_test);
        ViewPager viewPager = (ViewPager) findViewById(R.id.vpPager);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        fragmentPagerAdapter = new MyPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentPagerAdapter);
        indicator.setViewPager(viewPager);
        DBHelper dbHelper = new DBHelper(getApplicationContext());
        dbHelper.deleteData();
        dbHelper.insertInfo(new Car(0.7f, 54.0f, 4.5f, 100.0f, 23.1f, 12.2f, -10.2f, 1000f));
        dbHelper.deleteModelsData();
        dbHelper.insertCarModel(new Emission(179, "Alfa Romeo 159"));
        dbHelper.insertCarModel(new Emission(110, "Mercedes X"));
        dbHelper.insertCarModel(new Emission(145, "Honda Civic"));
        dbHelper.insertCarModel(new Emission(160, "Renault Megan"));
        dbHelper.insertCarModel(new Emission(220, "Ford Focus"));
        dbHelper.insertCarModel(new Emission(285, "Cherokee Jeep"));
        dbHelper.insertCarModel(new Emission(119, "Opel Astra"));
        dbHelper.insertCarModel(new Emission(146, "Volkswagen Passat"));
        dbHelper.insertCarModel(new Emission(106, "BMW 420d"));
        dbHelper.insertCarModel(new Emission(370, "Lamborghini Aventador"));
    }

    public static class MyPageAdapter extends FragmentPagerAdapter {

        private static int NUM_ITEMS = 3;

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new FirstFragment();
                case 1:
                    return new SecondFragment();
                case 2:
                    return new ThirdFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }
    }

    public void resetDB(View view) {
        DBHelper dbHelper = new DBHelper(getApplicationContext());
        dbHelper.deleteData();

        Fragment frg = fragmentPagerAdapter.getItem(0);
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.detach(frg);
        ft.attach(frg);
        ft.commit();

        SingleToast.show(getApplicationContext(), "Araç verileriniz sıfırlandı",
                Toast.LENGTH_SHORT);
    }

    public void calculate(View view) {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        EditText totalKm = (EditText) findViewById(R.id.editText);
        int n, nOfPeople;
        float result;
        DBHelper dbHelper = new DBHelper(getApplicationContext());
        n = dbHelper.getCO2(String.valueOf(spinner.getSelectedItem()));
        nOfPeople = Integer.valueOf(String.valueOf(spinner2.getSelectedItem()));
        if (String.valueOf(totalKm.getText()) != null && !String.valueOf(totalKm.getText()).isEmpty()) {
            result = ((n * Float.valueOf(String.valueOf(totalKm.getText()))) / 1000) / nOfPeople;

            new AlertDialog.Builder(RealTimeInfoTest.this)
                    .setTitle(R.string.co2)
                    .setMessage(getResources().getString(R.string.co2_desc_1) + " "
                            + String.valueOf(result) + " kg" +
                            (getResources().getString(R.string.co2_desc_2)))
                    .setIcon(R.drawable.ic_co_button)
                    .show();
        } else {
            SingleToast.show(this, "Lütfen bir değer giriniz", Toast.LENGTH_LONG);
        }
    }
}
