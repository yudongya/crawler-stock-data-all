package com.ths.parse.service.impl;

import com.ths.parse.service.ThsParseHtmlService;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ThsParseHtmlServiceImpl implements ThsParseHtmlService {

    /**
     * 解析同花顺概念板块的Html页面：http://q.10jqka.com.cn/gn/
     * 返回所有概念板块的url地址
     */
    public List<HashMap<String, String>> parseGnHtmlReturnGnUrlList(String html) {
        if (StringUtil.isBlank(html)) {
            return null;
        }
        List<HashMap<String, String>> list = new ArrayList<>();
        Document document = Jsoup.parse(html);
        Elements cateItemsFromClass = document.getElementsByClass("cate_items");
        for (Element element : cateItemsFromClass) {
            Elements as = element.getElementsByTag("a");
            for (Element a : as) {
                String gnUrl = a.attr("href");
                String name = a.text();
                HashMap<String, String> map = new HashMap<>();
                map.put("url", gnUrl);
                map.put("gnName", name);
                list.add(map);
            }
        }
        return list;
    }
}
