package br.com.brunolutterbach.gerenciamentolivros.dto.book;

import br.com.brunolutterbach.gerenciamentolivros.model.Book;

public record BookSummaryDTO(Long id, String title, String author, String description, String coverImage, String genre) {
    public BookSummaryDTO(Book book) {
        this(book.getId(), book.getTitle(), book.getAuthor(), book.getDescription(), book.getCoverImage(), book.getGenre());
    }
}
