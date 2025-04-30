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

    private AuthorRepository undertest;

    @Autowired

    public AuthorRepositoryIntegrationTests(AuthorRepository undertest) {
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
    public void TestThatManyAuthorCanBeCreatedAndRecalled(){

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
}
