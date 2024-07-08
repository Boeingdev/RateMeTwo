package com.example.rateme2;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.Manifest;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.rateme2.databinding.ActivityMainBinding;

import java.io.File;

public class cameraActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;
    private static final int CAMERA_PERMISSION_CODE = 1;

    ImageView img;
    ActivityResultLauncher<Uri> takePictureLauncher;

    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        //COMP
        img = findViewById(R.id.img);


        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        imageUri = createUri();
        registerPictureLauncher();

        checkCameraPermissionAndOpenCamera();

    }

    private Uri createUri() {
        File imageFile = new File(getApplicationContext().getFilesDir(),"camera_photo.jpg");
        return FileProvider.getUriForFile(getApplicationContext(),
                "com.example.rateme2.fileProvider",imageFile);

    }

    private void registerPictureLauncher () {
        takePictureLauncher = registerForActivityResult(
                new ActivityResultContracts.TakePicture(), new ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult(Boolean o) {
                        try {
                            if (o) {
                                mainBinding.img.setImageURI(null);  // RETURNS TO MAINACTIVITY !!
                                mainBinding.img.setImageURI(imageUri);
                            }
                        } catch (Exception e) {
                         Toast.makeText(getApplicationContext(),getString(R.string.something_wrong),Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    private void checkCameraPermissionAndOpenCamera () {
        if (ActivityCompat.checkSelfPermission(cameraActivity.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(cameraActivity.this,
                    new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        } else {
            takePictureLauncher.launch(imageUri);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                takePictureLauncher.launch(imageUri);
            } else {
                Toast.makeText(this,getString(R.string.camera_permission_denied),Toast.LENGTH_SHORT).show();
            }
        }
    }
}