package Utilizadores;
import java.util.*;

/**
 * @author Jorge Martins
 *
 */
public class Funcionarios extends Clientes {
	
	private Date dataInicio;
	private String especializacao;
	
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
	 * @param aDataInicio
	 * @param aEspecializacao
	 * Construtor do objecto Funcionarios
	 */
	public Funcionarios(String aNome, String aLogin, String aPass, String aEstado, String aEmail, String aTipo, int aContribuinte, int aContacto, String aMorada, Date aDataInicio, String aEspecializacao) {
		super(aNome, aLogin, aPass, aEstado, aEmail, aTipo, aContribuinte, aContacto, aMorada);
		dataInicio = aDataInicio;
		especializacao = aEspecializacao;
	}
	
	/**
	 * Construtor vazio do Objecto Funcionarios
	 */
	public Funcionarios() {
		super();
		dataInicio = null;
		especializacao = null;
	}

	/**
	 * @return Data de Inicio
	 */
	public Date getDataInicio() {
		return dataInicio;
	}

	/**
	 * @return Especializacao
	 */
	public String getEspecializacao() {
		return especializacao;
	}

	/**
	 * @param especializacao
	 * Modificar a especializacao (não implementado neste software)
	 */
	public void setEspecializacao(String especializacao) {
		this.especializacao = especializacao;
	}

	/**
	 * Metodo generico toString
	 */
	@Override
	public String toString() {
		return "Nome: " + super.getNome() + " Login: " + super.getLogin() + " Tipo: " + super.getTipo() + " Especializacao: "+especializacao+"\n";
	}
	
	
	

}
