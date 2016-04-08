package com.scorpions.beluga;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;

public class ShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_share);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_SUBJECT,"Share");
        //设置分享的内容
        intent.putExtra(Intent.EXTRA_TEXT, "哈喽");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, getTitle()));
    }
}
