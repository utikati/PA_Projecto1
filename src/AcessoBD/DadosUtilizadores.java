package AcessoBD;

import java.sql.SQLException;

import java.util.ArrayList;



import Utilizadores.*;

/**
 * 
 * @author Kos_o
 * Estão todos os metodos de acesso para dados relativos a utilizadores.
 */

public class DadosUtilizadores {
	
	
	/**
	 * Verifica se existem utilizadores na base de dados.
	 * @return inteiro com numero de utilizadores na base de dados.
	 */
	public static int verificaTabela(){
        int conta = 0;
        
        	  
	    StringBuffer sqlQuery = new StringBuffer();
		
	    DadosConnect.conecta();
			  
	    sqlQuery.append(" SELECT COUNT(*) AS \"Contador\" FROM utilizadores ; "); 
	    
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
	    		
	    		return 0;
	    	}
		DadosConnect.desliga();
        
        return conta;
	}
	
	
	/**
	 * @return inteiro com contagem de contas novas
	 * Envia contagem com contas que se encontram no estado 'espera'
	 * 
	 */
	public static int verificaContasNovas() {
		int conta = 0;
        
        	  
	    StringBuffer sqlQuery = new StringBuffer();
		
	    DadosConnect.conecta();
			  
	    sqlQuery.append(" SELECT COUNT(*) AS \"Contador\" FROM utilizadores "); 
	    sqlQuery.append(" WHERE ESTADO_UTILIZADOR = 'espera' ; ");
	    
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
	
	/**
	 * @return contagem de Contas de utilizador com estado 'pedido'
	 */
	public static int verificaPedidosApagar() {
		int conta = 0;
        
        
        	  
	    StringBuffer sqlQuery = new StringBuffer();
		
	    DadosConnect.conecta();
			  
	    sqlQuery.append(" SELECT COUNT(*) AS \"Contador\" FROM utilizadores "); 
	    sqlQuery.append(" WHERE ESTADO_UTILIZADOR = 'pedido' ; ");
	    
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
	
	//Adicionar dados----------------------------------------------------------
	
	/**
	 * @param aUtilizador
	 * @return boolean
	 * Adicionar Utilizador Gestor com apenas Objecto Pai (dados gerais)
	 */
	public static boolean adicionaUtilizador(Utilizadores aUtilizador) {
		
		DadosConnect.conecta();
		

        try {
        	  
			  StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" INSERT INTO utilizadores  (NOME_UTILIZADOR , LOGIN_UTILIZADOR, PASSWORD_UTILIZADOR, EMAIL_UTILIZADOR, ESTADO_UTILIZADOR, TIPO_UTILIZADOR) "
			  				+ "	VALUES( ?, ?, ?, ?, ?, ?); "); 

			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps.clearParameters();   
			  DadosConnect.ps.setString(1, aUtilizador.getNome());
			  DadosConnect.ps.setString(2, aUtilizador.getLogin());
			  DadosConnect.ps.setString(3, aUtilizador.getPass());
			  DadosConnect.ps.setString(4, aUtilizador.getEmail());
			  DadosConnect.ps.setString(5, aUtilizador.getEstado());
			  DadosConnect.ps.setString(6, aUtilizador.getTipo());
			  
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
	 * @param aCliente
	 * @return boolean
	 * Adicionar Utilizador Cliente, filho da Tabela Pai Utilizadores tanto na base de dados como em Objecto
	 * Adiciona o objecto nas tabelas referentes as suas informações
	 */
	public static boolean adicionaCliente(Clientes aCliente) {
		
		DadosConnect.conecta();
		
        try {
        	  DadosConnect.conn.setAutoCommit(false);
			  StringBuffer sqlQuery = new StringBuffer();
			  StringBuffer sqlQuery2 = new StringBuffer();
			  
			  sqlQuery.append(" INSERT INTO utilizadores (NOME_UTILIZADOR , LOGIN_UTILIZADOR, PASSWORD_UTILIZADOR, EMAIL_UTILIZADOR, ESTADO_UTILIZADOR, TIPO_UTILIZADOR) "
		  						+ "	VALUES( ?, ?, ?, ?, ?, ?); "); 

			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps.clearParameters();   
			  DadosConnect.ps.setString(1, aCliente.getNome());
			  DadosConnect.ps.setString(2, aCliente.getLogin());
			  DadosConnect.ps.setString(3, aCliente.getPass());
			  DadosConnect.ps.setString(4, aCliente.getEmail());
			  DadosConnect.ps.setString(5, aCliente.getEstado());
			  DadosConnect.ps.setString(6, aCliente.getTipo());
			  
			  DadosConnect.ps.executeUpdate();
			  DadosConnect.conn.commit();
			  
			  sqlQuery2.append(" INSERT INTO clientes (ID_UTILIZADOR, CONTRIBUINTE_UTILIZADOR, CONTACTO_UTILIZADOR, MORADA_UTILIZADOR) "
						+ "	VALUES( ?, ?, ?, ?); "); 
			  
			  
			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery2.toString());
			  DadosConnect.ps.clearParameters();   
			  DadosConnect.ps.setString(1, verificaLogin(aCliente.getLogin()));
			  DadosConnect.ps.setInt(2, aCliente.getContribuinte());
			  DadosConnect.ps.setInt(3, aCliente.getContacto());
			  DadosConnect.ps.setString(4, aCliente.getMorada());
			  
			  DadosConnect.ps.executeUpdate();
			  DadosConnect.conn.commit();
        } catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
        	try {
        		DadosConnect.conn.rollback();
        	}catch(Exception ex) {
        		ex.printStackTrace();
        	}
           	e.printStackTrace();
           	return false;
		}
        DadosConnect.desliga();
		
		return true;
	}
	
	/**
	 * @param aFuncionario
	 * @return boolean
	 * Adicionar Utilizador Funcionario filho da Tabela Pai Clientes na base de dados tal como em objecto em codigo
	 * Adiciona o objecto nas tabelas referentes as suas informações
	 */
	public static boolean adicionaFuncionario(Funcionarios aFuncionario) {
		DadosConnect.conecta();
        try {
        	  DadosConnect.conn.setAutoCommit(false);
			  StringBuffer sqlQuery = new StringBuffer();
			  StringBuffer sqlQuery2 = new StringBuffer();
			  StringBuffer sqlQuery3 = new StringBuffer();
			  
			  sqlQuery.append(" INSERT INTO utilizadores (NOME_UTILIZADOR , LOGIN_UTILIZADOR, PASSWORD_UTILIZADOR, EMAIL_UTILIZADOR, ESTADO_UTILIZADOR, TIPO_UTILIZADOR) "
		  						+ "	VALUES( ?, ?, ?, ?, ?, ?); "); 

			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps.clearParameters();   
			  DadosConnect.ps.setString(1, aFuncionario.getNome());
			  DadosConnect.ps.setString(2, aFuncionario.getLogin());
			  DadosConnect.ps.setString(3, aFuncionario.getPass());
			  DadosConnect.ps.setString(4, aFuncionario.getEmail());
			  DadosConnect.ps.setString(5, aFuncionario.getEstado());
			  DadosConnect.ps.setString(6, aFuncionario.getTipo());
			  
			  DadosConnect.ps.executeUpdate();
			  DadosConnect.conn.commit();
			  
			  sqlQuery2.append(" INSERT INTO clientes (ID_UTILIZADOR, CONTRIBUINTE_UTILIZADOR, CONTACTO_UTILIZADOR, MORADA_UTILIZADOR) "
						+ "	VALUES( ?, ?, ?, ?); "); 
			  
			  
			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery2.toString());
			  DadosConnect.ps.clearParameters();   
			  DadosConnect.ps.setString(1, verificaLogin(aFuncionario.getLogin()));
			  DadosConnect.ps.setInt(2, aFuncionario.getContribuinte());
			  DadosConnect.ps.setInt(3, aFuncionario.getContacto());
			  DadosConnect.ps.setString(4, aFuncionario.getMorada());
			  
			  DadosConnect.ps.executeUpdate();
			  DadosConnect.conn.commit();
			  
			  sqlQuery3.append(" INSERT INTO funcionarios (ID_UTILIZADOR, FUNCIONARIO_DATA_INICIO, ESPECIALIZACAO_FUNCIONARIO) "
						+ "	VALUES( ?, ?, ?); "); 
			  
			  java.sql.Date sqlDate = new java.sql.Date(aFuncionario.getDataInicio().getTime());
			  
			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery3.toString());
			  DadosConnect.ps.clearParameters();   
			  DadosConnect.ps.setString(1, verificaLogin(aFuncionario.getLogin()));
			  DadosConnect.ps.setDate(2, sqlDate);
			  DadosConnect.ps.setString(3, aFuncionario.getEspecializacao());
			  
			  DadosConnect.ps.executeUpdate();
			  DadosConnect.conn.commit();
			  
        } catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
        	try {
        		DadosConnect.conn.rollback();
        	}catch(Exception ex) {
        		ex.printStackTrace();
        	}
           	e.printStackTrace();
           	DadosConnect.desliga();
           	return false;
		}
        DadosConnect.desliga();
		
		return true;
	}
	
	//Actualizar dados------------------------------------------------------------
	//O Update é usado para desactivar um utilizador visto que alguns dados irão ter de ficar tal como o ID.
	
	/**
	 * @param aUtilizador
	 * Update de todos os dados que se encontram no objecto utilizador na base dados
	 * Reutilização do metodo para várias variantes
	 */
	public static void updateUtilizador(Utilizadores aUtilizador) {
		DadosConnect.conecta();
	        try {
	        	  
	              
				  StringBuffer sqlQuery = new StringBuffer();
				  sqlQuery.append(" UPDATE utilizadores ");
				  sqlQuery.append(" SET NOME_UTILIZADOR = ? ,");
				  sqlQuery.append(" PASSWORD_UTILIZADOR = ? ,");
				  sqlQuery.append(" EMAIL_UTILIZADOR = ? ,");
				  sqlQuery.append(" ESTADO_UTILIZADOR = ? ");
				  sqlQuery.append(" WHERE LOGIN_UTILIZADOR = ? ");

				  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());

				  DadosConnect.ps.clearParameters();
				  DadosConnect.ps.setString(1, aUtilizador.getNome());
				  DadosConnect.ps.setString(2, aUtilizador.getPass());
				  DadosConnect.ps.setString(3, aUtilizador.getEmail());
				  DadosConnect.ps.setString(4, aUtilizador.getEstado());
				  DadosConnect.ps.setString(5, aUtilizador.getLogin());
				  
				  
				  DadosConnect.ps.executeUpdate();
				  
	        } catch (SQLException e) {
	        	System.out.println("!! SQL Exception !!\n"+e);
	        	DadosConnect.desliga();
	           	e.printStackTrace();
			} 
	        
	        DadosConnect.desliga();
	}
	
	/**
	 * @param aCliente
	 * Update de todos os dados que se encontram no objecto cliente na base dados
	 * Reutilização do metodo para várias variantes
	 */
	public static void updateCliente(Clientes aCliente) {
		DadosConnect.conecta();
        try {
        	  
        	  DadosConnect.conn.setAutoCommit(false);
			  StringBuffer sqlQuery = new StringBuffer();
			  sqlQuery.append(" UPDATE utilizadores ");
			  sqlQuery.append(" SET NOME_UTILIZADOR = ? ,");
			  sqlQuery.append(" PASSWORD_UTILIZADOR = ? ,");
			  sqlQuery.append(" EMAIL_UTILIZADOR = ? ,");
			  sqlQuery.append(" ESTADO_UTILIZADOR = ? ");
			  sqlQuery.append(" WHERE LOGIN_UTILIZADOR = ? ");

			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());

			  DadosConnect.ps.clearParameters();
			  DadosConnect.ps.setString(1, aCliente.getNome());
			  DadosConnect.ps.setString(2, aCliente.getPass());
			  DadosConnect.ps.setString(3, aCliente.getEmail());
			  DadosConnect.ps.setString(4, aCliente.getEstado());
			  DadosConnect.ps.setString(5, aCliente.getLogin());
			  
			  DadosConnect.ps.executeUpdate();
			  DadosConnect.conn.commit();
			  
			  StringBuffer sqlQuery2 = new StringBuffer();
			  
			  sqlQuery2.append(" UPDATE clientes ");
			  sqlQuery2.append(" SET CONTRIBUINTE_UTILIZADOR = ? ");
			  sqlQuery2.append(" CONTACTO_UTILIZADOR = ? ,");
			  sqlQuery2.append(" MORADA_UTILIZADOR = ? ");
			  sqlQuery2.append(" WHERE ID_UTILIZADOR = ? ");
			  
			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery2.toString());

			  DadosConnect.ps.clearParameters();
			  DadosConnect.ps.setInt(1, aCliente.getContribuinte());
			  DadosConnect.ps.setInt(2, aCliente.getContacto());
			  DadosConnect.ps.setString(3, aCliente.getMorada());
			  DadosConnect.ps.setString(4, verificaLogin(aCliente.getLogin()));
			  
			  DadosConnect.ps.executeUpdate();
			  DadosConnect.conn.commit();
			  
        } catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
        	try {
        		DadosConnect.conn.rollback();
        	}catch(Exception ex) {
        		ex.printStackTrace();
        	}
        	DadosConnect.desliga();
           	e.printStackTrace();
		} 
        DadosConnect.desliga();
	}
	
	/**
	 * @param aFuncionario
	 * Update de todos os dados que se encontram no objecto Funcionario na base dados
	 * Reutilização do metodo para várias variantes
	 */
	public static void updateFuncionario(Funcionarios aFuncionario) {
		DadosConnect.conecta();
        try {
        	  
              DadosConnect.conn.setAutoCommit(false);
			  StringBuffer sqlQuery = new StringBuffer();
			  sqlQuery.append(" UPDATE utilizadores ");
			  sqlQuery.append(" SET NOME_UTILIZADOR = ? ,");
			  sqlQuery.append(" PASSWORD_UTILIZADOR = ? ,");
			  sqlQuery.append(" EMAIL_UTILIZADOR = ? ,");
			  sqlQuery.append(" ESTADO_UTILIZADOR = ? ");
			  sqlQuery.append(" WHERE LOGIN_UTILIZADOR = ? ");

			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());

			  DadosConnect.ps.clearParameters();
			  DadosConnect.ps.setString(1, aFuncionario.getNome());
			  DadosConnect.ps.setString(2, aFuncionario.getPass());
			  DadosConnect.ps.setString(3, aFuncionario.getEmail());
			  DadosConnect.ps.setString(4, aFuncionario.getEstado());
			  DadosConnect.ps.setString(5, aFuncionario.getLogin());
			  
			  DadosConnect.ps.executeUpdate();
			  DadosConnect.conn.commit();
			  
			  StringBuffer sqlQuery2 = new StringBuffer();
			  
			  sqlQuery2.append(" UPDATE clientes ");
			  sqlQuery2.append(" SET CONTRIBUINTE_UTILIZADOR = ? ,");
			  sqlQuery2.append(" CONTACTO_UTILIZADOR = ? ,");
			  sqlQuery2.append(" MORADA_UTILIZADOR = ? ");
			  sqlQuery2.append(" WHERE ID_UTILIZADOR = ? ");
			  
			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery2.toString());

			  DadosConnect.ps.clearParameters();
			  DadosConnect.ps.setInt(1, aFuncionario.getContribuinte());
			  DadosConnect.ps.setInt(2, aFuncionario.getContacto());
			  DadosConnect.ps.setString(3, aFuncionario.getMorada());
			  DadosConnect.ps.setString(4, verificaLogin(aFuncionario.getLogin()));
			  
			  
			  DadosConnect.ps.executeUpdate();
			  DadosConnect.conn.commit();
			  
			  StringBuffer sqlQuery3 = new StringBuffer();
			  
			  sqlQuery3.append(" UPDATE funcionarios ");
			  sqlQuery3.append(" SET FUNCIONARIO_DATA_INICIO = ? ,");
			  sqlQuery3.append(" ESPECIALIZACAO_FUNCIONARIO = ? ");
			  sqlQuery3.append(" WHERE ID_UTILIZADOR = ? ");
			  
			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery3.toString());
			  
			  java.sql.Date sqlDate = new java.sql.Date(aFuncionario.getDataInicio().getTime());

			  DadosConnect.ps.clearParameters();
			  DadosConnect.ps.setDate(1, sqlDate);
			  DadosConnect.ps.setString(2, aFuncionario.getEspecializacao());
			  DadosConnect.ps.setString(3, verificaLogin(aFuncionario.getLogin()));
			  
			  DadosConnect.ps.executeUpdate();
			  DadosConnect.conn.commit();
			  
        } catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
        	try {
        		DadosConnect.conn.rollback();
        	}catch(Exception ex) {
        		ex.printStackTrace();
        	}
           	e.printStackTrace();
		}
        DadosConnect.desliga();
	}
	
	
	//LISTAR DADOS-------------------------------------------------------
	
	/**
	 * @param aOrdem
	 * @return ArrayList com objectos Utilizadores
	 * Metodo para listagem através da condição Order BY
	 */
	public static ArrayList <Utilizadores> listarTodosUtilizadores(String aOrdem) {
        ArrayList <Utilizadores> lista = new ArrayList <Utilizadores> ();
        DadosConnect.conecta();
        try {
        	  
			  StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" SELECT * FROM utilizadores ");
			  sqlQuery.append(" ORDER BY "+aOrdem+" ;");

			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps.clearParameters();   
			  
			  DadosConnect.rs = DadosConnect.ps.executeQuery();
			  
			  if (DadosConnect.rs == null) {
				     System.out.println("!! No Record on table !!");
			  } else {
				  lista = new ArrayList <Utilizadores> (); //garantir que a lista fica vazia..
				  while (DadosConnect.rs.next()) {
					  lista.add(new Utilizadores(DadosConnect.rs.getString("NOME_UTILIZADOR"), DadosConnect.rs.getString("LOGIN_UTILIZADOR"), DadosConnect.rs.getString("PASSWORD_UTILIZADOR"), DadosConnect.rs.getString("ESTADO_UTILIZADOR"), DadosConnect.rs.getString("EMAIL_UTILIZADOR"), DadosConnect.rs.getString("TIPO_UTILIZADOR")));    
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
	
	/**
	 * @param aCondicao
	 * @return ArrayList com Objectos Utilizadores
	 * Metodo de Listagem através da condiçao Where
	 */
	public static ArrayList <Utilizadores> listarUtilizadoresCondicao(String aCondicao){
		ArrayList <Utilizadores> lista = new ArrayList <Utilizadores> ();
        DadosConnect.conecta();
        try {
        	  
			  StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" SELECT * FROM utilizadores "); 
			  sqlQuery.append(" WHERE "+aCondicao+" ; ");

			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps.clearParameters();   
			  
			  DadosConnect.rs = DadosConnect.ps.executeQuery();
			  
			  if (DadosConnect.rs == null) {
				     System.out.println("!! No Record on table !!");
			  } else {
				  lista = new ArrayList <Utilizadores> (); //garantir que a lista fica vazia..
				  while (DadosConnect.rs.next()) {
					  lista.add(new Utilizadores(DadosConnect.rs.getString("NOME_UTILIZADOR"), DadosConnect.rs.getString("LOGIN_UTILIZADOR"), DadosConnect.rs.getString("PASSWORD_UTILIZADOR"), DadosConnect.rs.getString("ESTADO_UTILIZADOR"), DadosConnect.rs.getString("EMAIL_UTILIZADOR"), DadosConnect.rs.getString("TIPO_UTILIZADOR")));    
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
	
	
	
	//PESQUISAR DADOS-------------------------------------------------------
	
	/**
	 * @param aLogin
	 * @return Objecto Utilizadores com o login recebido
	 */
	public static Utilizadores pesquisaLogin(String aLogin) {
		DadosConnect.conecta();
		Utilizadores utilizador = new Utilizadores();
        try {
        	  
			  StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" SELECT * FROM utilizadores ");
			  sqlQuery.append(" WHERE LOGIN_UTILIZADOR = ? ;");

			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps.clearParameters();   
			  DadosConnect.ps.setString(1, aLogin);
			  
			  DadosConnect.rs = DadosConnect.ps.executeQuery();
			  
			  if (DadosConnect.rs == null) {
				     System.out.println("!! No Record on table !!");
			  } else {
				  while (DadosConnect.rs.next()) {
					  utilizador = new Utilizadores(DadosConnect.rs.getString("NOME_UTILIZADOR"), DadosConnect.rs.getString("LOGIN_UTILIZADOR"), DadosConnect.rs.getString("PASSWORD_UTILIZADOR"), DadosConnect.rs.getString("ESTADO_UTILIZADOR"), DadosConnect.rs.getString("EMAIL_UTILIZADOR"), DadosConnect.rs.getString("TIPO_UTILIZADOR"));    
				  }
			  }
				  
			  
        } catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
           	e.printStackTrace();
		} 
        
        DadosConnect.desliga();
        
        return utilizador;
	}
	
	/**
	 * @param aLogin
	 * @return objecto cliente com o login recebido
	 */
	public static Clientes pesquisaClientes(String aLogin) {
		DadosConnect.conecta();
		Utilizadores utilizador = new Utilizadores();
		Clientes cliente = new Clientes();
		int id = 0;
        try {
        	  
			  StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" SELECT * FROM utilizadores ");
			  sqlQuery.append(" WHERE LOGIN_UTILIZADOR = ? ;");

			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps.clearParameters();   
			  DadosConnect.ps.setString(1, aLogin);
			  
			  DadosConnect.rs = DadosConnect.ps.executeQuery();
			  
			  if (DadosConnect.rs == null) {
				     System.out.println("!! No Record on table !!");
			  } else {
				  while (DadosConnect.rs.next()) {
					  id = DadosConnect.rs.getInt("ID_UTILIZADOR");
					  utilizador = new Utilizadores(DadosConnect.rs.getString("NOME_UTILIZADOR"), DadosConnect.rs.getString("LOGIN_UTILIZADOR"), DadosConnect.rs.getString("PASSWORD_UTILIZADOR"), DadosConnect.rs.getString("ESTADO_UTILIZADOR"), DadosConnect.rs.getString("EMAIL_UTILIZADOR"), DadosConnect.rs.getString("TIPO_UTILIZADOR"));    
				  }
			  }
			  
			  sqlQuery = new StringBuffer();
			  
			// prepared statement for select
			  sqlQuery.append(" SELECT * FROM clientes ");
			  sqlQuery.append(" WHERE ID_UTILIZADOR = "+id+" ;");

			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps.clearParameters();   
			  
			  DadosConnect.rs = DadosConnect.ps.executeQuery();
			  
			  if (DadosConnect.rs == null) {
				     System.out.println("!! No Record on table !!");
			  } else {
				  while (DadosConnect.rs.next()) {
					  cliente = new Clientes(utilizador.getNome(), utilizador.getLogin(), utilizador.getPass(), utilizador.getEstado(), utilizador.getEmail(), utilizador.getTipo(), DadosConnect.rs.getInt("CONTRIBUINTE_UTILIZADOR"), DadosConnect.rs.getInt("CONTACTO_UTILIZADOR"), DadosConnect.rs.getString("MORADA_UTILIZADOR"));    
				  }
			  }	  
			  
        } catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
           	e.printStackTrace();
		} 
        
        DadosConnect.desliga();
        
        return cliente;
	}
	
	/**
	 * @param aLogin
	 * @return objecto funcionarios com o login recebido
	 */
	public static Funcionarios pesquisaFuncionarios(String aLogin) {
		DadosConnect.conecta();
		Utilizadores utilizador = new Utilizadores();
		Clientes cliente = new Clientes();
		Funcionarios funcionario = new Funcionarios();
		int id = 0;
        try {
        	  
			  StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" SELECT * FROM utilizadores ");
			  sqlQuery.append(" WHERE LOGIN_UTILIZADOR = ? ;");

			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps.clearParameters();   
			  DadosConnect.ps.setString(1, aLogin);
			  
			  DadosConnect.rs = DadosConnect.ps.executeQuery();
			  
			  if (DadosConnect.rs == null) {
				     System.out.println("!! No Record on table !!");
			  } else {
				  while (DadosConnect.rs.next()) {
					  id = DadosConnect.rs.getInt("ID_UTILIZADOR");
					  utilizador = new Utilizadores(DadosConnect.rs.getString("NOME_UTILIZADOR"), DadosConnect.rs.getString("LOGIN_UTILIZADOR"), DadosConnect.rs.getString("PASSWORD_UTILIZADOR"), DadosConnect.rs.getString("ESTADO_UTILIZADOR"), DadosConnect.rs.getString("EMAIL_UTILIZADOR"), DadosConnect.rs.getString("TIPO_UTILIZADOR"));    
				  }
			  }
			  
			  sqlQuery = new StringBuffer();
			  
			// prepared statement for select
			  sqlQuery.append(" SELECT * FROM clientes ");
			  sqlQuery.append(" WHERE ID_UTILIZADOR = "+id+" ;");

			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps.clearParameters();   
			  
			  DadosConnect.rs = DadosConnect.ps.executeQuery();
			  
			  if (DadosConnect.rs == null) {
				     System.out.println("!! No Record on table !!");
			  } else {
				  while (DadosConnect.rs.next()) {
					  cliente = new Clientes(utilizador.getNome(), utilizador.getLogin(), utilizador.getPass(), utilizador.getEstado(), utilizador.getEmail(), utilizador.getTipo(), DadosConnect.rs.getInt("CONTRIBUINTE_UTILIZADOR"), DadosConnect.rs.getInt("CONTACTO_UTILIZADOR"), DadosConnect.rs.getString("MORADA_UTILIZADOR"));    
				  }
			  }	
			  
			  sqlQuery = new StringBuffer();
			  
				// prepared statement for select
				  sqlQuery.append(" SELECT * FROM funcionarios ");
				  sqlQuery.append(" WHERE ID_UTILIZADOR = "+id+" ;");

				  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
				  DadosConnect.ps.clearParameters();   
				  
				  DadosConnect.rs = DadosConnect.ps.executeQuery();
				  
				  if (DadosConnect.rs == null) {
					     System.out.println("!! No Record on table !!");
				  } else {
					  while (DadosConnect.rs.next()) {
						  funcionario = new Funcionarios(utilizador.getNome(), utilizador.getLogin(), utilizador.getPass(), utilizador.getEstado(), utilizador.getEmail(), utilizador.getTipo(), cliente.getContribuinte(), cliente.getContacto(), cliente.getMorada(), DadosConnect.rs.getDate("FUNCIONARIO_DATA_INICIO"), DadosConnect.rs.getString("ESPECIALIZACAO_FUNCIONARIO"));    
					  }
				  }	
			  
				  																																									
			  
        } catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
           	e.printStackTrace();
		} 
        
        DadosConnect.desliga();
        
        return funcionario;
	}
	
	//Verificar Dados--------------------------------------------------
	/**
	 * @param login
	 * @return String com o id do Utilizador na base de dados
	 */
	public static String verificaLogin(String login) {
		DadosConnect.conecta2();
        String envio = "";

        try {
        	  
              StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" SELECT ID_UTILIZADOR FROM utilizadores "); 
			  sqlQuery.append(" WHERE LOGIN_UTILIZADOR = ? ");

			  DadosConnect.ps2 = DadosConnect.conn2.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps2.clearParameters();
			  DadosConnect.ps2.setString(1, login);   
			  
			  DadosConnect.rs2 = DadosConnect.ps2.executeQuery();
			  
			  if (DadosConnect.rs2 == null) {
				     System.out.println("!! No Record on table !!");
			  } else
				  while (DadosConnect.rs2.next()) {
					  envio = DadosConnect.rs2.getString("ID_UTILIZADOR");
				  }
			  
        } catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
           	e.printStackTrace();
		} 
        DadosConnect.desliga2();
        return envio;
	}
	
	/**
	 * @param aEmail
	 * @return String com email vindo da base de dados
	 */
	public static String verificaEmail(String aEmail) {
        String email = new String();
        DadosConnect.conecta();
        try {
        	 
			  StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" SELECT EMAIL_UTILIZADOR FROM utilizadores "); 
			  sqlQuery.append(" WHERE EMAIL_UTILIZADOR = ? ; ");
			  

			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps.clearParameters();
			  DadosConnect.ps.setString(1, aEmail);
			  
			  DadosConnect.rs = DadosConnect.ps.executeQuery();
			  
			  if (DadosConnect.rs == null) {
				     System.out.println("!! No Record on table !!");
			  } else
				  while (DadosConnect.rs.next()) {
					  email = DadosConnect.rs.getString("EMAIL_UTILIZADOR"); 
				  }
			  
			  DadosConnect.ps.close();
			  
        } catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
        	DadosConnect.desliga();
           	e.printStackTrace();
           	return email;
		} 
        DadosConnect.desliga();
        return email;
	}
	
	/**
	 * @param aContribuinte
	 * @return inteiro com Contribuinte vindo da base de dados caso diferente será enviado 0
	 *
	 */
	public static int verificaContribuinte(int aContribuinte) {
		int contribuinte = 0;
        DadosConnect.conecta();
        try {
        	 
			  StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" SELECT CONTRIBUINTE_UTILIZADOR FROM clientes "); 
			  sqlQuery.append(" WHERE CONTRIBUINTE_UTILIZADOR = ? ; ");
			  

			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps.clearParameters();
			  DadosConnect.ps.setInt(1, aContribuinte);
			  
			  DadosConnect.rs = DadosConnect.ps.executeQuery();
			  
			  if (DadosConnect.rs == null) {
				     System.out.println("!! No Record on table !!");
			  } else
				  while (DadosConnect.rs.next()) {
					  contribuinte = DadosConnect.rs.getInt("CONTRIBUINTE_UTILIZADOR"); 
				  }
			  
			  DadosConnect.ps.close();
			  
        } catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
           	e.printStackTrace();
		} 
        DadosConnect.desliga();
        return contribuinte;
	}
	
	/**
	 * @param aContacto
	 * @return inteiro com contacto
	 * Pesquisa e verifica se existe contacto na base de dados igual ao que recebe
	 * caso não exista a base de dados devolve 0
	 */
	public static int verificaContacto(int aContacto) {
		int contacto = 0;
        DadosConnect.conecta();
        try {
        	 
			  StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" SELECT CONTACTO_UTILIZADOR FROM clientes "); 
			  sqlQuery.append(" WHERE CONTACTO_UTILIZADOR = ? ; ");
			  

			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps.clearParameters();
			  DadosConnect.ps.setInt(1, aContacto);
			  
			  DadosConnect.rs = DadosConnect.ps.executeQuery();
			  
			  if (DadosConnect.rs == null) {
				     System.out.println("!! No Record on table !!");
			  } else
				  while (DadosConnect.rs.next()) {
					  contacto = DadosConnect.rs.getInt("CONTACTO_UTILIZADOR"); 
				  }
			  
			  DadosConnect.ps.close();
			  
        } catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
           	e.printStackTrace();
		} 
        DadosConnect.desliga();
        return contacto;
	}
	
	
	/**
	 * @param idUtilizador
	 * @return String com Login
	 * Através do inteiro idUtilizador (ID_Utilizador na Base de Dados) devolve o Login (string) a que lhe pertence
	 */
	public static String getLogin(int idUtilizador) {
		DadosConnect.conecta2();
        String envio = "";

        try {
        	  
              StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" SELECT LOGIN_UTILIZADOR FROM utilizadores "); 
			  sqlQuery.append(" WHERE ID_UTILIZADOR = ? ");

			  DadosConnect.ps2 = DadosConnect.conn2.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps2.clearParameters();
			  DadosConnect.ps2.setInt(1, idUtilizador);   
			  
			  DadosConnect.rs2 = DadosConnect.ps2.executeQuery();
			  
			  if (DadosConnect.rs2 == null) {
				     System.out.println("!! No Record on table !!");
			  } else
				  while (DadosConnect.rs2.next()) {
					  envio = DadosConnect.rs2.getString("LOGIN_UTILIZADOR");
				  }
			  
        } catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
           	e.printStackTrace();
		} 
        DadosConnect.desliga2();
        return envio;
	}
	
	//Verificar quantas vezes o programa foi usado
	/**
	 * Serve para usar a tabela de logs e verifica o numero de utilizações do programa
	 * @return
	 */
	public static int contaExecuções() {
		int conta = 0;
        
  	  
	    StringBuffer sqlQuery = new StringBuffer();
		
	    DadosConnect.conecta();
			  
	    sqlQuery.append(" SELECT COUNT(*) AS \"Contador\" FROM logs "); 
	    sqlQuery.append(" WHERE ACAO_LOG = 'Iniciou o Programa' ;");
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
	    		
	    		return 0;
	    	}
		DadosConnect.desliga();
        
        return conta;
	}
	
	/**
	 * Serve para indicar a ultima data de execução do programa, ou seja a ultima vez que ele foi iniciado
	 * @return
	 */
	public static String ultimaData() {
		String data = "";
        
	  	  
	    StringBuffer sqlQuery = new StringBuffer();
		
	    DadosConnect.conecta();
			  
	    sqlQuery.append(" SELECT MAX(DATA_LOG) AS \"Data\" FROM logs "); 
	    sqlQuery.append(" WHERE ACAO_LOG = 'Iniciou o Programa' ;");
	    try {
	    	DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
	    	DadosConnect.ps.clearParameters();   
			
	    	DadosConnect.rs = DadosConnect.ps.executeQuery();
			  
	    	if (DadosConnect.rs == null) {
		    System.out.println("!! No Record on table !!");
	    	} else
	    		while (DadosConnect.rs.next()) {
	    			data += DadosConnect.rs.getDate("Data");  	  
				}
			  
	    	
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    		
	    		return "Sem data";
	    	}
		DadosConnect.desliga();
        
        return data;
	}
	}
	

