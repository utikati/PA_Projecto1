package PedidosNotificacoes;

import java.util.ArrayList;
import java.util.Iterator;
import Produtos.*;
import AcessoBD.DadosEncomendas;
import AcessoBD.DadosNotificacao;
import AcessoBD.DadosPedido;
import AcessoBD.DadosUtilizadores;
import DadosEstaticos.DadosStatic;
import InterfacePrincipal.Principal;
import Produtos.MetodoStaticEncomendas;
import Utilizadores.MetodoStaticUtilizadores;
import Utilizadores.*;
import Aviso.*;
/**
 * @author Jorge Martins
 * Classe com os metodos static do Pedido, aqueles que pode interagir com o utilizador
 */
public class MetodoStaticPedido {
	
	/**
	 * @param IdEncomenda
	 * Listar os estafetas Disponiveis para aquela encomenda (tendo em conta aquele que rejeitou)
	 */
	public static void listarCondicaoEstafetaDisponiveis(int IdEncomenda) {
		ArrayList <Funcionarios> estafetas = DadosPedido.listarEstafetasDisponiveis(IdEncomenda);                         
		
		if(estafetas != null){
			Iterator <Funcionarios> tabela = estafetas.iterator();
			String envio = "";
			Funcionarios auxiliar;
			int contador = 0;
		       while(tabela.hasNext()) {
			       auxiliar = tabela.next();
			       envio += auxiliar;
			       contador++;		
			    
		       if(contador == 10) {
		    	   MetodoStaticUtilizadores.listadorMaster(" Estafetas Disponiveis \n" + envio + "\n", contador); //reutilizar o listador dos utilizadores em vez de repetir o metodo
		    	   contador = 0;
		    	   envio = "";
		       }
		       }
		       if(contador > 0) {
		    	   MetodoStaticUtilizadores.listadorMaster(" Estafetas Disponiveis \n" + envio + "\n", contador);
		       }else {
		    	   MetodoStaticUtilizadores.listadorMaster(" Estafetas Disponiveis \nSem dados\n", 0);
		       }
		}
	 }

	/**
	 * @param aCondicao
	 * Listar os pedidos de entrega de encomenda ao ID do estafeta
	 */
	public static void listarPedidos(String aCondicao) {
		ArrayList <Pedido> pedido = DadosPedido.listarPedidosCondicao(aCondicao);                       
		
		if(pedido != null){
			Iterator <Pedido> tabela = pedido.iterator();
			String envio = "";
			Pedido auxiliar;
			int contador = 0;
		       while(tabela.hasNext()) {
			       auxiliar = tabela.next();
			       envio += auxiliar;
			       contador++;		
			    
		       if(contador == 10) {
		    	   MetodoStaticUtilizadores.listadorMaster(" Pedidos Disponiveis \n" + envio + "\n", contador); //reutilizar o listador dos utilizadores em vez de repetir o metodo
		    	   contador = 0;
		    	   envio = "";
		       }
		       }
		       if(contador > 0) {
		    	   MetodoStaticUtilizadores.listadorMaster(" Pedidos Disponiveis \n" + envio + "\n", contador);
		       }else {
		    	   MetodoStaticUtilizadores.listadorMaster(" Pedidos Disponiveis \nSem dados\n", 0);
		       }
		}
	 }
	
	/**
	 * Criar um pedido de entrega a um estafeta
	 */
	public static void criarPedidoEstafeta() {
		MetodoStaticEncomendas.listarCondicao(" WHERE ESTADO_ENCOMENDA = 'preparada' AND FUN_ID_UTILIZADOR = "+DadosUtilizadores.verificaLogin(DadosStatic.Login)+" ");
		String identificador = Principal.pedeDadosString("Insira o identificador da encomenda a delegar", DadosStatic.teclado);
		if(DadosEncomendas.idEncomenda(identificador) > 0) {
			Encomenda encomenda = DadosEncomendas.objEncomenda(identificador);
			listarCondicaoEstafetaDisponiveis(encomenda.getIdEncomenda());
			String login = Principal.pedeDadosString("Insira o login do Estafeta para fazer pedido de Encomenda", DadosStatic.teclado);
			if(!DadosUtilizadores.verificaLogin(login).equals("") && 
					DadosUtilizadores.pesquisaLogin(login).getTipo().equals("Funcionario") && 
					DadosUtilizadores.pesquisaFuncionarios(login).getEspecializacao().equals("Estafeta")) {
					Pedido pedido = new Pedido(Integer.parseInt(DadosUtilizadores.verificaLogin(DadosStatic.Login)), encomenda.getIdEncomenda(), encomenda.getIdCliente(), Integer.parseInt(DadosUtilizadores.verificaLogin(login)), "Pedido de Encomenda para Entrega", "activo");
					DadosPedido.adicionaPedido(pedido);
					Notificacao noti = new Notificacao(encomenda.getIdEncomenda(), Integer.parseInt(DadosUtilizadores.verificaLogin(login)), "activa", "Notificacao - Pedido de Encomenda id "+encomenda.getIdEncomenda()+" para Entrega");
					DadosNotificacao.adicionaNotifica(noti);
					encomenda.setEstado(MetodoStaticEncomendas.estadoEncomenda(4));
					DadosEncomendas.updateEncomenda(encomenda);
					Aviso.operacaoSucesso();
			}
			else {
				Aviso.operacaoInsucesso();
			}
		}else {
			Aviso.avisoErro();
		}
	}
	
	
	/**
	 * Menu Para o estafeta aceitar ou rejeitar um pedido
	 */
	public static void menuAceitaRejeita(){
		int opcao = 0;
		listarPedidos(" WHERE FUN_ID_UTILIZADOR = "+DadosUtilizadores.verificaLogin(DadosStatic.Login)+" "
				+ " AND ESTADO_PEDIDO = 'activo' ");
		while(opcao != 3) {
			
			// apresentar menu
			apresentaMenuAceitaRejeita();
			// escolha opçao
			opcao = Principal.pedeDadosInteiro("Escolha a opção do menu inserindo um numero inteiro da opçao", DadosStatic.teclado);
			//executar opção
			executaOpcaoAceitaRejeita(opcao);
		}
	}

	//LISTAGEM DE OPCOES
	/**
	 * Listagem das opções do Estafeta no menu de aceitar ou rejeitar pedido de entrega
	 */
	private static void apresentaMenuAceitaRejeita() {
		System.out.println("1 - Aceitar Pedido de Entrega\n" +
				"2 -  Rejeitar Pedido de Entrega\n" +
				"3 -  Sair\n");
				
	}

	//OPCOES
	/**
	 * @param aOpcao
	 * Metodo que executa a opção do estafeta no pedido de entrega
	 */
	private static void executaOpcaoAceitaRejeita(int aOpcao) {
		switch(aOpcao) {
		case 1:  AceitaPedido(); break;
		case 2:  RejeitaPedido(); break;
		case 4:  break;
		default: Principal.opcaoErrada(); break;
		}
	}
	
	/**
	 * Metodo de aceitar um pedido de entrega de encomenda
	 */
	public static void AceitaPedido() {
		int id = Principal.pedeDadosInteiro("Insira o Id do pedido a aceitar ", DadosStatic.teclado);
		if(DadosPedido.verificaPedido(id) > 0) {
			if(DadosPedido.getPedidoBd(id).getIdFuncionario() == Integer.parseInt(DadosUtilizadores.verificaLogin(DadosStatic.Login))) {
				if(DadosPedido.verificarDisponibilidadeEstafeta(Integer.parseInt(DadosUtilizadores.verificaLogin(DadosStatic.Login))) > 0) {
					System.out.println("Só pode aceitar uma encomenda quando entregar a que está a decorrer. \n");
				}else {
					Pedido pedido = DadosPedido.getPedidoBd(id);
					Encomenda encomenda = DadosEncomendas.objEncomendaID(pedido.getIdEncomenda());
					encomenda.setIdEstafeta(Integer.parseInt(DadosUtilizadores.verificaLogin(DadosStatic.Login)));
					encomenda.setEstado(MetodoStaticEncomendas.estadoEncomenda(5));
					DadosEncomendas.updateEncomenda(encomenda);
					pedido.setEstado("inactivo");
					DadosPedido.updateEstadoPedido(pedido);
					Aviso.operacaoSucesso();
				}
			}
		}else {
			Aviso.avisoErro();
		}
	}
	
	/**
	 * Metodo de rejeitar um pedido de entrega de encomenda
	 */
	public static void RejeitaPedido() {
		int id = Principal.pedeDadosInteiro("Insira o Id do pedido a rejeitar ", DadosStatic.teclado);
		if(DadosPedido.verificaPedido(id) > 0) {
			if(DadosPedido.getPedidoBd(id).getIdFuncionario() == Integer.parseInt(DadosUtilizadores.verificaLogin(DadosStatic.Login))) {
					Pedido pedido = DadosPedido.getPedidoBd(id);
					Encomenda encomenda = DadosEncomendas.objEncomendaID(pedido.getIdEncomenda());
					encomenda.setEstado(MetodoStaticEncomendas.estadoEncomenda(3));
					DadosEncomendas.updateEncomenda(encomenda);
					pedido.setEstado("rejeitado");
					DadosPedido.updateEstadoPedido(pedido);
					Notificacao noti = new Notificacao(encomenda.getIdEncomenda(), encomenda.getIdArmazenista(), "activa", "Notificacao - Estafeta Rejeitou a Encomenda com ID"+encomenda.getIdEncomenda()+"\n");
					DadosNotificacao.adicionaNotifica(noti);
					Aviso.operacaoSucesso();
			}
		}else {
			Aviso.avisoErro();
		}
	}

	
	
}
