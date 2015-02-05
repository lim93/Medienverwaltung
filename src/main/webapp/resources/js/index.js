$(document).ready(function() {

	$('#suchFormDiv').addClass("hidden");
	$('#indexSuchInput').focus();

	// Suchen-Button
	$("#indexSuchButton").button({}).click(function(e) {
		var input = $("#indexSuchInput").val();
		e.preventDefault();
		e.stopPropagation();

		if (input != undefined & input != null & input != "") {
			window.location = "../medienverwaltung/suche?suche=" + input;
		} else {
			window.location = "../medienverwaltung/suche";
		}

	});

});