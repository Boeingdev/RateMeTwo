package com.example.rateme2;

import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class UtilitesAnim {

    public static void setAnim (Techniques techniques, int duration, View object) {
        YoYo.with(techniques)
                .duration(duration)
                .repeat(0)
                .playOn(object);
    }

    public static void hideAnimations (View [] elements) {
        for (View element : elements) {
            element.setVisibility(View.INVISIBLE);
        }
    }


}
