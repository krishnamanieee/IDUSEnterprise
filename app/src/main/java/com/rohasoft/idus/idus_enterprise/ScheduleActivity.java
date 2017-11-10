package com.rohasoft.idus.idus_enterprise;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.rohasoft.idus.idus_enterprise.Adapter.Schedule;
import com.rohasoft.idus.idus_enterprise.Adapter.ScheduleAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity {

    List<Schedule> list;
    ListView listView;

    int tot_amt=14000;
    int duration=10;
    String dt = "10-11-2017";
    int paid_amt=0;
    int due_amt=0;
    int bal_amt=tot_amt;
    String loanOption="Weekly";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView= (ListView) findViewById(R.id.listView);
        list=new ArrayList<>();
        due_amt=tot_amt/duration;


        Toast.makeText(getApplicationContext(),tot_amt+"",Toast.LENGTH_SHORT).show();



        // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dt));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        list.add(new Schedule("Due Date","Pay Amount","Balance Amount"));
        for (int i=0;i<duration;i++){
            bal_amt=bal_amt-due_amt;
            if (loanOption.equals("Daily")){

                c.add(Calendar.DATE, 1 );

            }
            else if(loanOption.equals("Weekly")){


                c.add(Calendar.DATE, 7);

            }
            else if(loanOption.equals("By Weekly")){


                c.add(Calendar.DATE, 15);

            }
            else if(loanOption.equals("Monthly")){


                c.add(Calendar.MONTH, 1);

            }

            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
            String output = sdf1.format(c.getTime());

           list.add(new Schedule(output,due_amt+"",bal_amt+""));


        }

        ScheduleAdapter adapter=new ScheduleAdapter(this,R.layout.schedule_item,list);


        listView.setAdapter(adapter);



    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
