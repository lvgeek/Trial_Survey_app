package rhdr.afrl.trialsurvey;

import java.util.List;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import rhdr.afrl.trialsurvey.Protocol;
import rhdr.afrl.trialsurvey.DBHandler;

public class DisplayEditProtocolActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // define elements
    Spinner spnrProdocolID;
    EditText txtProtocolID;
    EditText intNumSubjects;
    EditText intNumShotcode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_edit_protocol);

        // link elements
        spnrProdocolID = (Spinner) findViewById(R.id.spnrProdocolID);
        intNumSubjects =(EditText) findViewById(R.id.intNumSubjects);
        intNumShotcode = (EditText) findViewById(R.id.intNumShotcode);

        // Spinner click listener
        spnrProdocolID.setOnItemSelectedListener(this);

        // Loading spinner data from database
        loadSpinnerData();

    }
    @Override
    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

        Protocol selected = (Protocol) parentView.getItemAtPosition(position);
        intNumSubjects.setText(selected.getnumSubjects());
        intNumShotcode.setText(selected.getnumShotcodes());

    }
    @Override
    public void onNothingSelected(AdapterView<?> parentView) {
        intNumSubjects.setText("");
        intNumShotcode.setText( "");
    }

    // Function to load the spinner data from SQLite database
    private void loadSpinnerData() {
        ProtocolSpinnerAdapter protocolAdapter;
        DBHandler db = new DBHandler(getApplicationContext());
        List<Protocol> protocolList = db.getAllProtocols();
        protocolAdapter = new ProtocolSpinnerAdapter(DisplayEditProtocolActivity.this,
                android.R.layout.simple_spinner_item, protocolList);
        spnrProdocolID.setAdapter(protocolAdapter);

        protocolAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

   }
}
