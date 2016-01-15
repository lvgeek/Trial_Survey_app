package rhdr.afrl.trialsurvey;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PostExposureSurvey extends AppCompatActivity {


    // define elements
    String protocolVal;
    String medMonitorVal;
    String subjectVal;
    String shotcodeVal;
    String question1Val;
    String question2Val;
    String spotsVal;
    String locationVal;
    String skinVal;
    String examVal;
    String commentVal;
    String notesval;
    String rb1val, rb2val, rb3val;
    Button btn;
    TextView subjectID;
    EditText notes;
    TextView notesID;
    private Toolbar toolbar;
    private RadioGroup radioGroup, radioGroup2, radioGroup3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_exposure_survey);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        btn = (Button) findViewById(R.id.btnSave);
        btn.setEnabled(false);

        notes = (EditText) findViewById(R.id.txtnotes);
        notes.setVisibility(View.INVISIBLE);
        notesID = (TextView) findViewById(R.id.txt4);
        notesID.setVisibility(View.INVISIBLE);

        /* Initialize Radio Group and attach click handler */
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.clearCheck();
        radioGroup2 = (RadioGroup) findViewById(R.id.radioGroup2);
        radioGroup2.clearCheck();
        radioGroup3 = (RadioGroup) findViewById(R.id.radioGroup1);
        radioGroup3.clearCheck();


        Bundle extras = getIntent().getExtras();
        if (null != extras){
            protocolVal = extras.getString("Protocol");
            medMonitorVal = extras.getString("MedMonitor");
            subjectVal = extras.getString("Subject");
            shotcodeVal = extras.getString("Shotcode");
            question1Val = extras.getString("Question1");
            question2Val = extras.getString("Question2");
            question2Val = extras.getString("Question2");
            spotsVal = extras.getString("Spots");
            locationVal = extras.getString("Location");
            skinVal = extras.getString("Skin");
            examVal = extras.getString("Exam");
            commentVal = extras.getString("Comment");
        }

        subjectID = (TextView) findViewById(R.id.txtsubjectID);
        subjectID.setText(subjectVal);


    }

    public void onRadioButtonClick(View v) {
        if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton2 || radioGroup2.getCheckedRadioButtonId() == R.id.radioButton4 || radioGroup3.getCheckedRadioButtonId() == R.id.radioButton6) {
            notes.setVisibility(View.VISIBLE);
            notesID.setVisibility(View.VISIBLE);
        } else {
            notes.setVisibility(View.INVISIBLE);
            notesID.setVisibility(View.INVISIBLE);
        }
        if (radioGroup.getCheckedRadioButtonId() != -1 && radioGroup2.getCheckedRadioButtonId() != -1 && radioGroup3.getCheckedRadioButtonId() != -1) {
            btn.setEnabled(true);
        } else {
            btn.setEnabled(false);
        }
    }
    public void SaveTrial(View view) {

        // read all radiobutton values to save
        RadioButton rb = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
        rb1val = (String)rb.getText();
        RadioButton rb2 = (RadioButton) radioGroup2.findViewById(radioGroup2.getCheckedRadioButtonId());
        rb2val = (String)rb2.getText();
        RadioButton rb3 = (RadioButton) radioGroup3.findViewById(radioGroup3.getCheckedRadioButtonId());
        rb3val = (String)rb3.getText();
        notesval = notes.getText().toString();
        notesval = notesval.replaceAll(",", " ");
        notesval = notesval.replaceAll("\\n", " ");

        //get date and time
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String date = df.format(c.getTime());
        SimpleDateFormat df1 = new SimpleDateFormat("HHmmss");
        String time = df1.format(c.getTime());

        //csv string to write to file SSADT_Data.csv
        String saveStringVal = date + "," + time + "," + protocolVal + "," + medMonitorVal + "," + subjectVal + "," + shotcodeVal + "," + question1Val + "," + question2Val + "," + spotsVal + "," + locationVal + "," + skinVal + "," + examVal + "," + commentVal + "," + rb1val + "," + rb2val +"," + rb3val + "," + notesval +"\n";

        try {
            File savefile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "SSADT_Data.csv");
            //check if file exists if not create new file else append to existing file
            if (!savefile.exists()) {
                savefile.createNewFile();
                String hdrStringVal = "Date,Time,Protocol,Medical,Subject,Shotcode,Question1,Question2,NumSpots,Location,Skincompaint,SkinExam,Comments,PostQ1,PostQ2,PostQ3,PostNotes \n";
                BufferedWriter writer = new BufferedWriter(new FileWriter(savefile, true));//append
                writer.write(hdrStringVal);
                writer.close();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(savefile, true));//append
            writer.write(saveStringVal);
            writer.close();
        } catch (IOException e) {
            Toast.makeText(this, "Unable to write: " + saveStringVal, Toast.LENGTH_LONG).show();
        }

        radioGroup.clearCheck();
        radioGroup2.clearCheck();
        radioGroup3.clearCheck();
        notes.setText("");
        finish();

    }
}
