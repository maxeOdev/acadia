
$(document).ready(function() {
	listCategories();
});

$(document).on("click", "#link-list-categories", function() {
	listCategories();	
});

$(document).on("click", "#link-create-category", function() {
	createCategoryTemplate();	
});

/**
 * Categories on dashboard.
 */

function listCategories() {

	$('#content').load('/admin/list-categories-template', function() {
		$.ajax({
			url: "/admin/every-categories",
			type: "get",
			data: {},
			success: function(categories) {
				let fragment = handleBarsGenerateCategories(categories);
				$('#content').html(fragment);
			}
		});
	});

}

function handleBarsGenerateCategories(categories) {
	let source = $('#handlebars-categories-template').html();
	let template = Handlebars.compile(source);

	return template({ categories: categories });
}

/**
 * Category update.
 */

function categoryDetails(categoryName) {

	$('#content').load('/admin/category-details-template', function () {
		$.ajax({
			url: "/admin/by-category-name",
			type: "get",
			data: { name: categoryName },
			success: function (category) {
				let data = handleBarsGenerateCategoryDetails(category);
				$('#content').html(data);
			}
		});
	});
}

function handleBarsGenerateCategoryDetails(category) {
	let source = $('#handlebars-category-details-template').html();
	let template = Handlebars.compile(source);

	return template(category);
}

function updateCategory() {
	
	$("#form-update").submit( function(e) {
		
		e.preventDefault();

		$.ajax({
			url: "/admin/update-category",
			type: "put",
			data: $(this).serialize(),
			success: function () {
				listCategories();
			}
		});
	});
}

/**
 * Category create.
 */

function createCategoryTemplate() {
	$('#content').load('/admin/create-category-template', );
}


function createCategory() {
	
	$("#form-create").submit( function(e) {
		
		e.preventDefault();

		$.ajax({
			url: "/admin/create-category",
			type: "post",
			data: $(this).serialize(),
			success: function () {
				listCategories();
			}
		});
	});
}

/**
 * Category delete.
 */

 
function deleteCategory() {
	
	$("#form-update").submit( function(e) {
		
		e.preventDefault();

		$.ajax({
			url: "/admin/delete-category",
			type: "delete",
			data: $(this).serialize(),
			success: function () {
				listCategories();
			}
		});
	});
}
