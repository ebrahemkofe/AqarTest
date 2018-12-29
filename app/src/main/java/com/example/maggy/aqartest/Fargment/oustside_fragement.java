package com.example.maggy.aqartest.Fargment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maggy.aqartest.Adapter.ImageAdapter;

import com.example.maggy.aqartest.R;

/*creat by abdalla*/

public class oustside_fragement extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         int[] Images = new int[]{
                R.drawable.out,
                R.drawable.out2,
                R.drawable.out3,

        };

        View v = inflater.inflate(R.layout.outside_fragment, container, false);

        ViewPager viewPager=v.findViewById(R.id.ViewPager_CompFrag1);
        ImageAdapter adapter= new ImageAdapter(getContext(),Images);
        viewPager.setAdapter(adapter);
        return v;
    }

    public oustside_fragement(){

    }

}
