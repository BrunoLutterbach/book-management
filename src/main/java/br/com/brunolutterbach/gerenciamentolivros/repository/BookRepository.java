package br.com.brunolutterbach.gerenciamentolivros.repository;

import br.com.brunolutterbach.gerenciamentolivros.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
