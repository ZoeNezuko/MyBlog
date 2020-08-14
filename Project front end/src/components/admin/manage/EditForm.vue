<template>
  <div style="text-align: left">
    <!-- From button -->
    <el-button
      class="add-button"
      type="primary"
      @click="dialogFormVisible = true"
    >
      Upload resources
    </el-button>

    <!-- Dialog box -->
    <el-dialog
      title="Upload resources"
      :visible.sync="dialogFormVisible"
      @close="clear"
    >
      <!-- Form -->
      <el-form :model="form" :rules="rules" ref="ruleForm">
        <!-- title input -->
        <el-form-item
          label="Resources title"
          :label-width="formLabelWidth"
          prop="title"
        >
          <el-input
            v-model="form.title"
            placeholder="Please enter a title"
          ></el-input>
        </el-form-item>

        <!-- description input -->
        <el-form-item
          label="description"
          :label-width="formLabelWidth"
          prop="description"
        >
          <el-input type="textarea" v-model="form.description"></el-input>
        </el-form-item>

        <!-- Date select-->
        <el-form-item
          prop="date"
          label="Date"
          :label-width="formLabelWidth"
          hidden
        >
          <el-col :span="11">
            <el-date-picker
              type="date"
              placeholder="Pick a date"
              v-model="form.date"
              style="width: 100%;"
            ></el-date-picker>
          </el-col>
        </el-form-item>

        <!-- Author -->
        <el-form-item
          label="Author"
          :label-width="formLabelWidth"
          prop="author"
          hidden
        >
          <el-input v-model="form.author"></el-input>
        </el-form-item>

        <!-- Format select -->
        <el-form-item
          label="Format"
          :label-width="formLabelWidth"
          prop="format"
        >
          <el-select v-model="form.format" placeholder="Select format">
            <el-option label="docx" value="docx"></el-option>
            <el-option label="pdf" value="pdf"></el-option>
          </el-select>
        </el-form-item>

        <!-- Category select -->
        <el-form-item
          label="Category"
          :label-width="formLabelWidth"
          prop="category"
        >
          <el-select v-model="form.category.id" placeholder="Select category">
            <el-option label="Python" value="1"></el-option>
            <el-option label="Java" value="2"></el-option>
            <el-option label="C" value="3"></el-option>
            <el-option label="C++" value="4"></el-option>
            <el-option label="JavaScript" value="5"></el-option>
            <el-option label="MySQL" value="6"></el-option>
            <el-option label="PHP" value="7"></el-option>
            <el-option label="Angular" value="8"></el-option>
            <el-option label="React" value="9"></el-option>
            <el-option label="Vue" value="10"></el-option>
            <el-option label="Others" value="11"></el-option>
          </el-select>
        </el-form-item>

        <!-- Upload box -->
        <el-form-item :label-width="formLabelWidth" prop="file">
          <el-input v-model="form.file"></el-input>
          <upload @onUpload="uploadDoc" ref="docUpload"></upload>
        </el-form-item>
      </el-form>

      <!-- Check button -->
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">Cancel</el-button>
        <el-button type="primary" @click="onSubmit">Confirm</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { mapState } from "vuex";
import Upload from "./Upload";
export default {
  name: "EditForm",
  components: { Upload },
  data() {
    return {
      form: {
        id: "",
        title: "",
        description: "",
        date: new Date(),
        author: this.username,
        format: "",
        file: "",
        cid: "",
        category: {
          id: "",
          name: ""
        }
      },
      dialogFormVisible: false,
      // Form verification
      rules: {
        title: [
          {
            required: true,
            message: "Title can not be empty",
            trigger: "blur"
          }
        ],
        description: [
          {
            required: true,
            message:
              "Please do not exceed 200 words and description can not be empty",
            trigger: "blur"
          }
        ],
        format: [
          {
            required: true,
            message: "please choose format",
            trigger: "blur"
          }
        ],
        category: [
          {
            required: true,
            message: "please choose category",
            trigger: "blur"
          }
        ]
      },
      formLabelWidth: "120px"
    };
  },
  computed: {
    ...mapState(["username"])
  },
  mounted() {
    // console.log(this.username);
    this.$set(this.form, "author", this.username.username);
  },
  methods: {
    // Clear the previous input when closing the input box
    clear() {
      this.$refs.docUpload.clear();
      this.form = {
        id: "",
        title: "",
        date: new Date(),
        author: this.username.username,
        description: "",
        format: "",
        file: "",
        category: {}
      };
    },
    // Post request, add document
    onSubmit() {
      this.$refs["ruleForm"].validate(valid => {
        if (valid) {
          this.$axios
            .post("/download/update", {
              id: this.form.id,
              title: this.form.title,
              author: this.form.author,
              description: this.form.description,
              date: this.form.date,
              format: this.form.format,
              category: this.form.category,
              file: this.form.file
            })
            .then(response => {
              if (response && response.data.code === 200) {
                this.$message({
                  type: "success",
                  message: "Upload successfully"
                });
                this.dialogFormVisible = false;
                // Transfer data to the custom event ‘@onSubmit’ of the parent component
                this.$emit("onSubmit");
              }
            })
            .catch(failResponse => {
              alert(failResponse.data + " post request failed");
            });
        }
      });
    },
    // Upload document
    uploadDoc() {
      this.form.file = this.$refs.docUpload.url;
    }
  }
};
</script>

<style>
.add-button {
  margin: 20px 0 0 20px;
}
</style>
