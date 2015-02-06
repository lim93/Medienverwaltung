$(document).ready(function() {

	$('.logo').css('cursor', 'pointer');
	$(".logo").click(function() {
		window.location = "../medienverwaltung/";
	});

	// Suchen-Button
	$("#suchButton").button({}).click(function(e) {
		var input = $("#suchInput").val();
		e.preventDefault();
		e.stopPropagation();

		if (input != undefined & input != null & input != "") {
			window.location = "../medienverwaltung/suche?suche=" + input;
		} else {
			window.location = "../medienverwaltung/suche";
		}

	});

	// Login-Button
	$("#goToLoginButton").button({}).click(function(e) {
		e.preventDefault();
		e.stopPropagation();

		window.location = "../medienverwaltung/login";

	});

	// Collection-Button
	$("#collectionButton").button({}).click(function(e) {
		e.preventDefault();
		e.stopPropagation();

		window.location = "../medienverwaltung/profil";

	});

	var userId = $('#userId').val();
	var userName = $('#userName').val();

	if (userId !== "") {
		$('#loginButtonDiv').addClass("hidden");
		$('#collectionDiv').removeClass("hidden");
		$('#loginDropdown').removeClass("hidden");
		$('#userNameSpan').html(userName);

	} else {

		$('#loginButtonDiv').removeClass("hidden");
		$('#collectionDiv').addClass("hidden");
		$('#loginDropdown').addClass("hidden");

	}

});

function urlParam(name) {
	var results = new RegExp('[\?&]' + name + '=([^&#]*)')
			.exec(window.location.href);
	if (results == null) {
		return null;
	} else {
		return results[1] || 0;
	}
}