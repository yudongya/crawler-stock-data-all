package com.ths.domain;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 
 * 同花顺的概念个股数据
 * 
 **/
public class StockThsGnInfo implements java.io.Serializable {


  private static final long serialVersionUID = 1L;


  /****/

  private Long id;


  /**概念名**/

  private String gnName;


  /**概念的code**/

  private String gnCode;


  /**股票code**/

  private String stockCode;


  /**股票名**/

  private String stockName;


  /**现价**/

  private BigDecimal stockPrice;


  /**涨跌幅（单位百分比：%）**/

  private BigDecimal stockChange;


  /**涨跌价格**/

  private BigDecimal stockChangePrice;


  /**涨跌速（单位百分比：%）**/

  private BigDecimal stockChangeSpeed;


  /**换手率（单位百分比：%）**/

  private BigDecimal stockHandoverScale;


  /**量比**/

  private BigDecimal stockLiangBi;


  /**振幅（单位百分比：%）**/

  private BigDecimal stockAmplitude;


  /**成交额(单位：万)**/

  private BigDecimal stockDealAmount;


  /**流通股票量（单位:万）**/

  private BigDecimal stockFlowStockNumber;


  /**流通市值（单位：万）**/

  private BigDecimal stockFlowMakertValue;


  /**市盈率**/

  private BigDecimal stockMarketTtm;


  /**发起抓取时间**/

  private String crawlerTime;


  /**当前抓取的版本**/

  private String crawlerVersion;


  /**扩展数据**/

  private String someExt;


  /****/

  private Date createTime;


  /****/

  private Date updateTime;




  public void setId(Long id) {     this.id = id;
  }


  public Long getId() {     return this.id;
  }


  public void setGnName(String gnName) {     this.gnName = gnName;
  }


  public String getGnName() {     return this.gnName;
  }


  public void setGnCode(String gnCode) {     this.gnCode = gnCode;
  }


  public String getGnCode() {     return this.gnCode;
  }


  public void setStockCode(String stockCode) {     this.stockCode = stockCode;
  }


  public String getStockCode() {     return this.stockCode;
  }


  public void setStockName(String stockName) {     this.stockName = stockName;
  }


  public String getStockName() {     return this.stockName;
  }


  public void setStockPrice(BigDecimal stockPrice) {     this.stockPrice = stockPrice;
  }


  public BigDecimal getStockPrice() {     return this.stockPrice;
  }


  public void setStockChange(BigDecimal stockChange) {     this.stockChange = stockChange;
  }


  public BigDecimal getStockChange() {     return this.stockChange;
  }


  public void setStockChangePrice(BigDecimal stockChangePrice) {     this.stockChangePrice = stockChangePrice;
  }


  public BigDecimal getStockChangePrice() {     return this.stockChangePrice;
  }


  public void setStockChangeSpeed(BigDecimal stockChangeSpeed) {     this.stockChangeSpeed = stockChangeSpeed;
  }


  public BigDecimal getStockChangeSpeed() {     return this.stockChangeSpeed;
  }


  public void setStockHandoverScale(BigDecimal stockHandoverScale) {     this.stockHandoverScale = stockHandoverScale;
  }


  public BigDecimal getStockHandoverScale() {     return this.stockHandoverScale;
  }


  public void setStockLiangBi(BigDecimal stockLiangBi) {     this.stockLiangBi = stockLiangBi;
  }


  public BigDecimal getStockLiangBi() {     return this.stockLiangBi;
  }


  public void setStockAmplitude(BigDecimal stockAmplitude) {     this.stockAmplitude = stockAmplitude;
  }


  public BigDecimal getStockAmplitude() {     return this.stockAmplitude;
  }


  public void setStockDealAmount(BigDecimal stockDealAmount) {     this.stockDealAmount = stockDealAmount;
  }


  public BigDecimal getStockDealAmount() {     return this.stockDealAmount;
  }


  public void setStockFlowStockNumber(BigDecimal stockFlowStockNumber) {     this.stockFlowStockNumber = stockFlowStockNumber;
  }


  public BigDecimal getStockFlowStockNumber() {     return this.stockFlowStockNumber;
  }


  public void setStockFlowMakertValue(BigDecimal stockFlowMakertValue) {     this.stockFlowMakertValue = stockFlowMakertValue;
  }


  public BigDecimal getStockFlowMakertValue() {     return this.stockFlowMakertValue;
  }


  public void setStockMarketTtm(BigDecimal stockMarketTtm) {     this.stockMarketTtm = stockMarketTtm;
  }


  public BigDecimal getStockMarketTtm() {     return this.stockMarketTtm;
  }


  public void setCrawlerTime(String crawlerTime) {     this.crawlerTime = crawlerTime;
  }


  public String getCrawlerTime() {     return this.crawlerTime;
  }


  public void setCrawlerVersion(String crawlerVersion) {     this.crawlerVersion = crawlerVersion;
  }


  public String getCrawlerVersion() {     return this.crawlerVersion;
  }


  public void setSomeExt(String someExt) {     this.someExt = someExt;
  }


  public String getSomeExt() {     return this.someExt;
  }


  public void setCreateTime(Date createTime) {     this.createTime = createTime;
  }


  public Date getCreateTime() {     return this.createTime;
  }


  public void setUpdateTime(Date updateTime) {     this.updateTime = updateTime;
  }


  public Date getUpdateTime() {     return this.updateTime;
  }

}
