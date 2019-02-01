package sda.java17.zgagamacservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sda.java17.zgagamacservice.model.AppUser;
import sda.java17.zgagamacservice.model.Device;
import sda.java17.zgagamacservice.model.dto.AddAppUserDto;
import sda.java17.zgagamacservice.model.dto.FilterAppUsersDto;
import sda.java17.zgagamacservice.model.dto.ModifyAppUsersDto;
import sda.java17.zgagamacservice.repository.AppUserRepository;
import sda.java17.zgagamacservice.repository.DeviceRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {
    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private ServiceTaskService serviceTaskService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Optional<AppUser> register(AddAppUserDto dto) {
        // sprawdzenie czy użytkownik z podanym mailem nie istnieje.
        if (!appUserRepository.findByEmail(dto.getEmail()).isPresent()) {
            AppUser appUser = new AppUser(/*id*/null,
                    dto.getEmail(),
                    dto.getName(),
                    dto.getSurname(),
//                    encoder.encode(dto.getPassword()),
                    dto.getPassword(), //^^ zmienilem na szyfrowane hasło
                    new ArrayList<>(),
                    new HashSet<>());

            appUser.setPassword(encoder.encode(appUser.getPassword()));

            AppUser newAppUser = appUserRepository.save(appUser);

            return Optional.ofNullable(newAppUser);
        }
        return Optional.empty();
    }

    public Optional<AppUser> modify(ModifyAppUsersDto dto) {
        Optional<AppUser> appUserOptional = appUserRepository.findById(dto.getIdToModify());
        if (appUserOptional.isPresent()) {
            AppUser appUser = appUserOptional.get();

            if (dto.getEmail() != null) {
                appUser.setEmail(dto.getEmail());
            }
            if (dto.getName() != null) {
                appUser.setName(dto.getName());
            }
            if (dto.getPassword() != null) {
                appUser.setPassword(dto.getPassword());
            }
            if (dto.getSurname() != null) {
                appUser.setSurname(dto.getSurname());
            }

            appUser = appUserRepository.save(appUser);

            return Optional.of(appUser);
        }
        return Optional.empty();
    }

    public List<AppUser> filter(FilterAppUsersDto dto) {
        // todo: check!
        return appUserRepository.findAllByNameContainingOrEmailContainingOrSurnameContaining(dto.getName(), dto.getEmail(), dto.getSurname());
    }

    public Optional<AppUser> find(Long id) {
        return appUserRepository.findById(id);
    }

    public List<AppUser> getAll() {
        return appUserRepository.findAll();
    }

    public Optional<AppUser> findByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    public void remove(Long id) {
        AppUser user = appUserRepository.getOne(id);
        for (Device d : user.getDeviceList()) {
            deviceRepository.deleteById(d.getId());
        }

        appUserRepository.deleteById(id);
    }
}
