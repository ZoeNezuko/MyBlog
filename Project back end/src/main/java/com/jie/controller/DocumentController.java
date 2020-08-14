package com.jie.controller;

import com.jie.model.Document;
import com.jie.result.Result;
import com.jie.result.ResultFactory;
import com.jie.service.DocumentService;
import com.jie.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * @author Jie Pu
 */
@RestController
@RequestMapping("/download")
public class DocumentController {

    @Autowired
    DocumentService documentService;

    // Get request, get all document and pagination
    @RequestMapping(value = "/{size}/{page}", method = RequestMethod.GET)
    public Page list(@PathVariable("size") int size, @PathVariable("page") int page) {
        return documentService.getAll(page - 1, size);
    }

    // Get request, only get information uploaded by users themselves
    @RequestMapping(value = "/user/{size}/{page}", method = RequestMethod.GET)
    public Page getByUser(@PathVariable("size") int size, @PathVariable("page") int page) {
        return documentService.getAllByUser(page - 1, size);
    }

    // Get request, get the id of all document
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Document findById(@PathVariable("id") int id) {
        return documentService.findById(id);
    }

    // Post request, create or update document
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result addOrUpdate(@RequestBody Document document) {
        documentService.addOrUpdate(document);
        return ResultFactory.SuccessResult("Change success");
    }

    // Post request, delete blog according to id
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result delete(@RequestBody Document document) {
        documentService.delete(document.getId());
        return ResultFactory.SuccessResult("delete success");
    }

    // Get request, Search by category
    @RequestMapping(value = "/categories/{cid}/document", method = RequestMethod.GET)
    public List<Document> listCategory(@PathVariable("cid") int cid) {
        if (0 != cid) {
            return documentService.listByCategory(cid);
        } else {
            return documentService.findAll();
        }
    }

    // Post request, upload file
    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        // replaceAll used to replace \\ in windows with /
        return FileUploadUtil.upload(multipartFile).replaceAll("\\\\", "/");
    }

    // Get request, download file
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void downloadFile(HttpServletResponse res, Integer id) {
        Document document = documentService.findById(id);
        String realFileName = document.getFile();
        res.setCharacterEncoding("UTF-8");
        res.setHeader("content-type", "application/octet-stream;charset=UTF-8");
        res.setContentType("application/octet-stream;charset=UTF-8");
        try {
            res.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(realFileName.trim(), "UTF-8"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(realFileName)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                }
            }
        }
    }
}

