package its.rizzoli.FastLicense.controller;

import its.rizzoli.FastLicense.DTO.CapitoliDto;
import its.rizzoli.FastLicense.models.Capitoli;
import its.rizzoli.FastLicense.repositories.CapitoliRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TeoriaController {
    @Autowired
    CapitoliRepository capitoliRepository;

    @GetMapping("/getCapitoli")
    public ResponseEntity<?> getSedi() {
        Iterable<Capitoli> capitoli = capitoliRepository.findAll();
        return ResponseEntity.ok(Map.of("capitoli", capitoli));
    }
}
