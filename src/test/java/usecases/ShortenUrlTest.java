package usecases;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import entities.UrlShortener;

public class ShortenUrlTest {

    private UrlShortener urlShortener;

    @BeforeEach
    public void setup() {
        urlShortener = mock(UrlShortener.class);
    }

    @Test
    @DisplayName("shorten given url")
    void shortenGivenUrl() {
        String url = "https://www.amazon.es/-/pt/dp/B07MSN1NPC/ref=pd_ci_mcx_mh_mcx_views_0?pd_rd_w=J8QXY&content-id=amzn1.sym.a34645c3-e1d7-498e-bcb7-a548b6540162&pf_rd_p=a34645c3-e1d7-498e-bcb7-a548b6540162&pf_rd_r=Z4FD07F2T1MA1RVWMFM2&pd_rd_wg=MKVXQ&pd_rd_r=35d5f0fb-4ae7-4c99-8f21-a21feb43885f&pd_rd_i=B07MSN1NPC";
        String expected = "https://tinyurl.com/AMZ-123";
        when(urlShortener.shorten(url)).thenReturn(expected);

        ShortenUrl shortenUrl = new ShortenUrl(urlShortener);
        ShortenedUrl shortenedUrl = shortenUrl.execute(url);
        assertThat(shortenedUrl).isEqualTo(new ShortenedUrl(expected, new Statistics(1,0)));
    }

    @Test
    @DisplayName("shortened url should contains statistics")
    void shortenedUrlShouldContainStatistics() {
        String url = "https://www.amazon.es/-/pt/dp/B07MSN1NPC/ref=pd_ci_mcx_mh_mcx_views_0?pd_rd_w=J8QXY&content-id=amzn1.sym.a34645c3-e1d7-498e-bcb7-a548b6540162&pf_rd_p=a34645c3-e1d7-498e-bcb7-a548b6540162&pf_rd_r=Z4FD07F2T1MA1RVWMFM2&pd_rd_wg=MKVXQ&pd_rd_r=35d5f0fb-4ae7-4c99-8f21-a21feb43885f&pd_rd_i=B07MSN1NPC";
        String returnUrl = "https://tinyurl.com/AMZ-123";
        when(urlShortener.shorten(url)).thenReturn(returnUrl);

        ShortenUrl shortenUrl = new ShortenUrl(urlShortener);
        ShortenedUrl shortenedUrl = shortenUrl.execute(url);

        Statistics expected = new Statistics(1,0);

        assertThat(shortenedUrl.statistics()).isEqualTo(expected);
    }
}
