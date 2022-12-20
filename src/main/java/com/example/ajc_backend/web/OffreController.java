package com.example.ajc_backend.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.ajc_backend.MessageResponse;
import com.example.ajc_backend.dao.OffreEmploisRepository;
import com.example.ajc_backend.entites.ActivitySectors;
import com.example.ajc_backend.entites.CompetenceOffre;
import com.example.ajc_backend.entites.EntrepriseAccount;
import com.example.ajc_backend.entites.MissionsOffre;
import com.example.ajc_backend.entites.OffreEmplois;
import com.example.ajc_backend.entites.PostulerOffre;
import com.example.ajc_backend.services.interfaces.entreprises.OffreServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@CrossOrigin("*")
public class OffreController {
        @Autowired
        OffreServices offreServices;
        @Autowired
        OffreEmploisRepository offreEmploisRepository;

        public static String fileDirectry = System.getProperty("user.home") + "/Documents/ajc-web-site/image_offre/";

        @PostMapping(path = "/create_offre", consumes = { "multipart/form-data" })
        public MessageResponse create_offre(@RequestPart("offreEmplois") String offreEmplois,
                        @RequestPart("missionsOffres") String missionsOffres,
                        @RequestPart("competenceOffres") String competenceOffres,
                        @RequestPart("image") MultipartFile image)
                        throws JsonMappingException, JsonProcessingException, IOException {
                System.out.print(15252);
                return offreServices.create_offre(
                                new ObjectMapper().readValue(offreEmplois, OffreEmplois.class),
                                new ObjectMapper().readValue(missionsOffres,
                                                new TypeReference<ArrayList<MissionsOffre>>() {
                                                }),
                                new ObjectMapper().readValue(competenceOffres,
                                                new TypeReference<ArrayList<CompetenceOffre>>() {
                                                }),
                                image);
        }

        @PostMapping(path = "/create_withoutImage", consumes = { "multipart/form-data" })
        public MessageResponse create_withoutImage(@RequestPart("offreEmplois") String offreEmplois,
                        @RequestPart("missionsOffres") String missionsOffres,
                        @RequestPart("competenceOffres") String competenceOffres)
                        throws JsonMappingException, JsonProcessingException, IOException {
                return offreServices.create_withoutImage(
                                new ObjectMapper().readValue(offreEmplois, OffreEmplois.class),
                                new ObjectMapper().readValue(missionsOffres,
                                                new TypeReference<ArrayList<MissionsOffre>>() {
                                                }),
                                new ObjectMapper().readValue(competenceOffres,
                                                new TypeReference<ArrayList<CompetenceOffre>>() {
                                                }));
        }

        @PutMapping(path = "/update_offre", consumes = { "multipart/form-data" })
        public MessageResponse update_offre(@RequestPart("offreEmplois") String offreEmplois,
                        @RequestPart("image") MultipartFile image)
                        throws JsonMappingException, JsonProcessingException, IOException {
                return offreServices.update_offre(
                                new ObjectMapper().readValue(offreEmplois, OffreEmplois.class), image);
        }

        @PutMapping(path = "/update_withoutImage", consumes = { "multipart/form-data" })
        public MessageResponse update_withoutImage(@RequestPart("offreEmplois") String offreEmplois)
                        throws JsonMappingException, JsonProcessingException, IOException {
                return offreServices.update_withoutImage(
                                new ObjectMapper().readValue(offreEmplois, OffreEmplois.class));
        }

        @GetMapping(value = "/find_offre/{oid_offre}")
        public Optional<OffreEmplois> find_offre(@PathVariable Long oid_offre) {
                return offreServices.find_offre(oid_offre);
        }

        @GetMapping(path = "/list_offre")
        public List<OffreEmplois> list_offre()
                        throws JsonMappingException, JsonProcessingException, IOException {
                return offreServices.list_offre();
        }

        @GetMapping(path = "/imageOffre/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
        public byte[] imageOffre(@PathVariable(name = "id") Long id) throws Exception {
                OffreEmplois offreEmploi = offreEmploisRepository.findById(id).get();
                String photoname = offreEmploi.getImage();
                // chemin repertoir utilisateur
                File file = new File(fileDirectry + photoname);
                Path path = Paths.get(file.toURI());
                // System.out.println(offreEmploi);
                return Files.readAllBytes(path);
        }

        // @PostMapping(path = "/getImageOffre")
        // void getImageOffre(@RequestBody String filename, HttpServletResponse response) {
        //         offreServices.getImageOffre(filename, response);
        // }

        @PostMapping(path = "/list_similary_offre")
        public List<OffreEmplois> list_similary_offre(@RequestBody ParemSimilaryOffer paremSimilaryOffer)
                        throws JsonMappingException, JsonProcessingException, IOException {
                return offreServices.list_similary_offre(paremSimilaryOffer.getActivitySectors(),
                                paremSimilaryOffer.getPays());
        }

        @PostMapping(path = "/postuler_offre")
        public MessageResponse postuler_offre(@RequestBody PostulerOffre postulerOffre)
                        throws JsonMappingException, JsonProcessingException, IOException {
                return offreServices.postuler_offre(postulerOffre);
        }

        @PostMapping(path = "/list_my_offre")
        public List<OffreEmplois> list_my_offre(@RequestBody EntrepriseAccount entrepriseAccount)
                        throws JsonMappingException, JsonProcessingException, IOException {
                return offreServices.list_my_offre(entrepriseAccount);
        }

        @PutMapping(path = "/supprimer_offre")
        public MessageResponse supprimer_offre(@RequestBody OffreEmplois offreEmplois)
                        throws JsonMappingException, JsonProcessingException, IOException {
                return offreServices.supprimer_offre(offreEmplois);
        }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class ParemSimilaryOffer {
        private ActivitySectors activitySectors;
        private String pays;
}
