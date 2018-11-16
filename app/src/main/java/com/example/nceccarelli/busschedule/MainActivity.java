package com.example.nceccarelli.busschedule;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start();
    }

    public void start() {
        Calendar calendar = Calendar.getInstance();
        final int day = calendar.get(Calendar.DAY_OF_WEEK);


        Spinner spinner = (Spinner) findViewById(R.id.stampede_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String s = adapterView.getSelectedItem().toString();
                if (s.contains("Monday-Thursday")) {
                    MainActivity.this.changeIntent("fall_stampede_M-Th.csv", "Stampede Monday-Thursday");
                    if (day < 2 || day > 5) {
                        makeWarningToast();
                    }
                } else if (s.contains("Overnight Fri-Sat")) {
                    MainActivity.this.changeIntent("fall_stampede_F-Sa_overnight.csv", "Stampede Overnight Fri & Sat");
                    if (day != 6 || day != 7){
                        makeWarningToast();
                    }
                } else if (s.contains("Overnight Sun-Thu")) {
                    MainActivity.this.changeIntent("fall_stampede_Su-Th_overnight.csv", "Stampede Overnight Sun-Thu");
                    if (day != 7 || day != 1){
                        makeWarningToast();
                    }
                } else if (s.contains("Red")) {
                    MainActivity.this.changeIntent("fall_stampede_Lee-Ellicott-Express_Red-Line_M-F.csv", "Stampede Red Line Mon-Fri");
                    if (day == 7 || day == 1){
                        makeWarningToast();
                    }
                } else if (s.contains("Yellow")) {
                    MainActivity.this.changeIntent("fall_stampede_North-South-Express_Yellow-Line_M-F.csv", "Stampede Yellow Line Mon-Fri");
                    if (day == 7 || day == 1){
                        makeWarningToast();
                    }
                } else if (s.contains("Friday")) {
                    MainActivity.this.changeIntent("fall_stampede_F.csv", "Stampede Friday");
                    if (day != 6){
                        makeWarningToast();
                    }
                } else if (s.contains("Saturday")) {
                    MainActivity.this.changeIntent("fall_stampede_Sa.csv", "Stampede Saturday");
                    if (day != 7){
                        makeWarningToast();
                    }
                } else if (s.contains("Sunday")) {
                    MainActivity.this.changeIntent("fall_stampede_Su.csv", "Stampede Sunday");
                    if (day != 1){
                        makeWarningToast();
                    }
                } else if (s.contains("Governors-SU-Lee Loop-Ellicott")){
                    MainActivity.this.changeIntent("fall_stampede_Govs-SU-Lee-El_F-Sa.csv", "Govs-SU-Lee Loop-Ellicott Express");
                    if (day != 7 || day != 6){
                        makeWarningToast();
                    }
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView){
            }
        });
        Spinner spinner2 = (Spinner) findViewById(R.id.shuttle_spinner);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){
                String s = adapterView.getSelectedItem().toString();
                if (s.contains("Blue")){
                    MainActivity.this.changeIntent("fall_shuttle_blue_south-dt_M-F.csv", "Blue Line Mon-Fri");
                    if (day == 7 || day == 1){
                        makeWarningToast();
                    }
                } else if (s.contains("North Campus Mon-Fri")){
                    MainActivity.this.changeIntent("fall_shuttle_north_M-F.csv","North Campus Mon-Fri");
                    if (day == 7 || day == 1){
                        makeWarningToast();
                    }
                } else if (s.contains("Green")){
                    MainActivity.this.changeIntent("fall_shuttle_green_M-F.csv", "Green Line (CFT-Crofts-Flint Loop-Flint Village)");
                    if (day == 7 || day == 1){
                        makeWarningToast();
                    }
                } else if (s.contains("North Campus Sat-Sun")){
                    MainActivity.this.changeIntent("fall_shuttle_north_Sa-Su.csv", "North Campus Sat & Sun");
                    if (day != 7 || day != 1){
                        makeWarningToast();
                    }
                }
            }
            public void onNothingSelected(AdapterView<?> adapterView){

            }
        });
        Spinner spinner3 = (Spinner) findViewById(R.id.mall_market_spinner);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String s = adapterView.getSelectedItem().toString();
                if (s.contains("Wegmans")) {
                    MainActivity.this.changeIntent("fall_wegmans_express_WED_ONLY.csv", "WEDNESDAY ONLY Wegmans Express");
                    if (day != 4){
                        makeWarningToast();
                    }
                } else if (s.contains("Galleria")) {
                    MainActivity.this.changeIntent("fall_galleria_FRI_ONLY.csv", "FRIDAY ONLY Walden Galleria Fun Run");
                    if (day != 6){
                        makeWarningToast();
                    }
                } else if (s.contains("North Campus Mall-Market")) {
                    MainActivity.this.changeIntent("fall_Mall-Market_from_north_SAT.csv", "SATURDAY ONLY North Campus Mall-Market");
                    if (day != 7){
                        makeWarningToast();
                    }
                } else if (s.contains("South Campus Mall-Market")){
                    MainActivity.this.changeIntent("fall_Mall-Market_from_south_SAT.csv", "SATURDAY ONLY South Campus Mall-Market");
                    if (day != 7){
                        makeWarningToast();
                    }
                } else if (s.contains("Asian")) {
                    MainActivity.this.changeIntent("fall_Asian-Market_TUES_ONLY.csv", "TUESDAY ONLY Asian Market Express");
                    if (day != 3){
                        makeWarningToast();
                    }
                } else if (s.contains("Apartment-Mall-Market")){
                    MainActivity.this.changeIntent("fall_Apartment-Mall-Market_SAT.csv", "SATURDAY ONLY Apartment-Mall-Market");
                    if (day != 7){
                        makeWarningToast();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void changeIntent(String file, String title){
        Intent intent = new Intent(this, ChoosePoint.class);
        intent.putExtra("file", file);
        intent.putExtra("title", title);
        startActivity(intent);
    }

    public void makeWarningToast(){
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "WARNING: Incorrect Day Selected", Toast.LENGTH_LONG);
        toast.show();
    }
}











