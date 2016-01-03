package rhdr.afrl.trialsurvey;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ExposureExam extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // define elements
    String protocolVal;
    String medMonitorVal;
    String subjectVal;
    String shotcodeVal;
    String question1Val;
    String question2Val;
    String[] locations;
    String[] skins;
    String[] exams;
    Spinner spnrlocationID;
    Spinner spnrskinID;
    Spinner spnrexamID;
    EditText comment;
    Button btn, btn2;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exposure_exam);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        btn = (Button) findViewById(R.id.btnSaveNextTrial);
        btn.setEnabled(false);
        btn2 = (Button) findViewById(R.id.btnSaveEndSession);
        btn2.setEnabled(false);


        locations = getResources().getStringArray(R.array.location_list);
        skins = getResources().getStringArray(R.array.skin_list);
        exams = getResources().getStringArray(R.array.exam_list);

        // link elements
        spnrlocationID = (Spinner) findViewById(R.id.spnrlocationID);
        spnrskinID = (Spinner) findViewById(R.id.spnrskinID);
        spnrexamID = (Spinner) findViewById(R.id.spnrexamID);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, locations);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        spnrlocationID.setAdapter(dataAdapter);

        ArrayAdapter<String> dataAdapterskin = new ArrayAdapter<String>(this,
                R.layout.spinner_item, skins);
        dataAdapterskin.setDropDownViewResource(R.layout.spinner_item);
        spnrskinID.setAdapter(dataAdapterskin);

        ArrayAdapter<String> dataAdapterexam = new ArrayAdapter<String>(this,
                R.layout.spinner_item, exams);
        dataAdapterexam.setDropDownViewResource(R.layout.spinner_item);
        spnrexamID.setAdapter(dataAdapterexam);

        comment = (EditText) findViewById(R.id.editcommentID);

        /*comment.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });*/


        //set all inputs to null
        spnrlocationID.setSelection(0);
        spnrskinID.setSelection(0);
        spnrexamID.setSelection(0);
        comment.setText("");


        // Spinner click listener
        spnrlocationID.setOnItemSelectedListener(this);
        spnrskinID.setOnItemSelectedListener(this);
        spnrexamID.setOnItemSelectedListener(this);


        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            protocolVal = extras.getString("Protocol");
            medMonitorVal = extras.getString("MedMonitor");
            subjectVal = extras.getString("Subject");
            shotcodeVal = extras.getString("Shotcode");
            question1Val = extras.getString("Question1");
            question2Val = extras.getString("Question2");
        }


    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

        if (spnrlocationID.getSelectedItemPosition() != 0 && spnrskinID.getSelectedItemPosition() != 0 && spnrexamID.getSelectedItemPosition() != 0) {
            btn.setEnabled(true);
            btn2.setEnabled(true);
        } else {
            btn.setEnabled(false);
            btn2.setEnabled(false);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parentView) {

    }


    /**
     * Called when the user clicks the SaveNextTrial button
     */
    public void showSaveNextTrial(View view) {

        // read all spinner values to pass to Next Activity
        final Spinner location = (Spinner) findViewById((R.id.spnrlocationID));
        final String locationVal = String.valueOf(location.getSelectedItem());
        final Spinner skin = (Spinner) findViewById((R.id.spnrskinID));
        final String skinVal = String.valueOf(skin.getSelectedItem());
        final Spinner exam = (Spinner) findViewById((R.id.spnrexamID));
        final String examVal = String.valueOf(exam.getSelectedItem());
        final String commentVal = comment.getText().toString();

        //get date and time
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String date = df.format(c.getTime());
        SimpleDateFormat df1 = new SimpleDateFormat("HHmmss");
        String time = df1.format(c.getTime());

        //csv string to write to file SSADT_Data.csv
        String saveStringVal = date + "," + time + "," + protocolVal + "," + medMonitorVal + "," + subjectVal + "," + shotcodeVal + "," + question1Val + "," + question2Val + "," + locationVal + "," + skinVal + "," + examVal + "," + commentVal + "\n";

        try {
            File savefile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "SSADT_Data.csv");
            //check if file exists if not create new file else append to existing file
            if (!savefile.exists())
                savefile.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(savefile, true));//append
            writer.write(saveStringVal);
            writer.close();
        } catch (IOException e) {
            Toast.makeText(this, "Unable to write: " + saveStringVal, Toast.LENGTH_LONG).show();
        }

        spnrlocationID.setSelection(0);
        spnrskinID.setSelection(0);
        spnrexamID.setSelection(0);
        comment.setText("");
        finish();

    }

    /**
     * Called when the user clicks the SaveEndSession button
     */
    public void showSaveEndSession(View view) {

        // read all spinner values to pass to Next Activity
        final Spinner location = (Spinner) findViewById((R.id.spnrlocationID));
        final String locationVal = String.valueOf(location.getSelectedItem());
        final Spinner skin = (Spinner) findViewById((R.id.spnrskinID));
        final String skinVal = String.valueOf(skin.getSelectedItem());
        final Spinner exam = (Spinner) findViewById((R.id.spnrexamID));
        final String examVal = String.valueOf(exam.getSelectedItem());
        final String commentVal = comment.getText().toString();

        Intent intent = new Intent(this, PostExposureSurvey.class);
        intent.putExtra("Protocol", protocolVal);
        intent.putExtra("MedMonitor", medMonitorVal);
        intent.putExtra("Subject", subjectVal);
        intent.putExtra("Shotcode", shotcodeVal);
        intent.putExtra("Question1", question1Val);
        intent.putExtra("Question2", question2Val);
        intent.putExtra("Location", locationVal);
        intent.putExtra("Skin", skinVal);
        intent.putExtra("Exam", examVal);
        intent.putExtra("Comment", commentVal);
        startActivity(intent);

        spnrlocationID.setSelection(0);
        spnrskinID.setSelection(0);
        spnrexamID.setSelection(0);
        comment.setText("");

    }

}
