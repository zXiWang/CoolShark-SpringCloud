<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right" style="font-size: 16px;">
      <el-breadcrumb-item :to="{ path: '/sys-admin' }">
        <i class="el-icon-s-promotion"></i> 后台管理
      </el-breadcrumb-item>
      <el-breadcrumb-item>添加属性</el-breadcrumb-item>
    </el-breadcrumb>

    <el-divider></el-divider>

    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="130px" class="demo-ruleForm">
      <el-form-item label="名称" prop="name">
        <el-input v-model="ruleForm.name"></el-input>
      </el-form-item>
      <el-form-item label="简介" prop="description">
        <el-input v-model="ruleForm.description"></el-input>
      </el-form-item>
      <el-form-item label="属性类型" prop="type">
        <el-input v-model="ruleForm.type"></el-input>
      </el-form-item>
      <el-form-item label="计量单位" prop="unit">
        <el-input v-model="ruleForm.unit"></el-input>
      </el-form-item>
      <el-form-item label="排序序号" prop="sort">
        <el-input v-model="ruleForm.sort"></el-input>
      </el-form-item>
      <el-form-item label="所属属性模板" prop="templateIds">
        <el-select
            v-model="ruleForm.templateIds"
            multiple
            collapse-tags
            style="margin-left: 20px;"
            placeholder="请选择">
          <el-option
              v-for="item in attributeTemplateList"
              :key="item.id"
              :label="item.name"
              :value="item.id">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="是否允许自定义" prop="enable">
        <el-switch
            v-model="ruleForm.isAllowCustomize"
            :active-value="1"
            :inactive-value="0"
            active-color="#13ce66"
            inactive-color="#999">
        </el-switch>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">添加</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      attributeTemplateList: [],
      ruleForm: {
        templateIds: '',
        name: '',
        description: '',
        type: '',
        unit: '',
        sort: '',
        isAllowCustomize: false,
      },
      rules: {
        name: [
          {required: true, message: '请输入类别名称', trigger: 'blur'},
          {min: 1, max: 25, message: '长度在 1 到 25 个字符', trigger: 'blur'}
        ],
        templateId: [
          {required: true, message: '请输入所属属性模板id', trigger: 'blur'},
          {min: 1, max: 25, message: '长度在 1 到 25 个字符', trigger: 'blur'}
        ],
      }
    };
  },
  methods: {
    attributeTemplateList(){

    }
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {

        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  },
  mounted() {
    this.attributeTemplateList();
  }
}
</script>

<style scoped>

</style>