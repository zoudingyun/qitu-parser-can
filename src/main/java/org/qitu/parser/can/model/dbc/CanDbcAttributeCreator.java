package org.qitu.parser.can.model.dbc;

import java.util.List;

/**
 * 支持自定义属性的数据项需要实现此接口
 *
 * @author zoudingyun
 * @since 2024/7/8 15:47
 */
public interface CanDbcAttributeCreator {

    /**
     * 添加一个自定义属性的定义
     * @param attributeDefinition 定义信息
     * */
    void addAttributeDefinition(CanDbcAttributeDefinition attributeDefinition);

    /**
     * 更新一个属性的定义
     * @param attributeDefinition 定义信息
     * */
    void updateAttributeDefinition(CanDbcAttributeDefinition attributeDefinition);

    /**
     * 查询一个自定义属性的定义
     * @param canDbcAttributeName 属性名称
     * @return 查询到的属性定义
     * */
    CanDbcAttributeDefinition loadAttributeDefinitionByName(String canDbcAttributeName);

    /**
     * 删除一个自定义属性的定义
     * @param canDbcAttributeName 属性名称
     * */
    void delAttributeDefinitionByName(String canDbcAttributeName);

    /**
     * 重置自定义属性的定义集
     * @param attributeDefinitions 属性集
     * */
    void setAttributeDefinitions(List<CanDbcAttributeDefinition> attributeDefinitions);

    /**
     * 获取自定义属性的定义集
     * @return 属性集
     * */
    List<CanDbcAttributeDefinition> getAttributeDefinitions();

    /**
     * 清除自定义属性的定义集
     * */
    void removeAttributeDefinitions();

}
