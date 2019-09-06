package tslic.discogs.jpa;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class ArtistJoin {

  @Column(name = "id")
  private Integer id;

  @Column(name = "name", columnDefinition = "TEXT")
  private String name;

  @Column(columnDefinition = "TEXT")
  private String anv;

  @Column(name = "joinRelation", columnDefinition = "TEXT")
  private String join;
}
