package com.itao.linux.bean;

import java.util.List;

public class Command {

    private String title;

    private String grammarFormat;

    private List<Param> params;

    private boolean exist;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGrammarFormat() {
        return grammarFormat;
    }

    public void setGrammarFormat(String grammarFormat) {
        this.grammarFormat = grammarFormat;
    }

    public List<Param> getParams() {
        return params;
    }

    public void setParams(List<Param> params) {
        this.params = params;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }


    public void clear(){
        this.title = null;
        this.grammarFormat = null;
        this.params = null;
        this.exist = false;
    }

    @Override
    public String toString() {
        return "Command{" +
                "title='" + title + '\'' +
                ", grammarFormat='" + grammarFormat + '\'' +
                ", params=" + params +
                ", exist=" + exist +
                '}';
    }
}
