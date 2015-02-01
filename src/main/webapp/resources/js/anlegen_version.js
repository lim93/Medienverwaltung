$(document).ready(function() {

	// Speichern-Button => Version anlegen und weiter zur angelegten Version
	$("#speichernButton").button({}).click(function(e) {
		e.preventDefault();
		e.stopPropagation();

		validateAndSubmit();

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

	// addButton => Weiteres Feld für die Tracklist
	$("#addButton").button({}).click(function(e) {
		addRow();

	});

	$('#label').blur(function() {

		var label = this.value;

		checkLabel(label);

	});

	// Master über dessen id holen und damit Meta-Informationen setzen
	getMaster();

	// Welche Formate können gewählt werden?
	getFormats();

	// Leere Inputfelder für Tracks anlegen
	initTracks();

});

function initTracks() {

	$('#tracks').html("<table id='trackTable'></table>");

	for (i = 0; i < 5; i++) {

		addRow();
	}

}

function addRow() {

	var table = document.getElementById("trackTable");

	var rowId = table.rows.length;

	var row = table.insertRow(rowId);

	var number = row.insertCell(0);
	var title = row.insertCell(1);
	var duration = row.insertCell(2);

	number.innerHTML = '<input id="number' + rowId + '" '
			+ 'type="text"class="form-control trackNumber"'
			+ 'placeholder="#" />';
	title.innerHTML = '<input id="title' + rowId + '" type="text"'
			+ 'class="form-control trackTitel"placeholder="Titel"/>';
	duration.innerHTML = '<input id="duration' + rowId
			+ '" type="text"class="form-control '
			+ 'trackDuration" placeholder="00:00" />';

}

function getMaster() {

	var masterId = urlParam("masterId");

	$
			.getJSON("api/masterdto/" + masterId + "/", function(master) {

				initMeta(master);

			})
			.fail(
					function(jqxhr, textStatus, error) {
						var errorMessage = "Beim laden des Masters ist ein Fehler aufgetreten: "
								+ jqxhr.responseText;
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
								+ jqxhr.responseText;
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

function validateAndSubmit() {

	var labelAnlegenHidden = $('#labelAnlegenDiv').hasClass("hidden");

	if (!labelAnlegenHidden) {

		if (validateAndSubmitLabel() == false) {
			return false;
		}
		$("#label").val($("#labelName").val());
	}

	var masterId = urlParam("masterId");
	var formatId = 0;
	var formattype = '';
	var label = $("#label").val();
	var labelcode = $("#lc").val();
	var catalogNo = $("#cat").val();
	var barcode = $("#barcode").val();
	var tracklist = [];
	var comment = $("#comment").val();
	var releaseDate = $("#releaseDate").val();
	var releaseDay = 0;
	var releaseMonth = 0;
	var releaseYear = 0;

	$('.format').each(function(i, format) {
		if ($(format).hasClass('active')) {
			formatId = $(format).data('formatid');
			formattype = $(format).data('formattype');
		}
	});

	var table = document.getElementById("trackTable");

	var rowCount = table.rows.length;

	for (i = 0; i < rowCount; i++) {
		var number = $('#number' + i).val();
		var title = $('#title' + i).val();
		var duration = $('#duration' + i).val();

		if (number === "" | title === "" | duration === "") {

			if (number === "" & title === "" & duration === "") {
				// komplett leer => alles ok
			} else {
				showErrorMsg("Geben Sie zu jedem Track die Nummer, den Titel und die Länge an");
				return false;

			}

		} else {
			var track = {
				number : number,
				title : title,
				duration : duration
			};

			tracklist[i] = track;
		}

	}

	if (tracklist.length < 1) {
		showErrorMsg("Geben Sie mindestens einen Titel in der Tracklist an");
		return false;
	}

	isvalid = true;
	var errorMessage = "Es wurden nicht alle Pflichtfelder bef&uuml;llt. Bitte bef&uuml;llen Sie:<br><ul>";
	if (formatId == "0" | formattype == "") {
		// Format muss gesetzt sein
		errorMessage = errorMessage + "<li>Format</li>";
		isvalid = false;
	}

	if (label === "") {
		// Label muss gesetzt sein
		errorMessage = errorMessage + "<li>Label</li>";
		isvalid = false;
	}

	if (labelcode == "") {
		// Labelcode muss gesetzt sein
		errorMessage = errorMessage + "<li>Labelcode</li>";
		isvalid = false;
	}

	if (releaseDate == "") {
		errorMessage = errorMessage + "<li>Erscheinungsdatum</li>";
		isvalid = false;
	}

	if (!isvalid) {
		// Falls eine Information nicht gesetzt ist:
		errorMessage = errorMessage + "</ul>";
		showErrorMsg(unescape(errorMessage));
		errorMessage = undefined;
		return;
	} else {

		if (validateReleaseDate(releaseDate) == false) {
			return false;

		}

		var releaseArray = releaseDate.split(".");

		if (releaseArray.length === 1) {
			releaseYear = releaseArray[0];
		}

		if (releaseArray.length === 2) {
			releaseMonth = releaseArray[0];
			releaseYear = releaseArray[1];
		}

		if (releaseArray.length === 3) {
			releaseDay = releaseArray[0];
			releaseMonth = releaseArray[1];
			releaseYear = releaseArray[2];
		}

	}

	// Wenn bis hierhin alles ok: POST an den Rest-Service
	saveVersion(masterId, formatId, label, labelcode, catalogNo, barcode,
			tracklist, comment, releaseDay, releaseMonth, releaseYear).done(
			function(result) {

				window.location = "../medienverwaltung/version?versionId="
						+ result + "&masterId=" + masterId;
			}).fail(
			function(jqxhr, textStatus, error) {
				var errorMessage = "Beim Anlegen ist ein Fehler aufgetreten: "
						+ jqxhr.responseText;
				showErrorMsg(errorMessage);
				return false;
			});

}

function saveVersion(masterId, formatId, label, labelcode, catalogNo, barcode,
		tracklist, comment, releaseDay, releaseMonth, releaseYear) {
	return $.ajax({
		url : 'api/release/',
		type : 'POST',
		data : JSON.stringify({
			"masterId" : masterId,
			"formatId" : formatId,
			"label" : label,
			"labelcode" : labelcode,
			"catalogNo" : catalogNo,
			"barcode" : barcode,
			"comment" : comment,
			"releaseDay" : releaseDay,
			"releaseMonth" : releaseMonth,
			"releaseYear" : releaseYear,
			"tracklist" : tracklist

		}),
		contentType : "application/json; charset=utf-8",
		dataType : 'json'

	});
}

function validateAndSubmitLabel() {

	var labelName = $("#labelName").val();
	var labelWebsite = $("#labelWebsite").val();

	isvalid = true;
	var errorMessage = "Es wurden nicht alle Pflichtfelder bef&uuml;llt. Bitte bef&uuml;llen Sie:<br><ul>";
	if (labelName == "") {
		// Name muss gesetzt sein
		errorMessage = errorMessage + "<li>Labelname</li>";
		isvalid = false;
	}

	if (labelWebsite == "") {
		// Webseite muss gesetzt sein
		errorMessage = errorMessage + "<li>Webseite</li>";
		isvalid = false;
	}

	if (!isvalid) {
		// Falls eine Information nicht gesetzt ist:
		errorMessage = errorMessage + "</ul>";
		showErrorMsg(unescape(errorMessage));
		errorMessage = undefined;
		return false;
	}

	// Wenn bis hierhin alles ok: POST an den Rest-Service
	saveLabel(labelName, labelWebsite)
			.done(function(result) {

				return true;

			})
			.fail(
					function(jqxhr, textStatus, error) {
						var errorMessage = "Beim Anlegen des Labels ist ein Fehler aufgetreten: "
								+ jqxhr.responseText;
						showErrorMsg(errorMessage);
						return false;
					});

}

function saveLabel(labelName, labelWebsite) {
	return $.ajax({
		url : 'api/labels/',
		type : 'POST',
		data : JSON.stringify({
			"name" : labelName,
			"website" : labelWebsite,

		}),
		contentType : "application/json; charset=utf-8",
		dataType : 'json'

	});
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

function checkLabel(label) {

	if (label !== "") {

		$
				.getJSON(
						"api/labels/search/?name=" + label,
						function(label) {

							if (label.name !== "") {

								$("#messageDiv").html(
										'<div class="alert alert-success" role="alert">'
												+ 'Label bekannt.</div>');

								$('#lebelAnlegenDiv').addClass("hidden");
							}

						})
				.fail(
						function(jqxhr, textStatus, error) {
							$("#messageDiv")
									.html(
											'<div class="alert alert-danger" role="alert">'
													+ 'Dieses Label ist uns nicht bekannt. '
													+ 'Pr&uuml;fen Sie die Schreibweise oder legen '
													+ 'Sie ein neues Label an.</div>');

							$('#labelAnlegenDiv').removeClass("hidden");
						});

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

function urlParam(name) {
	var results = new RegExp('[\?&]' + name + '=([^&#]*)')
			.exec(window.location.href);
	if (results == null) {
		return null;
	} else {
		return results[1] || 0;
	}
}
