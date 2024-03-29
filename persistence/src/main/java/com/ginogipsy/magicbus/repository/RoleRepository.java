package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.enums.ProfileEnum;
import com.ginogipsy.magicbus.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author ginogipsy
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByProfileEnum(final ProfileEnum profileEnum);

}
