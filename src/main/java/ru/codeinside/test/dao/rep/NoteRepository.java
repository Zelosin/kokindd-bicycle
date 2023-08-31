package ru.codeinside.test.dao.rep;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.codeinside.test.entity.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}