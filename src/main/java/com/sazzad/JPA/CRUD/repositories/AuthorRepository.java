package com.sazzad.JPA.CRUD.repositories;

import com.sazzad.JPA.CRUD.domain.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
