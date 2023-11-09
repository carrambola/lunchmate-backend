# Projekt grupowy

## Uruchamianie bazy danych

- wejdź do folderu z kodem źródłowym
- wykonaj na terminalu następującą komendę `docker run -e POSTGRES_PASSWORD=123456 -e POSTGRES_DB=launchmate -p 5432:5432 postgres:15`
- to spowoduje ze zostanie uruchomiona baza danych postgres na porcie 5432, z baza danych launchmate

## Pierwsze uruchamianie aplikacji
- Uruchom aplikację (będzie działać na adresie http://localhost:8080)

## Zakladanie sobie uzytkownika
- na terminalu wykonaj polecenie: `curl -X POST http://localhost:8080/api/auth/signup -H 'Content-Type: application/json' -d '{"username":"XXXXX","password":"YYYYYY","email":"ZZZZ@ZZZZ.ZZZ","role":["ROLE_ADMIN"]}'
- zamien w tym poleceniu wartosci XXXXX, YYYYY, ZZZZZ na swoje: tzn. nazwe uzytkownika, haslo i adres e-mail

## Testowanie logowania
- `curl -X POST http://localhost:8080/api/auth/signin -H 'Content-Type: application/json' -d '{"username":"ola","password":"123456"}'`
- tu zwraca token
- do kazdego zkolejnego zadania trzeba dodac do headera: `Authorization: Bearer TOKEN`

## Przykładowe dane do logowania

- User: `tester`
- Hasło: `123456`