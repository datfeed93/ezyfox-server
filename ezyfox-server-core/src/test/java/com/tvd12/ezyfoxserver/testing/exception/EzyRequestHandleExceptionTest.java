package com.tvd12.ezyfoxserver.testing.exception;

import org.mockito.Mockito;
import org.testng.annotations.Test;

import com.tvd12.ezyfoxserver.constant.EzyCommand;
import com.tvd12.ezyfoxserver.entity.EzySession;
import com.tvd12.ezyfoxserver.exception.EzyRequestHandleException;
import com.tvd12.test.base.BaseTest;

public class EzyRequestHandleExceptionTest extends BaseTest {

    @Test(expectedExceptions = {EzyRequestHandleException.class})
    public void test() {
        EzySession session = Mockito.mock(EzySession.class);
        Mockito.when(session.getName()).thenReturn("hello world");
        throw EzyRequestHandleException
            .requestHandleException(session, EzyCommand.LOGIN, new Object(), new Exception());
    }
    
}
