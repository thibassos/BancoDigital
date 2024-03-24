package br.com.cdb.bancodigital.entity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import br.com.cdb.bancodigital.utils.Constantes;

public class CartaoCredito extends Cartao {

	private int numeroCartao;
	private boolean ativo;
	private String tipoCliente;
	private double limitePreAprovado;
	private double saldoDisponivel;
	private double totalFatura;
	private String dataVencimento;
	private double taxaFechamentoFatura = 0.05;
	private HashMap<String, Double> fatura = new HashMap<>();
	

	public CartaoCredito(int numeroCartao, String tipoCliente); {
		this.numeroCartao = numeroCartao;
		this.ativo = false;
		this.tipoCliente = tipoCliente;
		this.limitePreAprovado = getLimitePreAprovado(tipoCliente);
		this.saldoDisponivel = limitePreAprovado;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 10);
		this.dataVencimento = sdf.format(calendar.getTime());

	}

	private double getLimitePreAprovado(String tipoCliente) {
		switch (tipoCliente) {
		case Constantes.COMUM:
			this.limitePreAprovado = 1000.00;
			break;
		case Constantes.SUPER:
			this.limitePreAprovado = 5000.00;
			break;
		case Constantes.PREMIUM:
			this.limitePreAprovado = 10000.00;
			break;
		}
		return limitePreAprovado;
	}

	public boolean ativarCartao() {
		if (!ativo) {
			this.ativo = true;
			return true;
		} else {
			System.out.println("Seu Cartão já está ativo.");
			return false;
		}
	}

	public double liberarLimite() {
		if (!ativo) {
			System.out.println("Cartão precisa estar ativo para liberar o limite.");
			return 0;
		}

		double limiteLiberado = getLimitePreAprovado(tipoCliente);
		saldoDisponivel += limiteLiberado;
		return limiteLiberado;
	}

	public boolean desativarCartao() {
		if (ativo) {
			this.ativo = false;
			return true;
		} else {
			System.out.println("Cartão já está Inavativo.");
			return false;
		}
	}

	public void realizarCompra(double valorCompra) {

	    if (!ativo) {
	        System.out.println("Cartão precisa estar ativo para realizar compras.");
	        return;
	    }

	    if (valorCompra > saldoDisponivel) {
	        System.out.println("Saldo insuficiente para realizar compra. Saldo disponível: R$" + saldoDisponivel);
	        return;
	    }

	    saldoDisponivel -= valorCompra;
	    fatura.put(dataAtual(), valorCompra);
	    totalFatura += valorCompra;

	    bloquearCartaoAoAtingirLimite();

	    if (saldoDisponivel <= 0) {
	        enviarMensagemLimiteAtingido();
	    }
	}

	private void enviarMensagemLimiteAtingido() {
	    System.out.println("**Você atingiu 100% do seu limite!** Após o pagamento, seu cartão será liberado.");
	}


	

	public String dataAtual() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(new Date());
	}

	public void mostrarSaldoDisponivel() {
		System.out.println("Saldo disponível: R$" + saldoDisponivel);
	}
	
	public void fecharFatura() {
        if (totalFatura >= (limitePreAprovado * 0.8)) {
            totalFatura += totalFatura * taxaFechamentoFatura;
        }

}

	public class PagarFatura extends Corrente {

	    private double saldoDisponivel;

		public PagarFatura(Cliente cli) {
			super(cli);
			
		}

		
	    public double getTotalFatura() {
	        return totalFatura;
	    }

	    public void setSaldoDisponivel(double saldo) {
	        this.saldoDisponivel = saldo;
	    }

	    public boolean pagarFatura(ContaCorrente contaCorrente) {
	        PagamentoFatura pagamento = new PagamentoFatura(this, contaCorrente);
	        return pagamento.pagarFatura();
	    }
	}

	
	
}
