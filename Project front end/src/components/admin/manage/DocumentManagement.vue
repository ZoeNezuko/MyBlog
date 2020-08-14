<template>
  <div>
    <el-row style="margin: 10px 0px 0px 20px ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item>Upload management</el-breadcrumb-item>
        <el-breadcrumb-item>Document information</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>

    <!-- EditForm components -->
    <edit-form @onSubmit="loadDocuments()" ref="edit"></edit-form>
    <el-card style="margin: 18px; width: 95%">
      <el-table :data="documents" stripe style="width: 100%">
        <!-- Description -->
        <el-table-column width="5"> </el-table-column>
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline>
              <el-form-item>
                <span>Description: {{ props.row.description }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>

        <!-- Document tital -->
        <el-table-column
          prop="title"
          label="Tital (Expand to view description)"
          fit
        >
        </el-table-column>

        <!--  Document author -->
        <el-table-column prop="author" label="Author" fit></el-table-column>

        <!-- Upload date -->
        <el-table-column prop="date" label="Date" fit> </el-table-column>

        <!-- Document format -->
        <el-table-column prop="format" label="Format" fit> </el-table-column>

        <!-- Document category -->
        <el-table-column prop="category.name" label="Category" fit>
        </el-table-column>

        <!-- Seting button -->
        <el-table-column label="Operating" width="100">
          <template slot-scope="scope">
            <el-button
              @click.native.prevent="editDocuments(scope.row)"
              type="text"
              size="small"
            >
              Edit
            </el-button>
            <el-button
              @click.native.prevent="deleteDocuments(scope.row.id)"
              type="text"
              size="small"
            >
              Delete
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- pagination for documents -->
      <div style="margin: 20px 0 50px 0">
        <el-pagination
          background
          style="float:right;"
          layout="total, prev, pager, next, jumper"
          @current-change="pageChange"
          :page-size="pageSize"
          :total="total"
        >
        </el-pagination>
      </div>
    </el-card>
  </div>
</template>

<script>
import EditForm from "./EditForm";
export default {
  name: "BookManagement",
  components: { EditForm },
  data() {
    return {
      documents: [],
      pageSize: 6,
      total: 0
    };
  },
  mounted() {
    this.loadDocuments();
  },
  methods: {
    // Get request, get all documents by upload user
    loadDocuments() {
      var _this = this;
      this.$axios
        .get("/download/user/" + this.pageSize + "/1")
        .then(response => {
          if (response && response.status === 200) {
            _this.documents = response.data.content;
            _this.total = response.data.totalElements;
          }
        });
    },
    // pagination for documents
    pageChange(page) {
      var _this = this;
      this.$axios
        .get("/download/user/" + this.pageSize + "/" + page)
        .then(response => {
          if (response && response.status === 200) {
            _this.documents = response.data.content;
            _this.total = response.data.totalElements;
          }
        });
    },
    // Post request, delete by document
    deleteDocuments(id) {
      this.$confirm(
        "This operation will permanently delete the document, do you want to continue ?",
        "Prompt",
        {
          confirmButtonText: "Confirm",
          cancelButtonText: "Cancel",
          type: "warning"
        }
      )
        .then(() => {
          this.$axios.post("/download/delete", { id: id }).then(response => {
            if (response && response.status === 200) {
              this.loadDocuments();
              this.$message({
                type: "info",
                message: "Deleted"
              });
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "Cancelled"
          });
        });
    },
    editDocuments(document) {
      this.$refs.edit.dialogFormVisible = true;
      this.$refs.edit.form = {
        id: document.id,
        title: document.title,
        description: document.description,
        date: document.date,
        format: document.format,
        file: document.file,
        category: {
          id: document.category.id.toString(),
          name: document.category.name
        }
      };
    }
  }
};
</script>

<style></style>
