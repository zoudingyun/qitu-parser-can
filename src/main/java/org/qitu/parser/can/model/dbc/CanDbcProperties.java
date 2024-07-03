package org.qitu.parser.can.model.dbc;

import java.util.List;

/**
 * DBC configuration for CAN message
 *
 * @author zoudingyun
 * @since 0.0.1
 * 2024/7/1 10:44
 */
public class CanDbcProperties {

    /**
     * 版本 (version)
     * <p>
     * 必须有，但是多半为空 (This section is required but is normally empty.)
     * */
    private String version;

    /**
     * 新节点（New Symbols）
     * <p>
     *  基本固定为以下内容：<br>
     *  NS_DESC_<br>
     * 	CM_<br>
     * 	BA_DEF_<br>
     * 	BA_<br>
     * 	VAL_<br>
     * 	CAT_DEF_<br>
     * 	CAT_<br>
     * 	FILTER<br>
     * 	BA_DEF_DEF_<br>
     * 	EV_DATA_<br>
     * 	ENVVAR_DATA_<br>
     * 	SGTYPE_<br>
     * 	SGTYPE_VAL_<br>
     * 	BA_DEF_SGTYPE_<br>
     * 	BA_SGTYPE_<br>
     * 	SIG_TYPE_REF_<br>
     * 	VAL_TABLE_<br>
     * 	SIG_GROUP_<br>
     * 	SIG_VALTYPE_<br>
     * 	SIGTYPE_VALTYPE_<br>
     * 	BO_TX_BU_<br>
     * 	BA_DEF_REL_<br>
     * 	BA_REL_<br>
     * 	BA_DEF_DEF_REL_<br>
     * 	BU_SG_REL_<br>
     * 	BU_EV_REL_<br>
     * 	BU_BO_REL_<br>
     * 	SG_MUL_VAL_<br>
     * */
    private List<String> newSymbols;

    /**
     * 位计时器（Bit Timing Definition）
     * <p>
     * 已弃用
     * */
    private Integer baudrate;

    /**
     * 节点定义（Node Definitions）
     * */
    private List<CanDbcNode> nodes;

    /**
     * 消息集 （Messages, BO_）
     * */
    private List<CanDbcMessage> messages;
}
