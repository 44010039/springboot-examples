package net.springboot.examples;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import net.springboot.examples.KeycloakApplicaion;


@Disabled("需要开启keycloak服务")
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {KeycloakApplicaion.class})
public class KeycloakApplicaionTests {
    private static WebClient webClient;

    @BeforeAll
    public static void BeforeTestClass() {
        webClient = new WebClient(BrowserVersion.CHROME);
        webClient.setCssErrorHandler(new SilentCssErrorHandler());
    }

    @Test
    public void testRedirect() throws IOException {
        HtmlPage page = webClient.getPage("http://127.0.0.1:8080");
        assertTrue(page.getBody().getTextContent().contains("Username or email"));
    }

    @Test
    public void testLogin() throws IOException {
        HtmlPage page = login("zhangs", "zhangs");
        assertTrue(page.getTitleText().contains("Home Page"));
        assertTrue(page.getBody().getTextContent().contains("Default Resource"));
        logout(page);
        page = login("lis", "lis");
        assertTrue(page.getBody().getTextContent().contains("Default Resource"));
        assertTrue(page.getBody().getTextContent().contains("Premium Resource"));
        logout(page);
    }

    @Test
    public void testLogout() throws IOException {
        HtmlPage page = login("zhangs", "zhangs");
        page = logout(page);
        assertTrue(page.getBody().getTextContent().contains("Username or email"));
    }

    @Test
    public void testProtectedResource() throws IOException {
        HtmlPage page = login("zhangs", "zhangs");
        page = page.getElementById("protected-resource").click();
        assertTrue(page.getBody().getTextContent().contains("\"Protected Resource\""));
        logout(page);
        page = login("lis", "lis");
        page = page.getElementById("protected-resource").click();
        assertTrue(page.getBody().getTextContent().contains("\"Protected Resource\""));
        logout(page);
    }

    @Test
    public void testPremiumResource() throws IOException {
        HtmlPage page = login("zhangs", "zhangs");
        page = page.getElementById("premium-resource").click();
        assertTrue(page.getBody().getTextContent().contains("lack permission"));
        logout(page);
        page = login("lis", "lis");
        page = page.getElementById("premium-resource").click();
        assertTrue(page.getBody().getTextContent().contains("\"Premium Resource\""));
        logout(page);
    }

    private HtmlPage login(String username, String password) throws IOException {
        HtmlPage page = webClient.getPage("http://127.0.0.1:8080");
        ((HtmlInput)page.getElementById("username")).setValueAttribute(username);
        ((HtmlInput)page.getElementById("password")).setValueAttribute(password);
        return page.getElementByName("login").click();
    }

    private HtmlPage logout(HtmlPage page) throws IOException {
        return page.getElementById("logout").click();
    }
}
