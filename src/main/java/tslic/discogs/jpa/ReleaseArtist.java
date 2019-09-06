package tslic.discogs.jpa;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import lombok.Data;

@Embeddable
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ReleaseArtist {

  @Column private int id;

  @Column(columnDefinition = "TEXT")
  private String name;

  @Column(columnDefinition = "TEXT")
  private String anv;

  @Column(name = "joinRelation", columnDefinition = "TEXT")
  private String join;

  @Column(columnDefinition = "TEXT")
  private String tracks;
}
