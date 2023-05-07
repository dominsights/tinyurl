package usecases;

import lombok.Value;

@Value
public class ShortenedUrl {
    Statistics statistics;
    public ShortenedUrl(String expected, Statistics statistics) {

        this.statistics = statistics;
    }
}
