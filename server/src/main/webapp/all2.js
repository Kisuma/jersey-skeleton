function inscrire(url) {
 if(document.getElementById("nom").value() != "" && document.getElementById("adresse").value() != "" && 
		 document.getElementById("mdp").value() != "" && document.getElementById("prenom").value() != "" && 
		 document.getElementById("mail").value() != "" && document.getElementById("mdp2").value( != "") {
     $.ajax
     ({
       type: "POST",
       url: url,
       dataType: 'json',
       beforeSend : function(req) {
        req.setRequestHeader("Authorization", "Basic " + btoa($("#userlogin").val() + ":" + $("#passwdlogin").val()));
       },
       success: function (data) {
        afficheUser(data);
       },
       error : function(jqXHR, textStatus, errorThrown) {
       			alert('error: ' + textStatus);
       		}
     });
     } else {
     $.getJSON(url, function(data) {
     	    afficheUser(data);
        });
     }
 }