package org.val.entity;

import java.math.BigDecimal;
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

@Entity
@Table(name = "accounts", schema = "payments", catalog = "postgres")
public class Account {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id")
  private int id;
  @Column(name = "account_id")
  private String accountId;

  @Column(name = "user_id")
  private String userId;
  @Column(name = "balance")
  private BigDecimal balance;
  @Column(name = "currency")
  private String currency;
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
    Account that = (Account) o;
    return id == that.id && Objects.equals(accountId, that.accountId)
        && Objects.equals(userId, that.userId) && Objects.equals(balance,
        that.balance) && Objects.equals(currency, that.currency)
        && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt,
        that.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, accountId, userId, balance, currency, createdAt, updatedAt);
  }
}
