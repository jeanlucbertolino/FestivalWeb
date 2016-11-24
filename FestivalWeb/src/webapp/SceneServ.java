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


@WebServlet("/Festival/planification/*")
public class SceneServ extends HttpServlet {
	
//	public static Connection connection;
	private static final long serialVersionUID = 1L;	
	private RequestDispatcher 	disp;
	
	// initialisation de la connexion
    public void init() {
//        	connection = Connect.initConnexion();
//        	System.out.println("connexion :"+connection);
        }
	/**
	 * Cette méthode permet de traiter les informations en provenance du formulaire de gestion (création/modification)
	 * et d'agir en conséquence sur la BdD et l'affichage des informations traitées
	 */
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
			
			// Contrôle de cohérence planification
			int cpterror = Datas.controlplanif(groupe,datec,heure,duree);
			if (cpterror==99999) {
			// Planification existante pour un autre groupe à cette date/heure 
				RequestDispatcher rd = request.getRequestDispatcher("/error/ErrPlanif.jsp");
				rd.forward( request, response );
			}
			else {
				
			// Test du paramètre "maj" , si null==> création d'occurence si renseigné==> Modification
			// Cas de la création d'une planification de scène
			if (maj==null) {

			// récupération valeur dernier id
			
			int lastId=Datas.lastId();
			System.out.println(lastId);

			int id=lastId+1;
			
			 try {
				 
					// Création instance Scene
				 
				Scene scene = new Scene(id,groupe,datec,heure,duree);

				System.out.println(id+"**"+groupe+"**"+datec+"**"+heure+"**"+duree);			 
			 
			 // MAJ table Scene2
				int £id = scene.getIdscene();
				String £groupe= scene.getGroupe();
				String £datec= scene.getDatec();
				String £heure= scene.getHeure();
				String £duree= scene.getDuree();
				
			 Datas.initScene2BdD(£id,£groupe,£datec,£heure,£duree);
			 
	// Création des attributs de l'instance request à transférer au formulaire des planifications des scènes
			 // en vue d'afficher l'occurrence créée
			 
			    request.setAttribute("id", £id);
				request.setAttribute("groupe", £groupe);
				request.setAttribute("datec", £datec);
				request.setAttribute("heure", £heure);
				request.setAttribute("duree", £duree);
				
				System.out.println(£id+"--"+£groupe+"--"+£datec+"--"+£heure+"--"+£duree);
							 

				// liste des scènes existantes
				ArrayList<String[]> liste = Datas.listScene();

				// création d'un attribut de l'instance request pointant sur la liste des planifications reçue de Datas.listScene()
				request.setAttribute("listScene", liste);
				
				response.setContentType( "text/html");
				RequestDispatcher rd = request.getRequestDispatcher("/planification/Scene.jsp");
				rd.forward( request, response );
				} catch (DateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	 
			}
			// Cas de la modification d'une planification de scène
			if (maj!=null) {
					
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
							 

				// liste des planifications existantes
				ArrayList<String[]> liste = Datas.listScene();
				
				// Création des attributs de l'instance request à transférer au formulaire des planifications des scènes
				 // en vue d'afficher l'occurrence modifiée
				
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
			}
	/**
	 * Cette méthode permet de rediriger la navigation vers des opérations ne nécessitant pas de modification
	 * de badD ou de situation fonctionnelles (formulaire de recherche consultation par exemple ...)
	 */
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
					doRecherche(request, response);
				}
			}
	private void doAccueil(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		disp = request.getRequestDispatcher("/gestion/Creation.jsp");
		disp.forward(request,response);	
		
	}
	/**
	 * la méthode doRecherche() permet d'afficher le formulaire de consultation des planifications existantes
	 * @param request	cette instance permet de récupérer les paramètres en provenance de la même servlet
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	private void doRecherche(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			// Constitution de la liste des groupes en fonction des critères de recherche
		
			String groupe= request.getParameter("groupe");
			String datec= request.getParameter("datec");
			String heure= request.getParameter("heure");
			String duree= request.getParameter("duree");
		
			ArrayList<String[]> listeScene = new ArrayList<String[]>();
			listeScene=Datas.SearchScene(groupe,datec,heure,duree);
			request.setAttribute("listScene", listeScene);
				
			disp = request.getRequestDispatcher("/consultation/Consult.jsp");
			disp.forward(request,response);	
			
		}
}