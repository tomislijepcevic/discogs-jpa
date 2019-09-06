package tslic.discogs;

import java.io.InputStream;
import java.util.Iterator;
import tslic.discogs.jpa.Artist;
import tslic.discogs.jpa.Label;
import tslic.discogs.jpa.Master;
import tslic.discogs.jpa.Release;

public class DumpRecords {

  public static Iterator<Artist> readArtists(InputStream inputStream) {
    return new DumpRecordIterator<>(inputStream, Artist.class);
  }

  public static Iterator<Release> readReleases(InputStream inputStream) {
    return new DumpRecordIterator<>(inputStream, Release.class);
  }

  public static Iterator<Master> readMasters(InputStream inputStream) {
    return new DumpRecordIterator<>(inputStream, Master.class);
  }

  public static Iterator<Label> readLabels(InputStream inputStream) {
    return new DumpRecordIterator<>(inputStream, Label.class);
  }
}
