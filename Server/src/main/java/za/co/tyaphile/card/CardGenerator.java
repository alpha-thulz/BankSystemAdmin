package za.co.tyaphile.card;

import java.text.DecimalFormat;
import java.util.Random;

public class CardGenerator {

    private final long num;
    private final int cvv, pin;
    private final long min = 0, max = 999_999_999;

    CardGenerator() {
        Random r = new Random();
        num = max + ((long) (r.nextDouble() * (max - min)));
        cvv = r.nextInt(1000);
        pin = r.nextInt(10000);
    }

    public String getCard() {
        DecimalFormat df = new DecimalFormat("000");
        return String.valueOf(num);
    }

    public String getCvv() {
        DecimalFormat df = new DecimalFormat("000");
        return df.format(cvv);
    }

    public String getPin() {
        DecimalFormat df = new DecimalFormat("0000");
        return df.format(pin);
    }
}