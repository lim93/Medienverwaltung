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

.profil {
	width: 15%;
	min-width: 210px;
	height: auto;
	float: left;
	margin-left: 15px;
	position: absolute;
	display: inline-block;
}

.medienBereich {
	width: 100%;
	min-height: 500px;
	padding: 0px 5px 5px 235px;
	float: right;
	display: inline-block;
}

.medienObjekt {
	width: 222.5px;
	display: inline-block;
	vertical-align: top;
	margin: 0px 7.5px 20px 7.5px;
	padding: 5px;
	border: 1px grey;
}

.dataTable {
	margin: 0px 7.5px 20px 7.5px;
}

.searchFilterPadding {
	padding-right: 7px;
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
		src="/medienverwaltung/resources/js/profil.js"></script>


	<!-- Navbar importieren -->
	<jsp:include page="navigation.jsp" />

	<div id=errorDiv
		style="max-width: 80%; margin-left: auto; margin-right: auto;"></div>

	<div id=content style="width: 100%;" class="hidden">


		<div class=profil>
			<span id=imageSpan></span> <span id=nameSpan></span>

			<hr>
			Genres: <span id=genreSpan></span>
			<hr>
			Favoriten: <span id=favoritenSpan></span>

			<hr>
			<p>
				Sammlung: <span id=collectionSize></span>
			</p>
		</div>

		<div class=medienBereich style="margin-bottom: 50px;">

			<div>
				<div id="controls" style="margin-bottom: 5px; height: 40px">
					<h2 style="margin-top: 0px; margin-left: 7.5px; float: left">
						Sammlung von <span id=collectionNameSpan></span>
					</h2>
					<button id="listButton"
						style="margin-right: 7.5px; float: right; display: inline"
						type="submit" class="btn btn-default hide"
						onclick="listenAnsicht();">
						<span class="glyphicon glyphicon-th-list"></span> Listenansicht
					</button>
					<button id="bigButton"
						style="margin-right: 7.5px; float: right; display: inline"
						type="submit" class="btn btn-default" onclick="grosseAnsicht();">
						<span class="glyphicon glyphicon-th-large"></span> Große Ansicht
					</button>
				</div>

				<div id="tableDiv" class="dataTable"></div>


			</div>

		</div>
	</div>



	<div id="removeModal" class="modal fade">

		<div class="modal-dialog">

			<div class="modal-content">

				<div class="modal-header">

					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>

					<h4 class="modal-title">Best&auml;tigung</h4>

				</div>

				<div class="modal-body">

					<p>Wollen Sie diese Ver&ouml;ffentlichung aus Ihrer Sammlung
						entfernen?</p>

				</div>

				<div class="modal-footer">

					<button type="button" class="btn btn-default" data-dismiss="modal">Abbrechen</button>

					<span id="removeButtonSpan"></span>

				</div>

			</div>

		</div>

	</div>



	<jsp:include page="footer.jsp" />

</body>

</html>