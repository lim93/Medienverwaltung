/**
 * Initialisierungen nach Laden der Seite
 */
$(document).ready(function() {

	getLabel();
	getArtists();
	getReleases();

});

function getLabel() {

	var labelId = urlParam("labelId");

	$
			.getJSON("api/labels/" + labelId + "/", function(label) {

				initPageLabel(label);

			})
			.fail(
					function(jqxhr, textStatus, error) {
						var errorMessage = "Beim laden des Labels ist ein Fehler aufgetreten: "
								+ jqxhr.responseText;
						showErrorMsg(errorMessage);
					});

}

function initPageLabel(label) {

	if (check(label)) {

		$('#labelHeading').html(label.name);

		if (check(label.imageURL)) {
			$('#imageDiv').html(
					'<img src="' + label.imageURL
							+ '" width="210px" height="210px">');
		} else {
			$('#imageDiv').html(
					'<img src="/medienverwaltung/resources/images/record.png"'
							+ 'width="210px" height="210px">');

		}

		$('#websiteSpan').html(
				'<a href="http://' + label.website + '" target="_blank">'
						+ label.website + '</a>');

	}

}

function getArtists() {

	var labelId = urlParam("labelId");

	$
			.getJSON("api/artists/?labelId=" + labelId, function(artists) {

				createArtists(artists);

			})
			.fail(
					function(jqxhr, textStatus, error) {
						var errorMessage = "Beim laden der K&uuml;nstler ist ein Fehler aufgetreten: "
								+ jqxhr.responseText;
						showErrorMsg(errorMessage);
					});

}

function createArtists(artists) {

	if (artists !== null & artists.length !== 0) {

		var artistString = "";

		$.each(artists, function(pos, artist) {

			artistString += '<button style="margin-right:5px;" data-artistId='
					+ artist.artistId + ' id="' + artist.artistId
					+ 'Button" class="btn btn-success artistButton"><b>'
					+ artist.name + '</b></button>';

		});

		$('#artistDiv').html(artistString);

		$('.artistButton').button({}).click(
				function(e) {
					e.preventDefault();
					e.stopPropagation();

					var $this = $(this);
					artistId = $this.data('artistid');

					window.location = "../medienverwaltung/artist?artistId="
							+ artistId;

				});

	}

}

function getReleases() {

	var labelId = urlParam("labelId");

	$
			.getJSON("api/release/?labelId=" + labelId, function(versionen) {

				createLabelReleaseTable(versionen);

			})
			.fail(
					function(jqxhr, textStatus, error) {
						var errorMessage = "Beim laden der Ver&ouml;ffentlichungen ist ein Fehler aufgetreten: "
								+ jqxhr.responseText;
						showErrorMsg(errorMessage);
					});

}

function createLabelReleaseTable(releases) {

	$('#releaseTable')
			.DataTable(
					{
						"bPaginate" : false,
						"bInfo" : false,
						"bFilter" : false,
						"sPaginationType" : "full_numbers",
						"oLanguage" : {

							"sEmptyTable" : "Zu diesem Label sind noch keine Ver&ouml;ffentlichungen vorhanden.",
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

	if (releases !== null & releases.length !== 0) {

		$.each(releases, function(pos, release) {

			addReleaseRow(release);

		});

		var table = $('#releaseTable').dataTable();

		table.fnDraw();
		new $.fn.dataTable.Responsive(table, {
			details : false
		});

	}

}

function addReleaseRow(release) {
	var table = $('#releaseTable').dataTable();

	var cover = check(release.imageURL) ? "<img src='" + release.imageURL
			+ "' width=85px height=85px>" : "";
	var title = check(release.title) ? "<a href='/medienverwaltung/version?versionId="
			+ release.releaseId
			+ "&masterId="
			+ release.masterId
			+ "'>"
			+ release.title + "</a>"
			: "";
	var cat = check(release.catalogNo) ? release.catalogNo : "";
	var format = check(release.format.type) ? release.format.type : "";
	var artist = check(release.artist) ? "<a href='/medienverwaltung/artist?artistId="
			+ release.artist.artistId + "'>" + release.artist.name + "</a>"
			: "";
	var jahr = check(release.releaseYear) ? release.releaseYear : "";

	table.fnAddData([ cover, title, artist, cat, format, jahr ], false);

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