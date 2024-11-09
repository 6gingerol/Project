package persistence;

import model.EnneagramCharacter;
import model.Roster;

import org.json.JSONObject;


import java.io.*;

// major inspiration taken from JsonSerializationDemo

// Represents a writer that writes JSON representation of Roster to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of Roster to file
    public void write(Roster roster) {
        JSONObject json = roster.toJson();
        saveToFile(json.toString(TAB));
    }
    
    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
