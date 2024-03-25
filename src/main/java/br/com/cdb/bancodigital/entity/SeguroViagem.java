package br.com.cdb.bancodigital.entity;

import java.time.LocalDate;


public class SeguroViagem extends Seguro {

    private static final Cliente COMUM = null;
	private static final Cliente SUPER = null;
	private static final Cliente PREMIUM = null;
	private double valorSeguroViagem;
    private Cliente tipoCliente;

    public SeguroViagem(int numeroSeguro, int numeroCartaoSegurado, int numeroApoliceSeguro,
                        LocalDate dataContratacao, LocalDate dataVencimento, Cliente tipoCliente) {
        super(numeroSeguro, numeroCartaoSegurado, numeroApoliceSeguro, dataContratacao, dataVencimento);
        this.tipoCliente = tipoCliente;
    }

    public double getValorSeguroViagem() {
        return valorSeguroViagem;
    }

    public void setValorSeguroViagem(double valorSeguroViagem) {
        this.valorSeguroViagem = valorSeguroViagem;
    }

    public Cliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(Cliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    @Override
    public double calcularValor() {
        switch (tipoCliente) {
            case COMUM:
                return 50.00;
            case SUPER:
                return 50.00;
            case PREMIUM:
                return 0.00;
            default:
                throw new IllegalArgumentException("Tipo de cliente inválido");
        }
    }

   
    public String gerarApolicePdf() {
        
        return "Apólice Seguro Viagem";
    }
   
   
   

      

}