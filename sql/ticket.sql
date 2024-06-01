CREATE TABLE tickets (
                         ticket_id INT AUTO_INCREMENT PRIMARY KEY,   -- 工单号，自动递增的主键
                         business_type VARCHAR(100) NOT NULL,        -- 业务性质
                         title VARCHAR(255) NOT NULL,                -- 工单标题
                         progress VARCHAR(50),                       -- 进度
                         submitter VARCHAR(100) NOT NULL,            -- 提交者
                         handler VARCHAR(100),                       -- 受理人员
                         status VARCHAR(50) NOT NULL              -- 工单状态
);
-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('工单系统', '3', '1', 'tickets', 'system/tickets/index', 1, 0, 'C', '0', '0', 'system:tickets:list', '#', 'admin', sysdate(), '', null, '工单系统菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('工单系统查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:tickets:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('工单系统新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:tickets:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('工单系统修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:tickets:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('工单系统删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:tickets:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('工单系统导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:tickets:export',       '#', 'admin', sysdate(), '', null, '');