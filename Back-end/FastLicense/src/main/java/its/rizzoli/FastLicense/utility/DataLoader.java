package its.rizzoli.FastLicense.utility;

import its.rizzoli.FastLicense.models.*;
import its.rizzoli.FastLicense.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
            u1.setPassword(BCrypt.hashpw("password", BCrypt.gensalt()));
            userRepository.save(u1);
        }

        if (!userRepository.findByUsername("admin").isPresent()) {
            User u2 = new User();
            u2.setUsername("admin");
            u2.setPassword(BCrypt.hashpw("admin", BCrypt.gensalt()));
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
        // ================= 60 DOMANDE PATENTE =====================
        String[] domandePatente = {
                "Il limite massimo di velocità in città è di 50 km/h?",
                "È obbligatorio allacciare le cinture di sicurezza anche nei sedili posteriori?",
                "I bambini sotto i 12 anni devono usare un seggiolino adatto?",
                "Il semaforo giallo lampeggiante indica di fermarsi immediatamente?",
                "Il sorpasso a destra è sempre consentito?",
                "Il limite di tasso alcolemico per i neopatentati è 0,0 g/l?",
                "Il parcheggio in doppia fila è sempre consentito se si scaricano merci?",
                "È obbligatorio usare l’indicatore di direzione quando si cambia corsia?",
                "Si può superare un veicolo davanti quando la linea è continua?",
                "Il casco è obbligatorio per i motocicli?",
                "È consentito l’uso del telefono senza vivavoce durante la guida?",
                "Il conducente deve dare precedenza ai pedoni sulle strisce pedonali?",
                "Il segnale di stop obbliga a fermarsi completamente?",
                "È obbligatorio mantenere la distanza di sicurezza?",
                "La luce verde del semaforo indica che si deve accelerare?",
                "Si può circolare contromano in una strada a senso unico?",
                "Il sorpasso in curva è sempre vietato?",
                "È obbligatorio avere l’assicurazione RC Auto aggiornata?",
                "Il segnale di divieto di accesso vieta l’ingresso a tutti i veicoli?",
                "È consentito trasportare persone sul vano di carico di un furgone?",
                "Il guidatore deve regolare la velocità in base alle condizioni atmosferiche?",
                "Il sorpasso in prossimità di un attraversamento pedonale è vietato?",
                "Il parcheggio su marciapiede è consentito?",
                "Il veicolo deve circolare con luci accese anche di giorno in galleria?",
                "Il semaforo rosso lampeggiante indica pericolo, procedere con cautela?",
                "Il segnale di precedenza obbliga a fermarsi solo se c’è traffico?",
                "La revisione periodica dell’auto è obbligatoria?",
                "È consentito trainare un veicolo senza catene a neve in inverno?",
                "Si può usare l’auto con pneumatici lisci in inverno?",
                "Il sorpasso in autostrada è consentito solo a sinistra?",
                "Il limite massimo in autostrada è di 130 km/h?",
                "Il limite minimo in autostrada è di 60 km/h?",
                "La cintura di sicurezza è obbligatoria anche per brevi tragitti?",
                "Il guidatore deve fermarsi se vede un mezzo di soccorso con lampeggianti accesi?",
                "È vietato guidare senza patente?",
                "Si può guidare con tasso alcolemico superiore al consentito?",
                "È obbligatorio il triangolo di emergenza in caso di fermata?",
                "È obbligatorio l’uso dei fendinebbia solo in caso di nebbia?",
                "Il segnale di divieto di sosta vieta la fermata anche momentanea?",
                "Si può superare un veicolo davanti se procede lentamente?",
                "È consentito parcheggiare davanti a un passo carrabile?",
                "Il guidatore deve dare precedenza ai veicoli provenienti da destra?",
                "Il sorpasso vicino a curve cieche è vietato?",
                "È obbligatorio il casco per i ciclomotori?",
                "Il veicolo deve fermarsi se la barriera del passaggio a livello è chiusa?",
                "Il semaforo giallo fisso indica di fermarsi se possibile?",
                "Il guidatore deve rispettare i limiti di velocità anche con strada libera?",
                "Si può usare il clacson in zone residenziali solo per avvertire?",
                "Il sorpasso su strade con linea continua è vietato?",
                "Il parcheggio in curva è sempre vietato?",
                "Il veicolo deve avere assicurazione valida per circolare?",
                "Il conducente deve mantenere l’attenzione costante durante la guida?",
                "Si può guidare senza cintura di sicurezza?",
                "Il guidatore deve dare precedenza ai veicoli di emergenza?",
                "Il limite di velocità in autostrada per mezzi pesanti è minore di 80 km/h?",
                "Il sorpasso in prossimità di scuole è vietato?",
                "È obbligatorio avere estintore a bordo?",
                "Il guidatore deve fermarsi davanti a semaforo rosso anche se non c’è traffico?",
                "Il segnale di obbligo indica un comportamento da seguire obbligatoriamente?",
                "Si può circolare con luci spente di notte?",
                "Il conducente deve rallentare in caso di pioggia intensa?",
                "Il sorpasso con linea tratteggiata è consentito?",
                "Il parcheggio vicino a fermata autobus è vietato?"
        };

        for (String testoDomanda : domandePatente) {
            if (!domandeRepository.findByTesto(testoDomanda).isPresent()) {
                Domande d = new Domande();
                d.setTesto(testoDomanda);
                // imposta la risposta automaticamente: true se contiene parole chiave
                d.setRisposta(testoDomanda.toLowerCase().contains("obbligatorio") ||
                        testoDomanda.toLowerCase().contains("deve") ||
                        testoDomanda.toLowerCase().contains("vietato"));
                d.setImmagine(null); // nessuna immagine
                domandeRepository.save(d);
            }
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
