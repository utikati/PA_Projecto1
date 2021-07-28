package Sistema;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Jorge Martins
 * Classe que calcula o tempo de execução do software desde que ligou ate encerrar
 */
public class TempoExecucao {
	private static Date inicio;
	private static Date fim;
	Calendar cal1 = Calendar.getInstance();
	Calendar cal2 = Calendar.getInstance();
	String format1;
	String format2;
	String dia [] = {" ","Domingo","Segunda - Feira","Terça - Feira","Quarta - Feira","Quinta - Feira",
					"Sexta - Feira", "Sabado"};
	
	/**
	 * Construtor da classe inicia logo com a gravação da data pelo qual iniciou o sistema
	 */
	public TempoExecucao() {
		inicio = new Date();
		cal1.setTime(inicio);
		format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(inicio);
	}
	
	/**
	 * A data de encerramento tem de ser dada pelo set fim para indicar com precisão a data final 
	 * e usar a mesma classe sem estar a usar dois objectos (por isso sao static)
	 */
	public void setFim() {
		fim = new Date();
		cal2.setTime(fim);
		format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(fim);
	}
	
	/**
	 * @return resultado em milisegundos da diferença entre o inicio e o fim do programa
	 */
	private long execucao() {
		return cal2.getTimeInMillis() - cal1.getTimeInMillis();
	}
	
	
	/**
	 * Metodo generico de toString que indica as informaçoes do inicio do programa com dia da semana
	 * hora e data e fim do processo do programa
	 * Indica também o tempo de execução do mesmo (diferença entre inicio e fim)
	 */
	public String toString() {
		
		return "Inicio do Processo : "+dia[cal1.get(Calendar.DAY_OF_WEEK)]+", "+format1+
			 "\nFim do Processo :    "+dia[cal1.get(Calendar.DAY_OF_WEEK)]+", "+format2+"\n"+
			   "Tempo de execução:   "+execucao()+" Milisegundos ("+ (int) Math.round((execucao()*0.001))+" Segundos "+ 
			 (int) Math.round((execucao()*0.000016666666666667))+" Minutos "+(int) Math.round(((execucao()*2.7777777778*(Math.pow(10, -7)))))+" Horas )";
	
	}
	
}
