package com.andapp.futcorp.obdfut;

import android.content.Context;
import android.widget.Toast;

public class SingleToast {

    private static Toast myToast;

    public static void show(Context context, String string, int duration) {
        if (myToast != null) myToast.cancel();
        myToast = Toast.makeText(context, string, duration);
        myToast.show();
    }
}