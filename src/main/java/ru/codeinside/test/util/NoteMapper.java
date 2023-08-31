package ru.codeinside.test.util;

import ru.codeinside.test.dto.NoteViewDto;
import ru.codeinside.test.entity.Note;
import ru.codeinside.test.entity.NoteDateHistory;

public interface NoteMapper {

  default NoteViewDto mapNote(Note note){
    return NoteViewDto.builder()
        .data(note.getData())
        .id(note.getId())
        .version(note.getVersion())
        .createDateTime(note.getCreateDateTime())
        .updateDateTime(note.getUpdateDateTime())
        .build();
  }

  default NoteViewDto mapNote(NoteDateHistory note){
    return NoteViewDto.builder()
        .data(note.getData())
        .id(note.getId())
        .version(note.getVersion())
        .createDateTime(note.getNote().getCreateDateTime())
        .updateDateTime(note.getUpdateDateTime())
        .build();
  }

}
