package rhdr.afrl.trialsurvey;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

public class DisplaySQLite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_sqlite);
 /*
        // my_child_toolbar is defined in the layout file
        Toolbar myChildToolbar =
                (Toolbar) findViewById(R.id.sql_toolbar);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
*/
        ListView lv = (ListView) findViewById(R.id.listViewProtocol);

        DBHandler db = new DBHandler(getApplicationContext());
        ArrayList<Protocol> protocolList = (ArrayList<Protocol>) db.getAllProtocols();

        //ArrayAdapter<Protocol> adapter = new ArrayAdapter<Protocol>(this, android.R.layout.simple_list_item_1, protocolList);
        ProtocolViewAdapter adapter = new ProtocolViewAdapter(this, protocolList);
        lv.setAdapter(adapter);
    }

    /** Called when the user clicks the Backup Database button */
    public void copyAppDbToDownloadFolder(View view) throws IOException {
        try {
            File backupDB = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "TrialSurvey_backup.db"); // for example "my_data_backup.db"

            File currentDB = getApplicationContext().getDatabasePath("TrialSurvey"); //databaseName=your current application database name, for example "my_data.db"
            if (currentDB.exists()) {
                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
                Toast.makeText(getApplicationContext(), "Backup Complete", Toast.LENGTH_LONG).show();
            }
        }
        catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Copying Database failed", Toast.LENGTH_LONG).show();
        }

    }


}
