/**
 * Initialisierungen nach Laden der Seite
 */
$(document).ready(function() {

	fillTable();

});

function fillTable() {

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

	addRows();

}

function addRows() {
	var table = $('#table').dataTable();

	table.fnAddData([ "A01", "Mein Lied", "2:37" ], false);
	table.fnAddData([ "A02", "Dynamit", "3:24" ], false);
	table.fnAddData([ "A03", "Was die Welt jetzt braucht", "2:45" ], false);
	table.fnAddData([ "A04", "Herz?Verloren", "3:23" ], false);
	table.fnAddData([ "B05", "AWG", "2:42" ], false);
	table.fnAddData([ "B06", "Heute tanzen", "2:28" ], false);
	table.fnAddData([ "B07", "iDisco", "4:03" ], false);
	table.fnAddData([ "B08", "Find dich gut", "3:10" ], false);
	table.fnAddData([ "C09", "Keine Angst", "3:56" ], false);
	table.fnAddData([ "C10", "Fan", "3:40" ], false);
	table.fnAddData([ "C11", "Newton hatte Recht", "3:17" ], false);
	table.fnAddData([ "C12", "Das Traurigste", "5:01" ], false);
	table.fnAddData([ "D13", "3000", "2:45" ], false);
	table.fnAddData([ "D14", "Sommer", "4:22" ], false);
	table.fnAddData([ "D15", "Immer dabei", "4:08" ], false);

	table.fnDraw();
	table.columns.adjust().draw();

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