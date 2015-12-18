package rhdr.afrl.trialsurvey;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class RunProto1Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // define elements
    int _ID;
    Spinner spnrProdocolID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_proto1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // link elements
        spnrProdocolID = (Spinner) findViewById(R.id.spnrProdocolID);


        // Spinner click listener
        spnrProdocolID.setOnItemSelectedListener(this);

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
        protocolAdapter = new ProtocolSpinnerAdapter(RunProto1Activity.this,
                android.R.layout.simple_spinner_item, protocolList);
        spnrProdocolID.setAdapter(protocolAdapter);

        protocolAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
}