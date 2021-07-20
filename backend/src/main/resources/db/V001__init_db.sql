-------------------------------------------------------
-- DDL: creating the tables
-------------------------------------------------------
CREATE TABLE project (
	id SERIAL NOT NULL PRIMARY KEY,
	version BIGINT NOT NULL default 0,
	name VARCHAR UNIQUE NOT null
);

-- DDL: Creating the table
CREATE TABLE todo_item (
	id SERIAL NOT NULL PRIMARY KEY,
	version BIGINT NOT NULL DEFAULT 0,
	status VARCHAR NOT NULL,
	description VARCHAR NOT NULL,
	project_id BIGINT  NULL,

	-- foreign key reference
	 CONSTRAINT fk_project
      FOREIGN KEY(project_id)
	  REFERENCES project (id)
);
