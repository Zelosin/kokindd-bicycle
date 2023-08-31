package ru.codeinside.test.entity;

import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Exclude;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.codeinside.test.consts.NoteConsts;

@Entity
@Table(name = "notes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Note {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Include
  private Long id;

  @Include
  @Column(columnDefinition = "TEXT", nullable = false)
  private String data;

  @Exclude
  @Column(columnDefinition = "TIMESTAMP", nullable = false)
  private ZonedDateTime createDateTime;

  @Exclude
  @Column(columnDefinition = "TIMESTAMP", nullable = true)
  private ZonedDateTime updateDateTime;

  @Include
  @Column(columnDefinition = "BIGINT", nullable = false)
  private Long version;

  @PrePersist
  protected void createOperations() {
    this.createDateTime = ZonedDateTime.now(NoteConsts.MSC_ZONE);
    this.version = NoteConsts.INIT_VERSION;
  }

  @PreUpdate
  protected void updateOperations() {
    this.updateDateTime = ZonedDateTime.now(NoteConsts.MSC_ZONE);
    this.version++;
  }
}
