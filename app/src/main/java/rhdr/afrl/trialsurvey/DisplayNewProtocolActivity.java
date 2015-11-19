package rhdr.afrl.trialsurvey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DisplayNewProtocolActivity extends AppCompatActivity {

    EditText txtProtocolID;
    EditText txtNumSubjects;
    EditText txtNumShotcode;

    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_new_protocol);
        txtProtocolID = (EditText) findViewById(R.id.txtProtocolID);
        txtNumSubjects = (EditText) findViewById(R.id.intNumSubjects);
        txtNumShotcode = (EditText) findViewById(R.id.intNumShotcode);
        db = new DBHandler(this);
    }

    public void SavebtnClick(View view){

        if("".equals(txtProtocolID.getText().toString()) || "".equals(txtNumSubjects.getText().toString()) || "".equals(txtNumShotcode.getText().toString()) )
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Sorry, you must input Protocol ID, Number of Subjects, and Number of Shotcodes!", Toast.LENGTH_LONG);
            toast.show();
        }
        else
        {
            long flag = 0;
            Protocol protocol = new Protocol(txtProtocolID.getText().toString(),Integer.parseInt(txtNumSubjects.getText().toString()), Integer.parseInt(txtNumShotcode.getText().toString()));
            flag = db.addProtocol(protocol);
            if(flag != -1)
            {
                Toast toast = Toast.makeText(getApplicationContext(), "You have successful inserted this record into database! ", Toast.LENGTH_LONG);
                toast.show();
                //clear the inputs for another add
                txtProtocolID.setText("");
                txtNumSubjects.setText("");
                txtNumShotcode.setText("");
                return;
            }
            else
            {
                Toast toast = Toast.makeText(getApplicationContext(), "An error occured when insert this record into database!", Toast.LENGTH_LONG);
                toast.show();
                return;
            }
        }
    }
}
