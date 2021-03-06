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

.centered {
	width: 480px;
	max-width: 100%;
	position: absolute;
	top:20%;
	left: 0;
	right: 0;
	margin-left: auto;
	margin-right: auto;
}

.content {
	position: absolute;
	background: url(/medienverwaltung/resources/images/back.jpg) no-repeat
		center center fixed; 
	width: 100%;
	height: 100%;
	min-height:500px;
	background-size: cover;
}

body {
	width: 100%;
	height: 100%;
}

h1 {
	text-align: center;
	font-weight: bolder;
}
</style>


</head>
<body>
	<jsp:include page="includes/includeScriptImports.jsp" />


	<!-- Index JS -->
	<script type="text/javascript"
		src="/medienverwaltung/resources/js/index.js"></script>

	<!-- Navbar importieren -->
	<jsp:include page="navigation.jsp" />


	<div id=content class="content">



		<div class="centered">



			<form id=searchForm action="">

				<div style="margin-left: 16px; margin-right: auto; max-width: 100%">
					<input id="indexSuchInput" type="text"
						style="width: 70%; float: left; margin-left: 0px; margin-right: 5px; margin-top: 10px;"
						class="form-control" placeholder="Katalog durchsuchen">

					<button id="indexSuchButton" type="submit" class="btn btn-info"
						style="display: inline; width: 26%; margin-right: 5px; margin-top: 10px;">
						<span class="glyphicon glyphicon-search"></span> <b>Suchen</b>
					</button>
				</div>

			</form>

			<h1>
				<span style="color: white">Nach K&uuml;nstlern und
					Ver&ouml;ffentlichungen</span><span style="color: #449d44;">
					suchen.</span>
			</h1>
			<h1>
				<span style="color: white">Die eigene Musiksammlung</span><span
					style="color: #449d44;"> verwalten.</span>
			</h1>
			<h1>
				<span style="color: white">Neue Musik</span><span
					style="color: #449d44;"> entdecken.</span>
			</h1>

		</div>



	</div>

	<jsp:include page="footer.jsp" />

</body>
</html>
