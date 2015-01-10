<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Medienverwaltung</title>

<!-- Imports ---------------------->

<!-- Bootstrap CSS-->
<link rel="stylesheet" media="screen"
	href="/medienverwaltung/resources/css/bootstrap.css" rel="stylesheet">

<!-- Bootstrap Selectpicker CSS-->
<link rel="stylesheet" media="screen"
	href="/medienverwaltung/resources/css/bootstrap-select.min.css"
	rel="stylesheet">
<!-- Styles ---------------------->
<style type="text/css">
p {
	word-wrap: break-word;
}

.genre {
	margin-bottom: 5px;
}

.subselect {
	
}
</style>
</head>
<body style="margin-top: 72px">
	<jsp:include page="includes/includeScriptImports.jsp" />

	<!-- Anlegen JS -->
	<script type="text/javascript"
		src="/medienverwaltung/resources/js/anlegen_master.js"></script>


	<!-- Navbar importieren -->
	<jsp:include page="navigation.jsp" />


	<div id=content
		style="max-width: 80%; margin-left: auto; margin-right: auto">

		<h2>Veröffentlichung anlegen</h2>

		<div id="editDiv" class="panel panel-default"
			style="float: left; display: inline; width: 70%; padding: 20px 20px 20px 20px">

			<form action="" id="inputForm">

				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">K&uuml;nstler</h4>
					</div>
					<div class="panel-body">
						<input style="width: 250px" name="artist" id="artist" type="text"
							class="form-control" placeholder="K&uuml;nstler" />
					</div>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">Titel</h4>
					</div>
					<div class="panel-body">
						<input style="width: 250px" name="title" id="title" type="text"
							class="form-control" placeholder="Titel" />

					</div>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">Genre</h4>
					</div>

					<div id="genrePanel" class="panel-body">

						<div id="genreDiv"></div>
						<div id="subGenreDiv"></div>

					</div>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">Coverbild</h4>
					</div>
					<div class="panel-body">
						<!--  <button id="uploadButton" class="btn btn-default">Datei
							ausw&auml;hlen</button>-->
						<input style="width: 450px; display: inline; float: left"
							name="coverURL" id="coverURL" type="text" class="form-control"
							placeholder="www.example.com/images/1234" />
					</div>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">Erscheinungsdatum</h4>
					</div>
					<div class="panel-body">
						<div>
							<input style="width: 150px; display: inline; float: left"
								name="releaseDate" id="releaseDate" type="text"
								class="form-control" />
						</div>
					</div>
				</div>

				<div id="errorDiv"></div>


				<button style="float: right; margin-left: 15px;" id="weiterButton"
					class="btn btn-success">Speichern und weiter</button>


			</form>



		</div>

		<div id="guideDiv"
			style="float: right; display: inline; max-width: 25%">
			<p style="font-size: 16px;">Auf dieser Seite k&ouml;nnen Sie die
				Basis-Informationen zu einer Ver&ouml;ffentlichung eintragen. Dazu
				z&auml;hlen K&uuml;nstler, Titel, Genre und das Erscheinungsjahr.</p>
			<p style="font-size: 16px;">Im Anschluss können Sie Ihre konkrete
				Version der Veröffentlichung anlegen (Format, Pressung, etc.).</p>

			<p>
				<b>K&uuml;nstler: </b>Name des K&uuml;nstlers, der Gruppe, etc.
				Beachten Sie die Groß- und Kleinschreibung.
			</p>

			<p>
				<b>Titel: </b>&Uuml;bernehmen Sie den Titel so exakt wie
				m&ouml;glich von der Ver&ouml;ffentlichung. Beachten Sie die Groß-
				und Kleinschreibung.
			</p>

			<p>
				<b>Genre: </b>Ordnen Sie die Ver&ouml;ffentlichung einem Genre zu.
				Danach k&ouml;nnen Sie optional beliebig viele Subgenres
				ausw&auml;hlen.
			</p>

			<p>
				<b>Coverbild: </b>Eine Internetadresse (URL), unter der das Bild
				abrufbar ist.
			</p>

			<p>
				<b>Erscheinungsdatum: </b>Geben Sie m&ouml;glichst exakt das
				Erscheinungsdatum an. G&uuml;ltige Formate sind:
			</p>
			<ul>
				<li>dd.mm.yyyy</li>
				<li>mm.yyyy</li>
				<li>yyyy</li>
			</ul>


		</div>






	</div>
	<jsp:include page="footer.jsp" />

</body>

</html>
