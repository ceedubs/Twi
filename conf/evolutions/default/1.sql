# --- !Ups

create table Twee (
	id bigint(20) not null auto_increment,
	created timestamp not null default current_timestamp,
	message varchar(14) not null,
	primary key (id)
);

# --- !Downs
drop table Twee
