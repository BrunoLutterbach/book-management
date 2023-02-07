package br.com.brunolutterbach.gerenciamentolivros.dto;

import com.sun.istack.NotNull;

public record BookCreationData(
        @NotNull
        String title,
        @NotNull
        String author,
        @NotNull
        String description,
        @NotNull
        String coverImage,
        @NotNull
        String genre) {
}
