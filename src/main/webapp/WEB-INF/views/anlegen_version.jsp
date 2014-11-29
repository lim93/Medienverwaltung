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
.track {
	height: 34px;
	margin-bottom: 10px;
	width: 100%;
	clear: both;
	margin-bottom: 10px;
}

.trackNumber {
	width: 40px;
	float: left;
	margin-left: 10px;
}

.trackTitel {
	width: 300px;
	float: left;
	margin-left: 10px;
}

.trackDuration {
	width: 80px;
	float: left;
	margin-left: 10px;
}

.reducedPanelPadding {
	padding-top: 0px;
}
</style>
</head>
<body style="margin-top: 72px">
	<jsp:include page="includes/includeScriptImports.jsp" />

	<!-- Anlegen JS -->
	<script type="text/javascript"
		src="/medienverwaltung/resources/js/anlegen_version.js"></script>


	<!-- Navbar importieren -->
	<jsp:include page="navigation.jsp" />


	<div id=content
		style="max-width: 80%; margin-left: auto; margin-right: auto">

		<h2>Version anlegen</h2>

		<div id="editDiv" class="panel panel-default"
			style="float: left; display: inline; width: 70%; padding: 0px 20px 20px 20px">

			<h4>
				Version anlegen für "<span id="titelSpan">Sonic Highways</span>" von
				"<span id="artistSpan">Foo Fighters</span>"
			</h4>
			<form action="">



				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">Format</h4>
					</div>
					<div class="panel-body">
						<div class="btn-group" data-toggle="buttons">
							<label class="btn btn-primary"> <input type="radio"
								name="vinyl" id="rock" autocomplete="off"> Vinyl
							</label> <label class="btn btn-primary"> <input type="radio"
								name="cd" id="metal" autocomplete="off"> CD
							</label> <label class="btn btn-primary"> <input type="radio"
								name="kassette" id="kassette" autocomplete="off">
								Kassette
							</label> <label class="btn btn-primary"> <input type="radio"
								name="mp3" id="mp3" autocomplete="off"> mp3
							</label>

						</div>
					</div>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">Label</h4>
					</div>
					<div class="panel-body">
						<input style="width: 250px" name="label" id="label" type="text"
							class="form-control" placeholder="Label" />

					</div>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">Identifikation</h4>
					</div>
					<div class="panel-body reducedPanelPadding">
						<h5>Labelcode (LC)</h5>
						<input style="width: 250px" name="lc" id="lc" type="text"
							class="form-control" placeholder="Labelcode" />

						<h5>Barcode</h5>
						<input style="width: 250px" name="barcode" id="barcode"
							type="text" class="form-control" placeholder="Barcode" />

					</div>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">Tracklist</h4>
					</div>
					<div class="panel-body">
						<div id="tracks" name="tracks">
							<div class="track">
								<input name="number" type="text"
									class="form-control trackNumber" placeholder="#" /> <input
									name="track" type="text" class="form-control trackTitel"
									placeholder="Titel" /> <input name="duration" type="text"
									class="form-control trackDuration" placeholder="00:00" />
							</div>

							<div class="track">
								<input name="number" type="text"
									class="form-control trackNumber" placeholder="#" /> <input
									name="track" type="text" class="form-control trackTitel"
									placeholder="Titel" /> <input name="duration" type="text"
									class="form-control trackDuration" placeholder="00:00" />
							</div>

							<div class="track">
								<input name="number" type="text"
									class="form-control trackNumber" placeholder="#" /> <input
									name="track" type="text" class="form-control trackTitel"
									placeholder="Titel" /> <input name="duration" type="text"
									class="form-control trackDuration" placeholder="00:00" />
							</div>

							<div class="track">
								<input name="number" type="text"
									class="form-control trackNumber" placeholder="#" /> <input
									name="track" type="text" class="form-control trackTitel"
									placeholder="Titel" /> <input name="duration" type="text"
									class="form-control trackDuration" placeholder="00:00" />
							</div>

							<div class="track">
								<input name="number" type="text"
									class="form-control trackNumber" placeholder="#" /> <input
									name="track" type="text" class="form-control trackTitel"
									placeholder="Titel" /> <input name="duration" type="text"
									class="form-control trackDuration" placeholder="00:00" />
							</div>


						</div>
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
								oder mm.yyyy oder yyyy. Kann vom Master abweichen.</span>
						</div>
					</div>
				</div>


				<button style="float: right; margin-left: 15px;" id="speichernButton"
					class="btn btn-success">Speichern</button>




			</form>






		</div>

		<div id="guideDiv"
			style="float: right; display: inline; max-width: 25%">
			<p style="font-size: 16px; word-wrap: break-word;">Auf dieser
				Seite k&ouml;nnen Sie eine konkrete Version einer
				Ver&ouml;ffentlichung eintragen. Dazu z&auml;hlen Format, Label, die
				Trackliste und weitere Identifikationsmerkmale.</p>
		</div>





	</div>
</body>
</html>
