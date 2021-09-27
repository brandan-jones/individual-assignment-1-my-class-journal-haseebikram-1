package edu.uc.cech.soit.myclassjournal.service;

import edu.uc.cech.soit.myclassjournal.dto.JournalEntry;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class journalService implements IJournalService {

    private List<JournalEntry> entries = new ArrayList<>();

    @Override
    public void save(JournalEntry journalEntry) {
    entries.add(journalEntry);
    }

    @Override
    public List<JournalEntry> fetchAll() {
        return this.entries;
    }

    @Override
    public List<JournalEntry> fetchByDate(String date){return this.entries.stream().filter(x -> x.getDate().equals(date)).collect(Collectors.toList());}
}
