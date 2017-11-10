package com.nankai.imageviewer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import java.text.DecimalFormat;

/**
 * Created by nankai on 11/10/2017.
 */

public class ZoomDragImageActivity extends Activity {
    private static final String ELEMENT_IMAGE_DETAIL = "element.image_detail";
    private static final String TAG = ZoomDragImageActivity.class.getSimpleName();
    TouchImageView image;
    ConstraintLayout container;
    private DecimalFormat df;

    public static void launch(AppCompatActivity activity, View view) {
        Intent intent = new Intent(activity, ZoomDragImageActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view, ELEMENT_IMAGE_DETAIL);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent, options.toBundle());
    }

    private static final int THRESHOLD = 400;

    private float halfWindowHeight;
    private float originY;
    private float dY;
    private float deltaY;
    private boolean isVertivalScroll = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_drag_image);
        df = new DecimalFormat("#.##");

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        halfWindowHeight = metrics.heightPixels / 2;

        image = findViewById(R.id.image);
        container = findViewById(R.id.container);
        image.setOnTouchImageViewListener(new TouchImageView.OnTouchImageViewListener() {

            @Override
            public void onDown(float originViewY, float eventRawY) {
                // to restore
                originY = originViewY;
                // calculate distance from top of view to top of touch point
                dY = originY - eventRawY;
            }

            @Override
            public void onUp(float getY, float rawY) {
                isVertivalScroll = false;
//                view.unLockSwipe();
                if (Math.round(Math.abs(deltaY)) > THRESHOLD) {
                    finish();
                } else {
                    float percent = 100f;
                    percent = percent < 0.2f ? 0.2f : percent;
                    // fade container
                    container.animate()
                            .alpha(percent)
                            .setDuration(0)
                            .start();

                    // move content
                    image.animate()
                            .y(originY)
                            .setDuration(0)
                            .start();
                }
            }

            @Override
            public void onMove(float eventRawY) {
                // calculate top of view when drag
                float viewY = eventRawY + dY;
                deltaY = Math.round(Math.abs(viewY - originY));
                float percent = calculateViewOpacity();
                float top = viewY;

                percent = percent < 0.2f ? 0.2f : percent;
                // fade container
                container.animate()
                        .alpha(percent)
                        .setDuration(0)
                        .start();

                // move content
                image.animate()
                        .y(top)
                        .setDuration(0)
                        .start();
            }
        });

        ViewCompat.setTransitionName(image, ELEMENT_IMAGE_DETAIL);
    }


    /**
     * @return opacity of container depend on screen height
     */
    private float calculateViewOpacity() {
        return 1 - (Math.abs(deltaY) / halfWindowHeight);
    }
}
