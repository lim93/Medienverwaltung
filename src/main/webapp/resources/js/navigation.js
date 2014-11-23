$(document).ready(function() {

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

});