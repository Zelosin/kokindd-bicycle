package ru.codeinside.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.codeinside.test.dao.NoteDao;
import ru.codeinside.test.dto.NoteCreateUpdateDto;
import ru.codeinside.test.dto.NoteViewDto;
import ru.codeinside.test.entity.Note;
import ru.codeinside.test.util.NoteMapper;

@Service
@RequiredArgsConstructor
public class NoteService implements NoteMapper {

  private final static String NOTE_NOT_FOUND_X = "Note with id = {%s} not found";
  private final static String NOTE_HISTORY_NOT_FOUND_X = "Note history with id = {%s} and version = {%s} not found";

  private final NoteDao noteDao;

  public NoteViewDto createNote(@Validated NoteCreateUpdateDto noteCreateUpdateDto) {
    return mapNote(noteDao.saveNote(Note.builder().data(noteCreateUpdateDto.getData()).build()));
  }

  public NoteViewDto updateNote(@NonNull Long id, @Validated NoteCreateUpdateDto updateNote) {
    var noteForUpdate = noteDao.findLatestVersionOfNote(id).orElseThrow(() ->
        new RuntimeException(NOTE_NOT_FOUND_X.formatted(id))
    );
    return mapNote(noteDao.updateNote(noteForUpdate, updateNote));
  }

  public NoteViewDto findSpecificVersionForNote(Long id, Long version) {
    return mapNote(noteDao.findSpecificVersionOfNote(id, version).orElseThrow(() ->
        new RuntimeException(NOTE_HISTORY_NOT_FOUND_X.formatted(id, version))
    ));
  }
}
