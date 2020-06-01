package com.example.test.ui.main;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

public class TimerActivity extends AppCompatActivity {
    private Timer mTimer;
    private TextView data;
    private TextView text;

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.fragment_1);

        data = (TextView) findViewById(R.id.textView);
        text = (TextView) findViewById(R.id.textView2);

        mTimer = new Timer();
        mTimer.schedule(new CustomTimer(),2000,1000);
    }

    protected void onDestory(){
        mTimer.cancel();
        super.onDestroy();
    }

    class CustomTimer extends TimerTask {
        public void run(){
            GetData();
        }
    }

    public void GetData(){
        //요청 전송
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
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
                            Log.d("timer","error");
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
