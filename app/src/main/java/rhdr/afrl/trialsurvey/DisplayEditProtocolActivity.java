package rhdr.afrl.trialsurvey;

import java.util.List;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class DisplayEditProtocolActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // define elements
    int _ID;
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
        txtProtocolID =(EditText) findViewById(R.id.txtProtocolID);
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
        _ID = selected.getID();
        txtProtocolID.setText(selected.getName());
        intNumSubjects.setText(String.valueOf(selected.getnumSubjects()));
        intNumShotcode.setText(String.valueOf(selected.getnumShotcodes()));
    }
    @Override
    public void onNothingSelected(AdapterView<?> parentView) {
        txtProtocolID.setText("");
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

    public void SavebtnClick(View view){

        DBHandler db = new DBHandler(this);
        if("".equals(txtProtocolID.getText().toString()) || "".equals(intNumSubjects.getText().toString()) || "".equals(intNumShotcode.getText().toString()))
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Sorry, you must input Protocol ID, Number of Subjects, and Number of Shotcodes!", Toast.LENGTH_LONG);
            toast.show();
        }
        else
        {
            long flag;
            Protocol protocol = new Protocol(txtProtocolID.getText().toString(),Integer.parseInt(intNumSubjects.getText().toString()), Integer.parseInt(intNumShotcode.getText().toString()));
            flag = db.updateProtocol(protocol);
            if(flag == 1)
            {
                Toast toast = Toast.makeText(getApplicationContext(), "You have successful updated this record in the database! ", Toast.LENGTH_LONG);
                toast.show();
                //close the activity
                finish();
            }
            else
            {
                Toast toast = Toast.makeText(getApplicationContext(), "An error occured when updating this record in the database!", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }
}
