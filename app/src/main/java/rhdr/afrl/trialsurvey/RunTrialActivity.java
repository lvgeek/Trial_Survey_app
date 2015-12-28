package rhdr.afrl.trialsurvey;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RunTrialActivity extends AppCompatActivity {
    String protocol;
    String subject;
    String shotcode;
    String date;
    String time;
    String seekBarval;
    String seekBar2val;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_trial);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);


        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            protocol = extras.getString("Protocol");
            subject = extras.getString("Subject");
            shotcode = extras.getString("Shotcode");
        }

    }
    /**
     * Called when the user clicks the Save button
     */
    public void SaveTrial(View view) {
        //read both seekbars
        SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);
        SeekBar seekBar2 = (SeekBar)findViewById(R.id.seekBar2);
        seekBarval = String.valueOf((float) seekBar.getProgress() / seekBar.getMax());
        seekBar2val = String.valueOf((float) seekBar2.getProgress()/ seekBar2.getMax());

        //get date and time
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String date = df.format(c.getTime());
        SimpleDateFormat df1 = new SimpleDateFormat("HHmmss");
        String time = df1.format(c.getTime());

        //csv string to write to file SSADT_Data.csv
        String saveString = date + "," + time + "," + protocol + "," + subject + "," + shotcode + "," + seekBarval + "," + seekBar2val + "\n";

        try{
            File savefile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "SSADT_Data.csv");
            //check if file exists if not create new file else append to existing file
            if (!savefile.exists())
                savefile.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(savefile, true));//append
            writer.write(saveString);
            writer.close();
        }
        catch (IOException e) {
            Toast.makeText(this,"Unable to write: "+ saveString,Toast.LENGTH_LONG).show();
        }
        finish(); //returns to RunProtocolActivity
    }
}
