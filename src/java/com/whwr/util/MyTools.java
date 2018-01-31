package com.whwr.util;

import java.io.*;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

public class MyTools {

    private static final int BUFFER_SIZE = 16 * 1024;

    public static String uploadZhuPhoto(HttpServletRequest request, File photo, String oldPhotoName,
            String id) {
        String newPhotoName = "";
        try {
            //获取web应用在磁盘上的路径，是绝对路径
            String filePath = request.getSession().getServletContext()
                    .getRealPath("/");
            System.out.println(oldPhotoName);
            newPhotoName = UUID.randomUUID().toString() + oldPhotoName.substring(oldPhotoName.indexOf("."), oldPhotoName.length());
            String newPath = filePath + "images" + "\\" + "zimg";
            File f = new File(newPath);
            if (!f.isDirectory()) {
                f.mkdirs();
            }
            writeFile(photo, newPath + "\\" + newPhotoName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return newPhotoName;
    }

    public static String uploadDiscPhoto(HttpServletRequest request, File photo, String oldPhotoName,
            String id) {
        String newPhotoName = "";
        try {
            //获取web应用在磁盘上的路径，是绝对路径
            String filePath = request.getSession().getServletContext()
                    .getRealPath("/");
            System.out.println(oldPhotoName);
            newPhotoName = UUID.randomUUID().toString() + oldPhotoName.substring(oldPhotoName.indexOf("."), oldPhotoName.length());
            String newPath = filePath + "images" + "\\" + "disimg" + "\\" + id;
            File f = new File(newPath);
            if (!f.isDirectory()) {
                f.mkdirs();
            }
            writeFile(photo, newPath + "\\" + newPhotoName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return newPhotoName;
    }

    private static void writeFile(File src, String newPath) {
        try {
            InputStream in = null;
            OutputStream out = null;
            try {

                in = new BufferedInputStream(new FileInputStream(src),
                        BUFFER_SIZE);
                out = new BufferedOutputStream(new FileOutputStream(newPath),
                        BUFFER_SIZE);
                byte[] buffer = new byte[BUFFER_SIZE];
                while (in.read(buffer) > 0) {
                    out.write(buffer);
                }
            } finally {
                if (null != in) {
                    in.close();
                }
                if (null != out) {
                    out.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
