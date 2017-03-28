function inscrire(url) {
	if($("#nomInsc").val().length>0 && $("#adresseInsc").val().length>0 && $("#mdpInsc").val().length>0 && $("#prenomInsc").val().length>0 && 
			$("#mailInsc").val().length>0 && $("#mdp2Insc").val().length>0 && $("#nomEntInsc").val().length>0) {


		var name = $("#nomInsc").val();
		var adresse = $("#adresseInsc").val();
		var mdp = $("#mdpInsc").val();
		var prenom = $("#prenomInsc").val();
		var mail = $("#mailInsc").val();
		var nomEnt = $("#nomEntInsc").val();



		$.ajax({
			type : "POST",
			url : url,
			contentType : "application/json",
			data : JSON.stringify({
				"nom" : name,
				"address" : adresse,
				"passwdHash" : mdp,
				"prenom" : prenom,
				"email" : mail,
				"entreprise" : nomEnt,
			}),
			success : function(data) {
				alert("Vous êtes bien inscrit. Vous pouvez maintenant vous connecter");

			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('error: ' + textStatus);
			}
		});
	} else {
		alert("Veuillez remplir tous les champs !");
	}

}


function connexion(url) {
	
	if($("#loginUserCon").val().length>0 && $("#passwordCon").val().length>0) {
		//var nameConnexion = $("#login").val();

		$.ajax({
			type : "GET",
			url : url,
			datatype: 'json',
			beforeSend: function(req) {
				const s = btoa($("#loginUserCon").val() + ":" + $("#passwordCon").val());
				req.setRequestHeader("Authorization","Basic "+s);
			},
			success : function(data) {
				alert("Vous êtes maintenant connecté");
				
				},

			
			error : function(jqXHR, textStatus, errorThrown) {
				alert("Erreur : veuillez réessayer ou vous inscrire");
			}
		});

	} else {
		alert("Veuillez remplir tous les champs !");
	}
}

function ajoutProduit(url){
	if($("#nomProduit").val().length>0 && $("#descriptionForm").val().length>0 && $("#prixForm").val().length>0 && $("#allergenesForm").val().length>0 && 
			$("#qteForm").val().length>0 && $("#imageForm").val().length>0) {

		var nomProduit= $("#nomProduit").val();
		var descriptionForm= $("#descriptionForm").val();
		var prixForm = $("#prixForm").val();
		var allergenesForm= $("#allergenesForm").val();
		var qteForm= $("#qteForm").val();
		var imageForm= $("#imageForm").val();
		var typeForm= $("#typeForm").val();

		$.ajax({
			type : "POST",
			url : url,
			contentType : "application/json",
			data : JSON.stringify({
				"name" : nomProduit,
				"description" : descriptionForm,
				"prix" : prixForm,
				"allergies" : allergenesForm,
				"pathImage" : imageForm,
				"stock" : qteForm,
				"search" : nomProduit + descriptionForm,
				"type" : typeProduitForm,
			}),
			success : function(data) {
				alert("Produit ajouté.");

			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('error: ' + textStatus);
			}
		});

	} else {
		alert("Veuillez remplir tous les champs !");
	}
}

function listerUsers(url) {
	$("#zone").text("chargement...");

	$.ajax({
		type : "GET",
		url : url,
		success : function(data) {
			$("#zone").text("");
			data.forEach(function(element) {
				$("#zone").append(element.nom + "<br />");
			});

		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert('error: ' + textStatus);
		}
	});
}

function listerProduits(url) {
	$("#menus").text("chargement...");

	$.ajax({
		type : "GET",
		url : url,
		success : function(data) {
			$("#menus").text("");
			var s = "<h1 style=\"margin-left:60px;\"> Les Menus et les suppléments</h1><div id=\"MainContainer\" class=\"container-fluid\" style=\"margin-top:60px;\" ><div class=\"row\" >";
			var j = "http://today.wecook.fr/wp-content/uploads/2014/04/salade-color%C3%A9e.jpg";
			data.forEach(function(element) {

				s+= "<div class =\"col-sm-4 col-md-4\"> <div class=\"thumbnail\" style=\"text-align:center;\">";
				s+= "<a href=\"#\" > <img src=\"" + j+ "\"></a>";
				s+= "<div class=\"caption\">";
				s+= "<h3>" + element.name + "</h3>"
				s+= "<p>" + element.prix + "€</p>";
				s+= "<p>" + element.allergies + "</p>";
				s+= "<p><a id =\"btnDetails\" class=\"btn btn-default\" role=\"button\">Détails</a>";
				s+= "<a href=\"#\" class=\"btn btn-primary\" role=\"button\">Ajouter</a></p></div></div></div>";

			});
			s += "</div></div>";
			$("#menus").append(s);
			
			
			$("#btnDetails").click(function() {
				$('.cache').hide();
				$('#menusDetails').show();
				listerDetailsProduits("v1/produit")
			});


		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert('error: ' + textStatus);
		}
		
		
		
	});
}

function listerDetailsProduits(url) {
	$("#menusDetails").text("chargement...");
	
	$.ajax({
		type : "GET",
		url : url,
		success : function(data) {
			$("#menusDetails").text("");
			var s = "<div id=\"MainContainer\" class=\"container-fluid\" style=\"margin-top=60px;\" >";
			var j= "http://today.wecook.fr/wp-content/uploads/2014/04/salade-color%C3%A9e.jpg";
	
				s+= "<div class =\"col-sm-6 col-md-3\"> <div class=\"thumbnail\" style=\"text-align:center;\">";
				s+= "<a href=\"#\" > <img src=\" " + j+ "\"></a>";
				s+= "<div class=\"caption\">";
				s+= "<h3>" + data.name + "</h3>"
				s+= "<p>" + data.prix + "</p>";
				s+= "<p>" + data.allergies + "</p>";
				s+= "<p>" + data.description + "</p>";
				s+= "<p><a id =\"menus\" href=\"#\" class=\"btn btn-default\" role=\"button\">Retour</a>";
				s+= "<a href=\"#\" class=\"btn btn-primary\" role=\"button\">Ajouter</a></p></div></div>";
			
		
		
			s += "</div></div>";
			$("#menusDetails").append(s);


		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert('error: ' + textStatus);
		}
	});
}