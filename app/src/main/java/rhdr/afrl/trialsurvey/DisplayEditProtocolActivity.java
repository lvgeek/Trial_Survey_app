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
    EditText Question1;
    EditText Question1_min;
    EditText Question1_max;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_edit_protocol);

        // link elements
        spnrProdocolID = (Spinner) findViewById(R.id.spnrProdocolID);
        txtProtocolID =(EditText) findViewById(R.id.txtProtocolID);
        intNumSubjects =(EditText) findViewById(R.id.intNumSubjects);
        intNumShotcode = (EditText) findViewById(R.id.intNumShotcode);
        Question1 = (EditText) findViewById(R.id.txtQuestion);
        Question1_min = (EditText) findViewById(R.id.txtMin);
        Question1_max = (EditText) findViewById(R.id.txtMax);


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
        Question1.setText(selected.getquestion1());
        Question1_min.setText(selected.getquestion1_min());
        Question1_max.setText(selected.getquestion1_max());
    }
    @Override
    public void onNothingSelected(AdapterView<?> parentView) {
        txtProtocolID.setText("");
        intNumSubjects.setText("");
        intNumShotcode.setText( "");
        Question1.setText("");
        Question1_min.setText("");
        Question1_max.setText("");
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
            long flag = 0;
            Protocol protocol = new Protocol(txtProtocolID.getText().toString(),Integer.parseInt(intNumSubjects.getText().toString()), Integer.parseInt(intNumShotcode.getText().toString()),Question1.getText().toString(),Question1_min.getText().toString(),Question1_max.getText().toString());
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
                return;
            }
        }
    }
}
