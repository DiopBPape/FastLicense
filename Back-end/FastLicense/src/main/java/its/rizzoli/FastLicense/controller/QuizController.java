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

import java.util.HashMap;
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
                    // NON ESPORRE LA RISPOSTA CORRETTA NELL'ENDPOINT PER OTTENERE LE DOMANDE
                    // dto.setRisposta(d.getRisposta());
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
            quiz.setPunteggio(0); // Inizializza a 0, sarà aggiornato
            quizEseguitoRepository.save(quiz);

            int punteggioTotale = 0;

            List<PunteggioQuizDto> risposteDto = quizDto.getRisposte();
            if (risposteDto != null) {
                for (PunteggioQuizDto rDto : risposteDto) {
                    Domande domanda = domandeRepository.findById(rDto.getDomandaId()).orElse(null);
                    if (domanda != null) {
                        // Verifica se la risposta data è corretta
                        boolean isCorretta = rDto.getRisposta().equals(domanda.getRisposta());

                        PunteggioQuiz pq = new PunteggioQuiz();
                        pq.setQuiz(quiz);
                        pq.setDomanda(domanda);
                        // Salva la risposta data dall'utente (True/False)
                        pq.setRisposta(rDto.getRisposta());
                        punteggioQuizRepository.save(pq);

                        if (isCorretta) {
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


    @GetMapping("/getQuizEseguito/{quizId}")
    public ResponseEntity<?> getQuizEseguito(@PathVariable Integer quizId) {

        // Verifica Utente Loggato (401 Unauthorized)
        User user = authService.getLoggedUser();
        if (user == null) {
            return ResponseEntity.status(401).body(Map.of("error", "Utente non loggato"));
        }

        // 1. Recupera il quiz eseguito e verifica l'appartenenza (404 Not Found)
        // Se la relazione @OneToMany in QuizEseguito è LAZY, dovresti usare un
        // findById con JOIN FETCH nel repository per caricare le risposte
        // QuizEseguito quiz = quizEseguitoRepository.findByIdWithRisposte(quizId).orElse(null);
        QuizEseguito quiz = quizEseguitoRepository.findById(quizId).orElse(null);


        // Controllo se il quiz esiste O se l'ID dell'utente loggato non corrisponde
        if (quiz == null || !quiz.getUser().getId().equals(user.getId())) {
            return ResponseEntity.status(404).body(Map.of("error", "Quiz non trovato o non appartenente all'utente loggato"));
        }

        // *** MODIFICA: Utilizza la lista di risposte direttamente dall'entità (caricamento implicito o esplicito) ***
        // 2. Recupera TUTTI i PunteggioQuiz tramite la relazione @OneToMany
        List<PunteggioQuiz> tutteLeDomandeDelQuiz = quiz.getRisposte(); // PUNTO CHIAVE

        // 3. Mappa in DTO con i dettagli e verifica la correttezza
        List<Map<String, Object>> risposteDettagliate = new ArrayList<>();
        int punteggioCalcolato = 0;

        // Itera su TUTTI i PunteggioQuiz, anche quelli con risposta = NULL
        for (PunteggioQuiz p : tutteLeDomandeDelQuiz) {
            Domande domanda = p.getDomanda();
            // La risposta corretta è in Domande.risposta
            Boolean rispostaCorretta = domanda.getRisposta();
            // La risposta data dall'utente è in PunteggioQuiz.risposta (può essere NULL)
            Boolean rispostaDataUtente = p.getRisposta();

            // Verifica se la risposta data dall'utente è uguale alla risposta corretta
            // Se rispostaDataUtente è NULL, la condizione è FALSE.
            boolean isRispostaGiusta = rispostaDataUtente != null && rispostaDataUtente.equals(rispostaCorretta);

            if (isRispostaGiusta) {
                punteggioCalcolato++;
            }

            Map<String, Object> dettaglioRisposta = new HashMap<>();
            dettaglioRisposta.put("domandaId", domanda.getId());
            dettaglioRisposta.put("testoDomanda", domanda.getTesto());
            dettaglioRisposta.put("immagine", domanda.getImmagine());
            dettaglioRisposta.put("rispostaDataUtente", rispostaDataUtente); // Può essere NULL
            dettaglioRisposta.put("rispostaCorretta", rispostaCorretta);      // Risposta True/False corretta
            dettaglioRisposta.put("isGiusta", isRispostaGiusta);              // Se la risposta dell'utente è corretta

            risposteDettagliate.add(dettaglioRisposta);
        }

        // 4. Prepara la mappa di risposta
        Map<String, Object> body = new HashMap<>();
        body.put("quizId", quiz.getId());
        body.put("punteggioMemorizzato", quiz.getPunteggio());
        body.put("punteggioCalcolato", punteggioCalcolato);
        body.put("risposte", risposteDettagliate);

        return ResponseEntity.ok(body);
    }
}