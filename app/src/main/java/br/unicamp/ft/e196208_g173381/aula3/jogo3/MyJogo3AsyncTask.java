package br.unicamp.ft.e196208_g173381.aula3.jogo3;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MyJogo3AsyncTask extends AsyncTask<String, Void, ResponseJogo3> {

    private ResponseJogo3 responseJogo3 = new ResponseJogo3();
    private Jogo3Fragment jogo3Fragment;

    public MyJogo3AsyncTask(Jogo3Fragment jogo3Fragment) {
        this.jogo3Fragment = jogo3Fragment;

    }

    @Override
    protected ResponseJogo3 doInBackground(String... strings) {
        HttpURLConnection httpURLConnection;
        try {
            /*
               Endereço que será acessado.
             */
            String HOST = "https://sa4a4dtiv4.execute-api.eu-west-1.amazonaws.com/default/PythonHTTP1?kind=alunos&num_outros=4";

        /*
          Abrindo uma conexão com o servidor
        */

            URL url = new URL(HOST);

            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
        /*
          Lendo a resposta do servidor
        */
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(httpURLConnection.getInputStream()));


            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            JSONObject jsonObject = new JSONObject(sb.append(line).toString());
            responseJogo3.setFrase(jsonObject.getString("frase"));
            responseJogo3.setNome(jsonObject.getString("nome"));
            ArrayList<String> list = new ArrayList<>();
            list.add(jsonObject.getJSONArray("outros").getString(0));
            list.add(jsonObject.getJSONArray("outros").getString(1));
            list.add(jsonObject.getJSONArray("outros").getString(2));
            list.add(jsonObject.getJSONArray("outros").getString(3));
            list.add(jsonObject.getString("nome"));
            responseJogo3.setOutros(list);
            return responseJogo3;
        } catch (IOException e) {
            Log.v("Erro", e.getMessage());
            return responseJogo3 = null;
        } catch (JSONException e) {
            e.printStackTrace();
            return responseJogo3 = null;
        }
    }

    @Override
    protected void onPostExecute(ResponseJogo3 responseJogo3) {
        jogo3Fragment.preencher(responseJogo3);
    }
}
