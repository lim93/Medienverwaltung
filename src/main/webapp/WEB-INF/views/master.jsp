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
			<img src="/medienverwaltung/resources/cover/0009.jpeg" width="210px"
				height="210px">

			<hr>
			Genre: <span class="label label-primary">Rock</span>
			<hr>
			Subgenres: <span class="label label-success">Punk</span> <span
				class="label label-success">Ska</span>
			<hr>
			Ver&ouml;ffentlicht: <span>2014</span>

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
					<h4 style="float: left;">Versionen:</h4>



					<div id="tableDiv" class="dataTable"></div>

					<h5>
						<span style="float: right; display: inline">Ihre Version
							nicht dabei? Jetzt anlegen
							<button id="anlegenButton" class="btn btn-success"><b>+</b></button>
						</span>
					</h5>

				</div>
			</div>

		</div>
	</div>

</body>

</html>