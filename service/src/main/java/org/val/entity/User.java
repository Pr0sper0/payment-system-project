package org.val.entity;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
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
@Table(name="users", schema="payments", catalog="postgres")
@Access(AccessType.FIELD)
public class User {

  @Id
  @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
  private long id;

  @Column(name = "user_id")
  private String userId;

  @Column(name = "name")
  private String name;

  @Column(name = "surname")
  private String surname;

  @Column(name = "birthday")
  private BirthDay birthday;

  @Column(name="email")
  private String email;

  @Column(name="password")
  private String password;

  @Transient
  @Enumerated(EnumType.STRING)
  private Role role;

  @Transient
  @Enumerated(EnumType.STRING)
  private Gender gender;

  @OneToMany(mappedBy = "userId")
  @ToString.Exclude
  private Set<Account> accounts = new HashSet<>();

  @OneToMany(mappedBy = "userId")
  @ToString.Exclude
  private List<Order> orders = new ArrayList<>();

}