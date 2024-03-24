package br.com.cdb.bancodigital.entity;

import java.time.LocalDate;
import java.util.Calendar;

public class TaxaManutencao extends Corrente{

	Calendar cal = Calendar.getInstance();
	
	LocalDate d01 = LocalDate.now();
	LocalDate diaAnterior = d01.minusDays(1);
	
	double taxa = 0.0;
	
	Cliente.get(TipoCliente());
	
	switch (TipoCliente) {
	
	case "comum":
		
		taxa = 0.39; //divisão do valor para conversão de dias
		break;	
	
	case "super":
       
		taxa = 0.26;
        break;
    
	case "premium":
        
		taxa = 0.0;
        break;
   
	default:
        System.out.println("Tipo de cliente inválido.");
        return;
	}
	
	if(d01 != diaAnterior) {
		saldo -= taxa;
		diaAnterior = d01; //redefinir a data atual
	}else {}
	
}
