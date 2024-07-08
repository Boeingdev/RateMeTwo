package com.example.rateme2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.daimajia.androidanimations.library.Techniques;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    TextView topTxt, titleText, angerTxt, happyTxt, sadTxt, neutralTxt, surpriseTxt, contemptTxt, fearTxt;
    ImageView img;

    ConstraintLayout resultCard;

    View[] elements;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //COMP
        topTxt = findViewById(R.id.topTxt);
        titleText = findViewById(R.id.textView);
        angerTxt = findViewById(R.id.angerTxt);
        happyTxt = findViewById(R.id.happyTxt);
        sadTxt = findViewById(R.id.sadTxt);
        neutralTxt = findViewById(R.id.neutralTxt);
        surpriseTxt = findViewById(R.id.surpriseTxt);
        contemptTxt = findViewById(R.id.contemptTxt);
        fearTxt = findViewById(R.id.fearTxt);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.repeat);
        navBarActivity();
        img = findViewById(R.id.img);
        resultCard = findViewById(R.id.resultCard);
        elements = new View[]{topTxt, titleText, angerTxt, happyTxt
                , sadTxt, neutralTxt, surpriseTxt, contemptTxt, fearTxt};

        //ANIM
        loadAnim();

        //NAVBAR

    }

    public void loadAnim() {
        for (int i = 0; i < elements.length; i++) {
            UtilitesAnim.setAnim(Techniques.BounceInUp, 3200, elements[i]);
        }

    }

    public void navBarActivity() {
        bottomNavigationView.setSelectedItemId(R.id.repeat);

        bottomNavigationView.setOnItemSelectedListener(menuItem -> {
                    switch (menuItem.getItemId()) {
                        case R.id.repeat:
                            startActivity(new Intent(getApplicationContext(), cameraActivity.class));
                            return true;
                        case R.id.home:
                            return true;
                        case R.id.next:
                            return true;
                    }
                    return false;
                }
        );

    }

}