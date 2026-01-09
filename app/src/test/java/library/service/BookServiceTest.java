package library.service;

import library.exception.BookNotFoundException;
import library.model.Book;
import library.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("BookService Tests")
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Book testBook;

    @BeforeEach
    void setUp() {
        testBook = new Book();
        testBook.setId(1L);
        testBook.setName("Test Book");
        testBook.setAuthor("Test Author");
        testBook.setDescription("Test Description");
    }

    @Test
    @DisplayName("Should return all books")
    void findAll_ShouldReturnAllBooks() {
        // Given
        List<Book> expectedBooks = Arrays.asList(testBook, new Book());
        when(bookRepository.findAll()).thenReturn(expectedBooks);

        // When
        List<Book> actualBooks = bookService.findAll();

        // Then
        assertThat(actualBooks).hasSize(2);
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return book by id")
    void findOne_WhenBookExists_ShouldReturnBook() {
        // Given
        when(bookRepository.findById(1L)).thenReturn(Optional.of(testBook));

        // When
        Book foundBook = bookService.findOne(1L);

        // Then
        assertThat(foundBook).isNotNull();
        assertThat(foundBook.getName()).isEqualTo("Test Book");
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should throw exception when book not found")
    void findOne_WhenBookNotExists_ShouldThrowException() {
        // Given
        when(bookRepository.findById(999L)).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> bookService.findOne(999L))
                .isInstanceOf(BookNotFoundException.class)
                .hasMessageContaining("Book not found with id: 999");
    }

    @Test
    @DisplayName("Should save new book")
    void save_ShouldSaveBook() {
        // Given
        when(bookRepository.save(any(Book.class))).thenReturn(testBook);

        // When
        Book savedBook = bookService.save(testBook);

        // Then
        assertThat(savedBook).isNotNull();
        verify(bookRepository, times(1)).save(testBook);
    }

    @Test
    @DisplayName("Should update existing book")
    void update_WhenBookExists_ShouldUpdateBook() {
        // Given
        when(bookRepository.existsById(1L)).thenReturn(true);
        when(bookRepository.save(any(Book.class))).thenReturn(testBook);

        // When
        Book updatedBook = bookService.update(1L, testBook);

        // Then
        assertThat(updatedBook).isNotNull();
        verify(bookRepository, times(1)).save(testBook);
    }

    @Test
    @DisplayName("Should throw exception when updating non-existent book")
    void update_WhenBookNotExists_ShouldThrowException() {
        // Given
        when(bookRepository.existsById(999L)).thenReturn(false);

        // When & Then
        assertThatThrownBy(() -> bookService.update(999L, testBook))
                .isInstanceOf(BookNotFoundException.class);
    }

    @Test
    @DisplayName("Should delete existing book")
    void delete_WhenBookExists_ShouldDeleteBook() {
        // Given
        when(bookRepository.existsById(1L)).thenReturn(true);
        doNothing().when(bookRepository).deleteById(1L);

        // When
        bookService.delete(1L);

        // Then
        verify(bookRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Should throw exception when deleting non-existent book")
    void delete_WhenBookNotExists_ShouldThrowException() {
        // Given
        when(bookRepository.existsById(999L)).thenReturn(false);

        // When & Then
        assertThatThrownBy(() -> bookService.delete(999L))
                .isInstanceOf(BookNotFoundException.class);
    }
}
