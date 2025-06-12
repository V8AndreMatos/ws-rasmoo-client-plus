package com.client.ws.rasmooplus.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionTypeDto {

    private long id;

    @NotBlank(message = "campo name não pode ser nulo ou vazio")
    @Size(min = 5 , max = 30)
    private String name;

    @Max(value = 12, message = "campo acessMonth não pode ser maior que 12")
    private Long accessMonths;

    @NotNull(message = "campo price não pode ser nulo ")
    private BigDecimal price;

    @NotBlank(message = "campo productKey não pode ser nulo ou vazio")
    @Size(min = 5 , max = 15 , message = "campo produtKey deve ter o tamanho entre 5 e 15")
    private String productKey;
}
