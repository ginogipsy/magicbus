package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.WineOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdineVinoRepository extends JpaRepository<WineOrder, Integer> {
}
