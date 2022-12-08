## Тестовое задание #775.

REST-сервис обработки пользовательских сообщений, с аутентификацией на основе jwt-токенов.

В приложении организованы два эндпойнта:

`POST /api/token` возвращает токен, в случае предоставления корректных пользовательских данных.

`POST api/messages` принимает на вход сообщение. Если сообщение содержит команду **history 10**, выводятся послдение 10 пользовательских сообщений.
В ином случае сообщение, после проверки токена, сохраняется в бд.

### Запрос токена
В бд заведены 2 пользователя: `user` и `superUser`, с паролем `password`.

Пример запроса:
```bash
curl --location --request POST 'localhost:8082/api/token' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name":"user",
    "password": "password"
}'
```
Тело запроса токена содержит имя пользователя и пароль:

```json
{
  "name": "user",
  "password": "password"
}
```
В ответ приходит json-строка с токеном, если пользователь и пароль существуют в базе, или сообщение об ошибке.

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjcwNTY2MDg1fQ.emipJXEL_R4sWlJwJz9Z0dT9RGFznBHvUigHBa1vyLI"
}
```
### Запрос сообщений
Запрос списка сообщений:
```bash
curl --location --request POST 'localhost:8082/api/messages' \
--header 'Authorization: Bearer_eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjcwNTY2MDg1fQ.emipJXEL_R4sWlJwJz9Z0dT9RGFznBHvUigHBa1vyLI' \
--header 'Content-Type: application/json' \
--data-raw '{
        "name": "user",
        "message": "history 10"
}'
```
Запрос на сохранение сообщения:
```bash
curl --location --request POST 'localhost:8082/api/messages' \
--header 'Authorization: Bearer_eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjcwNTY2MDg1fQ.emipJXEL_R4sWlJwJz9Z0dT9RGFznBHvUigHBa1vyLI' \
--header 'Content-Type: application/json' \
--data-raw '{
        "name": "user",
        "message": "Новое сообщение"
}'
```

## Настройка приложения
Настройки приложения находятся в файле application.yml.

В качестве базы данных указана in-memory бд H2.

Порт приложения: 8082.

В случае необходимости, базу данных можно подключить любую: первичная инициализация
базы происходит с помощью инструмента версионирования бд liquibase.
Скрипты создания таблиц и заполнение тестовыми данными в `/resources/db`.

## Тесты
К сожалению, опыта написания тестов у меня нет,
и я не стал оставлять свои "недопопытки" что-то протестировать те же контроллеры в коде.

Каждый раз попадал на проекты, где тестирование не использовалось изначально.
В своей самоподготовке тесты каждый раз откладывал "на потом"... зря, наверное.

## Запуск
### Docker
Для сборки docker-контейнера, в терминале из корня проекта выполняем (могут потребоваться права суперпользователя)
```bash
docker build -t task755:0.1.0 .
```
Для запуска:
```bash
docker run -d -p 8082:8082 -t task755:0.1.0
```