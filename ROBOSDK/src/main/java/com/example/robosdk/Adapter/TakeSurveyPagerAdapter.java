package com.example.robosdk.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.robosdk.Activities.TakeSurveyActivity;
import com.example.robosdk.Fragments.TakenSurveysFragment;
import com.example.robosdk.Fragments.UnTakenSurveysFragment;
import com.example.robosdk.R;
import com.example.robosdk.Utility.Utility;

public class TakeSurveyPagerAdapter extends FragmentStatePagerAdapter {
    Context context;
    public TakeSurveyPagerAdapter(FragmentManager fm, TakeSurveyActivity takeSurveyActivity) {
        super(fm);
        this.context = takeSurveyActivity;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new UnTakenSurveysFragment();
        } else {
            return new TakenSurveysFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    public View getTabView(int position) {
        // Given you have a custom layout in `res/layout/custom_tab.xml` with a TextView and ImageView
        View v = LayoutInflater.from(context).inflate(R.layout.custom_tab, null);

                TextView tv = (TextView) v.findViewById(R.id.textTitleTabBar);

        if(position == 0){
            tv.setText("UnTaken");
        }
        else
        {
            tv.setText("Taken");

        }
       /* TextView tv = (TextView) v.findViewById(R.id.item_name);
        TextView textView = v.findViewById(R.id.item_count);
        if (true) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(String.valueOf("Text"));
        } else {
            textView.setVisibility(View.GONE);
        }

        tv.setText("Tab 1");
        ImageView img = (ImageView) v.findViewById(R.id.item_icon);
*/


        if (position == 0) {
            tv.setBackgroundColor(Utility.getColor("#14538eff"));
            tv.setTextColor(Utility.getColor("#ffffffff"));
        }
        return v;
    }
}
