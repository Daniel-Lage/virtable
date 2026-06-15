package edu.ifal.virtable.dto;

public record CreateRequest<T>(T item, String token) {

}
