package br.com.brunolutterbach.gerenciamentolivros.controller;

import br.com.brunolutterbach.gerenciamentolivros.dto.review.ReviewResponse;
import br.com.brunolutterbach.gerenciamentolivros.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/review")
@AllArgsConstructor
public class ReviewController {

    final ReviewService reviewService;

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> getReviewDetails(@PathVariable Long id) {
        var reviewResponse = reviewService.getReviewDetails(id);
        return ResponseEntity.ok(reviewResponse);
    }

    @GetMapping()
    public ResponseEntity<Page<ReviewResponse>> listAllReviews(Pageable pageable) {
        var reviewResponse = reviewService.listAllReviews(pageable);
        return ResponseEntity.ok(reviewResponse);
    }



}
