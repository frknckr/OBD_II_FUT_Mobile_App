package com.andapp.futcorp.obdfut;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment implements SensorEventListener {

    private ImageView compass;

    private float currentDegree = 0f;

    private SensorManager sensorManager;

    TextView textView, acceleration, coolant, intake, speed, load, throttle, boost;


    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        compass = (ImageView) getActivity().findViewById(R.id.compass_test);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        insertDataToFragment();

    }

    @Override
    public void onResume() {
        super.onResume();

        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onPause() {
        super.onPause();

        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float degree = Math.round(event.values[0]);

        RotateAnimation rotateAnimation = new RotateAnimation(currentDegree,
                -degree,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setDuration(210);

        rotateAnimation.setFillAfter(true);

        compass.startAnimation(rotateAnimation);
        currentDegree = -degree;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void insertDataToFragment() {
        DBHelper dbHelper = new DBHelper(getActivity().getApplicationContext());
        HashMap<String, Float> car = dbHelper.carInfo(1);
        acceleration = (TextView) getActivity().findViewById(R.id.accelValue);
        acceleration.setText(getString(R.string.acceleration) + ": " +
                String.valueOf(car.get("acceleration")) + " g");
        coolant = (TextView) getActivity().findViewById(R.id.coolantValue);
        coolant.setText(getString(R.string.coolant) + ": " +
                String.valueOf(car.get("coolant")) + " °C");
        intake = (TextView) getActivity().findViewById(R.id.intakeValue);
        intake.setText(getString(R.string.intake) + ": " +
                String.valueOf(car.get("intake") + " psi"));
        speed = (TextView) getActivity().findViewById(R.id.speedValue);
        speed.setText(getString(R.string.speed) + ": " +
                String.valueOf(car.get("speed")) + " km/s");
        load = (TextView) getActivity().findViewById(R.id.loadValue);
        load.setText(getString(R.string.load) + ": " +
                String.valueOf(car.get("load")) + " %");
        throttle = (TextView) getActivity().findViewById(R.id.throttleValue);
        throttle.setText(getString(R.string.throttle) + ": " +
                String.valueOf(car.get("throttle")) + " %");
        boost = (TextView) getActivity().findViewById(R.id.boostValue);
        boost.setText(getString(R.string.boost) + ": " +
                String.valueOf(car.get("boost")) + " psi");
    }
}
