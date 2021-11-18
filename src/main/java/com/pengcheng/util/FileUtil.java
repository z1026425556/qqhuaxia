package com.pengcheng.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

    //售卖的临时图片和视频存储文件夹
    public static final String sellOrderTempDir = "D:/tempDir/";
    //售卖图片的源代码文件夹
    public static final String sellOrderImgCodeDir = "D:/idea_demo/qqhuaxia/src/main/resources/static/image/sell/";
    //售卖图片的class文件夹
    public static final String sellOrderImgClassDir = "D:/idea_demo/qqhuaxia/target/classes/static/image/sell/";
    //售卖视频的源代码文件夹
    public static final String sellOrderVideoCodeDir = "D:/idea_demo/qqhuaxia/src/main/resources/static/video/sell/";
    //售卖视频的class文件夹
    public static final String sellOrderVideoClassDir = "D:/idea_demo/qqhuaxia/target/classes/static/video/sell/";

    //删除文件和文件夹
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("删除文件失败:" + fileName + "不存在！");
            return false;
        } else {
            if (file.isFile())
                return deleteFile(fileName);
            else
                return deleteDirectory(fileName);
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName：要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir：要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            System.out.println("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            System.out.println("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            System.out.println("删除目录" + dir + "成功！");
            return true;
        } else {
            return false;
        }
    }

    public static void copyFile(File source,String dest )throws IOException {
        //创建目的地文件夹
        File destfile = new File(dest);
        if(!destfile.exists()){
            destfile.mkdir();
        }
        //如果source是文件夹，则在目的地址中创建新的文件夹
        if(source.isDirectory()){
            File file = new File(dest+"\\"+source.getName());//用目的地址加上source的文件夹名称，创建新的文件夹
            file.mkdir();
            //得到source文件夹的所有文件及目录
            File[] files = source.listFiles();
            if(files.length==0){
                return;
            }else{
                for(int i = 0 ;i<files.length;i++){
                    copyFile(files[i],file.getPath());
                }
            }

        }
        //source是文件，则用字节输入输出流复制文件
        else if(source.isFile()){
            FileInputStream fis = new FileInputStream(source);
            //创建新的文件，保存复制内容，文件名称与源文件名称一致
            File dfile = new File(dest+"\\"+source.getName());
            if(!dfile.exists()){
                dfile.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(dfile);
            // 读写数据
            // 定义数组
            byte[] b = new byte[1024];
            // 定义长度
            int len;
            // 循环读取
            while ((len = fis.read(b))!=-1) {
                // 写出数据
                fos.write(b, 0 , len);
            }

            //关闭资源
            fos.close();
            fis.close();

        }
    }

}
