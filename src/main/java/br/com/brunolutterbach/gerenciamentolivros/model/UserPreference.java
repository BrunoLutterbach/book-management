package br.com.brunolutterbach.gerenciamentolivros.model;

import br.com.brunolutterbach.gerenciamentolivros.dto.User.UserPreferenceCreationdata;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class UserPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String genre;

    public UserPreference(UserPreferenceCreationdata userPreferenceCreationdata) {
        this.name = userPreferenceCreationdata.name();
        this.email = userPreferenceCreationdata.email();
        this.genre = userPreferenceCreationdata.genre();
    }

    public UserPreference() {

    }
}
