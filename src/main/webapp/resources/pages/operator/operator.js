var url_api = "https://m-apii.appspot.com/_ah/api/";

function getAll(){
	   var parametros = {
           "get_all" : "-"
       };
	   $.ajax({
           url:   url_api+'apioperator/v1/find_all',
           type:  'get',
           contentType: "application/json; charset=utf-8",
           beforeSend: function () {
        	   document.getElementById("res_all").innerHTML = "Proccesing, waiting...";
           },
           success:  function (response) { 
        	   
               res = "<table class='table' id='mytable'>";				
               	res+="<thead>";
            	res += "<tr>";
           		res += "<td>Name</td>";
           		res += "<td>nif</td>";
           		res += "<td>email</td>";
           		res += "<td>phone</td>";
           		res += "<td>city</td>";
           		res += "<td>activity</td>";
           		res += "<td>accountBank</td>";
           		res += "<td>observation</td>";
           		res += "<td>Actions</td>";
           		res += "</tr>";
           		res += "</thead>";
           		res += "<tbody>";
           		
               for(i=0; i<response.items.length; i++){            	               	   
            	   res += "<tr>";
            	   res += "<td>"+response.items[i].name+"</td>";
            	   res += "<td>"+response.items[i].nif+"</td>";
            	   res += "<td>"+response.items[i].email+"</td>";
            	   res += "<td>"+response.items[i].phone+"</td>";
            	   res += "<td>"+response.items[i].city+"</td>";
            	   res += "<td>"+response.items[i].activity+"</td>";
            	   res += "<td>"+response.items[i].accountBank+"</td>";
            	   res += "<td>"+response.items[i].observation+"</td>";
            	   res += "<td><a type='button' href='javascript:;' onclick='find(\""+response.items[i].id+"\");return false;'>Update</a>";
            	   res += "<a type='button' href='javascript:;' onclick='del(\""+response.items[i].id+"\");return false;'>Delete</a></td>";
            	   res+="</tr>";	
               }
               
               
               res += "</tbody>";
               res += "</table>";
               
               
               $("#res_all").html(res);
               
           },
           error : function(e) {
               alert('error'+e);
               $("#res_all").html("");
           }
   		});
   	}
	
	
	function save(){		
	    var parametros = {
	    		name: document.getElementById("form:name").value,
			    nif: document.getElementById("form:nif").value,
			    email: document.getElementById("form:email").value,
			    phone: document.getElementById("form:phone").value,
			    city: document.getElementById("form:city").value,
			    activity: document.getElementById("form:activity").value,
			    accountBank: document.getElementById("form:accountBank").value,
			    observation: document.getElementById("form:observation").value
	    };
	    	    
	    $.ajax({
	            data:  parametros,
	            url:   url_api+'apioperator/v1/save',
	            type:  'post',
	            beforeSend: function () {
	            	document.getElementById("result").innerHTML = "Proccesing, waiting...";
	            },
	            success:  function (response) {	                	
	            	$("#result").addClass("alert alert-success").html(response.message);
	                document.getElementById("form:name").value="";
	                document.getElementById("form:nif").value="";
	                document.getElementById("form:email").value="";
	                document.getElementById("form:phone").value="";
	                document.getElementById("form:city").value="";
	                document.getElementById("form:activity").value="";
	                document.getElementById("form:accountBank").value="";
	                document.getElementById("form:observation").value="";
	                getAll();
	           },
	           error : function(e) {
	               alert('error: '+e);
	           }
	    });
	}
	
	
	
	function find(id){
		$.ajax({
            //data:  client_id_id,
            url:   url_api+'apioperator/v1/find?id='+id,
            type:  'get',
            beforeSend: function () {
            	document.getElementById("result").innerHTML = "Proccesing, waiting...";
            },
            success:  function (response) {             	
            	document.getElementById("result").className = '';
            	document.getElementById("result").innerHTML = "";
            	document.getElementById("form:id").value=response.id;
            	document.getElementById("form:name").value=response.name;
                document.getElementById("form:nif").value=response.nif;
                document.getElementById("form:email").value=response.email;
                document.getElementById("form:phone").value=response.phone;
                document.getElementById("form:city").value=response.city;
                document.getElementById("form:activity").value=response.activity;
                document.getElementById("form:accountBank").value=response.accountBank;
                document.getElementById("form:observation").value=response.observation;
           },
           error : function(e) {
               alert('error'+e);
           }
    	});
	}
	
	
	function update(){
		
		var n = Number(document.getElementById("form:id").value);
		
		var data = {		    
		    id: n,
		    name: document.getElementById("form:name").value,
		    nif: document.getElementById("form:nif").value,
		    email: document.getElementById("form:email").value,
		    phone: document.getElementById("form:phone").value,
		    city: document.getElementById("form:city").value,
		    activity: document.getElementById("form:activity").value,
		    accountBank: document.getElementById("form:accountBank").value,
		    observation: document.getElementById("form:observation").value
		};
		
		console.log(Object.values(data));
		$.ajax({
            data:  data,
            url:   url_api+'apioperator/v1/update',
            type:  'POST',
            beforeSend: function () {
            	document.getElementById("result").innerHTML = "Proccesing, waiting...";
            },
            success:  function (response) {	
                $("#result").addClass("alert alert-success").html(response.message);
                
                document.getElementById("form:id").value="";
                document.getElementById("form:name").value="";
                document.getElementById("form:nif").value="";
                document.getElementById("form:email").value="";
                document.getElementById("form:phone").value="";
                document.getElementById("form:city").value="";
                document.getElementById("form:activity").value="";
                document.getElementById("form:accountBank").value="";
                document.getElementById("form:observation").value="";
                getAll();
           },
           error : function(e) {
               alert('error: '+e);
               console.log(Object.values(e));
           }
    	});
	}
	
	
	function del(id){		
		var conf = confirm('Desea Eliminar el registro?');
		if(conf==true){
			$.ajax({
	            url:   url_api+'apioperator/v1/delete?id='+id,
	            type:  'get',
	            beforeSend: function () {
	            	document.getElementById("result").innerHTML = "Proccesing, waiting...";
	            },
	            success:  function (response) { 
	            	$("#result").addClass("alert alert-success").html(response.message);
	            	getAll();
	           },
	           error : function(e) {
	               alert('error: '+e);
	           }
	    	});	
		}
	}
