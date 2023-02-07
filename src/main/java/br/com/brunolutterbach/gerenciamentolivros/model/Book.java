package br.com.brunolutterbach.gerenciamentolivros.model;

import br.com.brunolutterbach.gerenciamentolivros.dto.BookCreationData;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String description;
    private String coverImage;
    private String genre;
    private LocalDateTime postDate = LocalDateTime.now();

    public Book() {
    }

    public Book(BookCreationData bookCreationData) {
        this.title = bookCreationData.title();
        this.author = bookCreationData.author();
        this.description = bookCreationData.description();
        this.coverImage = bookCreationData.coverImage();
        this.genre = bookCreationData.genre();
    }
}
