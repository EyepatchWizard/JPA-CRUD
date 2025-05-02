package com.sazzad.JPA.CRUD.repositories;

import com.sazzad.JPA.CRUD.TestDataUtil;
import com.sazzad.JPA.CRUD.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorRepositoryIntegrationTests {

    private final AuthorRepository undertest;

    @Autowired
    public AuthorRepositoryIntegrationTests(final AuthorRepository undertest) {
        this.undertest = undertest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.createTestAuthorA();
        undertest.save(author);
        Optional<Author> result = undertest.findById(author.getId());
        assertThat(result).isEmpty();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatManyAuthorCanBeCreatedAndRecalled(){

        Author authorA = TestDataUtil.createTestAuthorA();
        undertest.save(authorA);

        Author authorB = TestDataUtil.createTestAuthorB();
        undertest.save(authorB);

        Author authorC = TestDataUtil.createTestAuthorC();
        undertest.save(authorC);

        Iterable<Author> result = undertest.findAll();
        assertThat(result)
                .hasSize(3)
                .containsExactly(authorA, authorB, authorC);
    }

    @Test
    public void testThatAuthorCanBeUpdated(){

        Author author = TestDataUtil.createTestAuthorA();
        undertest.save(author);

        author.setName("updated");
        undertest.save(author);

        Optional<Author> result = undertest.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatAuthorCanBeDeleted(){

        Author author = TestDataUtil.createTestAuthorA();
        undertest.save(author);
        undertest.deleteById(author.getId());

        Optional<Author> result = undertest.findById(author.getId());
        assertThat(result).isEmpty();

    }

    @Test
    public void testThatGetAuthorsWithAgeLessThan() {

        Author authorA = TestDataUtil.createTestAuthorA();
        undertest.save(authorA);

        Author authorB = TestDataUtil.createTestAuthorB();
        undertest.save(authorB);

        Author authorC = TestDataUtil.createTestAuthorC();
        undertest.save(authorC);

        Iterable<Author> result = undertest.ageLessThan(50);
        assertThat(result).containsExactly(authorB, authorC);
    }

    @Test
    public void testThatGetAuthorsWithAgeGreaterThan() {

        Author authorA = TestDataUtil.createTestAuthorA();
        undertest.save(authorA);

        Author authorB = TestDataUtil.createTestAuthorB();
        undertest.save(authorB);

        Author authorC = TestDataUtil.createTestAuthorC();
        undertest.save(authorC);

        Iterable<Author> result = undertest.findAuthorWithAgeGreaterThan(40);
        assertThat(result).containsExactly(authorA, authorB);

    }
}
