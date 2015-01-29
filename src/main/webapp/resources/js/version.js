/**
 * Initialisierungen nach Laden der Seite
 */
$(document).ready(function() {

	getMaster();
	getVersion();

	// Zurück zum Master
	$("#allButton").button({}).click(function(e) {
		e.preventDefault();
		e.stopPropagation();

		var masterId = urlParam("masterId");

		window.location = "../medienverwaltung/master?masterId=" + masterId;

	});

});

function getMaster() {

	var masterId = urlParam("masterId");

	$
			.getJSON("api/master/" + masterId + "/", function(master) {

				initPageMaster(master);

			})
			.fail(
					function(jqxhr, textStatus, error) {
						var errorMessage = "Beim laden des Masters ist ein Fehler aufgetreten: "
							+ jqxhr.responseText;
						showErrorMsg(errorMessage, "Fehler");
					});

}

function initPageMaster(master) {

	if (master !== null & master !== undefined) {

		$('#titleHeading').html(master.title);

		// TODO: Weiterleitung auf Artist-Seite sobald vorhanden
		$('#artistHeading').html("<a>" + master.artist.name + "</a>");

		$('#imageDiv').html(
				'<img src="' + master.imageURL
						+ '" width="210px" height="210px">')

		$('#genreSpan').html(
				'<span class="label label-primary labelMargin">'
						+ master.genre.name + '</span>');

		var subgenres = "";
		$.each(master.subgenres, function(pos, subgenre) {

			subgenres = subgenres
					+ '<span class="label label-success labelMargin">'
					+ subgenre.name + '</span> ';

		});

		$('#subgenreSpan').html(subgenres);

	}

}

function getVersion() {

	var versionId = urlParam("versionId");

	$
			.getJSON("api/releases/" + versionId + "/", function(release) {

				initPageRelease(release);

			})
			.fail(
					function(jqxhr, textStatus, error) {
						var errorMessage = "Beim laden der Version ist ein Fehler aufgetreten: "
							+ jqxhr.responseText;
						showErrorMsg(errorMessage, "Fehler");
					});

}

function initPageRelease(release) {

	if (release !== null & release !== undefined) {

		$('#labelSpan').html("<a>" + release.label.name + "</a>");
		$('#lcSpan').html(release.labelCode);
		$('#formatSpan').html(release.format.type);

		var releaseDate = release.releaseYear;

		if (release.releaseMonth !== 0) {
			releaseDate = getMonth(release.releaseMonth) + " "
					+ release.releaseYear;
		}

		if (release.releaseMonth !== 0 & release.releaseDay !== 0) {
			releaseDate = release.releaseDay + ". "
					+ getMonth(release.releaseMonth) + " "
					+ release.releaseYear;
		}

		$('#releaseSpan').html(releaseDate);

		createTracklistTable(release);

		$('#noteSpan').html(release.comment);
		$('#barcodeSpan').html(release.barcode);
		$('#catSpan').html(release.catalogNo);

	}

}

function createTracklistTable(release) {

	$('#tableDiv')
			.html(
					'<table id="table" class="display"><thead><tr><th>Nummer</th><th>Titel</th><th>Länge</th></tr></thead><tbody></tbody></table>');

	$('#table').DataTable({
		"bPaginate" : false,
		"bInfo" : false,
		"bFilter" : false,
		"oLanguage" : {

			"sEmptyTable" : "Keine Tracks vorhanden",
			"sInfoPostFix" : "",
			"sInfoThousands" : ".",
			"sLoadingRecords" : "Wird geladen...",
			"sProcessing" : "Bitte warten...",
			"sZeroRecords" : "Keine Einträge vorhanden.",

		},
		"oClasses" : {},
		"aoColumns" : [ {
			"sWidth" : "10%"
		}, {
			"sWidth" : "75"
		}, {
			"sWidth" : "15%"
		} ]

	});

	if (release.tracklist !== null) {

		$.each(release.tracklist, function(pos, track) {

			addRow(track);

		});

		$('#table').dataTable().fnDraw();

	}

}

function addRow(track) {
	var table = $('#table').dataTable();

	var number = check(track.number) ? track.number : "";
	var title = check(track.title) ? track.title : "";
	var duration = check(track.duration) ? track.duration : "";

	table.fnAddData([ number, title, duration ], false);

}

function check(value) {
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

function getMonth(intMonth) {

	var month = new Array();
	month[0] = "Januar";
	month[1] = "Februar";
	month[2] = "M&auml;z";
	month[3] = "April";
	month[4] = "Mai";
	month[5] = "Juni";
	month[6] = "Juli";
	month[7] = "August";
	month[8] = "September";
	month[9] = "Oktober";
	month[10] = "November";
	month[11] = "Dezember";
	var name = month[intMonth - 1];

	return name;
}

function showErrorMsg(message) {

	$("#errorDiv").html(
			'<div class="alert alert-danger alert-dismissible"'
					+ 'role="alert"><button type="button" class="close" '
					+ 'data-dismiss="alert" aria-label="Close"><span '
					+ 'aria-hidden="true">&times;</span></button>' + message
					+ '</div>');

}