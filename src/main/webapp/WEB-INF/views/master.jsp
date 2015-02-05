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
	min-height: 450px;
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
		src="/medienverwaltung/resources/js/master.js"></script>


	<!-- Navbar importieren -->
	<jsp:include page="navigation.jsp" />


	<div id=content>


		<div class=coverPane>
			<div id="imageDiv"></div>

			<hr>
			Genre: <span id="genreSpan"></span>
			<hr>
			Subgenres: <span id="subgenreSpan"></span>
			<hr>
			Ver&ouml;ffentlicht: <span id="releaseSpan"></span>

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
					<h4 style="float: left;">Versionen:</h4>



					<div id="tableDiv" class="dataTable"></div>

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
			</div>

		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>

</html>