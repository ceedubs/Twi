# --- !Ups
create sequence twee_sequence start with 1000;

insert into twee (id, message) values (1, 'twee 1!');
insert into twee (id, message) values (2, 'twee 2!');

# --- !Downs
drop sequence twee_sequence;
delete from twee where id in (1, 2);
