drop table if exists clients;
drop table if exists pets;
drop table if exists consultations;

create table clients
(
    id       integer primary key,
    name     text,
    surname  text,
    birthday text
);

create table pets
(
    id        integer primary key,
    name      text,
    birthday  text
);

create table consultations
(
    id       integer primary key,
    client_id integer,
    pet_id integer,
    visit_date text,
    description text,
    foreign key (client_id) references clients(id),
    foreign key (pet_id) references pets(id)
);