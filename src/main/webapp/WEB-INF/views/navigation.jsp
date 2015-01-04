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
                <div class="form-group">
                    <input id="suchInput" type="text" style="width:350px" class="form-control" placeholder="Katalog durchsuchen">
                </div>
                <button id="suchButton" type="submit" class="btn btn-info">Suchen</button>
            </form>
            <ul class="nav navbar-nav navbar-right">

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Krispin Limbach <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">Einstellungen</a>
                        </li>
                        <li><a href="#">Hilfe</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="#">Abmelden</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
</nav>

</body>
</html>