package top.moma.fund.entity.vo.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * FundPositionResponse
 *
 * <p>//获取基金持仓详情
 *
 * @author ivan
 * @version 1.0 Created by ivan at 1/8/21.
 */
@NoArgsConstructor
@Data
public class FundPositionResponse {
  /**
   * code : 200 message : 操作成功 data : {"title":"嘉实农业产业股票
   * 2020年1季度股票投资明细","date":"2020-03-31","stock":"80.30%","bond":"2.93%","cash":"5.77%","total":"4.84","stockList":[["002311","海大集团","8.00%","96.35","3,873.36"],["000998","隆平高科","7.98%","213.27","3,864.45"]]}
   * meta : 003634
   */
  private Integer code;

  private String message;
  private DataDTO data;
  private String meta;

  @NoArgsConstructor
  @Data
  public static class DataDTO {
    /**
     * title : 嘉实农业产业股票 2020年1季度股票投资明细 date : 2020-03-31 stock : 80.30% bond : 2.93% cash : 5.77%
     * total : 4.84 stockList :
     * [["002311","海大集团","8.00%","96.35","3,873.36"],["000998","隆平高科","7.98%","213.27","3,864.45"]]
     */
    private String title;

    private String date;
    private String stock;
    private String bond;
    private String cash;
    private String total;
    private List<List<String>> stockList;
  }
}
