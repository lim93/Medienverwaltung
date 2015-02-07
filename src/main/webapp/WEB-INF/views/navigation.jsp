<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Styles ---------------------->
<style type="text/css">
a { /
	word-wrap: break-word;
}

.logo {
	margin-top: 8px;
	margin-bottom: 5px;
	color: white;
}
</style>
</head>
<body>

	<div class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">

			<!-- Header -->
			<div class="navbar-header">

				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>

				<a class="navbar-brand" href="../medienverwaltung/">Medienverwaltung</a>

			</div>

			<div class="collapse navbar-collapse">


				<!-- Zur Sammlung, Login/Logout, weitere Links -->
				<ul id="loginDropdown" class="nav navbar-nav navbar-right hidden">

					<li id=userDiv>
						<div id=collectionDiv class="hidden" style="padding-left: 15px">
							<button id="collectionButton" type="submit"
								class="btn btn-success navbar-btn">
								<span class="glyphicon glyphicon-headphones"></span> <b>Meine
									Sammlung</b>
							</button>
						</div>
					</li>

					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"><span id=userNameSpan></span><b
							class="caret"></b></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#"><span class="glyphicon glyphicon-cog"></span>
									Einstellungen</a></li>
							<li><a href="#"><span
									class="glyphicon glyphicon-question-sign"></span> Hilfe</a></li>
							<li class="divider"></li>
							<li><a href="/medienverwaltung/logout"><span
									class="glyphicon glyphicon-log-out"></span> Abmelden</a></li>
						</ul></li>
				</ul>

				<div id="loginButtonDiv" class="nav navbar-nav navbar-right hidden">
					<div id=loginButtonDiv style="padding-left: 15px">
						<button id="goToLoginButton" class="btn btn-success navbar-btn">
							<span class="glyphicon glyphicon-log-in"></span> <b> Anmelden</b>
						</button>
					</div>
				</div>


				<!-- Suche -->
				<form class="navbar-form navbar-right hidden-sm">
					<div id="suchFormDiv">
						<div class="form-group">
							<input id="suchInput" type="text" style="width: 250px"
								class="form-control" placeholder="Katalog durchsuchen">
						</div>
						<button id="suchButton" type="submit" class="btn btn-info">
							<span class="glyphicon glyphicon-search"></span> <b>Suchen</b>
						</button>
					</div>
				</form>

			</div>

		</div>
	</div>


	<input type="hidden" id="userId" value="${sessionScope.userId}" />
	<input type="hidden" id="userName" value="${sessionScope.userName}" />
</body>
</html>