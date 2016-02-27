package com.scorpions.beluga;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;

public class CameraActivity extends AppCompatActivity {

    private static int REQ_1 = 1;
    private static int REQ_2 = 2;
    private String mFilePath1;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        mFilePath1 = Environment.getExternalStorageDirectory().getPath();
        mFilePath1 = mFilePath1 + "/" + "temp1.png";
    }

    public void startCamera(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri photoUri = Uri.fromFile(new File(mFilePath1));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        startActivityForResult(intent, REQ_1);
    }
}
