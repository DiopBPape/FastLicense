package its.rizzoli.FastLicense.controller;

import its.rizzoli.FastLicense.DTO.NewsDto;
import its.rizzoli.FastLicense.models.News;
import its.rizzoli.FastLicense.repositories.NewsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NewsController {

    private final NewsRepository newsRepository;

    public NewsController(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @GetMapping("/news")
    public ResponseEntity<List<NewsDto>> getAllNews() {
        List<NewsDto> newsList = newsRepository.findAllByOrderByDataOraDesc()
                .stream()
                .map(news -> {
                    NewsDto dto = new NewsDto();
                    dto.setId(news.getId());
                    dto.setTitolo(news.getTitolo());
                    dto.setTesto(news.getTesto());
                    dto.setDataOra(news.getDataOra());
                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(newsList);
    }
}
