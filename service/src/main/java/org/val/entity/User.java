package org.val.entity;


import java.time.LocalDate;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name="users", catalog="postgres")
@Access(AccessType.FIELD)
public class User {

  @Id
  @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
  private long id;

  @Column(name = "user_name")
  private String userName;

  @Column(name = "name")
  private String name;

  @Column(name = "surname")
  private String surname;

  @Column
  private LocalDate birthday;

  @Column(name="email")
  private String email;

  @Column(name="password")
  private String password;

  @ManyToOne(optional = true, targetEntity = Role.class)
  @JoinColumn(name = "role_id", referencedColumnName = "id")
  private Role role;


  @Enumerated(EnumType.STRING)
  private Gender gender;

  @OneToMany(mappedBy = "user")
  @ToString.Exclude
  private Set<Account> accounts = new HashSet<>();

  @OneToMany(mappedBy = "userId")
  @ToString.Exclude
  private List<Order> orders = new ArrayList<>();

}
