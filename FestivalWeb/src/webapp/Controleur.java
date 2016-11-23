package webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Connect;
import dao.Datas;

/**
 * Servlet implementation class Controleur. Cette servlet permet de lancer l'application
 * et d'aiguiller les différents appels vers les MAJ de BdD et la gestion des transactions associées
 * l'annotation indique que toutes les URL ayant pour racine 'Festival/*' viendront se connecter à cette servlet de contrôle
 */
@WebServlet(
		name = "Controleur", 
		description = "Controleur General", 
		urlPatterns = {"/Festival/*"}
		)

/**
 * la classe controleur est de type servlet
 * On lui définit une variable de type connection initialisée depuis la méthode init() (appelée par défaut à la construction
 * de la servlet par le serveur d'applications)
 *
 */
public class Controleur extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public static Connection connection;
	private RequestDispatcher 	disp;
/**
 * La méthode init() s'éxécute par défaut et appelle la méthode Connect.initConnexion() qui retourne une variable
 * de type Connection définie comme constante (final) dans l'instance de la servlet controleur et à laquelle 
 * toutes les opérations de MAJ de la BdD feront référence (évitant ainsi les accès répétitifs au connecteur (connect.java) 
 */
    public void init() {
    	connection = Connect.initConnexion();
    	System.out.println("connexion :"+connection);
    }

 /**
  * La méthode doGet est appelée dès qu'une requête est exécutée par la méthode GET (utilisée de préférence en cas d'absence de
  * modification d'état de la BdD ou général de l'application)
  */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// recuperation de l'url (à partir du chemin relatif à la servlet c'est à dire consécutif à la racine /Festival/)
		String path = request.getPathInfo();
		
		// Affichage console des path pour vérification
		System.out.println("================  dans Controleur path=" + path );
		System.out.println("================  dans Controleur path contexte =" + request.getContextPath() );
		
		// si la requête ne fait aucune à une URL particulière
		// appel de la méthode DoAccueil
		if (path == null || path.equals("/")) {
			doAccueil(request, response);
		}
		
		// si l'URL est /Festival/runingorder
		else if (path.equals("/runingorder")) {
			
			doFormScene(request, response);
		}
//		else {
//			request.setAttribute("msgErreur", "Accl&eacute;e interdit : URL erron&eacute;e !!!");
//			doAccueil(request, response);
//		}
		// Affichage de la planification
		if (path.equals("/aff")) {
			doFormScene(request, response);
		};
		// Consultation de la planification + Recherche
				if (path.equals("/consult")) {
					doconsultScene(request, response);
				};
		// Modification de l'enregistrement
		if (path.equals("/modif")) {
			doModif(request, response);
		};
		
		// Suppression de l'enregistrement
		if (path.equals("/supp")) {
			doSupp(request, response);
		};
		
		System.out.println("** Fin Controleur");
	}

/**
 * La méthode doFormScene permet l'affichage du formulaire de gestion de la liste des groupes planifiés pour une scène
 * cette liste permet de choisir une programmation de groupe en vue de sa modification (débranchement vers le formulaire
 * de gestion) ou de supprimer directement une programmation depuis la liste
 * @param request	: passage de la liste des planifications actives par appel de la méthode Datas.listscene() au formulaire "planification/Scene2.jsp"
 * @param response
 * @throws ServletException
 * @throws IOException
 */
	private void doFormScene(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Affichage de la programmation de la scène
		
		ArrayList<String[]> listeScene = new ArrayList<String[]>();
		listeScene=Datas.listScene();
		request.setAttribute("listScene", listeScene);
		
		disp = request.getRequestDispatcher("/planification/Scene2.jsp");
		disp.forward(request,response);
	}
	
/** Cette méthode appelle le formulaire de création d'une programmation d'une scène
 * 	Les informations requises sont le groupe - la date - l'heure et la durée qui définiront une "scène" ou programmation
 * @param requestpassage de la liste des groupes/heures/durées actives au formulaire "/gestion/Creation.jsp"
 * @param response
 * @throws ServletException
 * @throws IOException
 */
	private void doAccueil(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Récupération des groupes
		ArrayList<String[]> listegroupe = Datas.initGroupe();
		request.setAttribute("listeGroupe", listegroupe);
		
		// Récupération des heures
		ArrayList<String[]> listeheure = Datas.initHeure();
		request.setAttribute("listeHeure", listeheure);
		
		// Récupération des durées
		ArrayList<String[]> listeDuree = Datas.initDuree();
		request.setAttribute("listeDuree", listeDuree);
		
		disp = request.getRequestDispatcher("/gestion/Creation.jsp");
		disp.forward(request,response);	
		
//		//on demande au navigateur de réémettre cette requete
//		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/index.jsp"));
	}
	 /**
	  * La méthode doPost est appelée dès qu'une requête est exécutée par la méthode POST (utilisée de préférence en cas de
	  * modification d'état de la BdD ou général de l'application)
	  */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
/**	Suppression de l'occurrence
 * Cette méthode permet de supprimer une planification d'un groupe sur une scène
 * @param request	Réception d'un paramètre de type int correspondant à l'ID de la classe scène (en vue de suppression)
 * la méthode initialise également un paramètre sup="ok" pour signifier qu'il s'agit d'une Suppression au formulaire "/planification/Scene.jsp"
 * @param response
 * @throws ServletException
 * @throws IOException
 */
	protected void doSupp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id= Integer.parseInt(request.getParameter("id"));
		
		ArrayList<String[]> liste=Datas.modifSceneBdD(id);
		String[] ligne = new String[]{};
		for (int i=0; i<liste.size();i++)
		{
			ligne = (String[]) liste.get(i);
		}
		String groupe = (String) ligne[0];
		String date = (String) ligne[1];
		String heure = (String) ligne[2];
		String duree = (String) ligne[3];
		
		request.setAttribute("id", id);
		request.setAttribute("groupe", groupe);
		request.setAttribute("datec", date);
		request.setAttribute("heure", heure);
		request.setAttribute("duree", duree);
		
		// Delete table Scene
		Datas.suppSceneBdD(id);
		

		String sup= "ok";
		
		request.setAttribute("sup", sup);
		ArrayList<String[]> listeScene = new ArrayList<String[]>();
		listeScene=Datas.listScene();
		request.setAttribute("listScene", listeScene);
		
		disp = request.getRequestDispatcher("/planification/Scene.jsp");
		disp.forward(request,response);
	}
	
/**	Modification d'une planification de groupe pour une scène	
 * 
 * @param request	Réception d'un paramètre de type int correspondant à l'ID de la classe scène (en vue de modification)
 * 					la méthode initialise également un paramètre maj="ok" pour signifier qu'il s'agit d'une MAJ au formulaire "/gestion/Creation.jsp"
 * @param response
 * @throws ServletException
 * @throws IOException
 */
	protected void doModif(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Réception d'un paramètre ID de type int
		int id= Integer.parseInt(request.getParameter("id"));
		
		// constitution d'une liste de scène ayant même ID (en principe unique)
		ArrayList<String[]> liste=Datas.modifSceneBdD(id);
				
		String maj= "ok";
		// Récupération des groupes
		ArrayList<String[]> listegroupe = Datas.initGroupe();
		request.setAttribute("listeGroupe", listegroupe);
		
		// Récupération des heures
		ArrayList<String[]> listeheure = Datas.initHeure();
		request.setAttribute("listeHeure", listeheure);
		
		// Récupération des durées
		ArrayList<String[]> listeDuree = Datas.initDuree();
		request.setAttribute("listeDuree", listeDuree);
		
		request.setAttribute("maj", maj);
		request.setAttribute("id", id);
		request.setAttribute("liste", liste);
				
		disp = request.getRequestDispatcher("/gestion/Creation.jsp");
		disp.forward(request,response);
	}
	/** Cette méthode affiche un formulaire de consultation + Recherche multi-critères (groupe-date-heure-durée) de l'organisation d'une scène
	 * 
	 * @param request	: passage de la liste des planifications actives par appel de la méthode Datas.listscene() au formulaire "/consultation/Consult.jsp"	
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doconsultScene(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Affichage de la consultation programmation d'une scène
		
		ArrayList<String[]> listeScene = new ArrayList<String[]>();
		listeScene=Datas.listScene();

		request.setAttribute("listScene", listeScene);
		
		disp = request.getRequestDispatcher("/consultation/Consult.jsp");
		disp.forward(request,response);
	}
}
