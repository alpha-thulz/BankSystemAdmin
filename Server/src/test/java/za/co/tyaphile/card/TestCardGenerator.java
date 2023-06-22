package za.co.tyaphile.card;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCardGenerator {
    @Test
    void testCard() {
        for (int i = 0; i < 10000; i++) {
            CardGenerator card = new CardGenerator();
            assertEquals(10, card.getCard().length());
        }
    }

    @Test
    void testCVV() {
        for(int i = 0; i < 10000; i++) {
            CardGenerator card = new CardGenerator();
            Pattern pattern = Pattern.compile("\\d{3}");
            Matcher matcher = pattern.matcher(card.getCvv());
            assertTrue(matcher.matches());
        }
    }
    @Test
    void testPin() {
        for(int i = 0; i < 10000; i++) {
            CardGenerator card = new CardGenerator();
            Pattern pattern = Pattern.compile("\\d{4}");
            Matcher matcher = pattern.matcher(card.getPin());
            assertTrue(matcher.matches());
        }
    }
}