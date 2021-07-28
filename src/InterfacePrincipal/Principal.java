package InterfacePrincipal;
import java.util.Scanner;


import AcessoBD.DadosProdutos;
import AcessoBD.DadosUtilizadores;
import DadosEstaticos.DadosStatic;
import PedidosNotificacoes.MetodoStaticNotificacao;
import PedidosNotificacoes.MetodoStaticPedido;
import Produtos.MetodoStaticProduto;
import Utilizadores.MetodoStaticLogs;
import Utilizadores.MetodoStaticUtilizadores;

import Aviso.*;
import Produtos.*;
import Sistema.*;

/**
 * @author Jorge Martins
 * Classe onde se encontra o main e os menus principais do software
 */
public class Principal {

	/**
	 * @param args
	 * Main do software
	 */
	public static void main(String[] args) {
		//inicio do programa
		InterfaceLogin.primeiraCamada();
		System.out.println("Bem-vindo " + DadosStatic.UserON);
		int execucoes = DadosUtilizadores.contaExecuções();
		String data = DadosUtilizadores.ultimaData(); //tenho de inserir antes de começar os movimentos para retirar as informações correctas
		MetodoStaticLogs.movimentoUtilizador("Iniciou o Programa");
		TempoExecucao tempo = new TempoExecucao();
		
		
		if(DadosStatic.Tipo.equals("Gestor")) {
			MetodoStaticUtilizadores.notiNovasContas();
			MetodoStaticUtilizadores.notiContasApagar();
			MetodoStaticNotificacao.novasEncomendas();
			MetodoStaticEncomendas.AvisoAtrasoEncomenda();
		}
		if(DadosStatic.Tipo.equals("Cliente")) {
			MetodoStaticNotificacao.NotificacaoEncomenda();;
		}
		if(DadosStatic.Tipo.equals("Funcionario")) {
			MetodoStaticNotificacao.NotificacaoEncomenda();
		}
		System.out.println("Numero de execuções do programa: "+execucoes);
		if(!data.equals("null")) {
			System.out.println("Data da ultima inicialização do programa: "+data+"\n");
		}else {
			System.out.println("Data da ultima inicialização do programa: Sem Data a Demonstrar \n");
		}
		
		

		menu();

		MetodoStaticLogs.movimentoUtilizador("Terminou o Programa");
		tempo.setFim();
		System.out.println(tempo);
		System.out.println("Adeus " + DadosStatic.UserON);
		System.exit(0);

	}
	// MENU DO PROG..
	/**
	 * Menu Principal do Programa
	 */
	public static void menu(){
		int opcao = 0;
		while(opcao != 7) {
			// apresentar menu
			apresentaMenu();
			// escolha opçao
			opcao = pedeDadosInteiro("Escolha a opção do menu inserindo um numero inteiro da opçao", DadosStatic.teclado);
			//executar opção
			executaOpcaoUtilizadores(opcao);
		}
	}

	//LISTAGEM DE OPCOES
	/**
	 * Listagem das opções do Menu Principal
	 */
	private static void apresentaMenu() {
		System.out.println("1 -  Utilizadores\n" +
				"2 -  Produtos\n" +
				"3 -  Encomendas\n" +
				"4 -  Pedidos ao Estafeta\n" +
				"5 -  Pesquisas\n"+
				"6 -  Logs\n"+
				"7 -  Sair.\n" );
	}

	//OPCOES
	/**
	 * @param aOpcao
	 * Metodo onde são executadas as opções do utilizador para o Menu Principal
	 */
	private static void executaOpcaoUtilizadores(int aOpcao) {
		switch(aOpcao) {
		case 1: menuUtilizadores(); MetodoStaticLogs.movimentoUtilizador("Entrou no Menu Utilizadores"); break;
		case 2: menuProdutos(); MetodoStaticLogs.movimentoUtilizador("Entrou no Menu Produtos"); break;
		case 3: menuEncomendas(); MetodoStaticLogs.movimentoUtilizador("Entrou no Menu Encomendas"); break;
		case 4: menuPedido(); MetodoStaticLogs.movimentoUtilizador("Entrou no Menu Pedidos"); break;
		case 5: menuPesquisas(); MetodoStaticLogs.movimentoUtilizador("Entrou no Menu Pesquisas"); break;
		case 6: menuLogs(); MetodoStaticLogs.movimentoUtilizador("Entrou No menu logs"); break;
		case 7: break;
		default: opcaoErrada(); break;
		}
	}

	//Metodos para as opcoes do Menu e afins

	//--------------------------------------------------------------------------------------

	// SUBMENUS

	//Submenu Utilizadores-----------------------------------------------------------------------------

	/**
	 * Menu com opções para acções focadas em Utilizadores
	 */
	public static void menuUtilizadores(){
		int opcao = 0;
		while(opcao != 13) {
			// apresentar menu
			apresentaMenuUtilizadores();
			// escolha opçao
			opcao = pedeDadosInteiro("Escolha a opção do menu inserindo um numero inteiro da opçao", DadosStatic.teclado);
			//executar opção
			executaOpcao(opcao);
		}
	}

	//LISTAGEM DE OPCOES
	/**
	 * Listagem das opções das acções possiveis a realizar neste menu
	 */
	private static void apresentaMenuUtilizadores() {
		System.out.println("1 -  Criar Utilizador\n" +
				"2 -  Alterar Meus Dados\n" +
				"3 -  Alterar Dados de Utilizador\n" +
				"4 -  Alterar Estados de Contas de Utilizadores\n" +
				"5 -  Listar Todos Utilizadores Ordem Crescente\n" +
				"6 -  Listar Todos Utilizadores Ordem Decrescente\n" +
				"7 -  Listar Todos Utilizadores por nome Ordem Crescente\n" +
				"8 -  Listar Todos Utilizadores por nome Ordem Decrescente\n" +
				"9 -  Listar Todos Utilizadores Inactivos\n" +
				"10 -  Realizar pedido de Inactivação de Conta Corrente\n" +
				"11 -  Pedidos de Inactivação de Conta\n"+
				"12 -  Contas Novas\n"+
				"13 -  Sair.\n" );
	}

	//OPCOES
	/**
	 * @param aOpcao
	 * Metodo que executa as ações listadas 
	 */
	private static void executaOpcao(int aOpcao) {
		switch(aOpcao) {
		case 1: if(DadosStatic.Tipo.equals("Gestor")) {
			MetodoStaticUtilizadores.CriarUtilizador(false);
			MetodoStaticLogs.movimentoUtilizador("Escolheu a opcao Criar Utilizador");
		}
		else {
			Aviso.semPermissao();
			MetodoStaticLogs.movimentoUtilizador("Tentativa de entrar na opcao Criar utilizador");
		} break;
		case 2: MetodoStaticUtilizadores.menuMudancaUtilizador(); MetodoStaticLogs.movimentoUtilizador("Alterar Seus Dados Utilizador"); break;

		case 3: if(DadosStatic.Tipo.equals("Gestor")) {
			MetodoStaticUtilizadores.menuMudancaUtilizadorGestor();
			MetodoStaticLogs.movimentoUtilizador("Alteração Dados de um Utilizador");
		}
		else {
			Aviso.semPermissao();
			MetodoStaticLogs.movimentoUtilizador("Tentativa de alterar dados de um utilizador");
		} break;

		case 4: if(DadosStatic.Tipo.equals("Gestor")) {
					MetodoStaticUtilizadores.mudarEstadoContaUtilizador();
					MetodoStaticLogs.movimentoUtilizador("Alterar Estado de conta de um Utilizador");
				}
				else {
					Aviso.semPermissao();
					MetodoStaticLogs.movimentoUtilizador("Tentativa de Alterar um Estado de Conta de Utilizador");
				} break;

		case 5: if(DadosStatic.Tipo.equals("Gestor")) {
			MetodoStaticUtilizadores.listarTodosUtilizadores("ID_UTILIZADOR ASC");
			MetodoStaticLogs.movimentoUtilizador("Listar todos Utilizadores por ordem crescente");
		}
		else {
			Aviso.semPermissao();
			MetodoStaticLogs.movimentoUtilizador("Tentativa de ordenar todos utilizadores ordem crescente");
		}break;


		case 6: if(DadosStatic.Tipo.equals("Gestor")) {
			MetodoStaticUtilizadores.listarTodosUtilizadores("ID_UTILIZADOR DESC");
			MetodoStaticLogs.movimentoUtilizador("Listar todos utilizadores ordem decrescente");
		}
		else {
			Aviso.semPermissao();
			MetodoStaticLogs.movimentoUtilizador("Tentativa de listar todos utilizadores ordem decrescente");
		}break;

		case 7: if(DadosStatic.Tipo.equals("Gestor")) {
			MetodoStaticUtilizadores.listarTodosUtilizadores("NOME_UTILIZADOR ASC");
			MetodoStaticLogs.movimentoUtilizador("Listar utilizadores por nome crescente");
		}
		else {
			Aviso.semPermissao();
			MetodoStaticLogs.movimentoUtilizador("Tentativa de listar utilizadores nome crescente");
		}break;

		case 8: if(DadosStatic.Tipo.equals("Gestor")) {
			MetodoStaticUtilizadores.listarTodosUtilizadores("NOME_UTILIZADOR DESC ");
			MetodoStaticLogs.movimentoUtilizador("Listar utilizadores ordem decrescente");
		}
		else {
			Aviso.semPermissao();
			MetodoStaticLogs.movimentoUtilizador("Tentativa listar utilizadores ordem decrescente");
		}break;

		case 9: if(DadosStatic.Tipo.equals("Gestor")) {
			MetodoStaticUtilizadores.listarUtilizadoresFiltro("ESTADO_UTILIZADOR = 'inactivo' ");
			MetodoStaticLogs.movimentoUtilizador("Listar utilizadores inactivos");
		}
		else {
			Aviso.semPermissao();
			MetodoStaticLogs.movimentoUtilizador("Tentativa listar utilizadores inactivos");
		}break;

		case 10: MetodoStaticUtilizadores.pedidoRemocaoUtilizador(); MetodoStaticLogs.movimentoUtilizador("Pedido para remover conta"); break;

		case 11: if(DadosStatic.Tipo.equals("Gestor")) {
			MetodoStaticUtilizadores.pedidosRemocao();
			MetodoStaticLogs.movimentoUtilizador("Aceitar/rejeitar pedidos de remocao de conta");
		}
		else {
			Aviso.semPermissao();
			MetodoStaticLogs.movimentoUtilizador("Tentativa de verificar pedidos de remocao");
		}break;


		case 12: if(DadosStatic.Tipo.equals("Gestor")) {
			MetodoStaticUtilizadores.contasNovas();
			MetodoStaticLogs.movimentoUtilizador("Opcao de aceitar/rejeitar novas contas");
		}
		else {
			Aviso.semPermissao();
			MetodoStaticLogs.movimentoUtilizador("Tentativa de aceitar/rejeitar novas contas");
		} break;

		case 13: break;
		default: opcaoErrada(); break;
		}
	}	  
	//_______________________________________________________________________________________________________________________

	//SubMenu Pesquisas---------------------------------------------------------------------------

	/**
	 * Menu Pesquisas
	 * Onde se encontram as pesquisas a realizar no software (cerca de 90 %)
	 * 
	 */
	public static void menuPesquisas(){
		int opcao = 0;
		while(opcao != 12) {
			// apresentar menu
			apresentaMenuPesquisas();
			// escolha opçao
			opcao = pedeDadosInteiro("Escolha a opção do menu inserindo um numero inteiro da opçao", DadosStatic.teclado);
			//executar opção
			executaOpcaoPesquisas(opcao);
		}
	}

	//LISTAGEM DE OPCOES
	/**
	 * Listagem de opções de pesquisa
	 */
	private static void apresentaMenuPesquisas() {
		System.out.println("1  -  Pesquisar Utilizador pelo Login\n" +
				"2  -  Pesquisar Utilizador pelo Nome\n" +
				"3  -  Pesquisar Utilizador pelo Tipo\n" +
				"4  -  Pesquisar Encomendas Por Data de Criação\n" +
				"5  -  Pesquisar Encomendas Por Estado\n"+
				"6  -  Pesquisar Encomendas por Identificador\n"+
				"7  -  Pesquisar Encomendas por cliente\n"+
				"8  -  Pesquisar Encomendas por intervalo de tempo\n"+
				"9  -  Pesquisar SUAS Encomendas por Data de Criação\n"+
				"10 -  Pesquisar SUAS Encomendas por Identificador\n"+
				"11 -  Pesquisar SUAS Encomendas por Estado\n"+
				"12 -  Sair.\n" );
	}

	//OPCOES
	/**
	 * @param aOpcao
	 * Metodo que realiza a acção escolhida pelo utilizador a pesquisar
	 */
	private static void executaOpcaoPesquisas(int aOpcao) {
		String pesquisa = "";
		switch(aOpcao) {
		case 1: if(DadosStatic.Tipo.equals("Gestor")) {
			MetodoStaticUtilizadores.pesquisaPorLogin();
			MetodoStaticLogs.movimentoUtilizador("Pesquisa Utilizador Login");
		}else {
			Aviso.semPermissao();
			MetodoStaticLogs.movimentoUtilizador("Tentativa Pesquisa Utilizador login");
		}
		break;

		case 2: if(DadosStatic.Tipo.equals("Gestor")) {
			MetodoStaticUtilizadores.pesquisaPorNome();
			MetodoStaticLogs.movimentoUtilizador("Pesquisa Utilizador Nome");
		}else {
			Aviso.semPermissao();
			MetodoStaticLogs.movimentoUtilizador("Tentativa pesquisa Utilizador nome");
		}
		break;

		case 3: if(DadosStatic.Tipo.equals("Gestor")) {
			MetodoStaticUtilizadores.pesquisaPorTipo();
			MetodoStaticLogs.movimentoUtilizador("Pesquisa utilizador por tipo");
		}else {
			Aviso.semPermissao();
			MetodoStaticLogs.movimentoUtilizador("Tentativa pesquisa utilizador por tipo");
		}
		break;

		case 4: if(DadosStatic.Tipo.equals("Gestor")) {
					String dia = pedeDadosString("\nInsira o dia (ex 02 ou 11 use sempre dois algarismos)", DadosStatic.teclado);
					String mes = pedeDadosString("\nInsira o mes (ex 01 ou 10 use sempre dois algarismos)", DadosStatic.teclado);
					String ano = pedeDadosString("\nInsira o ano (2021 ou 2012 use sempre quatro algarismos", DadosStatic.teclado);
			        pesquisa ="'"+ ano+"-"+mes+"-"+dia+"'";
					MetodoStaticEncomendas.condicaoEncomenda(7, pesquisa);
					MetodoStaticLogs.movimentoUtilizador("Pesquisa geral encomenda por data");
				}else {
					Aviso.semPermissao();
					MetodoStaticLogs.movimentoUtilizador("Tentativa de pesquisa geral de encomenda por data");
				} break;
		case 5: if(DadosStatic.Tipo.equals("Gestor")) {
					MetodoStaticEncomendas.apresentaEstadoPossiveis();
					int opcao = pedeDadosInteiro("Insira o estado da encomenda ", DadosStatic.teclado);
					pesquisa = MetodoStaticEncomendas.estadoEncomenda(opcao);
					MetodoStaticEncomendas.condicaoEncomenda(9, pesquisa);
					MetodoStaticLogs.movimentoUtilizador("Pesquisa geral de encomenda por estado");
				}else {
					Aviso.semPermissao();
					MetodoStaticLogs.movimentoUtilizador("Tentativa de pesquisa geral por estado");
				} break;	
		case 6: if(DadosStatic.Tipo.equals("Gestor")) {
					pesquisa = pedeDadosString("Insira o Identificador de encomenda a pesquisar ", DadosStatic.teclado);
					MetodoStaticEncomendas.condicaoEncomenda(8, pesquisa);
					MetodoStaticLogs.movimentoUtilizador("Pesquisa geral encomenda Identificador");
				}else {
					Aviso.semPermissao();
					MetodoStaticLogs.movimentoUtilizador("Tentativa geral encomenda Identificador");
				} break;
		case 7: if(DadosStatic.Tipo.equals("Gestor")) {
					pesquisa = pedeDadosString("Insira o login de cliente a pesquisar nas encomendas ", DadosStatic.teclado);
					MetodoStaticEncomendas.condicaoEncomenda(10, DadosUtilizadores.verificaLogin(pesquisa)); //envia o id em string
					MetodoStaticLogs.movimentoUtilizador("Pesquisa geral encomenda cliente");
				}else {
						Aviso.semPermissao();
						MetodoStaticLogs.movimentoUtilizador("Tentativa pesquisa geral encomenda cliente");
						} break;
		case 8:  if(DadosStatic.Tipo.equals("Gestor")) {
						System.out.println("Data de Inicio");
						String dia = pedeDadosString("\nInsira o dia (ex 02 ou 11 use sempre dois algarismos)", DadosStatic.teclado);
						String mes = pedeDadosString("\nInsira o mes (ex 01 ou 10 use sempre dois algarismos)", DadosStatic.teclado);
						String ano = pedeDadosString("\nInsira o ano (2021 ou 2012 use sempre quatro algarismos", DadosStatic.teclado);
				        String data1 ="'"+ ano+"-"+mes+"-"+dia+"'";
				        System.out.println("Data Final");
				        dia = pedeDadosString("\nInsira o dia (ex 02 ou 11 use sempre dois algarismos)", DadosStatic.teclado);
						mes = pedeDadosString("\nInsira o mes (ex 01 ou 10 use sempre dois algarismos)", DadosStatic.teclado);
						ano = pedeDadosString("\nInsira o ano (2021 ou 2012 use sempre quatro algarismos", DadosStatic.teclado);
						String data2 ="'"+ ano+"-"+mes+"-"+dia+"'";
						pesquisa = data1+" AND "+data2;
						MetodoStaticEncomendas.condicaoEncomenda(11, pesquisa);
						MetodoStaticLogs.movimentoUtilizador("Pesquisa geral encomenda intervalo datas");
					}else {
						Aviso.semPermissao();
						MetodoStaticLogs.movimentoUtilizador("Tentativa pesquisa geral encomenda intervalo datas");
					} break;	
		case 9: if(DadosStatic.Tipo.equals("Cliente") || DadosStatic.Tipo.equals("Funcionario")) {
					String dia = pedeDadosString("\nInsira o dia (ex 02 ou 11 use sempre dois algarismos)", DadosStatic.teclado);
					String mes = pedeDadosString("\nInsira o mes (ex 01 ou 10 use sempre dois algarismos)", DadosStatic.teclado);
					String ano = pedeDadosString("\nInsira o ano (2021 ou 2012 use sempre quatro algarismos", DadosStatic.teclado);
			        if(DadosStatic.Tipo.equals("Cliente")) {
			        	pesquisa ="WHERE DATACRIACAO_ENCOMENDA = '"+ ano+"-"+mes+"-"+dia+"' AND CLI_ID_UTILIZADOR = "+DadosUtilizadores.verificaLogin(DadosStatic.Login)+"";
						MetodoStaticEncomendas.condicaoEncomenda(16, pesquisa);	
						MetodoStaticLogs.movimentoUtilizador("Pesquisa suas encomendas por data");
			        }else {
			        	if(DadosStatic.especializacao.equals("Armazenista")) {
			        		pesquisa ="WHERE DATACRIACAO_ENCOMENDA = '"+ ano+"-"+mes+"-"+dia+"' AND FUN_ID_UTILIZADOR = "+DadosUtilizadores.verificaLogin(DadosStatic.Login)+"";
							MetodoStaticEncomendas.condicaoEncomenda(16, pesquisa);
							MetodoStaticLogs.movimentoUtilizador("Pesquisa suas encomendas por data");
			        	}else {
			        		pesquisa ="WHERE DATACRIACAO_ENCOMENDA = '"+ ano+"-"+mes+"-"+dia+"' AND FUN_ID_UTILIZADOR2 = "+DadosUtilizadores.verificaLogin(DadosStatic.Login)+"";
							MetodoStaticEncomendas.condicaoEncomenda(16, pesquisa);
							MetodoStaticLogs.movimentoUtilizador("Pesquisa suas encomendas por data");
			        	}
			        	
			        }
					
				}else {
					Aviso.semPermissao();
					MetodoStaticLogs.movimentoUtilizador("Pesquisa interdita a gestor");
				} break;
		case 10: if(DadosStatic.Tipo.equals("Cliente") || DadosStatic.Tipo.equals("Funcionario")) {
					String identificador = pedeDadosString("Insira o identificador da encomenda ", DadosStatic.teclado);
			        if(DadosStatic.Tipo.equals("Cliente")) {
			        	pesquisa ="WHERE IDENTIFICADOR_ENCOMENDA = '"+identificador+"' AND CLI_ID_UTILIZADOR = "+DadosUtilizadores.verificaLogin(DadosStatic.Login)+"";
						MetodoStaticEncomendas.condicaoEncomenda(16, pesquisa);	
						MetodoStaticLogs.movimentoUtilizador("Pesquisa suas encomendas por identificador");
			        }else {
			        	if(DadosStatic.especializacao.equals("Armazenista")) {
			        		pesquisa ="WHERE IDENTIFICADOR_ENCOMENDA = '"+identificador+"' AND FUN_ID_UTILIZADOR = "+DadosUtilizadores.verificaLogin(DadosStatic.Login)+"";
							MetodoStaticEncomendas.condicaoEncomenda(16, pesquisa);
							MetodoStaticLogs.movimentoUtilizador("Pesquisa suas encomendas por identificador");
			        	}else {
			        		pesquisa ="WHERE IDENTIFICADOR_ENCOMENDA = '"+identificador+"' AND FUN_ID_UTILIZADOR2 = "+DadosUtilizadores.verificaLogin(DadosStatic.Login)+"";
							MetodoStaticEncomendas.condicaoEncomenda(16, pesquisa);
							MetodoStaticLogs.movimentoUtilizador("Pesquisa suas encomendas por identificador");
			        	}
			        	
			        }
					
				}else {
					Aviso.semPermissao();
					MetodoStaticLogs.movimentoUtilizador("Pesquisa interdita a gestor");
				} break;
		case 11: if(DadosStatic.Tipo.equals("Cliente") || DadosStatic.Tipo.equals("Funcionario")) {
					MetodoStaticEncomendas.apresentaEstadoPossiveis();
					int opcao = pedeDadosInteiro("Insira o estado da encomenda ", DadosStatic.teclado);
					String estado = MetodoStaticEncomendas.estadoEncomenda(opcao);
					MetodoStaticLogs.movimentoUtilizador("Pesquisa suas encomendas por estado");
			        if(DadosStatic.Tipo.equals("Cliente")) {
			        	pesquisa ="WHERE IDENTIFICADOR_ENCOMENDA = '"+estado+"' AND CLI_ID_UTILIZADOR = "+DadosUtilizadores.verificaLogin(DadosStatic.Login)+"";
						MetodoStaticEncomendas.condicaoEncomenda(16, pesquisa);	
			        }else {
			        	if(DadosStatic.especializacao.equals("Armazenista")) {
			        		pesquisa ="WHERE IDENTIFICADOR_ENCOMENDA = '"+estado+"' AND FUN_ID_UTILIZADOR = "+DadosUtilizadores.verificaLogin(DadosStatic.Login)+"";
							MetodoStaticEncomendas.condicaoEncomenda(16, pesquisa);
			        	}else {
			        		pesquisa ="WHERE IDENTIFICADOR_ENCOMENDA = '"+estado+"' AND FUN_ID_UTILIZADOR2 = "+DadosUtilizadores.verificaLogin(DadosStatic.Login)+"";
							MetodoStaticEncomendas.condicaoEncomenda(16, pesquisa);
			        	}
			        	
			        }
					
				}else {
					Aviso.semPermissao();
					MetodoStaticLogs.movimentoUtilizador("Pesquisa interdita a gestor");
				} break;
		case 12: break;		
		default: opcaoErrada(); break;
		}
	}
	//---------------------------_____________________________________-----------------------------------------------

	//_______________________________________________________________________________________________________________________

	//SubMenu Produtos---------------------------------------------------------------------------

	/**
	 * Menu dos Produto
	 * Aqui encontram-se algumas opções de pesquisa para que o menu de pesquisa não ficasse demasiado grande
	 */
	public static void menuProdutos(){
		int opcao = 0;
		while(opcao != 12) {
			// apresentar menu
			apresentaMenuProdutos();
			// escolha opçao
			opcao = pedeDadosInteiro("Escolha a opção do menu inserindo um numero inteiro da opçao", DadosStatic.teclado);
			//executar opção
			executaOpcaoProdutos(opcao);
		}
	}

	//LISTAGEM DE OPCOES
	/**
	 * Listagem das acções possiveis a realizar no menu
	 */
	private static void apresentaMenuProdutos() {
		System.out.println("1  -  Criar Produto\n" +
				"2  -  Criar Categoria de Produto\n" +
				"3  -  Listar Categorias de Produto\n" +
				"4  -  Listar Produtos ordenados por Designação Ordem Crescente\n"+
				"5  -  Listar Produtos ordenados por Designação Ordem Decrescente\n"+
				"6  -  Listar Produtos por Categoria Ordem Crescente\n"+
				"7  -  Listar Produtos por Categoria Ordem Decrescente\n"+
				"8  -  Pesquisar Produtos por Designacao\n"+
				"9 -   Pesquisar Produtos por categoria\n"+
				"10 -  Pesquisar Produtos abaixo de determinado stock\n"+ 
				"11 -  Inserir Stock em Produto\n"+
				"12 -  Sair.\n" );
	}

	//OPCOES
	/**
	 * @param aOpcao
	 * Metodo que executa as ações listadas neste menu
	 */
	private static void executaOpcaoProdutos(int aOpcao) {
		String pesquisa = new String();
		switch(aOpcao) {
		case 1: if(DadosStatic.Tipo.equals("Funcionario") && DadosStatic.especializacao.equals("Armazenista") || DadosStatic.Tipo.equals("Gestor")) {
			MetodoStaticProduto.criarProduto();
			MetodoStaticLogs.movimentoUtilizador("Criou Produto");
		}else {
			MetodoStaticLogs.movimentoUtilizador("Tentativa criar produto");
			Aviso.semPermissao();
		}
		break;

		case 2: if(DadosStatic.Tipo.equals("Funcionario") && DadosStatic.especializacao.equals("Armazenista") || DadosStatic.Tipo.equals("Gestor")) {
			MetodoStaticProduto.criarCategoria();
			MetodoStaticLogs.movimentoUtilizador("Criou categoria de produto");
		}else {
			Aviso.semPermissao();
			MetodoStaticLogs.movimentoUtilizador("Tentativa de criar Categoria Produto");
		}
		break;

		case 3: if(DadosStatic.Tipo.equals("Funcionario") && DadosStatic.especializacao.equals("Armazenista") || DadosStatic.Tipo.equals("Gestor")) {
			MetodoStaticProduto.listarCategorias();
			MetodoStaticLogs.movimentoUtilizador("Listou as Categorias");

		}else {
			Aviso.semPermissao();
			MetodoStaticLogs.movimentoUtilizador("Tentativa de listar Categorias");
		}
		break;

		case 4: MetodoStaticProduto.condicaoProduto(1, ""); MetodoStaticLogs.movimentoUtilizador("Listar Produtos Designacao Crescente"); break;

		case 5: MetodoStaticProduto.condicaoProduto(2, ""); MetodoStaticLogs.movimentoUtilizador("Listar Produtos Designacao Decrescente"); break;

		case 6: MetodoStaticProduto.condicaoProduto(3, ""); MetodoStaticLogs.movimentoUtilizador("Listar produtos Categoria Crescente"); break;

		case 7: MetodoStaticProduto.condicaoProduto(4, ""); MetodoStaticLogs.movimentoUtilizador("Listar produtos Categoria Decrescente"); break;

		case 8: pesquisa = Principal.pedeDadosString("Insira a Designacao de produto a pesquisar", DadosStatic.teclado);
		MetodoStaticProduto.condicaoProduto(5, pesquisa); MetodoStaticLogs.movimentoUtilizador("Pesquisa Produto Designacao"); break;

		case 9: pesquisa = Principal.pedeDadosString("Insira a Designacao de categoria a pesquisar", DadosStatic.teclado);
		int idCat = DadosProdutos.idCategoria(pesquisa);
		if(idCat > 0) {
			pesquisa = String.valueOf(idCat);
			MetodoStaticProduto.condicaoProduto(6, pesquisa); 
		}else {
			Aviso.naoExistenciaCategoria();
		} MetodoStaticLogs.movimentoUtilizador("Pesquisa Categoria por Designacao de Categoria");  break;

		case 10: pesquisa = String.valueOf(Principal.pedeDadosInteiro("Insira o stock maximo que pode ter o produto", DadosStatic.teclado));
		MetodoStaticProduto.condicaoProduto(7, pesquisa); 
		MetodoStaticLogs.movimentoUtilizador("Pesquisa produto por stock inferior a um numero");
		break;

		case 11: if(DadosStatic.Tipo.equals("Funcionario") && DadosStatic.especializacao.equals("Armazenista") || DadosStatic.Tipo.equals("Gestor")) {
			MetodoStaticProduto.adicionarQuantidade();
			MetodoStaticLogs.movimentoUtilizador("Inserir Stock em produto");
		}else {
			Aviso.semPermissao();
			MetodoStaticLogs.movimentoUtilizador("Tentativa de inserir stock em produto");
		}
		break;

		case 12: break;

		default: opcaoErrada(); break;
		}
	}
	//---------------------------_____________________________________-----------------------------------------------

	//SubMenu Encomendas---------------------------------------------------------------------------

		/**
		 * Menu Encomendas, onde criação, listagem, delegação, marcar como entregue e 
		 * confirmar a entrega se encontram neste menu
		 */
		public static void menuEncomendas(){
			int opcao = 0;
			while(opcao != 17) {
				// apresentar menu
				apresentaMenuEncomendas();
				// escolha opçao
				opcao = pedeDadosInteiro("Escolha a opção do menu inserindo um numero inteiro da opçao", DadosStatic.teclado);
				//executar opção
				executaOpcaoEncomendas(opcao);
			}
		}

		//LISTAGEM DE OPCOES
		/**
		 * Listagem das acções possiveis neste menu de Encomendas
		 */
		private static void apresentaMenuEncomendas() {
			System.out.println("1  -  Criar Encomenda \n" +
					"2  -  Listar TODAS Encomendas por Data de Criação Ascendente\n" +
					"3  -  Listar TODAS Encomendas por Data de Criação Descendente\n" +
					"4  -  Listar TODAS Encomendas por Antiguidade de Cliente Ascendente\n" +
					"5  -  Listar TODAS Encomendas por Antiguidade de Cliente Descendente\n" +
					"6  -  Listar TODAS Encomendas não entregue ordenada por Data Ascendente\n"+
					"7  -  Listar TODAS Encomendas não entregues ordenadas por Data Descendente\n"+
					"8  -  Listar as SUAS Encomendas ordenadas por Data Criação Ascendente\n"+
					"9  -  Listar as SUAS Encomendas ordenadas por Data Criação Descendente\n"+
					"10 -  Listar as SUAS Encomendas ordenadas por Identificador Ascendente\n"+
					"11 -  Listar as SUAS Encomendas ordenadas por Identificador Descendente\n"+
					"12 -  Listar Encomendas Iniciadas por Clientes\n"+
					"13 -  Aceitar/Rejeitar Encomendas\n"+
					"14 -  Delegar Encomenda a Armazenista\n"+
					"15 -  Marcar Encomenda como Entregue\n"+
					"16 -  Confirmar entrega de Encomenda\n"+
					"17 -  Sair.\n" );
		}

		//OPCOES
		/**
		 * @param aOpcao
		 * Metodo que executa a acção escolhida pelo utilizador em relação à listagem
		 */
		private static void executaOpcaoEncomendas(int aOpcao) {
			
			switch(aOpcao) {
			case 1: if(DadosStatic.Tipo.equals("Cliente")) {
				MetodoStaticEncomendas.criarEncomenda();
				MetodoStaticLogs.movimentoUtilizador("Criou Encomenda");
			}else {
				Aviso.semPermissao();
				MetodoStaticLogs.movimentoUtilizador("Tentativa de Criar Encomenda");
			}
			break;

			case 2: if(DadosStatic.Tipo.equals("Gestor")) {
						MetodoStaticEncomendas.condicaoEncomenda(1, "");
						MetodoStaticLogs.movimentoUtilizador("Listar Todas Encomendas Data Ascendente");
					}else {
						Aviso.semPermissao();
						MetodoStaticLogs.movimentoUtilizador("Tentativa Listar Todas Encomendas Data Ascendente");
					}
			break;

			case 3: if(DadosStatic.Tipo.equals("Gestor")) {
				 		MetodoStaticEncomendas.condicaoEncomenda(2, "");
				 		MetodoStaticLogs.movimentoUtilizador("Tentativa Listar Todas Encomendas Data Descendente");

					}else {
						Aviso.semPermissao();
						MetodoStaticLogs.movimentoUtilizador("Tentativa Listar Todas Encomendas Data Descendente");
					}
			break;

			case 4: if(DadosStatic.Tipo.equals("Gestor")) {
						MetodoStaticEncomendas.condicaoEncomenda(3, "");
						MetodoStaticLogs.movimentoUtilizador("Listar todas encomendas antiguidade cli ASC");

					}else {
						Aviso.semPermissao();
						MetodoStaticLogs.movimentoUtilizador("Tentativa listar todas Encomendas antiguidade cli asc");
					}break;

			case 5: if(DadosStatic.Tipo.equals("Gestor")) {
				        MetodoStaticEncomendas.condicaoEncomenda(4, "");
				        MetodoStaticLogs.movimentoUtilizador("Tentativa Listar Todas Encomendas antiguidade cli descendente");

					}else {
						Aviso.semPermissao();
						MetodoStaticLogs.movimentoUtilizador("Tentativa Listar Todas Encomendas antiguidade cli descendente");
					}break;

			case 6: if(DadosStatic.Tipo.equals("Gestor")) {
				        MetodoStaticEncomendas.condicaoEncomenda(5, "");
				        MetodoStaticLogs.movimentoUtilizador("Listar Todas Encomendas nao entregue Data Ascendente");
		
					}else {
						Aviso.semPermissao();
						MetodoStaticLogs.movimentoUtilizador("Tentativa Listar Todas Encomendas nao entregue Data Ascendente");
					}break;

			case 7: if(DadosStatic.Tipo.equals("Gestor")) {
				        MetodoStaticEncomendas.condicaoEncomenda(6, "");
				        MetodoStaticLogs.movimentoUtilizador("Listar Todas Encomendas nao entregue Data Descendente");
					}else {
						Aviso.semPermissao();
						MetodoStaticLogs.movimentoUtilizador("Tentativa Listar Todas Encomendas nao entregues Data Descendente");
					}break;

			case 8: if(DadosStatic.Tipo.equals("Cliente")) {
						MetodoStaticEncomendas.condicaoEncomenda(12, "CLI_ID_UTILIZADOR");
						
					}if(DadosStatic.Tipo.equals("Funcionario") && DadosStatic.especializacao.equals("Armazenista")) {
						MetodoStaticEncomendas.condicaoEncomenda(12, "FUN_ID_UTILIZADOR");
						
					}if(DadosStatic.Tipo.equals("Funcionario") && DadosStatic.especializacao.equals("Estafeta")) {
						MetodoStaticEncomendas.condicaoEncomenda(12, "FUN_ID_UTILIZADOR2");
						
					}if(DadosStatic.Tipo.equals("Gestor")){
						MetodoStaticEncomendas.condicaoEncomenda(12, "ID_UTILIZADOR");
					}MetodoStaticLogs.movimentoUtilizador("Listar SUAS encomendas por data ascendente"); break;

			case 9: if(DadosStatic.Tipo.equals("Cliente")) {
						MetodoStaticEncomendas.condicaoEncomenda(13, "CLI_ID_UTILIZADOR");
						
					}if(DadosStatic.Tipo.equals("Funcionario") && DadosStatic.especializacao.equals("Armazenista")) {
						MetodoStaticEncomendas.condicaoEncomenda(13, "FUN_ID_UTILIZADOR");
						
					}if(DadosStatic.Tipo.equals("Funcionario") && DadosStatic.especializacao.equals("Estafeta")) {
						MetodoStaticEncomendas.condicaoEncomenda(13, "FUN_ID_UTILIZADOR2");
						
					}if(DadosStatic.Tipo.equals("Gestor")){
						MetodoStaticEncomendas.condicaoEncomenda(13, "ID_UTILIZADOR");
					} MetodoStaticLogs.movimentoUtilizador("Listar SUAS encomendas por data Descendente"); break;

			case 10:if(DadosStatic.Tipo.equals("Cliente")) {
						MetodoStaticEncomendas.condicaoEncomenda(14, "CLI_ID_UTILIZADOR");
						
					}if(DadosStatic.Tipo.equals("Funcionario") && DadosStatic.especializacao.equals("Armazenista")) {
						MetodoStaticEncomendas.condicaoEncomenda(14, "FUN_ID_UTILIZADOR");
						
					}if(DadosStatic.Tipo.equals("Funcionario") && DadosStatic.especializacao.equals("Estafeta")) {
						MetodoStaticEncomendas.condicaoEncomenda(14, "FUN_ID_UTILIZADOR2");
						
					}if(DadosStatic.Tipo.equals("Gestor")){
						MetodoStaticEncomendas.condicaoEncomenda(14, "ID_UTILIZADOR");
					} MetodoStaticLogs.movimentoUtilizador("Listar SUAS encomendas por identificador asc"); break;
				
			case 11: if(DadosStatic.Tipo.equals("Cliente")) {
						MetodoStaticEncomendas.condicaoEncomenda(15, "CLI_ID_UTILIZADOR");
						
					}if(DadosStatic.Tipo.equals("Funcionario") && DadosStatic.especializacao.equals("Armazenista")) {
						MetodoStaticEncomendas.condicaoEncomenda(15, "FUN_ID_UTILIZADOR");
						
					}if(DadosStatic.Tipo.equals("Funcionario") && DadosStatic.especializacao.equals("Estafeta")) {
						MetodoStaticEncomendas.condicaoEncomenda(15, "FUN_ID_UTILIZADOR2");
						
					}if(DadosStatic.Tipo.equals("Gestor")){
						MetodoStaticEncomendas.condicaoEncomenda(15, "ID_UTILIZADOR");
					} MetodoStaticLogs.movimentoUtilizador("Listar SUAS encomendas por identificador desc"); break;

			case 12: if(DadosStatic.Tipo.equals("Gestor")) {
				        MetodoStaticEncomendas.condicaoEncomenda(16, " WHERE ESTADO_ENCOMENDA = 'iniciada' ");
				        MetodoStaticLogs.movimentoUtilizador("Listar encomendas por iniciadas por clientes");
					}else {
						Aviso.semPermissao();
						MetodoStaticLogs.movimentoUtilizador("Tentativa Listar encomendas por iniciadas por clientes");
					}break;
			
			case 13: if(DadosStatic.Tipo.equals("Gestor")) {
				        MetodoStaticEncomendas.menuAceitaRejeitaEncomenda();
				        MetodoStaticLogs.movimentoUtilizador("Aceitar/Rejeitar encomendas");
					}else {
						Aviso.semPermissao();
						MetodoStaticLogs.movimentoUtilizador("Tentativa Aceitar/Rejeitar encomendas");
					}break;
			
			case 14: if(DadosStatic.Tipo.equals("Gestor")) {
				        MetodoStaticEncomendas.delegarEncomendaArmazenista();
				        MetodoStaticLogs.movimentoUtilizador("Delegar encomendas armazenista");
					}else {
						Aviso.semPermissao();
						MetodoStaticLogs.movimentoUtilizador("Tentativa Delegar encomendas armazenista");
					}break;
			
			case 15:  if(DadosStatic.Tipo.equals("Funcionario")) {
					      if(DadosUtilizadores.pesquisaFuncionarios(DadosStatic.Login).getEspecializacao().equals("Estafeta")) {
					    	  MetodoStaticEncomendas.entregaEncomendaEstafeta();
					    	  MetodoStaticLogs.movimentoUtilizador("Entregou encomenda a cliente");
					      }else {
					    	  Aviso.semPermissao();
					    	  MetodoStaticLogs.movimentoUtilizador("Tentativa entrar opcao de entregar encomenda");
					      }	
						}else {
							Aviso.semPermissao();
							MetodoStaticLogs.movimentoUtilizador("Tentativa entrar opcao de entregar encomenda");
						}break;
			case 16: if(DadosStatic.Tipo.equals("Cliente")) {
				        MetodoStaticEncomendas.confirmaEncomendaCliente();	
				        MetodoStaticLogs.movimentoUtilizador("Confirmou Encomenda");
					}else {
						Aviso.semPermissao();
						MetodoStaticLogs.movimentoUtilizador("Confirmou Encomenda");
					}break;
					
			case 17: break;

			default: opcaoErrada(); break;
			}
		}
		//---------------------------_____________________________________-----------------------------------------------

	//Submenu Pedido ------------------------------------______--------------------------------
		
		/**
		 * Menu Pedidos, onde se realiza o Pedido de Entrega de encomenda ao Estafeta
		 * Desenhado para puder ser usado para mais pedidos, neste software apenas foi aplicado nesta acção
		 */
		public static void menuPedido(){
			int opcao = 0;
			while(opcao != 3) {
				// apresentar menu
				apresentaMenuPedido();
				// escolha opçao
				opcao = pedeDadosInteiro("Escolha a opção do menu inserindo um numero inteiro da opçao", DadosStatic.teclado);
				//executar opção
				executaOpcaoPedido(opcao);
			}
		}

		//LISTAGEM DE OPCOES
		/**
		 * Listagem das acções do Menu de pedido
		 */
		private static void apresentaMenuPedido() {
			System.out.println("1 - Pedido de Entrega a Estafeta\n" +
					"2 -  Aceitar / Rejeitar Pedido\n" +
					"3 -  Sair\n");
					
		}

		//OPCOES
		/**
		 * @param aOpcao
		 * Metodo que executa a acção pedida pelo utilizador
		 */
		private static void executaOpcaoPedido(int aOpcao) {
			switch(aOpcao) {
			case 1: if(DadosUtilizadores.pesquisaLogin(DadosStatic.Login).getTipo().equals("Funcionario") && 
						DadosUtilizadores.pesquisaFuncionarios(DadosStatic.Login).getEspecializacao().equals("Armazenista")) {
						if(DadosProdutos.verificaStocks() == 0) {
							MetodoStaticPedido.criarPedidoEstafeta();
							MetodoStaticLogs.movimentoUtilizador("Criou Pedido a Estafeta");
						}else {
							Aviso.regularizarStock();
						}
					}else {
						Aviso.semPermissao();
						MetodoStaticLogs.movimentoUtilizador("Tentativa de criar pedido estafeta");
					}break;
			case 2:  if(DadosUtilizadores.pesquisaLogin(DadosStatic.Login).getTipo().equals("Funcionario") && 
						DadosUtilizadores.pesquisaFuncionarios(DadosStatic.Login).getEspecializacao().equals("Estafeta")) {
							MetodoStaticPedido.menuAceitaRejeita();
							MetodoStaticLogs.movimentoUtilizador("Aceitar/Rejeitar Pedido");
					 }else {
						 Aviso.semPermissao();
						 MetodoStaticLogs.movimentoUtilizador("Tentativa aceitar/rejeitar Pedido");
					 } break;
			case 3:  break;
			default: opcaoErrada(); break;
			}
		}

		
		
	//----------_____------------------------------------------------_____---------------------------	

		//SUBMENU LOGS
	//------------------------------------------------------------------------------------------------------
		/**
		 * Menu Logs onde são listados com condicionamento os movimentos realizados pelos utilizadores
		 * no software enquanto estiveram online
		 */
		public static void menuLogs(){
			int opcao = 0;
			while(opcao != 4) {
				// apresentar menu
				apresentaMenuLogs();
				// escolha opçao
				opcao = pedeDadosInteiro("Escolha a opção do menu inserindo um numero inteiro da opçao", DadosStatic.teclado);
				//executar opção
				executaOpcaoLogs(opcao);
			}
		}

		//LISTAGEM DE OPCOES
		/**
		 * Listagem das opções possiveis no menu de Logs
		 */
		private static void apresentaMenuLogs() {
			System.out.println("1 -  Listar Logs por Data Ascendente\n" +
					"2 -  Listar Logs por Ordem Descendente\n" +
					"3 -  Listar Logs de um determinado Utilizador\n" +
					"4 -  Sair\n");
					
		}

		//OPCOES
		/**
		 * @param aOpcao
		 * Metodo que executa a acção pedida pelo utilizador no menu Logs
		 */
		private static void executaOpcaoLogs(int aOpcao) {
			switch(aOpcao) {
			case 1: if(DadosStatic.Tipo.equals("Gestor")) {
						MetodoStaticLogs.listarLogs(MetodoStaticLogs.condicoesListaLog(1, ""));
						MetodoStaticLogs.movimentoUtilizador("Listou Logs asc");
					}else {
						Aviso.semPermissao();
						MetodoStaticLogs.movimentoUtilizador("Tentativa Listar Logs asc");
					}break;
			case 2:  if(DadosStatic.Tipo.equals("Gestor")) {
						MetodoStaticLogs.listarLogs(MetodoStaticLogs.condicoesListaLog(2, ""));	
						MetodoStaticLogs.movimentoUtilizador("Listou logs desc");
					 }else {
						 Aviso.semPermissao();
						 MetodoStaticLogs.movimentoUtilizador("Tentativa Listar logs desc");
					 } break;
			case 3:  if(DadosStatic.Tipo.equals("Gestor")) {
						String pesquisa = pedeDadosString("Indique o login para pesquisar os logs ", DadosStatic.teclado);
						if(Integer.parseInt(DadosUtilizadores.verificaLogin(pesquisa)) > 0) {
							MetodoStaticLogs.listarLogs(MetodoStaticLogs.condicoesListaLog(3, pesquisa));
							MetodoStaticLogs.movimentoUtilizador("Listou logs de utilizador");
						}else {
							Aviso.avisoNaoExistenciaLog();
							MetodoStaticLogs.movimentoUtilizador("Tentativa de listar logs de utilizador");
						}
					 }else {
						 Aviso.semPermissao();
					 } break;
			case 4:  break;
			default: opcaoErrada(); break;
			}
		}

		
		
		
		
		
	//---------------------------------------------------------------------------------------------------


	// Pedir dados ao utilizador Mensagem de Erro  
	/**
	 * @param aTexto
	 * @param aTeclado
	 * @return inteiro com verificação se o mesmo é inteiro
	 */
	public static int pedeDadosInteiro(String aTexto, Scanner aTeclado) { 
		while(true){
			try{
				return (Integer.parseInt(pedeDadosString(aTexto, aTeclado))); //assim já asseguro ser inteiro..
			}catch(NumberFormatException nfe){
				System.out.println("Não é um inteiro ou maior que 9 unidades, insira novamente");
			}
		}
	}
	/**
	 * @param aTexto
	 * @param aTeclado
	 * @return float com verificação se é mesmo float
	 */
	public static float pedeDadosFloat(String aTexto, Scanner aTeclado) { 
		while(true){
			try{
				return (Float.parseFloat(pedeDadosString(aTexto, aTeclado))); //assim já asseguro ser inteiro..
			}catch(NumberFormatException nfe){
				System.out.println("Não é um float, insira novamente");
			}
		}
	}


	/**
	 * @param aTexto
	 * @param aTeclado
	 * @return String escrita por utilizador
	 */
	public static String pedeDadosString(String aTexto, Scanner aTeclado) {
		System.out.println(aTexto);
		return aTeclado.nextLine();
	}

	/**
	 * Opção errada quando o utilizador tenta aceder a opção que não existe no menu
	 */
	public static void opcaoErrada() {
		System.out.println("!! Opção errada !!");
	} 



}


