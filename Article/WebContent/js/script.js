// READ articles
function readArticles() {

	$.ajax({
		url : 'api/articles',
		type : 'GET',
		success : function(data, status) {
			var template = $("#article_mustache_template").html();
			var html = "";
			for (var i = 0; i < data.length; i++) {
				html += Mustache.to_html(template, data[i]);
			}
			$('#articles_content').html(html);
		}
	});
}

// Add Article
function addArticle() {
	// get values
	var title = $("#add_new_article_modal #input_title").val();
	var body = $("#add_new_article_modal #input_body").val();

	var article = {
		"title" : title,
		"body" : body,
	};

	// Add Article
	$.ajax({
		url : 'api/articles/',
		type : 'POST',
		data : JSON.stringify(article),
		contentType : 'application/json',
		success : function(data, status) {
			// close the popup
			$("#add_new_article_modal").modal("hide");

			// read articles again
			readArticles();

			// clear fields from the popup
			$("#add_new_article_modal #input_title").val("");
			$("#add_new_article_modal #input_body").val("");
		}
	});

}

function DeleteArticle(id) {
	var conf = confirm("Are you sure, do you really want to delete this article ?");

	if (conf == true) {
		$.ajax({
			url : 'api/articles/' + id,
			type : 'DELETE',
			success : function(result) {
				readArticles();
			}
		});
	}
}

function GetArticleDetails(id) {
	$("#update_article_modal #hidden_article_id").val(id);
	$.get("api/articles/" + id, {}, function(data, status) {
		var article = data;
		// Assing existing values to the modal popup fields
		$("#update_article_modal #input_title").val(article.title);
		$("#update_article_modal #input_body").val(article.body);
	});
	// Open modal popup
	$("#update_article_modal").modal("show");
}

function UpdateArticleDetails() {
	// get values
	var title = $("#update_article_modal #input_title").val();
	var body = $("#update_article_modal #input_body").val();

	// get hidden field value
	var id = $("#update_article_modal #hidden_article_id").val();

	// Update the details by requesting to the server using ajax
	var article = {
		id:id,
		title : title,
		body : body,
	};
	$.ajax({
		url : 'api/articles',
		type : 'PUT',
		data : JSON.stringify(article),
		contentType : 'application/json',
		success : function(data, status) {
			$("#update_article_modal").modal("hide");
			// reload Articles
			readArticles();
		}
	});
}

$(document).ready(function() {
	// READ articles on page load
	readArticles();
});