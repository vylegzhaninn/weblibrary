# REST API Examples

## Все примеры используют JSON формат

### 1. Получить все книги
```bash
curl -X GET http://localhost:8080/api/books
```

### 2. Получить книги с пагинацией
```bash
# Первая страница, 10 книг
curl -X GET "http://localhost:8080/api/books/paginated?page=0&size=10"

# Вторая страница, сортировка по имени
curl -X GET "http://localhost:8080/api/books/paginated?page=1&size=5&sort=name,asc"
```

### 3. Получить книгу по ID
```bash
curl -X GET http://localhost:8080/api/books/1
```

### 4. Создать новую книгу
```bash
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{
    "name": "1984",
    "author": "George Orwell",
    "description": "Dystopian novel"
  }'
```

### 5. Обновить книгу
```bash
curl -X PUT http://localhost:8080/api/books/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "1984 (Updated)",
    "author": "George Orwell",
    "description": "Classic dystopian novel"
  }'
```

### 6. Удалить книгу
```bash
curl -X DELETE http://localhost:8080/api/books/1
```

## JavaScript/Fetch примеры

### Получить все книги
```javascript
fetch('http://localhost:8080/api/books')
  .then(res => res.json())
  .then(books => console.log(books));
```

### Создать книгу
```javascript
fetch('http://localhost:8080/api/books', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    name: 'Clean Code',
    author: 'Robert Martin',
    description: 'A handbook of agile software craftsmanship'
  })
})
  .then(res => res.json())
  .then(book => console.log('Created:', book));
```

## Swagger UI
Откройте в браузере: http://localhost:8080/swagger-ui.html
