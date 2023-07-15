package org.val.mapper;

import java.util.List;
import java.util.Set;

public interface EntityMapper<D, E> {

  E toEntity(D dao);

  D toDao(E entity);

  List<E> toEntity(List<D> daoList);

  List<D> toDao(List<E> entityList);

  Set<D> toDao(Set<E> entityList);
}
