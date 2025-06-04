package com.example.project1.controller;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class GeocodingService {

    public static double[] getLatLonFromLocation(String location) {
        try {
            String encodedLocation = java.net.URLEncoder.encode(location, "UTF-8");
            String url = "https://nominatim.openstreetmap.org/search?format=json&q=" + encodedLocation;

            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestProperty("User-Agent", "DisasterAidApp/1.0");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response = in.readLine();
            in.close();

            JSONArray arr = new JSONArray(response);
            if (arr.length() > 0) {
                JSONObject obj = arr.getJSONObject(0);
                double lat = obj.getDouble("lat");
                double lon = obj.getDouble("lon");
                return new double[]{lat, lon};
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new double[]{0, 0}; // Default or error value
    }
}
