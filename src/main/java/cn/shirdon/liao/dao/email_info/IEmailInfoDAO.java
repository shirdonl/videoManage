package cn.shirdon.liao.dao.email_info;

import java.util.List;

import cn.shirdon.liao.view.EmailInfo;
import org.apache.ibatis.annotations.Param;

import cn.shirdon.liao.view.EmailInfo;

public interface IEmailInfoDAO {

	List<EmailInfo> list();

	int insert(EmailInfo emailInfo);

	int update(EmailInfo emailInfo);

	int delete(@Param("idArr") String[] idArr);

	/**
	 * 根据主键查询记录信息
	 * @param id 主键
	 * @return
	 */
	EmailInfo selectById(String id);
	
}