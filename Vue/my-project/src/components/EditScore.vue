<template>
  <el-dialog
    title="修改成绩信息"
    :visible.sync="EditScoreFormVisible"
  >
    <el-button @click="cancelEditScore">取 消</el-button>
    <el-button
      type="primary"
      @click="confirmEditScore"
    >确 定</el-button>
  </el-dialog>
</template>
<script>
export default {
  name: 'EditScore',
  props: {
    EditScoreFormVisible: {
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
    }
  },
  data: function () {
    return {}
  },
  methods: {
    cancelEditScore: function () {
      this.$emit('CloseEditScore')
    },
    confirmEditScore: function () {
      this.$emit('CloseEditScore')
    }
  }
}
</script>
<style>
</style>
