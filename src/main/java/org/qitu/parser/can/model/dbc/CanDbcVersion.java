package org.qitu.parser.can.model.dbc;

import org.qitu.parser.can.model.dbc.enums.CanDbcPartType;

/**
 * dbc版本（version）
 *
 * @author zoudingyun
 * @since 2024/7/3 11:06
 */
public class CanDbcVersion extends CanDbcPart{

    /**
     * 版本（version）
     * */
    private String version;

    public CanDbcVersion(String version){
        this.version = version;
        setKeyword(CanDbcPartType.VERSION.toString());
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
