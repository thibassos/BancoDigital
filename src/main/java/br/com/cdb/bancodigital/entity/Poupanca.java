package br.com.cdb.bancodigital.entity;

import br.com.cdb.bancodigital.utils.Constantes;

public class Poupanca extends Conta {

	public Poupanca(Cliente cli) {
		super(cli, Constantes.POUPANCA);
		this.numeroConta = (cli.getId() + 02);
	}

	public double transferir(double transferencia) {
		this.saldo -= transferencia;
		return saldo;
	}

}
