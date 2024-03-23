package br.com.cdb.bancodigital.model;
import br.com.cdb.bancodigital.dao.ClienteDAO;
import br.com.cdb.bancodigital.dao.ContaDAO;
import br.com.cdb.bancodigital.entity.Cliente;
import br.com.cdb.bancodigital.entity.Corrente;

public class ClienteService {
	ClienteDAO clienteDAO = new ClienteDAO();
	ContaDAO contas = new ContaDAO();
	GerenciarId gerId = new GerenciarId();
	
	
	public boolean addCliente(String nome, String cpf,String tipoCliente, ClienteDAO cli) {
		if (!validarNome(nome)) {
			return false;
		}
		if (!validarCpf(cpf)) {
			return false;
		}
		int id = gerId.getId();
		gerId.setId(id);
		Cliente cliente = new Cliente(nome, cpf, tipoCliente, id);
		cli.addCliente(cliente);
		Corrente a = new Corrente(cliente);
		contas.addConta(a);
		return true;
	}
	
	
	public boolean removeCliente(Cliente cli) {
		if (clienteDAO.getListaDeClientes().contains(cli)) {
			clienteDAO.removerCliente(cli);
		}
		return false;
	}
	

	private boolean validarCpf(String cpf) {
		cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11)
            return false;

        if (cpf.matches("(\\d)\\1{10}"))
            return false;

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * (10 - i);
        }
        int primeiroDigito = 11 - (soma % 11);
        if (primeiroDigito > 9)
            primeiroDigito = 0;


        if (primeiroDigito != cpf.charAt(9) - '0')
            return false;

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * (11 - i);
        }
        int segundoDigito = 11 - (soma % 11);
        if (segundoDigito > 9)
            segundoDigito = 0;

        if (segundoDigito != cpf.charAt(10) - '0')
            return false;
        
        for (Cliente cli : clienteDAO.getListaDeClientes()) {
        	if (cli.getCpf().equals(cpf))
        		return false;
        }
        return true;
    }
	

	private boolean validarNome(String nome) {
		if(nome.length() >= 2 && nome.length() <= 100) {
			return true;
		}
		else {
		return false;
		}
	}
}
