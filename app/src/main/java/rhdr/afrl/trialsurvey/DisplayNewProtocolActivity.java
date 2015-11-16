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
    EditText Question1;
    EditText Question1_min;
    EditText Question1_max;

    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_new_protocol);
        txtProtocolID = (EditText) findViewById(R.id.txtProtocolID);
        txtNumSubjects = (EditText) findViewById(R.id.intNumSubjects);
        txtNumShotcode = (EditText) findViewById(R.id.intNumShotcode);
        Question1 = (EditText) findViewById(R.id.txtQuestion);
        Question1_min = (EditText) findViewById(R.id.txtMin);
        Question1_max = (EditText) findViewById(R.id.txtMax);
        db = new DBHandler(this);
    }

    public void SavebtnClick(View view){

        if("".equals(txtProtocolID.getText().toString()) || "".equals(txtNumSubjects.getText().toString()) || "".equals(txtNumShotcode.getText().toString()) || "".equals(Question1.getText().toString()) || "".equals(Question1_min.getText().toString()) || "".equals(Question1_max.getText().toString())   )
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Sorry, you must input Protocol ID, Number of Subjects, and Number of Shotcodes!", Toast.LENGTH_LONG);
            toast.show();
        }
        else
        {
            long flag = 0;
            Protocol protocol = new Protocol(txtProtocolID.getText().toString(),Integer.parseInt(txtNumSubjects.getText().toString()), Integer.parseInt(txtNumShotcode.getText().toString()),Question1.getText().toString(),Question1_min.getText().toString(),Question1_max.getText().toString());
            flag = db.addProtocol(protocol);
            if(flag != -1)
            {
                Toast toast = Toast.makeText(getApplicationContext(), "You have successful inserted this record into database! ", Toast.LENGTH_LONG);
                toast.show();
                //clear the inputs for another add
                txtProtocolID.setText("");
                txtNumSubjects.setText("");
                txtNumShotcode.setText("");
                Question1.setText("");
                Question1_min.setText("");
                Question1_max.setText("");
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
