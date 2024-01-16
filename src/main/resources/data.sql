insert into roles (id, name) values (1, 'ROLE_USER') on conflict do nothing;

/*
    Hasło: helikopter
*/
insert into users (id, email, password, username, birth_date, name, surname) values (1,	'jozek@tester.pl',	'$2a$10$XE8G1kkrzy6e8bxnrIph3.BgNcl1dLLxSQVuIqFTlNQBe.K6Wq3.u',	'juser', '2000-01-01',	'Jozef', 'Testowy') on conflict do nothing;

insert into user_roles (user_id, role_id) values (
    1, 1
) on conflict do nothing;

insert into category (id, name) values (1, 'Śniadania') on conflict do nothing;
insert into category (id, name) values (2, 'Obiady') on conflict do nothing;
insert into category (id, name) values (3, 'Kolacja') on conflict do nothing;
insert into category (id, name) values (4, 'Przystawki') on conflict do nothing;
insert into category (id, name) values (5, 'Desery') on conflict do nothing;
insert into category (id, name) values (6, 'Napoje') on conflict do nothing;

insert into ingridient (id, name) values (1, 'Ziemniak') on conflict do nothing;
insert into ingridient (id, name) values (2, 'Kurczak') on conflict do nothing;

insert into recipe (id, name, created_at, description, user_id, category_id, photo_url, amount_time_to_prepare, difficulty, likes) values (1, 'Pierogi', '2024-01-01 00:00:01', 'Pierogi po krakowski z kurczakiem.', 1, 4, 'https://img.freepik.com/darmowe-zdjecie/mloda-piekna-kobieta-korzystajacych-z-pelnowartosciowej-kolacji_273609-37472.jpg?w=1380&t=st=1705442475~exp=1705443075~hmac=f1af3ee60a850add0d25d11c8235d4d3b6c118a9254e9d64db26d78c31e85086', 123, 'easy', 0)  on conflict do nothing;;

insert into recipe_ingridient(amount, scale, recipe_id, ingridient_id) values (10, 'kg', 1, 1)  on conflict do nothing;;
insert into recipe_ingridient(amount, scale, recipe_id, ingridient_id) values (1, 'szt.', 1, 2)  on conflict do nothing;;