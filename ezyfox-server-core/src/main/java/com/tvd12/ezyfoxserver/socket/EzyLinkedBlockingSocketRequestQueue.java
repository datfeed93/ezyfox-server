package com.tvd12.ezyfoxserver.socket;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class EzyLinkedBlockingSocketRequestQueue extends EzyBlockingSocketRequestQueue {

    @Override
    protected BlockingQueue<EzySocketRequest> newQueue(int capacity) {
        return new LinkedBlockingQueue<>(capacity);
    }

}
