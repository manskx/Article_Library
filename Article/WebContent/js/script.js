// READ articles
function readArticles() {
	
	 $.ajax({
         url: 'api/articles',
         type: 'GET',
         success: function(data, status) {
             var template =$("#article_mustache_template").html();
             var html = "";
             for(var i = 0; i < data.length; i++){
                    html += Mustache.to_html(template, data[i]);
             }
             $('#articles_content').html(html);
           }
     });
	 

}

$(document).ready(function () {
    // READ articles on page load
	readArticles();
});