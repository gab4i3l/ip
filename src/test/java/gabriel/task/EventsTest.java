package gabriel.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventsTest {
    public void testStringConversion() {
        Events event = new Events("project meeting", "2/12/2019 1800", "2/12/2019 1900");
        String expected = "[E][ ] project meeting (from: Dec 02 2019, 6:00PM to: Dec 02 2019, 7:00PM)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testWriteToFileFormat() {
        Events event = new Events("project meeting", "2/12/2019 1800", "2/12/2019 1900");
        String expected = "Event | 0 | project meeting | from: 2019-12-02 1800 | to: 2019-12-02 1900";
        assertEquals(expected, event.writeToFile());
    }
}
