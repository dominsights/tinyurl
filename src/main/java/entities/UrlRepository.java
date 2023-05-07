package entities;

import java.util.Optional;

public interface UrlRepository {
    Optional<ShortenedUrl> get(String shortened);

    void save(ShortenedUrl shortenedUrl);
}
