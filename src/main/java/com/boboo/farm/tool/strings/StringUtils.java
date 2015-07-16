package com.boboo.farm.tool.strings;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 文字组装
 * <p/>
 * Created by 博博 on 2015/7/14.
 */
public class StringUtils
{
    private HanyuPinyinOutputFormat mPinyinFormat;

    /**
     * 为转换后的拼音结果设定一个合适的拼写格式
     *
     * @param format 输出后的拼写格式
     */
    public void setPinyinOutputFormat(HanyuPinyinOutputFormat format)
    {
        mPinyinFormat = format;
    }

    /**
     * 获取一个字符串的首个字符对应的首字母
     * <p/>
     * <pre>
     *     String source = "这是一个字符串源。";
     *     return "这"的首字母"z"
     * </pre>
     *
     * @param source 字符串源
     * @return 返回被解析后的首字母小写，若解析失败或没有解析到正确的结果则返回<code>null</code>
     * @throws NullPointerException                  若参数为<code>null</code>或者空的字符串，则抛出该异常！
     * @throws BadHanyuPinyinOutputFormatCombination 错误的拼音格式输出错误，抛出该异常
     */
    public String getFirstLetter(String source) throws BadHanyuPinyinOutputFormatCombination
    {
        if (null == source || source.isEmpty())
            throw new NullPointerException("参数源是空的字符串或者为null值。");
        if (null == mPinyinFormat)
            this.initHanyuPinyinFormat();
        char result = this.parseCharToFirstLetter(source.charAt(0));
        return String.valueOf(result).trim();
    }

    /**
     * 获取一个字符串中每个文字的首字母
     * <pre>
     *     String source = "这是一个字符串源。";
     *     return "zsygzfcy"
     * </pre>
     *
     * @param source 字符串源
     * @return 返回被解析后的首字母小写集合
     * @throws NullPointerException                  若参数为<code>null</code>或者空的字符串，则抛出该异常！
     * @throws BadHanyuPinyinOutputFormatCombination 错误的拼音格式输出错误，抛出该异常
     */
    public String getFirstLetterByFull(String source) throws BadHanyuPinyinOutputFormatCombination
    {
        if (null == source || source.isEmpty())
            throw new NullPointerException("参数源是空的字符串或者为null值。");
        if (null == mPinyinFormat)
            this.initHanyuPinyinFormat();
        StringBuilder letters = new StringBuilder();
        for (int i = 0; i < source.length(); i++)
        {
            char c = source.charAt(i);
            char result = this.parseCharToFirstLetter(c);
            letters.append(String.valueOf(result).trim());
        }
        return letters.toString();
    }

    private void initHanyuPinyinFormat()
    {
        mPinyinFormat = new HanyuPinyinOutputFormat();
        mPinyinFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        mPinyinFormat.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
    }

    private char parseCharToFirstLetter(char source) throws BadHanyuPinyinOutputFormatCombination
    {
        if (String.valueOf(source).matches("^[a-zA-Z]$"))
            return source;
        String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(source, mPinyinFormat);
        if (pinyinArray != null && pinyinArray.length > 0)
        {
            // 拼音数组中是对一个字的解析，表示首字母都是相同的，因此只需要获取第一个位置上的结果
            String pinyin = pinyinArray[0];
            return pinyin.charAt(0);
        }
        return '\t';
    }
}
