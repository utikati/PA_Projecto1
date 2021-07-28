package AcessoBD;

import java.sql.SQLException;

import Produtos.Categoria;
import Produtos.Produto;


import java.util.*;

/**
 * @author Jorge Martins
 *
 */
public class DadosProdutos {

	/**
	 * @param produto
	 * @return boolean
	 * Adiciona um produto na base de dados através dos dados do Objecto Produto que recebe
	 */
	public static boolean adicionarProduto(Produto produto) {
		DadosConnect.conecta();
        try {
        	  
			  StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" INSERT INTO produto  (DESIGNACAO_PRODUTO, FABRICANTE_PRODUTO, QUANTIDADE_PRODUTO, PRECO_PRODUTO, SKU_PRODUTO, LOTE_PRODUTO, DATAPRODUCAO_PRODUTO, ID_CATEGORIA) "
			  				+ "	VALUES( ?, ?, ?, ?, ?, ?, ?, ?); "); 
			  java.sql.Date sqlDate = new java.sql.Date(produto.getDataProducao().getTime());
			  
			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps.clearParameters();   
			  DadosConnect.ps.setString(1, produto.getDesignacao());
			  DadosConnect.ps.setString(2, produto.getFabricante());
			  DadosConnect.ps.setInt(3, produto.getQuantidade());
			  DadosConnect.ps.setFloat(4, produto.getPreco());
			  DadosConnect.ps.setInt(5, produto.getSku());
			  DadosConnect.ps.setString(6, produto.getLote());
			  DadosConnect.ps.setDate(7, sqlDate);
			  
			  DadosConnect.ps.setInt(8, idCategoria(produto.getCategoria().getDesignacao()));
			  
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
	 * @param categoria
	 * @return boolean
	 * Adiciona categoria na base de dados atraves da informacao do objecto Categoria que recebe
	 */
	public static boolean adicionarCategoria(Categoria categoria) {
		DadosConnect.conecta();
        try {
        	  
			  StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" INSERT INTO categoria (DESIGNACAO_PRODUTO, CLASSIFICACAO_PRODUTO) "
			  				+ "	VALUES( ?, ?); "); 

			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps.clearParameters();   
			  DadosConnect.ps.setString(1, categoria.getDesignacao());
			  DadosConnect.ps.setString(2, categoria.getClassificacao());
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
	 * @param designacao
	 * @return inteiro com ID da categoria na base de dados
	 */
	public static int idCategoria(String designacao) {
		DadosConnect.conecta2();
        int envio = 0;

        try {
        	  
              StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" SELECT ID_CATEGORIA FROM categoria "); 
			  sqlQuery.append(" WHERE DESIGNACAO_PRODUTO = ? ");

			  DadosConnect.ps2 = DadosConnect.conn2.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps2.clearParameters();
			  DadosConnect.ps2.setString(1, designacao);   
			  
			  DadosConnect.rs2 = DadosConnect.ps2.executeQuery();
			  
			  if (DadosConnect.rs2 == null) {
				     System.out.println("!! No Record on table !!");
			  } else
				  while (DadosConnect.rs2.next()) {
					  envio = DadosConnect.rs2.getInt("ID_CATEGORIA");
				  }
			  
        } catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
           	e.printStackTrace();
		} 
        DadosConnect.desliga2();
        return envio;
	}
	
	/**
	 * @param sku
	 * @return inteiro com o ID do produto na Base de Dados 
	 * Através da codição de verificar pelo SKU que é unico
	 */
	public static int idProduto(int sku) {
		DadosConnect.conecta3();
        int envio = 0;

        try {
        	  
              StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" SELECT ID_PRODUTO FROM produto "); 
			  sqlQuery.append(" WHERE SKU_PRODUTO = ? ");

			  DadosConnect.ps3 = DadosConnect.conn3.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps3.clearParameters();
			  DadosConnect.ps3.setInt(1, sku);   
			  
			  DadosConnect.rs3 = DadosConnect.ps3.executeQuery();
			  
			  if (DadosConnect.rs3 == null) {
				     System.out.println("!! No Record on table !!");
			  } else
				  while (DadosConnect.rs3.next()) {
					  envio = DadosConnect.rs3.getInt("ID_PRODUTO");
				  }
			  
        } catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
           	e.printStackTrace();
		} 
        DadosConnect.desliga3();
        return envio;
	}
	
	/**
	 * @param sku
	 * @return objecto Produto 
	 * Atraves de pesquisa por produto atraves da condição SKU que é unico
	 */
	public static Produto pesquisaProduto(int sku) {
		DadosConnect.conecta2();
        Produto produto = new Produto();

        try {
        	  
              StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" SELECT * FROM produto "); 
			  sqlQuery.append(" WHERE SKU_PRODUTO = ? ");

			  DadosConnect.ps2 = DadosConnect.conn2.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps2.clearParameters();
			  DadosConnect.ps2.setInt(1, sku);   
			  
			  DadosConnect.rs2 = DadosConnect.ps2.executeQuery();
			  
			  if (DadosConnect.rs2 == null) {
				     System.out.println("!! No Record on table !!");
			  } else
				  while (DadosConnect.rs2.next()) {
					  produto = new Produto(DadosConnect.rs2.getString("DESIGNACAO_PRODUTO"), DadosConnect.rs2.getString("FABRICANTE_PRODUTO"), DadosConnect.rs2.getInt("QUANTIDADE_PRODUTO"), DadosConnect.rs2.getFloat("PRECO_PRODUTO"), DadosConnect.rs2.getInt("SKU_PRODUTO"), DadosConnect.rs2.getString("LOTE_PRODUTO"), DadosConnect.rs2.getDate("DATAPRODUCAO_PRODUTO"), DadosProdutos.pesquisaCategoriaPorID(DadosConnect.rs2.getInt("ID_CATEGORIA")));
				  }
			  
        } catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
           	e.printStackTrace();
		} 
        DadosConnect.desliga2();
        return produto;
	}
	
	/**
	 * @param designacao
	 * @return Retorna Objecto categoria através da designação da mesma(condição)
	 */
	public static Categoria pesquisaCategoria(String designacao) {
		DadosConnect.conecta();
		Categoria categoria = new Categoria();
        try {
        	  
			  StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" SELECT * FROM categoria ");
			  sqlQuery.append(" WHERE DESIGNACAO_PRODUTO = ? ;");

			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps.clearParameters();   
			  DadosConnect.ps.setString(1, designacao);
			  
			  DadosConnect.rs = DadosConnect.ps.executeQuery();
			  
			  if (DadosConnect.rs == null) {
				     System.out.println("!! No Record on table !!");
			  } else {
				  while (DadosConnect.rs.next()) {
					  categoria = new Categoria(DadosConnect.rs.getString("DESIGNACAO_PRODUTO"), DadosConnect.rs.getString("CLASSIFICACAO_PRODUTO"));    
				  }
			  }
				  
			  
        } catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
           	e.printStackTrace();
		} 
        
        DadosConnect.desliga();
        
        return categoria;
	}
	
	/**
	 * @param idCategoria
	 * @return objecto Categoria
	 * Pesquisa através do ID da categoria e devolve um objecto com informações daquele ID na base de dados 
	 * na tabela de categorias
	 */
	public static Categoria pesquisaCategoriaPorID(int idCategoria) {
		DadosConnect.conecta3();
		Categoria categoria = new Categoria();
        try {
        	  
			  StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" SELECT * FROM categoria ");
			  sqlQuery.append(" WHERE ID_CATEGORIA = ? ;");

			  DadosConnect.ps3 = DadosConnect.conn3.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps3.clearParameters();   
			  DadosConnect.ps3.setInt(1, idCategoria);
			  
			  DadosConnect.rs3 = DadosConnect.ps3.executeQuery();
			  
			  if (DadosConnect.rs3 == null) {
				     System.out.println("!! No Record on table !!");
			  } else {
				  while (DadosConnect.rs3.next()) {
					  categoria = new Categoria(DadosConnect.rs3.getString("DESIGNACAO_PRODUTO"), DadosConnect.rs3.getString("CLASSIFICACAO_PRODUTO"));    
				  }
			  }
				  
			  
        } catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
           	e.printStackTrace();
		} 
        
        DadosConnect.desliga3();
        
        return categoria;
	}
	
	/**
	 * @param idProduto
	 * @return Objecto Produto
	 * Retorna objecto através da condição de verificar pelo seu ID
	 */
	public static Produto pesquisaProdutoPorID(int idProduto) {
		DadosConnect.conecta2();
        Produto produto = new Produto();

        try {
        	  
              StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" SELECT * FROM produto "); 
			  sqlQuery.append(" WHERE ID_PRODUTO = ? ");

			  DadosConnect.ps2 = DadosConnect.conn2.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps2.clearParameters();
			  DadosConnect.ps2.setInt(1, idProduto);   
			  
			  DadosConnect.rs2 = DadosConnect.ps2.executeQuery();
			  
			  if (DadosConnect.rs2 == null) {
				     System.out.println("!! No Record on table !!");
			  } else
				  while (DadosConnect.rs2.next()) {
					  produto = new Produto(DadosConnect.rs2.getString("DESIGNACAO_PRODUTO"), DadosConnect.rs2.getString("FABRICANTE_PRODUTO"), DadosConnect.rs2.getInt("QUANTIDADE_PRODUTO"), DadosConnect.rs2.getFloat("PRECO_PRODUTO"), DadosConnect.rs2.getInt("SKU_PRODUTO"), DadosConnect.rs2.getString("LOTE_PRODUTO"), DadosConnect.rs2.getDate("DATAPRODUCAO_PRODUTO"), DadosProdutos.pesquisaCategoriaPorID(DadosConnect.rs2.getInt("ID_CATEGORIA")));
				  }
			  
        } catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
           	e.printStackTrace();
		} 
        DadosConnect.desliga2();
        return produto;
	}
	
	/**
	 * @return ArrayList Categoria
	 * Pede à base de Dados todas as categorias existentes na tabela categoria
	 * é adicionado ao ArrayList todos os objectos criados de categoria
	 */
	public static ArrayList <Categoria> listarCategorias(){
		ArrayList <Categoria> lista = new ArrayList <Categoria> ();
        DadosConnect.conecta();
        try {
        	  
			  StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" SELECT * FROM categoria "); 

			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps.clearParameters();   
			  
			  DadosConnect.rs = DadosConnect.ps.executeQuery();
			  
			  if (DadosConnect.rs == null) {
				     System.out.println("!! No Record on table !!");
			  } else {
				  lista = new ArrayList <Categoria> (); //garantir que a lista fica vazia..
				  while (DadosConnect.rs.next()) {
					  lista.add(new Categoria(DadosConnect.rs.getString("DESIGNACAO_PRODUTO"), DadosConnect.rs.getString("CLASSIFICACAO_PRODUTO")));    
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
	 * @param aCondicao
	 * @return ArrayList de objectos de Produtos
	 * Dados da base de dados para criar objectos produtos através da condição
	 */
	public static ArrayList <Produto> listarProdutosCondicao(String aCondicao){
		ArrayList <Produto> lista = new ArrayList <Produto> ();
        DadosConnect.conecta();
        try {
        	  
			  StringBuffer sqlQuery = new StringBuffer();
			  
			  // prepared statement for select
			  sqlQuery.append(" SELECT * FROM produto "); 
			  sqlQuery.append(" "+aCondicao+" ; ");
			  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());
			  DadosConnect.ps.clearParameters();   
			  
			  DadosConnect.rs = DadosConnect.ps.executeQuery();
			  
			  if (DadosConnect.rs == null) {
				     System.out.println("!! No Record on table !!");
			  } else {
				  lista = new ArrayList <Produto> (); //garantir que a lista fica vazia..
				  while (DadosConnect.rs.next()) {
					  lista.add(new Produto(DadosConnect.rs.getString("DESIGNACAO_PRODUTO"), DadosConnect.rs.getString("FABRICANTE_PRODUTO"), DadosConnect.rs.getInt("QUANTIDADE_PRODUTO"), DadosConnect.rs.getFloat("PRECO_PRODUTO"), DadosConnect.rs.getInt("SKU_PRODUTO"), DadosConnect.rs.getString("LOTE_PRODUTO"), DadosConnect.rs.getDate("DATAPRODUCAO_PRODUTO"), DadosProdutos.pesquisaCategoriaPorID(DadosConnect.rs.getInt("ID_CATEGORIA"))));    
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
	 * @param aProduto
	 * Update de um produto na base de dados através de um objecto produto
	 * Todos os campos são levados em conta para metodo generico e reutilizavel em vários momentos
	 */
	public static void updateProduto(Produto aProduto) {
		DadosConnect.conecta();
	        try {
	        	  
	              
				  StringBuffer sqlQuery = new StringBuffer();
				  sqlQuery.append(" UPDATE produto ");
				  sqlQuery.append(" SET DESIGNACAO_PRODUTO = ? ,");
				  sqlQuery.append(" FABRICANTE_PRODUTO = ? ,");
				  sqlQuery.append(" QUANTIDADE_PRODUTO = ? ,");
				  sqlQuery.append(" PRECO_PRODUTO = ? ,");
				  sqlQuery.append(" LOTE_PRODUTO = ? ,");
				  sqlQuery.append(" DATAPRODUCAO_PRODUTO = ? ");
				  sqlQuery.append(" WHERE SKU_PRODUTO = ? ");

				  DadosConnect.ps = DadosConnect.conn.prepareStatement(sqlQuery.toString());

				  DadosConnect.ps.clearParameters();
				  DadosConnect.ps.setString(1, aProduto.getDesignacao());
				  DadosConnect.ps.setString(2, aProduto.getFabricante());
				  DadosConnect.ps.setInt(3, aProduto.getQuantidade());
				  DadosConnect.ps.setFloat(4, aProduto.getPreco());
				  DadosConnect.ps.setString(5, aProduto.getLote());
				  java.sql.Date sqlDate = new java.sql.Date(aProduto.getDataProducao().getTime());
				  DadosConnect.ps.setDate(6, sqlDate);
				  DadosConnect.ps.setInt(7, aProduto.getSku());
				  
				  
				  DadosConnect.ps.executeUpdate();
				  
	        } catch (SQLException e) {
	        	System.out.println("!! SQL Exception !!\n"+e);
	        	DadosConnect.desliga();
	           	e.printStackTrace();
			} 
	        
	        DadosConnect.desliga();
	}

	/**
	 * Metodo para condicionar o armazenista a inserir produtos no sistema que estejam com o seu stock negativo
	 * assim se o stock de algum produto estiver negativo não é possivel o armazenista realizar pedidos até regularizar o stock dos produtos
	 * 
	 * @return inteiro com a contagem do numero de produtos que tem stock negativo
	 */
	public static int verificaStocks() {
		int conta = 0;
        
  	  
	    StringBuffer sqlQuery = new StringBuffer();
		
	    DadosConnect.conecta();
			  
	    sqlQuery.append(" SELECT COUNT(*) AS \"Contador\" FROM produto "); 
	    sqlQuery.append(" WHERE QUANTIDADE_PRODUTO < 0 ; ");
	    
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
