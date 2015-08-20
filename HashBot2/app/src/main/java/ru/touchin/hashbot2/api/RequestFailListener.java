package ru.touchin.hashbot2.api;

import java.util.List;

public interface RequestFailListener {

    public void onRequestFailure(List<Exception> exceptionList);

}
