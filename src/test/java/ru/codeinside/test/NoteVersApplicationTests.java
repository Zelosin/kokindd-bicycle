package ru.codeinside.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.codeinside.test.consts.NoteConsts;
import ru.codeinside.test.dto.NoteCreateUpdateDto;
import ru.codeinside.test.service.NoteService;

@SpringBootTest
class NoteVersApplicationTests {

  private final static String INIT_NOTE_DATA = "Lorem ipsum dolor";
  private final static String UPDATED_NOTE_DATA = "Lorem ipsum dolor sit amet";

  @Autowired
  DataSource dataSource;
  @Autowired
  private NoteService noteService;

  @Test
  void contextLoads() {
  }

  @Test
  void saveAndUpdateTest() {

    final var createdNote = noteService.createNote(NoteCreateUpdateDto.builder().data(INIT_NOTE_DATA).build());

    assertEquals(INIT_NOTE_DATA, createdNote.getData());
    assertEquals(NoteConsts.INIT_VERSION, createdNote.getVersion());

    final var updatedNote = noteService.updateNote(createdNote.getId(), NoteCreateUpdateDto.builder().data(UPDATED_NOTE_DATA).build());

    assertEquals(UPDATED_NOTE_DATA, updatedNote.getData());
    assertNotEquals(NoteConsts.INIT_VERSION, updatedNote.getVersion());
    assertNotEquals(createdNote.getVersion(), updatedNote.getVersion());
    assertNotEquals(createdNote, updatedNote);

    var initVersionFromHistory = noteService.findSpecificVersionForNote(NoteConsts.INIT_VERSION, createdNote.getId());

    assertEquals(createdNote.getId(), initVersionFromHistory.getId());
    assertEquals(createdNote.getVersion(), initVersionFromHistory.getVersion());
    assertEquals(createdNote.getData(), initVersionFromHistory.getData());

    assertEquals(createdNote.getId(), initVersionFromHistory.getId());
    assertNotEquals(updatedNote.getVersion(), initVersionFromHistory.getVersion());
    assertNotEquals(updatedNote.getData(), initVersionFromHistory.getData());
  }

}
