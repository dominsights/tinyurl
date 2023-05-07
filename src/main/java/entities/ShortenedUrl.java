package entities;

import lombok.Value;

@Value
public class ShortenedUrl {
    private final String shortenedUrl;
    Statistics statistics;
    public ShortenedUrl(String shortenedUrl , Statistics statistics) {
        this.shortenedUrl = shortenedUrl;

        this.statistics = statistics;
    }
}
