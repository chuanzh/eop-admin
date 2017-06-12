package cn.chuanz.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.chuanzh.orm.DbBasicService;
import com.github.chuanzh.webframe.FreemarkerBuilder;
import com.github.chuanzh.webframe.HtmlBuilder;
import com.github.chuanzh.webframe.annotation.IjDbService;
import com.github.chuanzh.webframe.annotation.IjHttpServletRequest;
import com.github.chuanzh.webframe.annotation.IjHttpServletResponse;

import cn.chuanz.util.DbEopConnect;

public class Index {

	private HtmlBuilder htmlBuilder = new FreemarkerBuilder();

	@IjDbService(DbEopConnect.class)
	private DbBasicService dbService ;
	
	@IjHttpServletRequest
	private HttpServletRequest httpRequest;
	
	@IjHttpServletResponse
	private HttpServletResponse httpResponse;
	
	
	public String func() {
		htmlBuilder.setTemplatePath("index.ftl");
		return htmlBuilder.toString();
	}
	
	
	
}
