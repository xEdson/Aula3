package br.unicamp.ft.e196208_g173381.aula3.internet;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyFirstWebService extends AsyncTask<String, Void, String> {

    TextView textView;


    public MyFirstWebService(TextView textView) {
        this.textView = textView;
    }


    @Override
    protected void onPreExecute() {
;
    }
    @Override
    protected String doInBackground(String... args) {
        if (args.length == 0) {
            return "No Parameter";
        }

        HttpURLConnection httpURLConnection;
        try {
            URL url = new URL(args[0]);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod(args[1]);

            if (args.length == 3) {
                httpURLConnection.addRequestProperty("Content-Type", "application/json");

                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8");
                if (args[2] != null && !args[2].isEmpty())
                    outputStreamWriter.write(args[2]);
                outputStreamWriter.flush();
                outputStreamWriter.close();
            }

            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(httpURLConnection.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            Log.v("Erro", e.getMessage());
            return "Exception\n" + e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String args) {
        textView.setText(args);
    }
}
