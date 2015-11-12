package br.usjt.projetoandriod.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import br.usjt.projetoandriod.R;
import br.usjt.projetoandriod.model.Veiculo;
import br.usjt.projetoandriod.network.VeiculoRequester;

public class MainActivity extends ActionBarActivity
{

    Spinner spinnerCidade;
    Button btnConsultar;
    String cidade;
    ArrayList<Veiculo> veiculos;
    //final String servidor = "10.0.3.2:8080";
    final String servidor = "10.0.3.2:8080/servico_REST01";
    VeiculoRequester requester;
    ProgressBar mProgress;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();

    }

    private void setupViews()
    {
        cidade = "";
        btnConsultar = (Button) findViewById(R.id.botao_enviar);
        spinnerCidade = (Spinner) findViewById(R.id.dropdown_cidades);
        spinnerCidade.setOnItemSelectedListener(new CidadeSelecionada());
        mProgress = (ProgressBar) findViewById(R.id.carregando);
        mProgress.setVisibility(View.INVISIBLE);

    }

    private class CidadeSelecionada implements AdapterView.OnItemSelectedListener
    {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
        {
            cidade = (String) parent.getItemAtPosition(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent)
        {

        }
    }


// constante static para identificar o parametro
public final static String VEICULOS = "br.usjt.VEICULOS";

    //será chamado quando o usuário clicar em enviar
    public void consultarVeiculos(View view)
    {
        final String pCidade = this.cidade.equals("Escolha a cidade") ? "" : cidade;


        requester = new VeiculoRequester();
        if (requester.isConnected(this))
        {
            intent = new Intent(this, ListaVeiculoActivity.class);

            mProgress.setVisibility(View.VISIBLE);
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        veiculos = requester.get("http://" + servidor + "/selecao.json", pCidade);
                        runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                intent.putExtra(VEICULOS, veiculos);
                                mProgress.setVisibility(View.INVISIBLE);
                                startActivity(intent);
                            }
                        });

                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            }).start();
        } else
        {
            Toast toast = Toast.makeText(this, "Rede indisponível!", Toast.LENGTH_LONG);
            toast.show();
        }
    }

}
