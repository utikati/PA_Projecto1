package Utilizadores;

/**
 * @author Jorge Martins
 *Objecto Cliente herda do objecto Utilizadores
 */
public class Clientes extends Utilizadores {
	
	private int contribuinte;
	private int contacto;
	private String morada;
	
	/**
	 * @param aNome
	 * @param aLogin
	 * @param aPass
	 * @param aEstado
	 * @param aEmail
	 * @param aTipo
	 * @param aContribuinte
	 * @param aContacto
	 * @param aMorada
	 * Construtor do Objecto Cliente
	 */
	public Clientes(String aNome, String aLogin, String aPass, String aEstado, String aEmail, String aTipo, int aContribuinte, int aContacto, String aMorada) {
		super(aNome, aLogin, aPass, aEstado, aEmail, aTipo);
		contribuinte = aContribuinte;
		contacto = aContacto;
		morada = aMorada;
	}
	
	/**
	 * Construtor vazio
	 */
	public Clientes() {
		super();
		contribuinte = 0;
		contacto = 0;
		morada = "";
	}

	/**
	 * @return contribuinte
	 */
	public int getContribuinte() {
		return contribuinte;
	}
	
	/**
	 * @param aContribuinte
	 * Alterar seus dados de contribuinte
	 */
	public void setContribuinte(int aContribuinte) {
		contribuinte = aContribuinte;
	}


	/**
	 * @return contacto
	 */
	public int getContacto() {
		return contacto;
	}

	/**
	 * @param aContacto
	 * alterar seus dados de contacto
	 */
	public void setContacto(int aContacto) {
		contacto = aContacto;
	}

	/**
	 * @return String morada
	 */
	public String getMorada() {
		return morada;
	}

	/**
	 * @param aMorada
	 * Alterar a sua morada
	 */
	public void setMorada(String aMorada) {
		morada = aMorada;
	}

	/**
	 * Metodo Generico toString
	 */
	@Override
	public String toString() {
		return "Nome: " + super.getNome() + " Login: " + super.getLogin() + " Tipo: " + super.getTipo() + "\n";
	}
	
	

}
