package me.bartsimons.contactcardapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import static android.support.constraint.Constraints.TAG;

public class ContactCardItemDAOTask extends AsyncTask<String, Void, ContactCardItem[]> {
    private Context context;
    private ContactCardItem[] items;

    public ContactCardItemDAOTask(Context context) {
        this.context = context;
    }

    @Override
    protected ContactCardItem[] doInBackground(String... strings) {
        String urlString = strings[0];
        String response = "";

        InputStream inputStream = null;
        int responseCode = -1;


        try {
            URL url = new URL(urlString);
            URLConnection urlConnection = url.openConnection();

            if (!(urlConnection instanceof HttpURLConnection)) {
                return null;
            }

            HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;
            httpConnection.setInstanceFollowRedirects(true);
            httpConnection.setRequestMethod("GET");
            httpConnection.connect();

            responseCode = httpConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpConnection.getInputStream();
                response = getStringFromInputStream(inputStream);
            }

            JSONObject jsonObject;

            try {
                jsonObject = new JSONObject(response);
                JSONArray users = jsonObject.getJSONArray("results");

                items = new ContactCardItem[users.length()];

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);

                for (int i = 0; i < users.length(); i++) {
                    JSONObject array = users.getJSONObject(i);

                    JSONObject name = array.getJSONObject("name");
                    JSONObject picture = array.getJSONObject("picture");
                    JSONObject dob = array.getJSONObject("dob");

                    InputStream in = new java.net.URL(picture.getString("large")).openStream();
                    Bitmap icon = BitmapFactory.decodeStream(in);

                    String fullName = name.getString("first")+" "+name.getString("last");
                    String date = dob.getString("date");

                    String email = array.getString("email");
                    String phone = array.getString("phone");

                    try {
                        Date datetime = sdf.parse(date);
                        date = datetime.toString();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    items[i] = new ContactCardItem(icon, fullName, date, email, phone);
                }

                return items;
            } catch (JSONException e) {
                Log.e(TAG, e.getLocalizedMessage());
            }
        } catch (MalformedURLException e) {
            Log.e("TAG", e.getLocalizedMessage());
            return null;
        } catch (IOException e) {
            Log.e("TAG", e.getLocalizedMessage());
            return null;
        }

        return null;
    }

    private static String getStringFromInputStream(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }

    @Override
    protected void onPostExecute(ContactCardItem[] cci) {
        MainActivity activity = (MainActivity) this.context;
        RecyclerView listView = (RecyclerView) activity.findViewById(R.id.contactCardListView);
        listView.setAdapter(new ContactCardAdapter(this.context, cci, new ContactCardClickListener(this.context, this.items)));
        listView.setLayoutManager(new LinearLayoutManager(this.context));
    }
}
