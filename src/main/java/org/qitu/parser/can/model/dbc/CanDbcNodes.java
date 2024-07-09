package org.qitu.parser.can.model.dbc;

import org.qitu.parser.can.model.dbc.enums.CanDbcPartType;

import java.util.List;

/**
 * dbc 节点集合（nodes）
 *
 * @author zoudingyun
 * @since 2024/7/3 11:06
 */
public class CanDbcNodes extends CanDbcPart{

    /**
     * 节点名（node_name）
     * */
    private List<CanDbcNode> nodes;

    public CanDbcNodes() {
        this.setKeyword(CanDbcPartType.NODES.name);
    }

    public List<CanDbcNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<CanDbcNode> nodes) {
        this.nodes = nodes;
    }
}
