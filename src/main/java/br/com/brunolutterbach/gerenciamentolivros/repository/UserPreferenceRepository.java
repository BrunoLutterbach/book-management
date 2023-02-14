package br.com.brunolutterbach.gerenciamentolivros.repository;

import br.com.brunolutterbach.gerenciamentolivros.model.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserPreferenceRepository extends JpaRepository<UserPreference, Long> {

    @Query("SELECT u.email FROM UserPreference u WHERE u.genre = ?1")
    List<String> findAllEmailsWithGenre(String genre);

    @Query("SELECT u.name FROM UserPreference u WHERE u.email = ?1")
    String getNameByEmail(String email);

    boolean existsByEmail(String email);
}
