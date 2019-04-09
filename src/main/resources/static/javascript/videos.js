$(document).ready(function() {
	listVideos();
});

let isLoading = false;

$(document).on("click", "#link-list-videos", function() {
	listVideos();
});

$(document).on("click", "#link-create-video", function() {
	createVideoTemplate();	
});

let $fileUpload = $('#form-create').fileupload({
        url: '/admin/create-video',
        sequentialUploads: true,
        multipart: true,
        autoUpload: true
    });

/**
 * Videos on dashboard.
 */

function listVideos() {

	$('#content').load('/admin/list-videos-template', function() {
		$.ajax({
			url: "/admin/all-videos",
			type: "get",
			data: {},
			success: function(videos) {
				let fragment = handleBarsGenerateVideos(videos);
				$('#content').html(fragment);
			}
		});
	});

}

function handleBarsGenerateVideos(videos) {
	let source = $('#handlebars-videos-template').html();
	let template = Handlebars.compile(source);

	return template({ videos: videos });
}

/**
 * Video update.
 */

function videoDetails(uuid) {

	$('#content').load('/admin/video-details-template', function () {
		$.ajax({
			url: "/admin/video-by-id",
			type: "get",
			data: { uuid: uuid },
			success: function (video) {
				let data = handleBarsGenerateVideoDetails(video);
				$('#content').html(data);
			}
		});
	});
}

function handleBarsGenerateVideoDetails(video) {
	let source = $('#handlebars-video-details-template').html();
	let template = Handlebars.compile(source);

	return template(video);
}

function updateVideo() {
	
	$("#form-update").submit( function(e) {
		
		e.preventDefault();

		$.ajax({
			url: "/admin/update-video",
			type: "put",
			data: $(this).serialize(),
			success: function () {
				listVideos();
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
 * Video create.
 */

function createVideoTemplate() {
	$('#content').load('/admin/create-video-template', );
}


//function createVideo() {
//
//	$("#form-create").submit( function(e) {
//
//		e.preventDefault();
//
//		$.ajax({
//			url: "/admin/create-video",
//			enctype: "multipart/form-data",
//			type: "post",
//			data: $(this).serialize(),
//			success: function () {
//				listVideos();
//			},
//			error: function (jqxhr, textStatus, errorThrown) {
//				let errors = JSON.parse(jqxhr.responseText);
//				$('#input-error-already-exists').html(errors[0].defaultMessage);
//			}
//		});
//	});
//}

/**
 * Video create dynamic.
 */
//$(document).on('submit','#form-create', function(e){
//    e.preventDefault();
//    console.log(document.getElementById('form-create'));
//    console.log('test serialize',$(this).serialize());
//    	$.ajax({
//    			url: "/admin/create-video",
//    			enctype: 'multipart/form-data',
//                processData: false,  // Important!
//                contentType: false,
//                cache: false,
//    			type: "post",
//    			data: new FormData(document.getElementById('form-create')),
//    			success: function () {
//    				listVideos();
//    			},
//    			error: function () {
//    				listVideos();
//    				console.log('erreur de la requete Ajax');
//    			}
//    		});
//})

/**
 * Video delete.
 */

function deleteVideo() {
	
	$("#form-update").submit( function(e) {
		
		e.preventDefault();

		$.ajax({
			url: "/admin/delete-video",
			type: "delete",
			data: { uuid: video.uuid },
			success: function () {
				listVideos();
			},
			error: function (jqxhr, textStatus, errorThrown) {
//				let errors = JSON.parse(jqxhr.responseText);
//				$('#input-error-already-exists').html(errors[0].defaultMessage);
			}
		});
	});
}
