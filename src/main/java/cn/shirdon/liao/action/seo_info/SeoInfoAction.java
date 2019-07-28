package cn.shirdon.liao.action.seo_info;

import cn.shirdon.liao.service.seo_info.SeoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.shirdon.liao.service.seo_info.SeoInfoService;
import cn.shirdon.liao.view.Result;
import cn.shirdon.liao.view.SeoInfo;

@Controller
@RequestMapping("seo_info")
public class SeoInfoAction {

	@Autowired
	private SeoInfoService seoInfoService;
	
	/**
	 * 查询seo信息
	 */
	@RequestMapping("edit.action")
	public String edit(ModelMap map,
			@RequestParam(value="type") String type) {
		
		SeoInfo seoInfo = seoInfoService.selectByType(type);
		map.put("seoInfo", seoInfo);
		
		if ("index".equals(type)) {
			return "admin/seo_index/edit";
		} else if ("list".equals(type)) {
			return "admin/seo_list/edit";
		} else if ("play".equals(type)) {
			return "admin/seo_play/edit";
		} else if ("profile".equals(type)) {
			return "admin/seo_profile/edit";
		} else {
			return null;
		}
	}
	
	/**
	 * 保存seo信息配置
	 * @param seoInfo
	 * @return
	 */
	@RequestMapping("save.json")
	@ResponseBody
	public Result save(SeoInfo seoInfo) {
		
		seoInfoService.save(seoInfo);
		return Result.success();
	}
}
