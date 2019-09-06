package tslic.discogs.jpa;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import lombok.Data;

@Embeddable
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Video {

  @XmlAttribute private String src;

  @XmlAttribute private String duration;

  @XmlAttribute private Boolean embed;

  private String title;

  private String description;
}
