$(document)
		.ready(
				function() {

					var userId = $('#userId').val();

					if (check(userId)) {

						$('#anlegenTextSpan').html(
								"Gesuchte Ver&ouml;ffentlichung nicht dabei?");
						$('#anlegenButton').removeClass('hidden');
					} else {

						$('#anlegenTextSpan')
								.html(
										"Einloggen oder registrieren, um eine Ver&ouml;ffentlichung anzulegen ");
						$('#loginButton').removeClass('hidden');
					}

					// Anlegen-Button
					$("#anlegenButton").button({}).click(function(e) {
						e.preventDefault();
						e.stopPropagation();

						window.location = "../medienverwaltung/anlegen_master";

					});

					// Login-Button
					$("#loginButton").button({}).click(function(e) {
						e.preventDefault();
						e.stopPropagation();

						window.location = "../medienverwaltung/login";

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

	$.getJSON("/medienverwaltung/api/search/?suche=" + urlParam("suche"),
			function(masters) {

				var table = $('#releaseTable').dataTable();
				table.fnClearTable();
				var searchFilter = $('#releaseTable_filter');
				searchFilter.addClass('searchFilterPadding');

				$.each(masters, function(pos, master) {
					addRow(master);

				});

				table.fnDraw();

			}).fail(
			function(jqxhr, textStatus, error) {
				var errorMessage = "Bei der Suche ist ein Fehler aufgetreten: "
						+ jqxhr.responseText;
				showErrorMsg(errorMessage);
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