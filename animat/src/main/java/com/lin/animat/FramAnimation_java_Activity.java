


package com.lin.animat;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class FramAnimation_java_Activity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv;
    private Button btn1, btn2;
    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fram_animation_java_);

        iv = (ImageView) findViewById(R.id.fram_animation_java_iv);
        btn1 = (Button) findViewById(R.id.fram_ainmation_java_bnt1);
        btn2 = (Button) findViewById(R.id.fram_ainmation_java_bnt2);

        animationDrawable = new AnimationDrawable();

        for (int i = 1; i <= 6; i++) {
            int RsID = getResources().getIdentifier("pic" + i, "drawable", "com.lin.animat");
            Drawable drawable = getResources().getDrawable(RsID);
            animationDrawable.setOneShot(true);
            animationDrawable.addFrame(drawable, 200);
        }
        iv.setImageDrawable(animationDrawable);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fram_ainmation_java_bnt1:
                animationDrawable.start();
                break;
            case R.id.fram_ainmation_java_bnt2:
                animationDrawable.stop();
                break;
        }
    }
}
