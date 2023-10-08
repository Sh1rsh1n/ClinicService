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
    client_id integer,
    name      text,
    birthday  text,
    foreign key (client_id) REFERENCES clients (id)
);

create table consultation
(
    id       integer primary key,
    client_id integer,
    pet_id integer,
    visite_date text,
    foreign key (client_id) references clients(id),
    foreign key (pet_id) references pets(id)
);