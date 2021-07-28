package InterfacePrincipal;

import DadosEstaticos.*;
import Utilizadores.MetodoStaticUtilizadores;
import Aviso.*;
import AcessoBD.*;

import java.util.Scanner;

/**
 * @author Jorge Martins
 * Classe com o interface de inicio do software, antes ainda deste entrar no menu
 * Aqui encontra-se a primeira camada, login, dados de ligação à base de dados (Ficheiro Properties)
 */
public class InterfaceLogin {
	
	static Scanner teclado = new Scanner(System.in);
	
	/**
	 * Primeira Camada, leitura do ficheiro Properties para dar aos dados Static os valores corretos para o caminho
	 * password, user para a base de dados. 
	 * Este metodo contem o menu Inicial de registo, login e acesso à modificação do ficheiro Properties de algum elemento
	 * que ele contenha
	 */
	public static void primeiraCamada(){ //verificar o estado da leitura se exitem dados
		FicheiroProperties.leituraDadosProp();
		int conta = DadosUtilizadores.verificaTabela();
		if(conta == 0){
			System.out.println("Software sem Utilizadores insira dados para iniciar..");
			MetodoStaticUtilizadores.CriarUtilizador(true);
			boolean continuar = true;
			do{
				int opcao = Principal.pedeDadosInteiro("Escolha opcao: \n1-Registo \n2-Login \n3-Alterar Ligação \n4-Sair", DadosStatic.teclado);
				switch(opcao){
				case 1: MetodoStaticUtilizadores.CriarUtilizador(false); break; 
				case 2: continuar = login(DadosStatic.teclado); break;
				case 3: modificaPropriedades(teclado); break;
				case 4: System.out.println("Terminou programa \n"); System.exit(0); break;
				default: Aviso.avisoErro(); break;
				}
			}while(continuar);
		}else{
			System.out.println("Dados carregados com sucesso!");
			boolean continua = true;
			do{
				int opcao = Principal.pedeDadosInteiro("Escolha opcao: \n1-Registo \n2-Login \n3-Alterar Ligação \n4-Sair", DadosStatic.teclado);
				switch(opcao){
				case 1: MetodoStaticUtilizadores.CriarUtilizador(false); break; 
				case 2: continua = login(teclado); break;
				case 3: modificaPropriedades(teclado); break;
				case 4: System.out.println("Terminou programa \n"); System.exit(0); break;
				default: Aviso.avisoErro(); break;
				}
			}while(continua);
			
		}
	}
	
	/**
	 * @param aTeclado
	 * @return boolean
	 * Login do user, é verificado se o login existe e se a pass está em conformidade
	 */
	private static boolean login(Scanner aTeclado) {
		String login = Principal.pedeDadosString("Insira o seu Login", aTeclado);
		String pass = Principal.pedeDadosString("Insira a sua Password", aTeclado);
		
		boolean bool = MetodoStaticUtilizadores.loginUtilizador(login, pass);
		if(bool) {
			Aviso.semPermissao();
		}
		
		return bool;
	}
	
	
	/**
	 * @param teclado
	 * Modifica as propriedades existentes no ficheiro Properties que neste caso são informações
	 * do caminho e dados de acesso à base de dades relacional
	 */
	private static void modificaPropriedades(Scanner teclado){
		FicheiroProperties.leituraDadosProp();
		int opcao = 0;
		do{
			listaProp();
			opcao = Principal.pedeDadosInteiro("Escolha opcao a modificar: \n1-Base Dados \n2-Ip \n3-Password \n4-Porto \n5-Utilizador \n6-Guardar Alterações \n7-Sair", DadosStatic.teclado);
			switch(opcao){
			case 1: DadosStatic.baseDados = Principal.pedeDadosString("\nInsira nova base de dados: ", teclado); Aviso.operacaoSucesso(); break;
			case 2: DadosStatic.ip = Principal.pedeDadosString("\n Insira o novo Ip: ", teclado); Aviso.operacaoSucesso(); break;
			case 3: DadosStatic.pass = Principal.pedeDadosString("\n Insira nova password da Base de Dados: ", teclado); Aviso.operacaoSucesso(); break;
			case 4: DadosStatic.porto = Principal.pedeDadosString("\n Insira novo porto de ligação: ", teclado); Aviso.operacaoSucesso(); break;
			case 5: DadosStatic.utilizador = Principal.pedeDadosString("\n Insira novo utilizador da base de dados: ", teclado); Aviso.operacaoSucesso(); break;
			case 6: try {
					FicheiroProperties.escritaDadosProp(DadosStatic.ip, DadosStatic.porto, DadosStatic.baseDados, DadosStatic.utilizador, DadosStatic.pass); Aviso.operacaoSucesso(); break;
					}catch(Exception e) {
						e.printStackTrace();
						Aviso.operacaoInsucesso();
						break;
					}
			case 7: break;
			default: Aviso.avisoErro();
					
			}
		}while(opcao != 7);
		
		
	}
	
	/**
	 * Lista os dados existentes no ficheiro properties
	 */
	private static void listaProp() {
		System.out.println("Base de dados : "+DadosStatic.baseDados+"\n" + "Ip: " +DadosStatic.ip +"\n"+"Password: "+ DadosStatic.pass+"\n" +"Porto: "+ DadosStatic.porto+"\n"+"Utilizador: "+ DadosStatic.utilizador+"\n");
	}
	
	/**
	 * Metodo que serve para quando não existe uma pasta com um ficheiro properties com as credenciais de acesso a uma
	 * base de dados, este vai pedir ao utilizador se quer inserir os seus dados de acesso à base de dados ou irá inserir
	 * as que estao por defeito do programa para a sua base de dados original.
	 */
	public static void semDadosProp() {
		System.out.println("Ficheiro Properties sem dados\n");
		System.out.println("1-Dados Por Defeito\n2-Inserir seus dados de acesso a sua base de dados \n");
		int opcao = Principal.pedeDadosInteiro("Insira sua opcao : ", teclado);
		switch(opcao) {
		case 1: break; //valores por defeito da minha base de dados, apenas para com o executavel não estar sempre a tentar entrar com inserção de dados
		case 2: DadosStatic.ip = Principal.pedeDadosString("\nInsira o ip da base de dados : ", teclado);
				DadosStatic.porto = Principal.pedeDadosString("\nInsira o porto : ", teclado);
				DadosStatic.utilizador = Principal.pedeDadosString("\nInsira o nome do utilizador : ", teclado);
				DadosStatic.pass = Principal.pedeDadosString("\nInsira a password da base de dados :  ", teclado);
				DadosStatic.baseDados = Principal.pedeDadosString("Nome da Base de Dados : ", teclado);
				break;
		default: break;
		}
		try {
			FicheiroProperties.escritaDadosProp(DadosStatic.ip, DadosStatic.porto, DadosStatic.baseDados, DadosStatic.utilizador, DadosStatic.pass);
		}catch(Exception e) {
			e.printStackTrace();
			Aviso.operacaoInsucesso();
			
		}
	}



}
