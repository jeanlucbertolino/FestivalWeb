package webapp;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControlGestion
 * 
 * Controleur pour la gestion des bonbons
 * creation/Modification/Suppression
 */
@WebServlet(
		name = "ControlGestion", 
		description = "Controleur Gestion", 
		urlPatterns = {"/action/ControlGestion/*"}
		)
public class ControlGestion extends HttpServlet {
	
	private static final long 	serialVersionUID = 1L;
	private RequestDispatcher 	disp;
       
    public ControlGestion() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// recuperation du choix
		String path = request.getPathInfo();
		System.out.println("1================  dans ControleurGestion path=" + path );
		
		if (path != null && path.equals("/gestionBonbon")) {

			// gestion d'un bonbon
			doGestionBonbon(request, response);
		}
		else {
			request.setAttribute("msgErreur", "Gestion Bonbon : Vous avez tripatouill&eacute; l'url!!! ");
			disp = request.getRequestDispatcher("/"); 
			disp.forward(request,response);
		}
	}

	private void doGestionBonbon(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		out.print("<html><body><p>Voici ControlGestion</p></body></html>");

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
