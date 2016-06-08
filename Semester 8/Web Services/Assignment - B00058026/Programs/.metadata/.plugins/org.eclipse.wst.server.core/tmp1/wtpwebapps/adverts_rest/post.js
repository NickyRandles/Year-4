function post(){
	var name = $("#name").val();
	var description = $("#description").val();
	var JSONObject = {
            name:name,
            description:description,
            author:"Nicky"
            };
	console.log(JSON.stringify(JSONObject));
	
	var path = 'http://localhost:8080/adverts_rest/webapi/adverts';
	$.ajax({
		type: 'POST',
		url: path,
		contentType: 'application/json',
		dataType: 'json',
		data: JSON.stringify(JSONObject),
		success: function(data){
			$.each(data, function(index, item){
				$.each(item, function(key, value){
					$('#content').append('<div id="searchResult">');
					$('#content').append(key + ': ' + value);			
					$('#content').append('</div>');	
				});			
			});
		}
	});

}