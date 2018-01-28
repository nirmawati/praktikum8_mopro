package com.example.nirmawati.praktikum8;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Nirmawati on 11/24/2017.
 */

public class GetDataTask extends AsyncTask<Void,Void,Void> {
    private String url;
    private ArrayList<HashMap<String,String>> datas;

    public GetDataTask(String url, ArrayList<HashMap<String,String>> datas){
        this.url=url;
        this.datas=datas;

    }

    public ArrayList<HashMap<String, String>> getDatas() {
        return datas;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        HttpHandler hd =new HttpHandler();
        String resultJson = hd.makeServiceCall(url);

        try {
            JSONObject root = new JSONObject(resultJson);
            JSONArray mahasiswa = root.getJSONArray("mahasiswa");

            for (int i=0; i<mahasiswa.length(); i++){
                HashMap<String,String> dataMhs = new HashMap<>();
                JSONObject mhs = mahasiswa.getJSONObject(i);
                String nama = mhs.getString("nama");
                String nim = mhs.getString("nim");
                String prodi = mhs.getString("prodi");

                dataMhs.put("nama", nama);
                dataMhs.put("nim", nim);
                dataMhs.put("prodi", prodi);

                datas.add(dataMhs);
            }
        }catch ( Exception e){

        }
        return null;
    }
}