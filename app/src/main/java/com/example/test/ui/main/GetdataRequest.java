package com.example.test.ui.main;

import androidx.annotation.StringRes;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class GetdataRequest extends StringRequest {

    //php 파일 연동
    final static private String URL = "http://junior.catsecurity.net/~yesqwe/imfine/getdata.php";
    private Map<String, String> map;

    public GetdataRequest(Response.Listener<String> listener){
        super(Method.POST , URL, listener,null);

        map = new HashMap<>();
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
