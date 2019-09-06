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
public class ReleaseLabel {

  @Column(name = "labelId")
  @XmlAttribute
  private int id;

  @Column(name = "labelName")
  @XmlAttribute
  private String name;

  @Column(columnDefinition = "TEXT")
  @XmlAttribute
  private String catno;
}
