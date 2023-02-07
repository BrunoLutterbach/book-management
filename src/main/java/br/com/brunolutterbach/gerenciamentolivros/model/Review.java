package br.com.brunolutterbach.gerenciamentolivros.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    private double rating;
    private LocalDateTime reviewDate = LocalDateTime.now();
    private LocalDateTime updateDate;

}
