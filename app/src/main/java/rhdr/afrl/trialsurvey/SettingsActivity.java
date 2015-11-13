package rhdr.afrl.trialsurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    /** Called when the user clicks the NewProtocol button */
    public void showNewProto(View view) {
        Intent intent = new Intent(this, DisplayNewProtocolActivity.class);
        startActivity(intent);
    }
    /** Called when the user clicks the EditProtocol button */
    public void showEditProto(View view) {
        Intent intent = new Intent(this, DisplayEditProtocolActivity.class);
        startActivity(intent);
    }
    /** Called when the user clicks the ListProtocol button */
    public void showListProto(View view) {
        Intent intent = new Intent(this, DisplaySQLite.class);
        startActivity(intent);
    }
}
