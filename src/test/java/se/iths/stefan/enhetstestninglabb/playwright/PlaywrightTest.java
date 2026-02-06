package se.iths.stefan.enhetstestninglabb.playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PlaywrightTest {

    static Playwright playwright;
    static Browser browser;
    Page page;

    @BeforeAll
    static void setUpAll() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch( //Startar browser i chromium familjen t.ex chrome chromium och edge
                new BrowserType.LaunchOptions().setHeadless(true));//osynligt window utan ui
    }

    @AfterAll
    static void tearDownAll() {
        //Efter alla tester stänger den ned den osynliga browsern som öppnats
        // samt så stängs playwright också och dessa är för att ej få memory leak och använda onödigt minne
        //Bra tips för att skriva effektiv kod
        browser.close();
        playwright.close();
    }

    @BeforeEach
    void setUp() {
        page = browser.newPage();
        page.navigate("http://localhost:8080/atm/balance");
    }

    @AfterEach
    void closePage() {
        page.close();
    }

    @Test
    void canReachWebsite() {
        page.waitForLoadState(LoadState.LOAD);
        Locator accountSpan = page.locator("ul p span");
        String accountValue = accountSpan.textContent().trim();
        Assertions.assertEquals("0", accountValue);
    }

    @Test
    void shouldLoadPage() {
        Assertions.assertEquals("Balance", page.title());
    }
}
