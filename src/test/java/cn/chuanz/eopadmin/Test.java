package cn.chuanz.eopadmin;

import com.github.chuanzh.orm.DbBasicService;
import com.github.chuanzh.orm.DbFactory;

import cn.chuanz.util.DbEopConnect;

public class Test {

	public static void main(String[] args) throws Exception {
		DbBasicService dbService = DbFactory.instanceService(DbEopConnect.instance());
		System.out.println(dbService.queryExecSql("select count(*) from app_config"));
	}
	
}
