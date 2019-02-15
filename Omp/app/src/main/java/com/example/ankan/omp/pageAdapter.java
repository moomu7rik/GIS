package com.example.ankan.omp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class pageAdapter extends FragmentStatePagerAdapter {
    int mNoOfTabs;

    public pageAdapter(FragmentManager fm, int NumberOfTabs)
    {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;


    }


    @Override
    public Fragment getItem(int position) {
        switch(position) {

            case 0:
                BlankFragment tab1;
                tab1 = new BlankFragment();
                return tab1;
            case 1:
                BlankFragment2 tab2 = new BlankFragment2();
                return tab2;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}