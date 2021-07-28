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
		System.out.println("Erro, só pode usar esta opção quanto regularizar os stocks dos produtos, existem produtos com stock negativo \n");
	}
	
	/**
	 * Aviso Encomenda nao iniciada
	 */
	public static void encomendaNaoIniciada() {
		System.out.println("Encomenda não se encontra no estado iniciada");
	}
	
	/**
	 * Aviso de login rejeitado
	 */
	public static void avisoRejeitadoLogin(){
		System.out.println("Erro, este login foi rejeitado por um gestor da aplicação!\n");
	}
	
	/**
	 * Aviso de nao puder rejeitar login
	 */
	public static void avisoMudanca(){
		System.out.println("Erro, login já foi aceite não poderá rejeitar, use alterar estado de conta para inactivar \n");
	}
	
	/**
	 * Aviso de login não estar com pedido de remoção
	 */
	public static void avisoMudanca2(){
		System.out.println("Erro, login não se encontra com pedido de remoção, use alterar estado de conta para inactivar \n");
	}
	
	/**
	 * Aviso de não usar caracteres especiais no login nome o pass
	 */
	public static void avisoMensagem(){
		System.out.println("Não pode usar ; no login, nome ou pass, e caracteres especiais no nome\n");
	}
	
	/**
	 * Aviso para inserir caracteres
	 */
	public static void avisoString(){
		System.out.println("Erro, insira caracteres para este campo, não inicie que espaço o que inserir\n");
	}
	
	/**
	 * Aviso login nao existe
	 */
	public static void avisoNaoExistenciaLog(){
		System.out.println("Erro, login não existe \n");
	}
	
	/**
	 * Aviso de existencia de login na base de dados
	 */
	public static void avisoExistenciaLog(){
		System.out.println("Erro, login já existe \n");
	}
	
	/**
	 * Aviso de existencia de email
	 */
	public static void existenciaEmail() {
		System.out.println("Erro, email já existe \n");
	}
	
	/**
	 * Aviso de existencia de contribuinte
	 */
	public static void existenciaContribuinte() {
		System.out.println("Erro, contribuinte já existe \n");
	}
	
	/**
	 * Aviso de existencia de contacto
	 */
	public static void existenciaContacto() {
		System.out.println("Erro, contacto já existe \n");
	}
	
	/**
	 * Aviso de erro
	 */
	public static void avisoErro() {
		System.out.println("Erro! Não foi possivel realizar a sua acção! \n");
	}
	
	//mensagem de sucesso e insucesso
	  /**
	 * Aviso de operacao sucesso
	 */
	public static void operacaoSucesso(){
		  System.out.println("Operação realizada com Sucesso\n");
	  }
	  
	  /**
	 * Aviso de operacao insucesso
	 */
	public static void operacaoInsucesso(){
		  System.out.println("Aconteceu um erro no decorrer da operação\n");
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
		  System.out.println("Sua conta não tem permissão para aceder a esta opção!\n");
	  }
	  
	  /**
	 * aviso sem campo disponivel
	 */
	public static void semCampo() {
		  System.out.println("A sua conta não tem o campo disponivel que escolheu!\n");
	  }
	  
	  /**
	 * Aviso categoria existente
	 */
	public static void existenciaCategoria() {
			System.out.println("Erro, categoria já existe \n");
	 }
	  
	  /**
	 * Aviso categoria nao existe
	 */
	public static void naoExistenciaCategoria() {
			System.out.println("Erro, categoria não existe \n");
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
