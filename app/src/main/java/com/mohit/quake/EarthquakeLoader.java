package com.mohit.quake;

import android.content.AsyncTaskLoader;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.ArrayList;
import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {
    private String mUrl;
    public EarthquakeLoader(@NonNull Context context,String url) {
        super (context);
        mUrl=url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad ();
    }

    @Nullable
    @Override
    public List<Earthquake> loadInBackground() {
        if (mUrl== null) {
            return null;
        }
        // Create a fake list of earthquake locations.
        final ArrayList<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData (mUrl);

        return earthquakes;
    }
}
