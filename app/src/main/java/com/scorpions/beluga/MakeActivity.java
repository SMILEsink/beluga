package com.scorpions.beluga;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MakeActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_make);
        toolbar.setTitle(R.string.toolbar_title_make);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_make, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }else if(item.getItemId() == R.id.action_settings_make){

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";
        public Uri imageUri;
        public Uri videoUri;

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_make, container, false);

            FloatingActionButton fabMake = (FloatingActionButton) rootView.findViewById(R.id.fab_make);
            fabMake.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getArguments().getInt(ARG_SECTION_NUMBER) == 1){
                        File outputImage = new File(Environment.getExternalStorageDirectory(), "output_image.jpg");
                        try {
                            if (outputImage.exists()){
                                outputImage.delete();
                            }
                            outputImage.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        imageUri = Uri.fromFile(outputImage);
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        startActivityForResult(intent, 1);
                    }
                    else if (getArguments().getInt(ARG_SECTION_NUMBER) == 2){
                        File outputVideo = new File(Environment.getExternalStorageDirectory(), "output_video.mp4");
                        try {
                            if (outputVideo.exists()){
                                outputVideo.delete();
                            }
                            outputVideo.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        videoUri = Uri.fromFile(outputVideo);
                        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
                        startActivityForResult(intent, 2);
                    }
                }
            });
            return rootView;
        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new imageFragment();
                case 1:
                    return new videoFragment();
            }
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "拍照";
                case 1:
                    return "录制";
                case 2:
                    return "合成";
            }
            return null;
        }
    }

    public static class imageFragment extends Fragment {

        private ImageView imageView;
        private  Uri imageUri;

        private String mFileImage = Environment.getExternalStorageDirectory().getPath();

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_image, container, false);
            imageView = (ImageView) view.findViewById(R.id.cameraImage);
            FloatingActionButton fabImage = (FloatingActionButton) view.findViewById(R.id.fabImage);
            fabImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mFileImage = mFileImage + "/" + "temp.png";

                    imageUri = Uri.fromFile(new File(mFileImage));
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, 1);
                }
            });
            return view;
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            FileInputStream fisImage = null;
            try {
                fisImage = new FileInputStream(mFileImage);
                Bitmap bitmap = BitmapFactory.decodeStream(fisImage);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }finally {
                try {
                    fisImage.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public  static  class videoFragment extends Fragment{
        private  VideoView videoView;
        private  Uri videoUri;
        private String mFileVideo = Environment.getExternalStorageDirectory().getPath();

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_video, container ,false);
            videoView = (VideoView) view.findViewById(R.id.cameraVideo);

            FloatingActionButton fabVideo = (FloatingActionButton) view.findViewById(R.id.fabVideo);
            fabVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mFileVideo = mFileVideo + "/" + "temp.3gp";
                    videoUri = Uri.fromFile(new File(mFileVideo));
                    Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
                    startActivityForResult(intent, 2);
                }
            });
            return view;
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            videoView.setVideoPath(mFileVideo);
            videoView.requestFocus();
            videoView.start();
        }
    }
}
