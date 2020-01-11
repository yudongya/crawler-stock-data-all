package com.ths.test;

import com.ths.job.CrawlerThsGnListUrlJob;
import com.ths.service.ThsGnCrawlService;
import com.ths.service.ThsGnDetailCrawlService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrawlTest {

    @Autowired
    private ThsGnCrawlService thsGnCrawlService;
    @Autowired
    private ThsGnDetailCrawlService thsGnDetailCrawlService;

    @Test
    public void test1() {
        List<HashMap<String, String>> list = thsGnCrawlService.ThsGnCrawlListUrl();
        thsGnDetailCrawlService.putAllArrayBlockingQueue(list);
        thsGnDetailCrawlService.ConsumeCrawlerGnDetailData(1);
    }


}
