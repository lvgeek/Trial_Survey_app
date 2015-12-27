package rhdr.afrl.trialsurvey;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.SeekBar;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

public class RunTrialActivity extends AppCompatActivity {
    String protocol;
    String subject;
    String shotcode;
    String date;
    String time;
    int seekBarval;
    int seekBar2val;

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
        seekBarval = seekBar.getProgress() / seekBar.getMax();
        seekBar2val = seekBar2.getProgress()/ seekBar2.getMax();
        //get date and time
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        String[] splitStringArray = currentDateTimeString.split(" ");
        date = splitStringArray[0];
        time = splitStringArray[1];

        String saveString = date + "," + time + "," + protocol + "," + subject + "," + shotcode + "," + String.valueOf(seekBarval) + "," + String.valueOf(seekBar2val);

        try{
            File savefile =new File(((Context)this).getExternalFilesDir(null), "SSADT_Data.csv");
            if (!savefile.exists())
                savefile.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(savefile, true));
            writer.write(saveString);
            writer.close();
            MediaScannerConnection.scanFile((Context)(this),new String[] {savefile.toString()},null,null);
        }
        catch (IOException e) {
        }
    }
}
