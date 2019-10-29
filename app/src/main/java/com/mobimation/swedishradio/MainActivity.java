package com.mobimation.swedishradio;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
    private ListView listView;
    private ArrayList<DataModel> dataModelArrayList;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Log.d(this.getClass().getName(), "MainActivity back button pressed");
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lv);
        /* The ListView item click handler will launch the DetailActivity
           to display the radio program web page for that list entry. */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<? > arg0, View view, int position, long id) {
                // "position" is the list index
                String url=dataModelArrayList.get(position).getProgramUrl();
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("programurl",url);  // Pass url to detail page
                Log.d("detail url","URL="+url);
                startActivity(intent);
            }
        });
        
        retrieveJSON();
    }

    private void retrieveJSON() {

        showSimpleProgressDialog(this, "Loading...","Fetching Json",false);

        String URLstring = "http://api.sr.se/api/v2/programs?format=json&size=40";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);

                            dataModelArrayList = new ArrayList<>();
                            JSONArray dataArray  = obj.getJSONArray("programs");
 //                         Log.d ("srdata", "got "+obj.length()+" entries.");

                            for (int i = 0; i < dataArray.length(); i++) {

                                DataModel listRowModel = new DataModel();
                                JSONObject dataobj = dataArray.getJSONObject(i);
                                // Copy data from JSON array to data model row
                                listRowModel.setProgramName(dataobj.getString("name"));
                                listRowModel.setImageURL   (dataobj.getString("programimage"));
                                listRowModel.setProgramURL (dataobj.getString("programurl"));

                                dataModelArrayList.add(listRowModel);
                            }
                            setupListview();

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

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }

    private void setupListview(){
        removeSimpleProgressDialog();  //will remove progress dialog
        ListAdapter listAdapter = new ListAdapter(this, dataModelArrayList);
        listView.setAdapter(listAdapter);
    }







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

        } catch (Exception re) {
            re.printStackTrace();
        }

    }

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
        } catch (Exception re) {
            re.printStackTrace();
        }
    }
}
