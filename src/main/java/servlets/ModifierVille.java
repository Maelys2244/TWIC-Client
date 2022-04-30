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

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import beans.Ville;
import beans.Weather;

/**
 * Servlet implementation class ModifierVille
 */
@WebServlet("/ModifierVille")
public class ModifierVille extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifierVille() {
		super();
	}

	private static final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1)
			.connectTimeout(Duration.ofSeconds(10)).build();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/modifierVille.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String codeINSEE = request.getParameter("codeINSEE");
		ArrayList<Ville> listVille = new ArrayList<Ville>();
		Gson gson = new Gson();
		Ville ville = new Ville();

		HttpRequest request1 = HttpRequest.newBuilder().GET()
				.uri(URI.create("http://127.0.0.1:8181/ville?codeINSEE=" + codeINSEE)).build();

		HttpResponse<String> response1;

		try {
			response1 = httpClient.send(request1, HttpResponse.BodyHandlers.ofString());
			listVille = gson.fromJson(response1.body(),
					TypeToken.getParameterized(ArrayList.class, Ville.class).getType());
			ville = listVille.get(0);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		request.setAttribute("ville", ville);

		this.getServletContext().getRequestDispatcher("/WEB-INF/modifierVille.jsp").forward(request, response);
	}
}
