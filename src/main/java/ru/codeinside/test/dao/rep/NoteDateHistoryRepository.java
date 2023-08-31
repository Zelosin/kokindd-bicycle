package ru.codeinside.test.dao.rep;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.codeinside.test.entity.NoteDateHistory;

@Repository
public interface NoteDateHistoryRepository extends JpaRepository<NoteDateHistory, Long> {

  @Query("from NoteDateHistory nh where nh.note.id = :noteId and nh.version = :version")
  Optional<NoteDateHistory> findNoteInHistory(@Param("noteId") Long noteId, @Param("version") Long version);

}
