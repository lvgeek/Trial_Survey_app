package rhdr.afrl.trialsurvey;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;


public class DisplaySQLite extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_sqlite);
        ListView lv = (ListView) findViewById(R.id.listViewProtocol);
        //Button close =(Button) findViewById(R.id.btnClose);

        DBHandler db = new DBHandler(getApplicationContext());
        ArrayList<Protocol> protocolList = (ArrayList<Protocol>) db.getAllProtocols();

        ArrayAdapter<Protocol> adapter = new ArrayAdapter<Protocol>(this, android.R.layout.simple_list_item_1, protocolList);
        lv.setAdapter(adapter);
    }

    /** Called when the user clicks the Close button */
    public void closeActivity(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
        finish();
    }



}
