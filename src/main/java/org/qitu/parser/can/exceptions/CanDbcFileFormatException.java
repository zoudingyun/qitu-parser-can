package org.qitu.parser.can.exceptions;


/**
 * Dbc文件格式化错误
 *
 * @author zoudingyun
 * @since 2024/7/3 16:15
 */
public class CanDbcFileFormatException extends CanDbcException{


    public CanDbcFileFormatException(Throwable e) {
        super(e);
    }

    public CanDbcFileFormatException(String message) {
        super(message);
    }

    public CanDbcFileFormatException(String message, Throwable throwable) {
        super(message, throwable);
    }


}
