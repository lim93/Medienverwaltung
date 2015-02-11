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
<!-- responsive -->
<link rel="stylesheet"
	href="/medienverwaltung/resources/css/dataTables.responsive.css">

<!-- Styles ---------------------->
<style type="text/css">
a { /
	word-wrap: break-word;
}

table {
	width: 100%;
}

.coverPane {
	clear: both;
	width: 210px;
	height: auto;
	margin-left: 15px;
	margin-right: 15px;
	float: left;
	margin-bottom: 30px;
}

.infoPane {
	overflow: hidden;
	min-width: 300px;
	margin-left: 15px;
	margin-right: 7.5px;
	padding: 0px 5px 5px 0px;
	margin-bottom: 30px;
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


	<div id=content style="width: 100%;">


		<div class=coverPane>
			<div id="imageDiv"></div>

			<hr>
			Genre: <span id="genreSpan"></span>
			<hr>
			Subgenres: <span id="subgenreSpan"></span>
			<hr>

			<div id="addToCollectionDiv" class="hidden">
				<button id="addButton" class="btn btn-success">
					<b>Zur Sammlung hinzuf&uuml;gen</b>
				</button>

				<button id="removeButton" class="btn btn-danger">
					<span class="glyphicon glyphicon-trash"></span> <b>Aus Sammlung
						entfernen</b>
				</button>
				<hr>
			</div>

			<button id="allButton" class="btn btn-primary">
				<span class="glyphicon glyphicon-list"></span> <b>Andere
					Versionen</b>
			</button>



		</div>

		<div class=infoPane>

			<div>
				<div id="controls" style="margin-bottom: 5px;">
					<div id="errorDiv"></div>
					<h2 id="titleHeading" style="margin-top: 0px;"></h2>
					<h3 id="artistHeading" style="margin-top: 0px;"></h3>
					<hr>

				</div>

				<div>

					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">&Uuml;ber diese Version:</h4>
						</div>
						<div class="panel-body">

							<p>
								<b>Label:</b> <span id="labelSpan"></span>, Labelcode: <span
									id="lcSpan"></span>
							</p>
							<p>
								<b>Format:</b> <span id="formatSpan"></span>
							</p>
							<p>
								<b>Ver&ouml;ffentlicht:</b> <span id="releaseSpan"></span>
							</p>


						</div>
					</div>


					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">Trackliste:</h4>
						</div>
						<div class="panel-body">

							<div id="tableDiv" class="dataTable">

								<table id="table" class="display dt-responsive" width="100%">
									<thead>
										<tr>
											<th>Nummer</th>
											<th>Titel</th>
											<th>Länge</th>
										</tr>
									</thead>
									<tbody></tbody>
								</table>
							</div>
						</div>
					</div>


					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">Notiz:</h4>
						</div>
						<div class="panel-body">

							<p>
								<span id="noteSpan"></span>
							</p>
						</div>
					</div>

					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">Barcode und andere
								Identifikationsmerkmale:</h4>
						</div>
						<div class="panel-body">

							<p>
								<b>Barcode:</b> <span id="barcodeSpan"></span>
							</p>
							<p>
								<b>Katalog-Nummer (CAT):</b> <span id="catSpan"></span>
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