package library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "Название не должно быть пустым")
    @Size(max = 255, message = "Название не должно быть длиннее 255 символов")
    private String name;

    @Column(name = "author")
    @NotBlank(message = "Автор не должен быть пустым")
    @Size(max = 255, message = "Имя автора не должно быть длиннее 255 символов")
    private String author;

    @Column(name = "description")
    @Size(max = 1000, message = "Описание не должно быть длиннее 1000 символов")
    private String description;
}
