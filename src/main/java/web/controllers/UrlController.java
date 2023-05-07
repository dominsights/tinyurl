package web.controllers;

import entities.UrlRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usecases.ShortenUrl;
import entities.ShortenedUrl;

import java.util.Optional;

@RestController
@RequestMapping("")
public class UrlController {
    private final UrlRepository urlRepository;
    private final ShortenUrl shortenUrl;

    public UrlController(UrlRepository urlRepository, ShortenUrl shortenUrl) {
        this.urlRepository = urlRepository;
        this.shortenUrl = shortenUrl;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShortenedUrl create(@RequestBody String url) {
        return shortenUrl.execute(url);
    }

    // For simplicity the entity ShortenedUrl is exposed in the API contract.
    // Normally I would separate entities from the api contract because they change for different reasons.
    @GetMapping(value = "/{url}")
    public ResponseEntity<ShortenedUrl> get(String url) {

        Optional<ShortenedUrl> shortenedUrl = urlRepository.get(url);
        return shortenedUrl
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().body(null));
    }
}
