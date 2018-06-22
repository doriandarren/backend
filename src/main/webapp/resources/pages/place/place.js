var url_api = "https://m-apii.appspot.com/_ah/api/";

function getAllClient(){
	   var parametros = {
           "get_all" : "-"
       };
	   $.ajax({
           url:   url_api+'apiclient/v1/find_all',
           type:  'get',
           beforeSend: function () {
        	   //document.getElementById("res_all_client").innerHTML = "Proccesing, waiting...";
           },
           success:  function (response) {  
        	   document.getElementById("form:cliente_id").innerHTML = ''; 
        	   for(i=0; i<response.items.length; i++){	
        		   var z = document.createElement("option");
	        	   z.setAttribute("value", response.items[i].id);
	        	   var t = document.createTextNode(response.items[i].name);
	        	   z.appendChild(t);
	        	   document.getElementById("form:cliente_id").appendChild(z);
        	   }
           },
           error : function(e) {
               alert('error'+e);
           }
   		});
   	}

function getAll(){	
	
	   var parametros = {
           "get_all" : "-"
       };
	   $.ajax({
           url:   url_api+'apiplace/v1/find_all',
           type:  'get',
           beforeSend: function () {
        	   document.getElementById("res_all").innerHTML = "Proccesing, waiting...";
           },
           success:  function (response) { 
        	   
        	   console.log(Object.values(response));
        	   
               res = "<table class='table' id='mytable'>";				
               	res+="<thead>";
            	res += "<tr>";
            	res += "<td>Client</td>";
           		res += "<td>Name</td>";
           		res += "<td>Address</td>";
           		res += "<td>Description</td>";
           		res += "<td>Phone</td>";
           		res += "<td>Email</td>";
           		res += "<td>Zip</td>";
           		res += "<td>City</td>";           		
           		res += "<td>Actions</td>";
           		res += "</tr>";
           		res += "</thead>";
           		res += "<tbody>";
           		
               for(i=0; i<response.items.length; i++){
            	               	   
            	   res += "<tr>";
            	   res += "<td bgcolor=\"#D5E0EA\">"+response.items[i].client.name+"</td>";
            	   res += "<td>"+response.items[i].name+"</td>";
            	   res += "<td>"+response.items[i].address+"</td>";
            	   res += "<td>"+response.items[i].description+"</td>";
            	   res += "<td>"+response.items[i].phone+"</td>";
            	   res += "<td>"+response.items[i].email+"</td>";
            	   res += "<td>"+response.items[i].codeZip+"</td>";
            	   res += "<td>"+response.items[i].city+"</td>";            	   
            	   res += "<td><a type='button' href='javascript:;' onclick='find(\""+response.items[i].id+"\");return false;'>Update</a>";
            	   res += "<a type='button' href='javascript:;' onclick='del(\""+response.items[i].id+"\");return false;'>Delete</a></td>";
            	   res+="</tr>";	
               }
               
               
               res += "</tbody>";
               res += "</table>";
               
               
               $("#res_all").html(res);
               
           },
           error : function(e) {
        	   console.log(Object.values(e));
               alert('error'+e);
               $("#res_all").html("");
           }
   		});
   	}
   
     
   
	function save(){		
	    var parametros = {
	            "name" : document.getElementById("form:name").value,
	            "address" : document.getElementById("form:address").value,        
	            "description" : document.getElementById("form:description").value,
	            "phone" : document.getElementById("form:phone").value,
	            "email" : document.getElementById("form:email").value,
	            "codeZip" : document.getElementById("form:code_zip").value,
	            "city" : document.getElementById("form:city").value,
	            "clientId": document.getElementById("form:cliente_id").value
	    };
	    	    
	    $.ajax({
	            data:  parametros,
	            url:   url_api+'apiplace/v1/save',
	            type:  'post',
	            beforeSend: function () {
	            	document.getElementById("result").innerHTML = "Proccesing, waiting...";
	            },
	            success:  function (response) {	                	
	            	$("#result").addClass("alert alert-success").html(response.message);
	                document.getElementById("form:id").value="";
	                document.getElementById("form:name").value="";
	                document.getElementById("form:address").value="";
	                document.getElementById("form:description").value="";
	                document.getElementById("form:phone").value="";
	                document.getElementById("form:email").value="";
	                document.getElementById("form:code_zip").value="";
	                document.getElementById("form:city").value="";	                
	                getAll();
	           },
	           error : function(e) {
	               alert('error'+e);
	           }
	    });
	}
	
	
	
	function find(id){
		$.ajax({
            //data:  client_id_id,
            url:   url_api+'apiplace/v1/find?id='+id,
            type:  'get',
            beforeSend: function () {
            	document.getElementById("result").innerHTML = "Proccesing, waiting...";
            },
            success:  function (response) {             	
            	document.getElementById("result").className = '';
            	document.getElementById("result").innerHTML = "";
            	document.getElementById("form:id").value=response.id;
                document.getElementById("form:name").value=response.name;                
                document.getElementById("form:address").value=response.address;                
                document.getElementById("form:description").value=response.description;
                document.getElementById("form:phone").value=response.phone;
                document.getElementById("form:email").value=response.email;
                document.getElementById("form:code_zip").value=response.codeZip;
                document.getElementById("form:city").value=response.city;                
           },
           error : function(e) {
               alert('error'+e);
           }
    	});
	}
	
	
	function update(){
		
		var data = {
			"id" : document.getElementById("form:id").value,	
			"name" : document.getElementById("form:name").value,
	        "address" : document.getElementById("form:address").value,        
	        "description" : document.getElementById("form:description").value,
	        "phone" : document.getElementById("form:phone").value,
	        "email" : document.getElementById("form:email").value,
	        "codeZip" : document.getElementById("form:code_zip").value,
	        "city" : document.getElementById("form:city").value		    
		};
				
		$.ajax({
            data:  data,
            url:   url_api+'apiplace/v1/update',
            type:  'POST',
            beforeSend: function () {
            	document.getElementById("result").innerHTML = "Proccesing, waiting...";
            },
            success:  function (response) {	
                $("#result").addClass("alert alert-success").html(response.message);
                
                document.getElementById("form:id").value="";
                document.getElementById("form:name").value="";        
                document.getElementById("form:address").value="";               
                document.getElementById("form:description").value="";
                document.getElementById("form:phone").value="";
                document.getElementById("form:email").value="";
                document.getElementById("form:code_zip").value="";
                document.getElementById("form:city").value="";
                getAll();
           },
           error : function(e) {
               alert('error'+e);
               console.log(Object.values(e));
           }
    	});
	}
	
	
	function del(id){
		var conf = confirm('Desea Eliminar el registro?');
		if(conf==true){
			$.ajax({
	            url:   url_api+'apiplace/v1/delete?id='+id,
	            type:  'get',
	            beforeSend: function () {
	            	document.getElementById("result").innerHTML = "Proccesing, waiting...";
	            },
	            success:  function (response) { 
	            	$("#result").addClass("alert alert-success").html(response.message);
	            	getAll();
	           },
	           error : function(e) {
	               alert('error'+e);
	           }
	    	});	
		}
	}
