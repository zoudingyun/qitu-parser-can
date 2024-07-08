package org.qitu.parser.can.model.dbc;

import org.qitu.parser.can.model.dbc.enums.CanDbcPartType;

import java.util.Arrays;
import java.util.List;

/**
 * 新节点（NewSymbols）
 *
 * @author zoudingyun
 * @since 2024/7/5 12:02
 */
public class CanDbcNewSymbols extends CanDbcPart {

    /**
     * 新节点列表（New Symbols）
     * <p>
     * 基本为固定内容
     * */
    private List<String> newSymbols;

    public List<String> getNewSymbols() {
        return newSymbols;
    }

    public void setNewSymbols(List<String> newSymbols) {
        this.newSymbols = newSymbols;
        setKeyword(CanDbcPartType.NEW_SYMBOLS.toString());
    }

    public void init() {
        this.newSymbols = Arrays.asList(
                "NS_DESC_",
                "CM_",
                "BA_DEF_",
                "BA_",
                "VAL_",
                "CAT_DEF_",
                "CAT_",
                "FILTER",
                "BA_DEF_DEF_",
                "EV_DATA_",
                "ENVVAR_DATA_",
                "SGTYPE_",
                "SGTYPE_VAL_",
                "BA_DEF_SGTYPE_",
                "BA_SGTYPE_",
                "SIG_TYPE_REF_",
                "VAL_TABLE_",
                "SIG_GROUP_",
                "SIG_VALTYPE_",
                "SIGTYPE_VALTYPE_",
                "BO_TX_BU_",
                "BA_DEF_REL_",
                "BA_REL_",
                "BA_DEF_DEF_REL_",
                "BU_SG_REL_",
                "BU_EV_REL_",
                "BU_BO_REL_",
                "SG_MUL_VAL_"
        );
    }
}
