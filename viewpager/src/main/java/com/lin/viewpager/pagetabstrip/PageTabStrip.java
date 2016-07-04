package com.lin.viewpager.pagetabstrip;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lin.viewpager.Fragment01;
import com.lin.viewpager.Fragment02;
import com.lin.viewpager.Fragment03;
import com.lin.viewpager.R;

import java.util.ArrayList;
import java.util.List;

public class PageTabStrip extends FragmentActivity {

    private ViewPager viewPager;
    private PagerTabStrip pagerTabStrip;
    private Fragment01 fragment01;
    private Fragment02 fragment02;
    private Fragment03 fragment03;
    private List<Fragment> fragments;
    String[] data = {"hah", "fa", "dsf"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_tab_strip);

        fragments = new ArrayList<>();
        fragment01 = new Fragment01();
        fragment02 = new Fragment02();
        fragment03 = new Fragment03();
        fragments.add(fragment01);
        fragments.add(fragment02);
        fragments.add(fragment03);

        pagerTabStrip = (PagerTabStrip) findViewById(R.id.pts);
        pagerTabStrip.setTabIndicatorColor(Color.parseColor("#0000ff"));
        pagerTabStrip.setBackgroundColor(Color.parseColor("#ff0000"));

        viewPager = (ViewPager) findViewById(R.id.pagetabstrip_vp);
        viewPager.setAdapter(new MyFragmentpageadaper(getSupportFragmentManager()));
    }

    class MyFragmentpageadaper extends FragmentPagerAdapter {

        public MyFragmentpageadaper(FragmentManager fm) {
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

        @Override
        public CharSequence getPageTitle(int position) {
            return data[position];
        }
    }


}
