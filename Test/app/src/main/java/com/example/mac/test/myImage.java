package com.example.mac.test;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.util.AttributeSet;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.Random;

/**
 * Created by MAC on 2017/8/22.
 */

public class myImage extends android.support.v7.widget.AppCompatImageView {

    private int randomW;
    private int randomH;
    private int contentWidth;
    private int contentHeight;
    private int thisMeasureWidth;
    private int thisMeasureHeight;
    private int currentX;
    private int currentY;
    private Random random;

    public myImage(Context context) {
        super(context);
    }

    public myImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public myImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void layout(@Px int l, @Px int t, @Px int r, @Px int b) {
        LinearLayout parent = (LinearLayout) this.getParent();
        int parentMeasuredWidth = parent.getMeasuredWidth();
        int parentMeasuredHeight = parent.getMeasuredHeight();
        thisMeasureWidth = this.getMeasuredWidth();
        thisMeasureHeight = this.getMeasuredHeight();
        super.layout(0, 0, thisMeasureWidth, thisMeasureHeight);
        contentWidth = parentMeasuredWidth - thisMeasureWidth;
        contentHeight = parentMeasuredHeight - thisMeasureHeight;
        random = new Random();
        randomW = random.nextInt(contentWidth);
        randomH = random.nextInt(contentHeight);
        currentX = myImage.this.getLeft();
        currentY = myImage.this.getTop();
        this.post(runnable);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            myImage.this.removeCallbacks(this);
            //这是横坐标向左
            if (currentX - randomW > 0) {
                //这是纵坐标向上
                if (currentY - randomH > 0) {
                    myImage.super.layout(currentX--, currentY--, currentX + thisMeasureWidth, currentY + thisMeasureHeight);
                }
                //这是纵坐标向下
                else {
                    myImage.super.layout(currentX--, currentY++, currentX + thisMeasureWidth, currentY + thisMeasureHeight);
                }
            }
            //这是横坐标向右
            else {
                //这是纵坐标向上
                if (currentY - randomH > 0) {
                    myImage.super.layout(currentX++, currentY--, currentX + thisMeasureWidth, currentY + thisMeasureHeight);
                }
                //这是纵坐标向下
                else {
                    myImage.super.layout(currentX++, currentY++, currentX + thisMeasureWidth, currentY + thisMeasureHeight);
                }
            }
            if (myImage.this.getLeft() == randomW || myImage.this.getTop() == randomH) {
                currentX = myImage.this.getLeft();
                currentY = myImage.this.getTop();
                randomW = random.nextInt(contentWidth);
                randomH = random.nextInt(contentHeight);
            }
            myImage.this.invalidate();
            myImage.this.post(this);
        }
    };
}
