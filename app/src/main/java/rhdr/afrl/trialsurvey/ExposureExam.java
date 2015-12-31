package rhdr.afrl.trialsurvey;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ExposureExam extends AppCompatActivity {

    // define elements
    String protocolVal;
    String medMonitorVal;
    String subjectVal;
    String shotcodeVal;
    String[] locations;
    String[] skins;
    String[] exams;
    Spinner spnrlocationID;
    Spinner spnrskinID;
    Spinner spnrexamID;
    Button btn;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exposure_exam);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);

        btn = (Button) findViewById(R.id.btnRunTrial);
        btn.setEnabled(false);


        locations = getResources().getStringArray(R.array.location_list);
        skins = getResources().getStringArray(R.array.skin_list);
        exams = getResources().getStringArray(R.array.exam_list);

        // link elements
        spnrlocationID = (Spinner) findViewById(R.id.spnrProtocolID);
        spnrskinID = (Spinner) findViewById(R.id.spnrmedMonitorID);
        spnrexamID = (Spinner) findViewById(R.id.spnrSubjectID);

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

        spnrlocationID.setSelection(0);
        spnrskinID.setSelection(0);
        spnrexamID.setSelection(0);

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
        }


    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

        if (spnrlocationID.getSelectedItemPosition() != 0 && spnrskinID.getSelectedItemPosition() != 0 && spnrexamID.getSelectedItemPosition() != 0) {
            btn.setEnabled(true);
        } else {
            btn.setEnabled(false);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parentView) {

    }


    /**
     * Called when the user clicks the Run Protocol button
     */
    public void showRunTrial(View view) {

        // read all spinner values to pass to RunTrialActivity
        final Spinner location = (Spinner) findViewById((R.id.spnrlocationID));
        final String locationVal = String.valueOf(location.getSelectedItem());
        final Spinner skin = (Spinner) findViewById((R.id.spnrskinID));
        final String skinVal = String.valueOf(skin.getSelectedItem());
        final Spinner exam = (Spinner) findViewById((R.id.spnrexamID));
        final String examVal = String.valueOf(exam.getSelectedItem());

        Intent intent = new Intent(this, RunTrialActivity.class);
        intent.putExtra("Protocol", protocolVal);
        intent.putExtra("MedMonitor", medMonitorVal);
        intent.putExtra("Subject", subjectVal);
        intent.putExtra("Shotcode", shotcodeVal);
        startActivity(intent);
        spnrSubjectID.setSelection(0);
        spnrShotCodeID.setSelection(0);
    }

}
