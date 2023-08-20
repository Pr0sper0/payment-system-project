package org.val.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NamedQuery(name = "Account.deleteAllRows", query = "DELETE from Account")
@NamedEntityGraph(name = "AccountWithUserAndCards",
        attributeNodes = {
            @javax.persistence.NamedAttributeNode("user"),
            @javax.persistence.NamedAttributeNode("cards")
        })
@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts", catalog = "postgres")
public class Account {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "currency")
    private String currency;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private User user;

    @ToString.Exclude
    @OneToMany(mappedBy = "account")
    List<Card> cards;
}
