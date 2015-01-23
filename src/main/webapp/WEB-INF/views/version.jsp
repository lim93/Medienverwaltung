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

<!-- DataTables CSS -->
<link rel="stylesheet" media="screen"
	href="/medienverwaltung/resources/css/jquery.dataTables.css">
<link rel="stylesheet" media="screen"
	href="/medienverwaltung/resources/css/jquery.dataTables_themeroller.css">


<!-- Styles ---------------------->
<style type="text/css">
a { /
	word-wrap: break-word;
}

.coverPane {
	width: 210px;
	height: auto;
	float: left;
	margin-left: 15px;
	position: absolute;
}

.infoPane {
	width: 1250px;
	margin-left: 7.5px;
	padding: 0px 5px 5px 235px;
	float: left;
}

.labelMargin {
	font-size: 90%;
	display: inline-block;
	margin-bottom: 3px;
}
</style>
</head>
<body style="margin-top: 72px">
	<jsp:include page="includes/includeScriptImports.jsp" />

	<!-- Profil JS -->
	<script type="text/javascript"
		src="/medienverwaltung/resources/js/version.js"></script>


	<!-- Navbar importieren -->
	<jsp:include page="navigation.jsp" />


	<div id=content>


		<div class=coverPane>
			<img src="/medienverwaltung/resources/cover/0009.jpeg" width="210px"
				height="210px">

			<hr>
			Genre: <span class="label label-primary labelMargin">Rock</span>
			<hr>
			Subgenres: <span class="label label-success labelMargin">Punk</span>
			<span class="label label-success labelMargin">Ska</span>
			<hr>

			<button id="addButton"
				class="btn btn-success">
				<span class="glyphicon glyphicon-headphones"></span> <b>Zur
					Sammlung hinzuf&uuml;gen</b>
			</button>
			<hr>

			<button id="allButton" class="btn btn-primary">
				<span class="glyphicon glyphicon-list"></span> <b>Andere
					Versionen</b>
			</button>



		</div>

		<div class=infoPane>

			<div>
				<div id="controls" style="margin-bottom: 5px;">
					<h2 style="margin-top: 0px;">Faszination Weltraum</h2>
					<h3 style="margin-top: 0px;">
						<a>Farin Urlaub Racing Team</a>
					</h3>
					<hr>

				</div>

				<div>

					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">&Uuml;ber diese Version:</h4>
						</div>
						<div class="panel-body">

							<p>
								<b>Label:</b> <a>Völker hört die Tonträger</a>, Labelcode:
								LC11590
							</p>
							<p>
								<b>Format:</b> Vinyl
							</p>
							<p>
								<b>Ver&ouml;ffentlicht:</b> 14. Oktober 2014
							</p>


						</div>
					</div>


					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">Trackliste:</h4>
						</div>
						<div class="panel-body">

							<div id="tableDiv" class="dataTable"></div>
						</div>
					</div>


					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">Notiz:</h4>
						</div>
						<div class="panel-body">

							<p>Doppel-Vinyl inklusive Booklet und Download-Code.</p>
						</div>
					</div>

					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">Barcode und andere
								Identifikationsmerkmale:</h4>
						</div>
						<div class="panel-body">

							<p>
								<b>Barcode:</b> 4 019593 007699
							</p>
							<p>
								<b>Katalog-Nummer (CAT):</b> 930 076-9
							</p>
						</div>
					</div>




				</div>





			</div>

		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>

</html>