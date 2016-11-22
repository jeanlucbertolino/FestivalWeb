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
 * Servlet implementation class Controleur
 */
@WebServlet(
		name = "Controleur", 
		description = "Controleur General", 
		urlPatterns = {"/Festival/*"}
		)
public class Controleur extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public static Connection connection;
	private RequestDispatcher 	disp;
	
    public void init() {
    	connection = Connect.initConnexion();
    	System.out.println("connexion :"+connection);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// recuperation de l'url (à partir du chemin relatif à la servlet application cad après /bb)
		String path = request.getPathInfo();
		System.out.println("================  dans Controleur path=" + path );
		System.out.println("================  dans Controleur path contexte =" + request.getContextPath() );
		
		if (path == null || path.equals("/")) {
			doAccueil(request, response);
		}
		else if (path.equals("/planification")) {
			// affichage formulaire gestion des bonbons
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


	private void doFormScene(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Affichage de la programmation de la scène
		
		ArrayList<String[]> listeScene = new ArrayList<String[]>();
		listeScene=Datas.listScene();
		request.setAttribute("listScene", listeScene);
		
		disp = request.getRequestDispatcher("/planification/Scene2.jsp");
		disp.forward(request,response);
	}

	private void doAccueil(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		disp = request.getRequestDispatcher("/gestion/Creation.jsp");
		disp.forward(request,response);	
		
//		//on demande au navigateur de réémettre cette requete
//		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/index.jsp"));
	}

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
//	Modification de l'occurrence
	protected void doModif(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id= Integer.parseInt(request.getParameter("id"));
		// Update table Emprunt
		ArrayList<String[]> liste=Datas.modifSceneBdD(id);
		
		String maj= "ok";
		
		request.setAttribute("maj", maj);
		request.setAttribute("id", id);
		request.setAttribute("liste", liste);
				
		disp = request.getRequestDispatcher("/gestion/Creation.jsp");
		disp.forward(request,response);
	}
	// Consultation + Recherche organisation de la scène
	protected void doconsultScene(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Affichage de la consultation programmation d'une scène
		
		ArrayList<String[]> listeScene = new ArrayList<String[]>();
		listeScene=Datas.listScene();
		request.setAttribute("listScene", listeScene);
		
		disp = request.getRequestDispatcher("/consultation/Consult.jsp");
		disp.forward(request,response);
	}
}
