package rhdr.afrl.trialsurvey;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

public class RunProtocolActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // define elements
    int _ID;
    String[] protocols;
    String[] subjects;
    String[] shotcodes;
    Spinner spnrProtocolID;
    Spinner spnrSubjectID;
    Spinner spnrShotCodeID;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_protoco1);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);

        protocols = getResources().getStringArray(R.array.protocol_list);
        subjects = getResources().getStringArray(R.array.subject_list);
        shotcodes = getResources().getStringArray(R.array.shotcode_list);

        // link elements
        spnrProtocolID = (Spinner) findViewById(R.id.spnrProtocolID);
        spnrSubjectID = (Spinner) findViewById(R.id.spnrSubjectID);
        spnrShotCodeID = (Spinner) findViewById(R.id.spnrShotCodeID);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, protocols);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        spnrProtocolID.setAdapter(dataAdapter);

        ArrayAdapter<String> dataAdaptersubject = new ArrayAdapter<String>(this,
                R.layout.spinner_item, subjects);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        spnrSubjectID.setAdapter(dataAdaptersubject);

        ArrayAdapter<String> dataAdaptershotcode = new ArrayAdapter<String>(this,
                R.layout.spinner_item, shotcodes);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        spnrShotCodeID.setAdapter(dataAdaptershotcode);



        // Spinner click listener
        spnrProtocolID.setOnItemSelectedListener(this);


    }
    @Override
    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

        /*Protocol selected = (Protocol) parentView.getItemAtPosition(position);
        _ID = selected.getID();*/
    }
    @Override
    public void onNothingSelected(AdapterView<?> parentView) {

    }
 /*   // Function to load the spinner data from SQLite database
    private void loadSpinnerData() {
        ProtocolSpinnerAdapter protocolAdapter;
        DBHandler db = new DBHandler(getApplicationContext());
        List<Protocol> protocolList = db.getAllProtocols();
        protocolAdapter = new ProtocolSpinnerAdapter(RunProtocolActivity.this,
                android.R.layout.simple_spinner_item, protocolList);
        spnrProtocolID.setAdapter(protocolAdapter);

        protocolAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
*/

    /**
     * Called when the user clicks the Run Protocol button
     */
    public void showRunTrial(View view) {
        Intent intent = new Intent(this, RunTrialActivity.class);
        startActivity(intent);
    }
}