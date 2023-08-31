package ru.codeinside.test.dao;


import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.codeinside.test.dao.rep.NoteDateHistoryRepository;
import ru.codeinside.test.dao.rep.NoteRepository;
import ru.codeinside.test.dto.NoteCreateUpdateDto;
import ru.codeinside.test.entity.Note;
import ru.codeinside.test.entity.NoteDateHistory;

@Component
@RequiredArgsConstructor
public class NoteDao {

  private final NoteRepository noteRepository;
  private final NoteDateHistoryRepository noteDateHistoryRepository;

  @Transactional(readOnly = true)
  public Optional<Note> findLatestVersionOfNote(Long id) {
    return noteRepository.findById(id);
  }

  public Note saveNote(Note note) {
    return noteRepository.save(note);
  }

  public Note updateNote(Note target, NoteCreateUpdateDto source) {
    var noteDateHistory = NoteDateHistory.builder().data(target.getData()).version(target.getVersion()).note(target);
    target.setData(source.getData());
    var savedNote = saveNote(target);
    noteDateHistoryRepository.save(noteDateHistory.updateDateTime(savedNote.getUpdateDateTime()).build());
    return savedNote;
  }

  @Transactional(readOnly = true)
  public Optional<NoteDateHistory> findSpecificVersionOfNote(Long noteId, Long version) {
    return noteDateHistoryRepository.findNoteInHistory(noteId, version);
  }
}
