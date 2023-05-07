package usecases;

import lombok.Value;

@Value
public class ShortenedUrl {
    Statistics statistics;
    public ShortenedUrl(String shortenedUrl, Statistics statistics) {

        this.statistics = statistics;
    }
}
