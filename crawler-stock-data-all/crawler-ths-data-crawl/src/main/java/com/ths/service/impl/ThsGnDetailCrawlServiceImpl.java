package com.ths.service.impl;

import com.ths.dao.StockThsGnInfoDao;
import com.ths.domain.StockThsGnInfo;
import com.ths.service.ThsGnDetailCrawlService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

@Service
public class ThsGnDetailCrawlServiceImpl implements ThsGnDetailCrawlService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ThsGnDetailCrawlServiceImpl.class);

    /**
     * 阻塞队列
     */
    private ArrayBlockingQueue<HashMap<String, String>> arrayBlockingQueue = new ArrayBlockingQueue<>(1000);

    @Autowired
    private StockThsGnInfoDao stockThsGnInfoDao;

    @Override
    public void putAllArrayBlockingQueue(List<HashMap<String, String>> list) {
        if (!CollectionUtils.isEmpty(list)) {
            arrayBlockingQueue.addAll(list);
        }
    }

    @Override
    public void ConsumeCrawlerGnDetailData(int threadNumber) {
        for (int i = 0; i < threadNumber; ++i) {
            LOGGER.info("开启线程第[{}]个消费", i);
            new Thread(new crawlerGnDataThread()).start();
        }
        LOGGER.info("一共开启线程[{}]个消费", threadNumber);
    }

    class crawlerGnDataThread implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    Map<String, String> map = arrayBlockingQueue.take();
                    String url = map.get("url");
                    String gnName = map.get("gnName");
                    String crawlerDateStr = new SimpleDateFormat("yyyy-MM-dd HH:00:00").format(new Date());
                    //chromederiver存放位置
                    System.setProperty("webdriver.chrome.driver", "/Users/admin/Documents/selenium/chrome/79.0.3945.36/chromedriver");
                    ChromeOptions options = new ChromeOptions();
                    //无界面参数
                    //        options.addArguments("headless");
                    //禁用沙盒 就是被这个参数搞了一天
                    //        options.addArguments("no-sandbox");
                    WebDriver webDriver = new ChromeDriver(options);
                    try {
                        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                        webDriver.get(url);
                        Thread.sleep(1000L);
                        String oneGnHtml = webDriver.getPageSource();
                        LOGGER.info("当前概念：[{}],html数据为[{}]", gnName, oneGnHtml);
                        LOGGER.info(oneGnHtml);
                        // TODO 解析并存储数据
                        parseHtmlAndInsertData(oneGnHtml, gnName, crawlerDateStr);
                        clicktoOneGnNextPage(webDriver, oneGnHtml, gnName, crawlerDateStr);
                    } catch (Exception e) {
                        LOGGER.error("用chromerDriver抓取数据，出现异常，url为[{}],异常为[{}]", url, e);
                    } finally {
                        webDriver.close();
                        webDriver.quit();
                    }
                }
            } catch (Exception e) {
                LOGGER.error("阻塞队列出现循环出现异常:", e);
            }
        }
    }

    public void parseHtmlAndInsertData(String html, String gnName, String crawlerDateStr) {
        Document document = Jsoup.parse(html);
//        Element boardElement = document.getElementsByClass("board-hq").get(0);
//        String gnCode = boardElement.getElementsByTag("h3").get(0).getElementsByTag("span").get(0).text();

        Element table = document.getElementsByClass("m-pager-table").get(0);
        Element tBody = table.getElementsByTag("tbody").get(0);
        Elements trs = tBody.getElementsByTag("tr");
        for (Element tr : trs) {
            try {
                Elements tds = tr.getElementsByTag("td");
                String stockCode = tds.get(1).text();
                String stockName = tds.get(2).text();
                BigDecimal stockPrice = parseValueToBigDecimal(tds.get(3).text());
                BigDecimal stockChange = parseValueToBigDecimal(tds.get(4).text());
                BigDecimal stockChangePrice = parseValueToBigDecimal(tds.get(5).text());
                BigDecimal stockChangeSpeed = parseValueToBigDecimal(tds.get(6).text());
                BigDecimal stockHandoverScale = parseValueToBigDecimal(tds.get(7).text());
                BigDecimal stockLiangBi = parseValueToBigDecimal(tds.get(8).text());
                BigDecimal stockAmplitude = parseValueToBigDecimal(tds.get(9).text());
                BigDecimal stockDealAmount = parseValueToBigDecimal(tds.get(10).text());
                BigDecimal stockFlowStockNumber = parseValueToBigDecimal(tds.get(11).text());
                BigDecimal stockFlowMakertValue = parseValueToBigDecimal(tds.get(12).text());
                BigDecimal stockMarketTtm = parseValueToBigDecimal(tds.get(13).text());
                // 存储数据
                StockThsGnInfo stockThsGnInfo = new StockThsGnInfo();
                stockThsGnInfo.setGnName(gnName);
                stockThsGnInfo.setGnCode(null);
                stockThsGnInfo.setStockCode(stockCode);
                stockThsGnInfo.setStockName(stockName);
                stockThsGnInfo.setStockPrice(stockPrice);
                stockThsGnInfo.setStockChange(stockChange);
                stockThsGnInfo.setStockChangePrice(stockChangePrice);
                stockThsGnInfo.setStockChangeSpeed(stockChangeSpeed);
                stockThsGnInfo.setStockHandoverScale(stockHandoverScale);
                stockThsGnInfo.setStockLiangBi(stockLiangBi);
                stockThsGnInfo.setStockAmplitude(stockAmplitude);
                stockThsGnInfo.setStockDealAmount(stockDealAmount);
                stockThsGnInfo.setStockFlowStockNumber(stockFlowStockNumber);
                stockThsGnInfo.setStockFlowMakertValue(stockFlowMakertValue);
                stockThsGnInfo.setStockMarketTtm(stockMarketTtm);
                stockThsGnInfo.setCrawlerTime(crawlerDateStr);
                stockThsGnInfo.setCrawlerVersion("同花顺概念板块#" + crawlerDateStr);
                stockThsGnInfo.setCreateTime(new Date());
                stockThsGnInfo.setUpdateTime(new Date());
                stockThsGnInfoDao.insert(stockThsGnInfo);
            } catch (Exception e) {
                LOGGER.error("插入同花顺概念板块数据出现异常:", e);
            }

        }
    }

    public BigDecimal parseValueToBigDecimal(String value) {
        if (StringUtils.isEmpty(value)) {
            return BigDecimal.ZERO;
        } else if ("--".equals(value)) {
            return BigDecimal.ZERO;
        } else if (value.endsWith("亿")) {
            return new BigDecimal(value.substring(0, value.length() - 1)).multiply(BigDecimal.ONE);
        }
        return new BigDecimal(value);
    }

    public boolean clicktoOneGnNextPage(WebDriver webDriver, String oneGnHtml, String key, String crawlerDateStr) throws InterruptedException {
        // 是否包含下一页
        String pageNumber = includeNextPage(oneGnHtml);
        if (!StringUtils.isEmpty(pageNumber)) {
            WebElement nextPageElement = webDriver.findElement(By.linkText("下一页"));
            webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            nextPageElement.click();
            Thread.sleep(700);
            String nextPageHtml = webDriver.getPageSource();
            LOGGER.info("下一页：");
            LOGGER.info(nextPageHtml);
            // TODO 解析并存储数据
            parseHtmlAndInsertData(nextPageHtml, key, crawlerDateStr);
            clicktoOneGnNextPage(webDriver, nextPageHtml, key, crawlerDateStr);
        }
        return true;
    }

    public String includeNextPage(String html) {
        Document document = Jsoup.parse(html);
        List<Element> list = document.getElementsByTag("a");
        for (Element element : list) {
            String a = element.text();
            if ("下一页".equals(a)) {
                String pageNumber = element.attr("page");
                return pageNumber;
            }
        }
        return null;
    }


}
