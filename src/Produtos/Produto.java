package Produtos;
import java.util.*;

/**
 * @author Jorge Martins
 *
 */
public class Produto {
	
	private String designacao;
	private String fabricante;
	private int quantidade;
	private float preco;
	private int sku;
	private String lote;
	private Date dataProducao;
	private Date dataValidade;
	private float peso;
	private Categoria categoria;
	
	/**
	 * @param designacao
	 * @param fabricante
	 * @param quantidade
	 * @param preco
	 * @param sku
	 * @param lote
	 * @param dataProducao
	 * @param aCategoria
	 * Construtor do objecto Produto
	 */
	public Produto(String designacao, String fabricante, int quantidade, float preco, int sku, String lote,
			Date dataProducao, Categoria aCategoria) {
		
		this.designacao = designacao;
		this.fabricante = fabricante;
		this.quantidade = quantidade;
		this.preco = preco;
		this.sku = sku;
		this.lote = lote;
		this.dataProducao = dataProducao;
		categoria = aCategoria;
	}
	
	/**
	 * Construtor vazio do objecto Produto, apenas para iniciar o objecto sem inserir dados
	 */
	public Produto() {
		this("Sem designacao", "sem fabricante", 0, 0, 0, "alote233", new Date(), new Categoria());
	}
	
	/**
	 * Metodo generico toString
	 */
	@Override
	public String toString() {
		return "Designacao: "+designacao+" Quantidade: "+quantidade+" SKU: "+sku+" preco: "+preco+"\n";
	}
	
	/**
	 * @return categoria
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * @return designacao
	 */
	public String getDesignacao() {
		return designacao;
	}

	/**
	 * @param designacao
	 * Modificar a sua designacao
	 */
	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	/**
	 * @return fabricante
	 */
	public String getFabricante() {
		return fabricante;
	}

	/**
	 * @param fabricante
	 * Modificar o seu fabricante
	 */
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	/**
	 * @return quantidade de produto
	 */
	public int getQuantidade() {
		return quantidade;
	}

	/**
	 * @param quantidade
	 * Modificar a sua quantidade / stock
	 */
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * @return preco
	 */
	public float getPreco() {
		return preco;
	}

	/**
	 * @param preco
	 * Modificar o seu preco
	 */
	public void setPreco(float preco) {
		this.preco = preco;
	}

	/**
	 * @return lote
	 */
	public String getLote() {
		return lote;
	}

	/**
	 * @param lote
	 * modificar o seu lote de produto
	 */
	public void setLote(String lote) {
		this.lote = lote;
	}

	/**
	 * @return Data de produção
	 */
	public Date getDataProducao() {
		return dataProducao;
	}

	/**
	 * @param dataProducao
	 * Modificar a sua data de produção
	 */
	public void setDataProducao(Date dataProducao) {
		this.dataProducao = dataProducao;
	}

	/**
	 * @return Data Validade do produto
	 */
	public Date getDataValidade() {
		return dataValidade;
	}

	/**
	 * @param dataValidade
	 * Modificar a sua data de validade
	 */
	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	/**
	 * @return peso 
	 */
	public float getPeso() {
		return peso;
	}

	/**
	 * @param peso
	 * Modificar o peso do produto
	 */
	public void setPeso(float peso) {
		this.peso = peso;
	}

	/**
	 * @return SKU
	 */
	public int getSku() {
		return sku;
	}
	
	
}
