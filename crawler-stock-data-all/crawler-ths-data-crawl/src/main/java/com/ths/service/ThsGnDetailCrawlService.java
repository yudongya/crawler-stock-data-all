package com.ths.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;


public interface ThsGnDetailCrawlService {

    void putAllArrayBlockingQueue(List<HashMap<String, String>> list);

    void ConsumeCrawlerGnDetailData(int threadNumber);

}
