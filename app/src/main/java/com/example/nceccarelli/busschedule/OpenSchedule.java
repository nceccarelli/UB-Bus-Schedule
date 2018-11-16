package com.example.nceccarelli.busschedule;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class OpenSchedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_schedule);

        Intent intent = getIntent();
        ArrayList<String> list = intent.getStringArrayListExtra("list");
        setTitle(intent.getStringExtra("title"));
        TextView scrollView = (TextView) findViewById(R.id.box);
        for (String s : list){
            scrollView.append(s);
            scrollView.append("\n");
        }
        scrollView.setMovementMethod(new ScrollingMovementMethod());


        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String c = sdf.format(new Date());
        Date current = null;
        try {
            Date cu = sdf.parse(c);
            current = cu;
        } catch (ParseException p){}

        String[] currentTime = c.split(":");
        ArrayList<Date> times = new ArrayList<>();
        int prevHour = 5;
        for (String s : list){
            if (!s.isEmpty()) {
                String[] tim = s.split(":");
                Integer hour = Integer.parseInt(tim[0]);
                Integer minute = Integer.parseInt(tim[1]);
                if ((prevHour - hour) > 0 ) {
                    hour += 12
;
                } else  {
                    prevHour = hour;

                }
                String curr = hour.toString() + ":" + minute.toString();

                try {
                    Date ti = (Date) sdf.parse(curr);
                    if (current.compareTo(ti) <= 0) {
                        times.add(ti);
                    }
                } catch (ParseException p) {
                }
            }
        }
        if (times.isEmpty()){
            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText("No next bus could be found");
        } else{
            Date max = times.get(0);
            String z = max.toString();
            String[] x = z.split(" ");
            String v = x[3];
            String[] b = v.split(":");
            Integer h = Integer.parseInt(b[0]);
            String m = b[1];
            if (h > 12){
                h -= 12;
            }

            String t = "The Next Bus/Shuttle is at:  " + h.toString() + ":" + m.toString();
            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText(t);
        }

    }

}
