package cn.shirdon.liao.dao.qiniu_info;

import cn.shirdon.liao.view.QiniuInfo;

public interface IQiniuInfoDAO {

	/**
	 * 查询指定类型的配置
	 * @param type 类型
	 * @return
	 */
	QiniuInfo selectByType(String type);
	
	/**
	 * 根据类型更新
	 * @param qiniuInfo
	 * @return
	 */
	int update(QiniuInfo qiniuInfo);
}