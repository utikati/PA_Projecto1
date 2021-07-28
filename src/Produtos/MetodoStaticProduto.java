package Produtos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import Utilizadores.*;
import AcessoBD.DadosProdutos;

import DadosEstaticos.DadosStatic;
import InterfacePrincipal.Principal;

import Aviso.*;

/**
 * @author Jorge Martins
 *
 */
public class MetodoStaticProduto {
	
	/**
	 * Metodo para armazenista criar e inserir produto na base de dados
	 */
	public static void criarProduto() {
		boolean state = false;
		String designacao = "";
		String Fabricante = "";
		int quantidade = 0;
		float preco = 0;
		int sku = 0;
		String lote = "";
		Date dataProducao = new Date();
		Random rnd = new Random();
		designacao = Principal.pedeDadosString("Insira a descrição do produto ", DadosStatic.teclado);
		Fabricante = Principal.pedeDadosString("Insira o fabricante do produto", DadosStatic.teclado);
		quantidade = Principal.pedeDadosInteiro("Insira a quantidade do produto", DadosStatic.teclado);
		preco = Principal.pedeDadosFloat("Insira o preço do produto", DadosStatic.teclado);
		do {
			sku = rnd.nextInt(1000001);
			if(sku == 0) {
				sku = 1;
			}
			if(DadosProdutos.idProduto(sku) > 0) { //se for maior que 0 quer dizer que já existe
				state = true;
			}
		}while(state);
		lote = Principal.pedeDadosString("Insira o lote do produto", DadosStatic.teclado);
		
		do {
			state = false;
			String data = Principal.pedeDadosString("\nInsira a data de produção do Produto com dd/MM/yyyy", DadosStatic.teclado);
			SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
			formatoData.setLenient(false);
			try {
				dataProducao = formatoData.parse(data);
			}catch(ParseException e) {
				try {
					dataProducao = formatoData.parse("01/01/1970");
				}catch(ParseException e2) {
					e.printStackTrace();
				}
				System.out.println("Formato de data não aceite, tente de novo \n");
				state = true;
			}
		}while(state);
		
		state = true; 
		String designacaoCategoria = Principal.pedeDadosString("Insira a designacao da categoria do produto", DadosStatic.teclado);
			
		if(DadosProdutos.idCategoria(designacaoCategoria) == 0) { //pensei em fazer um loop mas como o utilizador depois iria pesquisar sobre uma categoria existente..
			Aviso.naoExistenciaCategoria();
			state = false;
		}
		if(state) {
			DadosProdutos.adicionarProduto(new Produto(designacao, Fabricante, quantidade, preco, sku, lote, dataProducao, DadosProdutos.pesquisaCategoria(designacaoCategoria)));
			Aviso.operacaoSucesso();
		}else {
			Aviso.operacaoInsucesso();
		}
	}

	/**
	 * Metodo para armazenista criar e inserir categoria na base de dados
	 */
	public static void criarCategoria() {
		boolean state = true;
		String designacao = "";
		String classificacao = "";
		do {
			state = false;
			designacao = Principal.pedeDadosString("Insira a designacao da Categoria", DadosStatic.teclado);
			if(DadosProdutos.idCategoria(designacao) > 0) {
				state = true;
				Aviso.existenciaCategoria();
			}
		}while(state);
		
		classificacao = Principal.pedeDadosString("Insira a classificação", DadosStatic.teclado);
		
		DadosProdutos.adicionarCategoria(new Categoria(designacao, classificacao));
		Aviso.operacaoSucesso();
		
	}

	
	/**
	 * @param aCondicao
	 * Metodo para listar produtos através de condição na segunda parte da query (ex WHERE NOME LIKE %CONDICAO%)
	 */
	public static void listarCondicao(String aCondicao) {
		ArrayList <Produto> produtos = DadosProdutos.listarProdutosCondicao(aCondicao);
		
		if(produtos != null){
			Iterator <Produto> tabela = produtos.iterator();
			String envio = "";
			Produto auxiliar;
			int contador = 0;
		       while(tabela.hasNext()) {
			       auxiliar = tabela.next();
			       envio += auxiliar;
			       contador++;		
			    
		       if(contador == 10) {
		    	   MetodoStaticUtilizadores.listadorMaster(" Produtos \n" + envio + "\n", contador); //reutilizar o listador dos utilizadores em vez de repetir o metodo
		    	   contador = 0;
		    	   envio = "";
		       }
		       }
		       if(contador > 0) {
		    	   MetodoStaticUtilizadores.listadorMaster(" Produtos \n" + envio + "\n", contador);
		       }else {
		    	   MetodoStaticUtilizadores.listadorMaster("Produtos \nSem dados\n", 0);
		       }
		}
	 }
	
	/**
	 * @param opcao
	 * @param pesquisa
	 * Metodo com algumas das condições (requisitos) para pesquisa e listagem
	 */
	public static void condicaoProduto(int opcao, String pesquisa) {
		switch(opcao) {
		case 1: listarCondicao(" ORDER BY DESIGNACAO_PRODUTO ASC"); break; //ascendente de designacao produto
		case 2: listarCondicao(" ORDER BY DESIGNACAO_PRODUTO DESC"); break;//descendente de designacao produto
		case 3: listarCondicao(" ORDER BY ID_CATEGORIA ASC"); break; //ascendente o numero da categoria id dela
		case 4: listarCondicao(" ORDER BY ID_CATEGORIA DESC "); break; //descendente o id da categoria
		case 5: listarCondicao(" WHERE DESIGNACAO_PRODUTO LIKE '%"+pesquisa+"%'"); break;
		case 6: listarCondicao(" WHERE ID_CATEGORIA = "+Integer.parseInt(pesquisa)+" "); break; //PEDIR A DESIGNACAO DA CATEGORIA E ENVIAR PARA AQUI O ID DELA
		case 7: listarCondicao(" WHERE QUANTIDADE_PRODUTO < "+Integer.parseInt(pesquisa)+" "); break;
		
		}
	}
	
	/**
	 * Listar todas as categorias existentes
	 */
	public static void listarCategorias() {
		ArrayList <Categoria> categoria = DadosProdutos.listarCategorias();
		
		if(categoria != null){
			
			Iterator <Categoria> tabela = categoria.iterator();
			String envio = "";
			Categoria auxiliar;
			int contador = 0;
		       while(tabela.hasNext()) {
			       auxiliar = tabela.next();
			       envio += auxiliar;
			       contador++;	
			       
			    }
		       if(contador == 10) {
		    	   MetodoStaticUtilizadores.listadorMaster(" Categorias \n" + envio + "\n", contador); //reutilizar o listador dos utilizadores em vez de repetir o metodo
		    	   contador = 0;
		    	   envio = "";
		       }
		       if(contador > 0) {
		    	   MetodoStaticUtilizadores.listadorMaster(" Categorias  \n" + envio + "\n", contador);
		    	   
		       }else {
		    	   MetodoStaticUtilizadores.listadorMaster("Categorias \nSem dados\n", 0);
		       }
		}else {
			MetodoStaticUtilizadores.listadorMaster("Categorias \nSem dados\n", 0);
			
		}
		
	 }
	
	/**
	 * Metodo para Adicionar a quantidade de um produto (manipulação de stock do produto)
	 */
	public static void adicionarQuantidade() {
		int sku = Principal.pedeDadosInteiro("Indique condigo sku do produto", DadosStatic.teclado);
		if(DadosProdutos.idProduto(sku) > 0) {
			Produto produto = DadosProdutos.pesquisaProduto(sku);
			System.out.println("Stock actual "+produto.getQuantidade()+"\n");
			int quantidade = Principal.pedeDadosInteiro("Insira a quantidade a adicionar ao stock", DadosStatic.teclado);
			produto.setQuantidade(quantidade + produto.getQuantidade());
			System.out.println("Stock final "+produto.getQuantidade()+"\n");
			DadosProdutos.updateProduto(produto);
			Aviso.operacaoSucesso();
		}else {
			System.out.println("Produto não existe");
		}
	}

	/**
	 * @param dataInicioString
	 * @return objecto DATE
	 * Recebe data em forma de string e a transforma em DATE
	 */
	public static Date StringParaDate(String dataInicioString) {
		Date data = new Date();
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		try {
			data = formatoData.parse(dataInicioString);
		}catch(ParseException e) {
			try {
				data = formatoData.parse("01/01/1970");
			}catch(ParseException e2) {
				e.printStackTrace();
			}
			System.out.println("Formato de data não aceite, tente de novo \n");
		}
		return data;
	}
}

	
	

