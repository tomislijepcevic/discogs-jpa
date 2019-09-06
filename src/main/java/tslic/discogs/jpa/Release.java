package tslic.discogs.jpa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Entity
@Table(name = "releases")
@Data
@EqualsAndHashCode(exclude = {"master"})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Release {

  @Id @XmlAttribute private int id;

  @Column(columnDefinition = "TEXT")
  private String title;

  private String country;

  @Column(columnDefinition = "TEXT")
  private String notes;

  private String released;

  @Column(columnDefinition = "TEXT")
  @XmlAttribute
  private String status;

  @XmlElement(name = "data_quality")
  private String dataQuality;

  @XmlElement(name = "master_id")
  private Integer masterId;

  @ElementCollection
  @CollectionTable(joinColumns = @JoinColumn(name = "releaseId"))
  @Column(name = "genre")
  @OrderColumn(name = "position")
  @XmlElementWrapper
  @XmlElement(name = "genre")
  private List<String> genres = new ArrayList<>();

  @ElementCollection
  @CollectionTable(joinColumns = @JoinColumn(name = "releaseId"))
  @Column(name = "style")
  @OrderColumn(name = "position")
  @XmlElementWrapper
  @XmlElement(name = "style")
  private List<String> styles = new ArrayList<>();

  @ElementCollection
  @CollectionTable(name = "releaseArtistMaps", joinColumns = @JoinColumn(name = "releaseId"))
  @OrderColumn(name = "position")
  @XmlElementWrapper
  @XmlElement(name = "artist")
  private List<ReleaseArtist> artists = new ArrayList<>();

  @ElementCollection
  @CollectionTable(name = "releaseExtraArtistMaps", joinColumns = @JoinColumn(name = "releaseId"))
  @OrderColumn(name = "position")
  @XmlElementWrapper(name = "extraartists")
  @XmlElement(name = "artist")
  private List<ReleaseExtraArtist> extraArtists = new ArrayList<>();

  @ElementCollection
  @CollectionTable(joinColumns = @JoinColumn(name = "releaseId"))
  @OrderColumn(name = "position")
  @XmlElementWrapper
  @XmlElement(name = "format")
  private List<ReleaseFormat> formats = new ArrayList<>();

  @ElementCollection
  @CollectionTable(joinColumns = @JoinColumn(name = "releaseId"))
  @OrderColumn(name = "position")
  @XmlElementWrapper
  @XmlElement(name = "video")
  private List<Video> videos = new ArrayList<>();

  @ElementCollection
  @CollectionTable(joinColumns = @JoinColumn(name = "releaseId"))
  @OrderColumn(name = "position")
  @XmlElementWrapper
  @XmlElement(name = "company")
  private List<Company> companies = new ArrayList<>();

  @ElementCollection
  @CollectionTable(joinColumns = @JoinColumn(name = "releaseId"))
  @OrderColumn(name = "position")
  @XmlElementWrapper
  @XmlElement(name = "label")
  private List<ReleaseLabel> labels = new ArrayList<>();

  @OneToMany(
      mappedBy = "release",
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  @OrderBy("position")
  @XmlElementWrapper(name = "tracklist")
  @XmlElement(name = "track")
  private List<Track> tracks = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(
      name = "masterId",
      insertable = false,
      updatable = false,
      foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  @Setter(AccessLevel.NONE)
  private Master master;
}
