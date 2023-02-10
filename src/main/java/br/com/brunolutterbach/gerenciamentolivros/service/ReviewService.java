package br.com.brunolutterbach.gerenciamentolivros.service;

import br.com.brunolutterbach.gerenciamentolivros.dto.review.ReviewResponse;
import br.com.brunolutterbach.gerenciamentolivros.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewService {

    final ReviewRepository repository;

    public ReviewResponse getReviewDetails(Long id) {
        var review = repository.getReferenceById(id);
        return new ReviewResponse(review);
    }

    public Page<ReviewResponse> listAllReviews(Pageable pageable) {
        return repository.findAll(pageable).map(ReviewResponse::new);

    }
}
