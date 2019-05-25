<template>
  <el-dialog
    title="新增学生"
    :visible.sync="NewStudentFormVisible"
  >
    <el-button @click="cancelNewStu">取 消</el-button>
    <el-button
      type="primary"
      @click="confirmNewStu"
    >确 定</el-button>
  </el-dialog>
</template>
<script>
export default {
  name: 'NewStudent',
  props: {
    NewStudentFormVisible: {
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
    cancelNewStu: function () {
      this.$emit('CloseNewStu')
    },
    confirmNewStu: function () {
      this.$emit('CloseNewStu')
    }
  }
}
</script>
<style>
</style>
