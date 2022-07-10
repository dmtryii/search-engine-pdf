package com.dmtryii.engine;

public class PageEntry implements Comparable<PageEntry>{
    private final String name;
    private final int page;
    private final int count;

    public PageEntry(String name, int page, int count){
        this.name = name;
        this.page = page;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getPage() {
        return page;
    }

    public int getCount() {
        return count;
    }

    @Override
    public int compareTo(PageEntry o) {
        return this.count - o.count;
    }

    @Override
    public String toString(){
        return "PageEntry{" + "pdfName=" + name + ", page=" + page + ", count=" + count + '}';
    }
}
