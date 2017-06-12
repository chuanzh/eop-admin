package cn.chuanz.control.permission;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.github.chuanzh.orm.ConditionOperator;
import com.github.chuanzh.orm.DbBasicService;
import com.github.chuanzh.orm.DbFactory;
import com.github.chuanzh.util.FuncStatic;
import com.github.chuanzh.util.PagerTool;
import com.github.chuanzh.webframe.FreemarkerBuilder;
import com.github.chuanzh.webframe.HtmlBuilder;
import com.github.chuanzh.webframe.annotation.IjDbService;
import com.github.chuanzh.webframe.annotation.IjHttpServletRequest;
import com.github.chuanzh.webframe.annotation.IjParam;

import cn.chuanz.db.query.AppConfigQuery;
import cn.chuanz.db.rowdata.AppConfigRowData;
import cn.chuanz.util.DbEopConnect;
import net.sf.json.JSONObject;

public class AppConfig {

	private static Logger logger = Logger.getLogger(AppConfig.class);
	@IjDbService(DbEopConnect.class)
	private DbBasicService dbService;
	private HtmlBuilder hBuilder = new FreemarkerBuilder();

	@IjParam
	private Integer page = 1;
	@IjParam
	private Integer pageSize = 10;

	@IjHttpServletRequest
	private HttpServletRequest request;

	public String func() throws Exception {
		List<Map<String, String>> appConfigList = new ArrayList<Map<String, String>>();
		AppConfigQuery query = DbFactory.instance(dbService, AppConfigQuery.class);
		PagerTool pt = PagerTool.init(page, pageSize, (int) query.queryCount());
		query.setPagerDto(pt);
		Map<String, String> appMap = null;
		for (AppConfigRowData app : query.queryRows()) {
			appMap = new HashMap<String, String>();
			appMap.put("id", FuncStatic.toStr(app.getId()));
			appMap.put("name", FuncStatic.toStr(app.getName()));
			appMap.put("app_key", FuncStatic.toStr(app.getAppKey()));
			appMap.put("app_secret", FuncStatic.toStr(app.getAppSecret()));
			appMap.put("bind_ip", app.getBindIp());
			appMap.put("app_limit_type", app.getAppLimitType()+"");
			appMap.put("is_lock", app.getIsLock()+"");
			appMap.put("valid_date", app.getValidDate());
			appMap.put("total_visits_times", app.getTotalVisitsTimes()+"");
			appMap.put("total_limit_times", app.getTotalLimitTimes()+"");
			appMap.put("date_visits_times", app.getDateVisitsTimes()+"");
			appMap.put("date_limit_times", app.getDateLimitTimes()+"");
			appMap.put("limit_rate", app.getLimitRate()+"");
			appConfigList.add(appMap);
		}

		hBuilder.setValue("appConfigList", appConfigList);
		hBuilder.setValue("pagerToolObj", pt);
		hBuilder.setTemplatePath("permission/appConfig.ftl");
		return hBuilder.toString();
	}

	public String doQuery() throws Exception {
		page = Integer.parseInt(request.getParameter("nowPage"));
		AppConfigQuery query = DbFactory.instance(dbService, AppConfigQuery.class);
		if (!FuncStatic.checkIsEmpty(request.getParameter("appName"))) {
			query.ctnName("%"+request.getParameter("appName")+"%",
					ConditionOperator.LIKE);
		}
		PagerTool pt = PagerTool.init(page, pageSize, (int) query.queryCount());
		query.setPagerDto(pt);
		List<Map<String, String>> appConfigList = new ArrayList<Map<String, String>>();
		Map<String, String> appMap = null;
		for (AppConfigRowData app : query.queryRows()) {
			appMap = new HashMap<String, String>();
			appMap.put("id", FuncStatic.toStr(app.getId()));
			appMap.put("name", FuncStatic.toStr(app.getName()));
			appMap.put("app_key", FuncStatic.toStr(app.getAppKey()));
			appMap.put("app_secret", FuncStatic.toStr(app.getAppSecret()));
			appMap.put("bind_ip", app.getBindIp());
			appMap.put("app_limit_type", app.getAppLimitType()+"");
			appMap.put("is_lock", app.getIsLock()+"");
			appMap.put("valid_date", app.getValidDate());
			appMap.put("total_visits_times", app.getTotalVisitsTimes()+"");
			appMap.put("total_limit_times", app.getTotalLimitTimes()+"");
			appMap.put("date_visits_times", app.getDateVisitsTimes()+"");
			appMap.put("date_limit_times", app.getDateLimitTimes()+"");
			appMap.put("limit_rate", app.getLimitRate()+"");
			appConfigList.add(appMap);
		}
		
		hBuilder.setValue("appConfigList", appConfigList);
		hBuilder.setValue("pagerToolObj", pt);
		hBuilder.setTemplatePath("permission/appconfig.ftl");

		hBuilder.setNode("dataHtml");
		return createJson("content", hBuilder.toString()).toString();

	}

	public String findById() throws Exception {
		String id = request.getParameter("id");
		AppConfigRowData app = DbFactory.find(dbService, AppConfigRowData.class,
				id);
		JSONObject jo = new JSONObject();
		jo.put("id", FuncStatic.toStr(app.getId()));
		jo.put("name", FuncStatic.toStr(app.getName()));
		jo.put("app_key", FuncStatic.toStr(app.getAppKey()));
		jo.put("app_secret", FuncStatic.toStr(app.getAppSecret()));
		jo.put("bind_ip", app.getBindIp());
		jo.put("app_limit_type", app.getAppLimitType()+"");
		jo.put("is_lock", app.getIsLock()+"");
		jo.put("valid_date", app.getValidDate());
		jo.put("total_visits_times", app.getTotalVisitsTimes()+"");
		jo.put("total_limit_times", app.getTotalLimitTimes()+"");
		jo.put("date_visits_times", app.getDateVisitsTimes()+"");
		jo.put("date_limit_times", app.getDateLimitTimes()+"");
		jo.put("limit_rate", app.getLimitRate()+"");
		return jo.toString();
	}

	public String save() throws Exception {
		JSONObject jo = new JSONObject();
		try {
			String id = request.getParameter("id");
			AppConfigRowData app = DbFactory.instance(dbService,
					AppConfigRowData.class);
			app.setName(request.getParameter("userName"));
			app.setBindIp(request.getParameter("bindIp"));
			app.setAppLimitType(Integer.parseInt(request.getParameter("appLimitType")));
			app.setValidDate(request.getParameter("validDate"));
			app.setIsLock(Integer.parseInt(request.getParameter("isLock")));
			app.setTotalLimitTimes(Integer.parseInt(request.getParameter("totalLimitTimes")));
			app.setDateLimitTimes(Integer.parseInt(request.getParameter("dateLimitTimes")));
			app.setLimitRate(Integer.parseInt(request.getParameter("limitRate")));
			Date date = new Date();
			if (!FuncStatic.checkIsEmpty(id)) {
				app.setId(Integer.parseInt(id));
				app.update();
			} else {
				app.setInsertTime(date);
				app.insert();
			}
			jo.put("result", "1");
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			jo.put("result", "0");
		}
		return jo.toString();
	}

	public String del() throws Exception {
		JSONObject jo = new JSONObject();
		try {
			String ids = request.getParameter("ids");
			for (String id : ids.split(",")) {
				AppConfigRowData upUser = DbFactory.instance(dbService,
						AppConfigRowData.class);
				upUser.setId(Integer.parseInt(id));
				upUser.delete();
			}
			jo.put("result", "1");
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			jo.put("result", "0");
		}
		return jo.toString();
	}

	private JSONObject createJson(String... args) {
		JSONObject jo = new JSONObject();
		for (int i = 0; i < args.length; i = i + 2) {
			jo.put(args[i], args[i + 1]);
		}
		return jo;
	}
	
}
