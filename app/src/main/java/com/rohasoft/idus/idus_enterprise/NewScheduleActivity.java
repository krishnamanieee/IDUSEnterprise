package com.rohasoft.idus.idus_enterprise;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.rohasoft.idus.idus_enterprise.Adapter.Schedule;
import com.rohasoft.idus.idus_enterprise.Adapter.ScheduleAdapter;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ayothi selvam on 10-11-2017.
 */

public class NewScheduleActivity extends AppCompatActivity {
    List<Schedule> list;
        ListView listView;

    Button button_share;
    ArrayList<String> arrayList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView= (ListView) findViewById(R.id.listView);
        button_share= (Button) findViewById(R.id.button_scheduleShare);

        list=new ArrayList<>();
        arrayList=new ArrayList<>();


        String tot_amount=getIntent().getExtras().getString("totalAmount");
        String duration_tot=getIntent().getExtras().getString("loanDurations");


        int tot_amt = Integer.parseInt(tot_amount);
        int duration = Integer.parseInt(duration_tot);
        String dt = getIntent().getExtras().getString("startDate");
        String loanOption = getIntent().getExtras().getString("loanOptions");

        int bal_amt=tot_amt;
        int due_amt=tot_amt/duration;

        button_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share();
            }
        });


       // Toast.makeText(getApplicationContext(),tot_amt+"",Toast.LENGTH_SHORT).show();



        // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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

                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                String output = sdf1.format(c.getTime());

            list.add(new Schedule(output,due_amt+"",bal_amt+""));
            arrayList.add("Due Date -"+output+"----"+" pay Amount - "+due_amt+"----"+"Balance due - "+bal_amt+"\n");


        }

        ScheduleAdapter adapter=new ScheduleAdapter(this,R.layout.schedule_item,list);


        listView.setAdapter(adapter);

    }

    private void share() {



     /*   File outputFile = new File(Environment.getExternalStoragePublicDirectory
                (Environment.DIRECTORY_DOWNLOADS), "example.pdf");
        Uri uri = Uri.fromFile(outputFile);

        Intent share = new Intent();
        share.setAction(Intent.ACTION_SEND);
        share.setType("application/pdf");
        share.putExtra(Intent.EXTRA_STREAM, uri);
        share.setPackage("com.whatsapp");

        startActivity(share);*/
        Intent share=new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
//                String shareBodyText = "i am test data for share button \n kris";
        String shareBodyText = arrayList+"";
        share.putExtra(android.content.Intent.EXTRA_SUBJECT, "Due Payment Details  ");
        share.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
        startActivity(Intent.createChooser(share, "Choose sharing method"));
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;
    @Override
    public void onBackPressed() {
        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            finish();
            System.exit(0);

        } else {
            Toast.makeText(getBaseContext(), "Press once again to exit!",
                    Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }
}
