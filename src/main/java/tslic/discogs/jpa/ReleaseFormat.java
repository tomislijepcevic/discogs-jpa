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
public class ReleaseFormat {

  @Column(columnDefinition = "TEXT")
  @XmlAttribute
  private String name;

  @Column(columnDefinition = "TEXT")
  @XmlAttribute
  private String qty;

  @Column(columnDefinition = "TEXT")
  @XmlAttribute
  private String text;

  //	 Missing array of descriptions

  //	<descriptions>
  //	    <description>12"</description>
  //	    <description>33 ⅓ RPM</description>
  //	</descriptions>
}
