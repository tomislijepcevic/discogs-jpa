package tslic.discogs;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import tslic.discogs.jpa.Artist;
import tslic.discogs.jpa.Label;
import tslic.discogs.jpa.Master;
import tslic.discogs.jpa.Release;
import tslic.discogs.jpa.Track;

public class Main {

  public static void main(String[] args) throws IOException {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("discogs");
    EntityManager entityManager = emf.createEntityManager();

    for (String arg : args) {
      if (arg.contains("artists")) {
        try (InputStream is = openStream(arg)) {
          insertArtists(DumpRecords.readArtists(is), entityManager);
        }
      } else if (arg.contains("releases")) {
        try (InputStream is = openStream(arg)) {
          insertReleases(DumpRecords.readReleases(is), entityManager);
        }
      } else if (arg.contains("masters")) {
        try (InputStream is = openStream(arg)) {
          insertMasters(DumpRecords.readMasters(is), entityManager);
        }
      } else if (arg.contains("labels")) {
        try (InputStream is = openStream(arg)) {
          insertLabels(DumpRecords.readLabels(is), entityManager);
        }
      }
    }

    emf.close();
  }

  private static InputStream openStream(String arg) throws IOException {
    final InputStream is;

    if (arg.startsWith("http")) {
      is = new URL(arg).openStream();
    } else {
      is = new FileInputStream(arg);
    }

    return arg.endsWith(".gz") ? new GZIPInputStream(is) : is;
  }

  private static void insertArtists(Iterator<Artist> artists, EntityManager entityManager) {
    insertItems(artists, entityManager);
  }

  private static void insertReleases(Iterator<Release> releases, EntityManager entityManager) {
    Iterator<Release> modifiedReleases =
        new Iterator<Release>() {
          @Override
          public boolean hasNext() {
            return releases.hasNext();
          }

          @Override
          public Release next() {
            Release release = releases.next();

            int offset = 0;
            for (Track track : release.getTracks()) {
              track.setReleaseId(release.getId());
              track.setOffset(offset++);
            }

            return release;
          }
        };

    insertItems(modifiedReleases, entityManager);
  }

  private static void insertMasters(Iterator<Master> masters, EntityManager entityManager) {
    insertItems(masters, entityManager);
  }

  private static void insertLabels(Iterator<Label> labels, EntityManager entityManager) {
    insertItems(labels, entityManager);
  }

  private static <T> void insertItems(Iterator<T> iterator, EntityManager entityManager) {
    EntityTransaction tx = entityManager.getTransaction();
    tx.begin();
    int count = 0;
    while (iterator.hasNext()) {
      entityManager.persist(iterator.next());
      count++;
      if (count % 25 == 0) {
        entityManager.flush();
        entityManager.clear();
        tx.commit();
        tx.begin();
      }
    }
    tx.commit();
  }
}
