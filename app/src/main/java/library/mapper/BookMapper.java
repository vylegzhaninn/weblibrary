package library.mapper;

import library.dto.BookDTO;
import library.dto.CreateBookRequest;
import library.dto.UpdateBookRequest;
import library.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookDTO toDTO(Book book) {
        if (book == null) {
            return null;
        }
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setName(book.getName());
        dto.setAuthor(book.getAuthor());
        dto.setDescription(book.getDescription());
        dto.setCreatedAt(book.getCreatedAt());
        dto.setUpdatedAt(book.getUpdatedAt());
        return dto;
    }

    public Book toEntity(CreateBookRequest request) {
        if (request == null) {
            return null;
        }
        Book book = new Book();
        book.setName(request.getName());
        book.setAuthor(request.getAuthor());
        book.setDescription(request.getDescription());
        return book;
    }

    public void updateEntity(Book book, UpdateBookRequest request) {
        if (request == null || book == null) {
            return;
        }
        if (request.getName() != null) {
            book.setName(request.getName());
        }
        if (request.getAuthor() != null) {
            book.setAuthor(request.getAuthor());
        }
        if (request.getDescription() != null) {
            book.setDescription(request.getDescription());
        }
    }
}
