<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
<%@ include file="../css/ajouterVille.css"%>
</style>
<title>Ajouter une ville - CADISTANCE</title>
</head>
<script  type="text/javascript">

	function confirm() {
		if (confirm("Etes-vous sûr de vouloir ajouter la ville ? ")) {
			modifierForm.submit();
		}
	}
</script>
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
		
		<form class="formulaire" method="post" name = "ajouterForm" action="ajouterVille">
		<p> Informations sur la ville : </p>
			<label for="registerCity">Nom commune : </label> <input required
				type="text" value="${ville.getNomCommune()}"  maxlength="20" id="name" name="name" /> <label
				for="registerCity">Code commune INSEE : </label> <input required
				type="text" value="${ville.getCodeCommuneINSEE()}"  minlength="5" maxlength="5" id="insee" name="insee" /> <label
				for="registerCity">Code postal : </label> <input required
				type="text" value="${ville.getCodePostal()}"  minlength="5" maxlength="5" id="postalCode" name="postalCode" /> <label
				for="registerCity">Longitude : </label> <input required
				type="number" value="${ville.getLongitude()}"   id="longitude" name="longitude"
				step="any" /> <label for="registerCity">Latitude : </label> <input
				required type="number" value="${ville.getLatitude()}"  id="latitude" name="latitude"
				step="any" /> <label for="registerCity">Libelle acheminement
				: </label> <input required type="text" value="${ville.getLibelleAcheminement()}" maxlength="20"
				id="libelle" name="libelle" /> 
				<label for="registerCity">Ligne_5
				: </label> <input type="text" value="${ville.getLigne5()}" maxlength="20"
				id="ligne5" name="ligne5" /> 
				<input type="submit"  style="margin-left: 70vh;" name = "valider" value="valider" onClick='confirm()'/>
		</form>
	</div>
</body>
</html>