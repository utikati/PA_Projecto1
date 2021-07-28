package Utilizadores;

import AcessoBD.DadosLogs;
import AcessoBD.DadosUtilizadores;
import DadosEstaticos.DadosStatic;
import java.util.*;

/**
 * @author Jorge Martins
 *
 */
public class MetodoStaticLogs {
	
	/**
	 * @param movimento
	 * Metodo que adiciona o movimento realizado pelo utilizador mais seu login 
	 * e a data do movimento
	 */
	public static void movimentoUtilizador(String movimento) {
		DadosLogs.adicionaLog(new Logs(Integer.parseInt(DadosUtilizadores.verificaLogin(DadosStatic.Login)), movimento, new Date()));
	}
	
	/**
	 * @param aCondicao
	 * Metodo para listar os logs / movimentos através de uma condicao
	 * as condições são impostas na query que condiciona a listagem dos dados no resulset
	 */
	public static void listarLogs(String aCondicao) {
		ArrayList <Logs> logs = DadosLogs.listarTodoslogs(aCondicao);
		
		if(logs != null){
			Iterator <Logs> tabela = logs.iterator();
			String envio = "";
			Logs auxiliar;
			int contador = 0;
		       while(tabela.hasNext()) {
			       auxiliar = tabela.next();
			       envio += "Login: " + DadosUtilizadores.getLogin(auxiliar.getIdUtilizador()) + " Acção: " + auxiliar.getAcaoLog() + " Data: " + auxiliar.getData() + "\n";
			       contador++;		
			    
			       if(contador == 10) {
			    	   MetodoStaticUtilizadores.listadorMaster(" Logs \n" + envio + "\n", contador); 
			    	   contador = 0;
			    	   envio = "";
			       }
		       }
		       if(contador > 0) {
		    	   MetodoStaticUtilizadores.listadorMaster(" Logs \n" + envio + "\n", contador);
		       }else {
		    	   MetodoStaticUtilizadores.listadorMaster("Logs \nSem dados\n", 0);
		       }
	     
		}
	 }
	
	/**
	 * @param opcao
	 * @param pesquisa
	 * @return String com parte que vai integrar a query a base de dados para condicionar
	 * a listagem
	 */
	public static String condicoesListaLog(int opcao, String pesquisa) {
		String envio = "";
		switch(opcao) {
			case 1: envio = "ORDER BY DATA_LOG ASC "; break;
			case 2: envio = "ORDER BY DATA_LOG DESC "; break;
			case 3: envio = "WHERE ID_UTILIZADOR = "+Integer.parseInt(DadosUtilizadores.verificaLogin(pesquisa))+" "; break;
		}
		return envio;
	}
	
}
