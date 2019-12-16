package com.example.activity_analysis_app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.app.DownloadManager;
import android.app.usage.NetworkStats;
import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.TrafficStats;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.android.volley.Request.Method.POST;
import static java.lang.reflect.Method.*;

public class HistoryActivity extends AppCompatActivity {

    TextView TX, RX, TXInsta, RXInsta, TXspotify, RXspotify,TXyoutube, RXyoutube, TextView1;
    private CoordinatorLayout mCLayout;

    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        TX = findViewById(R.id.TX);
        RX = findViewById(R.id.RX);
        TXInsta = findViewById(R.id.TXInsta);
        RXInsta = findViewById(R.id.RXInsta);
        TXspotify = findViewById(R.id.TXspotify);
        RXspotify = findViewById(R.id.RXspotify);
        TXyoutube = findViewById(R.id.TXyoutube);
        RXyoutube = findViewById(R.id.RXyoutube);
        TextView1 = findViewById(R.id.TextView1);
        final GraphView graph = (GraphView) findViewById(R.id.graph);

        graph.setVisibility(View.VISIBLE);


        AppOpsManager appOps = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                Process.myUid(), getPackageName());
        if (mode == AppOpsManager.MODE_ALLOWED) {

        }

        NetworkStatsManager networkStatsManager = (NetworkStatsManager) getApplicationContext().getSystemService(Context.NETWORK_STATS_SERVICE);

        NetworkStats.Bucket bucket = null;
        try {
            bucket = networkStatsManager.querySummaryForDevice(ConnectivityManager.TYPE_WIFI,
                    "",
                    0,
                    System.currentTimeMillis());
        } catch (RemoteException e) {

        }

        //TX.setText("TX -  " + bucket.getTxBytes());
        //RX.setText("RX -  " + bucket.getRxBytes());


        NetworkStats networkStats = null;
        networkStats = networkStatsManager.queryDetailsForUid(
                ConnectivityManager.TYPE_WIFI,
                "",
                0,
                System.currentTimeMillis(),
                10161);
        NetworkStats.Bucket bucket2 = new NetworkStats.Bucket();
        networkStats.getNextBucket(bucket2);

        Log.d("RX  - " + 10259, " " + bucket2.getRxBytes());
        Log.d("TX  - " + 10259, " " + bucket2.getTxBytes());
        TXInsta.setText("1. Instagram - " + bucket2.getTxBytes());
        //RXInsta.setText("RX 10259 -  " + bucket2.getRxBytes());

        isNamedProcessRunning(getAppName(99));


        NetworkStats networkStats3 = null;
        networkStats3 = networkStatsManager.queryDetailsForUid(
                ConnectivityManager.TYPE_WIFI,
                "",
                0,
                System.currentTimeMillis(),
                10238);
        NetworkStats.Bucket bucket3 = new NetworkStats.Bucket();
        networkStats3.getNextBucket(bucket3);

        Log.d("RX  - " + 10238, " " + bucket3.getRxBytes());
        Log.d("TX  - " + 10238, " " + bucket3.getTxBytes());
        TXspotify.setText("2. Spotify - " + bucket3.getTxBytes());
        //RXspotify.setText("RX 10238 -  " + bucket3.getRxBytes());

        isNamedProcessRunning(getAppName(99));


        NetworkStats networkStats4 = null;
        networkStats4 = networkStatsManager.queryDetailsForUid(
                ConnectivityManager.TYPE_WIFI,
                "",
                0,
                System.currentTimeMillis(),
                10105);
        NetworkStats.Bucket bucket4 = new NetworkStats.Bucket();
        networkStats4.getNextBucket(bucket4);

        Log.d("RX  - " + 10161, " " + bucket4.getRxBytes());
        Log.d("TX  - " + 10161, " " + bucket4.getTxBytes());
        TXyoutube.setText("3. Youtube - " + bucket4.getTxBytes());
        //RXyoutube.setText("RX 10161 -  " + bucket4.getRxBytes());

        isNamedProcessRunning(getAppName(99));

        try {
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                    new DataPoint(Integer.valueOf(1), Integer.valueOf(String.valueOf(bucket2.getTxBytes()))),
                    new DataPoint(Integer.valueOf(2), Integer.valueOf(String.valueOf(bucket3.getTxBytes()))),
                    new DataPoint(Integer.valueOf(3), Integer.valueOf(String.valueOf(bucket4.getTxBytes()))),
            });
            graph.addSeries(series);
        } catch (IllegalArgumentException e) {
            Toast.makeText(HistoryActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        CurrentActivity currentActivity = new CurrentActivity();
        String mail = "poszlo";
        String url_login = "http://192.168.43.117:8085/kinga/appdata";
        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("mail", mail);
            jsonObject.put("bluetooth", currentActivity.count);
            jsonObject.put("TXInstagram", String.valueOf(bucket2.getTxBytes()));
            jsonObject.put("TXYoutube", String.valueOf(bucket3.getTxBytes()));
            jsonObject.put("TXSpotify",String.valueOf(bucket4.getTxBytes()));
            new SendDeviceDetails().execute(url_login, jsonObject.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*

        Context mContext;
        mContext = getApplicationContext();

        // Initialize a new RequestQueue instance
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);

        // Initialize a new JsonObjectRequest instance
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url_login,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Do something with response
                        TextView1.setText(response.toString());

                        // Process the JSON
                        try{
                            // Get the JSON array
                            JSONArray array = response.getJSONArray("students");

                            // Loop through the array elements
                            for(int i=0;i<array.length();i++){
                                // Get current json object
                                JSONObject student = array.getJSONObject(i);

                                // Get the current student (json object) data
                                String firstName = student.getString("firstname");
                                String lastName = student.getString("lastname");
                                String age = student.getString("age");

                                // Display the formatted json data in text view
                                TextView1.append(firstName +" " + lastName +"\nage : " + age);
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred

                    }
                }
        );

        // Add JsonObjectRequest to the RequestQueue
        requestQueue.add(jsonObjectRequest);


         */
    }



    private String getAppName(int pID)
    {
        String processName = "";
        ActivityManager am = (ActivityManager)this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while(i.hasNext())
        {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo)(i.next());
            try
            {
                if(info.pid == pID)
                {
                    CharSequence c = pm.getApplicationLabel(pm.getApplicationInfo(info.processName, PackageManager.GET_META_DATA));
                    //Log.d("Process", "Id: "+ info.pid +" ProcessName: "+ info.processName +"  Label: "+c.toString());
                    //processName = c.toString();
                    processName = info.processName;
                }
            }
            catch(Exception e)
            {
                //Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }

    boolean isNamedProcessRunning(String processName){
        if (processName == null)
            return false;

        ActivityManager manager =
                (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processes = manager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo process : processes)
        {
            if (processName.equals(process.processName))
            {
                return true;
            }
        }
        return false;
    }

}
