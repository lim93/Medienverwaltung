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
	href="/medienverwaltung/resources/bootstrap/css/bootstrap.css"
	rel="stylesheet">

<!-- Bootstrap Selectpicker CSS-->
<link rel="stylesheet" media="screen"
	href="/medienverwaltung/resources/css/bootstrap-select.min.css"
	rel="stylesheet">


<!-- Styles ---------------------->
<style type="text/css">
p {
	word-wrap: break-word;
}

tr {
	height: 44px;
}

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

.addButton {
	margin-top: 5px;
	margin-left: 10px;
}

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



	<div id=content class="hidden"
		style="max-width: 80%; margin-left: auto; margin-right: auto">

		<h2>Version anlegen</h2>

		<div id="editDiv" class="panel panel-default"
			style="float: left; display: inline; width: 70%; padding: 0px 20px 20px 20px; margin-bottom: 50px;">

			<h4>
				Version anlegen für "<span id="titelSpan"></span>" von "<span
					id="artistSpan"></span>"
			</h4>
			<form action="" id="inputForm">



				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">Format*</h4>
					</div>
					<div class="panel-body">

						<div id="formatDiv"></div>

					</div>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">Label</h4>
					</div>
					<div class="panel-body">
						<h5>Name des Labels:*</h5>
						<input style="width: 250px" name="label" id="label" type="text"
							class="form-control" placeholder="Name" />

						<div id="labelAnlegenDiv" class="hidden">

							<h5>Webseite:*</h5>
							<input style="width: 250px" name="labelWebsite" id="labelWebsite"
								type="text" class="form-control" placeholder="www.example.com" />

						</div>

						<div id="messageDiv" style="margin-top: 15px;"></div>
						<hr>

						<h5>Labelcode (LC)*</h5>
						<input style="width: 250px" name="lc" id="lc" type="text"
							class="form-control" placeholder="LC-12345" />

					</div>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">Identifikation</h4>
					</div>
					<div class="panel-body reducedPanelPadding">

						<h5>Katalog-Nummer (CAT)</h5>
						<input style="width: 250px" name="cat" id="cat" type="text"
							class="form-control" placeholder="cat" />

						<h5>Barcode</h5>
						<input style="width: 250px" name="barcode" id="barcode"
							type="text" class="form-control" placeholder="Barcode" />

					</div>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">Tracklist*</h4>
					</div>
					<div class="panel-body">
						<div id="tracks" name="tracks"></div>
						<div id=tracksControl>
							<button id="addButton" class="btn btn-success addButton">
								<span class="glyphicon glyphicon-plus"></span> <b>weiterer</b>
							</button>
						</div>
					</div>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">Notizen</h4>
					</div>
					<div class="panel-body">
						<div>
							<textarea rows="4"
								style="width: 100%; max-width: 100%; min-width: 100%; display: inline; float: left"
								name="comment" id="comment" class="form-control"></textarea>
						</div>
					</div>
				</div>


				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">Erscheinungsdatum*</h4>
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


				<button style="float: right; margin-left: 15px;"
					id="speichernButton" class="btn btn-success">
					<span class="glyphicon glyphicon-save"></span> <b>Speichern</b>
				</button>




			</form>






		</div>

		<div id="guideDiv"
			style="float: right; display: inline; max-width: 25%; margin-bottom: 50px;">
			<p style="font-size: 16px; word-wrap: break-word;">Auf dieser
				Seite k&ouml;nnen Sie eine konkrete Version einer
				Ver&ouml;ffentlichung eintragen. Dazu z&auml;hlen Format, Label, die
				Trackliste und weitere Identifikationsmerkmale.</p>

			<p>
				<b>Format: </b>Geben Sie hier den physikalischen oder digitalen
				Tonträgr ihrer Version an.
			</p>

			<p>
				<b>Label: </b>Hier können Sie den Namen des Labels eintragen, unter
				dem Ihre Version der Ver&ouml;ffentlichung erschienen ist.<br>Der
				<b>Labelcode</b> setzt sich aus den Buchstaben "LC" und einer vier-
				bis f&uuml;nfstelligen Zahl zusammen. Jedes Label besitzt einen
				eigenen Labelcode, der in der Regel auf allen
				Ver&ouml;ffentlichungen des Labels abgedruckt ist.
			</p>

			<p>
				<b>Identifikation: </b>Zur eindeutigen Identifikation Ihrer Version
				empfehlen wir, weitere Merkmale anzugeben. <br> Dazu
				geh&ouml;rt die <b>Katalog-</b> oder <b>CAT-Nummer</b>, welche vom
				Label f&uuml;r jede Ver&ouml;ffentlichung vergeben wird und
				besonders bei Schallplatten auch direkt auf dem Tonträger zu finden
				ist.
			</p>

			<p>
				<b>Tracklist: </b>Geben Sie hier die Titel in der Reihenfolge an,
				wie sie auf der Ver&ouml;ffentlichung vorkommen. &Uuml;bernehmen Sie
				bei der Nummerierung den Stil von der Ver&ouml;ffentlichung,
				beispielsweise "1, 2, 3, 4" oder "A01, A02, B01, B02". Beachten Sie
				die Groß- und Kleinschreibung.
			</p>

			<p>
				<b>Notizen: </b>Hier können Sie beliebige Zusatzinformationen zu
				Ihrer Version angeben. Beispiele:
			</p>
			<ul>
				<li>"Auf rotem Vinyl"</li>
				<li>"Auf 500 Stück limitiert + signiert"</li>
				<li>"Inklusive Download-Code"</li>
			</ul>


			<p>
				<b>Erscheinungsdatum: </b>Das Erscheinungsdatum Ihrer Version kann
				vom urspr&uuml;nglichen Erscheinungsdatum der Ver&ouml;ffentlichung
				abweichen (bei Nachpressungen, Jubil&auml;umsausgaben oder
				regionalen Unterschieden). Geben Sie daher hier m&ouml;glichst exakt
				das Erscheinungsdatum ihrer konkreten Version an. G&uuml;ltige
				Formate sind:
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
