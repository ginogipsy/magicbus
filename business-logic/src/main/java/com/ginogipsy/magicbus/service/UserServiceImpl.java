package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.component.UserUtility;
import com.ginogipsy.magicbus.customexception.user.*;
import com.ginogipsy.magicbus.dto.RoleDTO;
import com.ginogipsy.magicbus.dto.UserDTO;
import com.ginogipsy.magicbus.marshall.MapperFactory;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Optional.*;

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
        if(mapperFactory.getUserMapper().findByUsernameOrEmail(user.getUsername(), user.getEmail()) == null){
            if(user.getFiscalCode() != null && mapperFactory.getUserMapper().findByFiscalCode(user.getFiscalCode()) != null){
                user.setFiscalCode(null);
            }

            if(mapperFactory.getUserMapper().findByCellNumber(user.getCellNumber()) != null){
                throw new CellPhoneIsPresentException("il numero è già presente");
            }

            user.setPassword(user.getPassword());
            Set<RoleDTO> role = new HashSet<>();
            RoleDTO roleDTO = mapperFactory.getRoleMapper().findByProfile("USER");
            role.add(roleDTO);
            user.setRoles(role);
            user.setIsEnabled(true);
            return mapperFactory.getUserMapper().save(user);
        }
        throw new UsernameOrEmailArePresent("Username o email già presenti");
    }



    @Override
    public UserDTO addAddress(final UserDTO oldUser, final String address, final String houseNumber, final String city, final String postalCode){
        oldUser.setAddress(stringUtility.formatWithFirstMaiusc(address));
        oldUser.setHouseNumber(houseNumber);
        oldUser.setCity(stringUtility.formatWithFirstMaiusc(city));
        if(stringUtility.checkPostalCode(postalCode)){
            oldUser.setPostalCode(postalCode);
        }else{
            throw new CapNotCorrectException("il cap non è corretto!");
        }

        return mapperFactory.getUserMapper().save(oldUser);
    }

    @Override
    public UserDTO addNameAndSurname(final UserDTO oldUser, final String name, final String surname){
        oldUser.setName(stringUtility.formatWithFirstMaiusc(name));
        oldUser.setSurname(stringUtility.formatWithFirstMaiusc(surname));
        return mapperFactory.getUserMapper().save(oldUser);
    }

    @Override
    public UserDTO updateUser(final UserDTO oldUser, final UserDTO updatedUser){

        final UserDTO user = userUtility.reformatUserDTO(updatedUser);

        modificaCredenziali(oldUser, user);
        ofNullable(user.getName()).ifPresent(oldUser::setName);
        ofNullable(user.getSurname()).ifPresent(oldUser::setSurname);
        ofNullable(user.getAddress()).ifPresent(oldUser::setAddress);
        ofNullable(user.getHouseNumber()).ifPresent(oldUser::setHouseNumber);
        ofNullable(user.getCity()).ifPresent(oldUser::setCity);
        ofNullable(user.getPostalCode()).ifPresent(cap -> {
            if(stringUtility.checkPostalCode(cap)) {
                oldUser.setPostalCode(cap);
            }
        });

      return mapperFactory.getUserMapper().save(oldUser);
    }

    @Override
    public UserDTO updateEmail(final UserDTO oldUser, final String newEmail) {
            privateUpdateEmail(oldUser, newEmail);
            return mapperFactory.getUserMapper().save(oldUser);
    }

    @Override
    public UserDTO addFiscalCode(final UserDTO oldUser, final String newFiscalCode) {
        if (stringUtility.checkFiscalCode(newFiscalCode)) {
            privateUpdateFiscalCode(oldUser, newFiscalCode);
            return mapperFactory.getUserMapper().save(oldUser);
        }

        throw new CodiceFiscaleNotCorrectException("Codice fiscale non corretto");
    }

    @Override
    public UserDTO updateUsername(final UserDTO oldUser, final String username){
        privateUpdateUsername(oldUser, username);
        return mapperFactory.getUserMapper().save(oldUser);
    }

    @Override
    public UserDTO updateCellNumber(final UserDTO oldUser, final String newCellNumber) {
        privateUpdateNumCell(oldUser, newCellNumber);
        return mapperFactory.getUserMapper().save(oldUser);
    }

    private void modificaCredenziali(final UserDTO oldUser,final UserDTO updatedUser){

        of(updatedUser.getEmail()).ifPresent(email -> privateUpdateEmail(oldUser, email));
        of(updatedUser.getUsername()).ifPresent(username -> privateUpdateUsername(oldUser, username));
        of(updatedUser.getCellNumber()).ifPresent(cellNumb -> privateUpdateNumCell(oldUser, cellNumb));
        ofNullable(updatedUser.getFiscalCode()).ifPresent(fiscalCode -> {
            if (stringUtility.checkFiscalCode(fiscalCode)) {
                privateUpdateFiscalCode(oldUser, fiscalCode);
            }else {
                throw new CodiceFiscaleNotCorrectException("Codice fiscale non corretto");
            }
        });

    }

    private void privateUpdateEmail(final UserDTO oldUser, final String newEmail){
        if (mapperFactory.getUserMapper().findUserByEmail(newEmail) != null) {
            throw new EmailIsPresentException("La nuova mail è già presente!");
        }
        oldUser.setEmail(newEmail.toLowerCase().trim());
    }

    private void privateUpdateUsername(final UserDTO oldUser, final String username){
        if(mapperFactory.getUserMapper().findUserByUsername(username) != null){
            throw new UsernameIsPresentException("il nuovo username è già presente!");
        }
        oldUser.setUsername(username.toLowerCase().trim());
    }

    private void privateUpdateNumCell(final UserDTO oldUser, final String newCellNumber){

        if(mapperFactory.getUserMapper().findByCellNumber(newCellNumber) != null){
            throw new CellPhoneIsPresentException("il nuovo numero di cellulare è già presente!");
        }

        oldUser.setCellNumber(newCellNumber);
    }

    private void privateUpdateFiscalCode(UserDTO oldUser, final String newFiscalCode) {
        UserDTO userOfNewCodiceFiscale = mapperFactory.getUserMapper().findByFiscalCode(newFiscalCode);

        if (userOfNewCodiceFiscale != null && !oldUser.getEmail().equals(userOfNewCodiceFiscale.getEmail())) {
            throw new CodiceFiscaleIsPresentException("Codice Fiscale " + newFiscalCode + " già presente");
        }
            oldUser.setFiscalCode(newFiscalCode.toUpperCase().trim());
    }
}
