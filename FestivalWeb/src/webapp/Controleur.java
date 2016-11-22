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
 * et d'aiguiller les diff�rents appels vers les MAJ de BdD et la gestion des transactions associ�es
 * l'annotation indique que toutes les URL ayant pour racine 'Festival/*' viendront se connecter � cette servlet de contr�le
 */
@WebServlet(
		name = "Controleur", 
		description = "Controleur General", 
		urlPatterns = {"/Festival/*"}
		)

/**
 * la classe controleur est de type servlet
 * On lui d�finit une variable de type connection initialis�e depuis la m�thode init() (appel�e par d�faut � la construction
 * de la servlet par le serveur d'applications)
 *
 */
public class Controleur extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public static Connection connection;
	private RequestDispatcher 	disp;
/**
 * La m�thode init() s'�x�cute par d�faut et appelle la m�thode Connect.initConnexion() qui retourne une variable
 * de type Connection d�finie comme constante (final) dans l'instance de la servlet controleur et � laquelle 
 * toutes les op�rations de MAJ de la BdD feront r�f�rence (�vitant ainsi les acc�s r�p�titifs au connecteur (connect.java) 
 */
    public void init() {
    	connection = Connect.initConnexion();
    	System.out.println("connexion :"+connection);
    }

 /**
  * La m�thode doGet est appel�e d�s qu'une requ�te est ex�cut�e par la m�thode GET (utilis�e de pr�f�rence en cas d'absence de
  * modification d'�tat de la BdD ou g�n�ral de l'application)
  */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// recuperation de l'url (� partir du chemin relatif � la servlet c'est � dire cons�cutif � la racine /Festival/)
		String path = request.getPathInfo();
		
		// Affichage console des path pour v�rification
		System.out.println("================  dans Controleur path=" + path );
		System.out.println("================  dans Controleur path contexte =" + request.getContextPath() );
		
		// si la requ�te ne fait aucune � une URL particuli�re
		// appel de la m�thode DoAccueil
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
 * La m�thode doFormScene permet l'affichage du formulaire de gestion de la liste des groupes planifi�s pour une sc�ne
 * cette liste permet de choisir une programmation de groupe en vue de sa modification (d�branchement vers le formulaire
 * de gestion) ou de supprimer directement une programmation depuis la liste
 * @param request	: passage de la liste des planifications actives par appel de la m�thode Datas.listscene() au formulaire "planification/Scene2.jsp"
 * @param response
 * @throws ServletException
 * @throws IOException
 */
	private void doFormScene(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Affichage de la programmation de la sc�ne
		
		ArrayList<String[]> listeScene = new ArrayList<String[]>();
		listeScene=Datas.listScene();
		request.setAttribute("listScene", listeScene);
		
		disp = request.getRequestDispatcher("/planification/Scene2.jsp");
		disp.forward(request,response);
	}
	
/** Cette m�thode appelle le formulaire de cr�ation d'une programmation d'une sc�ne
 * 
 * @param request
 * @param response
 * @throws ServletException
 * @throws IOException
 */
	private void doAccueil(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		disp = request.getRequestDispatcher("/gestion/Creation.jsp");
		disp.forward(request,response);	
		
//		//on demande au navigateur de r��mettre cette requete
//		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/index.jsp"));
	}
	 /**
	  * La m�thode doPost est appel�e d�s qu'une requ�te est ex�cut�e par la m�thode POST (utilis�e de pr�f�rence en cas de
	  * modification d'�tat de la BdD ou g�n�ral de l'application)
	  */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
//	Suppression de l'occurrence
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
		
		// Delete table Emprunt
		Datas.suppSceneBdD(id);
		

		String sup= "ok";
		
		request.setAttribute("sup", sup);
		ArrayList<String[]> listeScene = new ArrayList<String[]>();
		listeScene=Datas.listScene();
		request.setAttribute("listScene", listeScene);
		
		disp = request.getRequestDispatcher("/planification/Scene.jsp");
		disp.forward(request,response);
	}
	
/**	Modification d'une planification de groupe pour une sc�ne	
 * 
 * @param request	R�ception d'un param�tre de type int correspondant � l'ID de la classe sc�ne
 * 					la m�thode initialise �galement un param�tre maj="ok" pour signifier qu'il s'agit d'une MAJ au formulaire "/gestion/Creation.jsp"
 * @param response
 * @throws ServletException
 * @throws IOException
 */
	protected void doModif(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// R�ception d'un param�tre ID de type int
		int id= Integer.parseInt(request.getParameter("id"));
		
		// constitution d'une liste de sc�ne ayant m�me ID (en principe unique)
		ArrayList<String[]> liste=Datas.modifSceneBdD(id);
				
		String maj= "ok";
		
		request.setAttribute("maj", maj);
		request.setAttribute("id", id);
		request.setAttribute("liste", liste);
				
		disp = request.getRequestDispatcher("/gestion/Creation.jsp");
		disp.forward(request,response);
	}
	/** Cette m�thode affiche un formulaire de consultation + Recherche multi-crit�res (groupe-date-heure-dur�e) de l'organisation d'une sc�ne
	 * 
	 * @param request	: passage de la liste des planifications actives par appel de la m�thode Datas.listscene() au formulaire "/consultation/Consult.jsp"	
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doconsultScene(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Affichage de la consultation programmation d'une sc�ne
		
		ArrayList<String[]> listeScene = new ArrayList<String[]>();
		listeScene=Datas.listScene();

		request.setAttribute("listScene", listeScene);
		
		disp = request.getRequestDispatcher("/consultation/Consult.jsp");
		disp.forward(request,response);
	}
}
