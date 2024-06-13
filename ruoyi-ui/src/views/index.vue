<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :sm="24" :lg="24">
        <hr />
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :sm="24" :lg="12" style="padding-left: 20px">
        <el-row>
          <el-col :span="12">
            <h2>团队成员</h2>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <h4>成员姓名</h4>
            <ul>
              <li>程嘉豪</li>
              <li>林晓金</li>
              <li>夏鑫杭</li>
            </ul>
          </el-col>
          <el-col :span="6">
            <h4>成员学号</h4>
            <ul>
              <li>202130190235</li>
              <li>202130320960</li>
              <li>202130272436</li>
            </ul>
          </el-col>

      </el-row>
      </el-col>

      <el-col :sm="24" :lg="12" style="padding-left: 50px">
        <el-row>
          <el-col :span="12">
            <h2>技术选型</h2>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <h4>后端技术</h4>
            <ul>
              <li>SpringBoot</li>
              <li>Spring Security</li>
              <li>JWT</li>
              <li>MyBatis</li>
              <li>Druid</li>
              <li>Fastjson</li>
              <li>...</li>
            </ul>
          </el-col>
          <el-col :span="6">
            <h4>前端技术</h4>
            <ul>
              <li>Vue</li>
              <li>Vuex</li>
              <li>Element-ui</li>
              <li>Axios</li>
              <li>Sass</li>
              <li>Quill</li>
              <li>...</li>
            </ul>
          </el-col>
        </el-row>
      </el-col>

    </el-row>
    <el-divider />

    <el-col :xs="24" :sm="24" :md="12" :lg="8">
      <el-card class="update-log">
        <div slot="header" class="clearfix">
          <span>更新日志</span>
        </div>
        <el-collapse accordion>
          <el-collapse-item title="2024-06-12">
            <ol>
              <li>优化登陆页面，使之更加优美</li>
              <li>优化首页面，显示小组成员以及其他相关的信息</li>
              <li>其他细节优化</li>
            </ol>
          </el-collapse-item>
          <el-collapse-item title="2024-06-11">
            <ol>
              <li>支持表单的构建</li>
              <li>增加工单流转情况统计报表</li>
              <li>设置定时任务，每天更新表单</li>
              <li>其他细节优化</li>
            </ol>
          </el-collapse-item>
          <el-collapse-item title="2024-06-10">
            <ol>
              <li>数据库的重建</li>
              <li>引入在线表单的组件</li>
              <li>其他细节优化</li>
            </ol>
          </el-collapse-item>
          <el-collapse-item title="2024-06-07">
            <ol>
              <li>设计构建工单执行的页面</li>
              <li>经过审批的工单可以被执行</li>
              <li>其他细节优化</li>
            </ol>
          </el-collapse-item>
          <el-collapse-item title="2024-06-06">
            <ol>
              <li>设计构建work_order_approval表格</li>
              <li>完成工单的各级审批过程，让表单经过区级，市级，省级层层审批后抵达操作单位</li>
              <li>其他细节优化</li>
            </ol>
          </el-collapse-item>
          <el-collapse-item title="2024-06-05">
            <ol>
              <li>支持工单的驳回</li>
              <li>其他细节优化</li>
            </ol>
          </el-collapse-item>
          <el-collapse-item title="2024-06-01">
            <ol>
              <li>创建工单相关的sql表格，定义工单的各项属性</li>
              <li>新建《工单系统》页面，让有关工单的任务在此页面中完成</li>
              <li>实现工单的增删改查</li>
              <li>其他细节优化</li>
            </ol>
          </el-collapse-item>
          <el-collapse-item title="2024-05-29">
            <ol>
              <li>搭建项目框架</li>
              <li>完成初始登陆页面</li>
              <li>初始化首页</li>
              <li>其他细节优化</li>
            </ol>
          </el-collapse-item>
          <el-collapse-item title="2024-05-20">
            <ol>
              <li>组员讨论项目的框架，设计相关的数据库表格</li>
              <li>分析这次项目的要求，确定完成的任务</li>
              <li>其他细节优化</li>
            </ol>
          </el-collapse-item>

        </el-collapse>
      </el-card>
    </el-col>
  </div>

</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      // 版本号
      version: "3.8.7"
    };
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    }
  }
};
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>

