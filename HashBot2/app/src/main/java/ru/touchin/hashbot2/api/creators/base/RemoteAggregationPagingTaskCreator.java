package ru.touchin.hashbot2.api.creators.base;

import org.zuzuk.providers.base.PagingTaskCreator;

import ru.touchin.hashbot2.api.RequestFailListener;

public abstract class RemoteAggregationPagingTaskCreator<TItem> implements PagingTaskCreator<TItem> {

    private final RequestFailListener requestFailListener;

    protected RemoteAggregationPagingTaskCreator(RequestFailListener requestFailListener) {
        this.requestFailListener = requestFailListener;
    }

    public RequestFailListener getRequestFailListener() {
        return requestFailListener;
    }

}
