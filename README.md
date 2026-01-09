# Library Application

Современное веб-приложение для управления библиотекой книг с REST API на Spring Boot.

## 🚀 Технологии

- **Backend**: Java 21, Spring Boot 3.3.0
- **API**: REST API с JSON (OpenAPI/Swagger)
- **Database**: PostgreSQL + Spring Data JPA
- **Frontend**: Thymeleaf (legacy) + готовность для SPA
- **Build**: Gradle
- **Documentation**: SpringDoc OpenAPI
- **Testing**: JUnit 5, Mockito, AssertJ

## ✨ Возможности

- ✅ **REST API** с JSON форматом данных
- ✅ **CRUD операции** для книг
- ✅ **Пагинация** и сортировка
- ✅ **Валидация** входных данных
- ✅ **Exception handling** с JSON error responses
- ✅ **CORS** настройка для SPA фронтендов
- ✅ **Swagger UI** интерактивная документация API
- ✅ **DTO** pattern для разделения слоёв
- ✅ **Timestamps** (createdAt, updatedAt)
- ✅ **Логирование** с SLF4J
- ✅ **Unit тесты**

## 📋 Требования

- JDK 21+
- PostgreSQL 12+
- Gradle 8+ (или использовать ./gradlew)

## 🔧 Установка и запуск

### 1. Настройка базы данных

```sql
CREATE DATABASE library_db;
```

### 2. Клонирование и запуск

```bash
git clone https://github.com/vylegzhaninn/weblibrary.git
cd weblibrary
./gradlew bootRun
```

### 3. Доступ к приложению

- **REST API**: http://localhost:8080/api/books
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **HTML интерфейс**: http://localhost:8080/books

## 🔌 REST API Endpoints

### Базовый путь: `/api/books`

| Метод | URL | Описание | Body |
|-------|-----|----------|------|
| GET | `/api/books` | Список всех книг | - |
| GET | `/api/books/paginated` | Пагинированный список | ?page=0&size=10 |
| GET | `/api/books/{id}` | Книга по ID | - |
| POST | `/api/books` | Создать книгу | JSON |
| PUT | `/api/books/{id}` | Обновить книгу | JSON |
| DELETE | `/api/books/{id}` | Удалить книгу | - |

### Примеры запросов

```bash
# Получить все книги
curl http://localhost:8080/api/books

# Создать книгу
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{"name":"1984","author":"George Orwell","description":"Dystopian novel"}'

# Пагинация
curl "http://localhost:8080/api/books/paginated?page=0&size=5&sort=name,asc"
```

Больше примеров: [API_EXAMPLES.md](API_EXAMPLES.md)

## 📁 Структура проекта

```
src/main/java/library/
├── LibraryApp.java              # Точка входа
├── config/                       # Конфигурация
│   ├── CorsConfig.java          # CORS для фронтенда
│   └── OpenAPIConfig.java       # Swagger настройки
├── controllers/                  # Контроллеры
│   ├── BookController.java      # HTML (Thymeleaf)
│   └── BookRestController.java  # REST API (JSON)
├── dto/                         # Data Transfer Objects
│   ├── BookDTO.java
│   ├── CreateBookRequest.java
│   ├── UpdateBookRequest.java
│   └── ErrorResponse.java
├── exception/                   # Обработка исключений
│   ├── BookNotFoundException.java
│   └── GlobalExceptionHandler.java
├── mapper/                      # Entity ↔ DTO маппинг
│   └── BookMapper.java
├── model/                       # JPA сущности
│   └── Book.java
├── repository/                  # Spring Data репозитории
│   └── BookRepository.java
└── service/                     # Бизнес-логика
    └── BookService.java
```

## 🧪 Тестирование

```bash
# Все тесты
./gradlew test

# С отчётом
./gradlew test --info
```

## 🌐 Интеграция с фронтендом

Проект готов для интеграции с современными SPA фронтендами:

### React/Vue/Angular пример

```javascript
// Получить книги
const response = await fetch('http://localhost:8080/api/books');
const books = await response.json();

// Создать книгу
await fetch('http://localhost:8080/api/books', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    name: 'Clean Code',
    author: 'Robert Martin',
    description: 'Agile software craftsmanship'
  })
});
```

CORS настроен для `localhost:*` (все порты).

## 📊 Что отличает от учебного проекта

| Критерий | Учебный проект | Этот проект |
|----------|----------------|-------------|
| Архитектура | Монолит + SSR | API-first + готовность для SPA |
| Формат данных | HTML forms | JSON REST API |
| Документация | ❌ | ✅ Swagger/OpenAPI |
| DTO pattern | ❌ | ✅ Разделение слоёв |
| Пагинация | ❌ | ✅ Spring Data Pageable |
| CORS | ❌ | ✅ Настроен |
| Timestamps | ❌ | ✅ createdAt/updatedAt |
| Error handling | HTML страницы | JSON responses |

## 🔜 Дальнейшие улучшения

- [ ] Spring Security + JWT авторизация
- [ ] Flyway миграции БД
- [ ] Docker + docker-compose
- [ ] CI/CD с GitHub Actions
- [ ] Интеграционные тесты с Testcontainers
- [ ] Фронтенд на React/Vue
- [ ] GraphQL endpoint

## 📝 Лицензия

MIT

