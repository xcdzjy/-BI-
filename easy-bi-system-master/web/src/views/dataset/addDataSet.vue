<template>
  <div class="datasetContent">
    <div class="datasetContentUp">
      <div class="ContentUptopLeft">
        <div class="backBtn">
          <i class="el-icon-arrow-left" @click="$router.back()" />
        </div>
        <i class="el-icon-s-help" />
        <div class="datasetName">{{ temp.name == '' ? '未命名' : temp.name }}</div>
      </div>
      <el-button type="primary" size="mini" round @click="saveBtn">保存</el-button>
    </div>
    <div class="datasetContentDown">
      <div class="sideBar">
        <div class="chooseDb">
          <div class="chooseDbTitle">选择数据库</div>
          <el-select v-model="dbChoosed" placeholder="请选择" @change="dbSelect">
            <el-option
              v-for="item in dbList"
              :key="item.dbName"
              :label="item.dbName"
              :value="item.dbName"
            />
          </el-select>
          <el-tabs value="tableList">
            <el-tab-pane label="数据表" name="tableList">
              <draggable
                v-model="tablesList"
                :options="{group:{name: 'itxst',pull:'clone'},sort: true}"
                animation="300"
                @end="end1"
              >
                <div v-for="(item,index) in tablesList" :key="index" class="tablesBox">
                  <i class="el-icon-date" />
                  <div class="tablesName">{{ item.tableName }}</div>
                </div>
              </draggable>
            </el-tab-pane>
          </el-tabs>
        </div>
      </div>
      <div class="infocontent">
        <div v-show="selectTableList.length == 0" style="font-size: 15px; color: #9ea6b2; padding: 15px;">请从左侧拖入表</div>
        <draggable
          v-model="selectTableList"
          :options="{group:{name: 'itxst',pull:'clone'},sort: true}"
          animation="300"
          @end="end2"
          @start="selectListStart"
        >
          <div class="selectTablesContent">
            <div v-for="(item,index) in selectTableList" :key="index" class="infotablesBox">
              <i class="el-icon-date" />
              <div class="selectTablesName">{{ item.tableName }}</div>
            </div>
          </div>
        </draggable>
      </div>
    </div>
    <el-dialog title="保存数据集" :visible.sync="saveSetDialog">
      <el-form :model="temp" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
        <el-form-item label="名称" prop="name">
          <el-input v-model="temp.name" placeholder="请输入数据集名称" />
        </el-form-item>
        <el-form-item label="名称" prop="name">
          <el-input
            v-model="temp.descr"
            type="textarea"
            :rows="3"
            placeholder="请输入内容"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="saveSetDialog = false">
          取消
        </el-button>
        <el-button type="primary" @click="saveSet()">
          确定
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getDbList, getTableList, getSchema } from '@/api/datasource'
import { createDataSet } from '@/api/dataset'
import draggable from 'vuedraggable'

export default {
  components: {
    draggable
  },
  data() {
    return {
      dbList: [],
      dbChoosed: '',
      tableParams: {
        dataSourceType: 'click_house',
        dbName: ''
      },
      tablesList: [],
      selectTableList: [],
      tablesListLength: '',
      saveSetDialog: false,
      temp: {
        createUser: '',
        dataSourceType: 'click_house',
        dbName: '',
        tableName: '',
        descr: '',
        name: '',
        schema: [
          {
            descr: '',
            isPartition: false,
            name: '',
            type: ''
          }
        ]
      }
    }
  },
  created() {
    getDbList('click_house').then(res => {
      this.dbList = res.data
    })
  },
  methods: {
    dbSelect(e) {
      // 清空选择表数组
      this.selectTableList.length = 0
      this.tableParams.dbName = e
      getTableList(this.tableParams).then(res => {
        this.tablesList = res.data
      })
    },
    // 左边往右边拖动时的事件
    end1(e) {
      if (this.selectTableList.length > 1) {
        this.selectTableList.splice(1, 1)
      }
    },
    selectListStart(e) {
      this.tablesListLength = this.tablesList.length
    },
    // 右边往左边拖动时的事件
    end2(e) {
      if (this.tablesListLength !== this.tablesList.length) {
        this.tablesList.splice(e.newDraggableIndex, 1)
      }
    },
    saveBtn() {
      if (this.selectTableList.length === 0) {
        this.$notify({
          title: 'error',
          message: '请选择数据表',
          type: 'error',
          duration: 1500
        })
      } else {
        this.saveSetDialog = true
      }
    },
    saveSet() {
      this.temp.createUser = localStorage.getItem('name')
      this.temp.dbName = this.tableParams.dbName
      this.temp.tableName = this.selectTableList[0].tableName
      getSchema(this.temp).then(res => {
        this.temp.schema = res.data
        createDataSet(this.temp).then(res => {
          this.$notify({
            title: 'success',
            message: '保存数据集成功',
            type: 'success',
            duration: 1500
          })
          this.saveSetDialog = false
          this.$router.back()
        })
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.datasetContent {
  display: flex;
  flex-direction: column;
  -moz-user-select: none;
  -webkit-user-select: none;
  -ms-user-select: none;
  user-select: none;
  .datasetContentUp {
    padding: 10px 40px 10px 20px;
    background-color:#081126;
    color: #fcffff;
    display: flex;
    justify-content: space-between;
    align-items: center;
    .ContentUptopLeft {
      display: flex;
      justify-content: left;
      align-items: center;
      .datasetName {
        margin-left: 10px;
      }
    .backBtn {
      margin-right: 30px;
      font-size: 20px;
      cursor: pointer;
  }
    }
  }
  .datasetContentDown {
    display: flex;
    justify-content: space-between;

    .sideBar {
      width: 240px;
      height: 99vh;
      padding: 20px 15px;
      border-right: 1px solid #ececec;
      background-color: #fafafa;
      .chooseDbTitle {
        color: #8a8a8a;
        margin-bottom: 15px;
      }
      .tablesBox {
        display: flex;
        justify-content: left;
        padding: 8px 0;
        cursor: pointer;
        .tablesName {
          margin-left: 10px;
          font-size: 14px;
        }
      }
      .tablesBox:hover {
        background-color: #ededed;
      }
    }
    .infocontent {
      flex: 1;
      .selectTablesContent {
        height: 100vh;
        .infotablesBox {
          display: flex;
          justify-content: left;
          align-items: center;
          padding: 10px;
          background-color: #f7f9fb;
          border: 2px solid #dbe3eb;
          border-left: 4px solid #e6d248;
          margin: 15px;
          width: 300px;
          overflow: hidden;
          text-overflow: ellipsis;
          white-space: nowrap;
          cursor: pointer;
          .selectTablesName {
            margin-left: 10px;
            margin-right: 25px;
          }
        }
      }
    }
  }
}
</style>
