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
import metier.Scene;
import technique.DateException;


@WebServlet("/Festival/planification2/*")
public class SceneServ2 extends HttpServlet {
	
	public static Connection connection;
	private static final long serialVersionUID = 1L;	
	private RequestDispatcher 	disp;
	
	// initialisation de la connexion
    public void init() {
        	connection = Connect.initConnexion();
        	System.out.println("connexion :"+connection);
        }
	
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			// TODO Auto-generated method stub
			PrintWriter out = response.getWriter() ;
			response.setContentType( "text/html");
			
			// Récupération des informations du formulaire
			String groupe = request.getParameter("groupesel");
			String datec = request.getParameter("datesel");
			String heure = request.getParameter("heuresel");
			String duree = request.getParameter("dureesel");
			
			System.out.println(groupe+"//"+datec+"//"+heure+"//"+duree);
			
			String majid = request.getParameter("id");
			String maj = request.getParameter("maj");
			
			if (maj==null) {
			// Création instance Scene
			// récupération valeur dernier id
			
//			String lastId = new String();
//			lastId="0";
			int lastId=Datas.lastId();
			System.out.println(lastId);
//			if (lastId.isEmpty()) {
//				lastId="0";
//			}
//			int derId=Integer.parseInt(lastId);
//			int id=derId+1;
			int id=lastId+1;
			
			 try {
				Scene scene = new Scene(id,groupe,datec,heure,duree);

				System.out.println(id+"**"+groupe+"**"+datec+"**"+heure+"**"+duree);			 
			 
			 // MAJ table Scene2
			 Datas.initScene2BdD(id,groupe,datec,heure,duree);
			 
			 		
			    request.setAttribute("id", id);
				request.setAttribute("groupe", groupe);
				request.setAttribute("datec", datec);
				request.setAttribute("heure", heure);
				request.setAttribute("duree", duree);
				
				System.out.println(id+"--"+groupe+"--"+datec+"--"+heure+"--"+duree);
							 

				// liste des emprunts existants
				ArrayList<String[]> liste = Datas.listScene();
//				ArrayList liste = new ArrayList();
//				liste.add("Test");
				request.setAttribute("listScene", liste);
				
				response.setContentType( "text/html");
				RequestDispatcher rd = request.getRequestDispatcher("/planification/Scene.jsp");
				rd.forward( request, response );
				} catch (DateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	 
			}
			if (maj!=null) {
			// Modification instance Scene
		
			 try {
				 System.out.println("majId ...:"+majid);
				 int id=Integer.parseInt(majid);
				System.out.println(majid+"**"+groupe+"**"+datec+"**"+heure+"**"+duree);			 
			 
			 // MAJ table Scene2
			 Datas.majScene2BdD(id,groupe,datec,heure,duree);
			 
			 		
			    request.setAttribute("id",  id);
				request.setAttribute("groupe", groupe);
				request.setAttribute("datec", datec);
				request.setAttribute("heure", heure);
				request.setAttribute("duree", duree);
				
				System.out.println(majid+"--"+groupe+"--"+datec+"--"+heure+"--"+duree);
							 

				// liste des emprunts existants
				ArrayList<String[]> liste = Datas.listScene();
//				ArrayList liste = new ArrayList();
//				liste.add("Test");
				request.setAttribute("listScene", liste);
				
				response.setContentType( "text/html");
				RequestDispatcher rd = request.getRequestDispatcher("/planification/Scene.jsp");
				rd.forward( request, response );
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	 
			}				
			}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// recuperation de l'url (à partir du chemin relatif à la servlet application cad après /bb)
				String path = request.getPathInfo();
				System.out.println("================  dans SceneServ path=" + path );
				System.out.println("================  dans SceneServ path contexte =" + request.getContextPath() );
				
				if (path == null || path.equals("/")) {
					doAccueil(request, response);
				}
				if (path.equals("/recherche")) {
					doAccueil(request, response);
				}
			}
	private void doAccueil(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		disp = request.getRequestDispatcher("/gestion/Creation.jsp");
		disp.forward(request,response);	
		
	}
}