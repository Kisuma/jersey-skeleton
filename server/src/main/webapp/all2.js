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
				alert("vous êtes bien inscrit");

			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('error: ' + textStatus);
			}
		});
	} else {
		alert("veuillez remplir tous les champs !");
	}

}



function connexion(url) {
	if($("#loginUser").val().length>0 ) {
		var nameConnexion = $("#login").val();

		$.ajax({
			type : "GET",
			url : "v1/user",
			success : function(data) {
				$("#zone").text("");
				data.forEach(function(element) {
					$("#zone").append(element.name + "<br />");
				});

			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('error: ' + textStatus);
			}
		});

	} else {
		alert("veuillez remplir tous les champs !");
	}
}

function ajoutProduit(url){
	if($("#nomProduit").val().length>0 && $("#descriptionForm").val().length>0 && $("#prixForm").val().length>0 && $("#allergenesForm").val().length>0 && 
			$("#qteForm").val().length>0 && $("#imageForm").val().length>0) {
}
