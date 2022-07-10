package com.dmtryii.engine;

import java.util.List;

public interface SearchEngine {
    List<PageEntry> search(String searchWord);
}
