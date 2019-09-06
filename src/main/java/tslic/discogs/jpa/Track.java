package tslic.discogs.jpa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "tracks")
@IdClass(TrackId.class)
@Data
@EqualsAndHashCode(exclude = {"release"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Track {

  @Id @XmlTransient private Integer releaseId;

  @Id
  @XmlTransient
  @Column(name = "ofst")
  private Integer offset;

  @Column(columnDefinition = "TEXT")
  private String title;

  private String duration;

  @XmlElement(name = "position")
  private String positionStr;

  @ElementCollection
  @CollectionTable(
      name = "track_artist_maps",
      joinColumns = {
        @JoinColumn(name = "releaseId", referencedColumnName = "releaseId"),
        @JoinColumn(name = "trackOfst", referencedColumnName = "ofst")
      })
  @OrderColumn(name = "artistOfst")
  @XmlElementWrapper
  @XmlElement(name = "artist")
  private List<TrackArtist> artists = new ArrayList<>();

  @ElementCollection
  @CollectionTable(
      name = "track_extra_artist_maps",
      joinColumns = {
        @JoinColumn(name = "releaseId", referencedColumnName = "releaseId"),
        @JoinColumn(name = "trackOfst", referencedColumnName = "ofst")
      })
  @OrderColumn(name = "artistOfst")
  @XmlElementWrapper(name = "extraartists")
  @XmlElement(name = "artist")
  private List<TrackExtraArtist> extraArtists = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "releaseId",
      insertable = false,
      updatable = false,
      foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  private Release release;
}
