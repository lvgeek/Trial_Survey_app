package rhdr.afrl.trialsurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class SettingsActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id==android.R.id.home) {
            finish();
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_NewProtocol) {
            Intent intent = new Intent(this, DisplayNewProtocolActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_EditProtocol) {
            Intent intent = new Intent(this, DisplayEditProtocolActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_ListProtocol) {
            Intent intent = new Intent(this, DisplaySQLite.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
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
