<template>
  <div class="datasourceContent">
    <div class="topBox">
      <div class="topBoxLeft">数据源</div>
      <el-button type="primary" size="medium" icon="el-icon-plus" @click="sourceListDialog = true">新建数据源</el-button>
    </div>
    <div class="infoContent">
      <div class="infoContentLeft">
        <el-table
          ref="singleTable"
          :data="dbList"
          highlight-current-row
          style="width: 100%"
          @current-change="handleCurrentChange"
        >
          <el-table-column
            property="dbName"
            label="我的数据库"
            width="600"
          />
        </el-table>
      </div>
      <div class="infoContentRight">
        <el-table
          :data="tableList"
          style="width: 100%"
          class="rightTable"
        >
          <el-table-column
            property="tableName"
            label="表名称"
            width="1000"
          />
        </el-table>
      </div>
    </div>
    <el-dialog
      title="选择数据源"
      :visible.sync="sourceListDialog"
      width="20%"
    >
      <el-table
        :data="sourceList"
        style="width: 100%"
        show-header:false
        @cell-click="chooseSource"
      >
        <el-table-column
          prop="dataSourceName"
          width="330"
        />
      </el-table>
    </el-dialog>
  </div>
</template>

<script>

import { getDatasourceList, getDbList, getTableList } from '@/api/datasource'

export default {
  data() {
    return {
      sourceListDialog: false,
      sourceList: [],
      dbList: [],
      tableParam: {
        dataSourceType: 'click_house',
        dbName: ''
      },
      tableList: []
    }
  },
  created() {
    getDatasourceList().then(res => {
      this.sourceList = res.data
    })

    getDbList('click_house').then(res => {
      this.dbList = res.data
      // 数据库选择第一行
      this.$refs.singleTable.setCurrentRow(this.dbList[0])
    })
  },
  methods: {
    chooseSource(e) {
      this.sourceListDialog = false
    },
    handleCurrentChange(e) {
      this.tableParam.dbName = e.dbName
      this.tableListMethod(this.tableParam)
    },
    tableListMethod(data) {
      getTableList(data).then(res => {
        this.tableList = res.data
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.datasourceContent {
  background-color: #f5f5f5;
}
.topBox {
    padding: 25px;
    display: flex;
    justify-content: space-between;
    .topBoxLeft {
        font-size: 20px;
    }
}
.infoContent {
  padding: 25px;
  display: flex;
  justify-content: space-between;
  height: 100vh;
  .infoContentLeft {
    height: 100%;
    border-right: 1px solid #eeeef0;
  }
  .infoContentRight {
    flex: 1;
    .rightTable {
      padding: 0 20px;
    }
  }
}
</style>
