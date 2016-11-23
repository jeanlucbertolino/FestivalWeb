package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import metier.Cdifestivalband;
import metier.Duree;
import metier.Heure;
import webapp.Controleur;
import webapp.SceneServ;

public class Datas {
	
	public static void initScene2BdD(int id,String groupe,String datec,String heure,String duree) {
		
		
/**
 *  * * 
 * la méthode initGroupeBdD permet 
 * 1 - d'initialiser la combo box de sélection depuis la base de données de la table <b>Groupe</b>
 * 2 - de créer une instance de la classe <b>Groupe</b> pour chaque occurrence de la table Groupe
 * 3 - de renseigner l'arrayList <b>listeGroupe</b> permettant la gestion de la MAJ de la
 * 		programmation
 * 
 * 
 */
		Connection conn = Controleur.connection;

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
	/**
	 *  * * 
	 * la méthode lastId() permet 
	 * de récupérer la valeur la plus élevée de l'ID de la table Scene2 afin de l'incrémenter (+1)
	 * dans le cadre de la création d'une nouvelle planification
	 * 
	 */	
public static int lastId() {

	int id = 0;
	Connection conn = Controleur.connection;

	
	try {
		Statement stmt = conn.createStatement();
//		String reqSql = "select max(id) as maxid from Scene2";
		String reqSql = "SELECT id from Scene2 ORDER BY id DESC";
		ResultSet result =stmt.executeQuery(reqSql);
		int top=0;
		while( result.next() ){
			if (top==0) {
				System.out.println("top0 ...:"+top);
//			id = result.getString (1);
			top=1;
				System.out.println("top1 ...:"+top);
			id = result.getInt(1);
				System.out.println("Idmax ...:"+id);
			}
//			String numduree = result.getString (2); 
//			if(id == null) {
//				id = "0";
//			}
		}
		result.close();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return id;
}
/**
 *  * * 
 * la méthode listScene() permet 
 * de récupérer la liste de toutes les programmations actives (ID inclu) pour une scène
 * dans le but de la transmettre aux formulaires qui devront l'afficher
 */
public static ArrayList<String[]> listScene() {

ArrayList<String[]> myNumberList = new ArrayList<String[]>();
Connection conn = Controleur.connection;

try {
	Statement stmt = conn.createStatement();
	String reqSql = "select id,groupe,datec,heure,duree from Scene2 order by id";
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

/**
 *  * * 
 * la méthode suppSceneBdD permet 
 * de supprimer la programmation d'une programmation de scene en récupérant son ID
 */
public static void suppSceneBdD(int id) {
	// TODO Auto-generated method stub
	Connection conn = Controleur.connection;

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
/**
 *  * * 
 * la méthode modifSceneBdD permet 
 * de sélectionner une programmation de scene en récupérant son ID
 */
public static ArrayList<String[]> modifSceneBdD(int id) {
	// TODO Auto-generated method stub
	ArrayList<String[]> liste = new ArrayList<String[]>();
	Connection conn = Controleur.connection;
	
	try {
		Statement stmt = conn.createStatement();
		String reqSql = "select id,groupe,datec,heure,duree from Scene2 where id="+id;
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
/**
 *  * * 
 * la méthode majScene2BdD permet 
 * de modifier la programmation d'une scene en récupérant son ID
 */
public static void majScene2BdD(int id, String groupe, String datec, String heure, String duree) {
	// TODO Auto-generated method stub

			Connection conn = Controleur.connection;

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

public static ArrayList<String[]> SearchScene(String £groupe, String £datec, String £heure, String £duree) {
	// TODO Auto-generated method stub

ArrayList<String[]> myNumberList = new ArrayList<String[]>();
Connection conn = Controleur.connection;


try {
	Statement stmt = conn.createStatement();
	String reqSql = "select id,groupe,datec,heure,duree from Scene2 where groupe like '"+£groupe+"%' and datec like '"+£datec+"%' and heure like '"+£heure+ "%' and duree like '"+£duree+"%'";
	
	System.out.println("ReqSql ...:"+reqSql);
	
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
/**
 *  * * 
 * la méthode initGroupe() permet 
 * 1 - d'initialiser la combo box de sélection des groupes depuis la base de données de la table <b>Groupe</b>
 * 2 - de créer une instance de la classe <b>Groupe</b> pour chaque occurrence de la table Groupe
 * 3 - de renseigner l'arrayList <b>listeGroupe</b> permettant la gestion de la MAJ de la
 * 		programmation
 * 
 * 
 */
public static ArrayList<String[]> initGroupe() {
	// TODO Auto-generated method stub
	ArrayList<String[]> groupeList = new ArrayList<String[]>();
	Connection conn = Controleur.connection;

	try {
		Statement stmt = conn.createStatement();
		String reqSql = "select band_id,band_name,band_biography,band_website from cdifestival_band order by band_id";
		
		System.out.println("ReqSql ...:"+reqSql);
		
		ResultSet result =stmt.executeQuery(reqSql);

		while( result.next() ){
			
			int bandid = result.getInt(1);
			String bandname = result.getString(2);
			String bandbiography = result.getString(3);
			String bandwebsite = result.getString(4);
			
			// Création d'un objet cdifestivalband pour chaque enregistrement de la table cdifestivalband sélectionné			
			Cdifestivalband cdifestivalband = new Cdifestivalband(bandid,bandname,bandbiography,bandwebsite);
			
			// Renseigner la liste à renvoyer à la servlet
			String[] strTab = new String[4];

			int bandId = cdifestivalband.getBand_id();
			String bandName = cdifestivalband.getBand_name();
			String bandBiography = cdifestivalband.getBand_biography();
			String bandWebsite = cdifestivalband.getBand_website();
	 		
			// Conversion ID durée en String
			String bandIdS = Integer.toString(bandId);
	 		strTab[0]= bandIdS;
	 		strTab[1]=bandName;
	 		strTab[2]=bandBiography;
	 		strTab[3]=bandBiography;
	 		
	 		groupeList.add(strTab);

		}
		result.close();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return groupeList;
}

/**
*  * * 
* la méthode initHeure() permet 
* 1 - d'initialiser la combo box de sélection des heures autorisées depuis la base de données de la table <b>Heure</b>
* 2 - de créer une instance de la classe <b>Heure</b> pour chaque occurrence de la table Heure
* 3 - de renseigner l'arrayList <b>listeHeure</b> permettant la gestion de la MAJ de la
* 		programmation
*
*/
public static ArrayList<String[]> initHeure() {
	// Constitution des instances de la classe Heure + ArrayList des Heures
		
	ArrayList<String[]> heureList = new ArrayList<String[]>();
	Connection conn = Controleur.connection;

	try {
		Statement stmt = conn.createStatement();
		String reqSql = "select idheure,numheure,numminute from Heure order by idheure";
		
		System.out.println("ReqSql ...:"+reqSql);
		
		ResultSet result =stmt.executeQuery(reqSql);

		while( result.next() ){
			
			int idHeure = result.getInt(1);
			String numHeure = result.getString(2);
			String numMinute = result.getString(3);
			
			// Création d'un objet Heure pour chaque enregistrement de la table Heure sélectionné			
			Heure heure = new Heure(idHeure,numHeure,numMinute);
			
			// Renseigner la liste à renvoyer à la servlet
			String[] strTab = new String[3];

			int idheure = heure.getIdHeure();
			String numheure = heure.getNumHeure();
			String numminute = heure.getNumMinute();
	 		
			// Conversion ID durée en String
			String idheureS = Integer.toString(idheure);
	 		strTab[0]= idheureS;
	 		strTab[1]=numheure;
	 		strTab[2]=numminute;
	 		
	 		heureList.add(strTab);

		}
		result.close();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return heureList;
}


/**
 *  * * 
 * la méthode initDuree() permet 
 * 1 - d'initialiser la combo box de sélection depuis la base de données de la table <b>Durée</b>
 * 2 - de créer une instance de la classe <b>Duree</b> pour chaque occurrence de la table Duree
 * 3 - de renseigner l'arrayList <b>dureeList</b> permettant la gestion de la MAJ de la
 * 		programmation
 */
public static ArrayList<String[]> initDuree() {
	// Constitution des instances de la classe Duree + ArrayList des durées
	
	ArrayList<String[]> dureeList = new ArrayList<String[]>();
	Connection conn = Controleur.connection;

	try {
		Statement stmt = conn.createStatement();
		String reqSql = "select idduree,numduree from Duree order by idduree";
		
		System.out.println("ReqSql ...:"+reqSql);
		
		ResultSet result =stmt.executeQuery(reqSql);

		while( result.next() ){
			
			int idDuree = result.getInt(1);
			String numDuree = result.getString(2);
			
			// Création d'un objet Duree pour chaque enregistrement de la table Duree sélectionné			
			Duree duree = new Duree(idDuree,numDuree);
			
			// Renseigner la liste à renvoyer à la servlet
			String[] strTab = new String[2];

			int idduree = duree.getId();
			String numduree = duree.getNumDuree();
	 		
			// Conversion ID durée en String
			String iddureeS = Integer.toString(idduree);
	 		strTab[0]= iddureeS;
	 		strTab[1]=numduree;
	 		
	 		dureeList.add(strTab);

		}
		result.close();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	return dureeList;
}
public static Boolean controlplanif(String groupe,String datec,String heure,String duree) {
	// Contrôle si pas de programmation prévue pour un autre groupe à cette Date/heure
	
	Connection conn = Controleur.connection;
	Boolean okplanif=false;
	
	try {
		Statement stmt = conn.createStatement();
		String reqSql = "select id from Scene2 where datec='"+datec+"' and heure='"+heure+"'";
		
		System.out.println("ReqSql ...:"+reqSql);
		
		ResultSet result =stmt.executeQuery(reqSql);
		
		while( result.next() ){
			
			System.out.println("planification existe déjà ...:"+groupe);	
			okplanif=true;
		}
		result.close();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return okplanif;
}

}
