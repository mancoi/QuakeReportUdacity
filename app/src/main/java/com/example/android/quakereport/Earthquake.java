package com.example.android.quakereport;

import static com.example.android.quakereport.R.id.mag;

/**
 * Created by mancoi on 30/06/2017.
 */

public class Earthquake {
    private double mMag;
    private String mPlace;
    private long mTime;
    private String mEarthquakeUrl;

    public Earthquake(double mag, String place, long timeInMiliSec, String eqUrl)
    {
        mMag = mag;
        mPlace = place;
        mTime = timeInMiliSec;
        mEarthquakeUrl = eqUrl;
    }

    public double getMag()
    {
        return mMag;
    }

    public String getPlace()
    {
        return mPlace;
    }

    public long getTimeInMiliSec()
    {
        return mTime;
    }

    public String getEarthquakeUrl() { return mEarthquakeUrl; }

}
