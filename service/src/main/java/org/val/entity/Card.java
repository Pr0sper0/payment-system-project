package org.val.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "cards", catalog = "postgres")
public class Card {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "card_number")
  private String cardNumber;
  @Column(name = "cvv")
  private String cvv;
  @Column(name = "expiration_date")
  private LocalDate expirationDate;
  @Column(name = "created_at")
  private LocalDateTime createdAt;
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;
  @Column(name = "deleted_at")
  private LocalDateTime deletedAt;

  @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
  @JoinColumn(name = "account_id")
  private Account account;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Card card = (Card) o;

    if (id != card.id) {
      return false;
    }
    if (!cardNumber.equals(card.cardNumber)) {
      return false;
    }
    if (!cvv.equals(card.cvv)) {
      return false;
    }
    if (!expirationDate.equals(card.expirationDate)) {
      return false;
    }
    if (!createdAt.equals(card.createdAt)) {
      return false;
    }
    if (!Objects.equals(updatedAt, card.updatedAt)) {
      return false;
    }
    if (!Objects.equals(deletedAt, card.deletedAt)) {
      return false;
    }
    return account.equals(card.account);
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + cardNumber.hashCode();
    result = 31 * result + cvv.hashCode();
    result = 31 * result + expirationDate.hashCode();
    result = 31 * result + createdAt.hashCode();
    result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
    result = 31 * result + (deletedAt != null ? deletedAt.hashCode() : 0);
    result = 31 * result + account.hashCode();
    return result;
  }
}
