package br.com.cdb.bancodigital.entity;

import java.util.Calendar;

public class TaxaRendimento extends Poupanca{

	Calendar cal = Calendar.getInstance();
	
	int mesAtual = cal.get(Calendar.MONTH);
	int mesAnterior = (Calendar.MONTH - 1);
	
	double taxa = 0.0;
	
	Cliente.get(TipoCliente());
	
	switch (TipoCliente) {
	
	case "comum":
		
		taxa = 0.005;  //porcentagem convertido para decimal
		break;	
	
	case "super":
       
		taxa = 0.007;
        break;
    
	case "premium":
        
		taxa = 0.009;
        break;
   
	default:
        System.out.println("Tipo de cliente inválido.");
        return;
	}
	
	double calcTaxa = saldo * taxa;
	
	if(mesAtual != mesAnterior) {
		saldo + calcTaxa;
		mesAnterior = mesAtual;
	}else {}
	
}
