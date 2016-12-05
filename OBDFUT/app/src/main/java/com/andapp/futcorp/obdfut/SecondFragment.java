package com.andapp.futcorp.obdfut;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cardiomood.android.controls.gauge.SpeedometerGauge;

import java.math.BigDecimal;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {

    SpeedometerGauge speed, accel, load, intake, throttle, coolant;

    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fs2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DBHelper dbHelper = new DBHelper(getActivity().getApplicationContext());
        HashMap<String,Float> carInfo;
        carInfo = dbHelper.carInfo(1);

        speed = (SpeedometerGauge) getActivity().findViewById(R.id.speed);
        accel = (SpeedometerGauge) getActivity().findViewById(R.id.accel);
        load = (SpeedometerGauge) getActivity().findViewById(R.id.load);
        intake = (SpeedometerGauge) getActivity().findViewById(R.id.intake);
        throttle = (SpeedometerGauge) getActivity().findViewById(R.id.throttle);
        coolant = (SpeedometerGauge) getActivity().findViewById(R.id.coolant);

        speed.setLabelConverter(new SpeedometerGauge.LabelConverter() {
            @Override
            public String getLabelFor(double progress, double maxProgress) {
                return String.valueOf((int) Math.round(progress));
            }
        });

        speed.setMaxSpeed(200);
        speed.setLabelTextSize(30);
        speed.setSpeed(carInfo.get("speed").doubleValue(),true);
        speed.addColoredRange(50, 120, Color.YELLOW);
        speed.addColoredRange(120, 200, Color.RED);
        speed.addColoredRange(0, 50, Color.GREEN);


        accel.setLabelConverter(new SpeedometerGauge.LabelConverter() {
            @Override
            public String getLabelFor(double progress, double maxProgress) {
                Double truncatedDouble = new BigDecimal(progress)
                        .setScale(1, BigDecimal.ROUND_HALF_UP)
                        .doubleValue();
                return String.valueOf(truncatedDouble);
            }
        });

        accel.setMaxSpeed(1);
        accel.setMajorTickStep(0.2);
        accel.setLabelTextSize(30);
        accel.setSpeed(carInfo.get("acceleration"), true);


        coolant.setLabelConverter(new SpeedometerGauge.LabelConverter() {
            @Override
            public String getLabelFor(double progress, double maxProgress) {
                return String.valueOf((int) Math.round(progress));
            }
        });

        coolant.setMaxSpeed(120);
        coolant.setMajorTickStep(20);
        coolant.setLabelTextSize(30);
        coolant.addColoredRange(100, 120, Color.RED);
        coolant.setSpeed(carInfo.get("coolant"), true);

        intake.setLabelConverter(new SpeedometerGauge.LabelConverter() {
            @Override
            public String getLabelFor(double progress, double maxProgress) {
                return String.valueOf((int) Math.round(progress));
            }
        });

        intake.setMaxSpeed(240);
        intake.setMajorTickStep(40);
        intake.setLabelTextSize(30);
        intake.setSpeed(carInfo.get("intake"), true);

        throttle.setLabelConverter(new SpeedometerGauge.LabelConverter() {
            @Override
            public String getLabelFor(double progress, double maxProgress) {
                return String.valueOf((int) Math.round(progress));
            }
        });

        throttle.setMaxSpeed(100);
        throttle.setMajorTickStep(20);
        throttle.setLabelTextSize(30);
        throttle.setSpeed(carInfo.get("throttle"), true);

        load.setLabelConverter(new SpeedometerGauge.LabelConverter() {
            @Override
            public String getLabelFor(double progress, double maxProgress) {
                return String.valueOf((int) Math.round(progress));
            }
        });

        load.setMaxSpeed(100);
        load.setMajorTickStep(20);
        load.setLabelTextSize(30);
        load.setSpeed(carInfo.get("load"), true);

    }
}
