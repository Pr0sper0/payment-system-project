package org.val.entity;

import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@Setter
@Getter
@ToString
@Table(name = "roles", schema = "payments", catalog = "postgres")
@NoArgsConstructor
@AllArgsConstructor
public class Role {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private int id;
  @Column(name = "role")
  private String role;
  @Column(name = "description")
  private String description;
  @Column(name = "created_at")
  private Timestamp createdAt;
  @Column(name = "updated_at")
  private Timestamp updatedAt;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Role that = (Role) o;
    return id == that.id && Objects.equals(
        role, that.role) && Objects.equals(description, that.description)
        && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt,
        that.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, role, description, createdAt, updatedAt);
  }
}
