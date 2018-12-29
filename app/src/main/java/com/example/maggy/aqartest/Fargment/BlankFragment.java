package com.example.maggy.aqartest.Fargment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.maggy.aqartest.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {

    ScrollView sv;
    SliderLayout sl;
    int [] images = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5};

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        sv = view.findViewById(R.id.scroll);
        sl = view.findViewById(R.id.slider);

        for (int i = 0; i < images.length; i++) {

            TextSliderView textSliderView = new TextSliderView(this.getContext());
            textSliderView.setScaleType(BaseSliderView.ScaleType.CenterCrop);
            textSliderView.image(images[i]);
            sl.addSlider(textSliderView);
        }
        if (container!=null)container.removeAllViews();

        return view;
    }

}
