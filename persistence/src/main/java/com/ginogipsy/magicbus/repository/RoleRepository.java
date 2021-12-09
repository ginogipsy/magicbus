package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.enums.Profile;
import com.ginogipsy.magicbus.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByProfilo(Profile profile);

}
