package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import webapp.SceneServ;

public class Datas {
	
	public static void initScene2BdD(int id,String groupe,String datec,String heure,String duree) {
		
		
/**
 *  * * 
 * la m�thode initGroupeBdD permet 
 * 1 - d'initialiser la combo box de s�lection depuis la base de donn�es de la table <b>Groupe</b>
 * 2 - de cr�er une instance de la classe <b>Groupe</b> pour chaque occurrence de la table Groupe
 * 3 - de renseigner l'arrayList <b>listeGroupe</b> permettant la gestion de la MAJ de la
 * 		programmation
 * 
 * 
 */
		Connection conn = SceneServ.connection;
		try {
			System.out.println("connexion =" + conn);
			
			Statement stmt = conn.createStatement();
			
			String reqSql = "insert into Scene2 values ('"+id+"','"+groupe+"','"+datec+"','"+heure+"','"+duree+"')";
					
			stmt.executeUpdate(reqSql);

	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
public static String lastId() {

	String id = "";
	Connection conn = SceneServ.connection;
	
	try {
		Statement stmt = conn.createStatement();
		String reqSql = "select max(id) from Scene2";
		ResultSet result =stmt.executeQuery(reqSql);
		
		while( result.next() ){

			id = result.getString (1); 
//			String numduree = result.getString (2); 
			if(id == null) {
				id = "0";
			}
		}
		result.close();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return id;
}

public static ArrayList<String[]> listScene() {

ArrayList<String[]> myNumberList = new ArrayList<String[]>();
Connection conn = SceneServ.connection;


try {
	Statement stmt = conn.createStatement();
	String reqSql = "select * from Scene2 order by id";
	ResultSet result =stmt.executeQuery(reqSql);

	while( result.next() ){
		
		String[] strTab = new String[5];

		String id = result.getString (1); 
 		String groupe = result.getString (2);
		String datec = result.getString (3); 
 		String heure = result.getString (4);
 		String duree = result.getString (5);
 		
 		strTab[0]=id;
 		strTab[1]=groupe;
 		strTab[2]=datec;
 		strTab[3]=heure;
 		strTab[4]=duree;
 		
 		myNumberList.add(strTab);

	}
	result.close();

} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

return myNumberList;
}
public static void suppSceneBdD(int id) {
	// TODO Auto-generated method stub
	Connection conn = SceneServ.connection;
	try {
		System.out.println("connexion =" + conn);
		
		Statement stmt = conn.createStatement();
		
		String reqSql = "delete Scene2 where id="+id;
				
		stmt.executeUpdate(reqSql);


	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

public static ArrayList<String[]> modifSceneBdD(int id) {
	// TODO Auto-generated method stub
	ArrayList<String[]> liste = new ArrayList<String[]>();
	Connection conn = SceneServ.connection;
	
	try {
		Statement stmt = conn.createStatement();
		String reqSql = "select * from Scene2 where id="+id;
		ResultSet result =stmt.executeQuery(reqSql);

		while( result.next() ){

			String[] strTab = new String[4];
			
	 		String groupe = result.getString (2);
			String date = result.getString (3); 
	 		String heure = result.getString (4);
	 		String duree = result.getString (5);

	 		strTab[0]=groupe;
	 		strTab[1]=date;
	 		strTab[2]=heure;
	 		strTab[3]=duree;
	 		
	 		liste.add(strTab);
	 		
	 				}
		result.close();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	return liste;
}

public static void majScene2BdD(int id, String groupe, String datec, String heure, String duree) {
	// TODO Auto-generated method stub
	/**
	 *  * * 
	 * la m�thode initGroupeBdD permet 
	 * 1 - d'initialiser la combo box de s�lection depuis la base de donn�es de la table <b>Groupe</b>
	 * 2 - de cr�er une instance de la classe <b>Groupe</b> pour chaque occurrence de la table Groupe
	 * 3 - de renseigner l'arrayList <b>listeGroupe</b> permettant la gestion de la MAJ de la
	 * 		programmation
	 * 
	 * 
	 */
			Connection conn = SceneServ.connection;
			try {
				System.out.println("connexion =" + conn);
				
				PreparedStatement updateTest = conn.prepareStatement( "update Scene2 set groupe =? , datec=? , heure=? , duree=? where id = ? " );
				
				updateTest.setString(1,groupe); 
				updateTest.setString(2,datec);
				updateTest.setString(3,heure); 
				updateTest.setString(4,duree);
				updateTest.setInt(5,id);
				
				int res = updateTest.executeUpdate();

//				Statement stmt = conn.createStatement();
				
//				String reqSql = "update Scene2 set (groupe="+groupe+"','date="+datec+"','heure="+heure+"','duree="+duree+""
//						+ "where id="+id;
//						
//				stmt.executeUpdate(reqSql);

		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
}

}
