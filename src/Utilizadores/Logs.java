package Utilizadores;
import java.util.*;

/**
 * @author Jorge Martins
 *
 */
public class Logs {
	
	private int idUtilizador;
	private String acaoLog;
	private Date data;
	
	/**
	 * @param aIdUtilizador
	 * @param aAcaoLog
	 * @param aData
	 * Construtor para a objecto Logs
	 */
	public Logs(int aIdUtilizador, String aAcaoLog, Date aData) {
		idUtilizador = aIdUtilizador;
		acaoLog = aAcaoLog;
		data = aData;
	}

	/**
	 * @return idUtilizador
	 */
	public int getIdUtilizador() {
		return idUtilizador;
	}

	/**
	 * @return acaoLog
	 */
	public String getAcaoLog() {
		return acaoLog;
	}

	/**
	 * @return data
	 */
	public Date getData() {
		return data;
	}

	/**
	 * Metodo Generico toString
	 */
	@Override
	public String toString() {
		return "Logs [idUtilizador=" + idUtilizador + ", Ação=" + acaoLog + ", Data=" + data + "] \n";
	}
	
	



}
