package tslic.discogs.jpa;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Entity
@Table(name = "master_releases")
@Data
@XmlRootElement(name = "master")
@XmlAccessorType(XmlAccessType.FIELD)
public class Master {

  @Id @XmlAttribute private Integer id;

  @XmlElement(name = "main_release")
  private Integer mainReleaseId;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "mainReleaseId",
      insertable = false,
      updatable = false,
      foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  private Release mainRelease;
}
