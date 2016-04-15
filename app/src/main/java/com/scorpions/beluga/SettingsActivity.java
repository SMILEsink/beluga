package com.scorpions.beluga;


import android.annotation.TargetApi;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBar;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SettingsActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_setting);
        ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
        HashMap<String,String> map1=new HashMap<String,String>();
        HashMap<String,String> map2=new HashMap<String,String>();
        HashMap<String,String> map3=new HashMap<String,String>();
        HashMap<String,String> map4=new HashMap<String,String>();
        HashMap<String,String> map5=new HashMap<String,String>();
        map1.put("setting_content", "基本设置");
        map2.put("setting_content", "版本更新");
        map3.put("setting_content", "用户反馈");
        map4.put("setting_content", "关于我们");
        map5.put("setting_content", "帮助");

        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        SimpleAdapter listAdapter=new SimpleAdapter(this,list,R.layout.main_setting_content,
                new String[]{"setting_content"},
                new int[] {R.id.setting_content});
        setListAdapter(listAdapter);

    }
    @Override
    protected void onListItemClick(android.widget.ListView l, View v, int position, long id){
        super.onListItemClick(l,v,position,id);
        System.out.println("id------------"+id);
        System.out.println("position------------"+position);

    }
}
