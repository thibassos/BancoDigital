package br.com.cdb.bancodigital.entity;

import java.time.LocalDate;

public class SeguroFraude extends Seguro {

    private double valorApoliceBase;

    public SeguroFraude(int numeroSeguro, int numeroCartaoSegurado, int numeroApoliceSeguro,
                       LocalDate dataContratacao, LocalDate dataVencimento) {
        super(numeroSeguro, numeroCartaoSegurado, numeroApoliceSeguro, dataContratacao, dataVencimento);
        this.valorApoliceBase = 5000.00;
    }

    public double getValorApoliceBase() {
        return valorApoliceBase;
    }

    public void setValorApoliceBase(double valorApoliceBase) {
        this.valorApoliceBase = valorApoliceBase;
    }

    
    public double calcularValor() {
        return valorApoliceBase;
    }

    
    public String gerarApolicePdf() {
       
        return "Apólice Seguro Fraude";
    }

}