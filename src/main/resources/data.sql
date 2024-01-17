insert into roles (id, name) values (1, 'ROLE_USER') on conflict do nothing;

/*
    Hasło: helikopter
*/
insert into users (id, email, password, username, birth_date, name, surname, enable, locked) values (0,	'jozek@tester.pl',	'$2a$10$XE8G1kkrzy6e8bxnrIph3.BgNcl1dLLxSQVuIqFTlNQBe.K6Wq3.u',	'juser', '2000-01-01',	'Jozef', 'Testowy', true, false) on conflict do nothing;

insert into user_roles (user_id, role_id) values (
    0, 1
) on conflict do nothing;

-- Kategorie
insert into category (id, name, image) values (1, 'Śniadania','https://www.eatingwell.com/thmb/ZIsM-f-uVmqWx7JlJNsBFMCVOaY=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/HashBrown-5-e1941c86066346e8a592e4c589d4933d.jpg') on conflict do nothing;
insert into category (id, name, image) values (2, 'Dania główne','https://www.polonist.com/wp-content/uploads/2021/06/Polish-Kotlet-Schabowy-1600sq-o-735x735.jpg') on conflict do nothing;
insert into category (id, name, image) values (3, 'Desery', 'https://siosmutkinaslodko.pl/wp-content/uploads/2023/01/bardzo-puszysty-sernik-waniliowy-1200x1800.jpg' ) on conflict do nothing;
insert into category (id, name, image) values (4, 'Makarony', 'https://static01.nyt.com/images/2023/09/13/multimedia/11WEEKNIGHT-still-zwhl/11WEEKNIGHT-still-zwhl-master675.jpg') on conflict do nothing;
insert into category (id, name, image) values (5, 'Wegetariańskie', 'https://images.immediate.co.uk/production/volatile/sites/30/2023/01/Dhal-poached-eggs-284bff4.jpg?quality=90&resize=440,400') on conflict do nothing;
insert into category (id, name, image) values (6, 'Fit', 'https://fitchen.com/wp-content/uploads/2023/10/Avocado-Chick-bowl.jpg') on conflict do nothing;
insert into category (id, name, image) values (7, 'Zupy', 'https://www.kwestiasmaku.com/sites/v123.kwestiasmaku.com/files/zupa-pomidorowa-krem-01.jpg') on conflict do nothing;
insert into category (id, name, image) values (8, 'Sałatki', 'https://www.kwestiasmaku.com/sites/v123.kwestiasmaku.com/files/salatka_grecka_01.jpg') on conflict do nothing;

-- Składniki
insert into ingridient (id, name) values (1, 'Ziemniak') on conflict do nothing;
insert into ingridient (id, name) values (2, 'Pomidor') on conflict do nothing;
insert into ingridient (id, name) values (3, 'Marchewka') on conflict do nothing;
insert into ingridient (id, name) values (4, 'Cebula') on conflict do nothing;
insert into ingridient (id, name) values (5, 'Brokuły') on conflict do nothing;
insert into ingridient (id, name) values (6, 'Papryka') on conflict do nothing;
insert into ingridient (id, name) values (7, 'Kurczak') on conflict do nothing;
insert into ingridient (id, name) values (8, 'Wołowina') on conflict do nothing;
insert into ingridient (id, name) values (9, 'Seler') on conflict do nothing;
insert into ingridient (id, name) values (10, 'Bakłażan') on conflict do nothing;
insert into ingridient (id, name) values (11, 'Kapusta') on conflict do nothing;
insert into ingridient (id, name) values (12, 'Czosnek') on conflict do nothing;
insert into ingridient (id, name) values (13, 'Szpinak') on conflict do nothing;
insert into ingridient (id, name) values (14, 'Kukurydza') on conflict do nothing;
insert into ingridient (id, name) values (15, 'Kalafior') on conflict do nothing;
insert into ingridient (id, name) values (16, 'Pieczarki') on conflict do nothing;
insert into ingridient (id, name) values (17, 'Śmietana') on conflict do nothing;
insert into ingridient (id, name) values (18, 'Jogurt') on conflict do nothing;
insert into ingridient (id, name) values (19, 'Oliwa z oliwek') on conflict do nothing;
insert into ingridient (id, name) values (20, 'Mleko') on conflict do nothing;
insert into ingridient (id, name) values (21, 'Ser feta') on conflict do nothing;
insert into ingridient (id, name) values (22, 'Pietruszka') on conflict do nothing;
insert into ingridient (id, name) values (23, 'Kurkuma') on conflict do nothing;
insert into ingridient (id, name) values (24, 'Imbir') on conflict do nothing;
insert into ingridient (id, name) values (25, 'Ryż') on conflict do nothing;
insert into ingridient (id, name) values (26, 'Makaron') on conflict do nothing;
insert into ingridient (id, name) values (27, 'Krewetki') on conflict do nothing;
insert into ingridient (id, name) values (28, 'Tuńczyk') on conflict do nothing;
insert into ingridient (id, name) values (29, 'Czosnaczek') on conflict do nothing;
insert into ingridient (id, name) values (30, 'Ananas') on conflict do nothing;
insert into ingridient (id, name) values (31, 'Mango') on conflict do nothing;
insert into ingridient (id, name) values (32, 'Bazylia') on conflict do nothing;
insert into ingridient (id, name) values (33, 'Oregano') on conflict do nothing;
insert into ingridient (id, name) values (34, 'Pomarańcza') on conflict do nothing;
insert into ingridient (id, name) values (35, 'Cytryna') on conflict do nothing;
insert into ingridient (id, name) values (36, 'Makaron penne') on conflict do nothing;
insert into ingridient (id, name) values (37, 'Makaron spaghetti') on conflict do nothing;
insert into ingridient (id, name) values (38, 'Zielona fasolka') on conflict do nothing;
insert into ingridient (id, name) values (39, 'Kapary') on conflict do nothing;
insert into ingridient (id, name) values (40, 'Suszone pomidory') on conflict do nothing;
insert into ingridient (id, name) values (41, 'Tofu') on conflict do nothing;
insert into ingridient (id, name) values (42, 'Mango') on conflict do nothing;
insert into ingridient (id, name) values (43, 'Mieszanka sałat') on conflict do nothing;
insert into ingridient (id, name) values (44, 'Cytrynowa trawa') on conflict do nothing;
insert into ingridient (id, name) values (45, 'Koper włoski') on conflict do nothing;
insert into ingridient (id, name) values (46, 'Sok z limonki') on conflict do nothing;
insert into ingridient (id, name) values (47, 'Miod') on conflict do nothing;
insert into ingridient (id, name) values (48, 'Sok pomarańczowy') on conflict do nothing;
insert into ingridient (id, name) values (49, 'Kurczak brojler') on conflict do nothing;
insert into ingridient (id, name) values (50, 'Kapusta czerwona') on conflict do nothing;

-- Przepisy
insert into recipe (id, name, created_at, description, user_id, category_id, image, time, difficulty, likes) values (0, 'Pierogi', '2024-01-01 00:00:01', 'Pierogi po krakowski z kurczakiem.', 0, 4, 'https://img.freepik.com/darmowe-zdjecie/mloda-piekna-kobieta-korzystajacych-z-pelnowartosciowej-kolacji_273609-37472.jpg?w=1380&t=st=1705442475~exp=1705443075~hmac=f1af3ee60a850add0d25d11c8235d4d3b6c118a9254e9d64db26d78c31e85086', 123, 1, 0)  on conflict do nothing;;

-- Ilość-Przepis
insert into recipe_ingridient(amount, unit, recipe_id, ingridient_id) values (10, 'kg', 0, 1)  on conflict do nothing;
insert into recipe_ingridient(amount, unit, recipe_id, ingridient_id) values (1, 'szt.', 0, 2)  on conflict do nothing;