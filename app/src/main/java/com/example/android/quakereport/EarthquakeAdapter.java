package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mancoi on 30/06/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> Earthquake) {
        super(context, 0, Earthquake);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);

        //format the magnitude
        String formattedDecimal = formatMagnitude(currentEarthquake.getMag());

        // Find the TextView in the list_item.xml layout with the ID mag
        TextView magTextView = (TextView) listItemView.findViewById(R.id.mag);
        // Get the version name from the current Earthquake object and
        // set this text on the mag TextView
        magTextView.setText(formattedDecimal);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magTextView.getBackground();

        // Get the appropriate background color based on the current currentEarthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMag());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        //Store the location_offset and the primary_location in an String array
        //by split it to two parts
        //parts[0]: location_offset
        //parts[1]: primary_location
        String[] formattedPlace = getPlaceParts(currentEarthquake.getPlace());

        // Find the TextView in the list_item.xml layout with the ID location_offset
        TextView distanceTextView = (TextView) listItemView.findViewById(R.id.location_offset);
        // Display the time of the current currentEarthquake in that TextView
        distanceTextView.setText(formattedPlace[0]);

        // Find the TextView in the list_item.xml layout with the ID primary_location
        TextView placeTextView = (TextView) listItemView.findViewById(R.id.primary_location);
        // Display the time of the current currentEarthquake in that TextView
        placeTextView.setText(formattedPlace[1]);


        Date dateObject = new Date(currentEarthquake.getTimeInMiliSec());

        // Find the TextView with view ID date_tv
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date_tv);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current currentEarthquake in that TextView
        dateTextView.setText(formattedDate);

        // Find the TextView in the list_item.xml layout with the ID time
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time_tv);
        // Format the time string (i.e. "10:25 AM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current currentEarthquake in that TextView
        timeTextView.setText(formattedTime);

        return listItemView;
    }


    /**
     * return the color value to set to the background of magnitude TextView
     */
    private int getMagnitudeColor(double magnitude)
    {
        int mag = (int) Math.floor(magnitude);
        int magColor;
        switch (mag)
        {
            case 0:

            case 1:
                magColor = ContextCompat.getColor(getContext(),R.color.magnitude1);
                return magColor;
            case 2:
                magColor = ContextCompat.getColor(getContext(),R.color.magnitude2);
                return magColor;
            case 3:
                magColor = ContextCompat.getColor(getContext(),R.color.magnitude3);
                return magColor;
            case 4:
                magColor = ContextCompat.getColor(getContext(),R.color.magnitude4);
                return magColor;
            case 5:
                magColor = ContextCompat.getColor(getContext(),R.color.magnitude5);
                return magColor;
            case 6:
                magColor = ContextCompat.getColor(getContext(),R.color.magnitude6);
                return magColor;
            case 7:
                magColor = ContextCompat.getColor(getContext(),R.color.magnitude7);
                return magColor;
            case 8:
                magColor = ContextCompat.getColor(getContext(),R.color.magnitude8);
                return magColor;
            case 9:
                magColor = ContextCompat.getColor(getContext(),R.color.magnitude9);
                return magColor;
            default:
                magColor = ContextCompat.getColor(getContext(),R.color.magnitude10plus);
                return magColor;
        }
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private String[] getPlaceParts(String toDistance)
    {
        final String LOCATION_SEPARATOR = "of";
        String[] formattedString;
        if(toDistance.contains(LOCATION_SEPARATOR))
        {
            formattedString = toDistance.split(LOCATION_SEPARATOR);
            formattedString[0] = formattedString[0] + LOCATION_SEPARATOR;
            return formattedString;
        }
        else
        {
            formattedString = new String[] {getContext().getString(R.string.near_the), toDistance};
            return formattedString;
        }
    }

    private String formatDate(Date dateObject)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject)
    {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
}
