package rhdr.afrl.trialsurvey;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by dave on 12/31/15.
 */
public class SaveFiles {


    public void saveTrial(Context context, String saveString) {

        //get date and time
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String date = df.format(c.getTime());
        SimpleDateFormat df1 = new SimpleDateFormat("HHmmss");
        String time = df1.format(c.getTime());

        //csv string to write to file SSADT_Data.csv
        String saveStringVal = date + "," + time + "," + saveString;

        try {
            File savefile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "SSADT_Data.csv");
            //check if file exists if not create new file else append to existing file
            if (!savefile.exists())
                savefile.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(savefile, true));//append
            writer.write(saveStringVal);
            writer.close();
        } catch (IOException e) {
            Toast.makeText(context, "Unable to write: " + saveStringVal, Toast.LENGTH_LONG).show();
        }
    }
}
