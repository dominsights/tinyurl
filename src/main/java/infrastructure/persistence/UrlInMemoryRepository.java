package infrastructure.persistence;

import entities.UrlRepository;
import entities.ShortenedUrl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UrlInMemoryRepository implements UrlRepository {
    Map<String, ShortenedUrl> urls;

    public UrlInMemoryRepository() {
        this.urls = new HashMap<>();
    }

    @Override
    public Optional<ShortenedUrl> get(String shortened) {
        return Optional.ofNullable(urls.get(shortened));
    }

    @Override
    public void save(ShortenedUrl shortenedUrl) {
        urls.put(shortenedUrl.shortenedUrl(), shortenedUrl);
    }
}
