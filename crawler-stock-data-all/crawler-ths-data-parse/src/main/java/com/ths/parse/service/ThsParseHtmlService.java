package com.ths.parse.service;

import java.util.HashMap;
import java.util.List;

public interface ThsParseHtmlService {

    /**
     * 解析同花顺概念板块的Html页面：http://q.10jqka.com.cn/gn/
     * 返回所有概念板块的url地址
     */
    List<HashMap<String, String>> parseGnHtmlReturnGnUrlList(String html);

}
