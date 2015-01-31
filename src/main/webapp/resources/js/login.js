$(document).ready(function() {

	// Login-Button
	$("#loginButton").button({}).click(function(e) {
		e.preventDefault();
		e.stopPropagation();

		login();

	});

});

function login() {
	var user = $('#name').val();
	var pw = $('#pw').val();

	var errorMessage = "Bitte geben Sie Ihren Benutzernamen und Ihr Passwort an.";

	if (check(user) === false | check(pw) === false) {
		showErrorMsg(errorMessage);
		return false;
	}

	// POST an den Rest-Service
	checkLogin(user, pw)
			.done(
					function(result) {

						if (result !== 0) {

							window.location = "../medienverwaltung/loginSuccessful?userId="
									+ result;
						} else {
							showErrorMsg("Falscher Benutzername oder falsches Passwort.");
						}

					})
			.fail(
					function(jqxhr, textStatus, error) {
						var errorMessage = "Beim Anmelden ist ein Fehler aufgetreten: "
								+ jqxhr.responseText;
						showErrorMsg(errorMessage);
						return false;
					});

}

function checkLogin(user, pw) {
	return $.ajax({
		url : 'api/users/login/',
		type : 'POST',
		data : JSON.stringify({
			"username" : user,
			"password" : pw

		}),
		contentType : "application/json; charset=utf-8",
		dataType : 'json'

	});
}

function check(value) {
	if (value == "") {
		return false;
	}

	if (value == null) {
		return false;
	}

	if (value == undefined) {
		return false;
	}

	return true;
}

function showErrorMsg(message) {

	$("#errorDiv").html(
			'<div class="alert alert-danger alert-dismissible"'
					+ 'role="alert"><button type="button" class="close" '
					+ 'data-dismiss="alert" aria-label="Close"><span '
					+ 'aria-hidden="true">&times;</span></button>' + message
					+ '</div>');

}