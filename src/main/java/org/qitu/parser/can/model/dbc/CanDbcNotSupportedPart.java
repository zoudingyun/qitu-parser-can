package org.qitu.parser.can.model.dbc;

import org.qitu.parser.can.model.dbc.enums.CanDbcPartType;

import java.util.List;

/**
 * dbc 已知但暂不支持的配置
 *
 * @author zoudingyun
 * @since 2024/7/3 11:06
 */
public class CanDbcNotSupportedPart extends CanDbcPart{

    /**
     * 参数（arguments）
     * */
    private List<String> arguments;

    public CanDbcNotSupportedPart(CanDbcPartType type) {
        this.setKeyword(type.name);
    }

    public List<String> getArguments() {
        return arguments;
    }

    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }
}
