package com.ginogipsy.magicbusV2.repository;

import com.ginogipsy.magicbusV2.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByProfilo(String profilo);
}
