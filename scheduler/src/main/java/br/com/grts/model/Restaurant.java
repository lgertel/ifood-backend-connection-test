package br.com.grts.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

  @Id
  @GeneratedValue
  private Long id;

  private String axonId;

  private String name;

  @OneToMany
  private Set<Unavailability> unavailabilities = new HashSet<>();
}
