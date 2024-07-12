package org.qitu.parser.can.model.dbc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * DBC configuration for CAN message
 *
 * @author zoudingyun
 * @since 0.0.1
 * 2024/7/1 10:44
 */
public class CanDbcProperties implements CanDbcAttributeCreator{

    /**
     * 版本 (version)
     * <p>
     * 必须有，但是多半为空 (This section is required but is normally empty.)
     * */
    private CanDbcVersion version;

    /**
     * 新节点（New Symbols）
     * */
    private CanDbcNewSymbols newSymbols;

    /**
     * 节点定义（Node Definitions）
     * */
    private CanDbcNodes nodes;

    /**
     * 消息集 （Messages, BO_）
     * */
    private CanDbcMessages messages;




    /**
     * 自定义属性设定集
     * */
    private List<CanDbcAttributeDefinition> attributeDefinitions;


    public CanDbcVersion getVersion() {
        return version;
    }

    public void setVersion(CanDbcVersion version) {
        this.version = version;
    }

    public CanDbcNewSymbols getNewSymbols() {
        return newSymbols;
    }

    public void setNewSymbols(CanDbcNewSymbols newSymbols) {
        this.newSymbols = newSymbols;
    }

    public CanDbcNodes getNodes() {
        return nodes;
    }

    public void setNodes(CanDbcNodes nodes) {
        this.nodes = nodes;
    }

    public CanDbcMessages getMessages() {
        return messages;
    }

    public void setMessages(CanDbcMessages messages) {
        this.messages = messages;
    }

    /**
     * 添加一个自定义属性的定义
     *
     * @param attributeDefinition 定义信息
     */
    @Override
    public void addAttributeDefinition(CanDbcAttributeDefinition attributeDefinition) {
        if (this.attributeDefinitions == null){
            this.attributeDefinitions = new ArrayList<CanDbcAttributeDefinition>();
        }
        this.attributeDefinitions.add(attributeDefinition);
    }

    /**
     * 更新一个属性的定义
     *
     * @param attributeDefinition 定义信息
     */
    @Override
    public void updateAttributeDefinition(CanDbcAttributeDefinition attributeDefinition) {
        if (this.attributeDefinitions != null){
            // 使用 Iterator 进行遍历和删除
            Iterator<CanDbcAttributeDefinition> iterator = this.attributeDefinitions.iterator();
            while (iterator.hasNext()) {
                CanDbcAttributeDefinition element = iterator.next();
                if (attributeDefinition.getAttributeName().equals(element.getAttributeName())) {
                    iterator.remove();
                    this.attributeDefinitions.add(attributeDefinition);
                    break;
                }
            }
        }
    }

    /**
     * 查询一个自定义属性的定义
     *
     * @param canDbcAttributeName 属性名称
     * @return 查询到的属性定义
     */
    @Override
    public CanDbcAttributeDefinition loadAttributeDefinitionByName(String canDbcAttributeName) {
        if (this.attributeDefinitions != null){
            for (CanDbcAttributeDefinition element : this.attributeDefinitions) {
                if (canDbcAttributeName.equals(element.getAttributeName())) {
                    return element;
                }
            }
        }
        return null;
    }

    /**
     * 删除一个自定义属性的定义
     *
     * @param canDbcAttributeName 属性名称
     */
    @Override
    public void delAttributeDefinitionByName(String canDbcAttributeName) {
        if (this.attributeDefinitions != null){
            // 使用 Iterator 进行遍历和删除
            Iterator<CanDbcAttributeDefinition> iterator = this.attributeDefinitions.iterator();
            while (iterator.hasNext()) {
                CanDbcAttributeDefinition element = iterator.next();
                if (canDbcAttributeName.equals(element.getAttributeName())) {
                    iterator.remove();
                    break;
                }
            }
        }
    }

    /**
     * 重置自定义属性的定义集
     *
     * @param attributeDefinitions 属性集
     */
    @Override
    public void setAttributeDefinitions(List<CanDbcAttributeDefinition> attributeDefinitions) {
        this.attributeDefinitions = attributeDefinitions;
    }

    /**
     * 获取自定义属性的定义集
     *
     * @return 属性集
     */
    @Override
    public List<CanDbcAttributeDefinition> getAttributeDefinitions() {
        return this.attributeDefinitions;
    }

    /**
     * 清除自定义属性的定义集
     */
    @Override
    public void removeAttributeDefinitions() {
        this.attributeDefinitions = new ArrayList<>();
    }
}
