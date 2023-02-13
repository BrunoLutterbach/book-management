package br.com.brunolutterbach.gerenciamentolivros.model;

import br.com.brunolutterbach.gerenciamentolivros.dto.review.ReviewCreationData;
import br.com.brunolutterbach.gerenciamentolivros.dto.review.ReviewUpdateData;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    private double rating;
    private LocalDateTime reviewDate = LocalDateTime.now();
    private LocalDateTime updateDate = LocalDateTime.now();
    @ManyToOne
    private Book book;

    public Review() {
    }

    public Review(ReviewCreationData reviewCreationData, Book book) {
        this.comment = reviewCreationData.comment();
        this.rating = reviewCreationData.rating();
        this.book = book;
    }

    public void update(ReviewUpdateData updateData) {
        if (updateData.comment() != null) {
            this.comment = updateData.comment();
        }
        if (updateData.rating() > 0) {
            this.rating = updateData.rating();
        }
        this.updateDate = LocalDateTime.now();
    }
}
