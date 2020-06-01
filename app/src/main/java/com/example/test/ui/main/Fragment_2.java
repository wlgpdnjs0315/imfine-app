package com.example.test.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.test.R;

import java.util.concurrent.ExecutionException;

public class Fragment_2 extends Fragment {
    String url = "http://openapi.airkorea.or.kr/"
            + "openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty"
            + "?stationName=종로구&dataTerm=month&pageNo=1&numOfRows=10&ServiceKey=Pwp3GzEEMJhPVeNDnyJqZBoRi8x0fb6p5cmUgRlOBl5mR5rvPzxpIyS6hUSAHmKvKANTs70r7pg2zdgq53uPDw%3D%3D&ver=1.3";


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

        try {
            String resultText = new AirAPI(this.getContext(), url).execute().get();
            getXMLParser(resultText);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        return root;
    }
    private void getXMLParser(String resultText3) {

        if(resultText3.length() == 0)
            return;
        String[] array = resultText3.split("_");
        // text_dust_0.setText(array[0] + "시간"); // date, 시간
        Log.d("air",(array[1] + "㎍/m³")); // pm10Value, 미세먼지
        Log.d("air",(array[2] + "㎍/m³")); // pm25Value, 초미세먼지

    }
}
