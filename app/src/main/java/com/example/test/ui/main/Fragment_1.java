package com.example.test.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.test.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class Fragment_1<stringRequest> extends Fragment {
    private TextView data;
    private TimerTask mTimer;
    RequestQueue queue;


    public static Fragment_1 newInstance(){
        Fragment_1 fragment = new Fragment_1();
        return fragment;
    }

    public void onCreate (Bundle savedInstanceState) {
        super.onCreate((savedInstanceState));
        queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        final String log = "11";

        Timer timer = new Timer();

        TimerTask TT = new TimerTask(){
            public void run(){
                Log.d("Tag","timer");
                GetData();
            }
        };

        timer.schedule(TT,0,1000);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_1, container, false);
        data = root.findViewById(R.id.textView);




        /*
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
                            JSONObject jsonObject = new JSONObject(response);
                            float pm2 = jsonObject.getLong("pm2");
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
        */
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
                            JSONObject jsonObject = new JSONObject(response);
                            float pm2 = jsonObject.getLong("pm2");
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

    //데이터 수신 리스너
/*
    Response.Listener<String> responseListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                float pm2 = jsonObject.getLong("pm2");
                data.setText(pm2 + "");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
*/
    //데이터 요청
/*
    GetdataRequest getdataRequest = new GetdataRequest(responseListener);
    GetdataRequest queue = Volley.newRequestQueue(getActivity().getApplicationContext());
    queue.add(getdataRequest);
*/


}
