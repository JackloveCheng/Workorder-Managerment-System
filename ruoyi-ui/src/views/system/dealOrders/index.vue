<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="工单id" prop="orderId">
        <el-input
          v-model="queryParams.orderId"
          placeholder="请输入工单id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工单数量" prop="orderNumber">
        <el-input
          v-model="queryParams.orderNumber"
          placeholder="请输入工单数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工单标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入工单标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="dealOrdersList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="工单id" align="center" prop="orderId" />
      <el-table-column label="工单数量" align="center" prop="orderNumber" />
      <el-table-column label="工单类型" align="center" prop="businessType" />
      <el-table-column label="工单标题" align="center" prop="title" />
      <el-table-column label="描述" align="center" prop="description" />
      <el-table-column label="工单状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_ticket_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="工单发起人" align="center" prop="submitterId" />
      <el-table-column label="工单接收人" align="center" prop="assigneeId" />
      <el-table-column label="创建时间" align="center" prop="createdAt" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改时间" align="center" prop="updatedAt" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updatedAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-info"
            @click="handleDetailInfo(scope.row)"
            v-hasPermi="['system:dealOrders:edit']"
          >详情</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleExecute(scope.row)"
            v-hasPermi="['system:dealOrders:edit']"
          >执行</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleAssist(scope.row)"
            v-hasPermi="['system:dealOrders:edit']"
          >协助</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改接收工单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="工单标识符" prop="orderNumber">
          <el-input v-model="form.orderNumber" v-bind:disabled="isReadOnly" placeholder="请输入工单标识符" />
        </el-form-item>
        <el-form-item label="工单类型" prop="businessType">
          <el-select v-model="form.businessType"  v-bind:disabled="isReadOnly" placeholder="请选择工单类型">
            <el-option
              v-for="dict in dict.type.sys_ticket_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="工单标题" prop="title">
          <el-input v-model="form.title" v-bind:disabled="isReadOnly" placeholder="请输入工单标题" />
        </el-form-item>
        <el-form-item label="描述">
          <editor v-model="form.description" :min-height="192" :readOnly="isReadOnly" />
        </el-form-item>
        <el-form-item label="工单状态" prop="status">
          <el-select v-model="form.status" v-bind:disabled="isReadOnly" placeholder="请选择工单状态">
            <el-option
              v-for="dict in dict.type.sys_ticket_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-dialog :title="title" :visible.sync="openHelp" width="600px" append-to-body>
      <el-form ref="form_1" :model="form_1" :rules="rules_1" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="选择协助部门" prop="assistingDepartment" label-width="120px">
              <treeselect v-model="form_1.assistingDepartment" :options="deptOptions" :disable-branch-nodes="true" :show-count="true" placeholder="请选择协助部门" />
            </el-form-item>
            <el-form-item label="协助信息" prop="assistInfo" label-width="120px">
              <el-input v-model="form_1.assistInfo" type="textarea" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog :title="title" :visible.sync="openExe" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="回单信息">
          <el-input v-model="form.receiptInfo" v-bind:disabled="isReadOnly" placeholder="请输入回单信息"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm_1">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { listDealOrders, getDealOrders, delDealOrders, addDealOrders, updateDealOrders } from "@/api/system/dealOrders";
import { getAssistOrder, delAssistOrder, addAssistOrder, updateAssistOrder } from "@/api/system/assistOrder";

import { deptTreeSelect } from "@/api/system/user";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "DealOrders",
  components: {Treeselect},
  dicts: ['sys_ticket_status', 'sys_ticket_type'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 接收工单表格数据
      dealOrdersList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      openHelp:false,
      openExe:false,
      isReadOnly:false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderId: null,
        orderNumber: null,
        title: null,
      },
      // 表单参数
      form: {},
      form_1: {
        assistanceID: null,
        workOrderID: null,
        assistingDepartment: null,
        assistInfo: null
      },
      // 表单校验
      rules: {
        orderNumber: [
          { required: true, message: "工单数量不能为空", trigger: "blur" }
        ],
        businessType: [
          { required: true, message: "工单类型不能为空", trigger: "change" }
        ],
        title: [
          { required: true, message: "工单标题不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "状态不能为空", trigger: "change" }
        ],
      },
      rules_1: {
        assistInfo: [
          { required: true, message: "协助信息不能为空", trigger: "blur" }
        ],
         assistingDepartment: [
           { required: true, message: "协助部门不能为空", trigger: "blur" }
        ],
      },
      // 部门树选项
      deptOptions: undefined,
    };
  },
  created() {
    this.getList();
    this.getDeptTree();
  },
  methods: {
    /** 查询接收工单列表 */
    getList() {
      this.loading = true;
      listDealOrders(this.queryParams).then(response => {
        this.dealOrdersList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 查询部门下拉树结构 */
    getDeptTree() {
      deptTreeSelect().then(response => {
        this.deptOptions = response.data;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        orderId: null,
        orderNumber: null,
        businessType: null,
        title: null,
        description: null,
        status: null,
        submitterId: null,
        assigneeId: null,
        createdAt: null,
        updatedAt: null,
        approvalRoleId: null,
        receiptInfo: null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.orderId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加接收工单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const orderId = row.orderId || this.ids
      getDealOrders(orderId).then(response => {
        this.form = response.data;
        updateDealOrders(this.form).then(response => {
          this.$modal.msgSuccess("执行成功");
          this.getList();
        });
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form_1"].validate(valid => {
        if (valid) {
          addAssistOrder(this.form_1).then(response => {
            this.$modal.msgSuccess("新增成功");
            this.openHelp = false;
          });
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const orderIds = row.orderId || this.ids;
      this.$modal.confirm('是否确认删除接收工单编号为"' + orderIds + '"的数据项？').then(function() {
        return delDealOrders(orderIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/dealOrders/export', {
        ...this.queryParams
      }, `dealOrders_${new Date().getTime()}.xlsx`)
    },
    handleDetailInfo(row){
      const orderId = row.orderId || this.ids
      getDealOrders(orderId).then(response => {
        this.form = response.data;
        this.open = true;
        this.isReadOnly = true;
        this.title = "工单详情";
      });
    },

    handleAssist(row) {
      this.form_1.workOrderID = row.orderId;
      this.openHelp = true;
    },
    handleExecute(row) {
      this.reset();
      const orderId = row.orderId || this.ids;
      this.isReadOnly = false;
      getDealOrders(orderId).then(response => {
        this.form = response.data;
        this.openExe = true;
      });
    },
    submitForm_1() {
      delAssistOrder(this.form.orderId).then(() => {
      })

      updateDealOrders(this.form).then(response => {
        this.$modal.msgSuccess("执行成功");
        this.openExe = false;
        this.getList();
      });
    }

  }
};
</script>
