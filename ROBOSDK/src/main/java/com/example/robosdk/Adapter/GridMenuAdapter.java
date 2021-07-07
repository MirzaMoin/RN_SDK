package com.example.robosdk.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.robosdk.Fragments.GridLayoutOne;
import com.example.robosdk.Fragments.GridLayoutTwo;

 public class GridMenuAdapter extends FragmentStatePagerAdapter {
     int size;
    public GridMenuAdapter(FragmentManager fm, int size) {
        super(fm);
        this.size = size;
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

        if(size > 6)
        {
            return 2;
        }
        else
        {
            return  1;
        }
    }
}