package com.DigitalLibrary.DigitalLibrary.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateAuthorDto {
  @NotNull(message = "Author Id should not be null")
    private Integer authorId;
  @NotBlank(message = "Author Address should be null or Blank")
    private String address;
}
