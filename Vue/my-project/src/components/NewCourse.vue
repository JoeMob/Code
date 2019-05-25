<template>
  <el-dialog
    title="新增课程"
    :visible.sync="NewCourseFormVisible"
  >
    <el-button @click="cancelNewCourse">取 消</el-button>
    <el-button
      type="primary"
      @click="confirmNewCourse"
    >确 定</el-button>
  </el-dialog>
</template>
<script>
export default {
  name: 'NewCourse',
  props: {
    NewCourseFormVisible: {
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
    cancelNewCourse: function () {
      this.$emit('CloseNewCourse')
    },
    confirmNewCourse: function () {
      this.$emit('CloseNewCourse')
    }
  }
}
</script>
<style>
</style>
