package sda.java17.zgagamacservice.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sda.java17.zgagamacservice.model.AppUser;
import sda.java17.zgagamacservice.model.dto.AddAppUserDto;
import sda.java17.zgagamacservice.model.dto.FilterAppUsersDto;
import sda.java17.zgagamacservice.model.dto.ModifyAppUsersDto;
import sda.java17.zgagamacservice.service.AppUserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appuser/")
public class AppUserController {
    @Autowired
    private AppUserService appuserService;

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody AddAppUserDto dto) {
        Optional<AppUser> optionalAppUser = appuserService.register(dto);
        return optionalAppUser.<ResponseEntity>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/modify")
    public ResponseEntity add(@RequestBody ModifyAppUsersDto dto) {
        Optional<AppUser> optionalAppUser = appuserService.modify(dto);
        return optionalAppUser.<ResponseEntity>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/filter/")
    public ResponseEntity filter(@RequestBody FilterAppUsersDto dto) {
        List<AppUser> optionalAppUser = appuserService.filter(dto);
        return ResponseEntity.ok(optionalAppUser);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable(name = "id") Long id) {
        Optional<AppUser> optionalAppUser = appuserService.find(id);
        return optionalAppUser.<ResponseEntity>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        List<AppUser> optionalAppUser = appuserService.getAll();
        return ResponseEntity.ok(optionalAppUser);
    }

}
