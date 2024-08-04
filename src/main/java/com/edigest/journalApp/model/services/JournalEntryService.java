package com.edigest.journalApp.model.services;

import com.edigest.journalApp.model.entity.JournalEntry;
import com.edigest.journalApp.model.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getUserById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteEntryById(ObjectId id){
        journalEntryRepository.deleteById(id);
    }

}
