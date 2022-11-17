package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.component.UserUtility;
import com.ginogipsy.magicbus.exceptionhandler.MagicbusException;
import com.ginogipsy.magicbus.dto.RoleDTO;
import com.ginogipsy.magicbus.dto.UserDTO;
import com.ginogipsy.magicbus.marshall.MapperFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.ginogipsy.magicbus.exceptionhandler.BeErrorCodeEnum.*;
import static java.util.Optional.*;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final MapperFactory mapperFactory;
    private final StringUtility stringUtility;
    private final UserUtility userUtility;

    public UserServiceImpl(MapperFactory mapperFactory, StringUtility stringUtility, UserUtility userUtility) {
        this.mapperFactory = mapperFactory;
        this.stringUtility = stringUtility;
        this.userUtility = userUtility;
    }

    @Override
    public UserDTO signUpUser(final UserDTO userDTO) {
        final UserDTO user = userUtility.reformatUserDTO(userDTO);
        log.info("Creation new user - START");
        if (mapperFactory.getUserMapper().findByUsernameOrEmail(user.getUsername(), user.getEmail()).size() == 0) {
            if (user.getFiscalCode() != null && mapperFactory.getUserMapper().findByFiscalCode(user.getFiscalCode()) != null) {
                log.warn("Fiscal Code " + user.getFiscalCode() + " is already present in DB!");
                user.setFiscalCode(null);
            }

            if (mapperFactory.getUserMapper().findByCellNumber(user.getCellNumber()) != null) {
                log.error("Number " + user.getCellNumber() + " is already present in DB!");
                throw new MagicbusException(PHONE_NUMBER_IS_PRESENT, "Number " + user.getCellNumber() + " is already present in DB!");
            }

            user.setPassword(user.getPassword());
            Set<RoleDTO> role = new HashSet<>();
            RoleDTO roleDTO = mapperFactory.getRoleMapper().findByProfile("USER");
            role.add(roleDTO);
            user.setRoles(role);
            user.setIsEnabled(true);
            log.info("Creation new user - FINISH");
            return mapperFactory.getUserMapper().save(user);
        }
        log.error("Email " + user.getEmail() + " or username " + user.getUsername() + " are already present in DB!");
        throw new MagicbusException(USERNAME_OR_EMAIL_ARE_PRESENT,"Email " + user.getEmail() + " or username " + user.getUsername() + " are already present!");
    }


    @Override
    public UserDTO addAddress(final UserDTO oldUser, final String address, final String houseNumber, final String city, final String postalCode) {
        log.info("Add address - START");
        Optional.ofNullable(address).ifPresent(add -> oldUser.setAddress(stringUtility.formatAllMinusc(add)));
        Optional.ofNullable(houseNumber).ifPresent(hn -> oldUser.setHouseNumber(stringUtility.formatAllMinusc(hn)));
        Optional.ofNullable(city).ifPresent(c -> oldUser.setCity(stringUtility.formatAllMinusc(city)));
        if (postalCode != null) {
            oldUser.setPostalCode(Optional.of(postalCode)
                    .filter(stringUtility::checkPostalCode)
                    .orElseThrow(() -> new MagicbusException(CAP_NOT_CORRECT)));
            log.error("CAP " + postalCode + " not correct!");
        }
        log.info("Add address - FINISH");
        return mapperFactory.getUserMapper().save(oldUser);
    }

    @Override
    public UserDTO addNameAndSurname(final UserDTO oldUser, final String name, final String surname) {
        log.info("Add name and surname - START");
        oldUser.setName(stringUtility.formatWithFirstMaiusc(name));
        oldUser.setSurname(stringUtility.formatWithFirstMaiusc(surname));
        log.info("Add name and surname - FINISH");
        return mapperFactory.getUserMapper().save(oldUser);
    }

    @Override
    public UserDTO updateUser(final UserDTO oldUser, final UserDTO updatedUser) {
        log.info("Update user - START");
        final UserDTO user = userUtility.reformatUserDTO(updatedUser);

        modificaCredenziali(oldUser, user);
        ofNullable(user.getName()).ifPresent(oldUser::setName);
        ofNullable(user.getSurname()).ifPresent(oldUser::setSurname);
        ofNullable(user.getAddress()).ifPresent(oldUser::setAddress);
        ofNullable(user.getHouseNumber()).ifPresent(oldUser::setHouseNumber);
        ofNullable(user.getCity()).ifPresent(oldUser::setCity);
        ofNullable(user.getPostalCode()).ifPresent(cap -> {
            if (stringUtility.checkPostalCode(cap)) {
                oldUser.setPostalCode(cap);
            }
        });
        log.info("Update user - FINISH");
        return mapperFactory.getUserMapper().save(oldUser);
    }

    @Override
    public UserDTO updateEmail(final UserDTO oldUser, final String newEmail) {
        log.info("Update email - START");
        privateUpdateEmail(oldUser, newEmail);
        log.info("Update email - FINISH");
        return mapperFactory.getUserMapper().save(oldUser);
    }

    @Override
    public UserDTO addFiscalCode(final UserDTO oldUser, final String newFiscalCode) {
        log.info("Add fiscal code - START");
        if (stringUtility.checkFiscalCode(newFiscalCode)) {
            privateUpdateFiscalCode(oldUser, newFiscalCode);
            log.info("Add fiscal code - FINISH");
            return mapperFactory.getUserMapper().save(oldUser);
        }
        log.error("fiscal code "+newFiscalCode+" is not correct!");
        throw new MagicbusException(FISCAL_CODE_NOT_CORRECT);
    }

    @Override
    public UserDTO updateUsername(final UserDTO oldUser, final String username) {
        log.info("Update username - START");
        privateUpdateUsername(oldUser, username);
        log.info("Update username - FINISH");
        return mapperFactory.getUserMapper().save(oldUser);
    }

    @Override
    public UserDTO updateCellNumber(final UserDTO oldUser, final String newCellNumber) {
        log.info("Update cell number - START");
        privateUpdateNumCell(oldUser, newCellNumber);
        log.info("Update cell number - FINISH");
        return mapperFactory.getUserMapper().save(oldUser);
    }

    @Override
    public UserDTO findByEmail(String email) {
        return mapperFactory.getUserMapper().findUserByEmail(email);
    }

    private void modificaCredenziali(final UserDTO oldUser, final UserDTO updatedUser) {
        log.info("Update credentials - START");
        of(updatedUser.getEmail()).ifPresent(email -> privateUpdateEmail(oldUser, email));
        of(updatedUser.getUsername()).ifPresent(username -> privateUpdateUsername(oldUser, username));
        of(updatedUser.getCellNumber()).ifPresent(cellNumb -> privateUpdateNumCell(oldUser, cellNumb));
        ofNullable(updatedUser.getFiscalCode()).ifPresent(fiscalCode -> {
            if (stringUtility.checkFiscalCode(fiscalCode)) {
                privateUpdateFiscalCode(oldUser, fiscalCode);
            } else {
                log.error("Fiscal code not correct!");
                throw new MagicbusException(FISCAL_CODE_NOT_CORRECT);
            }
        });
        log.info("Update credentials - FINISH");
    }

    private void privateUpdateEmail(final UserDTO oldUser, final String newEmail) {
        log.info("Check new email");
        if (!oldUser.getEmail().equals(newEmail) && mapperFactory.getUserMapper().findUserByEmail(newEmail) != null) {
            log.error("New mail is already present in DB!");
            throw new MagicbusException(EMAIL_IS_PRESENT);
        }
        log.info("Set email to user");
        oldUser.setEmail(newEmail.toLowerCase().trim());
    }

    private void privateUpdateUsername(final UserDTO oldUser, final String username) {
        log.info("Check new username");
        if (!oldUser.getUsername().equals(username) && mapperFactory.getUserMapper().findUserByUsername(username) != null) {
            log.error("New username is already present in DB!");
            throw new MagicbusException(USERNAME_IS_PRESENT);
        }
        log.info("Set username to user");
        oldUser.setUsername(username.toLowerCase().trim());
    }

    private void privateUpdateNumCell(final UserDTO oldUser, final String newCellNumber) {
        log.info("Check new phone number");
        if (!oldUser.getCellNumber().equals(newCellNumber) && mapperFactory.getUserMapper().findByCellNumber(newCellNumber) != null) {
            log.error("New phone number is already present in DB!");
            throw new MagicbusException(PHONE_NUMBER_IS_PRESENT);
        }
        log.info("Set cell number to user");
        oldUser.setCellNumber(newCellNumber);
    }

    private void privateUpdateFiscalCode(UserDTO oldUser, final String newFiscalCode) {
        log.info("Check new fiscal code");
        UserDTO userOfNewCodiceFiscale = mapperFactory.getUserMapper().findByFiscalCode(newFiscalCode);

        if (userOfNewCodiceFiscale != null && !oldUser.getEmail().equals(userOfNewCodiceFiscale.getEmail())) {
            log.error("New fiscal code is already present in DB!");
            throw new MagicbusException(FISCAL_CODE_IS_PRESENT);
        }
        log.info("Set fiscal code to user");
        oldUser.setFiscalCode(newFiscalCode.toUpperCase().trim());
    }
}
