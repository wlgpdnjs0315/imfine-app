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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class Fragment_1<stringRequest> extends Fragment {
    private TextView data;
    private TextView text;
    SwipeRefreshLayout mSwipeRefreshLayout;


    public static Fragment_1 newInstance(){
        Fragment_1 fragment = new Fragment_1();
        return fragment;
    }

    public void onCreate (Bundle savedInstanceState) {
        super.onCreate((savedInstanceState));
        GetData();
    }


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_1, container, false);
        data = root.findViewById(R.id.textView);
        text = root.findViewById(R.id.textView2);
        LineChart lineChart = (LineChart)root.findViewById(R.id.chart);

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
                            JSONArray jsonArray = new JSONArray("history");

                            for(int i = 0; i < jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                float history_pm2 = jsonObject.getLong("pm2");
                                String history_time = jsonObject.getString("time");
                                Log.d("pm2",history_pm2+"");
                                Log.d("time",history_time);
                            }

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
