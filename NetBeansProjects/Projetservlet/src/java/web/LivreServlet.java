/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package web;

import DAO.LivreDAO;
import Model.Livre;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author asma
 */
@WebServlet("/")
public class LivreServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
	private LivreDAO livreDAO;
	
	public void init() {
		livreDAO = new LivreDAO();
	}
   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertLivre(request, response);
				break;
			case "/delete":
				deleteLivre(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateLivre(request, response);
				break;
			default:
				listLivre(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
        }
    

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

 
    @Override
    public String getServletInfo() {
        return "Gestion des Livres";
    }
    
    
    private void listLivre(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Livre> listLivre = livreDAO.selectAllLivres();
		request.setAttribute("listLivre", listLivre);
		RequestDispatcher dispatcher = request.getRequestDispatcher("livre-list.jsp");
		dispatcher.forward(request, response);
	}
    
    
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("livre-form.jsp");
		dispatcher.forward(request, response);
	}
        
   private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    System.out.println("Edit Form - ID: " + id); // Log ID parameter
    Livre existingUser = livreDAO.selectLivre(id);
    RequestDispatcher dispatcher = request.getRequestDispatcher("livre-form.jsp");
    request.setAttribute("livre", existingUser);
    dispatcher.forward(request, response);
}
    
    private void insertLivre(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String titre= request.getParameter("titre");
		String auteur = request.getParameter("auteur");
               
		Livre livre = new Livre( titre , auteur);
		livreDAO.insertLivre(livre);
		response.sendRedirect("list");
	}
    
    private void updateLivre(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String titre = request.getParameter("titre");
		String auteur = request.getParameter("auteur");
                String disponibleParam = request.getParameter("disponible");
                boolean disponible = Boolean.parseBoolean(disponibleParam);
		Livre livre = new Livre(id, titre , auteur, disponible);
		livreDAO.updateLivre(livre);
		response.sendRedirect("list");
	}
    
    private void deleteLivre(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    System.out.println("Delete Action - ID: " + id); // Log ID parameter
    livreDAO.deleteLivre(id);
    response.sendRedirect("list");
}

    
    

}
