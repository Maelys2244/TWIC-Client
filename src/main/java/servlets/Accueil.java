package servlets;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import beans.Ville;

/**
 * Servlet implementation class Accueil
 */
@WebServlet("/Accueil")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HttpSession maSession;
	private ArrayList<Ville> listVilles = new ArrayList<Ville>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Accueil() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1)
			.connectTimeout(Duration.ofSeconds(10)).build();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpRequest request1 = HttpRequest.newBuilder().GET().uri(URI.create("http://127.0.0.1:8181/ville")).build();

		HttpResponse<String> response1;
		Gson gson = new Gson();
		try {
			response1 = httpClient.send(request1, HttpResponse.BodyHandlers.ofString());
			String villeJson = response1.body();
			listVilles = gson.fromJson(villeJson, TypeToken.getParameterized(ArrayList.class, Ville.class).getType());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (maSession == null) {
			maSession = request.getSession();
		}
		maSession.setAttribute("listeVilles", listVilles);
		request.setAttribute("listeVilles", listVilles);

		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String codeINSEE = request.getParameter("oldINSEE");
		ArrayList<Ville> listVille = new ArrayList<Ville>();
		Gson gson = new Gson();
		Ville ville = new Ville();

		if (request.getParameter("supprimer") != null) {
			HttpRequest requestGet = HttpRequest.newBuilder().GET()
					.uri(URI.create("http://127.0.0.1:8181/ville?codeINSEE=" + codeINSEE)).build();

			HttpResponse<String> response1;
			try {
				response1 = httpClient.send(requestGet, HttpResponse.BodyHandlers.ofString());
				listVille = gson.fromJson(response1.body(),
						TypeToken.getParameterized(ArrayList.class, Ville.class).getType());
				ville = listVille.get(0);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			var requestVille = HttpRequest.newBuilder()
					.uri(URI.create("http://127.0.0.1:8181/ville/" + ville.getCodeCommuneINSEE()))
					.header("Content-Type", "application/json").DELETE().build();
			try {
				HttpClient.newHttpClient().send(requestVille, HttpResponse.BodyHandlers.ofString());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		if (request.getParameter("valider") != null)
		{
			ObjectMapper mapper = new ObjectMapper();
			String codePostal = request.getParameter("postalCode");
			String nomCommune = request.getParameter("name");
			String libelleAcheminement = request.getParameter("libelle");
			double latitudeD = Double.parseDouble(request.getParameter("latitude"));
			double longitudeD = Double.parseDouble(request.getParameter("longitude"));
			String codeCommuneINSEE = request.getParameter("insee");
			String ligne5 = request.getParameter("ligne5");
			ville = new Ville(codePostal, nomCommune, libelleAcheminement, latitudeD, longitudeD, codeCommuneINSEE,ligne5);
			String json = mapper.writeValueAsString(ville);
			var requestVille = HttpRequest.newBuilder()
					.uri(URI.create("http://127.0.0.1:8181/ville/" + codeCommuneINSEE))
					.header("Content-Type", "application/json").PUT((HttpRequest.BodyPublishers.ofString(json)))
					.build();
			try {
				HttpClient.newHttpClient().send(requestVille, HttpResponse.BodyHandlers.ofString());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		doGet(request, response);
	}

}
