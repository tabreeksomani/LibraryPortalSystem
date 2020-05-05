package model;

import javax.swing.*;

public interface IIssuableItem {
    @SuppressWarnings("checkstyle:MethodParamPad")
    void issueItem() throws ItemNotIssuableException;


    class ItemNotIssuableException extends Exception {
    }
}
