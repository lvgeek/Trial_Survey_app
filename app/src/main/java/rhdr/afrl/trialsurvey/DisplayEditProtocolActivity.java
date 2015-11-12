package rhdr.afrl.trialsurvey;

import java.util.List;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class DisplayEditProtocolActivity extends AppCompatActivity {

    // define elements
    Spinner spnrProdocolID;
    EditText txtProtocolID;
    EditText intNumSubjects;
    EditText intNumShotcode;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_edit_protocol);

        // link elements
        spnrProdocolID = (Spinner) findViewById(R.id.spnrProdocolID);
        intNumSubjects =(EditText) findViewById(R.id.intNumSubjects);
        intNumShotcode = (EditText) findViewById(R.id.intNumShotcode);

        // Spinner click listener
       // spnrProdocolID.setOnItemSelectedListener(this);

        // Loading spinner data from database
        loadSpinnerData();

    }

    // Function to load the spinner data from SQLite database
    private void loadSpinnerData() {

        // Spinner Drop down elements
        List<Protocol> protocolList = db.getAllProtocols();

        // Creating adapter for spinner
        ArrayAdapter<Protocol> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, protocolList);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spnrProdocolID.setAdapter(dataAdapter);
    }
}
