BEGIN TRANSACTION;
DROP TABLE IF EXISTS "TipoDeAtraccion";
CREATE TABLE IF NOT EXISTS "TipoDeAtraccion" (
	"Id"	INTEGER NOT NULL UNIQUE,
	"Tipo"	TEXT NOT NULL UNIQUE,
	PRIMARY KEY("Id" AUTOINCREMENT)
);
DROP TABLE IF EXISTS "Atraccion";
CREATE TABLE IF NOT EXISTS "Atraccion" (
	"Id"	INTEGER NOT NULL UNIQUE,
	"Nombre"	TEXT NOT NULL UNIQUE,
	"Costo"	REAL NOT NULL,
	"Tiempo"	REAL NOT NULL,
	"Cupo"	INTEGER NOT NULL,
	"Tipo"	INTEGER NOT NULL,
	PRIMARY KEY("Id" AUTOINCREMENT),
	CONSTRAINT "tipo" FOREIGN KEY("Tipo") REFERENCES "TipoDeAtraccion"("Id")
);
DROP TABLE IF EXISTS "Usuarios";
CREATE TABLE IF NOT EXISTS "Usuarios" (
	"Id"	INTEGER NOT NULL UNIQUE,
	"Nombre"	TEXT NOT NULL UNIQUE,
	"Preferencia"	INTEGER NOT NULL,
	"Presupuesto"	REAL NOT NULL,
	"TiempoDisponible"	REAL NOT NULL,
	PRIMARY KEY("Id" AUTOINCREMENT),
	FOREIGN KEY("Preferencia") REFERENCES "TipoDeAtraccion"("Id")
);
DROP TABLE IF EXISTS "Promocion";
CREATE TABLE IF NOT EXISTS "Promocion" (
	"Id"	INTEGER NOT NULL UNIQUE,
	"Tipo"	INTEGER NOT NULL,
	"Nombre"	TEXT NOT NULL UNIQUE,
	"Atracciones"	TEXT NOT NULL,
	"Especial"	TEXT NOT NULL,
	"TipoPromocion"	TEXT NOT NULL,
	PRIMARY KEY("Id" AUTOINCREMENT),
	FOREIGN KEY("Tipo") REFERENCES "TipoDeAtraccion"("Id")
);
DROP TABLE IF EXISTS "Itinerarios";
CREATE TABLE IF NOT EXISTS "Itinerarios" (
	"Id"	INTEGER NOT NULL UNIQUE,
	"Usuario"	TEXT NOT NULL,
	"Atracciones"	TEXT,
	"Promociones"	INTEGER,
	"Precio"	REAL NOT NULL,
	"Tiempo"	REAL NOT NULL,
	PRIMARY KEY("Id" AUTOINCREMENT),
	FOREIGN KEY("Usuario") REFERENCES "Usuarios"("Nombre"),
	FOREIGN KEY("Promociones") REFERENCES "Promocion"("Id")
);
INSERT INTO "TipoDeAtraccion" ("Id","Tipo") VALUES (1,'Aventura'),
 (2,'Paisaje'),
 (3,'Degustacion');
INSERT INTO "Atraccion" ("Id","Nombre","Costo","Tiempo","Cupo","Tipo") VALUES (7,'Moria',10.0,2.0,6,1),
 (8,'Minas de Tirith',5.0,2.5,25,2),
 (9,'La Comarca',5.0,6.5,150,3),
 (10,'Mordor',25.0,3.0,4,1),
 (11,'Abismo De Helm',5.0,2.0,15,2),
 (12,'Lothlorien',35.0,1.0,30,3),
 (13,'Erebor',12.0,3.0,32,2),
 (14,'Bosque Negro',3.0,4.0,12,1),
 (15,'Nevarra',5.0,2.0,15,3);
INSERT INTO "Usuarios" ("Id","Nombre","Preferencia","Presupuesto","TiempoDisponible") VALUES (1,'Eowyn',1,10.0,8.0),
 (2,'Gandalf',2,10.0,8.0),
 (3,'Sam',3,36.0,8.0),
 (4,'Galadriel',2,120.0,6.0);
INSERT INTO "Promocion" ("Id","Tipo","Nombre","Atracciones","Especial","TipoPromocion") VALUES (1,1,'Pack Aventura','Bosque Negro - Mordor','20','Porcentuales'),
 (2,1,'Pack Aventura II','Moria - Mordor','Bosque Negro','AxB'),
 (3,1,'Pack Aventura III','Bosque Negro - Mordor','4','Absolutas'),
 (4,2,'Pack Paisajes','Minas de Tirith - Abismo de Helm','Erebor','AxB'),
 (5,2,'Pack Paisajes II','Erebor - Abismo de Helm','8','Absolutas'),
 (6,2,'Pack Paisajes III','Minas de Tirith - Erebor','Abismo de Helm','AxB'),
 (7,3,'Pack Degustacion','Lothlorien - La Comarca','36','Absolutas'),
 (8,3,'Pack Degustacion II','La Comarca - Nevarra','15','Porcentuales'),
 (9,3,'Pack Degustacion III','Lothlorien - Nevarra','10','Porcentuales');
COMMIT;
