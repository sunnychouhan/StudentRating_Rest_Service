create table STUDENT_SCORE
(
   STUDENT_ID integer not null,
   NAME varchar(255) not null,
   SUBJECT varchar(255) not null,
   CATEGORY varchar(255) not null,
   POINTS varchar(255) not null,
   SUBMISSION_DATE TIMESTAMP not null,
   primary key(STUDENT_ID)
);

create table ASSIGNMENT_CATEGORY
(
   CATEGORY_ID integer not null,
   CATEGORY_TYPE varchar(255) not null,
   CATEGORY_WEIGHT varchar(255) not null,
   primary key(CATEGORY_ID)
);