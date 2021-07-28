package AcessoBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import DadosEstaticos.DadosStatic;


/**
 * @author Jorge Martins
 * Classe de conexão à Base de Dados
 * Cada metodo 1 2 ou 3 deve ser usado em par, 1 com o 1 e 2 com o 2.
 */
public class DadosConnect {
	
    static Connection conn = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;
    
    static Connection conn2 = null;
    static PreparedStatement ps2 = null;
    static ResultSet rs2 = null;
    
    static Connection conn3 = null;
    static PreparedStatement ps3 = null;
    static ResultSet rs3 = null;
    
	/**
	 * Metodo 1 conecta com a BD
	 */
	public static void conecta() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
        	conn = DriverManager.getConnection("jdbc:mysql://"+DadosStatic.ip+":"+DadosStatic.porto+"/"+DadosStatic.baseDados+"?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC", DadosStatic.utilizador, DadosStatic.pass);
		}catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
           	e.printStackTrace();
		} catch (ClassNotFoundException e) {
				System.out.println("!! Class Not Found. Unable to load Database Drive !!\n"+e);
		}
		
    }
	/**
	 * Metodo 2 conecta com a BD
	 */
	public static void conecta2() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
        	conn2 = DriverManager.getConnection("jdbc:mysql://"+DadosStatic.ip+":"+DadosStatic.porto+"/"+DadosStatic.baseDados+"?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC", DadosStatic.utilizador, DadosStatic.pass);
		}catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
           	e.printStackTrace();
		} catch (ClassNotFoundException e) {
				System.out.println("!! Class Not Found. Unable to load Database Drive !!\n"+e);
		}
		
    }
	
	/**
	 * Metodo 3 conecta com a BD
	 */
	public static void conecta3() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
        	conn3 = DriverManager.getConnection("jdbc:mysql://"+DadosStatic.ip+":"+DadosStatic.porto+"/"+DadosStatic.baseDados+"?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC", DadosStatic.utilizador, DadosStatic.pass);
		}catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
           	e.printStackTrace();
		} catch (ClassNotFoundException e) {
				System.out.println("!! Class Not Found. Unable to load Database Drive !!\n"+e);
		}
		
    }
	
	
	/**
	 * Metodo 1 que desliga a ligação à base de dados de forma segura
	 */
	public static void desliga() {
		try {
			if(conn != null)
				conn.close();
			if(ps != null)
				ps.close();
			if(rs != null)
				rs.close();
		}catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
           	e.printStackTrace();
		}
	}
	
	/**
	 * Metodo 2 que desliga a ligação à base de dados de forma segura
	 */
	public static void desliga2() {
		try {
			if(conn2 != null)
				conn2.close();
			if(ps2 != null)
				ps2.close();
			if(rs2 != null)
				rs2.close();
		}catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
           	e.printStackTrace();
		}
	}
	
	/**
	 * Metodo 3 que desliga a ligação à base de dados de forma segura
	 */
	public static void desliga3() {
		try {
			if(conn3 != null)
				conn3.close();
			if(ps3 != null)
				ps3.close();
			if(rs3 != null)
				rs3.close();
		}catch (SQLException e) {
        	System.out.println("!! SQL Exception !!\n"+e);
           	e.printStackTrace();
		}
	}
	
}
