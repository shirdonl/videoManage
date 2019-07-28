package cn.shirdon.liao.dao.comment_info;

import java.util.List;

import cn.shirdon.liao.view.CommentInfo;
import org.apache.ibatis.annotations.Param;

import cn.shirdon.liao.view.CommentInfo;

public interface ICommentInfoDAO {

	/**
	 * 插入新的评论
	 * @param commentInfo
	 * @return
	 */
	int insert(CommentInfo commentInfo);

	/**
	 * 获取某个视频的评论列表
	 * @param videoId 视频主键
	 * @return
	 */
	List<CommentInfo> listByVideoId(String videoId);

	/**
	 * 获取某个视频的评论条数
	 * @param videoId 视频主键
	 * @return
	 */
	int countByVideoId(String videoId);

	/**
	 * 删除评论表中的内容
	 * @param userIdArr 用户id数组
	 * @return
	 */
	int deleteByUserIdArr(@Param("userIdArr") String[] userIdArr);
}