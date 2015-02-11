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

h4 {
	margin-bottom: 20px;
}
</style>
</head>
<body style="margin-top: 72px">
	<jsp:include page="includes/includeScriptImports.jsp" />

	<!-- Login JS -->
	<script type="text/javascript"
		src="/medienverwaltung/resources/js/login.js"></script>

	<!-- Password Encryption -->
	<script
		src="http://crypto-js.googlecode.com/svn/tags/3.1.2/build/rollups/sha3.js"></script>

	<!-- Navbar importieren -->
	<jsp:include page="navigation.jsp" />




	<div id=content
		style="display: table; margin-left: auto; margin-right: auto; margin-bottom: 20px; max-width: 95%">



		<div class="panel panel-default">
			<div class="panel-heading">
				<h2 class="panel-title">Anmeldung</h2>
			</div>
			<div class="panel-body">

				<div id="errorDiv"></div>

				<div id=login
					style="width: auto; max-width: 300px; display: inline-block; float: left; margin-right: 20px;">

					<h4>Einloggen</h4>

					<form id=loginForm action="">
						<h5>Benutzername:</h5>
						<input style="width: 250px" name="name" id="name" type="text"
							class="form-control" placeholder="Benutzername" />
						<h5>Passwort:</h5>
						<input style="width: 250px" name="pw" id="pw" type="password"
							class="form-control" placeholder="Passwort" /> <br>
						<button id="loginButton" class="btn btn-success" type="submit">
							<span class="glyphicon glyphicon-log-in"></span> <b>Einloggen</b>
						</button>
					</form>


				</div>

				<div
					style="display: inline-block; float: left; padding-left: 20px; height: 370px; border-left: 1px solid grey;"
					class="hidden-xs"></div>



				<div id=register
					style="width: auto; max-width: 300px; display: inline-block; float: left;">


					<h4>Registrieren</h4>

					<form id=registerForm action="">
						<h5>Benutzername*:</h5>
						<input style="width: 250px" name="nameRegister" id="nameRegister"
							type="text" class="form-control" placeholder="Benutzername" />
						<h5>Email*:</h5>
						<input style="width: 250px" name="mailRegister" id="mailRegister"
							type="text" class="form-control" placeholder="Email" />
						<h5>Passwort*:</h5>
						<input style="width: 250px" name="pwRegister" id="pwRegister"
							type="password" class="form-control" placeholder="Passwort" />
						<h5>Passwort wiederholen*:</h5>
						<input style="width: 250px" name="pwControl" id="pwControl"
							type="password" class="form-control" placeholder="Passwort" /> <br>
						<button id="registerButton" class="btn btn-primary" type="submit">
							<span class="glyphicon glyphicon-user"></span> <b>Registrieren</b>
						</button>
					</form>

				</div>

			</div>
		</div>


	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>
