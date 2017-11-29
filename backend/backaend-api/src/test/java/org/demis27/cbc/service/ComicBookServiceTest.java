package org.demis27.cbc.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.demis27.cbc.data.entity.ComicBookEntity;
import org.demis27.cbc.data.entity.PersonEntity;
import org.demis27.cbc.data.repository.ComicBookRepository;
import org.demis27.cbc.data.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
public class ComicBookServiceTest {

    @Mock
    private ComicBookRepository comicBookRepository;

    @Mock
    private PersonRepository personRepository;

    @Test
    public void test_authors() throws Exception {
        ComicBookService comicBookService = new ComicBookService();
        PersonService personService = new PersonService();

        ReflectionTestUtils.setField(comicBookService, "repository", comicBookRepository);
        ReflectionTestUtils.setField(personService, "repository", personRepository);


        ComicBookEntity comicBook = new ComicBookEntity();
        comicBook.title = "Batman";
        PersonEntity writer = new PersonEntity();
        writer.lastName = "Bob";
        writer.firstName = "Kane";

        when(personRepository.insert(any(PersonEntity.class))).thenReturn(writer);

        writer = personService.create(writer);
        comicBook.writers.add(writer.getId());

        when(comicBookRepository.insert(any(ComicBookEntity.class))).thenReturn(comicBook);

        ComicBookEntity comicBookEntity = comicBookService.create(comicBook);
        assertNotNull(comicBookEntity);
    }

}
