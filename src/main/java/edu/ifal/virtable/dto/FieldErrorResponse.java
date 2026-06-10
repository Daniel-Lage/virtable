package edu.ifal.virtable.dto;

import java.util.List;
import java.util.Map;

public record FieldErrorResponse(
                String error, Map<String, List<String>> fields) {
}