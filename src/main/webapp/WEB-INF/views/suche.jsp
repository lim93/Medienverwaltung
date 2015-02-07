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


<script type="text/javascript">
	var suche = "${suche}";
</script>

<style type="text/css">
.searchFilterPadding {
	padding-right: 7px;
}

.loading {
	color: #777;
	font-size: 1.5em;
	display: none;
	position: fixed;
	z-index: 1000;
	top: 0;
	left: 0;
	height: 100%;
	width: 100%;
	text-align: center;
	background: rgba(255, 255, 255, .8)
		url('/medienverwaltung/resources/images/loading.gif') 50% 50%
		no-repeat;
}
</style>

</head>
<body style="margin-top: 72px">
	<jsp:include page="includes/includeScriptImports.jsp" />

	<!-- Suche JS -->
	<script type="text/javascript"
		src="/medienverwaltung/resources/js/suche.js"></script>


	<!-- Navbar importieren -->
	<jsp:include page="navigation.jsp" />




	<div id=content
		style="max-width: 80%; margin-left: auto; margin-right: auto">

		<div id=errorDiv></div>


		<div style="height: 20px; margin-bottom: 30px;">
			<h2>
				<span style="float: left; display: inline">Suchergebnisse
					f&uuml;r "${suche}"</span>
			</h2>


			<span style="float: right"></span>
		</div>
		<div id="divLoading" style="display: inline; float: left;">&nbsp;</div>


		<!-- ========================== Suchergebnisse ============================ -->
		<div id="tableDiv">
			<table id="releaseTable" class="display">
				<thead>
					<tr>
						<th></th>
						<th>Titel</th>
						<th>K&uuml;nstler</th>
						<th>Jahr</th>
						<th>Genre</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>


		<h5>
			<span id="anlegenSpan"
				style="float: right; display: inline; margin-top: 15px; margin-bottom: 50px;">

				<span id="anlegenTextSpan"></span>
				<button id="anlegenButton" class="btn btn-success hidden">
					<span class="glyphicon glyphicon-plus"></span> <b>Anlegen</b>
				</button>

				<button id="loginButton" class="btn btn-success hidden">
					<span class="glyphicon glyphicon-log-in"></span> <b>Anmelden</b>
				</button>
			</span>
		</h5>


	</div>

	<jsp:include page="footer.jsp" />
</body>
</html>
