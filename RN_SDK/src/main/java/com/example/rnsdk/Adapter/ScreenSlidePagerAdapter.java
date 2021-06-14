package com.example.rnsdk.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.rnsdk.Fragments.GridLayoutOne;
import com.example.rnsdk.Fragments.GridLayoutTwo;

 public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
    public ScreenSlidePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0)
        {
            return new GridLayoutOne();
        }
        else
        {
            return new GridLayoutTwo();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}