<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right" style="font-size: 16px;">
      <el-breadcrumb-item :to="{ path: '/sys-admin' }">
        <i class="el-icon-s-promotion"></i> 后台管理
      </el-breadcrumb-item>
      <el-breadcrumb-item>属性模板列表</el-breadcrumb-item>
    </el-breadcrumb>

    <el-divider></el-divider>

    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="id" label="序号" align="center" width="180">
      </el-table-column>
      <el-table-column prop="name" label="属性模板名称" align="center" width="180">
      </el-table-column>
      <el-table-column prop="pinyin" align="center" label="属性模板拼音" >
      </el-table-column>
      <el-table-column prop="sort" align="center" label="排序序号" width="100">
      </el-table-column>
      <el-table-column align="center" label="操作" width="150">
        <template slot-scope="scope">
          <el-button @click="handleEdit(scope.row)" type="primary" icon="el-icon-edit" size="mini" circle></el-button>
          <el-button @click="openDeleteConfirm(scope.row)" type="danger" icon="el-icon-delete" size="mini"
                     circle></el-button>
        </template>
      </el-table-column>

    </el-table>
  </div>
</template>

<script>

export default {
  data() {
    return {
      tableData: [],
    }
  },
  methods: {
    handleEdit(attributeTemplate) {
      alert(attributeTemplate.name);
    },
    handleDelete(attributeTemplate) {
      let url = "http://localhost:8080/attributeTemplate/" + attributeTemplate.id + "/delete";
      this.axios.post(url).then(response => {
        let responseBody = response.data;
        if (responseBody.state == 200) {
          this.$message.success("删除成功!");
        } else {
          this.$message.error(responseBody.message);
        }
        this.loadAlbumList();
      })
    },
    openDeleteConfirm(attributeTemplate){
      let title = "提示";
      let message = "此操作将永久删除"+attributeTemplate.name+"属性模板,是否继续?";
      this.$confirm(message, title, {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.handleDelete(attributeTemplate);
      }).catch(() => {
      });
    },
    loadAlbumList() {
      let url = "http://localhost:8080/attributeTemplate/list";
      console.log("url:" + url);
      this.axios.get(url).then(response => {
        let responseBody = response.data;
        console.log(responseBody);
        this.tableData = responseBody.data;
      })
    }
  },
  mounted() {
    this.loadAlbumList();

  },
  created() {
  }
}
</script>
