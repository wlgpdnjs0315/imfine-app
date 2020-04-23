package com.example.test.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.test.R;

public class Fragment_2 extends Fragment {

    public static Fragment_2 newInstance(){
        Fragment_2 fragment = new Fragment_2();
        return fragment;
    }

    public void onCreate (Bundle savedInstanceState){
        super.onCreate((savedInstanceState));
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_2, container, false);
        return root;
    }
}
