package br.unicamp.ft.e196208_g173381.aula3.jogo3;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

public class MyFirstWebServiceJogo3Status extends AsyncTask<String, Void, String> {


    private StatusJogador txt;
    private StringBuilder sb;
    private StringBuilder bd;


    public MyFirstWebServiceJogo3Status(StatusJogador txt) {
        this.txt = txt;
    }

    @Override
    protected void onPreExecute() {
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected String doInBackground(String... args) {
        return getValue(args);
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @NotNull
    private String getValue(String[] args) {
        HttpURLConnection httpURLConnection;
        try {
            /*
               Endereço que será acessado.
             */
            String HOST = "https://projetoandroid-94ff7.firebaseio.com/.json";

        /*
          Abrindo uma conexão com o servidor
        */
            URL url = new URL("https://projetoandroid-94ff7.firebaseio.com/.json");

            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");

            if (args.length == 3) {
                httpURLConnection.addRequestProperty("Content-Type", "application/json");
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8");
                outputStreamWriter.write(args[2]);
                outputStreamWriter.flush();
                outputStreamWriter.close();
            }
            /*
          Lendo a resposta do servidor
        */
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(httpURLConnection.getInputStream()));


            sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            int value;
            try {
                String name = "";
                String total = "";
                JSONObject jsn = new JSONObject(sb.toString());
                bd = new StringBuilder();
                Iterator<String> obj = jsn.keys();
                for (int x = 0; obj.hasNext(); x++) {
                    name = obj.next();
                    total = name +
                            "\n" +
                            " Acertos : " + (jsn.getJSONObject(name).getDouble("Acertos") / ((jsn.getJSONObject(name).getDouble("Acertos") + jsn.getJSONObject(name).getDouble("Erros")))) * 100+"%" +
                            " Erros : "   + (jsn.getJSONObject(name).getDouble("Erros") / (jsn.getJSONObject(name).getDouble("Acertos") + jsn.getJSONObject(name).getDouble("Erros"))) * 100 +"%" +
                            "\n<---------------->\n";
                    bd.append(total);
                }
                return bd.toString();
            } catch (Exception e) {

            }
            return sb.toString();
        } catch (IOException e) {
            Log.v("Erro", e.getMessage());
            return "Exception\n" + e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String args) {
        // Via Cep

        txt.put(bd.toString());

    }
}
