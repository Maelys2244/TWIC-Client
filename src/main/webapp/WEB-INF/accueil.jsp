<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<style type="text/css">
<%@ include file="../css/accueil.css"%>
</style>
<head>
<meta charset="UTF-8">
<title>Accueil - CADISTANCE</title>
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:100,200,300,400"
	rel="stylesheet">
</head>
<body>
	<%@ include file="enTete.jsp"%>
	<div class=fond>
	<div class="btn_villes">
		<a href="listeNomsVilles"><img width="50px" height="50px" src="images/logoVille.jpg"></a>
		
	</div>
	<div class = calculDistance>
	<h4>☼ CHOIX DES DEUX VILLES ☼</h4>
	<form class="formulaire" method="post" action="distanceResultat"> 
		<p>
			<label id="label" for="choix_ville1">Ville 1 : </label> 
			<input list="listeVilles"  name="choix_ville1" id="choix_ville1" required="required">
				<datalist id ="listeVilles">
				<c:forEach var="i" begin="0" end="${listeVilles.size()-1}" step="1">
					<option><c:out
							value="${listeVilles.get(i).getNomCommune()}" /></option>
				</c:forEach>
			</datalist>
		</p>
		<p>
			<label id="label" for="choix_ville2">Ville 2 : </label> 
			<input list="listeVilles"  name="choix_ville2" id="choix_ville2" required="required">
				<datalist id ="listeVilles">
				<c:forEach var="i" begin="0" end="${listeVilles.size()-1}" step="1">
					<option><c:out
							value="${listeVilles.get(i).getNomCommune()}" /></option>
				</c:forEach>
			</datalist>
		</p>
		<div class="bouton"> 
 			<input type="submit" value="Calculer distance    →" /> 
 		</div> 
 	</form> 
 	</div>
 	</div>
</body>
</html>
