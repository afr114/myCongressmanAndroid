package com.example.guest.mycongressman;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Guest on 12/8/15.
 */
public class Congressman {
    public String mFirstName;
    public String mLastName;
    public String mChamber;

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getChamber() {
        return mChamber;
    }

    public void setChamber(String chamber) {
        mChamber = chamber;
    }

    public static Congressman fromJson(JSONObject jsonObject) {
        Congressman congressman = new Congressman();

        try {
            congressman.mFirstName = jsonObject.getString("first_name");
            congressman.mLastName = jsonObject.getString("last_name");
            congressman.mChamber = jsonObject.getString("chamber");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return congressman;
    }

    public static ArrayList<Congressman> fromJson(JSONArray jsonArray){
        ArrayList<Congressman> congressmans = new ArrayList<Congressman>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject congressmanJson = null;
            try {
                congressmanJson = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }

            Congressman congressman = Congressman.fromJson(congressmanJson);
            if (congressman != null) {
                congressmans.add(congressman);
            }
        }

        return congressmans;
    }
}
