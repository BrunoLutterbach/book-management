package br.com.brunolutterbach.gerenciamentolivros.dto.review;

import javax.validation.constraints.NotNull;

public record ReviewCreationData(
        @NotNull
        String comment,
        @NotNull
        double rating) {
}
