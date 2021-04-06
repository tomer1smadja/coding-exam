package com.cautils.models;

public class Result {
    private boolean isValid = false;
    private final String rawResult;
    public Result(String resultString) {
        this.rawResult = resultString;
        parseResult(resultString);
    }

    private void parseResult(String resultString) {
        if (resultString.contains("valid"))
            this.isValid = true;
    }

    public boolean isValid() {
        return isValid;
    }

    public String getRawResult() {
        return rawResult;
    }
}
