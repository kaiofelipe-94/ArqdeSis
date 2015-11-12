package br.usjt.projetoandriod.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import br.usjt.projetoandriod.model.Veiculo;

/**
 * Created by asbonato on 2/6/15.
 * Vai fazer a requisição na API REST
 */
public class VeiculoRequester
{

    OkHttpClient client = new OkHttpClient();

    public ArrayList<Veiculo> get(String url, String pCidade) throws IOException, JSONException
    {

        ArrayList<Veiculo> lista = new ArrayList<>();

        //acentuacao nao funciona se mandar via get, mesmo usando URLEncode.encode(String,UTF-8)
        RequestBody formBody = new FormEncodingBuilder()
                .add("cidade", pCidade)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Response response = client.newCall(request).execute();

        String jsonStr = response.body().string();

        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));

        JSONArray root = new JSONArray(jsonStr);
        JSONObject item = null;
        for (int i = 0; i < root.length(); i++ ) {
            item = (JSONObject)root.get(i);

            String marca = item.getString("marca");
            String modelo = item.getString("modelo");
            String imagem = item.getString("imagem");
            String placa = item.getString("placa");
            String classe = item.getString("classe");
            String cidade = item.getString("cidade");
            String km = item.getString("km");
            double tarKmLivre = item.getDouble("tarKmLivre");
            double tarKmControlado = item.getDouble("tarKmControlado");

            lista.add(new Veiculo(marca, modelo, placa, classe, cidade, km, tarKmLivre, tarKmControlado, true, imagem));
        }
        return lista;
    }
    public Bitmap getImage(String url) throws IOException {

        Bitmap img = null;

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        InputStream is = response.body().byteStream();

        img = BitmapFactory.decodeStream(is);

        is.close();

        return img;
    }

    public boolean isConnected(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isConnected();
    }


}
