package org.qitu.parser.can.model.dbc;

/**
 * dbc节点（node）
 *
 * @author zoudingyun
 * @since 2024/7/3 11:06
 */
public class CanDbcNode{

    /**
     * 节点名（node_name）
     * */
    private String name;

    public CanDbcNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
