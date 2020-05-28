package com.example.test.ui.main;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.test.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Fragment_1<stringRequest> extends Fragment {
    private TextView data;
    private TextView text;
    SwipeRefreshLayout mSwipeRefreshLayout;
    LineChart lineChart;


    public static Fragment_1 newInstance(){
        Fragment_1 fragment = new Fragment_1();
        return fragment;
    }

    public void onCreate (Bundle savedInstanceState) {
        super.onCreate((savedInstanceState));
        GetData();
        draw_chart();
    }


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_1, container, false);
        data = root.findViewById(R.id.textView);
        text = root.findViewById(R.id.textView2);

        lineChart = (LineChart)root.findViewById(R.id.chart);

        mSwipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                GetData();
                mSwipeRefreshLayout.setRefreshing(false);

            }
        });
        return root;
    }



    public void GetData(){
        //요청 전송
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url ="http://junior.catsecurity.net/~yesqwe/imfine/getdata.php";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            Log.d("getdata","success");
                            JSONObject jsonObject = new JSONObject(response);
                            double pm2 = jsonObject.getDouble("pm2");
                            data.setText(pm2 + "");
                            //좋음
                            if(pm2>= 0 && pm2 <= 15){
                                data.setTextColor(Color.BLUE);
                                text.setText("좋음");
                                text.setTextColor(Color.BLUE);

                            }
                            //보통
                            else if (pm2 >= 16 && pm2 <= 35){
                                data.setTextColor(Color.GREEN);
                                text.setText("보통");
                                text.setTextColor(Color.BLUE);

                            }
                            //나쁨
                            else if (pm2 >= 36 && pm2 <= 75){
                                data.setTextColor(Color.YELLOW);
                                text.setText("나쁨");
                                text.setTextColor(Color.BLUE);


                            }
                            //매우 나쁨
                            else if (pm2 >= 76 && pm2 <= 500){
                                data.setTextColor(Color.RED);
                                text.setText("매우 나쁨");
                                text.setTextColor(Color.BLUE);
                            }
                            data.setText(pm2 + "");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("fail");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    //draw line chart
    public void draw_chart(){
        //요청 전송
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url ="http://junior.catsecurity.net/~yesqwe/imfine/getchart.php";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            //데이터 가져옴
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("history");
                            //차트 그리기 위한 ArrayList
                            ArrayList<Entry> entries = new ArrayList<>();
                            ArrayList<String> labels = new ArrayList<String>();

                            for(int i = 0; i < jsonArray.length(); i++){
                                JSONObject item = jsonArray.getJSONObject(i);
                                double history_pm2 = item.getDouble("pm2");
                                String history_time = item.getString("time");

                                entries.add(new Entry(i,(float) history_pm2));
                                labels.add(history_time);
                            }

                            LineDataSet dataset = new LineDataSet(entries,"pm2.5");
                            LineData data = new LineData(dataset);
                            lineChart.setData(data);
                            lineChart.invalidate();
                            //LineDataSet dataset = new LineDataSet(entries,"pm2.5");
                            //LineData data = new LineData(labels, dataset);
                            //dataset.setColors(ColorTemplate.COLORFUL_COLORS);
                            //lineChart.setData(data);
                            //lineChart.animateY(5000);


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("error","error");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("fail");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

}
