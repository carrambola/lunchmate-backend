insert into roles (id, name) values (1, 'ROLE_USER') on conflict do nothing;

/*
    Hasło: helikopter
*/
insert into users (id, email, password, username, birth_date, name, surname) values (1,	'jozek@tester.pl',	'$2a$10$XE8G1kkrzy6e8bxnrIph3.BgNcl1dLLxSQVuIqFTlNQBe.K6Wq3.u',	'juser', '2000-01-01',	'Jozef', 'Testowy') on conflict do nothing;

insert into user_roles (user_id, role_id) values (
    1, 1
) on conflict do nothing;