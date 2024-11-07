package com.primetech.primetech_store.auth.application.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.primetech.primetech_store.common.StringDeserializer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDTO {

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @JsonDeserialize(using = StringDeserializer.class)
    private String email;

    @NotBlank(message = "Password is mandatory")
    @JsonDeserialize(using = StringDeserializer.class)
    private String password;

    @NotBlank(message = "First name is mandatory")
    @JsonDeserialize(using = StringDeserializer.class)
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "First name must contain only letters and spaces")
    @Size(max = 50, message = "First name can have a maximum of 50 characters")
    private String firstName;

    @JsonDeserialize(using = StringDeserializer.class)
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Middle name must contain only letters and spaces")
    @Size(max = 50, message = "Middle name can have a maximum of 50 characters")
    private String middleName;

    @NotBlank(message = "Paternal surname is mandatory")
    @JsonDeserialize(using = StringDeserializer.class)
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Paternal surname must contain only letters and spaces")
    @Size(max = 50, message = "Paternal surname can have a maximum of 50 characters")
    private String paternalSurname;

    @NotBlank(message = "Maternal surname is mandatory")
    @JsonDeserialize(using = StringDeserializer.class)
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Maternal surname must contain only letters and spaces")
    @Size(max = 50, message = "Maternal surname can have a maximum of 50 characters")
    private String maternalSurname;
}

