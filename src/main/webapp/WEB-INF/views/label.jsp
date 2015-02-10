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

.artistButton {
	
}

.coverPane {
	clear: both;
	width: 210px;
	height: auto;
	margin-left: 15px;
	margin-right: 15px;
	float: left;
	width: 210px;
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


	<!-- Label JS -->
	<script type="text/javascript"
		src="/medienverwaltung/resources/js/label.js"></script>

	<!-- Navbar importieren -->
	<jsp:include page="navigation.jsp" />


	<div id=content style="width: 100%;">


		<div class=coverPane>
			<div id="imageDiv"></div>


		</div>

		<div class=infoPane>

			<div>
				<div id="controls" style="margin-bottom: 5px;">
					<div id="errorDiv"></div>
					<h2 id="labelHeading" style="margin-top: 0px;"></h2>
					<hr>

				</div>

				<div>

					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">&Uuml;ber dieses Label:</h4>
						</div>
						<div class="panel-body">
							<p>
								<b>Webseite:</b> <span id="websiteSpan"></span>
							</p>


						</div>
					</div>


					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">K&uuml;nstler:</h4>
						</div>
						<div class="panel-body">


							<div id="artistDiv"></div>
						</div>
					</div>


					<div class="panel panel-default" style="margin-bottom: 50px;">
						<div class="panel-heading">
							<h4 class="panel-title">Auf diesem Label erschienen:</h4>
						</div>
						<div class="panel-body">

							<div id="releaseTableDiv" class="dataTable">
								<table id="releaseTable" class="display dt-responsive"
									width="100%">
									<thead>
										<tr>
											<th></th>
											<th>Titel</th>
											<th>K&uuml;nstler</th>
											<th class="min-tablet-l">CAT #</th>
											<th class=not-mobile>Format</th>
											<th class="min-tablet-l">Jahr</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
						</div>
					</div>

				</div>

			</div>

		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>

</html>