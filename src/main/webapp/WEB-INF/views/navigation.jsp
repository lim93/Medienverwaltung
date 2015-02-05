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

	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container">
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li>
					<!-- <img src="/medienverwaltung/resources/NOTHING" width="85" height="34" style="margin:8px 0px 8px 0px"> -->
					<h2 class="logo">Medienverwaltung</h2>
				</li>
			</ul>
			<form class="navbar-form navbar-left">
				<div id="suchFormDiv">
					<div class="form-group">
						<input id="suchInput" type="text" style="width: 350px"
							class="form-control" placeholder="Katalog durchsuchen">
					</div>
					<button id="suchButton" type="submit" class="btn btn-info">
						<span class="glyphicon glyphicon-search"></span> <b>Suchen</b>
					</button>
				</div>
			</form>

			<div id=userDiv>

				<div id=collectionDiv class="nav navbar-nav logo hidden">
					<button id="collectionButton" type="submit" class="btn btn-success">
						<span class="glyphicon glyphicon-headphones"></span> <b>Meine
							Sammlung</b>
					</button>
				</div>

				<div id="loginDiv">

					<ul id="loginDropdown" class="nav navbar-nav navbar-right hidden">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"><span id=userNameSpan></span><span
								class="caret"></span></a>
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

					<div id="loginButtonDiv"
						class="nav navbar-nav navbar-right logo hidden">
						<button id="goToLoginButton" class="btn btn-success">
							<span class="glyphicon glyphicon-log-in"></span> <b> Anmelden</b>
						</button>
					</div>

				</div>


			</div>
		</div>
		<!-- /.navbar-collapse -->
	</div>

	<input type="hidden" id="userId" value="${sessionScope.userId}" /> <input
		type="hidden" id="userName" value="${sessionScope.userName}" /> </nav>

</body>
</html>