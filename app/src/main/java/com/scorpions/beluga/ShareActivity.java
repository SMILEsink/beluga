package com.scorpions.beluga;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;

public class ShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_share);
        //ACTION_SEND，该action表明该intent用于从一个activity发送数据到另外一个activity的，甚至可以是跨进程之间的数据发送。
        Intent intent = new Intent(Intent.ACTION_SEND);
        //指定数据的类型
        intent.setType("text/plain");
        //标题
        String title = (String) getResources().getText(R.string.share_to);
        //设置分享的内容
        intent.putExtra(Intent.EXTRA_TEXT, "链接");
        Intent chooser = Intent.createChooser(intent, title);
        startActivity(chooser);
        finish();
    }

}
