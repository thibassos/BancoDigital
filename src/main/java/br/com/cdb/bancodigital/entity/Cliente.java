package br.com.cdb.bancodigital.entity;

import java.util.ArrayList;

public class Cliente {
	private final int id;
	private final String nome;
	private final String cpf;
	private String tipoCliente;
	
	ArrayList<Conta> suasContas = new ArrayList<Conta>();
	
	public Cliente(String nome, String cpf,String tipoCliente, int id) {
		this.nome = nome;
		this.cpf = cpf;
		this.tipoCliente = tipoCliente;
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getCpf() {
		return cpf;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void mudarTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public ArrayList<Conta> getContas() {
		return suasContas;
	}

	public void addConta(Conta conta) {
		suasContas.add(conta);
	}

	
	
}
