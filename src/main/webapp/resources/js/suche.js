$(document).ready(function() {

	// Anlegen-Button
	$("#anlegenButton").button({}).click(function(e) {
		e.preventDefault();
		e.stopPropagation();

		window.location = "../medienverwaltung/anlegen";

	});

	doSearch();

});

function doSearch() {

	$('#releaseTable').DataTable({
		"bPaginate" : true,
		"bInfo" : true,
		"sPaginationType" : "full_numbers",
		"aaSorting" : [ [ 1, "asc" ] ],
		"oLanguage" : {

			"sEmptyTable" : "Keine Veröffentlichungen gefunden.",
			"sInfo" : "_START_ bis _END_ von _TOTAL_",
			"sInfoEmpty" : "0 bis 0 von 0",
			"sInfoFiltered" : "(gefiltert von _MAX_ Einträgen)",
			"sInfoPostFix" : "",
			"sInfoThousands" : ".",
			"sLengthMenu" : "Treffer pro Seite: _MENU_ ",
			"sLoadingRecords" : "Wird geladen...",
			"sProcessing" : "Bitte warten...",
			"sSearch" : "Treffer durchsuchen:",
			"sZeroRecords" : "Keine Veröffentlichungen gefunden.",
			"oPaginate" : {
				"sFirst" : "<<",
				"sLast" : ">>",
				"sNext" : ">",
				"sPrevious" : "<"
			}

		},
		"oClasses" : {
			"sFilterInput" : "form-control"
		}

	});

	$.getJSON("/medienverwaltung/resources/releases.json", function(data) {

		var table = $('#releaseTable').dataTable();
		table.fnClearTable();
		var searchFilter = $('#releaseTable_filter');
		searchFilter.addClass('searchFilterPadding');

		releases = data.releases;

		$.each(data.releases, function(pos, release) {
			addRow(release);

		});

		table.fnDraw();

	}).fail(
			function(jqxhr, textStatus, error) {
				var errorMessage = "Bei der Suche ist ein Fehler aufgetreten: "
						+ textStatus + ", " + error;
				alert(errorMessage);
			});
}

function addRow(release) {
	var table = $('#releaseTable').dataTable();

	var cover = check(release.cover) ? "<img src='/medienverwaltung/resources/"
			+ release.cover + "' width=100px height=100px>" : "";
	var title = check(release.release) ? "<a href='/bla' target='_blank'>"
			+ release.release + "</a>" : "";
	var artist = check(release.artist) ? "<a href='/bla' target='_blank'>"
			+ release.artist + "</a>" : "";
	var jahr = check(release.jahr) ? release.jahr : "";
	var genre = check(release.genre) ? release.genre : "";

	table.fnAddData([ cover, title, artist, jahr, genre ], false);

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