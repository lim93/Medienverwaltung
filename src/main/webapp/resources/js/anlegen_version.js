$(document).ready(function() {
	
	// Speichern-Button
	$("#speichernButton").button({}).click(function(e) {
		e.preventDefault();
		e.stopPropagation();
		
		alert("gespeichert");

		window.location = "../medienverwaltung/profil";

	});

});

