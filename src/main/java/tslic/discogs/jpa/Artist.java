package tslic.discogs.jpa;

import java.util.ArrayList;
import java.util.LinkedList;
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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Entity
@Table(name = "artists")
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Artist {

  @Id private int id;

  @Column(columnDefinition = "TEXT")
  private String name;

  @Column(columnDefinition = "TEXT")
  private String realname;

  @Column(columnDefinition = "TEXT")
  private String profile;

  @Column(columnDefinition = "TEXT")
  @XmlAttribute
  private String status;

  @XmlElement(name = "data_quality")
  private String dataQuality;

  @ElementCollection
  @CollectionTable(joinColumns = @JoinColumn(name = "artistId"))
  @Column(name = "nameVariation", columnDefinition = "TEXT")
  @OrderColumn(name = "position")
  @XmlElementWrapper
  @XmlElement(name = "name")
  private List<String> namevariations = new LinkedList<>();

  @ElementCollection
  @CollectionTable(joinColumns = @JoinColumn(name = "artistId"))
  @Column(name = "url", columnDefinition = "TEXT")
  @OrderColumn(name = "position")
  @XmlElementWrapper
  @XmlElement(name = "url")
  private List<String> urls = new LinkedList<>();

  @ElementCollection
  @CollectionTable(joinColumns = @JoinColumn(name = "artistId"))
  @OrderColumn(name = "position")
  @XmlElementWrapper
  @XmlElement(name = "name")
  private List<ArtistAlias> aliases = new LinkedList<>();

  @ElementCollection
  @CollectionTable(joinColumns = @JoinColumn(name = "artistId"))
  @OrderColumn(name = "position")
  @XmlElementWrapper
  @XmlElement(name = "name")
  private List<Group> groups = new LinkedList<>();

  @ElementCollection
  @CollectionTable(joinColumns = @JoinColumn(name = "artistId"))
  @OrderColumn(name = "position")
  @XmlElementWrapper(name = "members")
  @XmlElement(name = "name")
  private List<GroupMember> members = new ArrayList<>();
}
