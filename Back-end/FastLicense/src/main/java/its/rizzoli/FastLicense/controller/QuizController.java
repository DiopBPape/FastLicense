package its.rizzoli.FastLicense.controller;

import its.rizzoli.FastLicense.DTO.DomandeDto;
import its.rizzoli.FastLicense.DTO.PunteggioQuizDto;
import its.rizzoli.FastLicense.DTO.QuizEseguitoDto;
import its.rizzoli.FastLicense.models.Domande;
import its.rizzoli.FastLicense.models.PunteggioQuiz;
import its.rizzoli.FastLicense.models.QuizEseguito;
import its.rizzoli.FastLicense.models.User;
import its.rizzoli.FastLicense.repositories.DomandeRepository;
import its.rizzoli.FastLicense.repositories.PunteggioQuizRepository;
import its.rizzoli.FastLicense.repositories.QuizEseguitoRepository;
import its.rizzoli.FastLicense.utility.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;

@RestController
public class QuizController {

    @Autowired
    private DomandeRepository domandeRepository;

    @Autowired
    private QuizEseguitoRepository quizEseguitoRepository;

    @Autowired
    private PunteggioQuizRepository punteggioQuizRepository;

    @Autowired
    private AuthService authService;

    @GetMapping("/getDomande")
    public ResponseEntity<?> getThirtyRandomQuestions() {
        List<Domande> domandeRandom = domandeRepository.findRandom30();

        List<DomandeDto> domandeDtoList = domandeRandom.stream()
                .map(d -> {
                    DomandeDto dto = new DomandeDto();
                    dto.setId(d.getId());
                    dto.setTesto(d.getTesto());
                    dto.setImmagine(d.getImmagine());
                    dto.setRisposta(d.getRisposta());
                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(Map.of("domande", (Object) domandeDtoList));
    }

    @PostMapping("/salvaQuiz")
    public ResponseEntity<?> salvaQuiz(@RequestBody List<QuizEseguitoDto> quizDtoList) {
        User user = authService.getLoggedUser();
        if (user == null) {
            return ResponseEntity.status(401).body(Map.of("error", (Object) "Utente non loggato"));
        }

        List<Map<String, Object>> risultati = new ArrayList<>();

        for (QuizEseguitoDto quizDto : quizDtoList) {
            QuizEseguito quiz = new QuizEseguito();
            quiz.setUser(user);
            quiz.setPunteggio(0);
            quizEseguitoRepository.save(quiz);

            int punteggioTotale = 0;

            List<PunteggioQuizDto> risposteDto = quizDto.getRisposte();
            if (risposteDto != null) {
                for (PunteggioQuizDto rDto : risposteDto) {
                    Domande domanda = domandeRepository.findById(rDto.getDomandaId()).orElse(null);
                    if (domanda != null) {
                        PunteggioQuiz pq = new PunteggioQuiz();
                        pq.setQuiz(quiz);
                        pq.setDomanda(domanda);
                        pq.setRisposta(rDto.getRisposta());
                        punteggioQuizRepository.save(pq);

                        if (rDto.getRisposta().equals(domanda.getRisposta())) {
                            punteggioTotale++;
                        }
                    }
                }
            }

            quiz.setPunteggio(punteggioTotale);
            quizEseguitoRepository.save(quiz);

            Map<String, Object> risultatoQuiz = Map.of(
                    "quizId", (Object) quiz.getId(),
                    "punteggio", (Object) punteggioTotale
            );
            risultati.add(risultatoQuiz);
        }

        return ResponseEntity.ok(Map.of("quizSalvati", (Object) risultati));
    }

    @GetMapping("/getPunteggio")
    public ResponseEntity<?> getPunteggio(@RequestParam(required = false) Integer quizId) {
        User user = authService.getLoggedUser();
        if (user == null) {
            return ResponseEntity.status(401).body(Map.of("error", (Object) "Utente non loggato"));
        }

        if (quizId != null) {
            // Restituisce il punteggio di un singolo quiz
            QuizEseguito quiz = quizEseguitoRepository.findById(quizId).orElse(null);
            if (quiz == null || !quiz.getUser().getId().equals(user.getId())) {
                return ResponseEntity.status(404).body(Map.of("error", (Object) "Quiz non trovato"));
            }
            return ResponseEntity.ok(Map.of(
                    "quizId", (Object) quizId,
                    "punteggio", (Object) quiz.getPunteggio()
            ));
        } else {
            // Restituisce i punteggi distinti per ogni quiz eseguito dall'utente
            List<QuizEseguito> quizList = quizEseguitoRepository.findByUser(user);

            List<Map<String, Object>> punteggi = quizList.stream()
                    .map(q -> Map.of(
                            "quizId", (Object) q.getId(),
                            "punteggio", (Object) q.getPunteggio()
                    ))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(Map.of("punteggiQuiz", (Object) punteggi));
        }
    }



    @GetMapping("/getQuizEseguito")
    public ResponseEntity<?> getQuizEseguito() {
        User user = authService.getLoggedUser();
        if (user == null) {
            return ResponseEntity.status(401).body(Map.of("error", (Object) "Utente non loggato"));
        }

        List<QuizEseguito> quizList = quizEseguitoRepository.findByUser(user);

        List<Map<String, Object>> result = quizList.stream().map(q -> Map.of(
                "quizId", (Object) q.getId(),
                "punteggio", (Object) q.getPunteggio()
        )).collect(Collectors.toList());

        return ResponseEntity.ok(Map.of("quizEseguiti", (Object) result));
    }
}
