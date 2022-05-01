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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import beans.Ville;
import beans.Weather;

/**
 * Servlet implementation class MeteoVille
 */
@WebServlet("/MeteoVille")
public class MeteoVille extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MeteoVille() {
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
		this.getServletContext().getRequestDispatcher("/WEB-INF/meteoVille.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Weather meteoVille = new Weather();
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");
		String codeINSEE = request.getParameter("codeINSEE");
		ArrayList<Ville> listVille = new ArrayList<Ville>();
		Gson gson = new Gson();
		Ville ville = new Ville();
		
		HttpRequest requestWeather = HttpRequest.newBuilder().GET()
				.uri(URI.create("https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude
						+ "&appid=1fd9b554aa960b82145b67de2d5e2eaf&lang=fr"))
				.build();
		HttpResponse<String> responseWeather;

		HttpRequest requestVille = HttpRequest.newBuilder().GET()
				.uri(URI.create("http://127.0.0.1:8181/ville?codeINSEE=" + codeINSEE)).build();

		HttpResponse<String> responseVille;
		try {
			responseWeather = httpClient.send(requestWeather, HttpResponse.BodyHandlers.ofString());
			String meteoJson = responseWeather.body();
			JSONParser parser = new JSONParser();
			JSONObject json;
			responseVille = httpClient.send(requestVille, HttpResponse.BodyHandlers.ofString());
			listVille = gson.fromJson(responseVille.body(),
					TypeToken.getParameterized(ArrayList.class, Ville.class).getType());
			ville = listVille.get(0);
			try {
				json = (JSONObject) parser.parse(meteoJson);
				ArrayList<Weather> listMeteo = gson.fromJson(json.get("weather").toString(),
						TypeToken.getParameterized(ArrayList.class, Weather.class).getType());
				meteoVille = listMeteo.get(0);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			 Thread.currentThread().interrupt();
		}
		request.setAttribute("meteoVille", meteoVille);
		request.setAttribute("ville", ville);

		this.getServletContext().getRequestDispatcher("/WEB-INF/meteoVille.jsp").forward(request, response);
	}

}
