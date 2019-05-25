<template>
  <el-dialog
    title="修改学生信息"
    :visible.sync="EditStudentFormVisible"
  >
    <el-button @click="cancelEditStu">取 消</el-button>
    <el-button
      type="primary"
      @click="confirmEditStu"
    >确 定</el-button>
  </el-dialog>
</template>
<script>
export default {
  name: 'EditStudent',
  props: {
    EditStudentFormVisible: {
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
    cancelEditStu: function () {
      this.$emit('CloseEditStu')
    },
    confirmEditStu: function () {
      this.$emit('CloseEditStu')
    }
  }
}
</script>
<style>
</style>
