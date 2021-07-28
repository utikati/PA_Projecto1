package Produtos;

/**
 * @author Jorge Martins
 * Classe relativa às quantidades de produtos dentro de uma encomenda (varios produtos e cada um
 * com sua quantidade)
 */
public class Encomenda_Produto {
	private int idEncomenda;
	private int idProduto;
	private int quantidadeProduto;
	
	/**
	 * @param aIdProduto
	 * @param aQuantidade
	 * Construtor da classe com o ID do produto e a sua quantidade
	 */
	public Encomenda_Produto(int aIdProduto, int aQuantidade) {
		idProduto = aIdProduto;
		quantidadeProduto = aQuantidade;
	}

	/**
	 * @return IDEncomenda
	 */
	public int getIdEncomenda() {
		return idEncomenda;
	}

	/**
	 * @param idEncomenda
	 * Modifica o id de encomenda
	 * Usado quando se vai buscar informação na base de dados ao iniciar o objecto
	 * é usado o construtor e em seguida realizado um set no objecto para lhe inserir o id da sua
	 * encomenda
	 */
	public void setIdEncomenda(int idEncomenda) {
		this.idEncomenda = idEncomenda;
	}

	/**
	 * @return idProduto
	 */
	public int getIdProduto() {
		return idProduto;
	}

	/**
	 * @return quantidade de Produto
	 */
	public int getQuantidadeProduto() {
		return quantidadeProduto;
	}

	/**
	 * Metodo generico toString
	 */
	@Override
	public String toString() {
		return "Encomenda_Produto [idEncomenda=" + idEncomenda + ", idProduto=" + idProduto + ", quantidadeProduto="
				+ quantidadeProduto + "]";
	}
	
	
}
