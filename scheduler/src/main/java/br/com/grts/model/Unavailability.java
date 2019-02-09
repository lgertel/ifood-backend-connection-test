package br.com.grts.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Unavailability {

  @Id
  @GeneratedValue
  private Long id;

  private String axonId;

  private LocalDateTime start;
  private LocalDateTime end;
  private LocalDateTime createdAt;
}
