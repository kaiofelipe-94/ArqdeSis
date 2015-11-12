package br.usjt.projetoandriod.model;

import java.io.Serializable;

/**
 * Created by kaio on 11/10/2015.
 */
public class Veiculo implements Comparable<Veiculo>, Serializable
{

    private String marca;
    private String modelo;
    private String placa;
    private String classe;
    private String imagem;
    private String cidade;
    private String km;
    private double tarKmLivre;
    private double tarKmControlado;
    private boolean disponivel;
    private int id;

    public Veiculo(String marca, String modelo, String placa, String classe, String cidade, String km, double tarKmLivre, double tarKmControlado, boolean disponivel, String imagem)
    {
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.classe = classe;
        this.cidade = cidade;
        this.km = km;
        this.tarKmLivre = tarKmLivre;
        this.tarKmControlado = tarKmControlado;
        this.disponivel = disponivel;
        this.imagem = imagem;
    }

    public String getMarca()
    {
        return marca;
    }

    public String getModelo()
    {
        return modelo;
    }

    public String getPlaca()
    {
        return placa;
    }

    public String getClasse()
    {
        return classe;
    }

    public String getCidade()
    {
        return cidade;
    }

    public String getKm()
    {
        return km;
    }

    public double getTarKmLivre()
    {
        return tarKmLivre;
    }

    public double getTarKmControlado()
    {
        return tarKmControlado;
    }

    public boolean isDisponivel()
    {
        return disponivel;
    }

    public int getId()
    {
        return id;
    }



    public void setMarca(String marca)
    {
        this.marca = marca;
    }

    public void setModelo(String modelo)
    {
        this.modelo = modelo;
    }

    public void setPlaca(String placa)
    {
        this.placa = placa;
    }

    public void setClasse(String classe)
    {
        this.classe = classe;
    }

    public void setCidade(String cidade)
    {
        this.cidade = cidade;
    }

    public void setKm(String km)
    {
        this.km = km;
    }

    public void setTarKmLivre(double tarKmLivre)
    {
        this.tarKmLivre = tarKmLivre;
    }

    public void setTarKmControlado(double tarKmControlado)
    {
        this.tarKmControlado = tarKmControlado;
    }

    public void setDisponivel(boolean disponivel)
    {
        this.disponivel = disponivel;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getImagem()
    {
        return imagem;
    }

    public void setImagem(String imagem)
    {
        this.imagem = imagem;
    }

    @Override
    public String toString()
    {
        return "Veiculo{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", placa='" + placa + '\'' +
                ", classe=" + classe +
                ", cidade='" + cidade + '\'' +
                ", km='" + km + '\'' +
                ", tarKmLivre='" + tarKmLivre + '\'' +
                ", tarKmControlado='" + tarKmControlado + '\'' +
                ", disponivel=" + disponivel +
                ", id=" + id +
                '}';
    }

    @Override
    public int compareTo(Veiculo veiculo)
    {
        if (marca.equals(veiculo.getMarca())
                && modelo.equals(veiculo.getModelo())
                && placa.equals(veiculo.getPlaca()))
        {
            return 0;
        }
        return this.getModelo().compareTo(veiculo.getMarca());
    }

}

