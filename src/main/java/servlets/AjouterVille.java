package servlets;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Ville;

/**
 * Servlet implementation class AjouterVille
 */
@WebServlet("/AjouterVille")
public class AjouterVille extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjouterVille() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterVille.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("valider") != null) {
			ObjectMapper mapper = new ObjectMapper();
			String codePostal = request.getParameter("postalCode");
			String nomCommune =  request.getParameter("name");
			String libelleAcheminement  = request.getParameter("libelle");
			double latitude = Double.parseDouble(request.getParameter("latitude"));
			double longitude = Double.parseDouble(request.getParameter("longitude"));
			String codeCommuneINSEE = request.getParameter("insee");
			String ligne5 = request.getParameter("ligne5");
			Ville ville = new Ville(codePostal,nomCommune,libelleAcheminement,latitude,longitude,codeCommuneINSEE,ligne5);
			String json = mapper.writeValueAsString(ville);
			var requestVille = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:8181/ville")) 
			                         .header("Content-Type", "application/json") 
			                         .POST(HttpRequest.BodyPublishers.ofString(json)).build();
			try {
				HttpClient.newHttpClient().send(requestVille, HttpResponse.BodyHandlers.ofString());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	}

}
