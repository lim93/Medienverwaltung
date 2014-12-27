$(document).ready(function() {

	// Speichern-Button
	$("#speichernButton").button({}).click(function(e) {
		e.preventDefault();
		e.stopPropagation();

		alert("gespeichert");

		window.location = "../medienverwaltung/profil";

	});

	// Weiter-Button
	$("#weiterButton").button({}).click(function(e) {
		e.preventDefault();
		e.stopPropagation();

		window.location = "../medienverwaltung/anlegen_version";

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
								+ textStatus + ", " + error;
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
								+ "<label class='btn btn-primary genre'>"
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
