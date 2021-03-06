package datamotion.mvc.t6_dwnloadfile;

import java.sql.Timestamp;
import java.util.List;

import com.platform.constant.ConstantRender;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;

import oracle.net.aso.s;

import org.apache.log4j.Logger;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import csuduc.platform.util.JsonUtils;
import csuduc.platform.util.generID.UUIDGener;
import datamotion.constant.ConstantInitMy;
import datamotion.mvc.mdlcomm.MdlClientCheckout;


/**
 * XXX 管理	
 * 描述：
 * 
 * /jf/datamotion/t6_dwnloadfile
 * /jf/datamotion/t6_dwnloadfile/save
 * /jf/datamotion/t6_dwnloadfile/edit
 * /jf/datamotion/t6_dwnloadfile/update
 * /jf/datamotion/t6_dwnloadfile/view
 * /jf/datamotion/t6_dwnloadfile/delete
 * /datamotion/t6_dwnloadfile/add.html
 * 
 */
//@Controller(controllerKey = "/jf/datamotion/t6_dwnloadfile")
public class T6_dwnloadfileController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T6_dwnloadfileController.class);

	public static final String pthc = "/jf/datamotion/t6_dwnloadfile/";
	public static final String pthv = "/datamotion/t6_dwnloadfile/";
	public static final String pthvf = "/datamotion/f/";
	/**
	 * 列表
	 */
	@Clear
	public void index() {
		/*paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T6_dwnloadfile.sqlId_splitPage_from);
		renderWithPath(pthv+"list.html");*/
		
		String sql = "select * from t6_dwnloadfile order by id desc limit ?";
		List<T6_dwnloadfile> list = T6_dwnloadfile.dao.find(sql, 100);//取数据库的前100条记录
		
		setAttr("list", list);
		renderWithPath(pthv+"dwnloadfile.html");
	}
	
	// 查询
		@Clear
		public void search() {

			// 获得参数
			String strvalue = getPara("v");
			if (null == strvalue || strvalue.isEmpty()) {
				renderText("-1");//错误
			}
			try {
				MdlClientCheckout mdlClient = JsonUtils.deserialize(strvalue, MdlClientCheckout.class);
				if (null == mdlClient) {
	renderText("-1");//错误
					return;
				}
				
				// 遍历树结构，拼接SQL语句
				String sqlString = "select * from tg2datastore.t6_dwnloadfile "
						+ "where timedo > '" + mdlClient.getTimebegdb() + "' and timedo < '" + mdlClient.getTimeenddb() 
						+ "' and timerecive > '" + mdlClient.timebegreceive + "' and timerecive < '" + mdlClient.timeendreceive 
						+ "' and timecollectstart > '" + mdlClient.timebegcollect +"' and timecollectend < '" +mdlClient.timeendcollect + "'";
				
				// 数据库查询
				List<T6_dwnloadfile> list = T6_dwnloadfile.dao.find(sqlString);//取数据库的前100条记录
				log.debug("=========================="+list);
				
				// 返回结果
				setAttr("list", list);
				renderWithPath(pthv+"dwnloadfile.html");
				
				
				log.debug(JsonUtils.serialize(mdlClient));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				renderText("-1");//错误
				return;
			}

			
			

			// renderJson(null);
		}

		@Clear
		public void download() {

			return;
		}

		// 全部下载本地
		@Clear
		public void downloadAll() {

			// renderJson(null);
		}

	@Clear
	public void add(){
		String pathsrc="D://MWI//";
		String namesrc="TT_TT02_MWI_VNI_IMG_20161031000000_20161021000000_20161030000000_000_0C.csv";
		String[] namesStrings = namesrc.split("_");
		String pathdest="E://MWI//";
//		String timedo=getPara("datatype");
		int filesize= 3333;
		String station=namesStrings[0];
		String aircraft=namesStrings[1];
		String sensor=namesStrings[2];
		String datatype=namesStrings[3];
		String datalevel=namesStrings[9];
		String camera=namesStrings[4];
		Timestamp timerecive=new Timestamp(System.currentTimeMillis());
//		String timecollectstart=getPara("patharchive");
//		String timecollectend=getPara("ischeckout");
		String suffix=".csv";
		int status_=1;
//		String timeadd=getPara("namemdldes");
		Record t6_dwnloadfileRecord=new Record()
		.set("key_", UUIDGener.getUUIDShort())
		.set("pathsrc", pathsrc)
		.set("namesrc", namesrc)
		.set("pathdest", pathdest)
		.set("timedo", new Timestamp(System.currentTimeMillis()))
		.set("filesize", filesize)
		.set("station", station)
		.set("aircraft", aircraft)
		.set("sensor", sensor)
		.set("datatype", datatype)
		.set("datalevel", datalevel)
		.set("camera", camera)
		.set("timerecive", timerecive)
		.set("timecollectstart", new Timestamp(System.currentTimeMillis()))
		.set("timecollectend", new Timestamp(System.currentTimeMillis()))
		.set("suffix", suffix)
		.set("status_", status_)
		.set("timeadd", new Timestamp(System.currentTimeMillis()));
		Db.use(ConstantInitMy.db_dataSource_main)
		  .save("t6_dwnloadfile", t6_dwnloadfileRecord);
	}
	@Clear
	public void viewControlPanel() {
		renderWithPath(pthvf+"controlpanel.html");
	}

	
	/**
	 * 保存
	 */
	@Before(T6_dwnloadfileValidator.class)
	public void save() {
		T6_dwnloadfile t6_dwnloadfile = getModel(T6_dwnloadfile.class);
		//other set 
		
		//t6_dwnloadfile.save();		//guiid
		t6_dwnloadfile.saveGenIntId();	//serial int id
		renderWithPath(pthv+"add.html");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		//T6_dwnloadfile t6_dwnloadfile = T6_dwnloadfile.dao.findById(getPara());	//guuid
		T6_dwnloadfile t6_dwnloadfile = T6_dwnloadfileService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t6_dwnloadfile", t6_dwnloadfile);
		renderWithPath(pthv+"update.html");

	}
	
	/**
	 * 更新
	 */
	@Before(T6_dwnloadfileValidator.class)
	public void update() {
		getModel(T6_dwnloadfile.class).update();
		redirect(pthc);
	}

	/**
	 * 查看
	 */
	public void view() {
		//T6_dwnloadfile t6_dwnloadfile = T6_dwnloadfile.dao.findById(getPara());	//guuid
		T6_dwnloadfile t6_dwnloadfile = T6_dwnloadfileService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t6_dwnloadfile", t6_dwnloadfile);
		renderWithPath(pthv+"view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		//T6_dwnloadfileService.service.delete("t6_dwnloadfile", getPara() == null ? ids : getPara());	//guuid
		T6_dwnloadfileService.service.deleteById("t6_dwnloadfile", getPara() == null ? ids : getPara());	//serial int id
		redirect(pthc);
	}
	
	public void setViewPath(){
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}
	
}
