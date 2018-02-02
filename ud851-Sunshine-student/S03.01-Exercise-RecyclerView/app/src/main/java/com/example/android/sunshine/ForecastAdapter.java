package com.example.android.sunshine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by Ramon on 1/24/2018.
 */
// COMPLETED (24) Override onCreateViewHolder
// COMPLETED (25) Within onCreateViewHolder, inflate the list item xml into a view
// COMPLETED (26) Within onCreateViewHolder, return a new ForecastAdapterViewHolder with the above view passed in as a parameter

// COMPLETED (27) Override onBindViewHolder
// COMPLETED (28) Set the text of the TextView to the weather for this list item's position

// COMPLETED (29) Override getItemCount
// COMPLETED (30) Return 0 if mWeatherData is null, or the size of mWeatherData if it is not null

// COMPLETED (31) Create a setWeatherData method that saves the weatherData to mWeatherData
// COMPLETED (32) After you save mWeatherData, call notifyDataSetChanged
// Within ForecastAdapter.java /////////////////////////////////////////////////////////////////
public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder> {
    private String[] mWeatherData;

    public ForecastAdapter() {
        super();
    }

    public void setWeatherData(String[] weatherData){
        mWeatherData = weatherData;
        notifyDataSetChanged();
    }

    @Override
    public ForecastAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.forecast_list_item, parent, false);
        return new ForecastAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ForecastAdapterViewHolder holder, int position) {
        holder.mWeatherTextView.setText(mWeatherData[position]);
    }

    @Override
    public int getItemCount() {
        if (mWeatherData == null) {
            return 0;
        } else {
            return mWeatherData.length;
        }

    }

    class ForecastAdapterViewHolder extends RecyclerView.ViewHolder {
        public final TextView mWeatherTextView;

        public ForecastAdapterViewHolder(View view) {
            super(view);
            mWeatherTextView = (TextView) itemView.findViewById(R.id.tv_weather_data);
        }


    }
}
