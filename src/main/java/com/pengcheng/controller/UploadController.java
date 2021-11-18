package com.pengcheng.controller;

import com.pengcheng.util.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class UploadController {

    @PostMapping("/multiUpload")
    @ResponseBody
    public String multiUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("files");
        String sonPath = UUID.randomUUID().toString();
        String filePath = FileUtil.sellOrderTempDir;
        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);

            if (file.isEmpty()) {
                return "上传第" + (i++) + "个文件失败";
            }
            String fileName = file.getOriginalFilename();
            File dest = null;
            new File(filePath + sonPath).mkdir();
            if(fileName.endsWith(".mp4")){
                dest = new File(filePath + sonPath + "/" + fileName);
            }else{
                dest = new File(filePath + sonPath + "/" + fileName);
            }
            try {
                file.transferTo(dest);
            } catch (IOException e) {
                return "上传第" + (i++) + "个文件失败";
            }
        }
        return sonPath;
    }

}
