package Produtos;

/**
 * @author Jorge Martins
 * Classe referente à categoria do produto
 */
public class Categoria {
	
	private String designacao;
	private String classificacao;
	
	/**
	 * @param descricao
	 * @param classificacao
	 * Construtor da classe 
	 */
	public Categoria(String descricao, String classificacao) {
		
		this.designacao = descricao;
		this.classificacao = classificacao;
	}
	/**
	 * Construtor vazio para iniciar o objecto sem instanciar as variaveis
	 */
	public Categoria() {
		this("sem descricao", "sem classificacao");
	}

	/**
	 * @return String Designacao da categoria
	 */
	public String getDesignacao() {
		return designacao;
	}

	/**
	 * @return String Classificação da Categoria
	 */
	public String getClassificacao() {
		return classificacao;
	}

	/**
	 * Metodo toString da classe Categoria
	 */
	@Override
	public String toString() {
		return "Categoria [descricao=" + designacao + ", classificacao=" + classificacao + "]";
	}

	
	
	
	

}
