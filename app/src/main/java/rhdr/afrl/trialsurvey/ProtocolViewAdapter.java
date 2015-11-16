package rhdr.afrl.trialsurvey;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dave on 11/16/15.
 */
public class ProtocolViewAdapter extends ArrayAdapter<Protocol> {
        public ProtocolViewAdapter(Context context, ArrayList<Protocol> protocol) {
            super(context, 0, protocol);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Protocol protocol = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, parent, false);
            }
            // Lookup view for data population
            TextView tvID = (TextView) convertView.findViewById(R.id.tvID);
            TextView tvProtocolID = (TextView) convertView.findViewById(R.id.tvProtocolID);
            TextView tvNumSubjects = (TextView) convertView.findViewById(R.id.tvNumSubjects);
            TextView tvNumShotcode = (TextView) convertView.findViewById(R.id.tvNumShotcode);

            // Populate the data into the template view using the data object
            tvID.setText(String.valueOf(protocol.getID()));
            tvProtocolID.setText(protocol._name);
            tvNumSubjects.setText(String.valueOf(protocol._numSubjects));
            tvNumShotcode.setText(String.valueOf(protocol._numShotcodes));
            // Return the completed view to render on screen
            return convertView;
        }
    }
