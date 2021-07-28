package Aviso;

import java.util.Scanner;

/**
 * @author Jorge Martins
 * Classe que apenas contem metodos de envio de aviso com System.out.println
 */
public class Aviso {
	
	/**
	 * Aviso regularizar o stock
	 */
	public static void regularizarStock() {
		System.out.println("Erro, s� pode usar esta op��o quanto regularizar os stocks dos produtos, existem produtos com stock negativo \n");
	}
	
	/**
	 * Aviso Encomenda nao iniciada
	 */
	public static void encomendaNaoIniciada() {
		System.out.println("Encomenda n�o se encontra no estado iniciada");
	}
	
	/**
	 * Aviso de login rejeitado
	 */
	public static void avisoRejeitadoLogin(){
		System.out.println("Erro, este login foi rejeitado por um gestor da aplica��o!\n");
	}
	
	/**
	 * Aviso de nao puder rejeitar login
	 */
	public static void avisoMudanca(){
		System.out.println("Erro, login j� foi aceite n�o poder� rejeitar, use alterar estado de conta para inactivar \n");
	}
	
	/**
	 * Aviso de login n�o estar com pedido de remo��o
	 */
	public static void avisoMudanca2(){
		System.out.println("Erro, login n�o se encontra com pedido de remo��o, use alterar estado de conta para inactivar \n");
	}
	
	/**
	 * Aviso de n�o usar caracteres especiais no login nome o pass
	 */
	public static void avisoMensagem(){
		System.out.println("N�o pode usar ; no login, nome ou pass, e caracteres especiais no nome\n");
	}
	
	/**
	 * Aviso para inserir caracteres
	 */
	public static void avisoString(){
		System.out.println("Erro, insira caracteres para este campo, n�o inicie que espa�o o que inserir\n");
	}
	
	/**
	 * Aviso login nao existe
	 */
	public static void avisoNaoExistenciaLog(){
		System.out.println("Erro, login n�o existe \n");
	}
	
	/**
	 * Aviso de existencia de login na base de dados
	 */
	public static void avisoExistenciaLog(){
		System.out.println("Erro, login j� existe \n");
	}
	
	/**
	 * Aviso de existencia de email
	 */
	public static void existenciaEmail() {
		System.out.println("Erro, email j� existe \n");
	}
	
	/**
	 * Aviso de existencia de contribuinte
	 */
	public static void existenciaContribuinte() {
		System.out.println("Erro, contribuinte j� existe \n");
	}
	
	/**
	 * Aviso de existencia de contacto
	 */
	public static void existenciaContacto() {
		System.out.println("Erro, contacto j� existe \n");
	}
	
	/**
	 * Aviso de erro
	 */
	public static void avisoErro() {
		System.out.println("Erro! N�o foi possivel realizar a sua ac��o! \n");
	}
	
	//mensagem de sucesso e insucesso
	  /**
	 * Aviso de operacao sucesso
	 */
	public static void operacaoSucesso(){
		  System.out.println("Opera��o realizada com Sucesso\n");
	  }
	  
	  /**
	 * Aviso de operacao insucesso
	 */
	public static void operacaoInsucesso(){
		  System.out.println("Aconteceu um erro no decorrer da opera��o\n");
	  }
	  
	  /**
	 * Aviso sem dados
	 */
	public static void semDados(){
		  System.out.println("Sem dados\n");
	  }
	  
	  /**
	 * Aviso sem permissao
	 */
	public static void semPermissao() {
		  System.out.println("Sua conta n�o tem permiss�o para aceder a esta op��o!\n");
	  }
	  
	  /**
	 * aviso sem campo disponivel
	 */
	public static void semCampo() {
		  System.out.println("A sua conta n�o tem o campo disponivel que escolheu!\n");
	  }
	  
	  /**
	 * Aviso categoria existente
	 */
	public static void existenciaCategoria() {
			System.out.println("Erro, categoria j� existe \n");
	 }
	  
	  /**
	 * Aviso categoria nao existe
	 */
	public static void naoExistenciaCategoria() {
			System.out.println("Erro, categoria n�o existe \n");
		}
	  
	 //Metodo para continuar, faz uma pausa..
	 /**
	 * @param aTeclado
	 * Aviso continuar para menu
	 */
	public static void continua(Scanner aTeclado){
		 System.out.println("Clique enter para voltar ao menu");
			aTeclado.nextLine();
	 }
	 
	 /**
	 * @param aTeclado
	 * Aviso proxima pagina
	 */
	public static void continuaLista(Scanner aTeclado){
		 System.out.println("Clique enter para visualizar a proxima pagina");
			aTeclado.nextLine();
	 }
	 
	 /**
	 * @param aTeclado
	 * Aviso fim de lista
	 */
	public static void continuaListaFim(Scanner aTeclado){
		 System.out.println("Fim de Lista clique enter..");
			aTeclado.nextLine();
	 }

}
