package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.AssistOrder;

/**
 * 工单协助Mapper接口
 * 
 * @author lin
 * @date 2024-06-13
 */
public interface AssistOrderMapper 
{
    /**
     * 查询工单协助
     * 
     * @param assistanceID 工单协助主键
     * @return 工单协助
     */
    public AssistOrder selectAssistOrderByAssistanceID(Long assistanceID);

    /**
     * 查询工单协助列表
     * 
     * @param assistOrder 工单协助
     * @return 工单协助集合
     */
    public List<AssistOrder> selectAssistOrderList(AssistOrder assistOrder);

    /**
     * 新增工单协助
     * 
     * @param assistOrder 工单协助
     * @return 结果
     */
    public int insertAssistOrder(AssistOrder assistOrder);

    /**
     * 修改工单协助
     * 
     * @param assistOrder 工单协助
     * @return 结果
     */
    public int updateAssistOrder(AssistOrder assistOrder);

    /**
     * 删除工单协助
     * 
     * @param assistanceID 工单协助主键
     * @return 结果
     */
    public int deleteAssistOrderByAssistanceID(Long assistanceID);

    /**
     * 批量删除工单协助
     * 
     * @param assistanceIDs 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAssistOrderByAssistanceIDs(Long[] assistanceIDs);
}
