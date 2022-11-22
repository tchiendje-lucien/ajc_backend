package com.example.ajc_backend.services.interfaces.entreprises;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.ajc_backend.MessageResponse;
import com.example.ajc_backend.entites.ActivitySectors;

@Component
@Service
@CrossOrigin("*")
public interface SettingsService {
    MessageResponse create_ActivitySector(ActivitySectors activitySectors);

    MessageResponse update_ActivitySector(ActivitySectors activitySectors);

    List<ActivitySectors> list_ActivitySector();

    List<String> getCountriesListInAlphabetical();
}
