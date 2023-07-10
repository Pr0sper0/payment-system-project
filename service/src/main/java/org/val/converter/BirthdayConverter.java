package org.val.converter;


import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.val.entity.BirthDay;


@Converter(autoApply = true)
public class BirthdayConverter implements AttributeConverter<BirthDay, Date> {

  @Override
  public Date convertToDatabaseColumn(BirthDay attribute) {
    return Optional.ofNullable(attribute)
        .map(BirthDay::birthDate)
        .map(Date::valueOf)
        .orElse(null);
  }

  @Override
  public BirthDay convertToEntityAttribute(Date dbData) {
    return Optional.ofNullable(dbData)
        .map(Date::toLocalDate)
        .map(BirthDay::new)
        .orElse(null);
  }

  public LocalDate calculateDateBetweenDates(LocalDate begin, LocalDate end) {
    return end.minusYears(begin.getYear()).minusMonths(begin.getMonthValue())
        .minusDays(begin.getDayOfMonth());
  }

  public Integer calculateDaysBetweenDates(LocalDate begin, LocalDate end) {
    return (int) (end.toEpochDay() - begin.toEpochDay());
  }

}

