package com.jfarro.app.editors;

import java.beans.PropertyEditorSupport;

public class StringUppercaseEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        this.setValue(text.trim().toUpperCase());
    }
}
