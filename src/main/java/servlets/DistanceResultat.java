package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Ville;
import metiers.CalculDistance;

/**
 * Servlet implementation class DistanceResultat
 */
@WebServlet("/DistanceResultat")
public class DistanceResultat extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession maSession;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DistanceResultat() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/distanceResultat.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (maSession == null) {
			maSession = request.getSession();
		}
		String nomVille1 = "";
		String nomVille2 = "";
		ArrayList<Ville> listVilles = new ArrayList<Ville>();
		try {
			nomVille1 = request.getParameter("choix_ville1");
			nomVille2 = request.getParameter("choix_ville2");
			listVilles = (ArrayList<Ville>) maSession.getAttribute("listeVilles");
		} catch (Exception e) {
			e.printStackTrace();
		}
		CalculDistance calculDist = new CalculDistance();
		double distance = (double)Math.round(calculDist.calculteurDistance(nomVille1, nomVille2, listVilles)*100)/100;
		double lat1 = (double)Math.round(calculDist.getVille1().getLatitude()*100)/100;
		double lat2 = (double)Math.round(calculDist.getVille2().getLatitude()*100)/100;
		double long1 = (double)Math.round(calculDist.getVille1().getLongitude()*100)/100;
		double long2 = (double)Math.round(calculDist.getVille2().getLongitude()*100)/100;
		calculDist.getVille1().setLatitude(lat1);
		calculDist.getVille2().setLatitude(lat2);
		calculDist.getVille1().setLongitude(long1);
		calculDist.getVille2().setLongitude(long2);
		calculDist.setDistance(distance);
		request.setAttribute("ville1", calculDist.getVille1());
		request.setAttribute("ville2", calculDist.getVille2());
		request.setAttribute("distance", calculDist.getDistance());
		this.getServletContext().getRequestDispatcher("/WEB-INF/distanceResultat.jsp").forward(request, response);
	}

}
