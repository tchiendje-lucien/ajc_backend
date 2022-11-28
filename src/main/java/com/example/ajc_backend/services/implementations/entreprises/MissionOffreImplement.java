package com.example.ajc_backend.services.implementations.entreprises;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ajc_backend.MessageResponse;
import com.example.ajc_backend.dao.MissionsOffreRepository;
import com.example.ajc_backend.entites.MissionsOffre;
import com.example.ajc_backend.entites.OffreEmplois;
import com.example.ajc_backend.services.interfaces.entreprises.MissionOffreServices;

@Service
public class MissionOffreImplement implements MissionOffreServices {

    @Autowired
    MissionsOffreRepository missionsOffreRepository;

    @Override
    public MessageResponse delete_mission(MissionsOffre missionsOffre) {
        missionsOffreRepository.deleteById(missionsOffre.getOid());
        return new MessageResponse("La mission a été supprimer avec success", true);
    }

    @Override
    public MessageResponse Create_mission(MissionsOffre missionsOffre) {
        missionsOffreRepository.save(missionsOffre);
        return new MessageResponse("La mission creer avec success", true);
    }

    @Override
    public MessageResponse update_mission(MissionsOffre missionsOffre) {
        Optional<MissionsOffre> get_mission = missionsOffreRepository.findById(missionsOffre.getOid());
        if (get_mission.isPresent()) {
            get_mission.get().setName(missionsOffre.getName());
            missionsOffreRepository.save(get_mission.get());
            return new MessageResponse("Mission modifiée avec success", true);
        } else {
            return new MessageResponse("La missionre cherchée n'existe pas", false);
        }
    }

    @Override
    public List<MissionsOffre> list_mission(OffreEmplois offreEmplois) {
        return missionsOffreRepository.findByOffreEmplois(offreEmplois);
    }

}
