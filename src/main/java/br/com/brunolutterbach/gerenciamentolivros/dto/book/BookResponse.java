package br.com.brunolutterbach.gerenciamentolivros.dto.book;

import br.com.brunolutterbach.gerenciamentolivros.model.Book;

import java.util.Optional;

public record BookResponse(Long id, String title, String author, String description, String coverImage, String genre, String postDate) {
    public BookResponse(Book book) {
        this(book.getId(), book.getTitle(), book.getAuthor(), book.getDescription(), book.getCoverImage(), book.getGenre(), book.getPostDate().toString());
    }
}
