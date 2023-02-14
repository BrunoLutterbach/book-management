package br.com.brunolutterbach.gerenciamentolivros.service;

import br.com.brunolutterbach.gerenciamentolivros.dto.User.UserPreferenceCreationdata;
import br.com.brunolutterbach.gerenciamentolivros.model.UserPreference;
import br.com.brunolutterbach.gerenciamentolivros.repository.UserPreferenceRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailSenderService {

    final UserPreferenceRepository preferenceRepository;
    final JavaMailSender mailSender;

    public void createUserPreference(UserPreferenceCreationdata userPreferenceCreationdata) {
        if (preferenceRepository.existsByEmail(userPreferenceCreationdata.email()))
            throw new RuntimeException("Email já cadastrado");
        preferenceRepository.save(new UserPreference(userPreferenceCreationdata));
        sendEmail(userPreferenceCreationdata.email(), "Cadastro Book Manager",
                "Olá, " + userPreferenceCreationdata.name() + "! Você acabou de se cadastrar com sucesso!");
    }
    public void sendEmail(String toEmail, String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("brunolutterbach13@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

}
