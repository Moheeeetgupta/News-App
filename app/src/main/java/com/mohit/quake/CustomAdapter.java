package com.mohit.quake;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

class CustomAdapter extends ArrayAdapter<Earthquake> {



/**
 * This is our own custom constructor (it doesn't mirror a superclass constructor).
 * The context is used to inflate the layout file, and the list is the data we want
 * to populate into the lists.
 *
 * @param context        The current context. Used to inflate the layout file.
 * @param earthquakes A List of AndroidFlavor objects to display in a list
 */
public CustomAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, earthquakes);
        }
        private int getMagnitudeColor(double mag){
            int magnitudeColorResourceId;
            int magnitudeFloor = (int) Math.floor(mag);
            switch (magnitudeFloor){
                case 0:
                case 1:
                    magnitudeColorResourceId=R.color.magnitude1;
                    break;
                case 2:
                    magnitudeColorResourceId=R.color.magnitude2;
                    break;
                case 3:
                    magnitudeColorResourceId=R.color.magnitude3;
                    break;
                case 4:
                    magnitudeColorResourceId=R.color.magnitude4;
                    break;
                case 5:
                    magnitudeColorResourceId=R.color.magnitude5;
                    break;
                case 6:
                    magnitudeColorResourceId=R.color.magnitude6;
                    break;
                case 7:
                    magnitudeColorResourceId=R.color.magnitude7;
                    break;
                case 8:
                    magnitudeColorResourceId=R.color.magnitude8;
                    break;
                case 9:
                    magnitudeColorResourceId=R.color.magnitude9;
                    break;
                default:
                    magnitudeColorResourceId=R.color.magnitude10plus;
                    break;
            }
            return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
        }

/**
 * Provides a view for an AdapterView (ListView, GridView, etc.)
 *
 * @param position The position in the list of data that should be displayed in the
 *                 list item view.
 * @param convertView The recycled view to populate.
 * @param parent The parent ViewGroup that is used for inflation.
 * @return The View for the position in the AdapterView.
 */
@Override
public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
        listItemView = LayoutInflater.from(getContext()).inflate(
        R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Earthquake currentdata = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView MagTextView =  listItemView.findViewById(R.id.Magnitude);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
    // Set the proper background color on the magnitude circle.
    // Fetch the background from the TextView, which is a GradientDrawable.
    GradientDrawable magnitudeCircle = (GradientDrawable) MagTextView.getBackground();

    // Get the appropriate background color based on the current earthquake magnitude
    int magnitudeColor = getMagnitudeColor(currentdata.getmMagnitude ());

    // Set the color on the magnitude circle
    magnitudeCircle.setColor(magnitudeColor);
    DecimalFormat formatter = new DecimalFormat("0.0");
    String output = formatter.format(currentdata.getmMagnitude ());
        MagTextView.setText(output);

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView PlaceTextView =  listItemView.findViewById(R.id.Place);
        TextView OffsetTextView=listItemView.findViewById (R.id.location_offset);
        String Placedata=currentdata.getmPlace ();
        String PlaceString,OffsetString;
        int idx=Placedata.indexOf("of");
        if(idx==-1){
            PlaceString=Placedata;
            OffsetString="Near the";
        }else{
            PlaceString=Placedata.substring (idx+2,Placedata.length ());
            OffsetString=Placedata.substring (0,idx+2);
        }
        PlaceTextView.setText(PlaceString);
        OffsetTextView.setText (OffsetString);
    Date dateObject =new Date(currentdata.getmtimeInMillis ());
    // Find the TextView in the list_item.xml layout with the ID version_number
    TextView DateTextView =  listItemView.findViewById(R.id.Date);

    SimpleDateFormat dateFormat = new SimpleDateFormat ("LLL dd, yyyy");
    String FormattedString=dateFormat.format(dateObject);
    DateTextView.setText(FormattedString);

   TextView TimeTextview=listItemView.findViewById (R.id.time);

    SimpleDateFormat timeFormat = new SimpleDateFormat ("h:mm a");
    String Formattedtime=timeFormat.format(dateObject);
    TimeTextview.setText(Formattedtime);



    // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
        }

        }