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


	<!-- Navbar importieren -->
	<jsp:include page="navigation.jsp" />


	<div id=content>


		<div class=coverPane>
			<div id="imageDiv">
				<img src="/medienverwaltung/resources/images/artist.png"
					width="210px" height="210px">
			</div>

			<hr>
			Genre: <span id="genreSpan"><div
					class="label label-primary labelMargin">Rock</div></span>
			<hr>
			Subgenres: <span id="subgenreSpan"><div
					class="label label-success labelMargin">Punk</div></span>
			<hr>


		</div>

		<div class=infoPane>

			<div>
				<div id="controls" style="margin-bottom: 5px;">
					<div id="errorDiv"></div>
					<h2 id="artistHeading" style="margin-top: 0px;">donots</h2>
					<hr>

				</div>

				<div>

					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">&Uuml;ber diesen K&uuml;nstler:</h4>
						</div>
						<div class="panel-body">

							<p>
								<b>Herkunft:</b> <span id="fromSpan">Ibbenbüren,
									Deutschland</span>
							</p>
							<p>
								<b>Gr&uuml;ndung:</b> <span id="formedSpan">1994</span>
							</p>
							<p>
								<b>Webseite:</b> <span id="websiteSpan"><a
									href="http://www.donots.de/" target="_blank">www.donots.de</a></span>
							</p>


						</div>
					</div>


					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">Ver&ouml;ffentlichungen:</h4>
						</div>
						<div class="panel-body">

							<p>To be done...</p>

							<div id="tableDiv" class="dataTable"></div>
						</div>
					</div>

				</div>

			</div>

		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>

</html>