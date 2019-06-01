<template>
  <el-dialog
    @open="init"
    title="新增学生"
    :visible.sync="NewStudentFormVisible"
    :before-close="cancelNewStu"
  >
    <el-form
      :model="form"
      inline="true"
    >
      <el-form-item label="StudentID:">
        <el-input
          v-model="form.StudentID"
          autocomplete="off"
          maxlength="10"
          show-word-limit
        ></el-input>
      </el-form-item>
      <el-form-item label="Name:">
        <el-input
          v-model="form.Name"
          autocomplete="off"
          maxlength="6"
          show-word-limit
        ></el-input>
      </el-form-item>
    </el-form>
    <el-form
      :model="form"
      inline="true"
    >
      <el-form-item label="Age:">
        <el-input-number
          v-model="form.Age"
          :min="1"
          :max="1000"
        ></el-input-number>
      </el-form-item>
      <el-form-item label="Gender:">
        <el-select v-model="form.Gender">
          <el-option
            v-for="item in form.Option"
            :key="item.value"
            :label="item.value"
            :value="item.value"
          ></el-option>
        </el-select>
      </el-form-item>
    </el-form>
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
      form: {
        StudentID: '',
        Name: '',
        Age: '1',
        Gender: '',
        Option: [{
          value: 'Male'
        }, {
          value: 'Female'
        }]
      }
    }
  },
  methods: {
    init () {
      this.form = {
        StudentID: '',
        Name: '',
        Age: '1',
        Gender: '',
        Option: [{
          value: 'Male'
        }, {
          value: 'Female'
        }]
      }
    },
    cancelNewStu: function () {
      this.form = {
        StudentID: '',
        Name: '',
        Age: '1',
        Gender: '',
        Option: [{
          value: 'Male'
        }, {
          value: 'Female'
        }]
      }
      this.$emit('CloseNewStu')
    },
    confirmNewStu: function () {
      this.$axios('http://localhost:8080/student', {
        method: 'post',
        headers: {
          'Content-type': 'application/json'
        },
        data: {
          'studentId': this.form.StudentID,
          'studentName': this.form.Name,
          'age': this.form.Age,
          'gender': this.form.Gender
        }
      })
        .then(function (response) {
          console.log(response)
        })
        .catch(function (error) {
          console.log(error)
        })
      this.form = {
        StudentID: '',
        Name: '',
        Age: '1',
        Gender: '',
        Option: [{
          value: 'Male'
        }, {
          value: 'Female'
        }]
      }
      this.$emit('CloseNewStu')
    }
  }
}
</script>
<style>
</style>
