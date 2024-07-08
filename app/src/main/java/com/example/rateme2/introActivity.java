package com.example.rateme2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.rateme2.databinding.ActivityMainBinding;
import com.google.android.material.button.MaterialButton;

import java.io.File;
import java.util.Objects;

public class introActivity extends AppCompatActivity {

    MaterialButton takePic,nameBtn;
    MediaPlayer mediaPlayer,mediaPlayerName;

    TextView txt,conditions;
    Intent nextActivity;

    String name = "";

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        //COMP
        takePic = findViewById(R.id.takePic);
        nameBtn = findViewById(R.id.nameBtn);

        txt = findViewById(R.id.txt);
        conditions = findViewById(R.id.conditions);
        img = findViewById(R.id.img);
        nextActivity = new Intent(this, cameraActivity.class);
        disableComponents();
        loadAnim();
        set_sound_effects();

        nameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayerName = MediaPlayer.create(getApplicationContext(),R.raw.ui_click_2);
                AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                am.setStreamVolume(AudioManager.STREAM_MUSIC,am.getStreamMaxVolume(AudioManager.STREAM_MUSIC),0);
                mediaPlayerName.start();
                loadNameDialog();
            }
        });

        takePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Objects.equals(name, "")) {
                    nextActivity.putExtra("name",name);
                    startActivity(nextActivity);
                }
                else {
                    Toast.makeText(getApplicationContext(),getString(R.string.introduce_name),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void set_sound_effects() {
        takePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.click_ui);
                mediaPlayer.start();
            }
        });

        nameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayerName = MediaPlayer.create(getApplicationContext(),R.raw.ui_click_2);
                mediaPlayerName.start();
            }
        });
    }

    public void disableComponents () {
        takePic.setVisibility(View.INVISIBLE);
        conditions.setVisibility(View.INVISIBLE);
    }

    public void createAnim(Techniques tech,int duration,View object){
        YoYo.with(tech)
                .duration(duration)
                .repeat(0)
                .playOn(object);
    }

    public void loadAnim() {
        createAnim(Techniques.FadeInRight,1200,img);
        createAnim(Techniques.FadeInRight,1200,txt);
        createAnim(Techniques.FadeInUp,1200,nameBtn);


    }

    public void loadTakePic () {
        takePic.setVisibility(View.VISIBLE);
        conditions.setVisibility(View.VISIBLE);
        createAnim(Techniques.FadeInUp,2000,takePic);
        createAnim(Techniques.Bounce,2000,conditions);
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.click_ui);
        mediaPlayer.start();
    }

    public void loadNameDialog () {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText edittext = new EditText(this);
        alert.setView(R.drawable.rounded_edges);
        alert.setMessage(getString(R.string.let_us_know_name));
        alert.setTitle(getString(R.string.introduce_name));
        alert.setView(edittext);

        alert.setPositiveButton("Confirm name", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String txt = edittext.getText().toString();
                name = txt;
                mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.button_confirm);
                mediaPlayer.start();
                loadTakePic();
            }
        });

        alert.setNegativeButton("Back", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });

        alert.show();
    }
}