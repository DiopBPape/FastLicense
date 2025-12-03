package its.rizzoli.FastLicense.controller;

import its.rizzoli.FastLicense.DTO.ArgomentiDto;
import its.rizzoli.FastLicense.DTO.CapitoliDto;
import its.rizzoli.FastLicense.DTO.ImmagineDTO;
import its.rizzoli.FastLicense.models.Capitoli;
import its.rizzoli.FastLicense.models.Argomenti;
import its.rizzoli.FastLicense.repositories.ArgomentiRepository;
import its.rizzoli.FastLicense.repositories.CapitoliRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class TeoriaController {

    @Autowired
    CapitoliRepository capitoliRepository;

    @Autowired
    private ArgomentiRepository argomentiRepository;

    @GetMapping("/getCapitoli")
    public ResponseEntity<?> getAllCapitoli() {
        List<CapitoliDto> capitoliDtoList = StreamSupport.stream(capitoliRepository.findAll().spliterator(), false)
                .map(c -> {

                    String image = null;

                    if (!c.getImmagini().isEmpty()) {
                        image = c.getImmagini().get(0).getFileName();
                    }

                    return new CapitoliDto(
                            c.getId(),
                            c.getTitolo(),
                            image
                    );
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(Map.of("capitoli", capitoliDtoList));
    }



    @GetMapping("/getArgomento/{capitoloId}")
    public ResponseEntity<?> getArgomentiByCapitolo(@PathVariable Integer capitoloId) {

        Capitoli capitolo = capitoliRepository.findById(capitoloId).orElse(null);
        if (capitolo == null) {
            return ResponseEntity.status(404).body(Map.of("error", "Capitolo non trovato"));
        }

        List<ArgomentiDto> argomentiDtoList = argomentiRepository.findByCapitolo(capitolo)
                .stream()
                .map(a -> new ArgomentiDto(
                        a.getId(),
                        a.getTitolo(),
                        a.getTesto(),
                        capitolo.getId(),
                        capitolo.getTitolo(),
                        a.getImmagini()
                                .stream()
                                .map(img -> img.getFileName()) // <-- SOLO STRINGA
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());



        return ResponseEntity.ok(Map.of("argomenti", argomentiDtoList));
    }



    @GetMapping("/getArgomentoDettaglio/{capitoloId}/{argomentoId}")
    public ResponseEntity<?> getArgomentoDettaglio(
            @PathVariable Integer capitoloId,
            @PathVariable Integer argomentoId) {

        Capitoli capitolo = capitoliRepository.findById(capitoloId).orElse(null);
        if (capitolo == null) {
            return ResponseEntity.status(404).body(Map.of("error", "Capitolo non trovato"));
        }

        Argomenti argomento = argomentiRepository.findById(argomentoId).orElse(null);
        if (argomento == null || !argomento.getCapitolo().getId().equals(capitoloId)) {
            return ResponseEntity.status(404).body(Map.of("error", "Argomento non trovato in questo capitolo"));
        }

        ArgomentiDto dto = new ArgomentiDto(
                argomento.getId(),
                argomento.getTitolo(),
                argomento.getTesto(),
                capitolo.getId(),
                capitolo.getTitolo(),
                argomento.getImmagini()
                        .stream()
                        .map(img -> img.getFileName())
                        .collect(Collectors.toList())
        );

        return ResponseEntity.ok(Map.of("argomento", dto));
    }

}
