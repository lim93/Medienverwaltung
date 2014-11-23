$(document).ready(function() {
	
	var subSelect = $('#rockSelect')[0];
	initSub(subSelect, "rock");
	
	var subSelect = $('#metalSelect')[0];
	initSub(subSelect, "metal");
	
	var subSelect = $('#bullshitSelect')[0];
	initSub(subSelect, "bullshit");

	$("#rock").change(function() {
		hideSelects();
		$('#rockDiv').removeClass('hide');
	});

	$("#metal").change(function() {
		hideSelects();
		$('#metalDiv').removeClass('hide');

	});

	$("#bullshit").change(function() {
		hideSelects();
		$('#bullshitDiv').removeClass('hide');

	});

});

function initSub(subSelect, genre) {
	
	
	if(genre == "rock"){
		subSelect.add(new Option("Punk", 1));
		subSelect.add(new Option("Alternative", 2));
		subSelect.add(new Option("Ska", 3));
	}
	
	if(genre == "metal"){
		subSelect.add(new Option("Nu Metal", 1));
		subSelect.add(new Option("Alternative Metal", 2));
		subSelect.add(new Option("Thrash Metal", 3));
	}
	
	if(genre == "bullshit"){
		subSelect.add(new Option("Pop", 1));
		subSelect.add(new Option("Techno", 2));
		subSelect.add(new Option("Dubstep", 3));
	}
	

	


	$('.selectpicker').selectpicker('refresh');


}

function hideSelects(){
	
	$('#rockDiv').addClass('hide');
	$('#metalDiv').addClass('hide');
	$('#bullshitDiv').addClass('hide');
	
}
