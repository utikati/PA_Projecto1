package PedidosNotificacoes;

/**
 * @author Jorge Martins
 * Classe do Pedido (neste software usado para Pedido de Entrega de encomenda)
 * Construído para que possa ser adaptado para mais tipos de pedido
 */
public class Pedido {
	private int idPedido = 0;
	private int idUtilizador;
	private int idEncomenda;
	private int idCliente;
	private int idFuncionario;
	private String tipoPedido;
	private String estado;
	
	/**
	 * @param aIdPedido
	 * @param aIdUtilizador
	 * @param aIdEncomenda
	 * @param aIdCliente
	 * @param aIdFuncionario
	 * @param aTipoPedido
	 * @param aEstado
	 * Construtor para um Pedido vindo da base de dados para a memoria da maquina 
	 * 
	 */
	public Pedido(int aIdPedido, int aIdUtilizador, int aIdEncomenda, int aIdCliente, int aIdFuncionario, String aTipoPedido,
	String aEstado) {
		idPedido = aIdPedido;
		idUtilizador = aIdUtilizador;
		idEncomenda = aIdEncomenda;
		idCliente = aIdCliente;
		idFuncionario = aIdFuncionario;
		tipoPedido = aTipoPedido;
		estado = aEstado;
	}
	
	/**
	 * @param aIdUtilizador
	 * @param aIdEncomenda
	 * @param aIdCliente
	 * @param aIdFuncionario
	 * @param aTipoPedido
	 * @param aEstado
	 * Construtor idealizado para um pedido que seja formado no software para em seguida ser guardado na
	 * base de dados relacional (o seu ID é gerado na base de dados)
	 */
	public Pedido(int aIdUtilizador, int aIdEncomenda, int aIdCliente, int aIdFuncionario, String aTipoPedido,
			String aEstado) {
				idUtilizador = aIdUtilizador;
				idEncomenda = aIdEncomenda;
				idCliente = aIdCliente;
				idFuncionario = aIdFuncionario;
				tipoPedido = aTipoPedido;
				estado = aEstado;
	}
	
	/**
	 * Construtor vazio apenas para iniciar o objecto sem que este instancie as suas variaveis
	 */
	public Pedido() {
		
	}
	
	
	/**
	 * @return idPedido
	 */
	public int getIdPedido() {
		return idPedido;
	}
	
	/**
	 * @return IdUtilizador
	 */
	public int getIdUtilizador() {
		return idUtilizador;
	}
	
	/**
	 * @return idEncomenda
	 */
	public int getIdEncomenda() {
		return idEncomenda;
	}
	
	/**
	 * @return idCliente
	 */
	public int getIdCliente() {
		return idCliente;
	}
	
	/**
	 * @return idFuncionario
	 */
	public int getIdFuncionario() {
		return idFuncionario;
	}
	
	/**
	 * @return String com o tipo de pedido (normalmente uma descrição)
	 */
	public String getTipoPedido() {
		return tipoPedido;
	}
	
	/**
	 * @return String com estado do pedido
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado
	 * Modificar o estado do pedido
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * String com informações acerca do pedido, metodo generico toString
	 */
	@Override
	public String toString() {
		return tipoPedido+" Com ID: "+idPedido+ " Do cliente: "+idCliente+ " Do utilizador: "+idUtilizador+" Id da encomenda: "+idEncomenda+" Estado: "+estado+"\n" ;
	}
	
	
	
}
