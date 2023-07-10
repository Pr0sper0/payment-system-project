package org.val.entity;

import java.sql.Date;
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
@Table(name = "cards", schema = "payments", catalog = "postgres")
public class Card {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id")
  private int id;
  @Column(name = "card_id")
  private String cardId;
  @Column(name = "account_id")
  private String accountId;
  @Column(name = "card_number")
  private String cardNumber;
  @Column(name = "cvv")
  private String cvv;
  @Column(name = "expiration_date")
  private Date expirationDate;
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
    Card that = (Card) o;
    return id == that.id && Objects.equals(cardId, that.cardId) && Objects.equals(
        accountId, that.accountId) && Objects.equals(cardNumber, that.cardNumber)
        && Objects.equals(cvv, that.cvv) && Objects.equals(expirationDate,
        that.expirationDate) && Objects.equals(createdAt, that.createdAt)
        && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(deletedAt,
        that.deletedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, cardId, accountId, cardNumber, cvv, expirationDate, createdAt,
        updatedAt,
        deletedAt);
  }
}
