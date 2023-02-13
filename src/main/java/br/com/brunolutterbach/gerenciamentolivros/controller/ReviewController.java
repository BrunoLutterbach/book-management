package br.com.brunolutterbach.gerenciamentolivros.controller;

import br.com.brunolutterbach.gerenciamentolivros.dto.review.ReviewCreationData;
import br.com.brunolutterbach.gerenciamentolivros.dto.review.ReviewResponse;
import br.com.brunolutterbach.gerenciamentolivros.dto.review.ReviewUpdateData;
import br.com.brunolutterbach.gerenciamentolivros.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

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

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<ReviewResponse> createReview(@RequestBody @Valid ReviewCreationData creationData, @PathVariable Long id,
                                                       UriComponentsBuilder builder) {
        var reviewResponse = reviewService.createReview(creationData, id);
        var uri = builder.path("/api/review/{id}").buildAndExpand(reviewResponse.id()).toUri();
        return ResponseEntity.created(uri).body(reviewResponse);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ReviewResponse> updateReview(@RequestBody ReviewUpdateData updateData, @PathVariable Long id) {
        var reviewResponse = reviewService.updateReview(updateData, id);
        return ResponseEntity.ok(reviewResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

}
