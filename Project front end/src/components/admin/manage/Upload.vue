<template>
  <el-upload
    action="http://localhost:8666/download/file"
    class="upload-doc"
    ref="upload"
    accept=".docx, .pdf"
    with-credentials
    :on-preview="handlePreview"
    :on-success="handleSuccess"
    :on-remove="handleRemove"
    :before-upload="beforUpload"
    :before-remove="beforeRemove"
    :limit="1"
    :on-exceed="handleExceed"
    :file-list="fileList"
  >
    <el-button size="small" type="primary">Upload</el-button>
    <div slot="tip">
      Only docx or pdf files can be uploaded, and no more than 30MB.
    </div>
  </el-upload>
</template>

<script>
export default {
  name: "Upload",
  data() {
    return {
      fileList: [],
      url: ""
    };
  },
  methods: {
    handlePreview(file) {
      console.log(file);
      // Here is the entire file
    },
    // Tips for successful upload
    handleSuccess(response) {
      this.url = response;
      this.$emit("onUpload");
      this.$message.success("File upload successfully");
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    // File number limit
    handleExceed(files) {
      this.$message.warning(
        `Limit selection to 1 file, ${files.length} files selected this time`
      );
    },
    // Upload format restrictions
    beforUpload(file) {
      console.log(file);
      var fileMsg = file.name.substring(file.name.lastIndexOf(".") + 1);
      const extension = fileMsg === "docx";
      const extension2 = fileMsg === "pdf";
      // File size limit
      const limit = file.size / 1024 / 1024 < 30;
      if (!extension && !extension2) {
        this.$message({
          message: "Upload files can only be in docx, pdf format!",
          type: "error"
        });
      }
      if (!limit) {
        this.$message({
          message: "Upload file size cannot exceed 30MB!",
          type: "error"
        });
      }
      return (extension || extension2) && limit;
    },
    // Tips for deleting files
    beforeRemove(file) {
      return this.$confirm(`Are you sure to remove ${file.name}?`);
    },
    /**
     * When the component receives the success message returned by the backend,
     * it will trigger the onUpload event of the parent component EditForm.vue
     */
    clear() {
      this.$refs.upload.clearFiles();
    }
  }
};
</script>

<style></style>
