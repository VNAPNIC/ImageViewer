package com.nankai.imageviewer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.single_touchimageview_button).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SingleTouchImageViewActivity.class));
            }
        });
        findViewById(R.id.viewpager_example_button).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViewPagerExampleActivity.class));
            }
        });
        findViewById(R.id.mirror_touchimageview_button).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MirroringExampleActivity.class));
            }
        });
        findViewById(R.id.switch_image_button).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SwitchImageExampleActivity.class));
            }
        });
        findViewById(R.id.switch_scaletype_button).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SwitchScaleTypeExampleActivity.class));
            }
        });
        findViewById(R.id.zoom_drag_button).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               ZoomDragImageActivity.launch(MainActivity.this,v);
            }
        });
    }
}