package com.polarbookshop.catalogservice.domain;

public class BookAlreadyExistsException extends RuntimeException{

    public BookAlreadyExistsException(String isbn) {
        super("A book with ISNB " + isbn + " already exists.");
    }
}
