DROP TABLE IF EXISTS student;
CREATE TABLE student (
	id numeric(24) NOT NULL Primary Key,
	state_id varchar(255),
	first_name varchar(255),
	last_name varchar(255),
	gender char(1),
	birthdate date,
	race char(2),
	meal_status char(2),
	english_proficiency char(2),
	native_language char(5),
	service_code char(2),
	primary_disability_type char(2),
	iep_reading char(1),
	iep_math char(1),
	iep_behavior char(1),
	gifted_talented char(1),
	section504 char(1),
	mobility char(1)
);

DROP TABLE IF EXISTS teacher;
create table teacher (
	id numeric(24) NOT NULL Primary Key,
	first_name varchar(255),
	last_name varchar(255),
	email varchar(255)
);

ALTER TABLE [dbo].[enrollment] DROP CONSTRAINT [FK_enrollment]

DROP TABLE IF EXISTS school;
create table school (
	school_name varchar(255) NOT NULL Primary Key,
	school_district varchar(255),
	school_state varchar(255)
);

DROP TABLE IF EXISTS enrollment;
create table enrollment (
	student_id numeric(24) NOT NULL,
	teacher_id numeric(24) NOT NULL,
	grade char(2),
	course varchar(64),
	section varchar(16),
	school_name varchar(255) 
	CONSTRAINT PK_enrollment PRIMARY KEY (student_id,teacher_id)
	CONSTRAINT FK_enrollment FOREIGN KEY REFERENCES school(school_name)
);

