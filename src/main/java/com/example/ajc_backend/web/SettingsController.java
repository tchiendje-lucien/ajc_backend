package com.example.ajc_backend.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ajc_backend.MessageResponse;
import com.example.ajc_backend.entites.ActivitySectors;
import com.example.ajc_backend.services.interfaces.entreprises.SettingsService;

@RestController
@CrossOrigin("*")
public class SettingsController {

    @Autowired
    SettingsService settingsService;

    @PostMapping(path = "create_ActivitySector")
    public MessageResponse create_ActivitySector(@RequestBody ActivitySectors activitySectors) {
        return settingsService.create_ActivitySector(activitySectors);
    }

    @PutMapping(path = "update_ActivitySector")
    public MessageResponse update_ActivitySector(@RequestBody ActivitySectors activitySectors) {
        return settingsService.update_ActivitySector(activitySectors);
    }

    @GetMapping(path = "list_ActivitySector")
    public List<ActivitySectors> list_ActivitySector() {
        return settingsService.list_ActivitySector();
    }

    @GetMapping(path = "getCountriesListInAlphabetical")
    public List<String> getCountriesListInAlphabetical() {
        return settingsService.getCountriesListInAlphabetical();
    }

}
