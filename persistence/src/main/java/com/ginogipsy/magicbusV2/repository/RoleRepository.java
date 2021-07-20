package com.ginogipsy.magicbusV2.repository;

import com.ginogipsy.magicbusV2.domain.Profilo;
import com.ginogipsy.magicbusV2.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByProfilo(Profilo profilo);
}
