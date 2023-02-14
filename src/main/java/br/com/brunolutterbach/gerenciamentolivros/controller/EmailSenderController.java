package br.com.brunolutterbach.gerenciamentolivros.controller;

import br.com.brunolutterbach.gerenciamentolivros.dto.User.UserPreferenceCreationdata;
import br.com.brunolutterbach.gerenciamentolivros.service.EmailSenderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class EmailSenderController {

    final EmailSenderService userService;

    @PostMapping()
    @Transactional
    public ResponseEntity<UserPreferenceCreationdata> createUserPreference(@RequestBody @Valid UserPreferenceCreationdata
                                                                                       userPreference) {
        userService.createUserPreference(userPreference);
        return ResponseEntity.ok(userPreference);
    }

}
