package com.ruoyi.web.controller.system;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.OrderReport;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.WorkOrders;
import com.ruoyi.system.service.IWorkOrdersService;
import com.ruoyi.system.service.IOrderReportService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工单信息Controller
 * 
 * @author hao
 * @date 2024-06-01
 */
@Component("OrderTask")
public class OrderTask
{
    @Autowired
    private IWorkOrdersService workOrdersService;

    @Autowired
    private IOrderReportService orderReportService;

    public void GenerateReports() {
        Date today = new Date();
        long oneDayMillis = 24 * 60 * 60 * 1000; // 一天的毫秒数
        Date oneDayAgo = new Date(today.getTime() - oneDayMillis); // 一天前的日期
        workOrdersService.updateOverTimeOrders(oneDayAgo);

        List<Map<String,Object>> result =
        workOrdersService.getWorkOrderCounts();
        BigDecimal overtimeCount1 = (BigDecimal) result.get(0).get("overtimeCount");
        BigDecimal completedCount1 = (BigDecimal) result.get(0).get("completedCount");
        Long allCount = (Long) result.get(0).get("allCount");

        Long overtimeCount = overtimeCount1!= null ? Long.valueOf(overtimeCount1.longValue()) : null;
        Long completedCount = completedCount1 != null ? Long.valueOf(completedCount1.longValue()) : null;


        if (result.get(0).get("overtimeCount") == null) {
            overtimeCount = 0L;
        }
        if ( result.get(0).get("completedCount") == null) {
            completedCount = 0L;
        }
        if (allCount == null) {
            allCount = 0L;
        }
        OrderReport report = new OrderReport();
        report.setOrderId(1L);

        report.setOrderNum(completedCount);
        orderReportService.updateOrderReport(report);

        report.setOrderId(2L);
        report.setOrderNum(allCount-completedCount);
        orderReportService.updateOrderReport(report);

        report.setOrderId(3L);
        report.setOrderNum(overtimeCount);
        orderReportService.updateOrderReport(report);
    }

    public void SetReport() {
        OrderReport report = new OrderReport();
        report.setOrderId(1L);
        report.setOrderNum(7L);
        orderReportService.updateOrderReport(report);
    }
}
