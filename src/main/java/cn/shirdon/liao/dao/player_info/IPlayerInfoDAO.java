package cn.shirdon.liao.dao.player_info;

import java.util.List;

import cn.shirdon.liao.view.PlayerInfo;
import org.apache.ibatis.annotations.Param;

import cn.shirdon.liao.view.PlayerInfo;

public interface IPlayerInfoDAO {

	/**
	 * 查询播放器列表
	 */
	List<PlayerInfo> list();

	/**
	 * 插入新的播放器
	 * @param playerInfo
	 * @return
	 */
	int insert(PlayerInfo playerInfo);
	
	/**
	 * 更新播放器
	 * @param playerInfo
	 * @return
	 */
	int update(PlayerInfo playerInfo);

	/**
	 * 批量删除播放器
	 * @param idArr 主键数组
	 * @return
	 */
	int delete(@Param("idArr") String[] idArr);

	/**
	 * 根据主键查询播放器信息
	 * @param id 主键
	 * @return
	 */
	PlayerInfo selectById(String id);

	/**
	 * 根据主键查询播放器内容
	 * @param id 主键
	 * @return
	 */
	String selectContentById(String id);

}