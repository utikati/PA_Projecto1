package AcessoBD;

import java.sql.SQLException;
import java.util.ArrayList;


import Utilizadores.*;

/**
 * @author Jorge Martins
 *
 */
public class DadosLogs {
	
/**
 * @param log
 * @return boolean
 * Adicionar movimentos do utilizador na base de dados
 */
public static boolean adicionaLog(Logs log) {
		
		DadosConnect.conecta();
		

        try {
        	  
			  StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" INSERT INTO logs  (ID_UTILIZADOR, ACAO_LOG, DATA_LOG) "
			  				+ "	VALUES( ?, ?, ?); "); 

			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps.clearParameters();   
			  DadosConnect.ps.setInt(1, log.getIdUtilizador());
			  DadosConnect.ps.setString(2, log.getAcaoLog());
			  java.sql.Date sqlDate = new java.sql.Date(log.getData().getTime());
			  DadosConnect.ps.setDate(3, sqlDate);
			  
			  DadosConnect.ps.executeUpdate();
			  
			  
			  
        } catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
           	e.printStackTrace();
           	return false;
		}
        
        DadosConnect.desliga();
		return true;
	}


/**
 * @param aCondicao
 * @return ArrayList de Objecto Logs
 * Metodo de ligação à Base De Dados que através de uma condição (ex ORDER BY DATA) envia todos os logs
 * para serem listados em ArrayList
 */
public static ArrayList <Logs> listarTodoslogs(String aCondicao) {
    ArrayList <Logs> lista = new ArrayList <Logs> ();
    DadosConnect.conecta();
    try {
    	  
		  StringBuffer sqlQuery = new StringBuffer();
		  
		  // prepared statement for select
		  sqlQuery.append(" SELECT * FROM logs ");
		  sqlQuery.append(" "+aCondicao+" ;");

		  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
		  DadosConnect.ps.clearParameters();   
		  
		  DadosConnect.rs = DadosConnect.ps.executeQuery();
		  
		  if (DadosConnect.rs == null) {
			     System.out.println("!! No Record on table !!");
		  } else {
			  lista = new ArrayList <Logs> (); //garantir que a lista fica vazia..
			  while (DadosConnect.rs.next()) {
				  lista.add(new Logs(DadosConnect.rs.getInt("ID_UTILIZADOR"), DadosConnect.rs.getString("ACAO_LOG"),DadosConnect.rs.getDate("DATA_LOG")));    
			  }
		  }
			  
		  
		  DadosConnect.ps.close();
		  
    } catch (SQLException e) {
    	System.out.println("!! SQL Exception !!\n"+e);
    	DadosConnect.desliga();
       	e.printStackTrace();
       	return lista;
	} 
    DadosConnect.desliga();
    
    return lista;
}



}
