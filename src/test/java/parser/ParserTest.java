package parser;

import gabriel.parser.Parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import gabriel.exception.GabrielException;

public class ParserTest {

    @Test
    void getCommand_validCommand() {
        assertEquals("help", Parser.getCommand("help"));
        assertEquals("help", Parser.getCommand("Help"));
        assertEquals("help", Parser.getCommand("HELP"));
        assertEquals("example", Parser.getCommand("example"));
        assertEquals("example", Parser.getCommand("Example"));
        assertEquals("example", Parser.getCommand("EXAMPLE"));
        assertEquals("save", Parser.getCommand("save"));
        assertEquals("save", Parser.getCommand("Save"));
        assertEquals("save", Parser.getCommand("SAVE"));
        assertEquals("find", Parser.getCommand("find books"));
        assertEquals("find", Parser.getCommand("Find books"));
        assertEquals("find", Parser.getCommand("FIND BOOKS"));
        assertEquals("todo", Parser.getCommand("todo read book"));
        assertEquals("todo", Parser.getCommand("Todo read book"));
        assertEquals("todo", Parser.getCommand("TODO READ BOOK"));
        assertEquals("list", Parser.getCommand("list"));
        assertEquals("list", Parser.getCommand("LIST"));
        assertEquals("mark", Parser.getCommand("mark 1"));
        assertEquals("mark", Parser.getCommand("MARK 1"));
        assertEquals("unmark", Parser.getCommand("unmark 1"));
        assertEquals("unmark", Parser.getCommand("UNMARK 1"));
        assertEquals("deadline", Parser.getCommand("deadline return book /by 2/12/2019 1800"));
        assertEquals("deadline", Parser.getCommand("Deadline return book /by 2/12/2019 1800"));
        assertEquals("deadline", Parser.getCommand("DEADLINE return book /by 2/12/2019 1800"));
        assertEquals("event", Parser.getCommand("event meeting /from 2/10/2019 1800 /to 2/10/2019 1900"));
        assertEquals("event", Parser.getCommand("Event meeting /from 2/10/2019 1800 /to 2/10/2019 1900"));
        assertEquals("event", Parser.getCommand("EVENT meeting /from 2/10/2019 1800 /to 2/10/2019 1900"));
        assertEquals("bye", Parser.getCommand("bye"));
        assertEquals("bye", Parser.getCommand("Bye"));
        assertEquals("bye", Parser.getCommand("BYE"));
    }

    @Test
    void parseIndex_validIndex() throws GabrielException {
        assertEquals(0, Parser.parseIndex("mark 1"));
        assertEquals(2, Parser.parseIndex("unmark 3"));
        assertEquals(1, Parser.parseIndex("delete 2"));

    }

    @Test
    void parseDeadline_validInput_()  throws GabrielException {
        String [] parts = Parser.parseDeadline("deadline return book /by 2/12/2019 1800");
        assertEquals("return book", parts[0]);
        assertEquals("2/12/2019 1800", parts[1]);
    }
}
