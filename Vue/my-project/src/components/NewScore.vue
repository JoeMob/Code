<template>
  <el-dialog
    title="新增成绩"
    :visible.sync="NewScoreFormVisible"
  >
    <el-button @click="cancelNewScore">取 消</el-button>
    <el-button
      type="primary"
      @click="confirmNewScore"
    >确 定</el-button>
  </el-dialog>
</template>
<script>
export default {
  name: 'NewScore',
  props: {
    NewScoreFormVisible: {
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
    cancelNewScore: function () {
      this.$emit('CloseNewScore')
    },
    confirmNewScore: function () {
      this.$emit('CloseNewScore')
    }
  }
}
</script>
<style>
</style>
