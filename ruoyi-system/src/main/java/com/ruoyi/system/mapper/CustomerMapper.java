package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Customer;

/**
 * 客户管理Mapper接口
 * 
 * @author hao
 * @date 2024-05-28
 */
public interface CustomerMapper 
{
    /**
     * 查询客户管理
     * 
     * @param id 客户管理主键
     * @return 客户管理
     */
    public Customer selectCustomerById(Long id);

    /**
     * 查询客户管理列表
     * 
     * @param customer 客户管理
     * @return 客户管理集合
     */
    public List<Customer> selectCustomerList(Customer customer);

    /**
     * 新增客户管理
     * 
     * @param customer 客户管理
     * @return 结果
     */
    public int insertCustomer(Customer customer);

    /**
     * 修改客户管理
     * 
     * @param customer 客户管理
     * @return 结果
     */
    public int updateCustomer(Customer customer);

    /**
     * 删除客户管理
     * 
     * @param id 客户管理主键
     * @return 结果
     */
    public int deleteCustomerById(Long id);

    /**
     * 批量删除客户管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCustomerByIds(Long[] ids);
}
