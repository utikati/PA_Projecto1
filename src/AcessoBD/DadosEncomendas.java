package AcessoBD;

import java.sql.SQLException;
import java.sql.Types;

import Produtos.*;
import java.util.*;

import DadosEstaticos.DadosStatic;

/**
 * @author Jorge Martins
 * Classe de ligação à Bd para uso das encomendas
 */
public class DadosEncomendas {
	
	/**
	 * @return inteiro com o Id maximo da Base De Dados
	 */
	public static int maxIdEncomenda() {
		DadosConnect.conecta3();
        int envio = 0;

        try {
        	  
              StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" SELECT MAX(ID_ENCOMENDA) FROM encomenda "); 

			  DadosConnect.ps3 = DadosConnect.conn3.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps3.clearParameters();
			  
			  DadosConnect.rs3 = DadosConnect.ps3.executeQuery();
			  
			  if (DadosConnect.rs3 == null) {
				     System.out.println("!! No Record on table !!");
			  } else
				  while (DadosConnect.rs3.next()) {
					  envio = DadosConnect.rs3.getInt("MAX(ID_ENCOMENDA)");
				  }
			  
        } catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
           	e.printStackTrace();
		} 
        DadosConnect.desliga3();
        return envio;
	}
	
	/**
	 * @param identificador
	 * @return inteiro com o Id da encomenda
	 */
	public static int idEncomenda(String identificador) {
		DadosConnect.conecta3();
        int envio = 0;

        try {
        	  
              StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" SELECT ID_ENCOMENDA FROM encomenda "); 
			  sqlQuery.append(" WHERE IDENTIFICADOR_ENCOMENDA = ? ");

			  DadosConnect.ps3 = DadosConnect.conn3.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps3.clearParameters();
			  DadosConnect.ps3.setString(1, identificador);   
			  
			  DadosConnect.rs3 = DadosConnect.ps3.executeQuery();
			  
			  if (DadosConnect.rs3 == null) {
				     System.out.println("!! No Record on table !!");
			  } else
				  while (DadosConnect.rs3.next()) {
					  envio = DadosConnect.rs3.getInt("ID_ENCOMENDA");
				  }
			  
        } catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
           	e.printStackTrace();
		} 
        DadosConnect.desliga3();
        return envio;
	}
	
	/**
	 * @param identificador
	 * @return Objecto encomenda ao qual contem o Identificador que recebeu
	 */
	public static Encomenda objEncomenda(String identificador) {
		DadosConnect.conecta3();
        Encomenda envio = new Encomenda();

        try {
        	  
              StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" SELECT * FROM encomenda "); 
			  sqlQuery.append(" WHERE IDENTIFICADOR_ENCOMENDA = ? ");

			  DadosConnect.ps3 = DadosConnect.conn3.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps3.clearParameters();
			  DadosConnect.ps3.setString(1, identificador);   
			  
			  DadosConnect.rs3 = DadosConnect.ps3.executeQuery();
			  
			  if (DadosConnect.rs3 == null) {
				     System.out.println("!! No Record on table !!");
			  } else
				  while (DadosConnect.rs3.next()) {
					  envio = new Encomenda(DadosConnect.rs3.getString("IDENTIFICADOR_ENCOMENDA"), DadosConnect.rs3.getFloat("CUSTO_ENCOMENDA"), DadosConnect.rs3.getDate("DATACRIACAO_ENCOMENDA"), DadosConnect.rs3.getString("ESTADO_ENCOMENDA"), DadosConnect.rs3.getInt("CLI_ID_UTILIZADOR"));
					  envio.setDataEntrega(DadosConnect.rs3.getDate("DATAENTREGA_ENCOMENDA"));
					  envio.setDataAceitacao(DadosConnect.rs3.getDate("DATAACEITACAO_ENCOMENDA"));
					  envio.setIdUtilizador(DadosConnect.rs3.getInt("ID_UTILIZADOR"));
					  envio.setIdArmazenista(DadosConnect.rs3.getInt("FUN_ID_UTILIZADOR"));
					  envio.setIdEstafeta(DadosConnect.rs3.getInt("FUN_ID_UTILIZADOR2"));
					  envio.setIdEncomenda(DadosConnect.rs3.getInt("ID_ENCOMENDA"));
				  }
			  
        } catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
           	e.printStackTrace();
		} 
        DadosConnect.desliga3();
        return envio;
	}
	
	/**
	 * @param id
	 * @return Retorna o objecto encomenda atraves do id que a base de dados contem dessa encomenda
	 * Usado no caso dos Pedidos que só contem o id da encomenda
	 */
	public static Encomenda objEncomendaID(int id) {
		DadosConnect.conecta3();
        Encomenda envio = new Encomenda();

        try {
        	  
              StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" SELECT * FROM encomenda "); 
			  sqlQuery.append(" WHERE ID_ENCOMENDA = ? ");

			  DadosConnect.ps3 = DadosConnect.conn3.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps3.clearParameters();
			  DadosConnect.ps3.setInt(1, id);   
			  
			  DadosConnect.rs3 = DadosConnect.ps3.executeQuery();
			  
			  if (DadosConnect.rs3 == null) {
				     System.out.println("!! No Record on table !!");
			  } else
				  while (DadosConnect.rs3.next()) {
					  envio = new Encomenda(DadosConnect.rs3.getString("IDENTIFICADOR_ENCOMENDA"), DadosConnect.rs3.getFloat("CUSTO_ENCOMENDA"), DadosConnect.rs3.getDate("DATACRIACAO_ENCOMENDA"), DadosConnect.rs3.getString("ESTADO_ENCOMENDA"), DadosConnect.rs3.getInt("CLI_ID_UTILIZADOR"));
					  envio.setDataEntrega(DadosConnect.rs3.getDate("DATAENTREGA_ENCOMENDA"));
					  envio.setDataAceitacao(DadosConnect.rs3.getDate("DATAACEITACAO_ENCOMENDA"));
					  envio.setIdUtilizador(DadosConnect.rs3.getInt("ID_UTILIZADOR"));
					  envio.setIdArmazenista(DadosConnect.rs3.getInt("FUN_ID_UTILIZADOR"));
					  envio.setIdEstafeta(DadosConnect.rs3.getInt("FUN_ID_UTILIZADOR2"));
					  envio.setIdEncomenda(DadosConnect.rs3.getInt("ID_ENCOMENDA"));
				  }
			  
        } catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
           	e.printStackTrace();
		} 
        DadosConnect.desliga3();
        return envio;
	}
	
	
	/**
	 * @param encomenda
	 * @return boolean
	 * Metodo de adicionar Encomendas na base de dados
	 */
	public static boolean adicionarEncomenda(Encomenda encomenda) {
		DadosConnect.conecta();
        try {
        	  
			  StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" INSERT INTO encomenda  (CLI_ID_UTILIZADOR, IDENTIFICADOR_ENCOMENDA, CUSTO_ENCOMENDA, DATACRIACAO_ENCOMENDA, ESTADO_ENCOMENDA) "
			  				+ "	VALUES(?, ?, ?, ?, ?); "); 
			  java.sql.Date sqlDate = new java.sql.Date(encomenda.getDataCriacao().getTime());
			  
			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps.clearParameters();   
			  DadosConnect.ps.setInt(1, encomenda.getIdCliente());
			  DadosConnect.ps.setString(2, encomenda.getIdentificadorEncomenda());
			  DadosConnect.ps.setFloat(3, encomenda.getCusto());
			  DadosConnect.ps.setDate(4, sqlDate);
			  DadosConnect.ps.setString(5, encomenda.getEstado());
			  
			  
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
	 * @param encomendaProduto
	 * @param idEncomenda
	 * @return boolean
	 * Adicionar as tabelas de Encomenda_produto geradas com as quantidades e produtos numa encomenda
	 */
	public static boolean adicionaTabelaEncomendaProduto(Encomenda_Produto encomendaProduto, int idEncomenda) {
		DadosConnect.conecta();
        try {
        	  
			  StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" INSERT INTO encomenda_produto  (ID_ENCOMENDA, ID_PRODUTO, QUANTIDADE_PRODUTO) "
			  				+ "	VALUES(?, ?, ?); "); 
			  
			  
			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps.clearParameters();   
			  DadosConnect.ps.setInt(1, idEncomenda);
			  DadosConnect.ps.setInt(2, encomendaProduto.getIdProduto());
			  DadosConnect.ps.setInt(3, encomendaProduto.getQuantidadeProduto());
			  
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
	 * @return ArrayList de encomendas
	 * Retorna uma Lista de Encomendas através de uma condição Recebida para condicionar a query/pedido na Base de Dados
	 */
	public static ArrayList <Encomenda> listarEncomendasCondicao(String aCondicao){
		ArrayList <Encomenda> lista = new ArrayList <Encomenda> ();
	    DadosConnect.conecta();
	    try {
	    	  
			  StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" SELECT * FROM encomenda "); 
			  sqlQuery.append(" "+aCondicao+" ; ");
			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps.clearParameters();   
			  
			  DadosConnect.rs = DadosConnect.ps.executeQuery();
			  
			  if (DadosConnect.rs == null) {
				     System.out.println("!! No Record on table !!");
			  } else {
				  lista = new ArrayList <Encomenda> (); //garantir que a lista fica vazia..
				  while (DadosConnect.rs.next()) {
					  lista.add(new Encomenda(DadosConnect.rs.getString("IDENTIFICADOR_ENCOMENDA"), DadosConnect.rs.getFloat("CUSTO_ENCOMENDA"), DadosConnect.rs.getDate("DATACRIACAO_ENCOMENDA"), DadosConnect.rs.getString("ESTADO_ENCOMENDA"), DadosConnect.rs.getInt("CLI_ID_UTILIZADOR")));    
				  }
			  }
			  
	    } catch (SQLException e) {
	    	System.out.println("!! SQL Exception !!\n"+e);
	    	
	       	e.printStackTrace();
	       	return lista;
		} 
	    DadosConnect.desliga();
	    
	    return lista;
	}
	
	/**
	 * @param encomenda
	 * Realiza o update da encomenda, feito de modo geral pois faz update a todo o objecto mesmo que os valores sejam os
	 * mesmos, assim podendo reutilizar o metodo de várias formas
	 */
	public static void updateEncomenda(Encomenda encomenda) {
		DadosConnect.conecta();
        try {
        	  java.sql.Date sqlDate;
        	  java.sql.Date sqlDate2;
              
			  StringBuffer sqlQuery = new StringBuffer();
			  sqlQuery.append(" UPDATE encomenda ");
			  sqlQuery.append(" SET ESTADO_ENCOMENDA = ? ,");
			  sqlQuery.append(" ID_UTILIZADOR = ? ,");
			  sqlQuery.append(" FUN_ID_UTILIZADOR = ? ,");
			  sqlQuery.append(" FUN_ID_UTILIZADOR2 = ? ,");
			  sqlQuery.append(" DATAACEITACAO_ENCOMENDA = ? ,");
			  sqlQuery.append(" DATAENTREGA_ENCOMENDA = ? ");
			  sqlQuery.append(" WHERE IDENTIFICADOR_ENCOMENDA = ? ");

			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());

			  DadosConnect.ps.clearParameters();
			  DadosConnect.ps.setString(1, encomenda.getEstado());
			  DadosConnect.ps.setInt(2, encomenda.getIdUtilizador());
			  if(encomenda.getIdArmazenista() != 0) {
				  DadosConnect.ps.setInt(3, encomenda.getIdArmazenista());
			  }else {
				  DadosConnect.ps.setNull(3, Types.INTEGER); //como o valor pode ser nulo devido à encomenda sofrer alterações usei este metodo para inserir na tabela o null
			  }
			  if(encomenda.getIdEstafeta() != 0) {
				  DadosConnect.ps.setInt(4, encomenda.getIdEstafeta());
			  }else {
				  DadosConnect.ps.setNull(4, Types.INTEGER);
			  }
			  
			  if(encomenda.getDataAceitacao() != null) {
				  sqlDate = new java.sql.Date(encomenda.getDataAceitacao().getTime());
				  DadosConnect.ps.setDate(5, sqlDate);
			  }else {
				  DadosConnect.ps.setNull(5, Types.DATE);
			  }
			  if(encomenda.getDataEntrega() != null) {
				  sqlDate2 = new java.sql.Date(encomenda.getDataEntrega().getTime());
				  DadosConnect.ps.setDate(6, sqlDate2);
			  }else {
				  DadosConnect.ps.setNull(6, Types.DATE);
			  }
			  
			  DadosConnect.ps.setString(7, encomenda.getIdentificadorEncomenda());
			  
			  
			  DadosConnect.ps.executeUpdate();
			  
        } catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
        	DadosConnect.desliga();
           	e.printStackTrace();
		} 
        
        DadosConnect.desliga();
	}
	
	/**
	 * @param aCondicao
	 * @return ArrayList com objecto Encomenda_Produto
	 * Envia ArrayList com todas as tabelas numa determinada condicação "ex Id Encomenda = 1"
	 */
	public static ArrayList <Encomenda_Produto> listaEncomenda_Produto (String aCondicao){
		ArrayList <Encomenda_Produto> lista = new ArrayList <Encomenda_Produto> ();
	    DadosConnect.conecta();
	    try {
	    	  
			  StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" SELECT * FROM encomenda_produto "); 
			  sqlQuery.append(" "+aCondicao+" ; ");
			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps.clearParameters();   
			  
			  DadosConnect.rs = DadosConnect.ps.executeQuery();
			  
			  if (DadosConnect.rs == null) {
				     System.out.println("!! No Record on table !!");
			  } else {
				  lista = new ArrayList <Encomenda_Produto> (); //garantir que a lista fica vazia..
				  while (DadosConnect.rs.next()) {
					  Encomenda_Produto encomendaProduto = new Encomenda_Produto(DadosConnect.rs.getInt("ID_PRODUTO"), DadosConnect.rs.getInt("QUANTIDADE_PRODUTO"));
					  encomendaProduto.setIdEncomenda(DadosConnect.rs.getInt("ID_ENCOMENDA"));
					  lista.add(encomendaProduto);
				  }
			  }
	    } catch (SQLException e) {
	    	System.out.println("!! SQL Exception !!\n"+e);
	    	
	       	e.printStackTrace();
	       	return lista;
		} 
	    DadosConnect.desliga();
	    
	    return lista;
	}
	
	/**
	 * @return inteiro com numero de encomendas que passam 10 dias desde a sua criação
	 */
	public static int EncomendasForaDeTempo() {
		int conta = 0;
        
  	  
	    StringBuffer sqlQuery = new StringBuffer();
		
	    DadosConnect.conecta();
			  
	    sqlQuery.append(" SELECT COUNT(*) AS \"Contador\" FROM encomenda "); 
	    sqlQuery.append(" WHERE ID_UTILIZADOR = '"+DadosUtilizadores.verificaLogin(DadosStatic.Login)+"' "
	    				+ " AND ABS(DATEDIFF(DATAACEITACAO_ENCOMENDA, CURDATE())) > 10 " //vai verificar desde a data que aceitou até à data actual
	    				+ " AND ESTADO_ENCOMENDA != 'confirmada' AND ESTADO_ENCOMENDA != 'rejeitada' ;");
	    //importante que não é desde a data que a encomenda foi iniciada porque existe uma notificação permanente enquanto esta não for aceite ou rejeitada
	    try {
	    	DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
	    	DadosConnect.ps.clearParameters();   
			
	    	DadosConnect.rs = DadosConnect.ps.executeQuery();
			  
	    	if (DadosConnect.rs == null) {
		    System.out.println("!! No Record on table !!");
	    	} else
	    		while (DadosConnect.rs.next()) {
	    			conta += DadosConnect.rs.getInt("Contador");  	  
				}
			  
	    	
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    		DadosConnect.desliga();
	    		return 0;
	    	}
		DadosConnect.desliga();
        
        return conta;
	}


}
	

