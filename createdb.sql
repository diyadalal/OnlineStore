CREATE TABLE users (
	yelp_id varchar(10) PRIMARY KEY,
    email varchar(30), 
    first_name varchar(20),
    last_name varchar(20),
    DOB date,
    birthplace char(2),
    gender char(1)
);

CREATE TABLE friends (
	id1 varchar(10),
    id2 varchar(10),
    FOREIGN KEY (id1) REFERENCES users(yelp_id) 
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id2) REFERENCES users(yelp_id) 
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE complimented (
	id1 varchar(10),
    id2 varchar(10),
    FOREIGN KEY (id1) REFERENCES users(yelp_id) 
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id2) REFERENCES users(yelp_id) 
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE business (
	bid varchar(10) PRIMARY KEY,
    bname varchar(30),
    days_open ENUM('Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun') NOT NULL,
    ambience ENUM('Touristy'),
    street varchar(40),
    city varchar(20),
    state char(2),
    zip INT,
    bcat ENUM('BCT1','BCT2','BCT3','BCT4','BCT5','BCT6','BCT7','BCT8')
);

CREATE TABLE business_catg (
	bcat_id varchar(10) PRIMARY KEY,
    catg_name varchar(30) NOT NULL,
    subcat
);