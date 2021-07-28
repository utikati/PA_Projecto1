package Utilizadores;

/**
 * @author Jorge Martins
 *
 */
public class Utilizadores {
	
	private String nome;
	private String login;
	private String pass;
	private String estado;
	private String email;
	private String tipo;
	
	/**
	 * @param aNome
	 * @param aLogin
	 * @param aPass
	 * @param aEstado
	 * @param aEmail
	 * @param aTipo
	 * Construtor do objecto Utilizadores, classe Pai de Clientes e "Avo" de Funcionarios
	 */
	public Utilizadores(String aNome, String aLogin, String aPass, String aEstado, String aEmail, String aTipo) {
		nome = aNome;
		login = aLogin;
		pass = aPass;
		estado = aEstado;
		email = aEmail;
		tipo = aTipo;
	}
	//usarei em caso de erro o null para verificar.
	/**
	 * Construtor vazio da classe
	 */
	public Utilizadores() {
		this("Sem nome", null, null, null, null, null);
	}
	
	/**
	 * Metodo Generico toString
	 */
	public String toString() {
		return "Nome: " + nome + " Login: " + login + " Tipo: " + tipo + "\n";
	}

	//Get e Setter
	/**
	 * @return Nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param aNome
	 * Mudar o nome, mudança de dados
	 */
	public void setNome(String aNome) {
		nome = aNome;
	}

	/**
	 * @return Login
	 */
	public String getLogin() {
		return login;
	}


	/**
	 * @return Password
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass
	 * Mudança da sua password
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * @return estado de conta
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 * Mudança do estado de conta
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 * mudança do seu contacto de email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return tipo
	 * retorna o tipo de conta (gestor, funcionario, cliente)
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 * Mudar o seu tipo de conta, não implementado apenas para evolução futura do software
	 * gestor puder dar upgrade nas contas
	 * 
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
