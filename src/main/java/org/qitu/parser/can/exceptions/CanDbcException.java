package org.qitu.parser.can.exceptions;

import org.qitu.parser.core.exceptions.util.ExceptionUtils;

/**
 * Dbc文件操作异常
 *
 * @author zoudingyun
 * @since 2024/7/3 16:15
 */
public class CanDbcException extends RuntimeException{

    public CanDbcException(Throwable e) {
        super(ExceptionUtils.getMessage(e), e);
    }

    public CanDbcException(String message) {
        super(message);
    }

    public CanDbcException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
