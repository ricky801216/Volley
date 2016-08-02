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

         String url ="http://udn.com/rssfeed/news/1"; /*聯合新聞網的XML讀取不到是因為它可能是UTF-8編碼問題才會導致讀取失敗，因此需要建立一個
        新的類別 UTF-8StringRequest 來進行解碼*/
       // String url ="http://data.taipei/opendata/datalist/apiAccess?scope=resourceAquire&rid=55ec6d6e-dc5c-4268-a725-d04cc262172b";
                StringRequest request = new UTF8StringRequest(url
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("NET", response);

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
