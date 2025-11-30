package its.rizzoli.FastLicense.controller;

import its.rizzoli.FastLicense.DTO.DomandeDto;
import its.rizzoli.FastLicense.models.Domande;
import its.rizzoli.FastLicense.repositories.DomandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class QuizController {

    @Autowired
    private DomandeRepository domandeRepository;

    @GetMapping("/getDomande")
    public ResponseEntity<?> getThirtyRandomQuestions() {

        List<Domande> domandeRandom = domandeRepository.findRandom30();

        List<DomandeDto> domandeDtoList = domandeRandom.stream()
                .map(d -> {
                    DomandeDto dto = new DomandeDto();
                    dto.setId(d.getId());
                    dto.setTesto(d.getTesto());
                    dto.setImmagine(d.getImmagine());
                    dto.setRisposta(d.getRisposta()); // se NON vuoi mandarla, commenta
                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(Map.of("domande", domandeDtoList));
    }
}
