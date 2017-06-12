package cn.chuanz.util;

import com.github.chuanzh.orm.DbConfBean;
import com.github.chuanzh.orm.dbadapter.MysqlDb;
import com.github.chuanzh.util.FuncStatic;

public class DbEopConnect extends MysqlDb {

	private static DbEopConnect connect = null;
	private DbConfBean[] savleBeanArray = null;
	private DbConfBean masterBean = null;
	private DbEopConnect (){
	}
	public static synchronized DbEopConnect instance(){
		if(connect == null)
			connect = new DbEopConnect();
		return connect;
	}
	
 

	@Override
	protected String getEncode() {
		return "utf-8";
	}
	@Override
	public boolean printSql() {
		return ConfigRead.readBooleanValue("showSql");
	}
	@Override
	protected DbConfBean getMasterDb() {
		if(masterBean == null){
			masterBean = new DbConfBean();
			masterBean.setDbName(ConfigRead.readValue("eop_db_name"));
			masterBean.setIpAndPort(ConfigRead.readValue("eop_db_ipandport"));
			masterBean.setUserName(ConfigRead.readValue("eop_db_username"));
			masterBean.setPassword(ConfigRead.readValue("eop_db_password"));
			masterBean.setPoolConfByStr(ConfigRead.readValue("eop_db_poolconf"));
		}
		return masterBean;
	}
	@Override
	protected DbConfBean[] getSlaveDbArray() {
		String slaveIpStr = ConfigRead.readValue("eop_slave_ipandport");
		if(FuncStatic.checkIsEmpty(slaveIpStr)){
			return null;
		}else{
			if(savleBeanArray == null){
				String[] slaveIpArray = FuncStatic.trim(slaveIpStr.trim(), ";").split(";");
				String[] slaveDbNameArray	= ConfigRead.readValue("eop_slave_name").trim().split(";");
				String[] slaveUserNameArray	= ConfigRead.readValue("eop_slave_username").trim().split(";");
				String[] slavePasswordArray	= ConfigRead.readValue("eop_slave_password").trim().split(";");
				String  slavePoolconf = ConfigRead.readValue("eop_slave_poolconf");
				savleBeanArray = new DbConfBean[slaveIpArray.length];
				for(int i=0; i<slaveIpArray.length; i++){
					DbConfBean bean = new DbConfBean();
					bean.setIpAndPort(slaveIpArray[i]);
					bean.setDbName(slaveDbNameArray[i]);
					bean.setUserName(slaveUserNameArray[i]);
					bean.setPassword(slavePasswordArray[i]);
					bean.setPoolConfByStr(slavePoolconf);
					savleBeanArray[i] = bean;
				}
			}
			return savleBeanArray;
		}
	}
	
}
