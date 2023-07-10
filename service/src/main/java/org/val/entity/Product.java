package org.val.entity;

import java.math.BigDecimal;
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

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Builder
@Entity
@Table(name = "products", schema = "payments", catalog = "postgres")
public class Product {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id")
  private int id;
  @Column(name = "product_id")
  private String productId;
  @Column(name = "name")
  private String name;
  @Column(name = "description")
  private String description;
  @Column(name = "price")
  private BigDecimal price;
  @Column(name = "created_at")
  private Timestamp createdAt;
  @Column(name = "updated_at")
  private Timestamp updatedAt;
  @Column(name = "deleted_at")
  private Timestamp deletedAt;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product that = (Product) o;
    return id == that.id && Objects.equals(productId, that.productId)
        && Objects.equals(name, that.name) && Objects.equals(description,
        that.description) && Objects.equals(price, that.price) && Objects.equals(
        createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt)
        && Objects.equals(deletedAt, that.deletedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, productId, name, description, price, createdAt, updatedAt, deletedAt);
  }
}
