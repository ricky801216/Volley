package com.example.student.volley;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //建立一個Volly requestQueue
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        //StringRequest抓取字串資料
       // String url = "https://www.google.com.tw/";
       // String url = "http://data.ntpc.gov.tw/od/data/api/04958686-1B92-4B74-889D-9F34409B272B;jsessionid=62C3922EA2AD7CC85E984C4F5C2423DA?$format=json";

         String url ="http://data.ntpc.gov.tw/od/data/api/B1464EF0-9C7C-4A6F-ABF7-6BDF32847E68?$format=json"; /*聯合新聞網的XML讀取不到是因為它可能是UTF-8編碼問題才會導致讀取失敗，因此需要建立一個
        新的類別 UTF-8StringRequest 來進行解碼*/
       // String url ="http://data.taipei/opendata/datalist/apiAccess?scope=resourceAquire&rid=55ec6d6e-dc5c-4268-a725-d04cc262172b";
                StringRequest request = new UTF8StringRequest(url
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("NET", response);

                //建立一個 Gson 物件
                Gson gson = new Gson();
                //陣列為GsonParking             由gson.fromJason取得JSON以及Type資源由 GsonParking 取得
                ArrayList<GsonParking> mylist = gson.fromJson(response, new TypeToken<ArrayList<GsonParking>>() {}.getType());
                //進行防呆機制
                for(GsonParking p : mylist)
                {
                    Log.d("NET", p.NAME+","+p.ADDRESS);
                }
//                try {
//                    JSONArray array = new JSONArray(response);
//
//                    for (int i = 0; i < array.length(); i++)
//                    {
//                        JSONObject obj = array.getJSONObject(i);
//                        Log.d("NET", "Name:" + obj.getString("NAME") + " Addr:" + obj.getString("ADDRESS"));
//
//                    }
//
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("NET", "Error:" + error.toString());
            }
        });
        queue.add(request);
        queue.start();
        Log.d("NET", "Volley started");
    }
}
