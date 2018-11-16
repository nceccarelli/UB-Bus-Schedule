package com.example.nceccarelli.busschedule;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ChoosePoint extends AppCompatActivity {

    private String file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_point);
        Intent intent = getIntent();
        file = intent.getStringExtra("file");
        setTitle(intent.getStringExtra("title"));

        parseFile();
        start();
    }

    public String getFile() {
        return file;
    }

    private ArrayList<ArrayList<String>> arr = new ArrayList<>();

    private ArrayList<String> stops = new ArrayList<>();

    public ArrayList<String> getStops() {
        return stops;
    }

    public void setStops(ArrayList<String> stops) {
        this.stops = stops;
    }

    public void addToStops(String in) {
        this.stops.add(in);
    }
    public ArrayList<ArrayList<String>> getArr(){
        return this.arr;
    }

    public void setArr(ArrayList<ArrayList<String>> arr){
        this.arr = arr;
    }

    public void addToArr(ArrayList<String> intArr){
        ArrayList<ArrayList<String>> a = getArr();
        a.add(intArr);
    }
    public void parseFile(){
        addToStops("Tap to Select a Departure Point");
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open(file)));
            String line;

            while ((line = br.readLine()) != null){
                String[] data = line.split(",");
                addToStops(data[0]);
                ArrayList<String> name = new ArrayList<>();
                int count = 0;
                for (String s : data) {
                    if (count != 0 && !s.isEmpty()) {
                       name.add(s);
                    }
                    count++;
                }

                addToArr(name);

            }
            br.close();

        } catch (IOException e){

        }



    }
    public void start() {

        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        ArrayList<String> st = getStops();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, stops);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String s = adapterView.getSelectedItem().toString();
                if (!s.contains("Tap to Select a Departure Point")){
                    changeIntent(i, s);
                }

            }
            public void onNothingSelected(AdapterView<?> adapterView){

            }
        });




    }
    public void changeIntent(int pos, String title){
        Intent intent = new Intent(this, OpenSchedule.class);
        ArrayList<String> list = arr.get(pos-1);
        intent.putStringArrayListExtra("list", list);
        intent.putExtra("title", title);
        startActivity(intent);
    }

}


