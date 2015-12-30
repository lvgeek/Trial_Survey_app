package rhdr.afrl.trialsurvey;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.List;

public class RunProtocolActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // define elements
    String[] protocols;
    String[] medMonitors;
    String[] subjects;
    String[] shotcodes;
    Spinner spnrProtocolID;
    Spinner spnrmedMonitorID;
    Spinner spnrSubjectID;
    Spinner spnrShotCodeID;
    Button btn;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_protoco1);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);

        btn = (Button) findViewById(R.id.btnRunTrial);
        btn.setEnabled(false);

        protocols = getResources().getStringArray(R.array.protocol_list);
        medMonitors = getResources().getStringArray(R.array.medMonitor_list);
        subjects = getResources().getStringArray(R.array.subject_list);
        shotcodes = getResources().getStringArray(R.array.shotcode_list);

        // link elements
        spnrProtocolID = (Spinner) findViewById(R.id.spnrProtocolID);
        spnrmedMonitorID = (Spinner) findViewById(R.id.spnrmedMonitorID);
        spnrSubjectID = (Spinner) findViewById(R.id.spnrSubjectID);
        spnrShotCodeID = (Spinner) findViewById(R.id.spnrShotCodeID);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, protocols);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        spnrProtocolID.setAdapter(dataAdapter);

        ArrayAdapter<String> dataAdaptermed = new ArrayAdapter<String>(this,
                R.layout.spinner_item, medMonitors);
        dataAdaptermed.setDropDownViewResource(R.layout.spinner_item);
        spnrmedMonitorID.setAdapter(dataAdaptermed);

        ArrayAdapter<String> dataAdaptersubject = new ArrayAdapter<String>(this,
                R.layout.spinner_item, subjects);
        dataAdaptersubject.setDropDownViewResource(R.layout.spinner_item);
        spnrSubjectID.setAdapter(dataAdaptersubject);

        ArrayAdapter<String> dataAdaptershotcode = new ArrayAdapter<String>(this,
                R.layout.spinner_item, shotcodes);
        dataAdaptershotcode.setDropDownViewResource(R.layout.spinner_item);
        spnrShotCodeID.setAdapter(dataAdaptershotcode);

        spnrmedMonitorID.setSelection(0);
        spnrSubjectID.setSelection(0);
        spnrShotCodeID.setSelection(0);

        // Spinner click listener
        spnrmedMonitorID.setOnItemSelectedListener(this);
        spnrSubjectID.setOnItemSelectedListener(this);
        spnrShotCodeID.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

        if (spnrSubjectID.getSelectedItemPosition()!=0 && spnrShotCodeID.getSelectedItemPosition()!=0 && spnrmedMonitorID.getSelectedItemPosition()!=0) {
            btn.setEnabled(true);
        }else {
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
        final Spinner protocol = (Spinner) findViewById((R.id.spnrProtocolID));
        final String protocolVal = String.valueOf(protocol.getSelectedItem());
        final Spinner medMonitor = (Spinner) findViewById((R.id.spnrmedMonitorID));
        final String medMonitorVal = String.valueOf(medMonitor.getSelectedItem());
        final Spinner subject = (Spinner) findViewById((R.id.spnrSubjectID));
        final String subjectVal = String.valueOf(subject.getSelectedItem());
        final Spinner shotcode = (Spinner) findViewById((R.id.spnrShotCodeID));
        final String shotcodeVal = String.valueOf(shotcode.getSelectedItem());

        Intent intent = new Intent(this, RunTrialActivity.class);
        intent.putExtra("Protocol", protocolVal);
        intent.putExtra("MedMonitor", medMonitorVal);
        intent.putExtra("Subject",subjectVal);
        intent.putExtra("Shotcode",shotcodeVal);
        startActivity(intent);
        spnrSubjectID.setSelection(0);
        spnrShotCodeID.setSelection(0);
    }
}