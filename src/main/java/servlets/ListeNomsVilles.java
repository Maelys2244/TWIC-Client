package servlets;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Ville;
import metiers.GestionPages;

/**
 * Servlet implementation class ListeNomsVilles
 */
@WebServlet("/ListeNomsVilles")
public class ListeNomsVilles extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession maSession;
	private int debut = 0;
	private int fin = 49;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListeNomsVilles() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (maSession == null) {
			maSession = request.getSession();
		}
		ArrayList<Ville> listVilles = new ArrayList<Ville>();
		try {
			listVilles = (ArrayList<Ville>) maSession.getAttribute("listeVilles");
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("listeVilles", listVilles);
		request.setAttribute("debut", debut);
		request.setAttribute("fin", fin);
		this.getServletContext().getRequestDispatcher("/WEB-INF/listeNomsVilles.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String debutString = "";
		Ville ville = new Ville();
		GestionPages gestionPages = new GestionPages();

		if (maSession == null) {
			maSession = request.getSession();
		}
		ArrayList<Ville> listVilles = new ArrayList<Ville>();
		try {
			listVilles = (ArrayList<Ville>) maSession.getAttribute("listeVilles");
			debut = Integer.parseInt(request.getParameter("debut"));
			fin = Integer.parseInt(request.getParameter("fin"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (request.getParameter("suivant") != null) {
			gestionPages.pageSuivante(debut, fin, listVilles);
		}
		if (request.getParameter("precedent") != null) {
			gestionPages.pagePrecedente(debut, fin, listVilles);
		}
		if (request.getParameter("premierePage") != null) {
			gestionPages.premierePage(debut, fin);
		}
		request.setAttribute("debut", gestionPages.getDebut());
		request.setAttribute("fin", gestionPages.getFin());
		this.getServletContext().getRequestDispatcher("/WEB-INF/listeNomsVilles.jsp").forward(request, response);
	}
}
