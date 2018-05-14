package com.mogu.GEMAKER.model.params;

import java.util.List;
import java.util.Map;

public class DowndataParam {
    private String terminal;
    private List<Map<String,Object>> rcds;

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public List<Map<String, Object>> getRcds() {
        return rcds;
    }

    public void setRcds(List<Map<String, Object>> rcds) {
        this.rcds = rcds;
    }
}
