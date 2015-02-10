/**
 * Initialisierungen nach Laden der Seite
 */
$(document).ready(function() {

	getArtist();
	getReleases();

});

function getArtist() {

	var artistId = urlParam("artistId");

	$
			.getJSON("api/artists/" + artistId + "/", function(artist) {

				initPageArtist(artist);

			})
			.fail(
					function(jqxhr, textStatus, error) {
						var errorMessage = "Beim laden des K&uuml;nstlers ist ein Fehler aufgetreten: "
								+ jqxhr.responseText;
						showErrorMsg(errorMessage, "Fehler");
					});

}

function initPageArtist(artist) {

	if (check(artist)) {

		$('#artistHeading').html(artist.name);

		if (check(artist.imageURL)) {
			$('#imageDiv').html(
					'<img src="' + artist.imageURL
							+ '" width="210px" height="210px">');
		} else {
			$('#imageDiv').html(
					'<img src="/medienverwaltung/resources/images/artist.png"'
							+ 'width="210px" height="210px">');

		}

		$('#fromSpan').html(artist.from);
		$('#formedSpan').html(artist.formed);
		$('#websiteSpan').html(
				'<a href="http://' + artist.website + '" target="_blank">'
						+ artist.website + '</a>');

		var genres = "";
		$.each(artist.genres, function(pos, genre) {
			
			genres = genres + '<span class="label label-primary labelMargin">'
					+ genre.name + '</span>'
		});
		$('#genreSpan').html(genres);

		var subgenres = "";
		$.each(artist.subgenres, function(pos, subgenre) {

			subgenres = subgenres
					+ '<span class="label label-success labelMargin">'
					+ subgenre.name + '</span> ';

		});

		$('#subgenreSpan').html(subgenres);

	}

}

function getReleases() {

	var artistId = urlParam("artistId");

	$
			.getJSON("api/master/?artistId=" + artistId, function(masters) {

				createMasterTable(masters);

			})
			.fail(
					function(jqxhr, textStatus, error) {
						var errorMessage = "Beim laden der Ver&ouml;ffentlichungen ist ein Fehler aufgetreten: "
								+ jqxhr.responseText;
						showErrorMsg(errorMessage, "Fehler");
					});

}

function createMasterTable(masters) {

	$('#releaseTable')
			.DataTable(
					{
						"bPaginate" : false,
						"bInfo" : false,
						"bFilter" : false,
						"sPaginationType" : "full_numbers",
						"aaSorting" : [ [ 1, "asc" ] ],
						"oLanguage" : {

							"sEmptyTable" : "Keine Ver&ouml;ffentlichungen zu diesem K&uuml;nstler gefunden.",
							"sInfo" : "_START_ bis _END_ von _TOTAL_",
							"sInfoEmpty" : "0 bis 0 von 0",
							"sInfoFiltered" : "(gefiltert von _MAX_ Einträgen)",
							"sInfoPostFix" : "",
							"sInfoThousands" : ".",
							"sLoadingRecords" : "Wird geladen...",
							"sProcessing" : "Bitte warten...",
							"sZeroRecords" : "Keine Einträge vorhanden.",

						},
						"oClasses" : {}

					});

	$.each(masters, function(pos, master) {
		addRow(master);

	});

	var table = $('#releaseTable').dataTable();
	table.fnDraw();
	
	new $.fn.dataTable.Responsive(table, {
		details : false
	});

}

function addRow(master) {
	var table = $('#releaseTable').dataTable();

	var cover = check(master.url) ? "<img src='" + master.url
			+ "' width=100px height=100px>" : "";
	var title = check(master.title) ? "<a href='/medienverwaltung/master?masterId="
			+ master.masterId + "'>" + master.title + "</a>"
			: "";
	var artist = check(master.artist) ? "<a href='/medienverwaltung/artist?artistId="
			+ master.artistId + "'>" + master.artist + "</a>"
			: "";
	var jahr = check(master.releaseYear) ? master.releaseYear : "";
	var genre = check(master.genre) ? master.genre : "";

	table.fnAddData([ cover, title, artist, jahr, genre ], false);

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

function urlParam(name) {
	var results = new RegExp('[\?&]' + name + '=([^&#]*)')
			.exec(window.location.href);
	if (results == null) {
		return null;
	} else {
		return results[1] || 0;
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