package com.example.ajc_backend.services.implementations.entreprises;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ajc_backend.MessageResponse;
import com.example.ajc_backend.dao.ActivitySectorsRepository;
import com.example.ajc_backend.entites.ActivitySectors;
import com.example.ajc_backend.services.interfaces.entreprises.SettingsService;

@Service
public class SettingsImplement implements SettingsService {

    @Autowired
    ActivitySectorsRepository activitySectorsRepository;

    @Override
    public MessageResponse create_ActivitySector(ActivitySectors activitySectors) {
        if (activitySectorsRepository.findByName(activitySectors.getName()).isPresent()) {
            return new MessageResponse("Ce nom est deja utiliser", false);
        }
        activitySectorsRepository.save(activitySectors);
        return new MessageResponse("Enreigistrement effectuer avec success", true);
    }

    @Override
    public MessageResponse update_ActivitySector(ActivitySectors activitySectors) {
        if (activitySectorsRepository.findByName(activitySectors.getName()).isPresent()) {
            return new MessageResponse("Ce nom est deja utiliser", false);
        }
        Optional<ActivitySectors> get_activitySector = activitySectorsRepository.findById(activitySectors.getOid());
        if (get_activitySector.isPresent()) {
            get_activitySector.get().setName(activitySectors.getName());
            activitySectorsRepository.save(get_activitySector.get());
            return new MessageResponse("Enreigistrement Modifier avec success", true);
        } else {
            return new MessageResponse("C'est element n'existe pas", false);
        }
    }

    @Override
    public List<ActivitySectors> list_ActivitySector() {
        return activitySectorsRepository.findAll();
    }

    @Override
    public List<String> getCountriesListInAlphabetical() {
        List<String> crunchifyList = new ArrayList<String>();
        String[] locales = Locale.getISOCountries();
        for (String countryCode : locales) {
            Locale obj = new Locale("", countryCode);
            crunchifyList.add(obj.getDisplayCountry());
        }
        Collections.sort(crunchifyList);
        // ListIterator<String> crunchifyListIterator = crunchifyList.listIterator();
        // while (crunchifyListIterator.hasNext()) {
        // System.out.println(crunchifyListIterator.next());
        // }
        return crunchifyList;
    }

    // @Override
    // public List<String> getCountriesListInAlphabetical() {
    // List<String> crunchifyList = new ArrayList<String>();
    // String[] locales = Locale.getISOCountries();
    // for (int i=0; i < locales.length; i++) {
    // Locale obj = new Locale("", locales[i]);
    // crunchifyList.add(i, obj.getDisplayCountry());
    // }

    // // sort(): Sorts the specified list into ascending order,
    // // according to the natural ordering of its elements.
    // // All elements in the list must implement the Comparable interface.
    // // Furthermore,
    // // all elements in the list must be mutually comparable
    // // (that is, e1.compareTo(e2) must not throw a ClassCastException for any
    // // elements e1 and e2 in the list).
    // // Collections.sort(crunchifyList);

    // // listIterator(): Returns a list iterator over the elements in this list (in
    // // proper sequence).
    // ListIterator<String> crunchifyListIterator = crunchifyList.listIterator();
    // while (crunchifyListIterator.hasNext()) {

    // // next(): Returns the next element in the list and advances the cursor
    // // position. This method may be called repeatedly to iterate through the
    // list,
    // // or intermixed with calls to previous to go back and forth.
    // // (Note that alternating calls to next and previous will return the same
    // // element repeatedly.)
    // //System.out.println(crunchifyListIterator.next());
    // }
    // return crunchifyList;
    // }

}
