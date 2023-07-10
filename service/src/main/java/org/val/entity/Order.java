package org.val.entity;

import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Table(name = "orders", schema = "payments", catalog = "postgres")
public class Order {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id")
  private int id;
  @Column(name = "order_id")
  private String orderId;

  @Column(name = "user_id")
  private String userId;
  @Column(name = "product_id")
  private String productId;
  @Column(name = "status")
  private String status;
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
    Order that = (Order) o;
    return id == that.id && Objects.equals(orderId, that.orderId)
        && Objects.equals(userId, that.userId) && Objects.equals(productId,
        that.productId) && Objects.equals(status, that.status) && Objects.equals(
        createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt)
        && Objects.equals(deletedAt, that.deletedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, orderId, userId, productId, status, createdAt, updatedAt, deletedAt);
  }
}
