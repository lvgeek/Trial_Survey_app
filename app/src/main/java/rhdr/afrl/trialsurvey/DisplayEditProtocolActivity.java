package rhdr.afrl.trialsurvey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class DisplayEditProtocolActivity extends AppCompatActivity {
    EditText txtProtocolID;
    EditText txtNumSubjects;
    EditText txtNumShotcode;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_edit_protocol);
    }
}
