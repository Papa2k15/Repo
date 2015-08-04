CREATE TABLE users(
	LSUID 			VARCHAR(64),
	firstName 		VARCHAR(60),		
	lastName 		VARCHAR(60),
	userName		VARCHAR(30),		
	password		VARCHAR(60),
	dob				DATE default '0000-00-00',
	gender			VARCHAR(6),
	email			VARCHAR(100),
	PRIMARY  KEY (LSPID)
) ENGINE=MyISAM; 

CREATE TABLE missions(
	LSMID  		VARCHAR(64),
	LSUID		VARCHAR(64),
	title	 	VARCHAR(100),		
	startDate	DATE default '0000-00-00',
	endDate		DATE default '0000-00-00',	
	complete	BOOLEAN default false,
	PRIMARY  KEY (LSMID)
) ENGINE=MyISAM; 

CREATE TABLE objectives(
	LSOID 		VARCHAR(64),
	LSMID		VARCHAR(64),
	description	VARCHAR(100),		
	trackerVal  VARCHAR(50),
	PRIMARY  KEY (LSMID)
) ENGINE=MyISAM; 