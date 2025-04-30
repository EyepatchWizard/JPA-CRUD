package com.sazzad.JPA.CRUD;

import com.sazzad.JPA.CRUD.domain.Author;
import com.sazzad.JPA.CRUD.domain.Book;

public class TestDataUtil {

    private TestDataUtil(){

    }

    public static Author createTestAuthor() {
        return Author.builder()
                .id(1L)
                .name("Abigail Rose")
                .age(80)
                .build();
    }

    public static Book createTestBook() {
        return Book.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow In The Attic")
                .authorId(1L)
                .build();
    }
}
