package tslic.discogs.jpa;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import lombok.Data;

@Embeddable
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Company {

  private int id;

  private String name;

  private String catno;

  @XmlElement(name = "entity_type")
  private Integer entityType;

  @XmlElement(name = "entity_type_name")
  private String entityTypeName;

  @XmlElement(name = "resource_url")
  private String resourceUrl;
}
