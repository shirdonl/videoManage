package cn.shirdon.liao.action.group_info;

import java.util.ArrayList;
import java.util.List;

import cn.shirdon.liao.exception.QingException;
import cn.shirdon.liao.service.group_info.GroupInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.shirdon.liao.exception.QingException;
import cn.shirdon.liao.service.group_info.GroupInfoService;
import cn.shirdon.liao.view.GroupInfo;
import cn.shirdon.liao.view.Result;

@Controller
@RequestMapping("group_info")
public class GroupInfoAction {

	@Autowired
	private GroupInfoService groupInfoService;
	
	/**
	 * 查询所有用户组
	 */
	@RequestMapping("list.action")
	public String list(ModelMap map) {
		
		List<GroupInfo> list = groupInfoService.list();
		map.put("list", list);
		
		return "admin/group_info/list";
	}
	
	/**
	 * 保存用户组
	 * @param idArr 用户组主键数组
	 * @param sortArr 用户组排序数组
	 * @param nameArr 用户组名称数组
	 * @param powerArr 用户组权限值数组
	 * @throws QingException
	 */
	@RequestMapping("save.json")
	@ResponseBody
	public Result save(
			@RequestParam(value="idArr") String[] idArr,
			@RequestParam(value="sortArr") String[] sortArr,
			@RequestParam(value="nameArr") String[] nameArr,
			@RequestParam(value="powerArr") String[] powerArr) throws QingException {
		
		List<GroupInfo> groupInfoList = new ArrayList<GroupInfo>();
		
		// 遍历idArr数组
		for (int i=0; i<idArr.length; i++) {
			GroupInfo groupInfo = new GroupInfo();
			groupInfo.setId(idArr[i]);
			groupInfo.setSort(sortArr[i]);
			groupInfo.setName(nameArr[i]);
			groupInfo.setPower(powerArr[i]);
			
			groupInfoList.add(groupInfo);
		}
		
		groupInfoService.save(groupInfoList);

		return Result.success();
	}
	
	/**
	 * 删除
	 * @param idArr 用户组主键数组
	 * @throws QingException 
	 */
	@RequestMapping("delete.json")
	@ResponseBody
	public Result delete(@RequestParam(value="idArr") String[] idArr) throws QingException {
		
		groupInfoService.delete(idArr);
		return Result.success();
	}
}
