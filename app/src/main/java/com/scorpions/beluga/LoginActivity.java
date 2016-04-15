package com.scorpions.beluga;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity{

    private EditText accountEdit;
    private EditText passwordEdit;
    private Button btn_login;

    SQLiteOpenHelper helper;
    private String _acc;
    private String _pass;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Toorbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_login);
        toolbar.setTitle("注册");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        helper = new Sqliteopenhelper(this);
        helper.getWritableDatabase();

        accountEdit = (EditText) findViewById(R.id.email_login);
        passwordEdit= (EditText) findViewById(R.id.password_login);
        btn_login = (Button) findViewById(R.id.register);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _acc = accountEdit.getText().toString();
                _pass = passwordEdit.getText().toString();
                if (_acc.equals("") || _pass.equals("")) {
                    Toast.makeText(getApplicationContext(), "请输入账号密码！", Toast.LENGTH_SHORT).show();
                } else {
                    sureuser(_acc, _pass);
                }
            }
        });
    }


   private void sureuser(String userid, String username) {
   //3,数据库的操作，查询
    SQLiteDatabase sdb = helper.getReadableDatabase();
    try {
     String sql = "select * from student where id=? and name=?";
        Cursor cursor = sdb.rawQuery(sql, new String[] { _acc, _pass });
        if (cursor.getCount() > 0) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("name", _pass);
            intent.putExtras(bundle);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_SHORT).show();
        }
            cursor.close();
            sdb.close();
    } catch (SQLiteException e) {
       Toast.makeText(getApplicationContext(), "亲，请注册！", Toast.LENGTH_SHORT).show();
     }
}


    @Override
        public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}