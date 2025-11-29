package its.rizzoli.FastLicense.utility;

import its.rizzoli.FastLicense.models.Capitoli;
import its.rizzoli.FastLicense.models.Immagine;
import its.rizzoli.FastLicense.models.News;
import its.rizzoli.FastLicense.repositories.CapitoliRepository;
import its.rizzoli.FastLicense.repositories.NewsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final NewsRepository newsRepository;
    private final CapitoliRepository capitoliRepository;
    

    public DataLoader(NewsRepository newsRepository, CapitoliRepository capitoliRepository) {
        this.newsRepository = newsRepository;
        this.capitoliRepository = capitoliRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Caricamento news
        if (newsRepository.count() == 0) {
            List<News> newsList = Arrays.asList(
                    createNews("Nuovo limite di velocità in città",
                            "Il Comune ha stabilito un nuovo limite di 30 km/h nelle zone residenziali per aumentare la sicurezza dei pedoni.",
                            LocalDateTime.now().minusDays(1)),
                    createNews("Cintura di sicurezza obbligatoria",
                            "Ricordiamo a tutti gli automobilisti che l'uso della cintura di sicurezza è obbligatorio anche nei sedili posteriori.",
                            LocalDateTime.now().minusDays(2)),
                    createNews("Uso del cellulare alla guida",
                            "È severamente vietato l'uso del cellulare alla guida. Le sanzioni comprendono multe e decurtazione di punti dalla patente.",
                            LocalDateTime.now().minusDays(3)),
                    createNews("Nuove strisce pedonali illuminate",
                            "Sono state installate nuove strisce pedonali con illuminazione LED per aumentare la visibilità dei pedoni di notte.",
                            LocalDateTime.now().minusDays(4)),
                    createNews("Controlli della velocità",
                            "La polizia stradale intensificherà i controlli con autovelox sulle principali arterie cittadine.",
                            LocalDateTime.now().minusHours(5)),

                    createNews("Novità esame teorico",
                            "A partire da questo mese, l'esame teorico prevede nuove domande sulla sicurezza stradale e sulle norme aggiornate.",
                            LocalDateTime.now().minusDays(1).minusHours(2)),
                    createNews("Suggerimenti per l'esame pratico",
                            "Gli istruttori consigliano di concentrarsi sulle manovre di parcheggio e sul rispetto dei limiti di velocità per superare l'esame pratico.",
                            LocalDateTime.now().minusDays(2).minusHours(1)),
                    createNews("Esame teorico online",
                            "È ora possibile sostenere l'esame teorico in modalità digitale presso alcune sedi abilitate, riducendo tempi di attesa.",
                            LocalDateTime.now().minusDays(3).minusHours(3)),
                    createNews("Preparazione esame pratico",
                            "Consigli utili: ripetere i percorsi di guida, controllare specchietti e segnali e mantenere calma durante la prova.",
                            LocalDateTime.now().minusDays(4).minusHours(2))
            );

            newsRepository.saveAll(newsList);
            System.out.println("News iniziali caricate correttamente.");
        }

        // Caricamento capitoli con immagini
        if (capitoliRepository.count() == 0) {
            String[] capitoliTitoli = {
                    "Incidenti", "Seganli di obbligo", "Patenti", "Posizioni Vigile",
                    "Segnali di precedenza", "Segnaletica Orizzontale", "Segnali di pericolo",
                    "Semaforo", "Sicurezza", "Strada"
            };

            String[] immaginiFileName = {
                    "incidenti.png", "obbligo.png", "patenti.png", "posizione_vigile.png",
                    "segnale_precedenza.png", "segnaletica_orizzontale.png", "segnali_pericolo_cap.png",
                    "semaforo.png", "sicurezza.png", "strade_cap.png"
            };

            for (int i = 0; i < capitoliTitoli.length; i++) {
                Capitoli capitolo = new Capitoli();
                capitolo.setTitolo(capitoliTitoli[i]);

                Immagine immagine = new Immagine();
                immagine.setFileName(immaginiFileName[i]);
                immagine.setCapitolo(capitolo);

                capitolo.getImmagini().add(immagine);

                capitoliRepository.save(capitolo);
            }



            System.out.println("Capitoli e immagini iniziali caricati correttamente.");
        }
    }

    private News createNews(String titolo, String testo, LocalDateTime dataOra) {
        News news = new News();
        news.setTitolo(titolo);
        news.setTesto(testo);
        news.setDataOra(dataOra);
        return news;
    }
}
