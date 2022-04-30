<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<style type="text/css">
<%@
include file="../css/listeNomsVilles.css"%>
</style>
<head>
<meta charset="UTF-8">
<title>Liste villes - CADISTANCE</title>
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:100,200,300,400"
	rel="stylesheet">
</head>
<body>
	<%@ include file="enTete.jsp"%>
	<c:set var="debut" value="${debut}" />
	<c:set var="fin" value="${fin}" />

	<div class=fond>
		<div class="btn_accueil">
			<a style="border: none" href="accueil"><img width="50px"
				height="50px" src="images/accueil.jpg"></a>
		</div>
		
		<button class="btn" onclick="window.location.href = ('ajouterVille')">Ajouter Ville</button>
		<div class="fond_tableau">
			<div class="tableau">
				<table id="tableau">

					<thead>
						<tr class="first_ligne">
							<th>Nom commune</th>
							<th>Code postal</th>
							<th>Libelle acheminement</th>
							<th>Longitude</th>
							<th>Latitude</th>
							<th>Code INSEE</th>
							<th></th>
							<th></th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="i" begin="${debut}" end="${fin}" step="1">
							<tr>
								<td><c:out value="${listeVilles.get(i).getNomCommune()}" /></td>
								<td><c:out value="${listeVilles.get(i).getCodePostal()}" /></td>
								<td><c:out value="${listeVilles.get(i).getLibelleAcheminement()}" /></td> 									
								<td><c:out value="${listeVilles.get(i).getLongitude()}" /></td> 
								<td><c:out value="${listeVilles.get(i).getLatitude()}" /></td> 
 								<td><c:out value="${listeVilles.get(i).getCodeCommuneINSEE()}" /></td> 
								<td>
									<form action="modifierVille" method="post">
										<input hidden="hidden" value="${listeVilles.get(i).getCodeCommuneINSEE()}" name="codeINSEE" />
										<input type="submit" name="modifier" value="modifier" />
									</form>
								</td>
								<td>
									<form action="meteoVille" method="post">
										<input hidden="hidden" value="${listeVilles.get(i).getCodeCommuneINSEE()}" name="codeINSEE" />
										<input hidden="hidden" value="${listeVilles.get(i).getLongitude()}" name="longitude" />
										<input hidden="hidden" value="${listeVilles.get(i).getLatitude()}" name="latitude" />
										<input type="submit" name="meteo" value="meteo" />
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<form class="formulaire" method="post" action="listeNomsVilles">
			<input hidden="hidden" value="${debut}" name="debut" /> <input
				hidden="hidden" value="${fin}" name="fin" />
			<div class="boutonSuivant">
				<c:if test="${fin==listeVilles.size()-1}">
					<input type="submit" name="suivant" value="Page suivante"
						disabled="disabled" />
				</c:if>
				<c:if test="${(fin)<listeVilles.size()-1}">
					<input type="submit" name="suivant" value="Page suivante" />
				</c:if>
			</div>
			<div class="boutonPrecedent">
				<c:if test="${debut==0}">
					<input type="submit" name="precedent" value="Page précédente"
						disabled="disabled" />
				</c:if>
				<c:if test="${debut!=0}">
					<input type="submit" name="precedent" value="Page précédente" />
				</c:if>
			</div>
			<div class="boutonPage1">
				<c:if test="${debut==0}">
					<input type="submit" name="premierePage" value="Première page"
						disabled="disabled" />
				</c:if>
				<c:if test="${debut!=0}">
					<input type="submit" name="premierePage" value="Première page" />
				</c:if>
			</div>
		</form>
	</div>

</body>
</html>