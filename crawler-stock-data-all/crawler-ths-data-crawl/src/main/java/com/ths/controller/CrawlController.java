package com.ths.controller;

import com.ths.service.ThsGnCrawlService;
import com.ths.service.ThsGnDetailCrawlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
public class CrawlController {

    @Autowired
    private ThsGnCrawlService thsGnCrawlService;

    @Autowired
    private ThsGnDetailCrawlService thsGnDetailCrawlService;

    @RequestMapping("/test")
    @ResponseBody
    public void test() {
        List<HashMap<String, String>> list = thsGnCrawlService.ThsGnCrawlListUrl();
        thsGnDetailCrawlService.putAllArrayBlockingQueue(list);
        thsGnDetailCrawlService.ConsumeCrawlerGnDetailData(1);
    }

}
