package PedidosNotificacoes;

/**
 * @author Jorge Martins
 * Class de Notificação, usadas para notificações mais especificas (ex a um ID em especifico)
 */
public class Notificacao {

	private int id;
	private int idEncomenda;
	private int idUtilizador;
	private int idPedido = 0;
	private String estado;
	private String descricao;
	
	
	/**
	 * @param aId
	 * @param aIdEncomenda
	 * @param aIdUtilizador
	 * @param aEstado
	 * @param aDescricao
	 * Construtor para uma notificação que é inicializada vinda da Base de Dados
	 */
	public Notificacao(int aId, int aIdEncomenda, int aIdUtilizador, String aEstado, String aDescricao) {
		id = aId;
		idEncomenda = aIdEncomenda;
		idUtilizador = aIdUtilizador;
		
		estado = aEstado;
		descricao = aDescricao;
		
	}
	
	/**
	 * @param aIdEncomenda
	 * @param aIdUtilizador
	 * @param aEstado
	 * @param aDescricao
	 * Construtor para uma notificação gerada pelo codigo no decorrer do software para em seguida ser guardada
	 * em base de dados
	 */
	public Notificacao(int aIdEncomenda, int aIdUtilizador, String aEstado, String aDescricao) {
		idEncomenda = aIdEncomenda;
		idUtilizador = aIdUtilizador;
		
		estado = aEstado;
		descricao = aDescricao;
		
	}
	
	
	

	/**
	 * @param idPedido
	 */
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return idencomenda
	 */
	public int getIdEncomenda() {
		return idEncomenda;
	}


	/**
	 * @return idUtilizador
	 */
	public int getIdUtilizador() {
		return idUtilizador;
	}


	/**
	 * @return idPedido
	 */
	public int getIdPedido() {
		return idPedido;
	}

	/**
	 * @return estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 * Muda o estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	
	/**
	 *Metodo toString da Classe
	 */
	public String toString() {
		return descricao;
	}
	
	
	
}
