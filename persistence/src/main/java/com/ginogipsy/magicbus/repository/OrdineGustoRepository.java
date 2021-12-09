package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.TasteOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdineGustoRepository extends JpaRepository<TasteOrder, Integer> {
}
