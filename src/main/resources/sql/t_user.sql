use data_transport;
CREATE TABLE t_user (
    id INT AUTO_INCREMENT COMMENT 'user id',
	uid VARCHAR(20) NOT NULL UNIQUE COMMENT 'unique user id',
	username VARCHAR(20) NOT NULL UNIQUE COMMENT 'user name',
	password CHAR(32) NOT NULL COMMENT 'password',
	salt CHAR(36) COMMENT 'salt',
	is_delete INT COMMENT '0-not deleted，1-deleted',
	created_by VARCHAR(20),
	created_time DATETIME ,
	modified_by VARCHAR(20),
	modified_time DATETIME,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;