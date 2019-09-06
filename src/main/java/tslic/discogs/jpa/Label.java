package tslic.discogs.jpa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Entity
@Table(name = "labels")
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Label {

  @Id private int id;

  private String name;

  @Column(columnDefinition = "TEXT")
  private String contactinfo;

  @Column(columnDefinition = "TEXT")
  private String profile;

  @XmlElement(name = "data_quality")
  private String dataQuality;

  @ElementCollection
  @CollectionTable(joinColumns = @JoinColumn(name = "labelId"))
  @Column(name = "url", columnDefinition = "TEXT")
  @OrderColumn(name = "position")
  @XmlElementWrapper
  @XmlElement(name = "url")
  private List<String> urls = new ArrayList<>();

  @ElementCollection
  @CollectionTable(joinColumns = @JoinColumn(name = "labelId"))
  @OrderColumn(name = "position")
  @XmlElementWrapper
  @XmlElement(name = "label")
  private List<SubLabel> sublabels = new ArrayList<>();
}
