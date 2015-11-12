package br.usjt.projetoandriod.controller;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import br.usjt.projetoandriod.R;
import br.usjt.projetoandriod.model.Veiculo;
import br.usjt.projetoandriod.network.VeiculoRequester;

public class DetalheVeiculoActivity extends ActionBarActivity {
    TextView veiculoModelo;
    ImageView veiculoImageView;
    TextView veiculoTarKmLivre;
    TextView veiculoTarKmControlado;
    TextView veiculoMarca;
    TextView veiculoPlaca;
    TextView veiculoClasse;
    TextView veiculoCidade;
    VeiculoRequester requester;
    ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_veiculo);

        Intent intent = getIntent();
        final Veiculo veiculo = (Veiculo)intent.getSerializableExtra(ListaVeiculoActivity.VEICULO);
        setupViews(veiculo);

        requester = new VeiculoRequester();
        if(requester.isConnected(this)) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        mProgress.setVisibility(View.VISIBLE);
                        final Bitmap img = requester.getImage(veiculo.getImagem());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                veiculoImageView.setImageBitmap(img);
                                mProgress.setVisibility(View.INVISIBLE);
                            }
                        });

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } else {
            Resources res = getResources();
            Drawable drawable = res.getDrawable(R.drawable.carro);
            veiculoImageView.setImageDrawable(drawable);
            Toast toast = Toast.makeText(this, "Rede indisponível!", Toast.LENGTH_LONG);
            toast.show();
        }

    }

    private void setupViews(Veiculo veiculo) {
        veiculoMarca = (TextView) findViewById(R.id.txt_veiculo_marca);
        veiculoMarca.setText(veiculo.getMarca());
        veiculoModelo = (TextView) findViewById(R.id.txt_veiculo_modelo);
        veiculoModelo.setText(veiculo.getModelo());
        veiculoImageView = (ImageView) findViewById(R.id.veiculo_image_view);
        veiculoTarKmLivre = (TextView) findViewById(R.id.txt_veiculo_tar_km_livre);
        veiculoTarKmControlado = (TextView) findViewById(R.id.txt_veiculo_tar_km_controlado);
        Locale locale = new Locale("pt", "BR");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        veiculoTarKmLivre.setText(""+formatter.format(veiculo.getTarKmLivre()));
        veiculoTarKmControlado.setText(""+formatter.format(veiculo.getTarKmControlado()));
        veiculoPlaca = (TextView) findViewById(R.id.txt_veiculo_placa);
        veiculoPlaca.setText(veiculo.getPlaca());
        veiculoCidade = (TextView) findViewById(R.id.txt_veiculo_cidade);
        veiculoCidade.setText(veiculo.getCidade());
        veiculoClasse = (TextView) findViewById(R.id.txt_veiculo_classe);
        veiculoClasse.setText(veiculo.getClasse());
        mProgress = (ProgressBar) findViewById(R.id.carregando_veiculo);
        mProgress.setVisibility(View.INVISIBLE);
    }

}
