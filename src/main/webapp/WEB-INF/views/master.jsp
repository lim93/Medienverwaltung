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


	<div id=content style="width: 100%;">


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



					<div id="tableDiv" class="dataTable">
						<table id="table" class="display dt-responsive"
							style="margin-top: 30px;" width="100%">

							<thead>
								<tr>
									<th></th>
									<th>Titel</th>
									<th class=min-tablet-l>CAT#</th>
									<th class=not-mobile>Label</th>
									<th>Format</th>
									<th class=min-tablet-l>Jahr</th>
								</tr>
							</thead>
							<tbody></tbody>
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
			</div>

		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>

</html>