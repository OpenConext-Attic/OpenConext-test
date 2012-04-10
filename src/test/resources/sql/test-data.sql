drop table if exists example_table;

create table example_table (id varchar(255), user_id varchar(255) not null);

insert into example_table (id, user_id) values ('dada', 'whatever');
