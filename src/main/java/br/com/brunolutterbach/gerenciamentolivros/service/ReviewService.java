package br.com.brunolutterbach.gerenciamentolivros.service;

import br.com.brunolutterbach.gerenciamentolivros.dto.review.ReviewCreationData;
import br.com.brunolutterbach.gerenciamentolivros.dto.review.ReviewResponse;
import br.com.brunolutterbach.gerenciamentolivros.dto.review.ReviewUpdateData;
import br.com.brunolutterbach.gerenciamentolivros.model.Review;
import br.com.brunolutterbach.gerenciamentolivros.repository.BookRepository;
import br.com.brunolutterbach.gerenciamentolivros.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewService {

    final ReviewRepository repository;
    final BookRepository bookRepository;

    public ReviewResponse getReviewDetails(Long id) {
        var review = repository.getReferenceById(id);
        return new ReviewResponse(review);
    }

    public Page<ReviewResponse> listAllReviews(Pageable pageable) {
        return repository.findAll(pageable).map(ReviewResponse::new);

    }

    public ReviewResponse createReview(ReviewCreationData reviewCreationData, Long id) {
        var book = bookRepository.getReferenceById(id);
        var review = new Review(reviewCreationData, book);
        repository.save(review);
        return new ReviewResponse(review);
    }

    public ReviewResponse updateReview(ReviewUpdateData updateData, Long id) {
        var review = repository.getReferenceById(id);
        review.update(updateData);
        repository.save(review);
        return new ReviewResponse(review);
    }

    public void deleteReview(Long id) {
        repository.deleteById(id);
    }
}
