package com.example.android.xpensemanager;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Calendar extends AppCompatActivity {

    DataBaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDb = new DataBaseHelper(this);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_calendar);
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);

        SharedPreferences settings = getSharedPreferences("MyPrefsFile", 0);
        final String username = settings.getString("username", null);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String selectedDate = sdf.format(new Date(calendarView.getDate()));
                // add one because month starts at 0
                month = month + 1;
                // output to log cat **not sure how to format year to two places here**
                String newDate = year + "-" + month + "-" + day;
                Cursor cursor = myDb.retrieveData(username,selectedDate,"expenses");
                StringBuilder s1 = new StringBuilder();
                StringBuilder s2 = new StringBuilder();
                StringBuilder s3 = new StringBuilder();
                StringBuilder s4 = new StringBuilder();
                StringBuilder s5 = new StringBuilder();
                StringBuilder s6 = new StringBuilder();
                StringBuilder s7 = new StringBuilder();
                StringBuilder s8 = new StringBuilder();
                StringBuilder s9 = new StringBuilder();
                String a="",b="",c="",d="",e="",f="",g="",h="",i="";

               String[] cols = {"Food","Electronics","Transport","Gift","Bills","Home","Education","Child Care"," Miscellaneous"};
                while (cursor.moveToNext()) {
                    if(cursor.getString(1)!=null)
                    a="Food "+cursor.getString(1)+"\n";
                    if(cursor.getString(2)!=null)
                        b="Electronics "+cursor.getString(2)+"\n";
                    if(cursor.getString(3)!=null)
                        c="Transport "+cursor.getString(3)+"\n";
                    if(cursor.getString(4)!=null)
                        d="Gift "+cursor.getString(4)+"\n";
                    if(cursor.getString(5)!=null)
                        e="Bills "+cursor.getString(5)+"\n";
                    if(cursor.getString(6)!=null)
                        f="Home "+cursor.getString(6)+"\n";
                    if(cursor.getString(7)!=null)
                        g="Education "+cursor.getString(7)+"\n";
                    if(cursor.getString(8)!=null)
                        h="Child Care "+cursor.getString(8)+"\n";
                    if(cursor.getString(9)!=null)
                        i="Miscellaneous "+cursor.getString(9)+"\n";




                }

                AlertDialog.Builder builder = new AlertDialog.Builder(Calendar.this);
                builder.setCancelable(true);
                builder.setTitle("Here is your expense");
                String m=a+b+c+d+e+f+g+h+i;
                if(m==null)
                    builder.setMessage("No expense recorded");
                else
                    builder.setMessage(m);
                builder.show();

            }

            ;


        });
    }
}
