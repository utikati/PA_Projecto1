package Produtos;



import AcessoBD.DadosEncomendas;
import AcessoBD.DadosNotificacao;
import AcessoBD.DadosProdutos;
import AcessoBD.DadosUtilizadores;
import DadosEstaticos.DadosStatic;
import InterfacePrincipal.*;
import Utilizadores.MetodoStaticUtilizadores;
import Aviso.*;
import PedidosNotificacoes.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Jorge Martins
 * 
 */
public class MetodoStaticEncomendas {
	
	
//Criar uma encomenda	
	/**
	 * Metodo de Criação de uma encomenda
	 */
	public static void criarEncomenda() {
		ArrayList<Encomenda_Produto> encomendaProduto = menu(); //criar um arraylist com os produtos e suas quantidades (estas tabelas iniciam-se com este dados)
		if(encomendaProduto.size() > 0) {
			int custo = geraCustoEncomenda(encomendaProduto);
			Date dataCriacao = new Date();
			String estado = "iniciada";
			SimpleDateFormat formatoData = new SimpleDateFormat("yyyyMMddHHmmss"); //criar o formato para o identificador
			String parteFinalIdentificador = formatoData.format(dataCriacao.getTime()); //cria logo a parte final do forma do identificador
			
			String identificador = String.valueOf(DadosEncomendas.maxIdEncomenda() + 1) + parteFinalIdentificador; //como é auto increment o pedido será o maximo mais 1 para juntar no identificador
			DadosEncomendas.adicionarEncomenda(new Encomenda(identificador, custo, dataCriacao, estado, Integer.parseInt(DadosUtilizadores.verificaLogin(DadosStatic.Login))));
			
			geradorTabelasEncomendaProduto(encomendaProduto, DadosEncomendas.maxIdEncomenda()); // apos adicionar a encomenda o id dela será o maximo existente na tabela pois ela é auto increment e já foi adicionada
			
			autoActualizaStock(encomendaProduto); //este metodo vai actualizar todo o stock retirando a quantidade em relação à encomenda
			
			Aviso.operacaoSucesso();
		}else {
			Aviso.operacaoInsucesso();
		}
		
		
	}

//estados de encomenda
	
	/**
	 * Metodo com os estados possiveis de uma encomenda
	 * Modo automatico para não se tentar usar um estado não possivel numa encomenda
	 */
	public static void apresentaEstadoPossiveis() {
		System.out.println("1 - iniciada \n"
				+ "2 - aceite\n"
				+ "3 - preparada \n"
				+ "4 - aguarda entrega \n"
				+ "5 - em transporte \n"
				+ "6 - entregue \n"
				+ "7 - confirmada \n"
				+ "8 - rejeitada");
	}
	
	/**
	 * @param opcao
	 * @return String com o estado possivel através da opção escolhida
	 */
	public static String estadoEncomenda(int opcao) {
		String estado = new String();
		switch(opcao) {
		case 1: estado = "iniciada"; break;
		case 2: estado = "aceite"; break;
		case 3: estado = "preparada"; break;
		case 4: estado = "aguarda entrega"; break;
		case 5: estado = "em transporte"; break;
		case 6: estado = "entregue"; break;
		case 7: estado = "confirmada"; break;
		case 8: estado = "rejeitada"; break;
		}
		return estado;
	}


 /**
 * @return ArrayList com objectos Encomenda_Produto
 * Metodo de inserção dos produtos na encomenda
 */
public static ArrayList<Encomenda_Produto> menu(){
	ArrayList <Encomenda_Produto> encomendaProduto = new ArrayList <Encomenda_Produto> ();
	int opcao = 0;
	while(opcao != 3) {
		// apresentar menu
		apresentaMenuInserirProdutos();
		// escolha opçao
		opcao = Principal.pedeDadosInteiro("Escolha a opção do menu inserindo um numero inteiro da opçao", DadosStatic.teclado);
		//executar opção
		executaOpcaoProdutos(opcao, encomendaProduto);
	}
	return encomendaProduto;
}

//Inserir produtos na encomenda
/**
 * Listagens de opções no menu de inserir produtos
 */
private static void apresentaMenuInserirProdutos() {
	System.out.println("1 -   Insira produto e quantidade na encomenda\n" +
						"2 -  Listar Produtos Disponiveis\n" +
						"3 -  Terminar de Adicionar Produtos\n");
}

//OPCOES
/**
 * @param aOpcao
 * @param encomendaProduto
 * Metodo de execução da opção escolhida pelo utilizador
 */
private static void executaOpcaoProdutos(int aOpcao, ArrayList<Encomenda_Produto>encomendaProduto) {
	switch(aOpcao) {
	case 1: int sku = Principal.pedeDadosInteiro("Insira o Sku do produto a adicionar ", DadosStatic.teclado);
			if(DadosProdutos.idProduto(sku) > 0) {
				int quantidade = Principal.pedeDadosInteiro("Insira a quantidade de produto", DadosStatic.teclado);
				encomendaProduto.add(new Encomenda_Produto(DadosProdutos.idProduto(sku), quantidade));
			}else {
				System.out.println("Produto não existe \n");
			} break;
	case 2: MetodoStaticProduto.condicaoProduto(1, "");
	case 3: break;
	default: Principal.opcaoErrada(); break;
	}
}

/**
 * @param encomendaProduto
 * @return inteiro com custo total da encomenda
 */
public static int geraCustoEncomenda(ArrayList<Encomenda_Produto>encomendaProduto) {
	
	int custo = 0;
	
	Iterator <Encomenda_Produto> tabela = encomendaProduto.iterator();
	Encomenda_Produto auxiliar;
       while(tabela.hasNext()) {
	       auxiliar = tabela.next();
	       custo += DadosProdutos.pesquisaProdutoPorID(auxiliar.getIdProduto()).getPreco() * auxiliar.getQuantidadeProduto();
       }
	
	return custo;
}

/**
 * @param encomendaProduto
 * @param idEncomenda
 * @return boolean
 * Adiciona as tabelas de encomenda_produto relativas à encomenda na base de dados
 */
public static boolean geradorTabelasEncomendaProduto(ArrayList<Encomenda_Produto> encomendaProduto, int idEncomenda) {

	
	Iterator <Encomenda_Produto> tabela = encomendaProduto.iterator();
	Encomenda_Produto auxiliar;
       while(tabela.hasNext()) {
	       auxiliar = tabela.next();
	       DadosEncomendas.adicionaTabelaEncomendaProduto(auxiliar, idEncomenda);
       }
	return true;
}


/**
 * @param encomendaProduto
 * Actualiza o stock de cada produto inserido na encomenda
 */
public static void autoActualizaStock(ArrayList <Encomenda_Produto> encomendaProduto) {
		
		Iterator <Encomenda_Produto> tabela = encomendaProduto.iterator();
		Encomenda_Produto auxiliar;
	       while(tabela.hasNext()) {
		       auxiliar = tabela.next();
		       Produto produto = DadosProdutos.pesquisaProdutoPorID(auxiliar.getIdProduto());
		       produto.setQuantidade(produto.getQuantidade() - auxiliar.getQuantidadeProduto());
		      DadosProdutos.updateProduto(produto); 
	       }
	}

/**
 * @param encomendaProduto
 * Actualiza o stock de cada produto de uma encomenda que foi rejeitada por um gestor
 */
public static void autoActualizaStockRejeita(ArrayList <Encomenda_Produto> encomendaProduto) {
	
	Iterator <Encomenda_Produto> tabela = encomendaProduto.iterator();
	Encomenda_Produto auxiliar;
       while(tabela.hasNext()) {
	       auxiliar = tabela.next();
	       Produto produto = DadosProdutos.pesquisaProdutoPorID(auxiliar.getIdProduto());
	       produto.setQuantidade(produto.getQuantidade() + auxiliar.getQuantidadeProduto());
	      DadosProdutos.updateProduto(produto); 
       }
       
}

//------------------------------------------------------------------------------------------------------


//Listar encomendas

/**
 * @param aCondicao
 * Listar encomendas através de uma condição que será enviada dentro de uma query na base de dados
 */
public static void listarCondicao(String aCondicao) {
	ArrayList <Encomenda> encomendas = DadosEncomendas.listarEncomendasCondicao(aCondicao);
	
	if(encomendas != null){
		Iterator <Encomenda> tabela = encomendas.iterator();
		String envio = "";
		Encomenda auxiliar;
		int contador = 0;
	       while(tabela.hasNext()) {
		       auxiliar = tabela.next();
		       envio += auxiliar;
		       contador++;		
		    
	       if(contador == 10) {
	    	   MetodoStaticUtilizadores.listadorMaster(" Encomendas \n" + envio + "\n", contador); //reutilizar o listador dos utilizadores em vez de repetir o metodo
	    	   contador = 0;
	    	   envio = "";
	       }
	       }
	       if(contador > 0) {
	    	   MetodoStaticUtilizadores.listadorMaster(" Encomendas \n" + envio + "\n", contador);
	       }else {
	    	   MetodoStaticUtilizadores.listadorMaster("Encomendas \nSem dados\n", 0);
	       }
	}
 }

/**
 * @param opcao
 * @param pesquisa
 * Metodo com algumas condições que são usadas no pedido à base de dados
 */
public static void condicaoEncomenda(int opcao, String pesquisa) {
	switch(opcao) {
	//OPCOES GESTOR
	case 1: listarCondicao(" ORDER BY DATACRIACAO_ENCOMENDA ASC"); break; //ascendente 
	case 2: listarCondicao(" ORDER BY DATACRIACAO_ENCOMENDA DESC"); break;//descendente de
	case 3: listarCondicao(" ORDER BY CLI_ID_UTILIZADOR ASC"); break; //ascendente 
	case 4: listarCondicao(" ORDER BY CLI_ID_UTILIZADOR DESC "); break; //descendente 
	case 5: listarCondicao(" WHERE ESTADO_ENCOMENDA != 'entregue' AND ESTADO_ENCOMENDA != 'confirmada' ORDER BY DATACRIACAO_ENCOMENDA ASC "); break; 
	case 6: listarCondicao(" WHERE ESTADO_ENCOMENDA != 'entregue' AND ESTADO_ENCOMENDA != 'confirmada' ORDER BY DATACRIACAO_ENCOMENDA DESC "); break;
	case 7: listarCondicao(" WHERE DATACRIACAO_ENCOMENDA = "+pesquisa+" "); break; 
	case 8: listarCondicao(" WHERE IDENTIFICADOR_ENCOMENDA = '"+pesquisa+"' "); break; 
	case 9: listarCondicao(" WHERE ESTADO_ENCOMENDA = '"+pesquisa+"' "); break; 
	case 10: listarCondicao(" WHERE CLI_ID_UTILIZADOR = "+Integer.parseInt(pesquisa)+" "); break; //recebe id em string e transforma em integer 
	case 11: listarCondicao(" WHERE DATACRIACAO_ENCOMENDA BETWEEN "+pesquisa+" "); break; 
	//outros utilizadores
	case 12: listarCondicao(" WHERE "+pesquisa+" = "+Integer.parseInt(DadosUtilizadores.verificaLogin(DadosStatic.Login))+" ORDER BY DATACRIACAO_ENCOMENDA ASC "); break; 
	case 13: listarCondicao(" WHERE "+pesquisa+" = "+Integer.parseInt(DadosUtilizadores.verificaLogin(DadosStatic.Login))+" ORDER BY DATACRIACAO_ENCOMENDA DESC "); break;
	case 14: listarCondicao(" WHERE "+pesquisa+" = "+Integer.parseInt(DadosUtilizadores.verificaLogin(DadosStatic.Login))+" ORDER BY IDENTIFICADOR_ENCOMENDA ASC "); break; 
	case 15: listarCondicao(" WHERE "+pesquisa+" = "+Integer.parseInt(DadosUtilizadores.verificaLogin(DadosStatic.Login))+" ORDER BY IDENTIFICADOR_ENCOMENDA DESC "); break;
	
	case 16: listarCondicao(pesquisa) ; break; //insiro o comando completo da maneira que escolher..
	
	
	
	}
}

//aceitar / rejeitar encomendas GESTORES!!!!
/**
 * Aceitar a encomenda por parte de um gestor do software
 */
public static void aceitarEncomenda() {
	String identificador = Principal.pedeDadosString("Insira o identificador da encomenda a aceitar", DadosStatic.teclado);
	Encomenda encomenda = DadosEncomendas.objEncomenda(identificador);
	if(DadosEncomendas.idEncomenda(identificador) > 0) {
		if(encomenda.getEstado().equals("iniciada")) {
			encomenda.setEstado(estadoEncomenda(2)); //chamo o metodo que muda o estado, tem um switch com opcoes
			encomenda.setIdUtilizador(Integer.parseInt(DadosUtilizadores.verificaLogin(DadosStatic.Login)));
			encomenda.setDataAceitacao(new Date());
			DadosEncomendas.updateEncomenda(encomenda);
			Aviso.operacaoSucesso();
		}else {
			Aviso.encomendaNaoIniciada();
		}
	}else {
		Aviso.encomendaNaoIniciada();
	}
}

/**
 * Rejeitar a encomenda por parte de um gestor de software
 */
public static void rejeitarEncomenda() {
	String identificador = Principal.pedeDadosString("Insira o identificador da encomenda a aceitar", DadosStatic.teclado);
	Encomenda encomenda = DadosEncomendas.objEncomenda(identificador);
	
		if(DadosEncomendas.idEncomenda(identificador)>0) {
			if(encomenda.getEstado().equals("iniciada")) {
				encomenda.setEstado(estadoEncomenda(8)); //chamo o metodo que muda o estado, tem um switch com opcoes
				encomenda.setIdUtilizador(Integer.parseInt(DadosUtilizadores.verificaLogin(DadosStatic.Login)));
				DadosEncomendas.updateEncomenda(encomenda);
				autoActualizaStockRejeita(DadosEncomendas.listaEncomenda_Produto("WHERE ID_ENCOMENDA = "+DadosEncomendas.idEncomenda(encomenda.getIdentificadorEncomenda())+" "));
				Notificacao noti = new Notificacao(encomenda.getIdEncomenda(), encomenda.getIdCliente(), "activa", "Notificação - Encomenda com identificador "+identificador+" foi rejeitada por um gestor");
				DadosNotificacao.adicionaNotifica(noti);
				Aviso.operacaoSucesso();
			}else {
				Aviso.encomendaNaoIniciada();
			}
		}else {
			Aviso.encomendaNaoIniciada();
		}
}

/**
 * Menu do gestor para aceitar ou rejeitar encomenda
 */
public static void menuAceitaRejeitaEncomenda() {
	condicaoEncomenda(16, " WHERE ESTADO_ENCOMENDA = 'iniciada' ");
	System.out.println("1 - Aceitar uma Encomenda\n2 - Rejeitar uma Encomenda\n3-Sair\n");
	int opcao = Principal.pedeDadosInteiro("Insira a sua opcao", DadosStatic.teclado);
	switch(opcao) {
		case 1: aceitarEncomenda(); break;
		case 2: rejeitarEncomenda(); break;
		case 3: break;
		default: Principal.opcaoErrada(); break;
	}
}

//delegar ao Armazenista

/**
 * Metodo usado pelo gestor para delegar uma encomenda ao armazenista
 */
public static void delegarEncomendaArmazenista() {
	condicaoEncomenda(9, "aceite");
	String identificador = Principal.pedeDadosString("Indique o identificador da encomenda para delegação", DadosStatic.teclado);
	Encomenda encomenda = DadosEncomendas.objEncomenda(identificador);
	if(DadosEncomendas.idEncomenda(identificador) > 0) {
		if(encomenda.getEstado().equals("aceite")) {
			MetodoStaticUtilizadores.listarUtilizadoresSemFiltro("TIPO_UTILIZADOR = 'Funcionario' ");
			String login = Principal.pedeDadosString("Insira o Login do Funcionario Armazenista a Delegar Encomenda", DadosStatic.teclado);
			
			if(!DadosUtilizadores.verificaLogin(login).equals("")) {
				if(DadosUtilizadores.pesquisaLogin(login).getTipo().equals("Funcionario") && DadosUtilizadores.pesquisaFuncionarios(login).getEspecializacao().equals("Armazenista")) {
					encomenda.setIdArmazenista(Integer.parseInt(DadosUtilizadores.verificaLogin(login)));
					encomenda.setEstado("preparada");
					DadosEncomendas.updateEncomenda(encomenda);	
					Notificacao noti = new Notificacao(encomenda.getIdEncomenda(), Integer.parseInt(DadosUtilizadores.verificaLogin(login)), "activa", "Notificação - Uma encomenda foi-lhe delegada pelo Gestor");
					DadosNotificacao.adicionaNotifica(noti);
					Aviso.operacaoSucesso();
				}else {
					Aviso.avisoErro();
				}
			}else {
				Aviso.avisoErro();
			}
		}else {
			Aviso.avisoErro();
		}
	}else {
		Aviso.avisoErro();
	}
	
}




//confirmar entrega de encomenda

/**
 * Metodo usado pelo estafeta para dar uma encomenda como entregue
 */
public static void entregaEncomendaEstafeta() {
	condicaoEncomenda(16, " WHERE FUN_ID_UTILIZADOR2 = "+DadosUtilizadores.verificaLogin(DadosStatic.Login)+" AND ESTADO_ENCOMENDA = 'em transporte' ");
			
	String identificador = Principal.pedeDadosString("Indique o identificador da encomenda para dar como entregue", DadosStatic.teclado);
	Encomenda encomenda = DadosEncomendas.objEncomenda(identificador);
	if(DadosEncomendas.idEncomenda(identificador) > 0) {
		if(encomenda.getEstado().equals("em transporte") && encomenda.getIdEstafeta() == Integer.parseInt(DadosUtilizadores.verificaLogin(DadosStatic.Login)) ) {
			encomenda.setEstado(estadoEncomenda(6));
			encomenda.setDataEntrega(new Date());
			DadosEncomendas.updateEncomenda(encomenda);
			Notificacao noti = new Notificacao(encomenda.getIdEncomenda(), encomenda.getIdCliente(), "activa", "Notificação - A sua encomenda ID"+encomenda.getIdEncomenda()+" foi entregue pelo estafeta \n");
			DadosNotificacao.adicionaNotifica(noti);
			Aviso.operacaoSucesso();
		}else {
			Aviso.avisoErro();
		}
	}else {
		Aviso.avisoErro();
	}
}

/**
 * Metodo Usado pelo cliente para confirmar a entregua do estafeta da encomenda
 */
public static void confirmaEncomendaCliente() {
	condicaoEncomenda(16, " WHERE CLI_ID_UTILIZADOR = "+DadosUtilizadores.verificaLogin(DadosStatic.Login)+" AND ESTADO_ENCOMENDA = 'entregue' ");
			
	String identificador = Principal.pedeDadosString("Indique o identificador da encomenda a confirmar", DadosStatic.teclado);
	Encomenda encomenda = DadosEncomendas.objEncomenda(identificador);
	if(DadosEncomendas.idEncomenda(identificador) > 0) {
		if(encomenda.getEstado().equals("entregue") && encomenda.getIdCliente() == Integer.parseInt(DadosUtilizadores.verificaLogin(DadosStatic.Login)) ) {
			encomenda.setEstado(estadoEncomenda(7));
			DadosEncomendas.updateEncomenda(encomenda);
			Notificacao noti = new Notificacao(encomenda.getIdEncomenda(), encomenda.getIdUtilizador(), "activa", "Notificação - A sua encomenda ID"+encomenda.getIdEncomenda()+" foi confirmada pelo cliente \n");
			DadosNotificacao.adicionaNotifica(noti);
			Aviso.operacaoSucesso();
		}else {
			Aviso.avisoErro();
		}
	}else {
		Aviso.avisoErro();
	}
}

//aviso ao gestor responsavel pelos 10 dias de não ser entregue a encomenda
/**
 * Metodo usado para notificar o gestor responsavel que uma encomenda está atrasada na sua entrega
 */
public static void AvisoAtrasoEncomenda() {
	if(DadosEncomendas.EncomendasForaDeTempo() > 0) {
		System.out.println("Notificação - Existem encomendas da sua responsabilidade que não foram entregues em 10 dias!!!!!");
	}
}


}