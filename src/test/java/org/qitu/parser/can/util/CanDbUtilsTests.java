package org.qitu.parser.can.util;

import org.junit.jupiter.api.Test;
import org.qitu.parser.can.model.dbc.CanDb;
import org.qitu.parser.can.model.dbc.CanDbcMessages;
import org.qitu.parser.can.model.dbc.CanDbcSignal;
import org.qitu.parser.can.model.dbc.enums.CanDbcSignalMultiplexerType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * CanDb工具类测试
 *
 * @author zoudingyun
 * @since 2024/7/8 11:38
 */
public class CanDbUtilsTests {

    @Test
    public void test(){

        CanDb db = CanDbUtils.createCanDb("D:\\test\\test.dbc");

        // 版本
        assertEquals(db.getCanDbcProperties().getVersion().getVersion(),"");
        assertEquals(db.getCanDbcProperties().getVersion().getKeyword(),"VERSION");

        // 新节点
        List<String> newSymbolList = getNewSymbolStrings();
        assertArrayEquals(db.getCanDbcProperties().getNewSymbols().getNewSymbolList().toArray(new String[0]), newSymbolList.toArray(new String[0]));

        // 节点
        String[] rawNodes = "Receiver ChassisBus VehicleBus PartyBus".split(" ");
        for (int i = 0; i < db.getCanDbcProperties().getNodes().getNodeList().size(); i++) {
            assertEquals(db.getCanDbcProperties().getNodes().getNodeList().get(i).getName(),rawNodes[i]);
        }

        // 消息1
        CanDbcMessages messages = db.getCanDbcProperties().getMessages();
        assertEquals(messages.getMessageList().get(0).getMessageId(),new BigInteger("12"));
        assertEquals(messages.getMessageList().get(0).getMessageName(),"ID00CUI_status");
        assertEquals(messages.getMessageList().get(0).getMessageSize(),8);
        assertEquals(messages.getMessageList().get(0).getTransmitter().getName(),"VehicleBus");

        // 信号1
        List<CanDbcSignal> signals = messages.getMessageList().get(0).getSignalList();
        assertEquals(signals.get(0).getSignalName(),"UI_audioActive");
        assertEquals(signals.get(1).getMultiplexerIndicator(), CanDbcSignalMultiplexerType.SIGNAL);
        assertEquals(signals.get(2).getStartBit(),2);
        assertEquals(signals.get(3).getSignalSize(),1);
        assertEquals(signals.get(4).getByteOrder(),true);
        assertEquals(signals.get(5).getValueType(),true);
        assertEquals(signals.get(6).getFactor(),new BigDecimal("1"));
        assertEquals(signals.get(7).getOffset(),new BigDecimal("-128"));
        assertEquals(signals.get(8).getMinValue(),new BigDecimal("0"));
        assertEquals(signals.get(9).getMaxValue(),new BigDecimal("100"));
        assertEquals(signals.get(10).getUnit(),"w");
        assertEquals(signals.get(11).getReceiver().getName(),"Receiver");

        // 消息2
        assertEquals(messages.getMessageList().get(1).getMessageId(),new BigInteger("1025"));
        assertEquals(messages.getMessageList().get(1).getMessageName(),"ID401BrickVoltages");
        assertEquals(messages.getMessageList().get(1).getMessageSize(),8);
        assertEquals(messages.getMessageList().get(1).getTransmitter().getName(),"VehicleBus");
        assertEquals(messages.getMessageList().get(1).getSignalList().size(),2);

        // 信号2
        signals = messages.getMessageList().get(1).getSignalList();
        assertEquals(signals.get(0).getMultiplexerIndicator(),CanDbcSignalMultiplexerType.MULTIPLEXER);
        assertEquals(signals.get(0).getMultiplexedSignalMap().size(),37);
        assertEquals(signals.get(1).getMultiplexerIndicator(),CanDbcSignalMultiplexerType.SIGNAL);

        // 额外的信号发送器
        assertEquals(messages.getMessageList().get(0).getExtraTransmitters().size(),2);
        assertEquals(messages.getMessageList().get(0).getExtraTransmitters().get(0).getName(),"ChassisBus");
        assertEquals(messages.getMessageList().get(0).getExtraTransmitters().get(1).getName(),"VehicleBus");

        int a = 0;

    }

    private static List<String> getNewSymbolStrings() {
        String raw = "NS_DESC_\n" +
                "\tCM_\n" +
                "\tBA_DEF_\n" +
                "\tBA_\n" +
                "\tVAL_\n" +
                "\tCAT_DEF_\n" +
                "\tCAT_\n" +
                "\tFILTER\n" +
                "\tBA_DEF_DEF_\n" +
                "\tEV_DATA_\n" +
                "\tENVVAR_DATA_\n" +
                "\tSGTYPE_\n" +
                "\tSGTYPE_VAL_\n" +
                "\tBA_DEF_SGTYPE_\n" +
                "\tBA_SGTYPE_\n" +
                "\tSIG_TYPE_REF_\n" +
                "\tVAL_TABLE_\n" +
                "\tSIG_GROUP_\n" +
                "\tSIG_VALTYPE_\n" +
                "\tSIGTYPE_VALTYPE_\n" +
                "\tBO_TX_BU_\n" +
                "\tBA_DEF_REL_\n" +
                "\tBA_REL_\n" +
                "\tBA_DEF_DEF_REL_\n" +
                "\tBU_SG_REL_\n" +
                "\tBU_EV_REL_\n" +
                "\tBU_BO_REL_\n" +
                "\tSG_MUL_VAL_";
        List<String> newSymbolList = Arrays.asList(raw.split("\n\t"));
        return newSymbolList;
    }

}
