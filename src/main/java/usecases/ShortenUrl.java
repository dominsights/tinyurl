package usecases;

import entities.ShortenedUrl;
import entities.Statistics;
import entities.UrlRepository;
import entities.UrlShortener;

import java.util.Optional;

public class ShortenUrl {
    private final UrlShortener shortener;
    private final UrlRepository urlRepository;

    public ShortenUrl(UrlShortener urlShortener, UrlRepository urlRepository) {
        this.shortener = urlShortener;
        this.urlRepository = urlRepository;
    }

    public ShortenedUrl execute(String url) {
        String shortened = shortener.shorten(url);
        Optional<ShortenedUrl> shortenedUrl = urlRepository.get(shortened);
        ShortenedUrl updated = shortenedUrl
                .map(s -> new ShortenedUrl(
                        s.shortenedUrl(),
                        new Statistics(
                                s.statistics().timesShortened() + 1,
                                s.statistics().timesAccessed())))
                .orElse(new ShortenedUrl(shortened, new Statistics(1, 0)));
        urlRepository.save(updated);
        return updated;
    }
}
