package edu.ifal.virtable.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record UpdateRequest<T>(@Valid @NotNull(message = "Não deve ser nulo") T value) {

}
