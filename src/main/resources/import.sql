insert into user_entity (firstname, lastname, email, password, role, user_status) values ('Testeur', 'UN', 'test1@test.com', 'Password123!','USER', 'CONNECTED');
insert into user_entity (firstname, lastname, email, password, role, user_status) values ('Testeur', 'DEUX', 'test2@test.com', 'Password123!', 'USER', 'CONNECTED');
insert into user_entity (firstname, lastname, email, password, role, user_status) values ('Testeur', 'TROIS', 'test3@test.com', 'Password123!','USER', 'CONNECTED');
insert into user_entity (firstname, lastname, email, password, role, user_status) values ('Testeur', 'QUATRE', 'test4@test.com','Password123!', 'USER', 'CONNECTED');
insert into user_entity (firstname, lastname, email, password, role, user_status) values ('Testeur', 'CINQ', 'test5@test.com','Password123!', 'USER', 'CONNECTED');


insert into book_ref_entity (author_book, isbn_book, pub_date_book, copies, title_book) values ('Auteur UN', '1234567', '03-24-2022', 3, 'Le Crimier apprend à coder');
insert into book_ref_entity (author_book, isbn_book, pub_date_book, copies, title_book) values ('Auteur Deux', '1234568', '01-26-1997', 3, 'Les tribulations du Crimier vol 1');
insert into book_ref_entity (author_book, isbn_book, pub_date_book, copies, title_book) values ('Auteur Trois', '1239567', '02-24-2009', 3, 'Le crimier se reconverti');
insert into book_ref_entity (author_book, isbn_book, pub_date_book, copies, title_book) values ('Auteur Quatre', '1884567', '03-07-2012', 3, 'Le décors');

insert into cd_ref_entity (artist, copies, duration, publication_date, song_number, title) values ('Enrico Sangiuliano', 2, 190, '12-24-1992', 12, 'Biomorph');
insert into cd_ref_entity (artist, copies, duration, publication_date, song_number, title) values ('Vald', 2, 170, '02-24-2022', 10, 'Depression');
insert into cd_ref_entity (artist, copies, duration, publication_date, song_number, title) values ('Ninho', 2, 156, '10-24-2002', 12, 'la Hess');
insert into cd_ref_entity (artist, copies, duration, publication_date, song_number, title) values ('Garance',2, 178, '03-18-1995', 15, 'Sous les cocotiers');

insert into dvd_ref_entity (director, copies, duration, publication_date,  title) values ('Martia Cimini', 2, 190, '12-24-1992',  'Il faisait beau en Sicile');
insert into dvd_ref_entity (director, copies, duration, publication_date,  title) values ('Mario Cimino', 2, 198, '12-26-1972',  'Le grand demenagement');
insert into dvd_ref_entity (director, copies, duration, publication_date, title) values ('Victor Rodio', 2, 190, '12-24-1992',  'Demain');
insert into dvd_ref_entity (director, copies, duration, publication_date, title) values ('Virginie Cibi', 2, 190, '12-24-1992',  'Ce printemps à la mer');

insert into book_entity (is_dispo, reference_id) values (true, 1);
insert into book_entity (is_dispo, reference_id) values (false, 1);
insert into book_entity (is_dispo, reference_id) values (false, 1);
insert into book_entity (is_dispo, reference_id) values (false, 2);
insert into book_entity (is_dispo, reference_id) values (true, 2);
insert into book_entity (is_dispo, reference_id) values (true, 2);
insert into book_entity (is_dispo, reference_id) values (false, 3);
insert into book_entity (is_dispo, reference_id) values (true, 3);
insert into book_entity (is_dispo, reference_id) values (true, 3);
insert into book_entity (is_dispo, reference_id) values (true, 4);
insert into book_entity (is_dispo, reference_id) values (true, 4);
insert into book_entity (is_dispo, reference_id) values (false, 4);

insert into cd_entity (is_dispo, reference_id) values (false, 1);
insert into cd_entity (is_dispo, reference_id) values (false, 1);
insert into cd_entity (is_dispo, reference_id) values (true, 2);
insert into cd_entity (is_dispo, reference_id) values (true, 2);
insert into cd_entity (is_dispo, reference_id) values (true, 3);
insert into cd_entity (is_dispo, reference_id) values (true, 3);
insert into cd_entity (is_dispo, reference_id) values (true, 4);
insert into cd_entity (is_dispo, reference_id) values (true, 4);


insert into dvd_entity(is_dispo, type_dvd, reference_id) values (false, 'BLUERAY', 1);
insert into dvd_entity(is_dispo, type_dvd, reference_id) values (true, 'BLUERAY', 1);
insert into dvd_entity(is_dispo, type_dvd, reference_id) values (true, 'NORMAL', 1);
insert into dvd_entity(is_dispo, type_dvd, reference_id) values (true, 'NORMAL', 2);
insert into dvd_entity(is_dispo, type_dvd, reference_id) values (true, 'BLUERAY', 2);
insert into dvd_entity(is_dispo, type_dvd, reference_id) values (true, 'NORMAL', 3);
insert into dvd_entity(is_dispo, type_dvd, reference_id) values (true, 'BLUERAY', 3);
insert into dvd_entity(is_dispo, type_dvd, reference_id) values (true, 'NORMAL', 4);
insert into dvd_entity(is_dispo, type_dvd, reference_id) values (true, 'BLUERAY', 4);

insert into reservation_book_entity(borrowing_date, returning_date, book_id, user_id_user) values ('03-24-2022', '03-31-2022', 1, 1);
insert into reservation_book_entity(borrowing_date, returning_date, book_id, user_id_user) values ('03-24-2022', '03-31-2022', 2, 1);
insert into reservation_book_entity(borrowing_date, returning_date, book_id, user_id_user) values ('03-24-2022', '03-31-2022', 4, 2);
insert into reservation_book_entity(borrowing_date, returning_date, book_id, user_id_user) values ('03-24-2022', '03-31-2022', 12, 1);


insert into reservation_cd_entity(borrowing_date, returning_date, cd_id, user_id_user) values ('03-24-2022', '03-31-2022', 1, 2); 
insert into reservation_cd_entity(borrowing_date, returning_date, cd_id, user_id_user) values ('03-24-2022', '03-31-2022', 2, 3); 

insert into reservation_dvd_entity (borrowing_date, returning_date, dvd_id, user_id_user) values ('03-24-2022', '03-31-2022', 1, 3);