package com.example.nirmawati.praktikum8;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private ListView lstData;
    private final static  String URL = "http://192.168.43.86/mopro/getdata.php";
    private ArrayList<HashMap<String,String>> datas;
    private GetDataTask getDataTask;
    private AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstData = (ListView) findViewById(R.id.lst_data);//casting
        datas= new ArrayList<>();
        doRequest();
    }

    private void showDialog(String message){
        if (dialog == null) dialog = new AlertDialog.Builder(this).create();
        dialog.setMessage(message);
        dialog.setCancelable(false);
        dialog.show();
    }

    private void hideDialog(){
        if (dialog.isShowing()){
            dialog.dismiss();
        }

    }

    private  void doRequest(){
        getDataTask = new GetDataTask(URL,datas){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showDialog("Silahkan Tunggu...");
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                hideDialog();
                ListAdapter adapter = new SimpleAdapter(
                        MainActivity.this,
                        getDatas(),
                        R.layout.layout_item, new  String[]{"nama", "prodi"}, new int[]{ R.id.txt_name, R.id.txt_nIM}
                );
                lstData.setAdapter(adapter);
            }
        };
        getDataTask.execute();
    }
}
