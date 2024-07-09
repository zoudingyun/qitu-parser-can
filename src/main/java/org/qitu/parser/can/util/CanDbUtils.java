package org.qitu.parser.can.util;

import org.qitu.parser.can.exceptions.CanDbcException;
import org.qitu.parser.can.exceptions.CanDbcFileFormatException;
import org.qitu.parser.can.model.dbc.*;
import org.qitu.parser.can.model.dbc.enums.CanDbcPartType;
import org.qitu.parser.core.util.StrUtils;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * canDb 工具类
 * <p>
 * canDb tools
 * </p>
 * @author zoudingyun
 * @since 0.0.1
 * 2024/7/5 10:58
 */
public class CanDbUtils {

    /**
     * 文本类正则
     * */
    private static final String strRegex = "^\"(.*?)\"$";

    /**
     * 分隔符
     */
    private static final String SPLIT_KEYWORD = " ";

    /**
     * 标题内容分隔符
     */
    private static final String TITLE_SPLIT_KEYWORD = ":";

    /**
     * 默认发射器节点
     * */
    private static final CanDbcNode DEFAULT_TRANSMITTER = new CanDbcNode("Vector__XXX");

    /**
     * 初始化一个CanDb
     * <br>
     * create a new canDb
     * @param dbcFilePath 需要配解析的dbc文件路径
     * */
    public static CanDb createCanDb(String dbcFilePath) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(dbcFilePath));
            return createCanDb(lines);
        } catch (IOException e) {
            throw new CanDbcException(e);
        }
    }


    /**
     * 初始化一个CanDb
     * <br>
     * create a new canDb
     * @param canDbcFileLines dbc文件中读取出的所有文本行 (All text lines read from the dbc file.)
     * */
    public static CanDb createCanDb(List<String> canDbcFileLines) {
        return new CanDb(analysisCanDbc(canDbcFileLines));
    }


    /**
     * 分析Dbc文件
     * <br>
     * analysis dbc file.
     * @param canDbcFileLines dbc文件中读取出的所有文本行 (All text lines read from the dbc file.)
     * @return 分析后的配置属性 （Analyzed configuration attributes）
     * */
    public static CanDbcProperties analysisCanDbc(List<String> canDbcFileLines) {
        CanDbcProperties canDbcProperties = new CanDbcProperties();

        CanDbcPartType partType = CanDbcPartType.UNKNOWN;
        // 遍历dbc文本行 (Traverse each line of text in the dbc file.)
        for (int i = 0; i < canDbcFileLines.size(); i++) {
            try {
                if (!StrUtils.isBlank(canDbcFileLines.get(i))) {
                    if (partType != CanDbcPartType.UNKNOWN) {
                        // 当前是新数据项（第一个字符为非空字符），还是数据项内容（第一个字符为空字符）
                        if (StrUtils.isBlank(String.valueOf(canDbcFileLines.get(i).charAt(0)))) {
                            analysisDbcPartContent(canDbcFileLines.get(i),canDbcProperties,partType);
                        }else {
                            partType = analysisDbcPart(canDbcFileLines.get(i),canDbcProperties);
                        }
                    }else {
                        // 当前没有正在分析的部分，进入第一个分析部分
                        if (StrUtils.isBlank(canDbcFileLines.get(i))) {
                            // 跳过空行
                            continue;
                        }
                        partType = analysisDbcPart(canDbcFileLines.get(i),canDbcProperties);
                    }
                }
            }catch (Exception e){
                throw new CanDbcException(String.format("An error occurred at line %s when parsing the dbc file:",i+1),e);
            }
        }


        return canDbcProperties;
    }


    /**
     * 分析数据项的内容
     * @param canDbcFileLine dbc文件行
     * @param canDbcProperties dbc文件属性
     * @param partType 当前正在分析的数据项类型
     * */
    private static void analysisDbcPartContent(String canDbcFileLine, CanDbcProperties canDbcProperties, CanDbcPartType partType){
        switch (partType){
            case NEW_SYMBOLS:{
                List<String> newSymbolList = canDbcProperties.getNewSymbols().getNewSymbolList();
                if (newSymbolList == null || newSymbolList.isEmpty()) {
                    newSymbolList = new ArrayList<>();
                }
                String[] args = StrUtils.trim(canDbcFileLine).split(SPLIT_KEYWORD);
                for (String arg : args) {
                    arg = StrUtils.trim(arg);
                    if (!StrUtils.isBlank(arg)){
                        newSymbolList.add(arg);
                    }
                }
                canDbcProperties.getNewSymbols().setNewSymbolList(newSymbolList);
                break;
            }
            default:{
                throw new CanDbcFileFormatException("format error");
            }
        }
    }


    /**
     * 分析一个新数据项
     * @param canDbcFileLine dbc文件行
     * @param canDbcProperties dbc文件属性
     * */
    private static CanDbcPartType analysisDbcPart(String canDbcFileLine, CanDbcProperties canDbcProperties){
        String[] args = canDbcFileLine.split(SPLIT_KEYWORD);
        String keyWord = null;
        List<String> argValues = new ArrayList<>();
        boolean keyWordReady = false;
        for (String arg : args) {
            if (!StrUtils.isBlank(arg)) {
                if (!keyWordReady){
                    keyWord = arg;
                    keyWordReady = true;
                }else {
                    argValues.add(arg);
                }
            }
        }
        return createCanDbcPartByKeyWord(keyWord, argValues,canDbcProperties);
    }



    /**
     * 通过关键字创建数据项
     * @param keyWord   关键字
     * @param argValues 参数
     * @param canDbcProperties dbc文件属性
     */
    private static CanDbcPartType createCanDbcPartByKeyWord(String keyWord, List<String> argValues, CanDbcProperties canDbcProperties) {

        CanDbcPartType canDbcPartType = CanDbcPartType.fromValue(keyWord);
        switch (canDbcPartType) {
            // 版本
            case VERSION:{
                canDbcProperties.setVersion(formatVersion(argValues));
                break;
            }
            // 新节点
            case NEW_SYMBOLS:{
                canDbcProperties.setNewSymbols(formatNewSymbols(argValues));
                break;
            }
            // 波特率
            case BIT_TIMING:{
                // 已过时的项，忽略
                break;
            }
            // 节点
            case NODES:{
                canDbcProperties.setNodes(formatNodes(argValues));
                break;
            }
            // 消息
            case MESSAGE:{
                canDbcProperties.setMessages(formatMessages(argValues,canDbcProperties));
                break;
            }
        }
        return canDbcPartType;
    }

    /**
     * 分析版本
     * @param argValues 配置附带的参数
     * @return 版本信息
     * */
    private static CanDbcVersion formatVersion(List<String> argValues){
        if (argValues.isEmpty()) {
            return new CanDbcVersion("");
        }else if (argValues.size() == 1) {
            Pattern pattern = Pattern.compile(strRegex);
            Matcher matcher = pattern.matcher(argValues.get(0));
            if (matcher.find()) {
                // group(1) 表示第一个括号中的匹配结果
                String match = matcher.group(1);
                return new CanDbcVersion(match);
            } else {
                throw new CanDbcFileFormatException("The value of \"VERSION\" must be an expression enclosed in double quotes.");
            }
        }else {
            throw new CanDbcFileFormatException("The value of \"VERSION\" should not have multiple instances.");
        }
    }

    /**
     * 分析新节点
     * @param argValues 配置附带的参数
     * @return 新节点信息
     * */
    private static CanDbcNewSymbols formatNewSymbols(List<String> argValues){
        CanDbcNewSymbols newSymbols = new CanDbcNewSymbols();
        List<String> newSymbolList = new ArrayList<>();
        for (String arg : argValues) {
            arg = StrUtils.trim(arg);
            if (!StrUtils.isBlank(arg)&&!TITLE_SPLIT_KEYWORD.equals(arg)) {
                newSymbolList.add(arg);
            }
        }
        newSymbols.setNewSymbolList(newSymbolList);
        return newSymbols;
    }


    /**
     * 分析节点
     * @param argValues 配置附带的参数
     * @return 节点信息
     * */
    private static CanDbcNodes formatNodes(List<String> argValues){
        CanDbcNodes nodes = new CanDbcNodes();
        if (argValues.isEmpty()) {
            nodes.setNodeList(new ArrayList<>());
        }else {
            List<CanDbcNode> nodeList = new ArrayList<>();
            for (String arg : argValues) {
                arg = StrUtils.trim(arg);
                if (!StrUtils.isBlank(arg)&&!TITLE_SPLIT_KEYWORD.equals(arg)) {
                    nodeList.add(new CanDbcNode(arg));
                }
            }
            nodes.setNodeList(nodeList);
        }
        return nodes;
    }

    /**
     * 分析消息定义
     * @param argValues 配置附带的参数
     * @param canDbcProperties dbc信息集
     * @return 消息定义
     * */
    private static CanDbcMessages formatMessages(List<String> argValues, CanDbcProperties canDbcProperties){
        CanDbcMessages messages = canDbcProperties.getMessages();
        if (messages == null) {
            messages = new CanDbcMessages();
        }
        CanDbcMessage message = new CanDbcMessage();

        if (argValues.isEmpty()) {
            throw new CanDbcFileFormatException("The configuration information format of the message is abnormal.");
        }else {
            // 检查名称后面是不是跟着冒号
            if (argValues.size() == 4) {
                if (argValues.get(1).lastIndexOf(TITLE_SPLIT_KEYWORD) == (argValues.get(1).length()-1)) {
                    // 如果消息名后面跟着冒号
                    argValues.set(1,argValues.get(1).substring(0, argValues.get(1).length() - 1));
                    argValues.add(2, TITLE_SPLIT_KEYWORD);
                }else {
                    throw new CanDbcFileFormatException("The configuration information format of the message is abnormal.");
                }
            }
            for (int i = 0; i < argValues.size(); i++) {
                if (!StrUtils.isBlank(argValues.get(i))) {
                    switch (i) {
                        case 0:{
                            // 此配置为消息id
                            message.setMessageId(new BigInteger(argValues.get(i)));
                            break;
                        }
                        case 1:{
                            // 此配置为消息名
                            message.setMessageName(argValues.get(i));
                            break;
                        }
                        case 2:{
                            // 此为冒号分隔符
                            if (!TITLE_SPLIT_KEYWORD.equals(argValues.get(i))){
                                throw new CanDbcFileFormatException(String.format("The configuration information of the message does not meet expectations. The expected value is \"%s\", but the actual value is \"%s\".",TITLE_SPLIT_KEYWORD,argValues.get(i)));
                            }
                            break;
                        }
                        case 3:{
                            // 此为消息长度
                            message.setMessageSize(Integer.parseInt(argValues.get(i)));
                            break;
                        }
                        case 4:{
                            // 此为发送器
                            CanDbcNodes nodes = canDbcProperties.getNodes();
                            for (CanDbcNode node : nodes.getNodeList()) {
                                if (node.getName().equals(argValues.get(i))) {
                                    message.setTransmitter(node);
                                    break;
                                }
                            }
                            if (DEFAULT_TRANSMITTER.getName().equals(argValues.get(i))){
                                message.setTransmitter(DEFAULT_TRANSMITTER);
                            }
                            if (message.getTransmitter() == null) {
                                throw new CanDbcFileFormatException(String.format("Unknown transmitter node name:'%s'", argValues.get(i)));
                            }
                        }
                        default:{
                            break;
                        }
                    }
                }
            }
        }
        messages.getMessageList().add(message);
        return messages;
    }

}
