package rhdr.afrl.trialsurvey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /** Called when the user clicks the New Protocal button */
    public void showNewProto(View view) {
        Intent intent = new Intent(this, DisplayNewProtocolActivity.class);
        startActivity(intent);
    }

}
