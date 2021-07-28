package PedidosNotificacoes;

import java.util.ArrayList;
import java.util.Iterator;
import AcessoBD.*;
import AcessoBD.DadosNotificacao;
import DadosEstaticos.DadosStatic;
import Utilizadores.MetodoStaticUtilizadores;

/**
 * @author Jorge Martins
 * Classe utilizada para gerar notificações
 */
public class MetodoStaticNotificacao {
	
	/**
	 * @param aCondicao
	 * Metodo estatico para listar as notificações com condição (ex Estado = 'activa')
	 */
	public static void listarCondicao(String aCondicao) {
		ArrayList <Notificacao> notificacao = DadosNotificacao.listarNotificacoesCondicao(aCondicao);
		
		if(notificacao != null){
			Iterator <Notificacao> tabela = notificacao.iterator();
			String envio = "";
			Notificacao auxiliar;
			int contador = 0;
		       while(tabela.hasNext()) {
			       auxiliar = tabela.next();
			       envio += auxiliar;
			       contador++;		
			    
		       if(contador == 10) {
		    	   MetodoStaticUtilizadores.listadorMaster(" Notificações \n" + envio + "\n", contador); //reutilizar o listador dos utilizadores em vez de repetir o metodo
		    	   contador = 0;
		    	   envio = "";
		       }
		       }
		       if(contador > 0) {
		    	   MetodoStaticUtilizadores.listadorMaster(" Notificações \n" + envio + "\n", contador);
		       }else {
		    	   MetodoStaticUtilizadores.listadorMaster("Notificações \nSem dados\n", 0);
		       }
		}
	 }
	
	
	/**
	 * Notificação acerca de encomendas (rejeitada ou pedido)
	 */
	public static void NotificacaoEncomenda() {
		listarCondicao(" WHERE ID_UTILIZADOR = "+DadosUtilizadores.verificaLogin(DadosStatic.Login)+" "
						+ "AND ESTADO_NOTIFICACAO = 'activa' ");
	}
	
	/**
	 * Notificação geral 
	 */
	public static void Notificacao() {
		listarCondicao(" WHERE ID_UTILIZADOR = "+DadosUtilizadores.verificaLogin(DadosStatic.Login)+" "
						+ "AND ESTADO_NOTIFICACAO = 'activa' ");
	}
	
	/**
	 * Notificação para novas encomendas pedidas por clientes
	 */
	public static void novasEncomendas() {
		if(DadosNotificacao.verificaNovasEncomendas() > 0) {
			System.out.println("Notificação - Existem novas Encomendas por Aceitar/Rejeitar \n");
		}
	}
	
	

	
	
}
