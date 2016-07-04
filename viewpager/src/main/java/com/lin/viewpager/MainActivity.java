package com.lin.viewpager;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private LinearLayout ln111, ln112, ln113;
    private TextView tv1, tv2, tv3;
    private Fragment01 fragment01;
    private Fragment02 fragment02;
    private Fragment03 fragment03;
    private List<Fragment> fragments;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ln111 = (LinearLayout) findViewById(R.id.ln_111);
        ln112 = (LinearLayout) findViewById(R.id.ln_112);
        ln113 = (LinearLayout) findViewById(R.id.ln_113);
        ln111.setOnClickListener(this);
        ln112.setOnClickListener(this);
        ln113.setOnClickListener(this);

        tv1 = (TextView) findViewById(R.id.tv_1);
        tv2 = (TextView) findViewById(R.id.tv_2);
        tv3 = (TextView) findViewById(R.id.tv_3);

        fragments = new ArrayList<Fragment>();
        fragment01 = new Fragment01();
        fragment02 = new Fragment02();
        fragment03 = new Fragment03();
        fragments.add(fragment01);
        fragments.add(fragment02);
        fragments.add(fragment03);

        viewPager = (ViewPager) findViewById(R.id.vp);
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
        setTabColer(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setTabColer(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public void setTabColer(int postion) {
        resetTabColor();
        switch (postion) {
            case 0:
                tv1.setTextColor(Color.parseColor("#ff0000"));
                break;
            case 1:
                tv2.setTextColor(Color.parseColor("#00ff00"));
                break;
            case 2:
                tv3.setTextColor(Color.parseColor("#0000ff"));
        }
    }

    public void resetTabColor() {
        tv1.setTextColor(Color.parseColor("#000000"));
        tv2.setTextColor(Color.parseColor("#000000"));
        tv3.setTextColor(Color.parseColor("#000000"));
    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter {


        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ln_111:
                viewPager.setCurrentItem(0);
                break;
            case R.id.ln_112:
                viewPager.setCurrentItem(1);
                break;
            case R.id.ln_113:
                viewPager.setCurrentItem(2);
                break;
        }
    }
}
