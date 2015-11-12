package br.usjt.projetoandriod.util;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by asbonato on 9/7/15.
 */
public class ViewHolder {
    private ImageView fotoVeiculo;
    private TextView nomeVeiculo, detalhesVeiculo;

    public ViewHolder(ImageView fotoVeiculo, TextView nomeVeiculo, TextView detalhesVeiculo) {
        this.fotoVeiculo = fotoVeiculo;
        this.nomeVeiculo = nomeVeiculo;
        this.detalhesVeiculo = detalhesVeiculo;
    }

    public ImageView getFotoVeiculo() {
        return fotoVeiculo;
    }

    public void setFotoVeiculo(ImageView fotoVeiculo) {
        this.fotoVeiculo = fotoVeiculo;
    }

    public TextView getNomeVeiculo() {
        return nomeVeiculo;
    }

    public void setNomeVeiculo(TextView nomeVeiculo) {
        this.nomeVeiculo = nomeVeiculo;
    }

    public TextView getDetalhesVeiculo() {
        return detalhesVeiculo;
    }

    public void setDetalhesVeiculo(TextView detalhesVeiculo) {
        this.detalhesVeiculo = detalhesVeiculo;
    }
}
