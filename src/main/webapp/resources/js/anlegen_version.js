$(document).ready(function() {

	// Speichern-Button => Version anlegen und weiter zur angelegten Version
	$("#speichernButton").button({}).click(function(e) {
		e.preventDefault();
		e.stopPropagation();

		alert("gespeichert");

		window.location = "../medienverwaltung/profil";

	});

	// Enter => funktionslos
	$('.form-control').bind("keydown", function(e) {
		$(this).addClass('keydowning');
		if (e.which == 13) {
			e.preventDefault();
			e.stopPropagation();
		}
		$(this).removeClass('keydowning');

	});

	// Submit
	$('#inputForm').submit(function(e) {
		e.preventDefault();
		e.stopPropagation();
		validateAndSubmit();

	});

	// Master über dessen id holen und damit Meta-Informationen setzen
	getMaster();

	// Welche Formate können gewählt werden?
	getFormats();

	// Leere Inputfelder für Tracks anlegen
	initTracks();

});

function initTracks() {

	for (i = 0; i < 6; i++) {
		$('#tracks')
				.html(
						$('#tracks').html()
								+ '<div class="track"><input name="number"'
								+ 'type="text"class="form-control trackNumber"'
								+ 'placeholder="#" /> <input name="track" type="text"'
								+ 'class="form-control trackTitel"placeholder="Titel"/>'
								+ '<input name="duration" type="text"class="form-control '
								+ 'trackDuration" placeholder="00:00" /></div>');
	}

}

function getMaster() {

	var masterId = $.urlParam("masterId");

	$
			.getJSON("api/master/" + masterId + "/", function(master) {

				initMeta(master);

			})
			.fail(
					function(jqxhr, textStatus, error) {
						var errorMessage = "Beim laden des Masters ist ein Fehler aufgetreten: "
								+ textStatus + ", " + error;
						showErrorMsg(errorMessage, "Fehler");
					});

}

function initMeta(master) {

	if (master !== null & master !== undefined) {

		$('#titelSpan').html(master.title);

		$('#artistSpan').html(master.artist);

	}

}

function getFormats() {
	$
			.getJSON("api/formats/", function(formats) {

				initFormats(formats);

			})
			.fail(
					function(jqxhr, textStatus, error) {
						var errorMessage = "Beim laden der Formate ist ein Fehler aufgetreten: "
								+ textStatus + ", " + error;
						showErrorMsg(errorMessage, "Fehler");
					});

}

function initFormats(formats) {

	var formatString = "<div class='btn-group' data-toggle='buttons'>";

	$.each(formats, function(pos, format) {
		formatString = formatString
				+ "<label class='btn btn-primary format' data-formatid='"
				+ format.formatId + "' data-formattype='" + format.type + "'>"
				+ "<input type='radio' name='" + format.type + "' id='"
				+ format.type + "' autocomplete='off'> " + format.type
				+ "</label>";

	});

	formatString = formatString + "</div>";

	$('#formatDiv').html(formatString);

}

function validateReleaseDate(date) {

	var errorMessage = "";

	// Datum kann folgendes Format haben: yyyy
	var yearRegex = new RegExp("^(19|20)\\d\\d$");

	if (yearRegex.test(date) == true) {
		return true;
	}

	// Datum kann folgendes Format haben: mm.yyyy
	var yearAndMonthRegex = new RegExp("^(0?[1-9]|1[0-2])\\.(19|20)\\d\\d$");

	if (yearAndMonthRegex.test(date) == true) {
		return true;
	}

	// Datum kann folgendes Format haben: dd.mm.yyyy
	var fullDateRegex = new RegExp(
			"^(0?[1-9]|[12][0-9]|3[01])\\.(0?[1-9]|1[0-2])\\.(19|20)\\d\\d$");

	if (fullDateRegex.test(date) == false) {
		errorMessage = "Das angegebene Datum entspricht keinem g&uuml;ltigen Format. "
				+ "Beispiele f&uuml;r g&uuml;ltige Eingaben: "
				+ "<ul><li>01.01.2015</li><li>01.2015</li><li>2015</li></ul>";
		showErrorMsg(unescape(errorMessage));

		return false;
	} else {

		// Datum muss gültig sein
		var comp = date.split('.');
		var m = parseInt(comp[1], 10);
		var d = parseInt(comp[0], 10);
		var y = parseInt(comp[2], 10);
		var testDate = new Date(y, m - 1, d);

		if ((testDate.getFullYear() != y) || (testDate.getMonth() + 1 != m)
				|| (testDate.getDate() != d)) {
			errorMessage = "Das angegebene Erscheinungsdatum ist ung&uuml;ltig.";
			showErrorMsg(unescape(errorMessage));

			return false;
		}

		return true;
	}

}

function showErrorMsg(message) {

	$("#errorDiv").html(
			'<div class="alert alert-danger alert-dismissible"'
					+ 'role="alert"><button type="button" class="close" '
					+ 'data-dismiss="alert" aria-label="Close"><span '
					+ 'aria-hidden="true">&times;</span></button>' + message
					+ '</div>');

}

$.urlParam = function(name) {
	var results = new RegExp('[\?&]' + name + '=([^&#]*)')
			.exec(window.location.href);
	if (results == null) {
		return null;
	} else {
		return results[1] || 0;
	}
}
