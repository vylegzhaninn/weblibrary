package library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequest {
    
    @NotBlank(message = "Название не должно быть пустым")
    @Size(max = 255, message = "Название не должно быть длиннее 255 символов")
    private String name;
    
    @NotBlank(message = "Автор не должен быть пустым")
    @Size(max = 255, message = "Имя автора не должно быть длиннее 255 символов")
    private String author;
    
    @Size(max = 1000, message = "Описание не должно быть длиннее 1000 символов")
    private String description;
}
