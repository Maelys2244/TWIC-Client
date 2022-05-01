<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<style type="text/css">
<%@ include file="../css/distanceResultat.css"%>
</style>
<head>
<meta charset="UTF-8">
<title>Distance - CADISTANCE</title>
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:100,200,300,400"
	rel="stylesheet">
</head>
<body>
	<%@ include file="enTete.jsp"%>
	<div class=fond>
		<div class="btn_accueil">
			<a style="border: none" href="accueil"><img width="50px"
				height="50px" src="images/accueil.jpg"></a>
		</div>
		<div class="btn_villes">
			<a href="listeNomsVilles"><img width="50px" height="50px"
				src="images/logoVille.jpg"></a>
		</div>
		<div class=affichageInformation>
			<div class=informationVille1>
				<p>
					Ville :
					<c:out value="${ville1.getNomCommune()}" />
				</p>
				<p>
					Code postal :
					<c:out value="${ville1.getCodePostal()}" />
				</p>
				<p>
					Longitude :
					<c:out value="${ville1.getLatitude()}" />
					°
				</p>
				<p>
					Latitude :
					<c:out value="${ville1.getLongitude()}" />
					°
				</p>
			</div>
			<div class=informationVille2>
				<p>
					Ville :
					<c:out value="${ville2.getNomCommune()}" />
				</p>
				<p>
					Code postal :
					<c:out value="${ville2.getCodePostal()}" />
				</p>

				<p>
					Longitude :
					<c:out value="${ville2.getLatitude()}" />
					°
				</p>
				<p>
					Latitude :
					<c:out value="${ville2.getLongitude()}" />
					°
				</p>
			</div>
			<div class=distance>
				<p>
					La distance est de
					<c:out value="${distance}" />
					km
				</p>
			</div>
		</div>
	</div>
</body>
</html>