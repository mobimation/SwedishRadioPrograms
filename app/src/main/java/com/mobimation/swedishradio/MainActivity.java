package com.mobimation.swedishradio;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

@SuppressWarnings("SpellCheckingInspection")
public class MainActivity extends AppCompatActivity {
    private static ProgressDialog mProgressDialog;
    private RecyclerView rv;
    private ArrayList<ProgramItem> contentList;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Log.d("gf", "MainActivity back button pressed");
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        
        retrieveJSON();  // Fetch SR Open API data
    }

    /**
     * retrieveJSON() initializes program list data from the SR Open API.
     * The current approach is to load ALL of the data since practical use
     * has shown that we do not run into performance issues even with quite old
     * Android devices. The progress dialog is quite unneccessary so it could be
     * removed, but maybe has merits in slow network connection situations.
     **/
    private void retrieveJSON() {

        showSimpleProgressDialog(this, "Loading...","Fetching Json",false);

        /**
         *  String URLstring = "http://api.sr.se/api/v2/programs?format=json&size=40";
         *  For now let's get ALL of the SR Program entries. The expensive memory consumers
         *  will be the inflated image objects, and RecyclerView takes care of the
         *  view recycling.
         *  The API gives use the amount available (744 att time of developing this).
         *  A future paging implementation will improve on this.
         **/
        String URLstring = "http://api.sr.se/api/v2/programs?format=json&size=1000";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);

                            contentList = new ArrayList<>();
                            JSONArray dataArray  = obj.getJSONArray("programs");
 //                         Log.d ("srprograms", "got "+obj.length()+" entries.");

                            for (int i = 0; i < dataArray.length(); i++) {

                                ProgramItem item = new ProgramItem();
                                JSONObject dataobj = dataArray.getJSONObject(i);
                                // Copy data from JSON array to data model row
                                item.setProgramName(dataobj.getString("name"));
                                item.setImageURL   (dataobj.getString("programimage"));
                                item.setProgramURL (dataobj.getString("programurl"));

                                contentList.add(item);
                            }
                            setupListview();
                            androidx.appcompat.app.ActionBar ab=getSupportActionBar();
                            assert ab != null;
                            // Modify displayed app title with a program count.
                            ab.setTitle("Sveriges Radio - "+dataArray.length()+" program");


                        } catch (JSONException e) {
                            Log.d ("retrieval","Error in JSON data parsing");
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Displaying the error in toast if it occurs  TODO: Change this
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        /** Asynchronous fetch management using 'Volley'.. */
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest); // Starts the asynchronous fetch
    }

    private void setupListview(){
        removeSimpleProgressDialog();
        rv.setAdapter(new ProgramAdapter(contentList, null));

        /*  Kept here for future consideration. The onItemClick handler is better
            placed here than in the Adapter..

                    public void onItemClick(AdapterView<? > arg0, View view, int position, long id) {
                        // "position" is the list index
                        String url=contentList.get(position).getProgramUrl();
                        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                        intent.putExtra("programurl",url);  // Pass url to detail page
                        Log.d("detail url","URL="+url);
                        startActivity(intent);
                    }
        */
    }

    /**
     * Removal of progress dialog
     */
    private static void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();

        }

    }

    /**
     *  The progress dialog shows up upon slow load..
     */
    private static void showSimpleProgressDialog(Context context, String title,
                                                 String msg, boolean isCancelable) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, title, msg);
                mProgressDialog.setCancelable(isCancelable);
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }

        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        }
    }
}
