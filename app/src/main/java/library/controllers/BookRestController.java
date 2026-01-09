package library.controllers;

import jakarta.validation.Valid;
import library.dto.BookDTO;
import library.dto.CreateBookRequest;
import library.dto.UpdateBookRequest;
import library.mapper.BookMapper;
import library.model.Book;
import library.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookRestController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        log.debug("REST API: Getting all books");
        List<BookDTO> books = bookService.findAll().stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(books);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<BookDTO>> getBooksPage(
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        log.debug("REST API: Getting books page");
        Page<BookDTO> booksPage = bookService.findAll(pageable)
                .map(bookMapper::toDTO);
        return ResponseEntity.ok(booksPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        log.debug("REST API: Getting book by id: {}", id);
        Book book = bookService.findOne(id);
        return ResponseEntity.ok(bookMapper.toDTO(book));
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody CreateBookRequest request) {
        log.info("REST API: Creating new book: {}", request.getName());
        Book book = bookMapper.toEntity(request);
        Book savedBook = bookService.save(book);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookMapper.toDTO(savedBook));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(
            @PathVariable Long id,
            @Valid @RequestBody UpdateBookRequest request) {
        log.info("REST API: Updating book with id: {}", id);
        Book book = bookService.findOne(id);
        bookMapper.updateEntity(book, request);
        Book updatedBook = bookService.update(id, book);
        return ResponseEntity.ok(bookMapper.toDTO(updatedBook));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        log.info("REST API: Deleting book with id: {}", id);
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
