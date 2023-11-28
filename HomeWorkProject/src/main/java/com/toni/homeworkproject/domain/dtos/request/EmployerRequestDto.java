package com.toni.homeworkproject.domain.dtos.request;

import com.toni.homeworkproject.domain.Customer;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployerRequestDto {
    @NotBlank
    @Min(value = 3, message = "Name must be longer then 3")
    private String name;
    @NotBlank
    @Min(value = 3, message = "Address must be longer then 3")
    private String address;
}
