package rhdr.afrl.trialsurvey;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
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
import java.util.Random;

public class RunTrialActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    String protocol;
    String medMonitor;
    String subject;
    String shotcode;
    String date;
    String time;
    String seekBarval;
    String seekBar2val;
    SeekBar seekBar, seekBar2;
    Button btn;
    int seekchng, seekchng2;
    Random rand = new Random();

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_trial);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);

        btn = (Button) findViewById(R.id.btnSave);
        btn.setEnabled(false);

        seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setProgress(50);
 //       seekBar.setProgress(rand.nextInt(seekBar.getMax()));
        seekBar.setOnSeekBarChangeListener(this);
        seekBar2 = (SeekBar)findViewById(R.id.seekBar2);
        seekBar2.setProgress(50);
 //       seekBar2.setProgress(rand.nextInt(seekBar2.getMax()));
        seekBar2.setOnSeekBarChangeListener(this);

        seekchng = 0;
        seekchng2 = 0;


        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            protocol = extras.getString("Protocol");
            medMonitor = extras.getString("MedMonitor");
            subject = extras.getString("Subject");
            shotcode = extras.getString("Shotcode");
        }

    }
    @Override
    public void onProgressChanged(SeekBar bar, int progress, boolean fromUser)
    {
        if (bar.equals(seekBar))
        {
            seekchng = 1;
        }
        else if (bar.equals(seekBar2))
        {
            seekchng2 = 1;
        }
        if (seekchng!=0 && seekchng2!=0) {
            btn.setEnabled(true);
        }else {
            btn.setEnabled(false);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


    /**
     * Called when the user clicks the Save button
     */
    public void SaveTrial(View view) {
        //read both seekbars

        seekBarval = String.valueOf((float) seekBar.getProgress() / seekBar.getMax());
        seekBar2val = String.valueOf((float) seekBar2.getProgress()/ seekBar2.getMax());


        finish(); //returns to RunProtocolActivity
    }
}
