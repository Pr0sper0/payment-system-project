package org.val.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

//public record BirthDay(LocalDate birthDate){
//  public long getAge() {
//    return ChronoUnit.YEARS.between(birthDate, LocalDate.now());
//  }
//}
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class BirthDay {

  public LocalDate birthDate() {
    return birthDate;
  }

  private LocalDate birthDate;

  // Default constructor for JPA
  public BirthDay() {
  }

  public BirthDay(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public long getAge() {
    return ChronoUnit.YEARS.between(birthDate, LocalDate.now());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BirthDay birthDay = (BirthDay) o;
    return Objects.equals(birthDate, birthDay.birthDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(birthDate);
  }

  @Override
  public String toString() {
    return "BirthDay{" +
            "birthDate=" + birthDate +
            '}';
  }
}
