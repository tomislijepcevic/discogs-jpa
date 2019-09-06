package tslic.discogs.jpa;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackId implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer releaseId;

  private Integer offset;
}
