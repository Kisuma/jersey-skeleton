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
				alert("Vous êtes bien inscrit.");

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
			var s = "<h1 style=\"margin-left:60px;\"> Les Menus et les suppléments</h1><div id=\"MainContainer\" class=\"container-fluid\" style=\"margin-top:60px;\" >";
			//var j = "http://www.supermarchesmatchdrive.fr/media/catalog/product/cache/1/image/300x/9df78eab33525d08d6e5fb8d27136e95/1/3/1365428_37901.jpg";
			data.forEach(function(element) {
				
				s+= "<div class=\"row\" > <div class =\"col-sm-6 col-md-3\"> <div class=\"thumbnail\" style=\"text-align:center;\">";
				s+= "<a href=\"#\" > <img src=\"" + element.pathImage+ "\"></a>";
				s+= "<div class=\"caption\">";
				s+= "<h3>" + element.name + "</h3>"
				s+= "<p>" + element.prix + "</p>";
				s+= "<p>" + element.allergies + "</p>";
				s+= "<p><a id =\"btnDetails\" href=\"#\" class=\"btn btn-default\" role=\"button\">Détails</a>";
				s+= "<a href=\"#\" class=\"btn btn-primary\" role=\"button\">Ajouter</a></p></div></div></div>";
				
			});
			s += "</div>";
			$("#menus").append(s);


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
			//var j = "http://www.supermarchesmatchdrive.fr/media/catalog/product/cache/1/image/300x/9df78eab33525d08d6e5fb8d27136e95/1/3/1365428_37901.jpg";
			data.forEach(function(element) {
				s+= "<div class=\"row\" > <div class =\"col-sm-6 col-md-3\"> <div class=\"thumbnail\" style=\"text-align:center;\">";
				s+= "<a href=\"#\" > <img src=\" " + element.pathImage+ "\"></a>";
				s+= "<div class=\"caption\">";
				s+= "<h3>" + element.name + "</h3>"
				s+= "<p>" + element.prix + "</p>";
				s+= "<p>" + element.allergies + "</p>";
				s+= "<p>" + element.description + "</p>";
				s+= "<p><a id =\"btnDetails\" href=\"#\" class=\"btn btn-default\" role=\"button\">Détails</a>";
				s+= "<a href=\"#\" class=\"btn btn-primary\" role=\"button\">Ajouter</a></p></div></div></div>";

				/*	
				 s += "<tr><td>Nom : " + element.name + "</td><br /><td> Prix : " + element.prix + "</td><br /><td> Desctription du produit : " 
					     + element.description + "</td><br /><td> Allergies : " + element.allergies + "</td><br /><td> Nous avons en stock " 
					     + element.stock + " " + element.name+ "</td><br /><td>" 
					     + "<img src='http://www.supermarchesmatchdrive.fr/media/catalog/product/cache/1/image/300x/9df78eab33525d08d6e5fb8d27136e95/1/3/1365428_37901.jpg'></td> <br />"
					     + "</tr><br />";

				$("#zone").append("<tr><td> Nom : " + element.name + "<br />Prix : " + element.prix + "<br />Desctription du produit : " 
					     + element.description + "<br />Allergies : " + element.allergies + "<br />Nous avons en stock " 
					     + element.stock + " " + element.name+ "<br />" 
					  //   + "<img src='http://www.supermarchesmatchdrive.fr/media/catalog/product/cache/1/image/300x/9df78eab33525d08d6e5fb8d27136e95/1/3/1365428_37901.jpg'> <br />"
					     + "</tr></td>");*/
			});
			s += "</div>";
			$("#menusDetails").append(s);


		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert('error: ' + textStatus);
		}
	});
}