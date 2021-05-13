package com.imeira.pet.api.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigInteger;

@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class Pet implements Serializable {

  @Id
  private BigInteger id;

  private String name;

  @DBRef
  private Client client;

}
