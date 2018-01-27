package net.lsshare.service;

import net.lsshare.dao.NewsDAO;
import net.lsshare.model.News;
import net.lsshare.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

/**
 * @author create by lishuo
 * @date 2018/1/24
 */

/**
 * 咨询业务逻辑
 */
@Service
public class NewsService {

    @Autowired
    private NewsDAO newsDAO;

    /**
     * 查询咨询列表
     * @param userId
     * @param limit
     * @param offset
     * @return
     */
    public List<News> getLatestNews(int userId,int limit,int offset){
        List<News> newsList = newsDAO.selectByUserIdAndOffset(userId,limit,offset);
        return newsList;
    }

    public int addOneNews(News news){
        int flag =newsDAO.addNews(news);
       return news.getId();
    }

    public News getById(int id){
       return newsDAO.selectByid(id);
    }

    /**
     * 上传文件（目前只是上传咨询图片）
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public String UploadFile(MultipartFile multipartFile)throws IOException{
        //1.获取图片类型
        int pos = multipartFile.getOriginalFilename().lastIndexOf(".");
        if(pos < 0){
            return "";
        }
        //查看当前的文件类型是否合法
        String fileType = multipartFile.getOriginalFilename().substring(pos+1).toLowerCase();
        if(!MD5Util.isFileAllowed(fileType)){
            return "";
        }
        //2.将上传的文件按照规定的格式重新命名
        String filename = UUID.randomUUID().toString().replaceAll("-","")+"."+fileType;
        //3.将文件传到指定服务器路径下
        Files.copy(multipartFile.getInputStream(),new File(MD5Util.IMAGE_DIR+filename).toPath()
        , StandardCopyOption.REPLACE_EXISTING);
        return MD5Util.TOUTIAO_DOMAIN+"image?name="+filename;
    }

    /**
     * 更新当前评论数
     * @param id
     * @param count
     * @return
     */
    public int updateCommentCount(int id, int count) {
        return newsDAO.updateCommentCount(id, count);
    }

    /**
     * 更新喜欢数
     * @param id
     * @param count
     * @return
     */
    public int updateLikeCount(int id, int count) {
        return newsDAO.updateLikeCount(id, count);
    }
}
