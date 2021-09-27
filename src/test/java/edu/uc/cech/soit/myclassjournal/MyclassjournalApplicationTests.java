package edu.uc.cech.soit.myclassjournal;

import edu.uc.cech.soit.myclassjournal.dto.JournalEntry;
import edu.uc.cech.soit.myclassjournal.service.IJournalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class MyclassjournalApplicationTests {

    @Autowired
    IJournalService journalService;

    @Test
    void contextLoads() {
    }

    /**
     * Validate that the DTO properties can be set and retrieved.
     */
    @Test
    void verifyJournalEntryProperties() {
        String notes =  "I am running a unit test";
        String date = "September 2021";

        JournalEntry journalEntry = new JournalEntry();
        journalEntry.setNotes(notes);
        assertEquals(notes, journalEntry.getNotes());

        journalEntry.setDate(date);
        assertEquals(date, journalEntry.getDate());
    }

    /**
     * Validate that the JournalService can save and return Journal Entries.
     */
    @Test
    void verifyAddAndRemoveJournalEntries() {
        String notes =  "My first entry!";
        String date = "October 2021";

        JournalEntry journalEntry = new JournalEntry();
        journalEntry.setNotes(notes);
        journalEntry.setDate(date);

        journalService.save(journalEntry);

        List<JournalEntry> journalEntries = journalService.fetchAll();
        boolean journalEntryPresent = false;
        for (JournalEntry je : journalEntries) {
            if (je.getNotes().equals(notes) && je.getDate().equals(date)) {
                journalEntryPresent = true;
                break;
            }
        }

        assertTrue(journalEntryPresent);


    }

    /**
     * Validates that JournalService can return an Arraylist of JournalEntries with the given date
     */

    @Test
    void verifyThatJournalServicesFetchByDateWorksCorrectly(){
        String notes1 =  "My first entry!";
        String date1 = "October 2021";

        String notes2 = "My second entry!";
        String date2 = "October 2021";

        String notes3 = "My third entry!";
        String date3 = "November 2021";

        JournalEntry journalEntry1 = new JournalEntry();
        journalEntry1.setNotes(notes1);
        journalEntry1.setDate(date1);

        JournalEntry journalEntry2 = new JournalEntry();
        journalEntry2.setNotes(notes2);
        journalEntry2.setDate(date2);

        JournalEntry journalEntry3 = new JournalEntry();
        journalEntry3.setNotes(notes3);
        journalEntry3.setDate(date3);

        List<JournalEntry> octoberEntries = new ArrayList<JournalEntry>();
        octoberEntries.add(journalEntry1);
        octoberEntries.add(journalEntry2);

        journalService.save(journalEntry1);
        journalService.save(journalEntry2);
        journalService.save(journalEntry3);

        List<JournalEntry> returnedList = journalService.fetchByDate("October 2021");

        boolean listsAreEqual = true;
        if(returnedList.size() != octoberEntries.size()){
            assertTrue(!listsAreEqual);
        }else {
            for (JournalEntry je :
                    returnedList) {
                boolean foundMatch = false;
                for (JournalEntry oe :
                        octoberEntries) {
                    if (je.getDate().equals(oe.getDate()) && je.getNotes().equals(oe.getNotes())){
                        foundMatch = true;
                        break;
                    }
                }
                if (!foundMatch){
                    listsAreEqual = false;
                    break;
                }
            }
        }
        assertTrue(listsAreEqual);
    }
}
