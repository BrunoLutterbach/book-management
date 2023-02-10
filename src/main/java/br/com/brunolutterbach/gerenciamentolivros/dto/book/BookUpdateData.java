package br.com.brunolutterbach.gerenciamentolivros.dto.book;

public record BookUpdateData(String title, String author, String description, String coverImage, String genre) {
}
