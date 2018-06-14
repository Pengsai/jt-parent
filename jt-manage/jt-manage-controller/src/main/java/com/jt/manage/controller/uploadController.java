package com.jt.manage.controller;

import com.jt.common.service.SysProperties;
import com.jt.common.vo.PicUploadResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @ClassName uploadController
 * @Description TODO
 * @Author PS
 * @Date 2018/6/13 17:00
 **/
@Controller
public class uploadController {


    /**
     * 1.获取图片的名称
     * 2.获取文件的后缀
     * 3.判断是否为图片类型    gif|jpg|png
     * 4.判断是否为非法文件
     * 5.获取宽度和高度
     * 6.拼接虚拟路径    由浏览器发起访问  url:http://image.jt.com/images/2017/10/31/XXX1.jpg
     * 7.拼接文件夹路径     D://jt-upload/images/2017/10/31
     * 8.拼接本地磁盘路径  D://jt-upload/images/2017/10/31/XXX1.jpg
     * 9.将文件进行写盘操作
     *
     * @param uploadFile
     * @return 页面回显的JSON串: {"error":0,"url":"图片的保存路径","width":图片的宽度,"height":图片的高度}
     * error 0表示图片    1表示非图片
     */
    @RequestMapping("/pic/upload")
    @ResponseBody
    public PicUploadResult uploadFile(MultipartFile uploadFile) {

        PicUploadResult result = new PicUploadResult();

        String originalFilename = uploadFile.getOriginalFilename();

        String endType = originalFilename.toLowerCase().substring(originalFilename.lastIndexOf("."));

        if (!endType.matches("^.*(gif|jpg|png)$")) {
            result.setError(1);
            return result;
        }

        try{
            InputStream inputStream = uploadFile.getInputStream();

            BufferedImage image = ImageIO.read(inputStream);
            result.setHeight(""+image.getHeight());
            result.setWidth(""+image.getWidth());

            //为了避免图片名称重复问题   以yyyy/MM/dd/三位随机数+文件名称
            String datePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
            int randomNumber = new Random().nextInt(900) + 100;

            //页面的访问路径          http://image.jt.com/images/2017/10/31/9991.jpg
            String urlPath = SysProperties.imageUrl + datePath + "/" +randomNumber + originalFilename;
            result.setUrl(urlPath);

            //创建文件夹                  D:/jt-upload/images/2017/10/31/9991.jpg
            String localDir = SysProperties.imagePath + datePath;

            File file = new File(localDir);

            //判断文件夹是否存在
            if(!file.exists()){
                file.mkdirs();	//创建文件夹
            }

            //D:/jt-upload/images/2017/10/31/9991.jpg     执行写盘操作
            String localPath = localDir +"/" + randomNumber+originalFilename;
            uploadFile.transferTo(new File(localPath));

            return result;

        } catch (IOException e) {
            e.printStackTrace();

            result.setError(1);
            return result;
        }

    }



}
