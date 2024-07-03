package org.qitu.parser.can.exceptions;


/**
 * Dbc文件操作时传入了无效参数
 *
 * @author zoudingyun
 * @since 2024/7/3 16:15
 */
public class CanDbcInvalidParameterException extends CanDbcException{


    public CanDbcInvalidParameterException(Throwable e) {
        super(e);
    }

    public CanDbcInvalidParameterException(String message) {
        super(message);
    }

    public CanDbcInvalidParameterException(String message, Throwable throwable) {
        super(message, throwable);
    }


}
