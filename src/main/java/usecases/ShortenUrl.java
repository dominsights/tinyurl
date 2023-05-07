package usecases;

import entities.UrlShortener;

public class ShortenUrl {
    private final UrlShortener shortener;

    public ShortenUrl(UrlShortener urlShortener) {
        this.shortener = urlShortener;
    }

    public ShortenedUrl execute(String url) {
        String shortened = shortener.shorten(url);
        return new ShortenedUrl(shortened, new Statistics(1, 0));
    }
}
