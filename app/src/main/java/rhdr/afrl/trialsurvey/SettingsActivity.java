package rhdr.afrl.trialsurvey;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        /*// my_child_toolbar is defined in the layout file
        Toolbar myChildToolbar =
                (Toolbar) findViewById(R.id.settings_toolbar);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);*/
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
