<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<style type="text/css">
<%@ include file="../css/meteoVille.css"%>
</style>
<head>
<meta charset="UTF-8">
<title>Meteo - CADISTANCE</title>
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
			<h3>LA MÉTÉO</h3>
			<div>
				<p>Ville :</p>
				<c:out value="${ville.getNomCommune()}" />
			</div>
			<div>
				<p>Code postal :</p>
				<c:out value="${ville.getCodePostal()}" />
			</div>
			<div>
				<p>Longitude :</p>
				<c:out value="${ville.getLatitude()}" />
				<p style="font-weight: normal;">°</p>
			</div>
			<div>
				<p>Latitude :</p>
				<c:out value="${ville.getLongitude()}" />
				<p style="font-weight: normal;">°</p>
			</div>
			
			<div>
				<p>Description :</p>
				<c:out value="${meteoVille.getDescription()}" />
			</div>
		</div>
	</div>

</body>
</html>