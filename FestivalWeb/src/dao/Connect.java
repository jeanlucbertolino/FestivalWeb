package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	
	public static Connection initConnexion() {
		// Connexion BdD Déclaration
/**
 * Cette méthode crée une instance de connexion avec la base de données oracle et
 * retourn el'adresse de l'instance de connexion
 */
		Connection conn=null;
		String strNomDriver = "oracle.jdbc.driver.OracleDriver";
		String dbURL = "jdbc:oracle:thin:stag02/stag02pw@junon:1521:AFPA";
//		String dbURL = "jdbc:oracle:thin:jluc/honolulu@LocalHost:1521:ORCL";

		try {
			Class.forName(strNomDriver);
			conn = DriverManager.getConnection(dbURL);
		}
		catch(ClassNotFoundException e){ // 1
			System.err.println("Erreur Appel2Connexion1 : " + e);
		}
		catch (SQLException s){ // 2
			System.err.println("Erreur Appel2Connexion2 : "
					+ s.getSQLState() + " , "
					+ " (" + s + ")" );
		}
		
		return conn;
	}

	// Déconnexion BdD Déclaration
	public void initDeConnexion(Connection conn) {

	}
}
