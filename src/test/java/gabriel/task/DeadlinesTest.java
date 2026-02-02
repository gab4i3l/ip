package gabriel.task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlinesTest {

    @Test
    public void testStringConversion() {
        Deadlines deadline = new Deadlines("return book", "2/12/2019 1800");
        String expected = "[D][ ] return book (by:Dec 02 2019, 6:00PM)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void testWriteToFileFormat() {
        Deadlines deadline = new Deadlines("return book","2/12/2019 1800");
        String expected = "Deadlines | 0 | return book | by: 2019-12-02 1800";
        assertEquals(expected, deadline.writeToFile());
    }
}


