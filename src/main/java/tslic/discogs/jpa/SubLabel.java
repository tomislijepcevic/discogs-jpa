package tslic.discogs.jpa;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;
import lombok.Data;

@Embeddable
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class SubLabel {

  @Column(name = "sublabelId")
  @XmlAttribute
  private int id;

  @Column(name = "sublabelName")
  @XmlValue
  private String name;
}
