package usecases;

import infrastructure.persistence.UrlInMemoryRepository;
import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import entities.UrlShortener;

public class ShortenUrlTest {

    private UrlShortener urlShortener;
    private ShortenUrl shortenUrl;

    @BeforeEach
    public void setup() {
        urlShortener = mock(UrlShortener.class);
        shortenUrl = new ShortenUrl(urlShortener, new UrlInMemoryRepository());
    }

    @Test
    @DisplayName("shorten given url")
    void shortenGivenUrl() {
        String url = "https://www.amazon.es/-/pt/dp/B07MSN1NPC/ref=pd_ci_mcx_mh_mcx_views_0?pd_rd_w=J8QXY&content-id=amzn1.sym.a34645c3-e1d7-498e-bcb7-a548b6540162&pf_rd_p=a34645c3-e1d7-498e-bcb7-a548b6540162&pf_rd_r=Z4FD07F2T1MA1RVWMFM2&pd_rd_wg=MKVXQ&pd_rd_r=35d5f0fb-4ae7-4c99-8f21-a21feb43885f&pd_rd_i=B07MSN1NPC";
        String expected = "https://tinyurl.com/AMZ-123";
        when(urlShortener.shorten(url)).thenReturn(expected);

        ShortenedUrl shortenedUrl = shortenUrl.execute(url);

        assertThat(shortenedUrl).isEqualTo(new ShortenedUrl(expected, new Statistics(1,0)));
    }

    @Test
    @DisplayName("shortened url should contains statistics")
    void shortenedUrlShouldContainStatistics() {
        String url = "https://www.amazon.es/-/pt/dp/B07MSN1NPC/ref=pd_ci_mcx_mh_mcx_views_0?pd_rd_w=J8QXY&content-id=amzn1.sym.a34645c3-e1d7-498e-bcb7-a548b6540162&pf_rd_p=a34645c3-e1d7-498e-bcb7-a548b6540162&pf_rd_r=Z4FD07F2T1MA1RVWMFM2&pd_rd_wg=MKVXQ&pd_rd_r=35d5f0fb-4ae7-4c99-8f21-a21feb43885f&pd_rd_i=B07MSN1NPC";
        String returnUrl = "https://tinyurl.com/AMZ-123";
        when(urlShortener.shorten(url)).thenReturn(returnUrl);
        Statistics expected = new Statistics(1,0);

        ShortenedUrl shortenedUrl = shortenUrl.execute(url);

        assertThat(shortenedUrl.statistics()).isEqualTo(expected);
    }

    @Test
    @DisplayName("Url shortened twice should count shortened times correctly")
    void urlShortenedTwiceShouldCountShortenedTimesCorrectly() {
        String url = "https://www.amazon.es/-/pt/dp/B07MSN1NPC/ref=pd_ci_mcx_mh_mcx_views_0?pd_rd_w=J8QXY&content-id=amzn1.sym.a34645c3-e1d7-498e-bcb7-a548b6540162&pf_rd_p=a34645c3-e1d7-498e-bcb7-a548b6540162&pf_rd_r=Z4FD07F2T1MA1RVWMFM2&pd_rd_wg=MKVXQ&pd_rd_r=35d5f0fb-4ae7-4c99-8f21-a21feb43885f&pd_rd_i=B07MSN1NPC";
        String returnUrl = "https://tinyurl.com/AMZ-123";
        when(urlShortener.shorten(url)).thenReturn(returnUrl);
        Statistics expectedStatistics = new Statistics(2, 0);

        shortenUrl.execute(url);
        ShortenedUrl result = shortenUrl.execute(url);

        assertThat(result.statistics()).isEqualTo(expectedStatistics);
    }
}
