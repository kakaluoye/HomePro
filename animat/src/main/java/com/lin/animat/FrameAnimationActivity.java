package com.lin.animat;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class FrameAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv;
    private Button btn1, btn2;
    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_animation);
        iv = (ImageView) findViewById(R.id.fram_animation_iv);
        btn1 = (Button) findViewById(R.id.fram_animation_btn1);
        btn2 = (Button) findViewById(R.id.fram_animation_btn2);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        animationDrawable = (AnimationDrawable) iv.getBackground();//动画对象
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fram_animation_btn1:
                animationDrawable.start();
                break;
            case R.id.fram_animation_btn2:
                animationDrawable.stop();
                break;
        }
    }
}
