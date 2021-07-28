package Utilizadores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import AcessoBD.*;
import Aviso.*;
import DadosEstaticos.DadosStatic;
import InterfacePrincipal.Principal;
import java.util.*;

/**
 * @author Jorge Martins
 *
 */
public class MetodoStaticUtilizadores {
	

static Scanner teclado = new Scanner(System.in);

	
	//MANIPULAÇÂO DADOS-----------------------------------------------
	/**
	 * @param primeiroLogin
	 * Metodo Criar utilizador para o software, metodo estatico de interação com utilizador
	 */
	public static void CriarUtilizador(boolean primeiroLogin) {
		boolean state;
		String nome;
		String login;
		String password;
		String email;
		String tipo = "Gestor";
		String estado = "espera";
		Integer contacto = 0;
		String especializacao = new String();
		Date dataInicio = new Date();
		String morada = new String();
		Integer contribuinte = 0;
		do{
		    login = Principal.pedeDadosString("Insira o seu Login", teclado);
			state = login.contains(";");
			if(state)
				Aviso.avisoMensagem();
			if(login.isEmpty() || login.trim().isEmpty() || login.charAt(0) == ' '){
				state = true;
				Aviso.avisoString();
			}
			if(DadosUtilizadores.verificaLogin(login) == null || DadosUtilizadores.verificaLogin(login).length() > 0) {
				Aviso.avisoExistenciaLog();
				state = true;
			}
		}while(state);
		do{
			nome = Principal.pedeDadosString("Insira o seu nome", teclado);
			state = !nome.matches("[a-zA-Z õ ã á à é è Õ Ã Á À É È]+");
			if(state){
				Aviso.avisoMensagem();
			}
			if(nome.isEmpty() || nome.trim().isEmpty() || nome.charAt(0)== ' '){ //caso a string seja vazia ou tenha apenas espaços o state fica true e o utilizador tem de repetir o processo
				state = true;
				Aviso.avisoString();
			}
			
		}while(state);
		do{ 
			password = Principal.pedeDadosString("Insira a password", teclado);
			state = password.contains(";");
			if(state)
				Aviso.avisoMensagem();
			if(password.isEmpty() || password.trim().isEmpty() || password.charAt(0) == ' '){
				state = true;
				Aviso.avisoString();
			}
		}while(state);
		do{ 
			email = Principal.pedeDadosString("Insira o seu email", teclado);
			state = email.contains(";");
			if(state) {
				Aviso.avisoMensagem();
				continue;
			}
			if(email.isEmpty() || email.trim().isEmpty() || email.charAt(0) == ' '){
				state = true;
				Aviso.avisoString();
				continue;
			}
			if(verificaEmail(email)) {
				state = false;
				if(DadosUtilizadores.verificaEmail(email).equals(email)) {
					state = true;
					Aviso.existenciaEmail();
				}else {
					state = false;
				}
			}else {
				state = true;
			}
		}while(state);
		if(primeiroLogin){ //o tipo esta iniciado por defeito como Gestor e so preciso de mudar o estado para activo.
			estado = "activo";
		}else{
			do{
				int opcao = 0;
				opcao = Principal.pedeDadosInteiro("Insira o seu tipo de utilizador: \n1- Gestor \n2 - Cliente \n3 - Funcionario", teclado);
				switch(opcao){
					case 1: tipo = "Gestor"; state = false; break;
					case 2: tipo = "Cliente"; state = false; break;
					case 3: tipo = "Funcionario"; state = false; break;
					default: Principal.opcaoErrada(); state = true; break; //não passa a fase seguinte
				}
			}while(state);
		}
		if(!tipo.equalsIgnoreCase("Gestor")) { //nao igual a gestor
			do {
				contribuinte = Principal.pedeDadosInteiro("\nInsira o seu contribuinte\n", teclado);
				if(DadosUtilizadores.verificaContribuinte(contribuinte) == 0) {
					String auxContribuinte = Integer.toString(contribuinte);
					if(auxContribuinte.length() < 9 || contribuinte / 100000000 < 0 || contribuinte / 100000000 > 3) { //divisao inteira para que tenha 1 2 3 
						state = true;
						System.out.println("Erro na Inserção do Contribuinte, tem de ter 9 digitos e começa por 1, 2 ou 3");
					}else {
						state = false;
					}
					
				}else {
					state = true;
					Aviso.existenciaContribuinte();
				}
			}while(state);
			do {
				contacto = Principal.pedeDadosInteiro("\nInsira o seu contacto\n", teclado);
				if(DadosUtilizadores.verificaContacto(contacto) == 0) {
					String auxContacto = Integer.toString(contacto);
					if(auxContacto.length() < 9) {
						state = true;
						System.out.println("Erro na inserção do contacto, tem de ter 9 digitos");
					}else {
						if(auxContacto.startsWith("9") || auxContacto.startsWith("2") || auxContacto.startsWith("3")){
							state = false;
						}else {
							state = true;
							System.out.println("Erro na inserção do contacto, só pode começar por 9, 2 ou 3");
						}
						
							
						}
				}else {
					state = true;
					Aviso.existenciaContacto();
				}
			}while(state);
			morada = Principal.pedeDadosString("\nInsira a sua morada", teclado);
			if(morada.length() == 0) {
				morada = "sem morada";
			}
			if(tipo.equalsIgnoreCase("Funcionario")) {
				do{
					int opcao = 0;
					opcao = Principal.pedeDadosInteiro("Insira a especialização do funcionário: \n1- Armazenista \n2 - Estafeta \n", teclado);
					switch(opcao){
						case 1: especializacao = "Armazenista"; state = false; break;
						case 2: especializacao = "Estafeta"; state = false; break;
						default: Principal.opcaoErrada(); state = true; break; //não passa a fase seguinte
					}
				}while(state);
				do {
					state = false;
					String dataInicioString = Principal.pedeDadosString("\nInsira a data de inicio da actividade com dd/MM/yyyy", teclado);
					SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
					formatoData.setLenient(false); //para verificar se o formato da data é valido tipo 30/02/2021
					try {
						dataInicio = formatoData.parse(dataInicioString);
					}catch(ParseException e) {
						try {
							dataInicio = formatoData.parse("01/01/1970");
						}catch(ParseException e2) {
							e.printStackTrace();
						}
						System.out.println("Formato de data não aceite, tente de novo \n");
						state = true;
					}
					try {
					Date antes = formatoData.parse("01/01/1970");
					Date agora = new Date();
					if(dataInicio.after(antes) && dataInicio.before(agora) && state == false) {
						state = false;
					}else {
						state = true;
						System.out.println("Data não pode ser antes de 1970 nem depois da data de Hoje!");
					}
					}catch(ParseException e3) {
						e3.printStackTrace();
					}
				}while(state);
			}
		}
		
		switch(tipo) {
			case "Gestor": 
				DadosUtilizadores.adicionaUtilizador(new Utilizadores(nome, login, password, estado, email, tipo));
				break;
			case "Cliente": 
				DadosUtilizadores.adicionaCliente(new Clientes(nome, login, password, estado, email, tipo, contribuinte, contacto, morada));
				break;
			case "Funcionario": 
				DadosUtilizadores.adicionaFuncionario(new Funcionarios(nome, login, password, estado, email, tipo, contribuinte, contacto, morada, dataInicio, especializacao));
				break;
			}
			Aviso.operacaoSucesso();
		}
	
	
	//actualização dados---------------------------------------------------
	/**
	 * Metodo static de interação com utilizador para modificar o estado de um utilizador
	 */
	public static void mudarEstadoContaUtilizador(){
		String login = Principal.pedeDadosString("Insira o login do utilizador a modificar o seu estado", DadosStatic.teclado);
		if(login.equals(DadosStatic.Login)){
			System.out.println("Não pode modificar o seu próprio Estado por Segurança do Sistema!");
		}else{
			Utilizadores utilizador = DadosUtilizadores.pesquisaLogin(login);
			if(utilizador.getLogin() != null) {
				if(utilizador.getLogin().equals(login)){
					int opcao = Principal.pedeDadosInteiro("Insira para que estado quer mudar \n1-Activo \n2-Inactivo", DadosStatic.teclado);
					switch(opcao){
						case 1: mudaEstado(login, "activo"); Aviso.operacaoSucesso(); break;
						case 2: mudaEstado(login, "inactivo"); Aviso.operacaoSucesso(); break;
						default: Aviso.operacaoInsucesso(); break; 
						
					}
				}else{
					Aviso.avisoNaoExistenciaLog();
				}
			}else{
				Aviso.avisoNaoExistenciaLog();
			}
			
		}
	}
	
	/**
	 * @param aLogin
	 * @param aEstado
	 * Metodo que invoca metodos de acesso a BD para modificar estado de utilizador
	 */
	public static void mudaEstado(String aLogin, String aEstado) {
		Utilizadores utilizador = DadosUtilizadores.pesquisaLogin(aLogin);
		utilizador.setEstado(aEstado);
		DadosUtilizadores.updateUtilizador(utilizador);
	}
	
	/**
	 * Metodo que interage com utilizador demonstrando contas novas no sistema
	 * neste metodo estas são aceites ou rejeitadas
	 */
	public static void contasNovas() {
		listarUtilizadoresFiltro("ESTADO_UTILIZADOR = 'espera' "); //lista todas as contas em espera
		String login = Principal.pedeDadosString("Insira o login do utilizador a modificar o seu estado", DadosStatic.teclado);
		if(login.equals(DadosStatic.UserON)){
			System.out.println("Não pode aceitar o seu próprio Estado por Segurança do Sistema!");
		}else{
			Utilizadores utilizador = DadosUtilizadores.pesquisaLogin(login);
			if(utilizador.getLogin() != null) {
				if(utilizador.getLogin().equals(login)){
					int opcao = Principal.pedeDadosInteiro("Insira para que estado quer mudar \n1-Aceitar \n2-Rejeitar", DadosStatic.teclado);
					switch(opcao){
						case 1: mudaEstado(login, "activo"); Aviso.operacaoSucesso(); break;
						case 2: if(utilizador.getEstado().equals("espera")) {
									mudaEstado(login, "rejeitado"); Aviso.operacaoSucesso(); break;
								}else {
									Aviso.avisoMudanca(); break;
								}
								
						default: Aviso.operacaoInsucesso(); break; 
						
					}
				}else{
					Aviso.avisoNaoExistenciaLog();
				}
			}else {
				Aviso.avisoNaoExistenciaLog();
			}
		}
	}
	
	/**
	 * Metodo de interação com utilizador com os pedidos de remoção dos utilizadores para a inactivação
	 * da sua conta corrente
	 */
	public static void pedidosRemocao() {
		listarUtilizadoresFiltro("ESTADO_UTILIZADOR = 'pedido' "); //lista todas as contas em espera
		String login = Principal.pedeDadosString("Insira o login do utilizador para remover a informação", DadosStatic.teclado);
		if(login.equals(DadosStatic.UserON)){
			System.out.println("Não pode remover o seu próprio Estado por Segurança do Sistema!");
		}else{
			Utilizadores utilizador = DadosUtilizadores.pesquisaLogin(login);
			if(utilizador.getLogin() != null) {
				if(utilizador.getLogin().equals(login)){
					int opcao = Principal.pedeDadosInteiro("Aviso - Após esta acção não poderá voltar atrás!!  \n1-Rejeitar Pedido de Remoção de Conta \n2-Apagar Conta", DadosStatic.teclado);
					switch(opcao){
						case 1: mudaEstado(login, "activo"); Aviso.operacaoSucesso(); break;
						case 2: if(utilizador.getEstado().equals("pedido")) {
									if(utilizador.getTipo().equals("Gestor")) {
										DadosUtilizadores.updateUtilizador(new Utilizadores("anonimo", login, utilizador.getPass(), 
												"inactivo", "anonimo@anonimo.com", utilizador.getTipo()));
									}if(utilizador.getTipo().equals("Cliente")) {
										DadosUtilizadores.updateCliente(new Clientes("anonimo", login, utilizador.getPass(), 
												"inactivo", "anonimo@anonimo.com", utilizador.getTipo(), 0, 0, "sem morada"));
									}if(utilizador.getTipo().equals("Funcionario")) {
										Funcionarios funcionario = DadosUtilizadores.pesquisaFuncionarios(login);
										DadosUtilizadores.updateFuncionario(new Funcionarios("anonimo", login, utilizador.getPass(), 
												"inactivo", "anonimo@anonimo.com", utilizador.getTipo(), 0, 0, "sem morada", funcionario.getDataInicio(), funcionario.getEspecializacao()));
									}
									Aviso.operacaoSucesso(); break;
								}else {
									Aviso.avisoMudanca2(); break;
								}
								
						default: Aviso.operacaoInsucesso(); break; 
						
					}
				}else{
					Aviso.avisoNaoExistenciaLog();
				}
			}else {
				Aviso.avisoNaoExistenciaLog();
			}
		}
	}
	
	/**
	 * Metodo que o utilizador para fazer o pedido de remoção
	 */
	public static void pedidoRemocaoUtilizador() {
			Utilizadores utilizador = DadosUtilizadores.pesquisaLogin(DadosStatic.Login);
			if(utilizador.getLogin() != null) {
				if(utilizador.getLogin().equals(DadosStatic.Login)){
					int opcao = Principal.pedeDadosInteiro("Insira para que estado quer mudar \n1- Cancelar Pedido/Manter Activa \n2-Realizar Pedido de Remoção", DadosStatic.teclado);
					switch(opcao){
						case 1: mudaEstado(DadosStatic.Login, "activo"); Aviso.operacaoSucesso(); break;
						case 2: mudaEstado(DadosStatic.Login, "pedido"); Aviso.operacaoSucesso();
								break;	
						default: Aviso.operacaoInsucesso(); break; 
						
					}
				}else{
					Aviso.avisoNaoExistenciaLog();
				}
			}else {
				Aviso.avisoNaoExistenciaLog();
			}
		
	}
	
	
	//-----------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Menu de mudança de dados da conta que esta online no momento (seus dados)
	 */
	public static void menuMudancaUtilizador() {
			int opcao = 0;
			while(opcao != 7) {
			      // apresentar menu
					apresentaMenuMudancaDadosContaCorrente();
			      // escolha opçao
			       opcao = Principal.pedeDadosInteiro("Escolha a opção do menu inserindo um numero inteiro da opçao", DadosStatic.teclado);
			      //executar opção
			      executaOpcaoContaCorrente(opcao);
			    }
	}

				//LISTAGEM DE OPCOES
			  /**
			 * Listagem das opcoes
			 */
			private static void apresentaMenuMudancaDadosContaCorrente() {
			    System.out.println("1 -  Alterar Nome\n" +
			    				   "2 -  Alterar Password \n" +
			    				   "3 -  Alterar email\n" +
			    				   "4 -  Alterar Contribuinte\n" +
			                       "5 -  Alterar Contacto\n" +
			                       "6 -  Alterar Morada\n" +
			                       "7 -  Sair.\n" );
			  }
			  
			  //OPCOES
			  /**
			 * @param aOpcao
			 * Metodo de execução das opções
			 */
			private static void executaOpcaoContaCorrente(int aOpcao) {
				  Utilizadores utilizador = new Utilizadores();
				  Clientes cliente = new Clientes();
			    switch(aOpcao) {
			      case 1: String nome = Principal.pedeDadosString("Insira o novo nome do utilizador", teclado);
			      		  if(nome.contains(";")) {
			      			  Aviso.avisoMensagem();
			      		  }else {
			      			if(nome.isEmpty() || nome.trim().isEmpty() || nome.charAt(0) == ' '){
								Aviso.avisoString();
								Aviso.operacaoInsucesso();
				      		  }else {
				      			  utilizador = DadosUtilizadores.pesquisaLogin(DadosStatic.Login);
					      		  utilizador.setNome(nome);
					      		  DadosUtilizadores.updateUtilizador(utilizador);
				      		  }
			      		  }
			      		  break;
			      		  
			      case 2: String password = Principal.pedeDadosString("Insira o novo password", teclado);
			      		  if(password.contains(";")) {
			      			  Aviso.avisoMensagem();
			      		  }else {
			      			  if(password.isEmpty() || password.trim().isEmpty() || password.charAt(0) == ' '){
			      		  
								Aviso.avisoString();
								Aviso.operacaoInsucesso();
			      			  }else {
								utilizador = DadosUtilizadores.pesquisaLogin(DadosStatic.Login);
					      		utilizador.setPass(password);
					      		DadosUtilizadores.updateUtilizador(utilizador);
			      			  }
			      		  }
			      		  break;
			      		  
			      case 3: 	String email = Principal.pedeDadosString("Insira novo email", teclado);
			      			if(verificaEmail(email)) {
			      				if(email.contains(";")) {
									Aviso.avisoMensagem();
								}else {
									if(email.isEmpty() || email.trim().isEmpty() || email.charAt(0) == ' '){
										Aviso.avisoString();
										Aviso.operacaoInsucesso();
									}else{
										utilizador = DadosUtilizadores.pesquisaLogin(DadosStatic.Login);
							      		utilizador.setEmail(email);
							      		DadosUtilizadores.updateUtilizador(utilizador);
									}
								}
			      			}else {
			      				Aviso.avisoErro();
			      			}
							break;
			      
			      case 4: if(DadosStatic.Tipo.equals("Gestor")) {
			    	  		Aviso.semCampo();
			      		  }else {
			      			  int contribuinte = Principal.pedeDadosInteiro("Insira o novo contribuite", teclado);
			      			  String auxContribuinte = Integer.toString(contribuinte);
			      			  if(auxContribuinte.length() < 9 || contribuinte / 100000000 < 0 || contribuinte / 100000000 > 3) { //divisao inteira para que tenha 1 2 3 
			      				  System.out.println("Erro na Inserção do Contribuinte, tem de ter 9 digitos e começa por 1, 2 ou 3");
			      			  }else {
			      				  if(contribuinte == DadosUtilizadores.verificaContribuinte(contribuinte)) {
			      					  Aviso.existenciaContribuinte();
			      				  }else {
			      					  cliente = DadosUtilizadores.pesquisaClientes(DadosStatic.Login);
			      					  cliente.setContribuinte(contribuinte);
			      					  DadosUtilizadores.updateCliente(cliente);
			      					  Aviso.operacaoSucesso();
			      				  }
			      				  
			      			  }
			      		  }
			    	  
			      case 5: if(DadosStatic.Tipo.equals("Gestor")) {
				    	  		Aviso.semCampo();
			      		  }else {
			      			  int contacto = Principal.pedeDadosInteiro("Insira o novo contacto", teclado);
			      			  String auxContacto = Integer.toString(contacto);
			      			  if(auxContacto.length() < 9) {
			      				  System.out.println("Erro na inserção do contacto, tem de ter 9 digitos");
			      			  }else {
			      				  if(auxContacto.startsWith("9") || auxContacto.startsWith("2") || auxContacto.startsWith("3")){
			      					  if(contacto == DadosUtilizadores.verificaContacto(contacto)) {
			      						  Aviso.existenciaContacto();
			      					  }else {
			      						  cliente = DadosUtilizadores.pesquisaClientes(DadosStatic.Login);
			      						  cliente.setContacto(contacto);
			      						  DadosUtilizadores.updateCliente(cliente);
			      						  Aviso.operacaoSucesso();
			      					  }
									}else {
										System.out.println("Erro na inserção do contacto, só pode começar por 9, 2 ou 3");
									}
			      			  }
			      				  
			      		  }
			      		  break;
			    	  
			      case 6: if(DadosStatic.Tipo.equals("Gestor")) {
				    	  		Aviso.semCampo();
			      		  }else {
			      			  String morada = Principal.pedeDadosString("Insira a nova morada", teclado);
			      			  if(morada.length() == 0) {
			      				  morada = "sem morada";
			      			  }
			      			  cliente = DadosUtilizadores.pesquisaClientes(DadosStatic.Login);
			      			  cliente.setMorada(morada);
			      			  DadosUtilizadores.updateCliente(cliente);
			      			  Aviso.operacaoSucesso();	
			      			  }	  
			      		  break;
			    	  
			      case 7: break;
			      
			      default: Principal.opcaoErrada();
			    }
			  }
	
	//--------------------------------------------------------------------------------------------------------------------------------
			  
	/**
	 * Metodo de mudança de dados de conta por parte de um gestor a outro utilizador
	 */
	public static void menuMudancaUtilizadorGestor() {
		String login = Principal.pedeDadosString("Insira o login do utilizador pelo qual vai modificar dados", teclado);
		if(DadosUtilizadores.verificaLogin(login).equals("")) {
			Aviso.avisoNaoExistenciaLog();
		}else {
			Utilizadores auxiliar = DadosUtilizadores.pesquisaLogin(login);
			String tipo = auxiliar.getTipo();
			int opcao = 0;
			while(opcao != 8) {
			      // apresentar menu
					apresentaMenuMudancaDadosConta();
			      // escolha opçao
			       opcao = Principal.pedeDadosInteiro("Escolha a opção do menu inserindo um numero inteiro da opçao", DadosStatic.teclado);
			      //executar opção
			      executaOpcaoConta(opcao, login, tipo);
			    }
		}	
	}
	

	//LISTAGEM DE OPCOES
   /**
 * Listagem das suas opções a mudar na conta de utilizador
 */
private static void apresentaMenuMudancaDadosConta() {
    System.out.println("1 -  Alterar Nome\n" +
    				   "2 -  Alterar Password \n" +
    				   "3 -  Alterar email\n" +
    				   "4 -  Alterar Contribuinte\n" +
                       "5 -  Alterar Contacto\n" +
                       "6 -  Alterar Morada\n" +
                       "7 -  Alterar Especialização do Funcionário\n" +
                       "8 -  Sair.\n" );
  }
  
   //OPCOES
   /**
 * @param aOpcao
 * @param aLogin
 * @param aTipo
 * Metodo que executa as opções escolhidas pelo gestor
 */
private static void executaOpcaoConta(int aOpcao, String aLogin, String aTipo) {
	  Utilizadores utilizador = new Utilizadores();
	  Clientes cliente = new Clientes();
	  Funcionarios funcionario = new Funcionarios();
    switch(aOpcao) {
      case 1: String nome = Principal.pedeDadosString("Insira o novo nome do utilizador", teclado);
      		  if(nome.contains(";")) {
      			  Aviso.avisoMensagem();
      		  }else {
      			if(nome.isEmpty() || nome.trim().isEmpty() || nome.charAt(0) == ' '){
					Aviso.avisoString();
					Aviso.operacaoInsucesso();
	      		  }else {
	      			  utilizador = DadosUtilizadores.pesquisaLogin(aLogin);
		      		  utilizador.setNome(nome);
		      		  DadosUtilizadores.updateUtilizador(utilizador);
	      		  }
      		  }
      		  break;
      		  
      case 2: String password = Principal.pedeDadosString("Insira o novo password", teclado);
      		  if(password.contains(";")) {
      			  Aviso.avisoMensagem();
      		  }else {
      			  if(password.isEmpty() || password.trim().isEmpty() || password.charAt(0) == ' '){
      		  
					Aviso.avisoString();
					Aviso.operacaoInsucesso();
      			  }else {
					utilizador = DadosUtilizadores.pesquisaLogin(aLogin);
		      		utilizador.setPass(password);
		      		DadosUtilizadores.updateUtilizador(utilizador);
      			  }
      		  }
      		  break;
      		  
      case 3: 	String email = Principal.pedeDadosString("Insira novo email", teclado);
      			if(verificaEmail(email)) {
      				if(email.contains(";")) {
						Aviso.avisoMensagem();
					}else {
						if(email.isEmpty() || email.trim().isEmpty() || email.charAt(0) == ' '){
							Aviso.avisoString();
							Aviso.operacaoInsucesso();
						}else{
							utilizador = DadosUtilizadores.pesquisaLogin(aLogin);
				      		utilizador.setEmail(email);
				      		DadosUtilizadores.updateUtilizador(utilizador);
						}
					}
      			}else {
      				Aviso.avisoErro();
      			}
				break;
      
      case 4: if(aTipo.equals("Gestor")) {
    	  		Aviso.semCampo();
      		  }else {
      			  int contribuinte = Principal.pedeDadosInteiro("Insira o novo contribuite", teclado);
      			  String auxContribuinte = Integer.toString(contribuinte);
      			  if(auxContribuinte.length() < 9 || contribuinte / 100000000 < 0 || contribuinte / 100000000 > 3) { //divisao inteira para que tenha 1 2 3 
      				  System.out.println("Erro na Inserção do Contribuinte, tem de ter 9 digitos e começa por 1, 2 ou 3");
      			  }else {
      				  if(contribuinte == DadosUtilizadores.verificaContribuinte(contribuinte)) {
      					  Aviso.existenciaContribuinte();
      				  }else {
      					  cliente = DadosUtilizadores.pesquisaClientes(DadosStatic.Login);
      					  cliente.setContribuinte(contribuinte);
      					  DadosUtilizadores.updateCliente(cliente);
      					  Aviso.operacaoSucesso();
      				  }
      				  
      			  }
      		  }
    	  
      case 5: if(aTipo.equals("Gestor")) {
	    	  		Aviso.semCampo();
      		  }else {
      			  int contacto = Principal.pedeDadosInteiro("Insira o novo contacto", teclado);
      			  String auxContacto = Integer.toString(contacto);
      			  if(auxContacto.length() < 9) {
      				  System.out.println("Erro na inserção do contacto, tem de ter 9 digitos");
      			  }else {
      				  if(auxContacto.startsWith("9") || auxContacto.startsWith("2") || auxContacto.startsWith("3")){
      					  if(contacto == DadosUtilizadores.verificaContacto(contacto)) {
      						  Aviso.existenciaContacto();
      					  }else {
      						  cliente = DadosUtilizadores.pesquisaClientes(aLogin);
      						  cliente.setContacto(contacto);
      						  DadosUtilizadores.updateCliente(cliente);
      						  Aviso.operacaoSucesso();
      					  }
						}else {
							System.out.println("Erro na inserção do contacto, só pode começar por 9, 2 ou 3");
						}
      			  }
      				  
      		  }
      		  break;
    	  
      case 6: if(aTipo.equals("Gestor")) {
	    	  		Aviso.semCampo();
      		  }else {
      			  String morada = Principal.pedeDadosString("Insira a nova morada", teclado);
      			  if(morada.length() == 0) {
      				  morada = "sem morada";
      			  }
      			  cliente = DadosUtilizadores.pesquisaClientes(aLogin);
      			  cliente.setMorada(morada);
      			  DadosUtilizadores.updateCliente(cliente);
      			  Aviso.operacaoSucesso();	
      			  }	  
      		  break;
    	  
      case 7: if(aTipo.equals("Funcionario")) {
    	  		boolean state = true;
    	  		String especializacao = "";
	    	  do{
				 int op = 0;
				 op = Principal.pedeDadosInteiro("Insira a especialização do funcionário: \n1- Armazenista \n2 - Estafeta \n", teclado);
				 switch(op){
					case 1: especializacao = "Armazenista"; state = false; break;
					case 2: especializacao = "Estafeta"; state = false; break;
					default: Principal.opcaoErrada(); state = true; break; //não passa a fase seguinte
				}
				}while(state);
	    	  	funcionario = DadosUtilizadores.pesquisaFuncionarios(aLogin);
	    	  	funcionario.setEspecializacao(especializacao);
	    	  	DadosUtilizadores.updateFuncionario(funcionario);
	    	  	Aviso.operacaoSucesso();
      		  }else {
      			  Aviso.semCampo();
      		  }
      
      case 8: break;
      
      default: Principal.opcaoErrada();
    }
  }

			  
			  
	
			  
			  
	//LISTAR_____________________________________________________________________________________
	
	/**
	 * @param lista
	 * @param tamanho
	 * Listador de todas as listagens do programa, este versatil recebe de todos os outros metodos
	 * pedidos para listar produtos, encomendas entre outros
	 */
	public static void listadorMaster(String lista, int tamanho) {
		if(lista != null || tamanho > 0)
			System.out.println(lista);
		else
			Aviso.semDados();
		if(tamanho < 10) {
			Aviso.continuaListaFim(teclado);
		}else {
			Aviso.continuaLista(teclado);
		}
		
	}
	
	/**
	 * @param aOrdem
	 * Listar todos os utilizador com uma condição derivada de uma ordenação (ex order by ..)
	 */
	public static void listarTodosUtilizadores(String aOrdem) {
		
		ArrayList <Utilizadores> utilizadores = DadosUtilizadores.listarTodosUtilizadores(aOrdem);
		
		if(utilizadores != null){
			Iterator <Utilizadores> tabela = utilizadores.iterator();
			String gestor = "";
			String cliente = "";
			String funcionario = "";
			Utilizadores auxiliar;
			int contador = 0;
			while(tabela.hasNext()) {
		       auxiliar = tabela.next();
		       contador++;
		       if(auxiliar.getTipo().equals("Gestor")){
		       		gestor += "Login: " + auxiliar.getLogin() + " Nome: " + auxiliar.getNome() + " \n";
		       }
		       if(auxiliar.getTipo().equals("Cliente")){
		       		cliente += "Login: " + auxiliar.getLogin() + " Nome: " + auxiliar.getNome() + " \n";
		       }
		       if(auxiliar.getTipo().equals("Funcionario")){
		       		funcionario += "Login: " + auxiliar.getLogin() + " Nome: " + auxiliar.getNome() + " \n";
		       }
		       if(contador == 10) {
		    	   listadorMaster(" Gestores \n" + gestor + " \n" + " Clientes \n" + cliente + " \n" + " Funcionarios \n" + funcionario + " \n", contador); 
		    	   contador = 0;
		    	   gestor = "";     
		    	   cliente = "";    
		    	   funcionario = "";
		       }
		       		
		    }
			if(contador > 0) {
				listadorMaster(" Gestores \n" + gestor + " \n" + " Clientes \n" + cliente + " \n" + " Funcionarios \n" + funcionario + " \n", contador);
			}
			
		}
	}
	
	/**
	 * @param aCondicao
	 * Listar utilizadores sem o filtro do seu tipo de conta, aparecendo apenas os seus dados atraves
	 * de uma condição (ex where id = 2)
	 */
	public static void listarUtilizadoresSemFiltro(String aCondicao) {
		ArrayList <Utilizadores> utilizadores = DadosUtilizadores.listarUtilizadoresCondicao(aCondicao);
		
		if(utilizadores != null){
			Iterator <Utilizadores> tabela = utilizadores.iterator();
			String envio = "";
			Utilizadores auxiliar;
			int contador = 0;
		       while(tabela.hasNext()) {
			       auxiliar = tabela.next();
			       envio += "Login: " + auxiliar.getLogin() + " Nome: " + auxiliar.getNome() + "Tipo: " + auxiliar.getTipo() + "\n";
			       contador++;		
			    
			       if(contador == 10) {
			    	   listadorMaster(" Utilizadores \n" + envio + "\n", contador); 
			    	   contador = 0;
			    	   envio = "";
			       }
		       }
		       if(contador > 0) {
		    	   listadorMaster(" Utilizadores \n" + envio + "\n", contador);
		       }
	     
		}
	 }
	
	/**
	 * @param aCondicao
	 * Listar utilizadores com filtro onde são filtrados os tipos de utilizador
	 */
	public static void listarUtilizadoresFiltro(String aCondicao) {
		ArrayList <Utilizadores> utilizadores = DadosUtilizadores.listarUtilizadoresCondicao(aCondicao);
		
		if(utilizadores != null){
			Iterator <Utilizadores> tabela = utilizadores.iterator();
			String gestor = "";
			String cliente = "";
			String funcionario = "";
			Utilizadores auxiliar;
			int contador = 0;
			while(tabela.hasNext()) {
		       auxiliar = tabela.next();
		       contador++;
		       if(auxiliar.getTipo().equals("Gestor")){
		       		gestor += "Login: " + auxiliar.getLogin() + " Nome: " + auxiliar.getNome() + " \n";
		       }
		       if(auxiliar.getTipo().equals("Cliente")){
		       		cliente += "Login: " + auxiliar.getLogin() + " Nome: " + auxiliar.getNome() + " \n";
		       }
		       if(auxiliar.getTipo().equals("Funcionario")){
		       		funcionario += "Login: " + auxiliar.getLogin() + " Nome: " + auxiliar.getNome() + " \n";
		       } 
		       if(contador == 10) {
		    	   listadorMaster(" Gestores \n" + gestor + " \n" + " Clientes \n" + cliente + " \n" + " Funcionarios \n" + funcionario + " \n", contador); 
		    	   contador = 0;
		    	   gestor = "";
		    	   cliente = "";
		    	   funcionario = "";
		       } 
		       
		    }
			 if(contador > 0) {
		    	   listadorMaster(" Gestores \n" + gestor + " \n" + " Clientes \n" + cliente + " \n" + " Funcionarios \n" + funcionario + " \n", contador);
		       }
			 if(contador == 0) {
				 listadorMaster("Sem dados para demonstrar", 0);
			 }
			 
		}
	    
	}
	
	//PESQUISAS-------------------------------------------------------------------------
	/**
	 * Metodo de interação com o utilizador para pesquisar utilizador através do seu login
	 */
	public static void pesquisaPorLogin() {
		String login = Principal.pedeDadosString("Insira o login a pesquisar", DadosStatic.teclado);
		Utilizadores utilizador = DadosUtilizadores.pesquisaLogin(login);
		if(utilizador.getLogin() != null) {
			if(utilizador.getLogin().equalsIgnoreCase(login)) {
				System.out.println(utilizador);
			}else
				Aviso.semDados();
		}else {
			Aviso.semDados();
		}
		
		
		Aviso.continua(DadosStatic.teclado);
	}
	
	/**
	 * Metodo de interação com o utilizador para pesquisar utilizador através do seu nome
	 * usa a listagem como as outras pesquisas ou seja está numa pesquisa avançada
	 * usa o LIKE para isso na query
	 */
	public static void pesquisaPorNome() {
		String nome = Principal.pedeDadosString("Insira o nome a pesquisar", DadosStatic.teclado);
		listarUtilizadoresFiltro("NOME_UTILIZADOR LIKE '%"+nome+"%'");
		Aviso.continua(DadosStatic.teclado);
	}
	
	/**
	 * Metodo de interação com o utilizador para pesquisar utilizador através do seu tipo
	 * este gera uma listagem de tipos de utilizador
	 */
	public static void pesquisaPorTipo() {
		int opcao = 0;
		String tipo = "";
		opcao = Principal.pedeDadosInteiro("Insira o tipo de utilizador: \n1- Gestor \n2 - Cliente \n3 - Funcionario", teclado);
		switch(opcao){
			case 1: tipo = "Gestor"; break;
			case 2: tipo = "Cliente"; break;
			case 3: tipo = "Funcionario"; break;
			default: Principal.opcaoErrada(); break; //não passa a fase seguinte
		}
		if(tipo.length() > 0) {
			listarUtilizadoresSemFiltro("TIPO_UTILIZADOR = '"+tipo+"' ");
		}
		Aviso.continua(DadosStatic.teclado);
	}
	
	/**
	 * @param aLogin
	 * @param aPass
	 * @return boolean
	 * Metodo de interação com o utilizador para receber os dados de login e chamar os metodos
	 * para verificar a vericidade dos mesmos
	 */
	public static boolean loginUtilizador(String aLogin, String aPass) {
		
		Utilizadores utilizador = DadosUtilizadores.pesquisaLogin(aLogin);
		if(utilizador.getLogin() != null) {
			if(utilizador.getEstado().equals("rejeitado")) {
				Aviso.avisoRejeitadoLogin();
			}else {
				if(utilizador.getLogin().equals(aLogin) && utilizador.getPass().equals(aPass)) {
				if(utilizador.getEstado().equalsIgnoreCase("activo") || utilizador.getEstado().equalsIgnoreCase("pedido")) {
					DadosStatic.UserON = utilizador.getNome();
					DadosStatic.Login = utilizador.getLogin();
					DadosStatic.Tipo = utilizador.getTipo();
					if(utilizador.getTipo().equals("Funcionario")) {
						Funcionarios funcionario = DadosUtilizadores.pesquisaFuncionarios(aLogin);
						DadosStatic.especializacao = funcionario.getEspecializacao();
					}
					return false;
				}
				}else {
					return true;
				}
			}
			
		}return true;
		
		
	}
	
	
	/**
	 * @param aEmail
	 * @return boolean
	 * Verifica se email está dentro da expressao regular
	 */
	public static boolean verificaEmail(String aEmail) {
		
		boolean retorna = aEmail.matches("^[a-zA-Z0-9.]+@[a-z0-9]+\\.[a-z]+");//procura de a-z em pequeno e grande, numero e pode ter ponto, seguido de arroba com numero ou letra seguido de ponto e depois caracteres..
		
		
		return retorna;
	}
	
	
	
	
	//Notificações --------------------------------------------------------------------------------------------------------
	
	/**
	 * Notificação de contas novas para demonstrar a um gestor ao iniciar conta
	 * gera notificação apenas
	 * chama metodo de acesso a base de dados pre definido que contabiliza o numero de contas novas
	 */
	public static void notiNovasContas() {
		if(DadosUtilizadores.verificaContasNovas() > 0) {
			System.out.println("Notificação - Existem novas contas registadas para serem aceites!!\n");
		}
	}
	
	/**
	 * Notificação de pedidos de remoção de conta ou contas que são para "apagar" pois
	 * não se apagam por completo apenas se muda o seu estado
	 */
	public static void notiContasApagar() {
		if(DadosUtilizadores.verificaPedidosApagar() > 0) {
			System.out.println("Notificação - Existem pedidos de remoção de conta de utilizador Pendentes!!\n");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
