$(document).ready(function() {

	// Login-Button
	$("#loginButton").button({}).click(function(e) {
		e.preventDefault();
		e.stopPropagation();

		login();

	});

	// Registrieren-Button
	$("#registerButton").button({}).click(function(e) {
		e.preventDefault();
		e.stopPropagation();

		register();

	});

});

function login() {
	var user = $('#name').val();
	var pw = $('#pw').val();

	var errorMessage = "Bitte geben Sie Ihren Benutzernamen und Ihr Passwort an.";

	if (user === "" | pw === "") {
		showErrorMsg(errorMessage);
		return false;
	}

	var pwHash = CryptoJS.SHA3(pw, {
		outputLength : 224
	});

	// POST an den Rest-Service
	checkLogin(user, pwHash.toString(CryptoJS.enc.Base64))
			.done(
					function(result) {

						if (result !== 0) {

							window.location = "../medienverwaltung/loginSuccessful?userId="
									+ result + "&userName=" + user;
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

function checkLogin(user, pwHash) {
	return $.ajax({
		url : 'api/users/login/',
		type : 'POST',
		data : JSON.stringify({
			"username" : user,
			"password" : pwHash

		}),
		contentType : "application/json; charset=utf-8",
		dataType : 'json'

	});
}

function register() {
	var user = $('#nameRegister').val();
	var email = $('#mailRegister').val();
	var pw = $('#pwRegister').val();
	var pwControl = $('#pwControl').val();

	var errorMessage = "Die Registrierung war nicht erforlgreich:<br><ul>";
	var valid = true;

	if (user === "") {
		errorMessage = errorMessage
				+ "<li>Bitte w&auml;hlen Sie einen Benutzernamen.</li>";
		valid = false;
	}

	if (email === "") {
		errorMessage = errorMessage
				+ "<li>Falls Sie Ihr Passwort vergessen sollten, brauchen wir Ihre Email-Adresse, um es zur&uuml;cksetzen zu k&ouml;nnen.</li>";
		valid = false;
	} else {
		// Email-Adresse muss dem folgenden Format entsprechen:
		// 'wort@wort.wort'.
		var mailRegex = new RegExp("^.+@.+\\.[^.]{2,}$");

		if (!mailRegex.test(email)) {

			errorMessage += "<li>Die angegebene Email-Adresse entspricht nicht dem g&uuml;ltigen Format. <br />"
					+ "Beispiel f&uuml;r eine g&uuml;ltige Eingabe: ihr.name@provider.de</li>";
			valid = false;
		}
	}

	if (pw === "" | pw.length < 6) {
		errorMessage = errorMessage
				+ "<li>Bitte w&auml;hlen Sie ein Passwort das aus mindestens 6 Zeichen besteht.</li>";
		valid = false;
	}

	if (pwControl === "" | pwControl !== pw) {
		errorMessage = errorMessage
				+ "<li>Die eingegebenen Passw&ouml;rter stimmen nicht &uuml;berein.</li>";
		valid = false;
	}

	if (valid === false) {
		showErrorMsg(errorMessage);
		return false;
	}

	var pwHash = CryptoJS.SHA3(pw, {
		outputLength : 224
	});

	// POST an den Rest-Service
	postUser(user, email, pwHash.toString(CryptoJS.enc.Base64)).done(
			function(result) {

				window.location = "../medienverwaltung/loginSuccessful?userId="
						+ result + "&userName=" + user;

			}).fail(function(jqxhr, textStatus, error) {
		var errorMessage = "Fehler: " + jqxhr.responseText;
		showErrorMsg(errorMessage);
		return false;
	});

}

function postUser(user, email, pw) {
	return $.ajax({
		url : 'api/users/',
		type : 'POST',
		data : JSON.stringify({
			"userName" : user,
			"email" : email,
			"password" : pw

		}),
		contentType : "application/json; charset=utf-8",
		dataType : 'json'

	});
}

function showErrorMsg(message) {

	$("#errorDiv").html(
			'<div class="alert alert-danger alert-dismissible"'
					+ 'role="alert"><button type="button" class="close" '
					+ 'data-dismiss="alert" aria-label="Close"><span '
					+ 'aria-hidden="true">&times;</span></button>' + message
					+ '</div>');

}