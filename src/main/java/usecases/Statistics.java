package usecases;

import lombok.Value;

@Value
public class Statistics {
    int timesShortened;
    int timesAccessed;

    public Statistics(int timesShortened, int timesAccessed) {
        this.timesShortened = timesShortened;
        this.timesAccessed = timesAccessed;
    }
}
