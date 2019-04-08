$(document).ready(function() {
	listTrainings();
});

let isLoading = false;

$(document).on("click", "#link-list-trainings", function() {
	listTrainings();
});

$(document).on("click", "#link-create-training", function() {
	createTrainingTemplate();	
});

/**
 * Trainings on dashboard.
 */

function listTrainings() {

	$('#content').load('/admin/list-trainings-template', function() {
		$.ajax({
			url: "/admin/all-trainings",
			type: "get",
			data: {},
			success: function(trainings) {
				let fragment = handleBarsGenerateTrainings(trainings);
				$('#content').html(fragment);
			}
		});
	});

}

function handleBarsGenerateTrainings(trainings) {
	let source = $('#handlebars-trainings-template').html();
	let template = Handlebars.compile(source);

	return template({ trainings: trainings });
}

/**
 * Training update.
 */

function trainingDetails(uuid) {

	$('#content').load('/admin/training-details-template', function () {
		$.ajax({
			url: "/admin/training-by-id",
			type: "get",
			data: { uuid: uuid },
			success: function (training) {
				let data = handleBarsGenerateTrainingDetails(training);
				$('#content').html(data);
			}
		});
	});
}

function handleBarsGenerateTrainingDetails(training) {
	let source = $('#handlebars-training-details-template').html();
	let template = Handlebars.compile(source);

	return template(training);
}

function updateTraining() {
	
	$("#form-update").submit( function(e) {
		
		e.preventDefault();

		$.ajax({
			url: "/admin/update-training",
			type: "put",
			data: $(this).serialize(),
			success: function () {
				listTrainings();
			},
			error: function (jqxhr, textStatus, errorThrown) {
				let errors = JSON.parse(jqxhr.responseText);
				for (let e of errors) {
					$('#input-error-'+e.field).html(e.defaultMessage);
				}
			}
		});
	});
}

/**
 * Training create.
 */

function createTrainingTemplate() {
	$('#content').load('/admin/create-training-template', );
}


function createTraining() {
	
	$("#form-create").submit( function(e) {
		
		e.preventDefault();

		$.ajax({
			url: "/admin/create-training",
			enctype: "multipart/form-data",
			type: "post",
			data: $(this).serialize(),
			success: function () {
				listTrainings();
			},
			error: function (jqxhr, textStatus, errorThrown) {
//				let errors = JSON.parse(jqxhr.responseText);
//				$('#input-error-already-exists').html(errors[0].defaultMessage);
			}
		});
	});
}

/**
 * Training delete.
 */

function deleteTraining() {
	
	$("#form-update").submit( function(e) {
		
		e.preventDefault();

		$.ajax({
			url: "/admin/delete-training",
			type: "delete",
			data: { uuid: training.uuid },
			success: function () {
				listTrainings();
			},
			error: function (jqxhr, textStatus, errorThrown) {
//				let errors = JSON.parse(jqxhr.responseText);
//				$('#input-error-already-exists').html(errors[0].defaultMessage);
			}
		});
	});
}
