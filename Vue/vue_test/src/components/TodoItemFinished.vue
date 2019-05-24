<template>
  <div class="FinishedItem">
    <hr>
    <div class="itemTitle">标题：{{item.title}}</div>
    <div>内容：{{item.content}}</div>
    <div>From {{item.beginDate}} to {{item.deadline}}</div>
    <div>属性：{{item.property}}</div>
    <div>
      <el-progress type="circle" :percentage="item.complete"></el-progress>
    </div>
    <div>完成情况说明：{{item.completeDesc}}</div>
    <i class="el-icon-success" v-if="success"></i>
    <i class="el-icon-error" v-if="fail"></i>
    <el-row :gutter="10" type="center">
      <el-button type="success" icon="el-icon-check" circle @click="onSuccess"></el-button>
      <el-button type="danger" icon="el-icon-close" circle @click="onFail"></el-button>
      <el-button type="danger" icon="el-icon-delete" circle @click="onDeleteItem"></el-button>
    </el-row>
    <el-row :gutter="5" type="center">
      <div>
        <el-input v-model="input" placeholder="更新完成情况"></el-input>
      </div>
    </el-row>
    <el-row :gutter="0">
      <div>
        <el-col :span="2">
          <el-input v-model="input" placeholder="进度百分比"></el-input>
        </el-col>
        <el-button type="button" @click="update">更新进度</el-button>
      </div>
    </el-row>
  </div>
</template>
<script>
/* eslint-disable */
export default {
  name: "TodoItem",
  props: {
    item: Object
  },
  methods: {
    onDeleteItem() {
      //触发事件
      this.$emit("onDeleteItem", this.item.id);
    },
    onSuccess() {},
    onFail() {},
    update() {}
  }
};
</script>
<style scoped>
.item {
  margin: 10px;
  display: block;
  text-align: center;
  align-content: center;
  font-size: 12px;
}

.itemTitle {
  font-size: 60px;
  display: block;
  text-align: center;
  align-content: center;
}
</style>
