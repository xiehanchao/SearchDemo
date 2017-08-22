package com.example.mac.test;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<ImageView> pointsList = new ArrayList<ImageView>();
    private int count = 0;
    private LinearLayout points;
    private ImageView image;
    private LinearLayout parentRL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parentRL= (LinearLayout) findViewById(R.id.parentRL);
        points = (LinearLayout) findViewById(R.id.points);
        image= (ImageView) findViewById(R.id.image1);
        handler.sendEmptyMessage(1);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (points.getChildCount() == 6) {
                points.removeAllViews();
            }
            ImageView imageView = new ImageView(MainActivity.this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(6, 6);
            params.leftMargin = 8;
            imageView.setLayoutParams(params);
            imageView.setBackgroundResource(R.drawable.a);
            pointsList.add(imageView);
            points.addView(pointsList.get(count), -1);
            count++;
            handler.sendEmptyMessageDelayed(1, 1000);
        }
    };

}

