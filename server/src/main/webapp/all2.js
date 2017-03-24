function inscrire(url) {
 if($("#nom").val().length>0 && $("#adresse").val().length>0 && $("#mdp").val().length>0 && $("#prenom").val().length>0 && 
		 $("#mail").val().length>0 && $("#mdp2").val().length>0) {
   
	 var name = $("#nom").val();
	 var adresse = $("#adresse").val();
	 var mdp = $("#mdp").val();
	 var prenom = $("#prenom").val();
	 var mail = $("#mail").val();
	 
	 
	 
	 $.ajax({
				type : "POST",
				url : url,
				contentType : "application/json",
				data : JSON.stringify({
					"nom" : name,
					"adresse" : adresse,
					"mdp" : mdp,
					"prenom" : prenom,
					"mail" : mail,
				}),
				success : function(data) {
					alert('vous êtes bien inscrit');

				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert('error: ' + textStatus);
				}
			});
		} else {
			alert("veuillez remplir tous les champs !");
		}

     }
 