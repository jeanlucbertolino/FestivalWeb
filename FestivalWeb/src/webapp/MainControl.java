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

public class MainControl extends HttpServlet {
	
	public static Connection connection;
	private static final long serialVersionUID = 1L;	
	
	// initialisation de la connexion
    public void init() {
        	connection = Connect.initConnexion();
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
			// Création instance Scene
			// récupération valeur dernier id
			
			int lastId=0;
			lastId=Datas.lastId();
			System.out.println(lastId);
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
				ArrayList liste = Datas.listScene();
				request.setAttribute("listScene", liste);
				
				response.setContentType( "text/html");
				RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/Scene.jsp");
				rd.forward( request, response );
				} catch (DateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	 
				
			}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			// on passe la main au GET
//			doPost(request, response);
			}
	}
