DROP TABLE IF EXISTS Commandes cascade;
DROP TABLE IF EXISTS Users cascade;
DROP TABLE IF EXISTS Produits cascade;

CREATE TABLE Users
(	IdUser int primary key,
	login text NOT NULL,
	mdp text NOT NULL,
	Nom text NOT NULL,
	Prenom text NOT NULL,
	Adresse text NOT NULL,
	Tel text,
	Mail text NOT NULL,
	Role text);

CREATE TABLE Produits
(	IdProduit int primary key,
	Nom text NOT NULL,
	Description text NOT NULL,
	Prix float NOT NULL,
	Allergies text,
	Stock int);

CREATE TABLE Commandes
(	IdUser int not null,
	IdProduit int not null,
	DateCommande timestamp not null,
	constraint pk_commandes primary key (IdUser,IdProduit),
	constraint fk_Users foreign key (IdUser) references Users(IdUser),
	constraint fk_products foreign key (IdProduit) references Produits(IdProduit));

Insert into Users (IdUser,login,mdp,Nom,Prenom,Adresse,Mail,Role) values (1,'root','root','admin','admin','rue des admin 00000 AdminLand','admin@admin.admin','admin');
Insert into Users (IdUser,login,mdp,Nom,Prenom,Adresse,Mail,Role) values(2,'user','user','theo','capon','iut-lille1','theo.capon@etudiant.univ-lille1.fr','user');
Insert into Produits(IdProduit,Nom,Description,Prix,Allergies,Stock) values (1,'Café','Un délicieux café moulu au grain', 0.50, 'Caféine',100);
Insert into Commandes(IdUser, IdProduit, DateCommande) values (2, 1, '2017-03-22 14:38:29');