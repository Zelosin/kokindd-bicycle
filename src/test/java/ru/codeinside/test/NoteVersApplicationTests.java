package ru.codeinside.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NoteVersApplicationTests {

    @Autowired
    private NoteRepository noteRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void saveAndUpdateTest() {
        var note = Note.builder()
                .data("Lorem ipsum dolor")
                .build();
        noteRepository.save(note);
        Assertions.assertEquals("Lorem ipsum dolor", noteRepository.findById(note.getId()).map(Note::getData).orElse(null));
        note.setData("Lorem ipsum dolor sit amet");
        noteRepository.save(note);
        Assertions.assertEquals("Lorem ipsum dolor sit amet", noteRepository.findById(note.getId()).map(Note::getData).orElse(null));

        // TODO: Get versions and check it
    }

}
