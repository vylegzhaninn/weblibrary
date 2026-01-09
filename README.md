# Library Application

Веб-приложение для управления библиотекой книг на Spring Boot.

## Технологии

- Java 21
- Spring Boot 3.3.0
- Spring Data JPA
- PostgreSQL
- Thymeleaf
- Lombok
- Gradle

## Требования

- JDK 21+
- PostgreSQL 12+
- Gradle 8+

## Установка и запуск

### 1. Настройка базы данных

```sql
CREATE DATABASE library_db;
```

### 2. Конфигурация

Для разработки используйте профиль `dev`:

```bash
./gradlew bootRun --args='--spring.profiles.active=dev'
```

Для production настройте переменные окружения:
- `DATABASE_URL`
- `DATABASE_USERNAME`
- `DATABASE_PASSWORD`

### 3. Запуск приложения

```bash
./gradlew bootRun
```

Приложение будет доступно по адресу: http://localhost:8080/books

## API Endpoints

| Метод | URL | Описание |
|-------|-----|----------|
| GET | /books | Список всех книг |
| GET | /books/new | Форма создания книги |
| POST | /books | Создать книгу |
| GET | /books/{id}/edit | Форма редактирования |
| PATCH | /books/{id} | Обновить книгу |
| DELETE | /books/{id} | Удалить книгу |

## Тестирование

```bash
./gradlew test
```

## Структура проекта

```
src/main/java/library/
├── LibraryApp.java          # Точка входа
├── controllers/             # REST контроллеры
├── exception/               # Обработка исключений
├── model/                   # JPA сущности
├── repository/              # Spring Data репозитории
└── service/                 # Бизнес-логика
```

## Лицензия

MIT
