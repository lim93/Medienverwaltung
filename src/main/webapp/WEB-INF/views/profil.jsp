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
	width: 210px;
	height: auto;
	float: left;
	margin-left: 15px;
	position: absolute;
}

.medienBereich {
	width: 1250px;
	padding: 0px 5px 5px 235px;
	float: left;
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
	font-size:90%;
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


	<div id=content>


		<div class=profil>
			<img src="/medienverwaltung/resources/profile/profile.jpg">
			<h3>Krispin Limbach</h3>

			<hr>
			Genres:
			<div class="label label-primary labelMargin">Rock</div>
			<div class="label label-primary labelMargin">Punk</div>
			<div class="label label-primary labelMargin">Ska</div>
			<div class="label label-primary labelMargin">Metal</div>
			<div class="label label-primary labelMargin">Nu-Metal</div>
			<div class="label label-primary labelMargin">Ska-Punk</div>
			<div class="label label-primary labelMargin">Hardrock</div>
			<hr>
			Favoriten: <span class="label label-success labelMargin">die
				ärzte</span> <span class="label label-success labelMargin">System Of
				A Down</span> <span class="label label-success labelMargin">Donots</span> <span
				class="label label-success labelMargin">Farin Urlaub Racing
				Team</span> <span class="label label-success labelMargin">Foo
				Fighters</span> <span class="label label-success labelMargin">Linkin
				Park</span> <span class="label label-success labelMargin">Metallica</span>
			<span class="label label-success labelMargin">Nirvana</span> <span
				class="label label-success labelMargin">Rise Against</span> <span
				class="label label-success labelMargin">Sondaschule</span>

			<hr>
			<p>Sammlung: 12</p>
		</div>

		<div class=medienBereich>

			<div>
				<div id="controls" style="margin-bottom: 5px; height: 40px">
					<h2 style="margin-top: 0px; margin-left: 7.5px; float: left">Krispins
						Sammlung</h2>
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

	<jsp:include page="footer.jsp" />

</body>

</html>