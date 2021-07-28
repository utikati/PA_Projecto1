package AcessoBD;



import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import Utilizadores.*;

import PedidosNotificacoes.*;

/**
 * @author Jorge Martins
 *
 */
public class DadosPedido {
	/**
	 * @param pedido
	 * @return boolean
	 * Adicionar Pedido Na Base de Dados através do objecto Pedido que recebe o metodo
	 */
	public static boolean adicionaPedido(Pedido pedido) {
		DadosConnect.conecta();
        try {
        	  
			  StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" INSERT INTO pedido  (ID_ENCOMENDA, ID_UTILIZADOR, CLI_ID_UTILIZADOR, FUN_ID_UTILIZADOR, ESTADO_PEDIDO, TIPO_PEDIDO) "
			  				+ "	VALUES( ?, ?, ?, ?, ?, ?); "); 
			  
			  
			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps.clearParameters();   
			  DadosConnect.ps.setInt(1, pedido.getIdEncomenda());
			  DadosConnect.ps.setInt(2, pedido.getIdUtilizador());
			  if(pedido.getIdCliente() != 0) {
				  DadosConnect.ps.setInt(3, pedido.getIdCliente());
			  }else {
				  DadosConnect.ps.setNull(3, Types.INTEGER);
			  }
			  DadosConnect.ps.setInt(4, pedido.getIdFuncionario());
			  DadosConnect.ps.setString(5, pedido.getEstado());
			  DadosConnect.ps.setString(6, pedido.getTipoPedido());
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
	 * @param pedido
	 * Metodo Update na base de dados para a tabela pedido com condição através do ID do Pedido
	 * Update ao estado do pedido apenas deixando-o inactivo ou rejeitado
	 */
	public static void updateEstadoPedido(Pedido pedido) {
		DadosConnect.conecta2();
        try {
        	  
              
			  StringBuffer sqlQuery = new StringBuffer();
			  sqlQuery.append(" UPDATE pedido ");
			  sqlQuery.append(" SET ESTADO_PEDIDO = ? ");
			  sqlQuery.append(" WHERE ID_PEDIDO = ? ");

			  DadosConnect.ps2 = DadosConnect.conn2.prepareStatement(sqlQuery.toString());

			  DadosConnect.ps2.clearParameters();
			  DadosConnect.ps2.setString(1, pedido.getEstado());
			  DadosConnect.ps2.setInt(2, pedido.getIdPedido()); 
			  
			  DadosConnect.ps2.executeUpdate();
			  
        } catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
           	e.printStackTrace();
		} 
        
        DadosConnect.desliga2();
	}

	/**
	 * @param aCondicao
	 * @return ArrayList de objecto pedido
	 * Recebe todos os result set de pedido através da condicionante que preenche a query após o from
	 * Transforma em objecto pedido e o insere num ArrayList
	 */
	public static ArrayList <Pedido> listarPedidosCondicao(String aCondicao){
		ArrayList <Pedido> lista = new ArrayList <Pedido> ();
        DadosConnect.conecta();
        try {
        	  
			  StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" SELECT * FROM pedido "); 
			  sqlQuery.append(" "+aCondicao+" ; ");
			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps.clearParameters();   
			  
			  DadosConnect.rs = DadosConnect.ps.executeQuery();
			  
			  if (DadosConnect.rs == null) {
				     System.out.println("!! No Record on table !!");
			  } else {
				  lista = new ArrayList <Pedido> (); //garantir que a lista fica vazia..
				  while (DadosConnect.rs.next()) {
					  lista.add(new Pedido(DadosConnect.rs.getInt("ID_PEDIDO"), DadosConnect.rs.getInt("ID_UTILIZADOR"), DadosConnect.rs.getInt("ID_ENCOMENDA"), DadosConnect.rs.getInt("CLI_ID_UTILIZADOR"), DadosConnect.rs.getInt("FUN_ID_UTILIZADOR"), DadosConnect.rs.getString("TIPO_PEDIDO"), DadosConnect.rs.getString("ESTADO_PEDIDO")));    
					  
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
	 * @param idEncomenda
	 * @param idEstafeta
	 * @return inteiro com numero de rejeições do estatefa a pedidos com a condicao de terem o id de encomenda recebido
	 */
	public static int verificarRejeicoesFunc(int idEncomenda, int idEstafeta) {
		int conta = 0;      
        	  
	    StringBuffer sqlQuery = new StringBuffer();
		
	    DadosConnect.conecta2();
			  
	    sqlQuery.append(" SELECT COUNT(*) AS \"Contador\" FROM pedido "); 
	    sqlQuery.append(" WHERE ID_ENCOMENDA = "+idEncomenda+" AND FUN_ID_UTILIZADOR = "+idEstafeta+" AND ESTADO_PEDIDO = 'rejeitado' ; ");
	    
	    try {
	    	DadosConnect.ps2 = DadosConnect.conn2.prepareStatement(sqlQuery.toString());
	    	DadosConnect.ps2.clearParameters();   
			
	    	DadosConnect.rs2 = DadosConnect.ps2.executeQuery();
			  
	    	if (DadosConnect.rs2 == null) {
		    System.out.println("!! No Record on table !!");
	    	} else
	    		while (DadosConnect.rs2.next()) {
	    			conta += DadosConnect.rs2.getInt("Contador");  	  
				}
			  
	    	
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    		return 0;
	    	}
		DadosConnect.desliga2();
        
        return conta;
	}
	
	/**
	 * @param idEstafeta
	 * @return inteiro com a contagem do numero de encomendas que o estafeta está envolvido com o estado 'em transporte'
	 */
	public static int verificarDisponibilidadeEstafeta(int idEstafeta) {
		int conta = 0;      
  	  
	    StringBuffer sqlQuery = new StringBuffer();
		
	    DadosConnect.conecta3();
			  
	    sqlQuery.append(" SELECT COUNT(*) AS \"Contador\" FROM encomenda "); 
	    sqlQuery.append(" WHERE FUN_ID_UTILIZADOR = "+idEstafeta+" AND ESTADO_ENCOMENDA = 'em transporte' ; ");
	    
	    try {
	    	DadosConnect.ps3 = DadosConnect.conn3.prepareStatement(sqlQuery.toString());
	    	DadosConnect.ps3.clearParameters();   
			
	    	DadosConnect.rs3 = DadosConnect.ps3.executeQuery();
			  
	    	if (DadosConnect.rs3 == null) {
		    System.out.println("!! No Record on table !!");
	    	} else
	    		while (DadosConnect.rs3.next()) {
	    			conta += DadosConnect.rs3.getInt("Contador");  	  
				}
			  
	    	
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    		return 0;
	    	}
		DadosConnect.desliga3();
        
        return conta;
	}
	
	
	/**
	 * @param idEncomenda
	 * @return ArrayList com objectos Funcionarios com especialização de Estafeta
	 * 
	 */
	public static ArrayList <Funcionarios> listarEstafetasDisponiveis(int idEncomenda){
		ArrayList <Funcionarios> lista = new ArrayList <Funcionarios> ();
        DadosConnect.conecta();
        try {
        	  
			  StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" SELECT * FROM funcionarios f, utilizadores u, clientes c "); 
			  sqlQuery.append(" WHERE f.ID_UTILIZADOR = u.ID_UTILIZADOR AND f.ID_UTILIZADOR = c.ID_UTILIZADOR "
			  					+ "AND f.ESPECIALIZACAO_FUNCIONARIO = 'estafeta' ; ");
			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps.clearParameters();   
			  
			  DadosConnect.rs = DadosConnect.ps.executeQuery();
			  
			  if (DadosConnect.rs == null) {
				     System.out.println("!! No Record on table !!");
			  } else {
				  lista = new ArrayList <Funcionarios> (); //garantir que a lista fica vazia..
				  while (DadosConnect.rs.next()) {
					  Funcionarios funcionario = new Funcionarios(DadosConnect.rs.getString("NOME_UTILIZADOR"), DadosConnect.rs.getString("LOGIN_UTILIZADOR"), DadosConnect.rs.getString("PASSWORD_UTILIZADOR"), DadosConnect.rs.getString("ESTADO_UTILIZADOR"), 
							  				DadosConnect.rs.getString("EMAIL_UTILIZADOR"), DadosConnect.rs.getString("TIPO_UTILIZADOR"),
							  				DadosConnect.rs.getInt("CONTRIBUINTE_UTILIZADOR"), DadosConnect.rs.getInt("CONTACTO_UTILIZADOR"), DadosConnect.rs.getString("MORADA_UTILIZADOR"), 
							  				DadosConnect.rs.getDate("FUNCIONARIO_DATA_INICIO"), DadosConnect.rs.getString("ESPECIALIZACAO_FUNCIONARIO"));    
					  if(verificarRejeicoesFunc(idEncomenda, DadosConnect.rs.getInt("ID_UTILIZADOR")) == 0 && verificarDisponibilidadeEstafeta(DadosConnect.rs.getInt("ID_UTILIZADOR")) == 0) {
						  lista.add(funcionario);
					  }
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
	 * @param id
	 * @return inteiro com o numero do Id do Pedido
	 * Verificar se o pedido existe, caso não exista é devolvido 0 porque o result set não tem nada
	 */
	public static int verificaPedido(int id) {
		DadosConnect.conecta2();
        int envio = 0;

        try {
        	  
              StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" SELECT ID_PEDIDO FROM pedido "); 
			  sqlQuery.append(" WHERE ID_PEDIDO = ? ");

			  DadosConnect.ps2 = DadosConnect.conn2.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps2.clearParameters();
			  DadosConnect.ps2.setInt(1, id);   
			  
			  DadosConnect.rs2 = DadosConnect.ps2.executeQuery();
			  
			  if (DadosConnect.rs2 == null) {
				     System.out.println("!! No Record on table !!");
			  } else
				  while (DadosConnect.rs2.next()) {
					  envio = DadosConnect.rs2.getInt("ID_PEDIDO");
				  }
			  
        } catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
           	e.printStackTrace();
		} 
        DadosConnect.desliga2();
        return envio;
	}
	
	/**
	 * @param id
	 * @return objecto Pedido
	 * Através do Id retorna os elementos de um pedido para a construção de um objecto e assim o devolver
	 */
	public static Pedido getPedidoBd(int id) {
		DadosConnect.conecta();
		Pedido pedido = new Pedido();
        try {
        	  
			  StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" SELECT * FROM pedido ");
			  sqlQuery.append(" WHERE ID_PEDIDO = ? ;");

			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps.clearParameters();   
			  DadosConnect.ps.setInt(1, id);
			  
			  DadosConnect.rs = DadosConnect.ps.executeQuery();
			  
			  if (DadosConnect.rs == null) {
				     System.out.println("!! No Record on table !!");
			  } else {
				  while (DadosConnect.rs.next()) {
					  pedido = new Pedido(DadosConnect.rs.getInt("ID_PEDIDO"), DadosConnect.rs.getInt("ID_UTILIZADOR"), DadosConnect.rs.getInt("ID_ENCOMENDA"), 
							  				DadosConnect.rs.getInt("CLI_ID_UTILIZADOR"), DadosConnect.rs.getInt("FUN_ID_UTILIZADOR"), 
							  				DadosConnect.rs.getString("TIPO_PEDIDO"), DadosConnect.rs.getString("ESTADO_PEDIDO"));    
				  }
			  }
				  
			  
        } catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
           	e.printStackTrace();
		} 
        
        DadosConnect.desliga();
        
        return pedido;
	}
	
}
