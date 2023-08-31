package ru.codeinside.test.dto;

import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@EqualsAndHashCode
public class NoteViewDto {

  private Long id;

  private Long version;

  private String data;

  private ZonedDateTime createDateTime;

  private ZonedDateTime updateDateTime;

}
