package ru.codeinside.test.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

@Builder
@RequiredArgsConstructor
public class NoteCreateUpdateDto {

  @Getter
  @NonNull
  private String data;

}
