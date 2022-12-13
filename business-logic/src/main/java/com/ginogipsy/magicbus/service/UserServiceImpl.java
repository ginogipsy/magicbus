package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.component.UserUtility;
import com.ginogipsy.magicbus.exceptionhandler.BeErrorCodeEnum;
import com.ginogipsy.magicbus.exceptionhandler.MagicbusException;
import com.ginogipsy.magicbus.dto.RoleDTO;
import com.ginogipsy.magicbus.dto.UserDTO;
import com.ginogipsy.magicbus.marshall.MapperFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.ginogipsy.magicbus.exceptionhandler.BeErrorCodeEnum.*;
import static java.util.Optional.*;

/**
 * @author ginogipsy
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final MapperFactory mapperFactory;
    private final StringUtility stringUtility;
    private final UserUtility userUtility;

    @Override
    public UserDTO signUpUser(final UserDTO userDTO) {
        log.info("UserServiceImpl - signUpUser() -> Creation new user - START");
        final UserDTO user = Optional.ofNullable(userDTO).map(userUtility::reformatUserDTO)
                        .orElseThrow(() -> new MagicbusException(USER_NOT_FOUND, "UserDTO is null!"));

        if(!mapperFactory.getUserMapper().findByUsernameOrEmail(user.getUsername(), user.getEmail()).isEmpty()) {
            final String resultString = "Email " + user.getEmail() + " or username " + user.getUsername() + " are already present in DB!";
            log.error("UserServiceImpl - signUpUser() -> {}", resultString);
            throw new MagicbusException(USERNAME_OR_EMAIL_ARE_PRESENT,resultString);
        }

        if(user.getFiscalCode() != null && mapperFactory.getUserMapper().findByFiscalCode(user.getFiscalCode()).isPresent()) {
            log.warn("Fiscal Code " + user.getFiscalCode() + " is already present in DB!");
            user.setFiscalCode(null);
        }

        if (mapperFactory.getUserMapper().findByCellNumber(user.getCellNumber()).isPresent()) {
            final String resultString1 = "Number " + user.getCellNumber() + " is already present in DB!";
            log.error("UserServiceImpl - signUpUser() -> {}", resultString1);
            throw new MagicbusException(PHONE_NUMBER_IS_PRESENT, resultString1);
        }

        user.setPassword(user.getPassword());
        Set<RoleDTO> role = new HashSet<>();
        RoleDTO roleDTO = mapperFactory.getRoleMapper().findByProfile("USER").orElse(null);
        role.add(roleDTO);
        user.setRoles(role);
        user.setIsEnabled(true);
        log.info("UserServiceImpl - signUpUser() -> Creation new user - FINISH");
        return mapperFactory.getUserMapper().save(user)
                .orElseThrow(() -> new MagicbusException(SAVE_FAILED));
    }


    @Override
    public UserDTO addAddress(final UserDTO oldUser, final String address, final String houseNumber, final String city, final String postalCode) {
        log.info("UserServiceImpl - addAddress() -> Add address - START");
        Optional.ofNullable(address).ifPresent(add -> oldUser.setAddress(stringUtility.formatAllLower(add)));
        Optional.ofNullable(houseNumber).ifPresent(hn -> oldUser.setHouseNumber(stringUtility.formatAllLower(hn)));
        Optional.ofNullable(city).ifPresent(c -> oldUser.setCity(stringUtility.formatAllLower(city)));
        if (postalCode != null) {
            oldUser.setPostalCode(Optional.of(postalCode)
                    .filter(stringUtility::checkPostalCode)
                    .orElseThrow(() -> new MagicbusException(CAP_NOT_CORRECT)));
        }
        log.info("UserServiceImpl - addAddress() -> Add address - FINISH");
        return mapperFactory.getUserMapper().save(oldUser)
                .orElseThrow(() -> new MagicbusException(SAVE_FAILED));
    }

    @Override
    public UserDTO addNameAndSurname(final UserDTO oldUser, final String name, final String surname) {
        log.info("UserServiceImpl - addNameAndSurname() -> Add name and surname - START");
        oldUser.setName(stringUtility.formatWithFirstUpper(name));
        oldUser.setSurname(stringUtility.formatWithFirstUpper(surname));
        log.info("UserServiceImpl - addNameAndSurname() -> Add name and surname - FINISH");
        return mapperFactory.getUserMapper().save(oldUser)
                .orElseThrow(() -> new MagicbusException(SAVE_FAILED));
    }

    @Override
    public UserDTO updateUser(final UserDTO oldUser, final UserDTO updatedUser) {
        log.info("UserServiceImpl - updateUser() -> Update user - START");
        final UserDTO user = userUtility.reformatUserDTO(updatedUser);

        updateCredentials(oldUser, user);
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
        log.info("UserServiceImpl - updateUser() -> Update user - FINISH");
        return mapperFactory.getUserMapper().save(oldUser)
                .orElseThrow(() -> new MagicbusException(SAVE_FAILED));
    }

    @Override
    public UserDTO updateEmail(final UserDTO oldUser, final String newEmail) {
        log.info("UserServiceImpl - updateEmail() -> Update email - START");
        privateUpdateEmail(oldUser, newEmail);
        log.info("UserServiceImpl - updateEmail() -> Update email - FINISH");
        return mapperFactory.getUserMapper().save(oldUser)
                .orElseThrow(() -> new MagicbusException(SAVE_FAILED));
    }

    @Override
    public UserDTO addFiscalCode(final UserDTO oldUser, final String newFiscalCode) {
        log.info("UserServiceImpl - addFiscalCode() -> Add fiscal code - START");

        if (!stringUtility.checkFiscalCode(newFiscalCode)) {
            log.error("UserServiceImpl - addFiscalCode() -> fiscal code "+newFiscalCode+" is not correct!");
            throw new MagicbusException(FISCAL_CODE_NOT_CORRECT);
        }

        privateUpdateFiscalCode(oldUser, newFiscalCode);
        log.info("UserServiceImpl - addFiscalCode() -> Add fiscal code - FINISH");
        return mapperFactory.getUserMapper().save(oldUser)
                .orElseThrow(() -> new MagicbusException(SAVE_FAILED));
    }

    @Override
    public UserDTO updateUsername(final UserDTO oldUser, final String username) {
        log.info("UserServiceImpl - updateUsername() -> Update username - START");
        privateUpdateUsername(oldUser, username);
        log.info("UserServiceImpl - updateUsername() -> Update username - FINISH");
        return mapperFactory.getUserMapper().save(oldUser)
                .orElseThrow(() -> new MagicbusException(SAVE_FAILED));
    }

    @Override
    public UserDTO updateCellNumber(final UserDTO oldUser, final String newCellNumber) {
        log.info("UserServiceImpl - updateCellNumber() -> Update cell number - START");
        privateUpdateNumCell(oldUser, newCellNumber);
        log.info("UserServiceImpl - updateCellNumber() -> Update cell number - FINISH");
        return mapperFactory.getUserMapper().save(oldUser)
                .orElseThrow(() -> new MagicbusException(SAVE_FAILED));
    }

    @Override
    public UserDTO findByEmail(final String email) {
        log.info("UserServiceImpl - findByEmail() -> Checking if email name is null..");
        final String usr = Optional.ofNullable(email)
                .map(stringUtility::formatAllLower)
                .orElseThrow(() -> new MagicbusException(BeErrorCodeEnum.NAME_IS_NULL));
        log.info("UserServiceImpl - findByEmail() -> Finding user named '{}'..", usr);
        return mapperFactory.getUserMapper().findUserByEmail(usr)
                .orElseThrow(() -> new MagicbusException(USER_NOT_FOUND));
    }

    @Override
    public Optional<UserDTO> findByUsername(final String username) {
        log.info("UserServiceImpl - findByUsername() -> Checking if username name is null..");
        final String usr = Optional.ofNullable(username)
                .map(stringUtility::formatAllLower)
                .orElseThrow(() -> new MagicbusException(BeErrorCodeEnum.NAME_IS_NULL));
        log.info("UserServiceImpl - findByUsername() -> Finding user named '{}'..", usr);
        return mapperFactory.getUserMapper().findUserByUsername(usr);
    }

    private void updateCredentials(final UserDTO oldUser, final UserDTO updatedUser) {
        log.info("UserServiceImpl - updateCredentials() -> Update credentials - START");
        of(updatedUser.getEmail()).ifPresent(email -> privateUpdateEmail(oldUser, email));
        of(updatedUser.getUsername()).ifPresent(username -> privateUpdateUsername(oldUser, username));
        of(updatedUser.getCellNumber()).ifPresent(cellNumb -> privateUpdateNumCell(oldUser, cellNumb));
        ofNullable(updatedUser.getFiscalCode()).ifPresent(fiscalCode -> {
            if (stringUtility.checkFiscalCode(fiscalCode)) {
                privateUpdateFiscalCode(oldUser, fiscalCode);
            } else {
                log.error("UserServiceImpl - updateCredentials() -> Fiscal code not correct!");
                throw new MagicbusException(FISCAL_CODE_NOT_CORRECT);
            }
        });
        log.info("UserServiceImpl - updateCredentials() -> Update credentials - FINISH");
    }

    private void privateUpdateEmail(final UserDTO oldUser, final String newEmail) {
        final Optional<UserDTO> usrOfNewEmail = mapperFactory.getUserMapper().findUserByUsername(newEmail);
        log.info("UserServiceImpl - privateUpdateEmail() -> Check new email");
        if (usrOfNewEmail.isPresent() && !usrOfNewEmail.get().equals(oldUser)) {
            log.error("UserServiceImpl - privateUpdateEmail() -> New mail is already present in DB!");
            throw new MagicbusException(EMAIL_IS_PRESENT);
        }
        log.info("UserServiceImpl - privateUpdateEmail() -> Set email to user");
        oldUser.setEmail(newEmail.toLowerCase().trim());
    }

    private void privateUpdateUsername(final UserDTO oldUser, final String username) {
        log.info("UserServiceImpl - privateUpdateUsername() -> Check new username");
        final Optional<UserDTO> usrOfNewUsername = mapperFactory.getUserMapper().findUserByUsername(username);

        if (usrOfNewUsername.isPresent() && !usrOfNewUsername.get().equals(oldUser)) {
            log.error("UserServiceImpl - privateUpdateUsername() -> New username is already present in DB!");
            throw new MagicbusException(USERNAME_IS_PRESENT);
        }
        log.info("UserServiceImpl - privateUpdateUsername() -> Set username to user");
        oldUser.setUsername(username.toLowerCase().trim());
    }

    private void privateUpdateNumCell(final UserDTO oldUser, final String newCellNumber) {
        final Optional<UserDTO> usrOfNewCellNumber = mapperFactory.getUserMapper().findByCellNumber(newCellNumber);
        log.info("UserServiceImpl - privateUpdateNumCell() -> Check new phone number");

        if (usrOfNewCellNumber.isPresent() && !usrOfNewCellNumber.get().equals(oldUser)) {
            log.error("UserServiceImpl - privateUpdateNumCell() -> New phone number is already present in DB!");
            throw new MagicbusException(PHONE_NUMBER_IS_PRESENT);
        }

        log.info("UserServiceImpl - privateUpdateNumCell() -> Set cell number to user");
        oldUser.setCellNumber(newCellNumber);
    }

    private void privateUpdateFiscalCode(final UserDTO oldUser, final String newFiscalCode) {
        log.info("UserServiceImpl - privateUpdateFiscalCode() -> Checking if there is another user with this fiscal code..");
        final Optional<UserDTO> userOfNewFiscalCode = mapperFactory.getUserMapper().findByFiscalCode(newFiscalCode);


        if (userOfNewFiscalCode.isPresent() && !userOfNewFiscalCode.get().equals(oldUser)) {
            log.error("UserServiceImpl - privateUpdateFiscalCode() -> New fiscal code is already present in DB!");
            throw new MagicbusException(FISCAL_CODE_IS_PRESENT);
        }
        log.info("UserServiceImpl - privateUpdateFiscalCode() -> Set fiscal code to user");
        oldUser.setFiscalCode(newFiscalCode.toUpperCase().trim());
    }
}
