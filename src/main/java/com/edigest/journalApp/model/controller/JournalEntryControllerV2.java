package com.edigest.journalApp.model.controller;

import com.edigest.journalApp.model.entity.JournalEntry;
import com.edigest.journalApp.model.services.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping("")
    public List<JournalEntry> getAll(){
        return journalEntryService.getAll() ;
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry ){
        myEntry.setTime(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return true;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId myId){
        return journalEntryService.getUserById(myId).orElse(null);
    }

    @DeleteMapping("id/{myId}")
    public boolean deleteEntryById(@PathVariable ObjectId myId){
        journalEntryService.deleteEntryById(myId);
        return true;
    }

    @PutMapping("id/{myId}")
    public JournalEntry updateById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry){

        JournalEntry oldEntry = journalEntryService.getUserById(myId).orElse(null);
        if (oldEntry != null){
            oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getTitle().equals("") ? newEntry.getContent() : oldEntry.getContent());
        }
        journalEntryService.saveEntry(oldEntry);
        return oldEntry;
    }
}
