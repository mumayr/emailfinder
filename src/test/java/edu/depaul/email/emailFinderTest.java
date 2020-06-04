package edu.depaul.email;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.io.*;
import java.io.File;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class emailFinderTest {
    @Test
    @DisplayName("Testing email crawler")
    void test() throws FileNotFoundException {
        File file = new File("C:\\Users\\Moham\\OneDrive\\Desktop\\email-finder-master\\email-finder-master\\email.txt");
        file.delete();
        EmailFinder test = new EmailFinder();
        String[] testlist = new String[1];
        testlist[0] = "https://law.depaul.edu/Pages/default.aspx";
        test.run(testlist);
        int c = 0;
        Scanner scan = new Scanner(new File("C:\\Users\\Moham\\OneDrive\\Desktop\\email-finder-master\\email-finder-master\\email.txt"));
        while (scan.hasNextLine()) {
            c++;
            scan.nextLine();
        }
        assertEquals(12, c);

    }

    @Test
    @DisplayName("Testing email crawler for 10 emails")
    void testCertainNumberFour() throws FileNotFoundException {
        File file = new File("C:\\Users\\Moham\\OneDrive\\Desktop\\email-finder-master\\email-finder-master\\email.txt");
        file.delete();
        EmailFinder test = new EmailFinder();
        String[] testlist = new String[1];
        testlist[0] = "C:\\Users\\Moham\\OneDrive\\Desktop\\FinalProjectSend\\EmailFinderWebsite\\MidtermProject\\Html\\midterm3.html";
        test.run(testlist);
        int c = 0;
        Scanner scan = new Scanner(new File("C:\\Users\\Moham\\OneDrive\\Desktop\\email-finder-master\\email-finder-master\\email.txt"));
        while (scan.hasNextLine()) {
            c++;
            scan.nextLine();
        }
        assertEquals(10, c);
    }

    @Test
    @DisplayName("Testing email crawler for 12 emails")
    void testCertainNumberTwelve() throws FileNotFoundException {
        File file = new File("C:\\Users\\Moham\\OneDrive\\Desktop\\email-finder-master\\email-finder-master\\email.txt");
        file.delete();
        EmailFinder test = new EmailFinder();
        String[] testlist = new String[1];
        testlist[0] = "C:\\Users\\Moham\\OneDrive\\Desktop\\FinalProjectSend\\EmailFinderWebsite\\MidtermProject\\Html\\midterm2.html";
        test.run(testlist);
        int c = 0;
        Scanner scan = new Scanner(new File("C:\\Users\\Moham\\OneDrive\\Desktop\\email-finder-master\\email-finder-master\\email.txt"));
        while (scan.hasNextLine()) {
            c++;
            scan.nextLine();
        }
        assertEquals(12, c);
    }

    @Test
    @DisplayName("Testing email crawler for no emails on no website")
    void testEmpty() throws FileNotFoundException {
        File file = new File("C:\\Users\\Moham\\OneDrive\\Desktop\\email-finder-master\\email-finder-master\\email.txt");
        file.delete();
        EmailFinder test = new EmailFinder();
        String[] testlist = new String[1];
        testlist[0] = "";
        test.run(testlist);
        int c = 0;
        Scanner scan = new Scanner(new File("C:\\Users\\Moham\\OneDrive\\Desktop\\email-finder-master\\email-finder-master\\email.txt"));
        while (scan.hasNextLine()) {
            c++;
            scan.nextLine();
        }
        assertEquals(0, c);

    }
    @Test
    @DisplayName("URL does not work correctly")
    void testInvalidUrlError() {
        PageFetcher x = new PageFetcher();
        assertThrows(EmailFinderException.class, () -> x.get("abcd"));
    }
    @Test
    @DisplayName("name of the file error")
    void testInvalidFileError() {
        PageFetcher x = new PageFetcher();
        assertThrows(EmailFinderException.class, () -> x.get("test.html"));
    }
    @Test
    @DisplayName("Does not take local files in system")
    void testGetStringEmpty() {
        PageFetcher x = new PageFetcher();
        assertThrows(EmailFinderException.class, () -> x.getString("xyz.html"));
    }
    @Test
    @DisplayName("URL error")
    void testGetStringUrl() {
        PageFetcher x = new PageFetcher();
        assertThrows(EmailFinderException.class, () -> x.getString("https://facetbook.com"));
    }


    @ParameterizedTest
    @ValueSource(strings = {"keiji@att.net", "nelson@me.com", "johnbob@gmail.com", "psharpe@live.com", "paina@comcast.net", "abc@att.net", "depaul@me.com", "billyboib@gmail.com", "deeznuts@live.com", "theysuck@comcast.net"})
    void testingOutPut(String inputs){
        StorageService storage = new StorageService();
        EmailFinder fix = new EmailFinder();
        PageCrawler test = new PageCrawler(storage);
        test.crawl("C:\\Users\\Moham\\OneDrive\\Desktop\\FinalProjectSend\\EmailFinderWebsite\\MidtermProject\\Html\\midterm3.Html");
        Set<String> hset = new HashSet<>();
        hset = test.getEmails();
        assertTrue(hset.contains(inputs));
    }
    @ParameterizedTest
    @ValueSource(strings = {"C:\\Users\\Moham\\OneDrive\\Desktop\\FinalProjectSend\\EmailFinderWebsite\\MidtermProject\\Html\\midterm3.Html"})
    void testGoodLinks(String inputs){
        StorageService storage = new StorageService();
        EmailFinder fix = new EmailFinder();
        PageCrawler test = new PageCrawler(storage);
        test.crawl("C:\\Users\\Moham\\OneDrive\\Desktop\\FinalProjectSend\\EmailFinderWebsite\\MidtermProject\\Html\\midterm3.Html");
        Set<String> life = new HashSet<>();
        life = test.getGoodLinks();
        assertTrue(life.contains(inputs));

    }
}