$(document).ready(function() {

	var userId = $('#userId').val();

	if (!check(userId)) {

		window.location = "../medienverwaltung/login?error=1";

	} else {
		$('#content').removeClass("hidden");
	}

	// Weiter-Button => Master anlegen (submit) + weiter zu
	// Version anlegen
	$("#weiterButton").button({}).click(function(e) {
		e.preventDefault();
		e.stopPropagation();

		$('#inputForm').submit();

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

	// Speichern-Button => Nur Master anlegen
	// $("#speichernButton").button({}).click(function(e) {
	// e.preventDefault();
	// e.stopPropagation();
	//
	// $('#inputForm').submit(versionAnlegen);
	//
	// window.location = "../medienverwaltung/profil";
	//
	// });

	// Submit
	$('#inputForm').submit(function(e) {
		e.preventDefault();
		e.stopPropagation();
		validateAndSubmit();

	});

	$('#artist').blur(function() {

		var artist = this.value;

		checkArtist(artist);

	});

	// Genres laden
	getGenres();

});

function getGenres() {
	$
			.getJSON("api/genres/", function(genres) {

				initGenres(genres);

			})
			.fail(
					function(jqxhr, textStatus, error) {
						var errorMessage = "Beim laden der Genres ist ein Fehler aufgetreten: "
								+ jqxhr.responseText;
						showErrorMsg(errorMessage, "Fehler");
					});

}

function initGenres(genres) {

	var genreString = "<div class='btn-group' data-toggle='buttons'>";
	var subGenreString = "";

	$
			.each(
					genres,
					function(pos, genre) {
						genreString = genreString
								+ "<label class='btn btn-primary genre' data-genreid='"
								+ genre.genreId + "' data-genrename='"
								+ genre.name + "'>"
								+ "<input type='radio' name='" + genre.name
								+ "' id='" + genre.name
								+ "' autocomplete='off'> " + genre.name
								+ "</label>";

						if (genre.subgenres.length !== 0) {
							subGenreString = subGenreString
									+ "<div id='"
									+ genre.name
									+ "Div"
									+ "'class='hide subselect' style='margin-top: 20px; width: 200px'>"
									+ "<p>Subgenre</p><select class='selectpicker' "
									+ "id='" + genre.name + "Select"
									+ "' data-width='auto' "
									+ "multiple></select></div>";
						}

					});

	genreString = genreString + "</div>";

	$('#genreDiv').html(genreString);
	$('#subGenreDiv').html(subGenreString);

	$.each(genres, function(pos, genre) {

		if (genre.subgenres.length !== 0) {

			var subselect = $('#' + genre.name + 'Select')[0];

			$.each(genre.subgenres, function(pos, subgenre) {
				subselect.add(new Option(subgenre.name, subgenre.subgenreId));

			});
		}
	});

	$('.selectpicker').selectpicker('refresh');

	$.each(genres, function(pos, genre) {
		$("#" + genre.name).change(function() {
			hideSelects();
			$('#' + genre.name + 'Div').removeClass('hide');
		});
	});

}

function hideSelects() {

	$('.subselect').addClass('hide');

}

function validateAndSubmit() {

	var artist = $('#artist').val();

	$.getJSON("api/artists/search/?name=" + artist).done(function() {

		// Artist bekannt, also nur Master submitten
		validateAndSubmitMaster();

	}).fail(function(jqxhr, textStatus, error) {

		// Artist unbekannt, also Artist und Master submitten
		validateAndSubmitArtist();

	});

}

function validateAndSubmitArtist() {

	var artistName = $("#artist").val();
	var artistFormed = $("#artistFormed").val();
	var artistFrom = $("#artistFrom").val();
	var artistWebsite = $("#artistWebsite").val();

	isvalid = true;
	var errorMessage = "Es wurden nicht alle Pflichtfelder bef&uuml;llt. Bitte bef&uuml;llen Sie:<br><ul>";
	if (artistName == "") {
		// Artist muss gesetzt sein
		errorMessage = errorMessage + "<li>K&uuml;nstler</li>";
		isvalid = false;
	}

	if (artistFormed === "") {
		// Gründungsjahr muss gesetzt sein
		errorMessage = errorMessage + "<li>Gr&uuml;ndungsjahr</li>";
		isvalid = false;
	}

	if (artistFrom == "") {
		// Herkunft muss gesetzt sein
		errorMessage = errorMessage + "<li>Herkunft</li>";
		isvalid = false;
	}

	if (artistWebsite == "") {
		// Webseite muss gesetzt sein
		errorMessage = errorMessage + "<li>Webseite</li>";
		isvalid = false;
	}

	if (!isvalid) {
		// Falls eine Information nicht gesetzt ist:
		errorMessage = errorMessage + "</ul>";
		showErrorMsg(unescape(errorMessage));
		errorMessage = undefined;
		return;
	} else {

		if (validateDate(artistFormed) == false) {
			return false;
		}

	}

	// Wenn bis hierhin alles ok: POST an den Rest-Service
	saveArtist(artistName, artistFormed, artistFrom, artistWebsite)
			.done(function(result) {

				// Erst hier das anlegen des Masters anstoßen. Sonst wird
				// versucht, den Master anzulegen, bevor der Artist angelegt
				// wurde
				validateAndSubmitMaster();

			})
			.fail(
					function(jqxhr, textStatus, error) {
						var errorMessage = "Beim Anlegen des K&uuml;nstlers ist ein Fehler aufgetreten: "
								+ jqxhr.responseText;
						showErrorMsg(errorMessage);
						return false;
					});

}

function validateAndSubmitMaster() {

	var artist = $("#artist").val();
	var title = $("#title").val();
	var coverUrl = $("#coverURL").val();
	var releaseDate = $("#releaseDate").val();
	var genreId = 0;
	var genreName = '';
	var subgenreIds = [];
	var releaseDay = 0;
	var releaseMonth = 0;
	var releaseYear = 0;

	$('.genre').each(function(i, genre) {
		if ($(genre).hasClass('active')) {
			genreId = $(genre).data('genreid');
			genreName = $(genre).data('genrename');
		}
	});

	if (genreName !== '' & genreName !== undefined) {
		subgenreIds = $('#' + genreName + 'Select').val();
	}

	isvalid = true;
	var errorMessage = "Es wurden nicht alle Pflichtfelder bef&uuml;llt. Bitte bef&uuml;llen Sie:<br><ul>";
	if (artist == "") {
		// Artist muss gesetzt sein
		errorMessage = errorMessage + "<li>K&uuml;nstler</li>";
		isvalid = false;
	}

	if (title === "") {
		// Titel muss gesetzt sein
		errorMessage = errorMessage + "<li>Titel</li>";
		isvalid = false;
	}

	if (genreId === 0 | genreId === undefined) {
		// Genre muss gesetzt sein
		errorMessage = errorMessage + "<li>Genre</li>";
		isvalid = false;
	}

	if (coverUrl == "") {
		errorMessage = errorMessage + "<li>Coverbild</li>";
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

		if (validateDate(releaseDate) == false) {
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
	saveMaster(artist, title, genreId, subgenreIds, coverUrl, releaseDay,
			releaseMonth, releaseYear)
			.done(
					function(result) {

						window.location = "../medienverwaltung/anlegen_version?masterId="
								+ result;

					})
			.fail(
					function(jqxhr, textStatus, error) {
						var errorMessage = "Beim Anlegen ist ein Fehler aufgetreten: "
								+ jqxhr.responseText;
						showErrorMsg(errorMessage);
						return false;
					});

}

function saveArtist(artistName, artistFormed, artistFrom, artistWebsite) {
	return $.ajax({
		url : 'api/artists/',
		type : 'POST',
		data : JSON.stringify({
			"name" : artistName,
			"formed" : artistFormed,
			"from" : artistFrom,
			"website" : artistWebsite,

		}),
		contentType : "application/json; charset=utf-8",
		dataType : 'json'

	});
}

function saveMaster(artist, title, genreId, subgenreIds, coverUrl, releaseDay,
		releaseMonth, releaseYear) {
	return $.ajax({
		url : 'api/master/',
		type : 'POST',
		data : JSON.stringify({
			"artist" : artist,
			"title" : title,
			"genreId" : genreId,
			"subgenreIds" : subgenreIds,
			"url" : coverUrl,
			"releaseDay" : releaseDay,
			"releaseMonth" : releaseMonth,
			"releaseYear" : releaseYear,

		}),
		contentType : "application/json; charset=utf-8",
		dataType : 'json'

	});
}

function validateDate(date) {

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

function checkArtist(artist) {

	if (artist !== "") {

		$
				.getJSON(
						"api/artists/search/?name=" + artist,
						function(artist) {

							if (artist.name !== "") {

								$("#messageDiv")
										.html(
												'<div class="alert alert-success" role="alert">'
														+ 'K&uuml;nstler im Katalog vorhanden.</div>');

								$('#artistAnlegenDiv').addClass("hidden");
							}

						})
				.fail(
						function(jqxhr, textStatus, error) {
							$("#messageDiv")
									.html(
											'<div class="alert alert-danger" role="alert">'
													+ 'Dieser K&uuml;nstler ist uns nicht bekannt. '
													+ 'Pr&uuml;fen Sie die Schreibweise oder legen '
													+ 'Sie einen neuen K&uuml;nstler an.</div>');

							$('#artistAnlegenDiv').removeClass("hidden");
						});

	}

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
