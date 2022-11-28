package com.example.ajc_backend.services.interfaces.entreprises;

import java.util.List;

import com.example.ajc_backend.MessageResponse;
import com.example.ajc_backend.entites.MissionsOffre;
import com.example.ajc_backend.entites.OffreEmplois;

public interface MissionOffreServices {
    MessageResponse delete_mission(MissionsOffre missionsOffre);

    MessageResponse Create_mission(MissionsOffre missionsOffre);

    MessageResponse update_mission(MissionsOffre missionsOffre);

    List<MissionsOffre> list_mission(OffreEmplois offreEmplois);
}
