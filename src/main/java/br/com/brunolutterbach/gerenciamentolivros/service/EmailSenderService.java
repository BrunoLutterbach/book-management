package br.com.brunolutterbach.gerenciamentolivros.service;

import br.com.brunolutterbach.gerenciamentolivros.dto.User.UserPreferenceCreationdata;
import br.com.brunolutterbach.gerenciamentolivros.model.UserPreference;
import br.com.brunolutterbach.gerenciamentolivros.repository.UserPreferenceRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
@AllArgsConstructor
public class EmailSenderService {

    final UserPreferenceRepository preferenceRepository;
    final JavaMailSender mailSender;
    final TemplateEngine templateEngine;

    public void createUserPreference(UserPreferenceCreationdata user) {
        if (preferenceRepository.existsByEmail(user.email()))
            throw new RuntimeException("Email já cadastrado");
        preferenceRepository.save(new UserPreference(user));
        sendRegistrationEmail(user.email(), user.name());
    }

    public void sendRegistrationEmail(String toEmail, String name) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            Context context = new Context();
            context.setVariable("name", name);
            String htmlContent = templateEngine.process("RegistrationEmail.html", context);

            helper.setFrom("brunolutterbach13@gmail.com");
            helper.setTo(toEmail);
            helper.setSubject("Bem-vindo ao Book Manager!");
            helper.setText(htmlContent, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            System.out.println("Erro ao enviar e-mail: " + e.getMessage());
        }
    }

    public void sendEmailIfBookMatchesUserPreferences(String bookTitle, String genre) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            Context context = new Context();
            context.setVariable("bookTitle", bookTitle);
            context.setVariable("genre", genre);
            String htmlContent = templateEngine.process("BookRegistrationEmail.html", context);

            helper.setFrom("brunolutterbach13@gmail.com");
            helper.setSubject("Novo livro cadastrado: " + bookTitle);

            List<String> emails = preferenceRepository.findAllEmailsWithGenre(genre);

            for (String email : emails) {
                helper.setTo(email);
                helper.setText(htmlContent, true);
                mailSender.send(message);
            }
        } catch (MessagingException e) {
            System.out.println("Erro ao enviar e-mail: " + e.getMessage());
        }
    }


}
