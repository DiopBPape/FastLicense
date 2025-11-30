package its.rizzoli.FastLicense.utility;

import its.rizzoli.FastLicense.models.*;
import its.rizzoli.FastLicense.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final DomandeRepository domandeRepository;
    private final CapitoliRepository capitoliRepository;
    private final ArgomentiRepository argomentiRepository;
    private final QuizEseguitoRepository quizEseguitoRepository;
    private final PunteggioQuizRepository punteggioQuizRepository;
    private final NewsRepository newsRepository;

    public DataLoader(UserRepository userRepository,
                      DomandeRepository domandeRepository,
                      CapitoliRepository capitoliRepository,
                      ArgomentiRepository argomentiRepository,
                      QuizEseguitoRepository quizEseguitoRepository,
                      PunteggioQuizRepository punteggioQuizRepository,
                      NewsRepository newsRepository) {

        this.userRepository = userRepository;
        this.domandeRepository = domandeRepository;
        this.capitoliRepository = capitoliRepository;
        this.argomentiRepository = argomentiRepository;
        this.quizEseguitoRepository = quizEseguitoRepository;
        this.punteggioQuizRepository = punteggioQuizRepository;
        this.newsRepository = newsRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // ================= USERS =====================
        if (!userRepository.findByUsername("mario").isPresent()) {
            User u1 = new User();
            u1.setUsername("mario");
            u1.setPassword("password");
            userRepository.save(u1);
        }

        if (!userRepository.findByUsername("admin").isPresent()) {
            User u2 = new User();
            u2.setUsername("admin");
            u2.setPassword("admin");
            userRepository.save(u2);
        }

        // ================= NUOVI CAPITOLI =====================
        Map<String, Capitoli> newCaps = new HashMap<>();

        String[] capNames = {
                "Incidenti", "Obbligo", "Patenti", "Posizione Vigile", "Segnale Precedenza",
                "Segnaletica Orizzontale", "Segnali Pericolo", "Semaforo", "Sicurezza", "Strade"
        };

        for (String capName : capNames) {
            Capitoli cap = capitoliRepository.findByTitolo(capName)
                    .orElseGet(() -> {
                        Capitoli c = new Capitoli();
                        c.setTitolo(capName);
                        return capitoliRepository.save(c);
                    });
            newCaps.put(capName, cap);
        }

        // ================= NUOVI ARGOMENTI + IMMAGINI =====================
        Map<String, String> argomentiImmagini = new LinkedHashMap<>();
        argomentiImmagini.put("Incidenti", "incidenti.png");
        argomentiImmagini.put("Obbligo", "obbligo.png");
        argomentiImmagini.put("Patenti", "patenti.png");
        argomentiImmagini.put("Posizione Vigile", "posizione_vigile.png");
        argomentiImmagini.put("Segnale Precedenza", "segnale_precedenza.png");
        argomentiImmagini.put("Segnaletica Orizzontale", "segnaletica_orizzontale.png");
        argomentiImmagini.put("Segnali Pericolo", "segnali_pericolo_cap.png");
        argomentiImmagini.put("Semaforo", "semaforo.png");
        argomentiImmagini.put("Sicurezza", "sicurezza.png");
        argomentiImmagini.put("Strade", "strade_cap.png");

        for (Map.Entry<String, String> entry : argomentiImmagini.entrySet()) {

            String titoloArgomento = entry.getKey();
            String fileImg = entry.getValue();

            if (!argomentiRepository.findByTitolo(titoloArgomento).isPresent()) {

                Argomenti arg = new Argomenti();
                arg.setTitolo(titoloArgomento);
                arg.setTesto("Informazioni riguardo: " + titoloArgomento);
                arg.setCapitolo(newCaps.get(titoloArgomento));

                Immagine img = new Immagine();
                img.setFileName(fileImg);
                img.setArgomento(arg);

                img.setCapitolo(newCaps.get(titoloArgomento));
                newCaps.get(titoloArgomento).getImmagini().add(img);

                arg.getImmagini().add(img);

                argomentiRepository.save(arg);
            }
        }

        // ================= DOMANDE =====================
        if (!domandeRepository.findByTesto("Il segnale indica una curva pericolosa a destra?").isPresent()) {
            Domande d1 = new Domande();
            d1.setTesto("Il segnale indica una curva pericolosa a destra?");
            d1.setRisposta(true);
            d1.setImmagine("curva.png");
            domandeRepository.save(d1);
        }

        if (!domandeRepository.findByTesto("Il casco non è obbligatorio in città?").isPresent()) {
            Domande d2 = new Domande();
            d2.setTesto("Il casco non è obbligatorio in città?");
            d2.setRisposta(false);
            d2.setImmagine("casco.png");
            domandeRepository.save(d2);
        }

        // ================= QUIZ ESEGUITO + PUNTEGGI =====================
        Optional<QuizEseguito> existingQuiz =
                quizEseguitoRepository.findByUserUsername("mario");

        if (!existingQuiz.isPresent()) {

            User user = userRepository.findByUsername("mario").orElse(null);

            if (user != null) {

                QuizEseguito quiz = new QuizEseguito();
                quiz.setUser(user);
                quiz.setPunteggio(1);
                quizEseguitoRepository.save(quiz);

                List<Domande> domande = new ArrayList<>();
                domandeRepository.findAll().forEach(domande::add);

                if (domande.size() >= 2) {

                    if (!punteggioQuizRepository.existsByQuizAndDomanda(quiz, domande.get(0))) {
                        PunteggioQuiz r1 = new PunteggioQuiz();
                        r1.setQuiz(quiz);
                        r1.setDomanda(domande.get(0));
                        r1.setRisposta(true);
                        punteggioQuizRepository.save(r1);
                    }

                    if (!punteggioQuizRepository.existsByQuizAndDomanda(quiz, domande.get(1))) {
                        PunteggioQuiz r2 = new PunteggioQuiz();
                        r2.setQuiz(quiz);
                        r2.setDomanda(domande.get(1));
                        r2.setRisposta(false);
                        punteggioQuizRepository.save(r2);
                    }
                }
            }
        }

        // ================= NEWS =====================
        if (!newsRepository.findByTitolo("Nuove norme sui limiti di velocità").isPresent()) {
            News n1 = new News();
            n1.setTitolo("Nuove norme sui limiti di velocità");
            n1.setTesto("Il nuovo limite urbano scende a 30 km/h in molte città.");
            n1.setDataOra(LocalDateTime.now().minusDays(1));
            newsRepository.save(n1);
        }

        if (!newsRepository.findByTitolo("Esame teorico aggiornato").isPresent()) {
            News n2 = new News();
            n2.setTitolo("Esame teorico aggiornato");
            n2.setTesto("Aggiunte nuove domande sulla sicurezza stradale.");
            n2.setDataOra(LocalDateTime.now().minusDays(2));
            newsRepository.save(n2);
        }
    }
}
