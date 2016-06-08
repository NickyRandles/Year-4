$(document).ready(function(){
	var path = 'http://localhost:8080/adverts_rest/webapi/adverts';
	$.ajax({
		type: 'GET',
		url: path,
		dataType: 'json',
		success: function(data){
			$.each(data, function(index, item){
				var id = '', name = '';
				$.each(item, function(key, value){
					if(key == 'id'){id = value;}
					if(key == 'name'){name = value;}
				});
				$('#content').append('<div id="searchResult">');
				$('#content').append('<a href="http://localhost:8080/adverts_rest/webapi/adverts/' + id + '"><h2>' + name + '</h2');
				$('#content').append('<button onclick="remove(' + id + ')">Delete</button>');
				$('#content').append('<div id="update">');
				$('#content').append('<input type="text" name="name" id="name" placeholder="Enter name"><br><br>');
				$('#content').append('<input type="text" name="description" id="description" placeholder="Enter description"><br><br>');
				$('#content').append('<button onclick="update(' + id + ')">Update</button>');
				$('#content').append('</div>');	
				$('#content').append('</div>');	
			});
		}
	});
	$("#update").hide();
});

function remove(id){
	var path = 'http://localhost:8080/adverts_rest/webapi/adverts/' + id;
	$.ajax({
		type: 'DELETE',
		url: path,
		dataType: 'json',
		success: function(){
			location.reload();
		} 
	});
	console.log(path);
}

function update(id){
	var name = $("#name").val();
	var description = $("#description").val();
	var JSONObject = {
            name:name,
            description:description,
            author:"Nicky"
            };
	console.log(JSON.stringify(JSONObject));
	
	var path = 'http://localhost:8080/adverts_rest/webapi/adverts/' + id;
	$.ajax({
		type: 'PUT',
		url: path,
		contentType: 'application/json',
		dataType: 'json',
		data: JSON.stringify(JSONObject),
		success: function(){
			location.reload();
		}
	});
}

function searchRequest(){
	$('#content').html('');
	var searchQuery = $('#searchQuery').val();
	var path = 'http://localhost:8080/adverts_rest/webapi/adverts';
	$.ajax({
		type: 'GET',
		url: path,
		dataType: 'json',
		success: function(data){
			$.each(data, function(index, item){
				var name = '';
				$.each(item, function(key, value){
					if(key == 'name'){name = value;}			
				});
				$('#content').append('<div id="searchResults">');
				if(name == searchQuery){$('#content').append('<h2>' + name + '</h2');}			
				$('#content').append('</div>');			
			});
		} 
	});
	console.log(path);
}

