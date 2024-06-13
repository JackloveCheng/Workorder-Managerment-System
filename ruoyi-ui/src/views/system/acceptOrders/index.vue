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

    <el-table v-loading="loading" :data="acceptOrdersList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="工单id" align="center" prop="orderId" />
      <el-table-column label="工单数量" align="center" prop="orderNumber" />
      <el-table-column label="工单类型" align="center" prop="businessType" />
      <el-table-column label="工单标题" align="center" prop="title" />
      <el-table-column label="描述" align="center" prop="description" />
      <el-table-column label="状态" align="center" prop="status">
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
            v-hasPermi="['system:acceptOrders:remove']"
          >详情</el-button>
          <el-button
            size="mini"
            type="text"
            @click="handleAccept(scope.row)"
            v-hasPermi="['system:acceptOrders:remove']"
          >接受</el-button>
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
        <el-form-item label="工单数量" prop="orderNumber">
          <el-input v-model="form.orderNumber" placeholder="请输入工单数量" />
        </el-form-item>
        <el-form-item label="工单标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入工单标题" />
        </el-form-item>
        <el-form-item label="描述">
          <editor v-model="form.description" :min-height="192"/>
        </el-form-item>
        <el-form-item label="工单发起人" prop="submitterId">
          <el-input v-model="form.submitterId" placeholder="请输入工单发起人" />
        </el-form-item>
        <el-form-item label="工单接收人" prop="assigneeId">
          <el-input v-model="form.assigneeId" placeholder="请输入工单接收人" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 详情对话框 -->
    <el-dialog :title="title" :visible.sync="open_detail" width="500px" append-to-body>
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
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { listAcceptOrders, getAcceptOrders, delAcceptOrders, addAcceptOrders, updateAcceptOrders } from "@/api/system/acceptOrders";

export default {
  name: "AcceptOrders",
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
      acceptOrdersList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否显示详情
      open_detail: false,
      // 是否只读
      isReadOnly: false,
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
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询接收工单列表 */
    getList() {
      this.loading = true;
      listAcceptOrders(this.queryParams).then(response => {
        this.acceptOrdersList = response.rows;
        this.total = response.total;
        this.loading = false;
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
        approvalRoleId: null
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
      getAcceptOrders(orderId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改接收工单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.orderId != null) {
            updateAcceptOrders(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAcceptOrders(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const orderIds = row.orderId || this.ids;
      this.$modal.confirm('是否确认删除接收工单编号为"' + orderIds + '"的数据项？').then(function() {
        return delAcceptOrders(orderIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/acceptOrders/export', {
        ...this.queryParams
      }, `acceptOrders_${new Date().getTime()}.xlsx`)
    },

    /** 接受按钮操作 */
    handleAccept(row) {
      this.reset();
      const orderId = row.orderId || this.ids
      getAcceptOrders(orderId).then(response => {
        this.form = response.data;
        this.form.assigneeId = this.$store.state.id;
        var length = this.$store.state.dict.dict.at(0).value.length;
        var status = this.form.status;
        for (let i = 0; i < length; i++) {
          var item = this.$store.state.dict.dict.at(0).value[i];
          if (item.dictValue === status) {
            this.form.status = this.$store.state.dict.dict.at(0).value[i + 1].dictValue;
          }
        }
        updateAcceptOrders(this.form).then(response => {
          this.$modal.msgSuccess("接受成功");
          this.getList();
        });

      });
    },
    handleDetailInfo(row){
      this.reset();
      const orderId = row.orderId || this.ids
      getAcceptOrders(orderId).then(response => {
        this.form = response.data;
        this.open_detail = true;
        this.isReadOnly = true;
        this.title = "工单详情";
      });
    }
  }
};
</script>
