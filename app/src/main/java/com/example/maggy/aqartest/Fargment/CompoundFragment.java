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

        ViewPager viewPager=v.findViewById(R.id.ViewPager_CompFrag);
        ImageAdapter adapter= new ImageAdapter(getContext());
        viewPager.setAdapter(adapter);
        return v;
    }

    public CompoundFragment(){

    }

}
