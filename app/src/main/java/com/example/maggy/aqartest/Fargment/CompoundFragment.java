package com.example.maggy.aqartest.Fargment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maggy.aqartest.Adapter.ImageAdapter;
import com.example.maggy.aqartest.R;

/**
 * Created by aswany on 12/29/18.
 */

public class CompoundFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.compound_frag, container, false);


       //static values
        int[] Images = new int[]{

                R.drawable.comp7,
                R.drawable.comp8,
                R.drawable.comp9,
                R.drawable.comp10,
                R.drawable.comp11,
                R.drawable.comp12,
                R.drawable.comp13,
                R.drawable.comp3,
                R.drawable.comp4,
                R.drawable.comp5,
                R.drawable.comp6
        };


        ViewPager viewPager=v.findViewById(R.id.ViewPager_CompFrag);
        ImageAdapter adapter= new ImageAdapter(getContext(),Images);
        viewPager.setAdapter(adapter);
        return v;
    }

    public CompoundFragment(){

    }

}
