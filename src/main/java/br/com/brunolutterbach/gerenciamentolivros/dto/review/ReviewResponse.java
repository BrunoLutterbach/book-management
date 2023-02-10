package br.com.brunolutterbach.gerenciamentolivros.dto.review;

import br.com.brunolutterbach.gerenciamentolivros.dto.book.BookSummaryDTO;
import br.com.brunolutterbach.gerenciamentolivros.model.Review;

public record ReviewResponse(String id, String comment, double rating, String reviewDate, String updateDate,
                             BookSummaryDTO bookSummaryDTO) {

    public ReviewResponse(Review review) {
        this(review.getId().toString(), review.getComment(), review.getRating(), review.getReviewDate().toString(),
                review.getUpdateDate().toString(), new BookSummaryDTO(review.getBook()));
    }
}
