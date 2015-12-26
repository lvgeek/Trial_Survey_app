package rhdr.afrl.trialsurvey;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.List;

public class RunProtocolActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // define elements
    int _ID;
    Spinner spnrProtocolID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_protocol);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // link elements
        spnrProtocolID = (Spinner) findViewById(R.id.spnrProtocolID);


        // Spinner click listener
        spnrProtocolID.setOnItemSelectedListener(this);

        // Loading spinner data from database
        loadSpinnerData();
    }
    @Override
    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

        Protocol selected = (Protocol) parentView.getItemAtPosition(position);
        _ID = selected.getID();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parentView) {

    }
    // Function to load the spinner data from SQLite database
    private void loadSpinnerData() {
        ProtocolSpinnerAdapter protocolAdapter;
        DBHandler db = new DBHandler(getApplicationContext());
        List<Protocol> protocolList = db.getAllProtocols();
        protocolAdapter = new ProtocolSpinnerAdapter(RunProtocolActivity.this,
                android.R.layout.simple_spinner_item, protocolList);
        spnrProtocolID.setAdapter(protocolAdapter);

        protocolAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
}