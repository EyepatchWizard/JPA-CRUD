package com.sazzad.JPA.CRUD.repositories;

import com.sazzad.JPA.CRUD.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {
}
