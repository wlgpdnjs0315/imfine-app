package com.example.test.ui.main;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.test.R;

import org.w3c.dom.Text;

import java.util.concurrent.ExecutionException;

public class Fragment_2 extends Fragment {
    String url = "http://openapi.airkorea.or.kr/"
            + "openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty"
            + "?stationName=종로구&dataTerm=month&pageNo=1&numOfRows=10&ServiceKey=Pwp3GzEEMJhPVeNDnyJqZBoRi8x0fb6p5cmUgRlOBl5mR5rvPzxpIyS6hUSAHmKvKANTs70r7pg2zdgq53uPDw%3D%3D&ver=1.3";
    private TextView tView;
    private TextView tView2;
    private TextView tView5;
    private TextView tView7;
    private TextView tView8;
    private TextView tView9;
    private TextView tView10;
    private TextView tView11;
    private TextView tView12;
    private TextView tView13;
    private TextView tView14;
    private TextView tView15;
    private TextView tView16;


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
            String[] getData = getXMLParser(resultText);
            Log.d("data",getData+"");
            tView = root.findViewById(R.id.textView);
            tView2 = root.findViewById(R.id.textView2);
            tView5 = root.findViewById(R.id.textView5);
            tView7 = root.findViewById(R.id.textView7);
            tView8 = root.findViewById(R.id.textView8);
            tView9 = root.findViewById(R.id.textView9);
            tView10 = root.findViewById(R.id.textView10);
            tView11 = root.findViewById(R.id.textView11);
            tView12 = root.findViewById(R.id.textView12);
            tView13 = root.findViewById(R.id.textView13);
            tView14 = root.findViewById(R.id.textView14);
            tView15 = root.findViewById(R.id.textView15);
            tView16 = root.findViewById(R.id.textView16);

            // pm2.5
            tView.setText(getData[6]);

            //좋음
            if(Double.parseDouble(getData[6])>= 0 && Double.parseDouble(getData[6]) <= 15){
                tView.setTextColor(Color.BLUE);
                tView5.setText("좋음");
                tView5.setTextColor(Color.BLUE);
            }

            //보통
            else if (Double.parseDouble(getData[6]) >= 16 && Double.parseDouble(getData[6]) <= 35){
                tView.setTextColor(Color.GREEN);
                tView5.setText("보통");
                tView5.setTextColor(Color.GREEN);
            }
            //나쁨
            else if (Double.parseDouble(getData[6]) >= 36 && Double.parseDouble(getData[6]) <= 75){
                tView.setTextColor(Color.YELLOW);
                tView5.setText("나쁨");
                tView5.setTextColor(Color.YELLOW);
            }
            //매우 나쁨
            else if (Double.parseDouble(getData[6]) >= 76 && Double.parseDouble(getData[6]) <= 500){
                tView.setTextColor(Color.RED);
                tView5.setText("매우 나쁨");
                tView5.setTextColor(Color.RED);
            }

            // so2
            tView7.setText(getData[1]);

            //좋음
            if(Double.parseDouble(getData[1])>= 0 && Double.parseDouble(getData[1]) <= 0.02){
                tView7.setTextColor(Color.BLUE);
                tView12.setText("좋음");
                tView12.setTextColor(Color.BLUE);
            }

            //보통
            else if (Double.parseDouble(getData[1]) >= 0.021 && Double.parseDouble(getData[1]) <= 0.05){
                tView7.setTextColor(Color.GREEN);
                tView12.setText("보통");
                tView12.setTextColor(Color.GREEN);
            }
            //나쁨
            else if (Double.parseDouble(getData[1]) >= 0.051 && Double.parseDouble(getData[1]) <= 0.15){
                tView7.setTextColor(Color.YELLOW);
                tView12.setText("나쁨");
                tView12.setTextColor(Color.YELLOW);
            }
            //매우 나쁨
            else if (Double.parseDouble(getData[1]) >= 0.151 && Double.parseDouble(getData[1]) <= 1){
                tView7.setTextColor(Color.RED);
                tView12.setText("매우 나쁨");
                tView12.setTextColor(Color.RED);
            }


            // no2
            tView10.setText(getData[4]);

            //좋음
            if(Double.parseDouble(getData[4])>= 0 && Double.parseDouble(getData[4]) <= 0.03){
                tView10.setTextColor(Color.BLUE);
                tView15.setText("좋음");
                tView15.setTextColor(Color.BLUE);
            }

            //보통
            else if (Double.parseDouble(getData[4]) >= 0.031 && Double.parseDouble(getData[4]) <= 0.06){
                tView10.setTextColor(Color.GREEN);
                tView15.setText("보통");
                tView15.setTextColor(Color.GREEN);
            }
            //나쁨
            else if (Double.parseDouble(getData[4]) >= 0.061 && Double.parseDouble(getData[4]) <= 0.2){
                tView10.setTextColor(Color.YELLOW);
                tView15.setText("나쁨");
                tView15.setTextColor(Color.YELLOW);
            }
            //매우 나쁨
            else if (Double.parseDouble(getData[4]) >= 0.201 && Double.parseDouble(getData[4]) <= 2){
                tView10.setTextColor(Color.RED);
                tView15.setText("매우 나쁨");
                tView15.setTextColor(Color.RED);
            }



            // o3
            tView9.setText(getData[3]);

            //좋음
            if(Double.parseDouble(getData[3])>= 0 && Double.parseDouble(getData[3]) <= 0.03){
                tView9.setTextColor(Color.BLUE);
                tView14.setText("좋음");
                tView14.setTextColor(Color.BLUE);
            }

            //보통
            else if (Double.parseDouble(getData[3]) >= 0.031 && Double.parseDouble(getData[3]) <= 0.09){
                tView9.setTextColor(Color.GREEN);
                tView14.setText("보통");
                tView14.setTextColor(Color.GREEN);
            }
            //나쁨
            else if (Double.parseDouble(getData[3]) >= 0.091 && Double.parseDouble(getData[3]) <= 0.15){
                tView9.setTextColor(Color.YELLOW);
                tView14.setText("나쁨");
                tView14.setTextColor(Color.YELLOW);
            }
            //매우 나쁨
            else if (Double.parseDouble(getData[3]) >= 0.151 && Double.parseDouble(getData[3]) <= 0.6){
                tView9.setTextColor(Color.RED);
                tView14.setText("매우 나쁨");
                tView14.setTextColor(Color.RED);
            }


            // co
            tView8.setText(getData[2]);

            //좋음
            if(Double.parseDouble(getData[2])>= 0 && Double.parseDouble(getData[2]) <= 2){
                tView8.setTextColor(Color.BLUE);
                tView13.setText("좋음");
                tView13.setTextColor(Color.BLUE);
            }

            //보통
            else if (Double.parseDouble(getData[2]) >= 2.01 && Double.parseDouble(getData[2]) <= 9){
                tView8.setTextColor(Color.GREEN);
                tView13.setText("보통");
                tView13.setTextColor(Color.GREEN);
            }
            //나쁨
            else if (Double.parseDouble(getData[2]) >= 9.01 && Double.parseDouble(getData[2]) <= 15){
                tView8.setTextColor(Color.YELLOW);
                tView13.setText("나쁨");
                tView13.setTextColor(Color.YELLOW);
            }
            //매우 나쁨
            else if (Double.parseDouble(getData[2]) >= 15.01 && Double.parseDouble(getData[2]) <= 50){
                tView8.setTextColor(Color.RED);
                tView13.setText("매우 나쁨");
                tView13.setTextColor(Color.RED);
            }


            // pm10
            tView11.setText(getData[5]);
            //좋음
            if(Double.parseDouble(getData[5])>= 0 && Double.parseDouble(getData[5]) <= 30){
                tView11.setTextColor(Color.BLUE);
                tView16.setText("좋음");
                tView16.setTextColor(Color.BLUE);
            }

            //보통
            else if (Double.parseDouble(getData[5]) >= 31 && Double.parseDouble(getData[5]) <= 80){
                tView11.setTextColor(Color.GREEN);
                tView16.setText("보통");
                tView16.setTextColor(Color.GREEN);
            }
            //나쁨
            else if (Double.parseDouble(getData[5]) >= 81 && Double.parseDouble(getData[5]) <= 150){
                tView11.setTextColor(Color.YELLOW);
                tView16.setText("나쁨");
                tView16.setTextColor(Color.YELLOW);
            }
            //매우 나쁨
            else if (Double.parseDouble(getData[5]) >= 151 && Double.parseDouble(getData[5]) <= 600){
                tView11.setTextColor(Color.RED);
                tView16.setText("매우 나쁨");
                tView16.setTextColor(Color.RED);
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        return root;
    }
    private String[] getXMLParser(String resultText3) {

        String[] array = resultText3.split("_");

        Log.d("air",(array[0] + "시간")); // date, 시간

        Log.d("air",(array[1] + "㎍/m³")); // pm10Value, 미세먼지
        Log.d("air",(array[2] + "㎍/m³")); // pm25Value, 초미세먼지
        return array;
    }
}
