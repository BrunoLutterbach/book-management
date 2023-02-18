package br.com.brunolutterbach.gerenciamentolivros.model;

import br.com.brunolutterbach.gerenciamentolivros.dto.book.BookCreationData;
import br.com.brunolutterbach.gerenciamentolivros.dto.book.BookUpdateData;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "book", orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    public Book() {
    }

    public Book(BookCreationData bookCreationData) {
        this.title = bookCreationData.title();
        this.author = bookCreationData.author();
        this.description = bookCreationData.description();
        this.coverImage = bookCreationData.coverImage();
        this.genre = bookCreationData.genre();
    }

    public void update(BookUpdateData bookUpdateData) {
        if (bookUpdateData.title() != null) {
            this.title = bookUpdateData.title();
        }
        if (bookUpdateData.author() != null) {
            this.author = bookUpdateData.author();
        }
        if (bookUpdateData.description() != null) {
            this.description = bookUpdateData.description();
        }
        if (bookUpdateData.coverImage() != null) {
            this.coverImage = bookUpdateData.coverImage();
        }
        if (bookUpdateData.genre() != null) {
            this.genre = bookUpdateData.genre();
        }
    }
}
