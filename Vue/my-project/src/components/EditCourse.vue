<template>
  <el-dialog
    title="修改课程信息"
    :visible.sync="EditCourseFormVisible"
  >
    <el-button @click="cancelEditCourse">取 消</el-button>
    <el-button
      type="primary"
      @click="confirmEditCourse"
    >确 定</el-button>
  </el-dialog>
</template>
<script>
export default {
  name: 'EditCourse',
  props: {
    EditCourseFormVisible: {
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
    cancelEditCourse: function () {
      this.$emit('CloseEditCourse')
    },
    confirmEditCourse: function () {
      this.$emit('CloseEditCourse')
    }
  }
}
</script>
<style>
</style>
