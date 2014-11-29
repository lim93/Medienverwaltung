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

			<form action="">

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

					<div class="panel-body">
						<div class="btn-group" data-toggle="buttons">
							<label class="btn btn-primary"> <input type="radio"
								name="rock" id="rock" autocomplete="off"> Rock
							</label> <label class="btn btn-primary"> <input type="radio"
								name="metal" id="metal" autocomplete="off"> Metal
							</label> <label class="btn btn-primary"> <input type="radio"
								name="bullshit" id="bullshit" autocomplete="off">
								Bullshit
							</label>


						</div>

						<div id="rockDiv" class="hide"
							style="margin-top: 20px; width: 200px">
							<p>Subgenre</p>
							<select class="selectpicker" name="rockSelect" id="rockSelect"
								data-width="auto" multiple>

							</select>

						</div>
						<div id="metalDiv" class="hide"
							style="margin-top: 20px; width: 200px">
							<p>Subgenre</p>
							<select class="selectpicker" name="metalSelect" id="metalSelect"
								data-width="auto" multiple>

							</select>

						</div>
						<div id="bullshitDiv" class="hide"
							style="margin-top: 20px; width: 200px">
							<p>Subgenre</p>
							<select class="selectpicker" name="bullshitSelect"
								id="bullshitSelect" data-width="auto" multiple>

							</select>

						</div>

					</div>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">Coverbild</h4>
					</div>
					<div class="panel-body">
						<button id="uploadButton" class="btn btn-default">Datei
							ausw&auml;hlen</button>
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
								class="form-control" /> <span
								style="margin-left: 7px; display: inline-block; padding-top: 7px; padding-bottom: 7px; min-height: 20px">dd.mm.yyyy
								oder mm.yyyy oder yyyy</span>
						</div>
					</div>
				</div>


				<button style="float: right; margin-left: 15px;" id="weiterButton"
					class="btn btn-success">Speichern und weiter</button>
				<button style="float: right;" id="speichernButton"
					class="btn btn-default">Als Master speichern</button>




			</form>






		</div>

		<div id="guideDiv"
			style="float: right; display: inline; max-width: 25%">
			<p style="font-size: 16px; word-wrap: break-word;">Auf dieser
				Seite k&ouml;nnen Sie die Basis-Informationen zu einer
				Ver&ouml;ffentlichung eintragen. Dazu z&auml;hlen K&uuml;nstler,
				Titel, Genre und das Erscheinungsjahr.</p>
			<p style="font-size: 16px; word-wrap: break-word;">Im Anschluss
				können Sie Ihre konkrete Version der Veröffentlichung anlegen
				(Format, Pressung...), oder die Veröffentlichung als Master
				speichern und Ihre Version später anlegen.</p>
		</div>





	</div>
</body>
</html>
