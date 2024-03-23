package br.com.cdb.bancodigital.entity;
import br.com.cdb.bancodigital.utils.*;

public class Corrente extends Conta {
	
	public Corrente(Cliente cli) {
		super(cli, Constantes.CORRENTE);
		this.numeroConta = (cli.getId() + 01);
	}
	
	public void depositar(double deposito) {
		this.saldo += deposito;	
	}

	public void transferir(double transferencia, Conta destino) {
		this.saldo -= transferencia;
		destino.depositar(transferencia);
	}

}
