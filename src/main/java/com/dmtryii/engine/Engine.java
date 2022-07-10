package com.dmtryii.engine;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Engine implements SearchEngine{
    private final Map<String, List<PageEntry>> idx; // Map for recording results

    public Engine(File dir) throws IOException {
        idx = new HashMap<>();
        for(File file: dir.listFiles()){ // Browsing files in a directory
            PdfDocument doc = new PdfDocument(new PdfReader(file));
            for (int i = 1; i <= doc.getNumberOfPages(); i++){
                String text = PdfTextExtractor.getTextFromPage(doc.getPage(i));
                String[] words = text.split("\\P{IsAlphabetic}+");
                // A set of words with their counts for the current page
                Map<String, Integer> freqs = new HashMap<>();
                for(String word: words){
                    if(!word.isBlank()) {
                        freqs.put(word.toLowerCase(), freqs.getOrDefault(word, 0) + 1);
                    }
                }
                // We get a set of occurrences from the previous map to iterate over this set
                Set<Map.Entry<String, Integer>> currPageWordSet = freqs.entrySet();
                for(Map.Entry<String, Integer> freqsEntry: currPageWordSet){
                    /*
                    If such a word was found in the map, then it means that it is displayed on an already existing list,
                    which we get from the index and add a new element to it, respectively meanings of a word,
                    page number, number per page
                     */
                    if(idx.containsKey(freqsEntry.getKey())){
                        idx.get(freqsEntry.getKey()).add(new PageEntry(file.getName(), i, freqsEntry.getValue()));
                    } else {
                        // Otherwise, create a new list, add the first element to it
                        List<PageEntry> currEntryList = new ArrayList<>();
                        currEntryList.add(new PageEntry(file.getName(), i, freqsEntry.getValue()));
                        idx.put(freqsEntry.getKey(), currEntryList);
                    }
                }
            }
        }
        // Sort the lists in the index by each key
        Set<String> keySet = idx.keySet();
        for (String str: keySet){
            Collections.sort(idx.get(str), Comparator.reverseOrder());
        }
    }

    @Override
    public List<PageEntry> search(String searchWord) {
        searchWord = searchWord.toLowerCase(); // Convert the word to lowercase
        if (idx.containsKey(searchWord)){
            return idx.get(searchWord);
        } else {
            // If the word is not found, then return a list with one element with default parameters
            List<PageEntry> defaultList = new ArrayList<>();
            defaultList.add(new PageEntry("Defunct.pdf", 0, 0));
            return defaultList;
        }
    }

    public Map<String, List<PageEntry>> getIdx() {
        return idx;
    }
}
