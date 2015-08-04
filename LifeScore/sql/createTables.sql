CREATE TABLE users(
	LSUID 			VARCHAR(100),
	firstName 		VARCHAR(60),		
	lastName 		VARCHAR(60),
	userName		VARCHAR(30),		
	password		VARCHAR(60),
	email			VARCHAR(100),
	PRIMARY  KEY (LSPID)
) ENGINE=MyISAM; 

CREATE TABLE demographics(
	LSUID 			VARCHAR(100),
	dob				DATE default '0000-00-00',
	gender			VARCHAR(6),
	PRIMARY  KEY (LSPID)
) ENGINE=MyISAM;

CREATE TABLE missions(
	LSMID  		VARCHAR(100),
	LSUID		VARCHAR(100),
	title	 	VARCHAR(100),		
	startDate	DATE default '0000-00-00',
	endDate		DATE default '0000-00-00',	
	complete	BOOLEAN default false,
	PRIMARY  KEY (LSMID)
) ENGINE=MyISAM; 

CREATE TABLE objectives(
	LSOID 		VARCHAR(100),
	LSMID		VARCHAR(100),
	description	VARCHAR(100),		
	trackerVal  VARCHAR(50),
	PRIMARY  KEY (LSMID)
) ENGINE=MyISAM; 