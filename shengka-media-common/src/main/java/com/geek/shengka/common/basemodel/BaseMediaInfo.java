package com.geek.shengka.common.basemodel;

import java.io.Serializable;

/**
 * @Filename: BaseMediaInfo
 * @Description:
 * @Version: 1.0
 * @Author: wangchao
 * @Email: wangchao@hellogeek.com
 * @date: 2019/8/1 ;
 */
public class BaseMediaInfo implements Serializable {

    private static final long serialVersionUID = -4589613732367779172L;

    private String id;
    private String title;		//标题
    private String coverImage;	//封面图片
    private String url;			//视频url
    private String contentCategoryCode;			//内容中心分类id
    private String contentCategoryName;			//内容中心分类name
    private String duration;		//持续时长
    private String giveThumbsNums;	//喜欢数
    private String commentNums;	//评论数
    private String hasBeenCollected;	//收藏数
    private String watchedTimes;	//观看次数
    private String size;	//视频大小
    private Integer score = 0;  //视频热度
    private Integer watchMode;
    private String authorId;	//作者头像
    private String nickname;	//作者名称
    private String avatar;		//作者id
    private String indexId;		//索引id

    public String getId() {
        return id == null ? "0" : id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title == null ? "" : title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getCoverImage() {
        return coverImage == null ? "" : coverImage;
    }
    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
    public String getUrl() {
        return url == null ? "" : url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getContentCategoryCode() {
        return contentCategoryCode;
    }

    public void setContentCategoryCode(String contentCategoryCode) {
        this.contentCategoryCode = contentCategoryCode;
    }

    public String getContentCategoryName() {
        return contentCategoryName;
    }

    public void setContentCategoryName(String contentCategoryName) {
        this.contentCategoryName = contentCategoryName;
    }

    public String getDuration() {
        return duration == null ? "00:30" : duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getWatchMode() {
        return watchMode;
    }

    public void setWatchMode(Integer watchMode) {
        this.watchMode = watchMode;
    }

    public String getGiveThumbsNums() {
        return giveThumbsNums == null ? "0" : giveThumbsNums;
    }
    public void setGiveThumbsNums(String giveThumbsNums) {
        this.giveThumbsNums = giveThumbsNums;
    }
    public String getCommentNums() {
        return commentNums == null ? "0" : commentNums;
    }
    public void setCommentNums(String commentNums) {
        this.commentNums = commentNums;
    }

    public String getWatchedTimes() {
        return watchedTimes == null ? "0" : watchedTimes;
    }
    public void setWatchedTimes(String watchedTimes) {
        this.watchedTimes = watchedTimes;
    }
    public String getHasBeenCollected() {
        return hasBeenCollected == null ? "0" : hasBeenCollected;
    }
    public void setHasBeenCollected(String hasBeenCollected) {
        this.hasBeenCollected = hasBeenCollected;
    }

    public String getSize() {
        return size == null ? "" : size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
    public String getAuthorId() {
        return authorId == null ? "" : authorId;
    }
    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
    public String getNickname() {
        return nickname == null ? "" : nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getAvatar() {
        return avatar == null ? "" : avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIndexId() {
        return indexId;
    }

    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }
}
