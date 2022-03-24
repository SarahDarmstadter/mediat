insert into user_entity (firstname, lastname, email, password, role, user_status) values ('Testeur', 'UN', 'test1@test.com', 'Password123!','user', 'connected');
insert into user_entity (firstname, lastname, email, password, role, user_status) values ('Testeur', 'DEUX', 'test2@test.com', 'Password123!', 'user', 'connected');
insert into user_entity (firstname, lastname, email, password, role, user_status) values ('Testeur', 'TROIS', 'test3@test.com', 'Password123!','user', 'connected');


insert into book_ref_entity (author_book, isbn_book, pub_date_book, copy_number, title_book) values ('Auteur UN', '1234567', '03-24-2022', 3, 'Le Crimier apprend à coder');
insert into book_ref_entity (author_book, isbn_book, pub_date_book, copy_number, title_book) values ('Auteur Deux', '1234568', '01-26-1997', 3, 'Les tribulations du Crimier vol 1');


insert into CDref_entity (artist, copies, duration, publication_date, song_number, title) values ('Enrico Sangiuliano', 2, 190, '12-24-1992', 12, 'Biomorph');
insert into CDref_entity (artist, copies, duration, publication_date, song_number, title) values ('Vald', 2, 170, '02-24-2022', 10, 'Depression');


insert into dvdref_entity (director, copies, duration, publication_date,  title) values ('Martia Cimini', 2, 190, '12-24-1992',  'Il faisait beau en Sicile');
insert into dvdref_entity (director, copies, duration, publication_date,  title) values ('Mario Cimino', 2, 198, '12-26-1972',  'Le grand demenagement');

insert into book_entity (is_dispo, reference_id) values (false, 1);
insert into book_entity (is_dispo, reference_id) values (true, 1);
insert into book_entity (is_dispo, reference_id) values (true, 1);
insert into book_entity (is_dispo, reference_id) values (false, 2);
insert into book_entity (is_dispo, reference_id) values (true, 2);

insert into CDentity (is_dispo, reference_id) values (false, 1);
insert into CDentity (is_dispo, reference_id) values (true, 1);
insert into CDentity (is_dispo, reference_id) values (false, 2);



insert into dvdentity(is_dispo, type_dvd, reference_id) values (true, 'blueray', 1);
insert into dvdentity(is_dispo, type_dvd, reference_id) values (false, 'blueray', 1);
insert into dvdentity(is_dispo, type_dvd, reference_id) values (true, 'normal', 1);
insert into dvdentity(is_dispo, type_dvd, reference_id) values (true, 'normal', 2);
insert into dvdentity(is_dispo, type_dvd, reference_id) values (true, 'blueray', 2);




