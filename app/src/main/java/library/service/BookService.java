package library.service;

import library.exception.BookNotFoundException;
import library.model.Book;
import library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> findAll() {
        log.debug("Fetching all books");
        return bookRepository.findAll();
    }

    public Page<Book> findAll(Pageable pageable) {
        log.debug("Fetching books page");
        return bookRepository.findAll(pageable);
    }

    public Book findOne(Long id) {
        log.debug("Fetching book with id: {}", id);
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
    }

    @Transactional
    public Book save(Book book) {
        log.info("Saving new book: {}", book.getName());
        return bookRepository.save(book);
    }

    @Transactional
    public Book update(Long id, Book updatedBook) {
        log.info("Updating book with id: {}", id);
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book not found with id: " + id);
        }
        updatedBook.setId(id);
        return bookRepository.save(updatedBook);
    }

    @Transactional
    public void delete(Long id) {
        log.info("Deleting book with id: {}", id);
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }
}
