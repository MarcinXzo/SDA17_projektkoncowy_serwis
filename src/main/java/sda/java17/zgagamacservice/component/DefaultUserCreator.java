package sda.java17.zgagamacservice.component;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import sda.java17.zgagamacservice.model.AppUser;
import sda.java17.zgagamacservice.model.dto.AddAppUserDto;
import sda.java17.zgagamacservice.service.AppUserService;

import java.util.Optional;

@Component
@Lazy
public class DefaultUserCreator {

    @Autowired
    public DefaultUserCreator(AppUserService appuserService) {
        Optional<AppUser> appUserOptional = appuserService.findByEmail("admin@localhost");
        if (!appUserOptional.isPresent()) {
            appuserService.register(
                    new AddAppUserDto(
                            "admin@localhost",
                            "-",
                            "-",
                            "admin"));
        }
    }
}
