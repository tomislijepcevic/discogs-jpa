package tslic.discogs.jpa;

import java.io.Serializable;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

/** https://www.baeldung.com/hibernate-naming-strategy */
public class PhysicalNamingStrategyImpl extends PhysicalNamingStrategyStandardImpl
    implements Serializable {

  @Override
  public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment jdbcEnv) {
    return convertToSnakeCase(identifier);
  }

  @Override
  public Identifier toPhysicalColumnName(Identifier identifier, JdbcEnvironment jdbcEnv) {
    return convertToSnakeCase(identifier);
  }

  private Identifier convertToSnakeCase(Identifier identifier) {
    String regex = "([a-z])([A-Z])";
    String replacement = "$1_$2";
    String newName = identifier.getText().replaceAll(regex, replacement).toLowerCase();

    return Identifier.toIdentifier(newName);
  }
}
