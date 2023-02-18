package br.com.brunolutterbach.gerenciamentolivros.listener;

import br.com.brunolutterbach.gerenciamentolivros.model.Book;
import br.com.brunolutterbach.gerenciamentolivros.service.EmailSenderService;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BookListener {

    final EmailSenderService senderService;

    @EventListener
    public void onBookCreated(Book book) {
        senderService.sendEmailIfBookMatchesUserPreferences(book.getTitle(), book.getGenre());
    }
}
