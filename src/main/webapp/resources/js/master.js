/**
 * Initialisierungen nach Laden der Seite
 */
$(document)
		.ready(
				function() {

					var userId = $('#userId').val();

					if (check(userId)) {

						$('#anlegenTextSpan').html(
								"Ihre Version ist nicht dabei?");
						$('#anlegenButton').removeClass('hidden');
					} else {

						$('#anlegenTextSpan')
								.html(
										"Einloggen oder registrieren, um eigene Version anzulegen ");
						$('#loginButton').removeClass('hidden');
					}

					// Anlegen-Button => weitere Version anlegen
					$("#anlegenButton").button({}).click(function(e) {
						e.preventDefault();
						e.stopPropagation();

						versionAnlegen();

					});

					// Login-Button
					$("#loginButton").button({}).click(function(e) {
						e.preventDefault();
						e.stopPropagation();

						window.location = "../medienverwaltung/login";

					});

					// Master über dessen id holen + Informationen in die Seite
					// schreiben
					getMaster();

				});

function versionAnlegen() {
	var masterId = urlParam("masterId");

	window.location = "../medienverwaltung/anlegen_version?masterId="
			+ masterId;

}

function getMaster() {

	var masterId = urlParam("masterId");

	$
			.getJSON("api/master/" + masterId + "/", function(master) {

				initPage(master);

			})
			.fail(
					function(jqxhr, textStatus, error) {
						var errorMessage = "Beim laden des Masters ist ein Fehler aufgetreten: "
								+ jqxhr.responseText;
						showErrorMsg(errorMessage, "Fehler");
					});

}

function initPage(master) {

	if (check(master)) {

		$('#titleHeading').html(master.title);

		$('#artistHeading').html(
				"<a href='/medienverwaltung/artist?artistId="
						+ master.artist.artistId + "'>" + master.artist.name
						+ "</a>");

		if (check(master.imageURL)) {
			$('#imageDiv').html(
					'<img src="' + master.imageURL
							+ '" width="210px" height="210px">');
		} else {
			$('#imageDiv').html(
					'<img src="/medienverwaltung/resources/images/record.png"'
							+ 'width="210px" height="210px">');

		}

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

		var releaseDate = master.releaseYear;

		if (master.releaseMonth !== 0) {
			releaseDate = getMonth(master.releaseMonth) + " "
					+ master.releaseYear;
		}

		if (master.releaseMonth !== 0 & master.releaseDay !== 0) {
			releaseDate = master.releaseDay + ". "
					+ getMonth(master.releaseMonth) + " " + master.releaseYear;
		}

		$('#releaseSpan').html(releaseDate);

		createReleaseTable(master);

	}

}

function createReleaseTable(master) {

	$('#tableDiv')
			.html(
					'<table id="table" class="display" style="margin-top:30px;"><thead><tr><th></th><th>Titel</th><th>CAT#</th><th>Label</th><th>Format</th><th>Jahr</th></tr></thead><tbody></tbody></table>');

	$('#table')
			.DataTable(
					{
						"bPaginate" : false,
						"bInfo" : false,
						"bFilter" : false,
						"sPaginationType" : "full_numbers",
						"oLanguage" : {

							"sEmptyTable" : "Zu dieser Ver&ouml;ffentlichung sind noch keine Versionen vorhanden.",
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

	if (master.releases !== null & master.releases.length !== 0) {

		$.each(master.releases, function(pos, release) {

			addRow(release, master);

		});

		$('#table').dataTable().fnDraw();

	}

}

function addRow(release, master) {
	var table = $('#table').dataTable();

	var cover = check(master.imageURL) ? "<img src='" + master.imageURL
			+ "' width=85px height=85px>" : "";
	var title = check(master.title) ? "<a href='/medienverwaltung/version?versionId="
			+ release.releaseId
			+ "&masterId="
			+ master.masterId
			+ "'>"
			+ master.title + "</a>"
			: "";
	var cat = check(release.catalogNo) ? release.catalogNo : "";
	var format = check(release.format.type) ? release.format.type : "";
	var label = check(release.label.name) ? release.label.name : "";
	var jahr = check(release.releaseYear) ? release.releaseYear : "";

	table.fnAddData([ cover, title, cat, label, format, jahr ], false);

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