package com.example.guest.mycongressman.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.guest.mycongressman.Congressman;
import com.example.guest.mycongressman.R;

import java.util.ArrayList;

/**
 * Created by Guest on 12/10/15.
 */
public class CongressmanAdapter extends ArrayAdapter<Congressman> {


    public CongressmanAdapter(Context context, ArrayList<Congressman> congressmans) {
        super(context, R.layout.activity_congressman, congressmans);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        Congressman congressman = getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.activity_congressman, null);
            holder = new ViewHolder();
            holder.firstNameLabel = (TextView) convertView.findViewById(R.id.firstNameLabel);
            holder.lastNameLabel = (TextView) convertView.findViewById(R.id.lastNameLabel);
            holder.houseSenatelabel = (TextView) convertView.findViewById(R.id.houseSenatelabel);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.houseSenatelabel.setText(congressman.getChamber());
        holder.lastNameLabel.setText(congressman.getLastName());
        holder.firstNameLabel.setText(congressman.getFirstName());

        return convertView;
    }

    private static class ViewHolder {
        TextView firstNameLabel;
        TextView lastNameLabel;
        TextView houseSenatelabel;
    }
}
