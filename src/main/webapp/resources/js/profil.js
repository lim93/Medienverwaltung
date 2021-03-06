/**
 * Initialisierungen nach Laden der Seite
 */
$(document)
		.ready(
				function() {

					var userId = $('#userId').val();

					if (!check(userId)) {

						window.location = "../medienverwaltung/login?error=1";

					} else {
						$('#content').removeClass("hidden");
						getUser(userId);
					}

					$("#removeModal")
							.on(
									'show.bs.modal',
									function(event) {

										var userId = $('#userId').val();

										// Get button that triggered the modal
										var button = $(event.relatedTarget)
										var versionId = button
												.data('versionid');

										var removeButton = '<button id="removeButton" data-versionId='
												+ versionId
												+ ' class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span>'
												+ ' <b>Aus Sammlung entfernen</b></button>';

										$('#removeButtonSpan').html(
												removeButton);

										// Aus der Sammlung entfernen
										$("#removeButton").button({}).click(
												function(e) {
													e.preventDefault();
													e.stopPropagation();

													var $this = $(this);
													versionId = $this
															.data('versionid');

													removeFromCollection(
															userId, versionId);
												});

									});

				});

var releases;

function getUser(userId) {

	$
			.getJSON("api/users/" + userId + "/", function(user) {

				initPage(user);

			})
			.fail(
					function(jqxhr, textStatus, error) {
						var errorMessage = "Beim laden des Profils ist ein Fehler aufgetreten: "
								+ jqxhr.responseText;
						showErrorMsg(errorMessage, "Fehler");
					});

}

function initPage(user) {

	if (check(user)) {

		$('#imageSpan').html(
				'<img src="/medienverwaltung/resources/images/user.png"'
						+ 'width="210px" height="210px">');

		$('#nameSpan').html("<h3>" + user.userName + "</h3>");
		$('#collectionNameSpan').html(user.userName);

		var genreString = "";
		$.each(user.userSubgenres, function(pos, subgenre) {
			genreString += '<span class="label label-primary labelMargin">'
					+ subgenre.name + '</span> ';
		})

		$('#genreSpan').html(genreString);

		var artistString = "";
		$.each(user.userArtists, function(pos, artist) {
			artistString += '<span class="label label-success labelMargin">'
					+ artist.name + '</span> ';
		})

		$('#favoritenSpan').html(artistString);

		$('#collectionSize').html(user.collection.length);

		releases = user.collection;

		createCollectionTable(releases);

	}

}

function createCollectionTable(releases) {

	$('#table')
			.DataTable(
					{
						"bPaginate" : true,
						"bInfo" : true,
						"sPaginationType" : "full_numbers",
						"aaSorting" : [ [ 1, "asc" ] ],
						"oLanguage" : {

							"sEmptyTable" : "Sie haben noch keine Ver&ouml;ffentlichungen in Ihrer Sammlung",
							"sInfo" : "_START_ bis _END_ von _TOTAL_",
							"sInfoEmpty" : "0 bis 0 von 0",
							"sInfoFiltered" : "(gefiltert von _MAX_ Einträgen)",
							"sInfoPostFix" : "",
							"sInfoThousands" : ".",
							"sLengthMenu" : "Alben pro Seite: _MENU_ ",
							"sLoadingRecords" : "Wird geladen...",
							"sProcessing" : "Bitte warten...",
							"sSearch" : "Sammlung durchsuchen:",
							"sZeroRecords" : "Keine Einträge vorhanden.",
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

	var table = $('#table').dataTable();
	table.fnClearTable();
	var searchFilter = $('#table_filter');
	searchFilter.addClass('searchFilterPadding');

	$.each(releases, function(pos, release) {
		addRow(release);

	});

	table.fnDraw();
	new $.fn.dataTable.Responsive(table, {
		details : false
	});

}

function addRow(release) {
	var table = $('#table').dataTable();

	var title = check(release.title) ? "<a href='/medienverwaltung/version?versionId="
			+ release.releaseId
			+ "&masterId="
			+ release.masterId
			+ "'>"
			+ release.title + "</a>"
			: "";

	var cover = check(release.imageURL) ? "<a href='/medienverwaltung/version?versionId="
			+ release.releaseId
			+ "&masterId="
			+ release.masterId
			+ "'><img src='"
			+ release.imageURL
			+ "' width=85px height=85px></a>"
			: "";

	var artist = check(release.artist) ? "<a href='/medienverwaltung/artist?artistId="
			+ release.artist.artistId + "'>" + release.artist.name + "</a>"
			: "";
	var format = check(release.format.type) ? release.format.type : "";
	var jahr = check(release.releaseYear) ? release.releaseYear : "";

	var removeButton = '<button class="btn btn-danger removeButton" data-versionId='
			+ release.releaseId
			+ ' data-toggle="modal" data-target="#removeModal">'
			+ '<span class="glyphicon glyphicon-trash">' + '</span></button>';

	table
			.fnAddData([ cover, title, artist, format, jahr, removeButton ],
					false);

}

function listenAnsicht(button) {

	location.reload();

}

function grosseAnsicht() {

	$('#bigButton').addClass("hide");
	$('#listButton').removeClass("hide");

	fillContent();
}

function fillContent() {

	var contentString = "<div>";

	for (i = 0; i < releases.length; i++) {
		var release = releases[i];
		contentString = contentString + addRelease(contentString, release);

	}

	contentString = contentString + "</div>";

	$("#tableDiv").empty();
	$('#tableDiv').html(contentString);

}

function addRelease(contentString, release) {

	var title = check(release.title) ? "<a href='/medienverwaltung/version?versionId="
			+ release.releaseId
			+ "&masterId="
			+ release.masterId
			+ "'>"
			+ release.title + "</a>"
			: "";

	var cover = check(release.imageURL) ? "<a href='/medienverwaltung/version?versionId="
			+ release.releaseId
			+ "&masterId="
			+ release.masterId
			+ "'><img src='"
			+ release.imageURL
			+ "' width=210px height=210px></a>"
			: "";
	var artist = check(release.artist) ? "<a href='/medienverwaltung/artist?artistId="
			+ release.artist.artistId + "'>" + release.artist.name + "</a>"
			: "";
	var format = check(release.format.type) ? release.format.type : "";

	var releaseString = "<div class=medienObjekt>" + cover
			+ "<div style='max-width:100%'>" + title + "<br>" + artist
			+ "<p>Format: " + format + "</p></div></div>";

	return releaseString;

}

function removeFromCollection(userId, versionId) {

	$.ajax({
		url : 'api/users/collection/',
		type : 'DELETE',
		data : JSON.stringify({
			"userId" : userId,
			"versionId" : versionId,

		}),
		contentType : "application/json; charset=utf-8",
		dataType : 'json'

	}).done(function() {
		$("#removeModal").modal('hide');
		location.reload();
	}).fail(
			function(jqxhr, textStatus, error) {
				var errorMessage = "Beim entfernen ist ein Fehler aufgetreten"
						+ jqxhr.responseText;
				showErrorMsg(errorMessage);
				return false;
			});

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