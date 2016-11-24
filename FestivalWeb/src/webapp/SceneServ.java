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
	 * Cette m�thode permet de traiter les informations en provenance du formulaire de gestion (cr�ation/modification)
	 * et d'agir en cons�quence sur la BdD et l'affichage des informations trait�es
	 */
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			// TODO Auto-generated method stub
			PrintWriter out = response.getWriter() ;
			response.setContentType( "text/html");
			
			// R�cup�ration des informations du formulaire	
			String groupe = request.getParameter("groupesel");
			String datec = request.getParameter("datesel");
			String heure = request.getParameter("heuresel");
			String duree = request.getParameter("dureesel");
			
			System.out.println(groupe+"//"+datec+"//"+heure+"//"+duree);
			
			String majid = request.getParameter("id");
			String maj = request.getParameter("maj");
			
			// Contr�le de coh�rence planification
			int cpterror = Datas.controlplanif(groupe,datec,heure,duree);
			if (cpterror==99999) {
			// Planification existante pour un autre groupe � cette date/heure 
				RequestDispatcher rd = request.getRequestDispatcher("/error/ErrPlanif.jsp");
				rd.forward( request, response );
			}
			else {
				
			// Test du param�tre "maj" , si null==> cr�ation d'occurence si renseign�==> Modification
			// Cas de la cr�ation d'une planification de sc�ne
			if (maj==null) {

			// r�cup�ration valeur dernier id
			
			int lastId=Datas.lastId();
			System.out.println(lastId);

			int id=lastId+1;
			
			 try {
				 
					// Cr�ation instance Scene
				 
				Scene scene = new Scene(id,groupe,datec,heure,duree);

				System.out.println(id+"**"+groupe+"**"+datec+"**"+heure+"**"+duree);			 
			 
			 // MAJ table Scene2
				int �id = scene.getIdscene();
				String �groupe= scene.getGroupe();
				String �datec= scene.getDatec();
				String �heure= scene.getHeure();
				String �duree= scene.getDuree();
				
			 Datas.initScene2BdD(�id,�groupe,�datec,�heure,�duree);
			 
	// Cr�ation des attributs de l'instance request � transf�rer au formulaire des planifications des sc�nes
			 // en vue d'afficher l'occurrence cr��e
			 
			    request.setAttribute("id", �id);
				request.setAttribute("groupe", �groupe);
				request.setAttribute("datec", �datec);
				request.setAttribute("heure", �heure);
				request.setAttribute("duree", �duree);
				
				System.out.println(�id+"--"+�groupe+"--"+�datec+"--"+�heure+"--"+�duree);
							 

				// liste des sc�nes existantes
				ArrayList<String[]> liste = Datas.listScene();

				// cr�ation d'un attribut de l'instance request pointant sur la liste des planifications re�ue de Datas.listScene()
				request.setAttribute("listScene", liste);
				
				response.setContentType( "text/html");
				RequestDispatcher rd = request.getRequestDispatcher("/planification/Scene.jsp");
				rd.forward( request, response );
				} catch (DateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	 
			}
			// Cas de la modification d'une planification de sc�ne
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
				
				// Cr�ation des attributs de l'instance request � transf�rer au formulaire des planifications des sc�nes
				 // en vue d'afficher l'occurrence modifi�e
				
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
	 * Cette m�thode permet de rediriger la navigation vers des op�rations ne n�cessitant pas de modification
	 * de badD ou de situation fonctionnelles (formulaire de recherche consultation par exemple ...)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// recuperation de l'url (� partir du chemin relatif � la servlet application cad apr�s /bb)
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
	 * la m�thode doRecherche() permet d'afficher le formulaire de consultation des planifications existantes
	 * @param request	cette instance permet de r�cup�rer les param�tres en provenance de la m�me servlet
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	private void doRecherche(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			// Constitution de la liste des groupes en fonction des crit�res de recherche
		
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