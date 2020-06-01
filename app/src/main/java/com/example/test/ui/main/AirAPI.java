package com.example.test.ui.main;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class AirAPI extends AsyncTask<String, Void, String> {

    private static final String TAG = "XMLParsingTask";
    private String receiveMsg = "";
    private String requestUrl;
    public Context mContext;

    public AirAPI(Context context, String requestUrl){
        this.requestUrl = requestUrl;
        this.mContext = context;
    }

    @Override
    protected void onProgressUpdate(Void... values){
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(String... strings) {

        try{
            boolean b_dataTime = false;
            boolean b_pm10Value = false;
            boolean b_pm25Value = false;
            boolean b_endtag = false;

            URL url = new URL(requestUrl);
            InputStream is = url.openStream();
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new InputStreamReader(is, "UTF-8"));

            String tag;
            int eventType = parser.getEventType();
            parser.next();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {

                    case XmlPullParser.START_DOCUMENT:
                        Log.i("air","parsing start");
                        break;

                    case XmlPullParser.END_DOCUMENT:
                        break;


                    // 시작태그를 만나는순간 (JSON으로 치면 key을 대입해서 찾음)
                    case XmlPullParser.START_TAG:
                        tag = parser.getName();
                        Log.i("air",parser.getName());
                        if (tag.equals("item")) {

                            b_endtag = true;
                            parser.next();
                        }
                        else if (tag.equals("dataTime")) {
                            //parser.next();
                            //Log.i("air", parser.getText());
                            //receiveMsg = receiveMsg + parser.getText()+"_";
                            b_dataTime = true;
                            parser.next();
                        }
                        else if (tag.equals("pm10Value")) {
                            //parser.next();
                            //Log.i("air", parser.getText());
                            //receiveMsg = receiveMsg + parser.getText()+"_";

                            b_pm10Value = true;
                            parser.next();
                        }
                        else if (tag.equals("pm25Value")) {
                            //receiveMsg = receiveMsg + parser.getText().trim() + "_";
                            //parser.next();
                            //Log.i("air", parser.getText());

                            b_pm25Value = true;
                            parser.next();
                        }
                        // TODO : 만약 더 많은 value을 원한다면, 이어서 기술




                    // 시작태그와 종료태그 사이 값을 만나는순간 (JSON으로 치면 key를 넣어서 값을 얻음)
                    case XmlPullParser.TEXT:

                        if (b_dataTime) {
                            receiveMsg = receiveMsg + parser.getText() + "_";
                            b_dataTime = false;
                        } else if (b_pm10Value) {
                            receiveMsg = receiveMsg + parser.getText()+ "_";
                            b_pm10Value = false;
                        } else if (b_pm25Value) {
                            receiveMsg = receiveMsg + parser.getText() + "_";
                            b_pm25Value = false;
                        }
                        break;
                    // 종료태그를 만나는순간 (JSON으로 치면, value 찾고 이제 볼일없음)
                    case XmlPullParser.END_TAG:
                        tag = parser.getName();
                        if(b_endtag)
                            break;
                }
                eventType = parser.next();
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return receiveMsg;
    }
}
