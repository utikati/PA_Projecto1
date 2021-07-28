package Produtos;

import java.util.*;

/**
 * @author Jorge Martins
 * Classe encomenda, relativa à tabela encomenda na base de dados
 */
public class Encomenda {
	private int idEncomenda;
	private int idUtilizador;
	private int idCliente;
	private int idArmazenista;
	private int idEstafeta;
	private String identificadorEncomenda;
	private float custo;
	private Date dataCriacao;
	private Date dataAceitacao;
	private Date dataEntrega;
	private String estado;
	
	
	/**
	 * @param aIdentificadorEncomenda
	 * @param aCusto
	 * @param aDataCria
	 * @param aEstado
	 * @param aIdCliente
	 * Construtor com a instanciação das suas variaveis da classe
	 */
	public Encomenda(String aIdentificadorEncomenda, float aCusto, Date aDataCria, String aEstado, int aIdCliente) {
		identificadorEncomenda = aIdentificadorEncomenda;
		custo = aCusto;
		dataCriacao = aDataCria;
		estado = aEstado;
		idCliente = aIdCliente;
	}
	
	/**
	 * Construtor vazio para iniciar um objecto sem instanciação de variaveis
	 */
	public Encomenda() {
		
	}
	
	/**
	 * Metodo generico toString
	 */
	public String toString() {
		return "Identificador Encomenda: "+identificadorEncomenda+" custo: "+custo+" datacriacao: "+dataCriacao+" estado: "+estado+"\n";
	}


	/**
	 * @return idEncomenda
	 */
	public int getIdEncomenda() {
		return idEncomenda;
	}


	/**
	 * @param idEncomenda
	 * Modificar o id da encomenda
	 */
	public void setIdEncomenda(int idEncomenda) {
		this.idEncomenda = idEncomenda;
	}


	/**
	 * @return idUtilizadores
	 */
	public int getIdUtilizador() {
		return idUtilizador;
	}


	/**
	 * @param idUtilizador
	 * A encomenda é iniciada com um cliente mas sem Gestor este serve para num objecto com uma encomenda
	 * existente assim inserir um gestor
	 */
	public void setIdUtilizador(int idUtilizador) {
		this.idUtilizador = idUtilizador;
	}


	/**
	 * @return idArmazenista
	 */
	public int getIdArmazenista() {
		return idArmazenista;
	}


	/**
	 * @param idArmazenista
	 * Inserir um armazenista pois a encomenda inicia apenas com cliente
	 */
	public void setIdArmazenista(int idArmazenista) {
		this.idArmazenista = idArmazenista;
	}


	/**
	 * @return idEstafeta
	 */
	public int getIdEstafeta() {
		return idEstafeta;
	}


	/**
	 * @param idEstafeta
	 * Inserir um estafeita pois encomenda apenas inicia com cliente
	 */
	public void setIdEstafeta(int idEstafeta) {
		this.idEstafeta = idEstafeta;
	}


	/**
	 * @return inserir custo
	 */
	public float getCusto() {
		return custo;
	}


	/**
	 * @param custo
	 * Modificando o custo no decorrer da conta das tabelas
	 */
	public void setCusto(float custo) {
		this.custo = custo;
	}


	/**
	 * @return DataCriação
	 */
	public Date getDataCriacao() {
		return dataCriacao;
	}



	/**
	 * @return DataEntrega
	 */
	public Date getDataEntrega() {
		return dataEntrega;
	}


	/**
	 * @param dataEntrega
	 * Encomenda iniciar com dataEntrega a null, aqui modifica quando esta é entregue
	 */
	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}


	/**
	 * @return estado
	 */
	public String getEstado() {
		return estado;
	}


	/**
	 * @param estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}


	/**
	 * @return idCliente
	 */
	public int getIdCliente() {
		return idCliente;
	}


	/**
	 * @return Identificador de encomenda
	 */
	public String getIdentificadorEncomenda() {
		return identificadorEncomenda;
	}


	/**
	 * @return Data de Aceitação
	 */
	public Date getDataAceitacao() {
		return dataAceitacao;
	}
	
	/**
	 * @param aData
	 * Iniciar com data de aceitação a null, serve para modificar sua data quando for a 
	 * encomenda aceite
	 */
	public void setDataAceitacao(Date aData) {
		dataAceitacao = aData;
	}
	
	
	
	
}
