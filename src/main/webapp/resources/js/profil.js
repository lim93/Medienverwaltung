/**
 * Initialisierungen nach Laden der Seite
 */
$(document).ready(function () {





    fillTable();

});

releases;


function fillTable() {


    $('#tableDiv').html('<table id="table" class="display"><thead><tr><th></th><th>Titel</th><th>Artist</th><th>Format</th><th>Jahr</th></tr></thead><tbody></tbody></table>');

    $('#table')
        .DataTable({
            "bPaginate": true,
            "bInfo": true,
            "sPaginationType": "full_numbers",
            "aaSorting": [[1, "asc"]],
            "oLanguage": {

                "sEmptyTable": "Keine Daten in der Tabelle vorhanden",
                "sInfo": "_START_ bis _END_ von _TOTAL_",
                "sInfoEmpty": "0 bis 0 von 0",
                "sInfoFiltered": "(gefiltert von _MAX_ Einträgen)",
                "sInfoPostFix": "",
                "sInfoThousands": ".",
                "sLengthMenu": "Alben pro Seite: _MENU_ ",
                "sLoadingRecords": "Wird geladen...",
                "sProcessing": "Bitte warten...",
                "sSearch": "Sammlung durchsuchen:",
                "sZeroRecords": "Keine Einträge vorhanden.",
                "oPaginate": {
                    "sFirst": "<<",
                    "sLast": ">>",
                    "sNext": ">",
                    "sPrevious": "<"
                }

    		},
    		"oClasses" : {
    			"sFilterInput" : "form-control"
    		}

        });


    $
        .getJSON(
            "/medienverwaltung/resources/releases.json", function (data) {

                var table = $('#table').dataTable();
                table.fnClearTable();
                var searchFilter = $('#table_filter');
                searchFilter.addClass('searchFilterPadding');

                releases = data.releases;

                $.each(data.releases, function (pos, release) {
                    addRow(release);

                });

                table.fnDraw();



            })
        .fail(
            function (jqxhr, textStatus, error) {
                var errorMessage = "Bei der Abfrage ist ein Fehler aufgetreten: " + textStatus + ", " + error;
                alert(errorMessage);
            });
}

function addRow(release) {
    var table = $('#table').dataTable();

    var cover = check(release.cover) ? "<img src='/medienverwaltung/resources/" + release.cover + "' width=85px height=85px>" : "";
    var title = check(release.release) ? "<a href='/bla' target='_blank'>" + release.release + "</a>" : "";
    var artist = check(release.artist) ? "<a href='/bla' target='_blank'>" + release.artist + "</a>" : "";
    var format = check(release.format) ? release.format : "";
    var jahr = check(release.jahr) ? release.jahr : "";

    table.fnAddData([cover, title, artist, format, jahr], false);

}

function listenAnsicht(button) {

    $('#listButton').addClass("hide");
    $('#bigButton').removeClass("hide");

    fillTable();

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

    var cover = check(release.cover) ? "<img src='/medienverwaltung/resources/" + release.cover + "' width=210px height=210px>" : "";
    var title = check(release.release) ? "<a href='/bla' target='_blank'><b>" + release.release + "</b></a>" : "";
    var artist = check(release.artist) ? "<a href='/bla' target='_blank'>" + release.artist + "</a>" : "";
    var format = check(release.format) ? release.format : "";


    var releaseString = "<div class=medienObjekt>" + cover + "<div style='max-width:100%'>" + title + "<br>" + artist + "<p>Format: " + format + "</p></div></div>";

    return releaseString;


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