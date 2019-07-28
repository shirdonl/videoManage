package cn.shirdon.liao.service.player_info;

import java.util.List;

import cn.shirdon.liao.constant.ErrorMsg;
import cn.shirdon.liao.exception.QingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.shirdon.liao.constant.ErrorMsg;
import cn.shirdon.liao.dao.player_info.IPlayerInfoDAO;
import cn.shirdon.liao.dao.video_info.IVideoInfoDAO;
import cn.shirdon.liao.exception.QingException;
import cn.shirdon.liao.view.PlayerInfo;

@Service("PlayerInfoService")
public class PlayerInfoService {
	@Autowired
	private IPlayerInfoDAO iPlayerInfoDAO;
	@Autowired
	private IVideoInfoDAO iVideoInfoDAO;
	
	/**
	 * 查询播放器列表
	 */
	public List<PlayerInfo> list() {
		return iPlayerInfoDAO.list();
	}

	/**
	 * 批量保存播放器
	 * @param playerInfoList
	 */
	public void batchSave(List<PlayerInfo> playerInfoList) {
		for (PlayerInfo playerInfo : playerInfoList) {
			if (StringUtils.isEmpty(playerInfo.getId())) {
				// 插入
				iPlayerInfoDAO.insert(playerInfo);
			} else {
				// 更新
				iPlayerInfoDAO.update(playerInfo);
			}
		}
	}

	/**
	 * 删除播放器
	 * @param idArr 播放器主键数组
	 * @throws QingException
	 */
	public void delete(String[] idArr) throws QingException {
		// 判断所选播放器有没有被使用的
		for (int i=0; i<idArr.length; i++) {
			int nCount = iVideoInfoDAO.countByPlayerId(idArr[i]);
			if (nCount>0) {
				throw new QingException(ErrorMsg.ERROR_400001);
			}
		}
		
		// 批量删除播放器
		iPlayerInfoDAO.delete(idArr);
	}

	/**
	 * 根据主键查询播放器信息
	 * @param id 主键
	 * @return
	 */
	public PlayerInfo selectById(String id) {
		return iPlayerInfoDAO.selectById(id);
	}

	/**
	 * 保存播放器
	 * @param playerInfo
	 */
	public void save(PlayerInfo playerInfo) {
		iPlayerInfoDAO.update(playerInfo);
	}

}
