/**
 * Initialisierungen nach Laden der Seite
 */
$(document).ready(function() {

	// Anlegen-Button => weitere Version anlegen
	$("#anlegenButton").button({}).click(function(e) {
		e.preventDefault();
		e.stopPropagation();
		
		versionAnlegen();

	});

	fillTable();

});

releases;

function versionAnlegen() {
	var masterId = urlParam("masterId");

	window.location = "../medienverwaltung/anlegen_version?masterId="
			+ masterId;

}

function fillTable() {

	$('#tableDiv')
			.html(
					'<table id="table" class="display" style="margin-top:30px;"><thead><tr><th></th><th>Titel</th><th>Label</th><th>Format</th><th>Jahr</th></tr></thead><tbody></tbody></table>');

	$('#table').DataTable({
		"bPaginate" : false,
		"bInfo" : false,
		"bFilter" : false,
		"sPaginationType" : "full_numbers",
		"oLanguage" : {

			"sEmptyTable" : "Keine Versionen vorhanden",
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

	$
			.getJSON("/medienverwaltung/resources/releases.json",
					function(data) {

						var table = $('#table').dataTable();
						table.fnClearTable();

						releases = data.releases;

						$.each(data.releases, function(pos, release) {

							if (release.release === "Faszination Weltraum") {
								addRow(release);
							}

						});

						table.fnDraw();

					})
			.fail(
					function(jqxhr, textStatus, error) {
						var errorMessage = "Bei der Abfrage ist ein Fehler aufgetreten: "
								+ textStatus + ", " + error;
						alert(errorMessage);
					});
}

function addRow(release) {
	var table = $('#table').dataTable();

	var cover = check(release.cover) ? "<img src='/medienverwaltung/resources/"
			+ release.cover + "' width=85px height=85px>" : "";
	var title = check(release.release) ? "<a href='/bla' target='_blank'>"
			+ release.release + "</a>" : "";
	var format = check(release.format) ? release.format : "";
	var label = check(release.label) ? release.label : "";
	var jahr = check(release.jahr) ? release.jahr : "";

	table.fnAddData([ cover, title, label, format, jahr ], false);

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