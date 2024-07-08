package org.qitu.parser.can.util;

import org.qitu.parser.can.exceptions.CanDbcException;
import org.qitu.parser.can.exceptions.CanDbcFileFormatException;
import org.qitu.parser.can.model.dbc.*;
import org.qitu.parser.can.model.dbc.enums.CanDbcPartTypes;
import org.qitu.parser.core.util.StrUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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
     * 分隔符
     */
    private static final String SPLIT_KEYWORD = " ";

    /**
     * 初始化一个CanDb
     * <br>
     * create a new canDb
     * @param dbcFilePath 需要配解析的dbc文件路径
     * */
    public static CanDb createCanDb(String dbcFilePath) {
        List<String> lines = new ArrayList<>();
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

        CanDbcPartTypes partType = CanDbcPartTypes.UNKNOWN;
        // 遍历dbc文本行 (Traverse each line of text in the dbc file.)
        for (int i = 0; i < canDbcFileLines.size(); i++) {
            try {
                if (StrUtils.isBlank(canDbcFileLines.get(i))) {
                    continue;
                }else {
                    if (partType != CanDbcPartTypes.UNKNOWN) {
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
    private static void analysisDbcPartContent(String canDbcFileLine,CanDbcProperties canDbcProperties,CanDbcPartTypes partType){
        switch (partType){
            case NEW_SYMBOLS:{
                List<String> newSymbolList = canDbcProperties.getNewSymbols().getNewSymbols();
                if (newSymbolList == null || newSymbolList.isEmpty()) {
                    newSymbolList = new ArrayList<>();
                }
                String[] args = StrUtils.trim(canDbcFileLine).split(SPLIT_KEYWORD);
                newSymbolList.addAll(Arrays.asList(args));
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
    private static CanDbcPartTypes analysisDbcPart(String canDbcFileLine,CanDbcProperties canDbcProperties){
        String[] args = canDbcFileLine.split(SPLIT_KEYWORD);
        String keyWord = null;
        List<String> argValues = new ArrayList<String>();
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
    private static CanDbcPartTypes createCanDbcPartByKeyWord(String keyWord, List<String> argValues, CanDbcProperties canDbcProperties) {
        // 文本类
        String strRegex = "^\"(.*?)\"$";
        CanDbcPartTypes canDbcPartType = CanDbcPartTypes.fromValue(keyWord);
        switch (canDbcPartType) {
            // 版本
            case VERSION:{
                if (argValues.isEmpty()) {
                    new CanDbcVersion("");
                }else if (argValues.size() == 1) {
                    Pattern pattern = Pattern.compile(strRegex);
                    Matcher matcher = pattern.matcher(argValues.get(0));
                    if (matcher.find()) {
                        // group(1) 表示第一个括号中的匹配结果
                        String match = matcher.group(1);
                        canDbcProperties.setVersion(new CanDbcVersion(match));
                    } else {
                        throw new CanDbcFileFormatException("The value of \"VERSION\" must be an expression enclosed in double quotes.");
                    }

                }else {
                    throw new CanDbcFileFormatException("The value of \"VERSION\" should not have multiple instances.");
                }
                break;
            }
            // 新节点
            case NEW_SYMBOLS:{
                CanDbcNewSymbols newSymbols = new CanDbcNewSymbols();
                newSymbols.setNewSymbols(argValues);
                canDbcProperties.setNewSymbols(newSymbols);
                break;
            }
        }
        return canDbcPartType;
    }

}
