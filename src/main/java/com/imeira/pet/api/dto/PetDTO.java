package com.imeira.pet.api.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PetDTO implements Serializable {

  private BigInteger id;

  private String name;

  private BigInteger clientId;

}
