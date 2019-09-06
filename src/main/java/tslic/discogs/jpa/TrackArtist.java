package tslic.discogs.jpa;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import lombok.Data;

@Embeddable
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class TrackArtist {

  @Column(name = "artistId")
  @XmlAttribute
  private int id;

  @Column(columnDefinition = "TEXT")
  private String name;

  @Column(columnDefinition = "TEXT")
  private String anv;

  @Column(name = "joinRelation", columnDefinition = "TEXT")
  private String join;
}
