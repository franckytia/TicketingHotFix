
CREATE  TABLE "public".avion ( 
	id                   serial  NOT NULL  ,
	modele               varchar(100)  NOT NULL  ,
	capacite             integer  NOT NULL  ,
	date_fabrication     date  NOT NULL  ,
	CONSTRAINT avion_pkey PRIMARY KEY ( id )
 );

CREATE  TABLE "public".categorie_age ( 
	id                   bigint  NOT NULL GENERATED  BY DEFAULT AS IDENTITY ,
	age_max              integer    ,
	age_min              integer    ,
	description          varchar(255)    ,
	coefficient          double precision    ,
	prix_billet          double precision    ,
	CONSTRAINT categorie_age_pkey PRIMARY KEY ( id )
 );

CREATE  TABLE "public".reservation_detail ( 
	id                   integer  NOT NULL GENERATED  BY DEFAULT AS IDENTITY ,
	id_reservation       integer  NOT NULL  ,
	id_type_siege        integer  NOT NULL  ,
	nom_passager         varchar(100)  NOT NULL  ,
	passport             varchar(50)  NOT NULL  ,
	remarque             varchar(255)    ,
	taille               double precision    ,
	CONSTRAINT reservation_detail_pkey PRIMARY KEY ( id )
 );

CREATE  TABLE "public".typesiege ( 
	id                   serial  NOT NULL  ,
	nom                  varchar(50)  NOT NULL  ,
	coefficient_prix     double precision  NOT NULL  ,
	CONSTRAINT typesiege_pkey PRIMARY KEY ( id ),
	CONSTRAINT typesiege_nom_key UNIQUE ( nom ) 
 );

CREATE  TABLE "public".utilisateur ( 
	id                   serial  NOT NULL  ,
	nom                  varchar(100)  NOT NULL  ,
	prenom               varchar(100)  NOT NULL  ,
	email                varchar(150)  NOT NULL  ,
	"password"           varchar(255)  NOT NULL  ,
	"role"               varchar(50)  NOT NULL  ,
	CONSTRAINT utilisateur_pkey PRIMARY KEY ( id ),
	CONSTRAINT utilisateur_email_key UNIQUE ( email ) 
 );

ALTER TABLE "public".utilisateur ADD CONSTRAINT utilisateur_role_check CHECK ( role)::text = ANY ((ARRAY['admin'::character varying, 'client'::character varying])::text[] );

CREATE  TABLE "public".ville ( 
	id                   serial  NOT NULL  ,
	nom                  varchar(100)  NOT NULL  ,
	CONSTRAINT ville_pkey PRIMARY KEY ( id ),
	CONSTRAINT ville_nom_key UNIQUE ( nom ) 
 );

CREATE  TABLE "public".vol ( 
	id                   serial  NOT NULL  ,
	numero_vol           varchar(50)  NOT NULL  ,
	id_avion             integer    ,
	id_ville_depart      integer    ,
	id_ville_arrivee     integer    ,
	date_depart          timestamp  NOT NULL  ,
	date_arrivee         timestamp  NOT NULL  ,
	prix_base            numeric(10,2)  NOT NULL  ,
	id_cat_age           integer    ,
	CONSTRAINT vol_pkey PRIMARY KEY ( id ),
	CONSTRAINT vol_numero_vol_key UNIQUE ( numero_vol ) ,
	CONSTRAINT vol_id_avion_fkey FOREIGN KEY ( id_avion ) REFERENCES "public".avion( id ) ON DELETE CASCADE  ,
	CONSTRAINT vol_id_ville_depart_fkey FOREIGN KEY ( id_ville_depart ) REFERENCES "public".ville( id ) ON DELETE CASCADE  ,
	CONSTRAINT vol_id_ville_arrivee_fkey FOREIGN KEY ( id_ville_arrivee ) REFERENCES "public".ville( id ) ON DELETE CASCADE  ,
	CONSTRAINT fk_vol_vol FOREIGN KEY ( id_cat_age ) REFERENCES "public".categorie_age( id )   
 );

CREATE  TABLE "public".promotion ( 
	id                   serial  NOT NULL  ,
	id_vol               integer    ,
	pourcentage_reduction numeric(5,2)  NOT NULL  ,
	nombre_places_promo  integer  NOT NULL  ,
	id_type_siege        integer    ,
	CONSTRAINT promotion_pkey PRIMARY KEY ( id ),
	CONSTRAINT fk_promotion_promotion FOREIGN KEY ( id_type_siege ) REFERENCES "public".typesiege( id )   ,
	CONSTRAINT promotion_id_vol_fkey FOREIGN KEY ( id_vol ) REFERENCES "public".vol( id ) ON DELETE CASCADE  
 );

CREATE  TABLE "public".reservation ( 
	id                   serial  NOT NULL  ,
	id_vol               integer    ,
	au_nom               varchar(200)  NOT NULL  ,
	cin                  integer  NOT NULL  ,
	date_reservation     timestamp DEFAULT CURRENT_TIMESTAMP   ,
	statut               varchar DEFAULT 'En attente'::character varying   ,
	prix_total           double precision    ,
	CONSTRAINT reservation_pkey PRIMARY KEY ( id ),
	CONSTRAINT reservation_id_vol_fkey FOREIGN KEY ( id_vol ) REFERENCES "public".vol( id ) ON DELETE CASCADE  
 );

ALTER TABLE "public".reservation ADD CONSTRAINT reservation_statut_check CHECK ( statut)::text = ANY ((ARRAY['Confirm‚'::character varying, 'Annul‚'::character varying, 'En attente'::character varying])::text[] );

CREATE  TABLE "public".reservation_personne ( 
	id                   serial  NOT NULL  ,
	id_reservation       integer    ,
	id_categorie_peronne integer    ,
	total                integer    ,
	CONSTRAINT reservation_personne_pkey PRIMARY KEY ( id ),
	CONSTRAINT reservation_personne_id_reservation_fkey FOREIGN KEY ( id_reservation ) REFERENCES "public".reservation( id ) ON DELETE CASCADE  ,
	CONSTRAINT reservation_personne_id_categorie_peronne_fkey FOREIGN KEY ( id_categorie_peronne ) REFERENCES "public".categorie_age( id ) ON DELETE CASCADE  
 );
