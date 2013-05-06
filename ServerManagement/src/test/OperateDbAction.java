package test;

public class OperateDbAction extends MappingDispatchAction {
	private OperateDbBiz operateDbBiz = new OperateDbBizImpl();//实例化数据库操作接口

	/** 
	 * toBackup方法
	 * 加载备份数据库界面
	 */
public ActionForward toBackup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		//备份名以日期来定义，先检查今天是否有备份，如果有的话提示已经备份过了，如果需要重新备份则删除原来的备份后重新备份
		String path = request.getSession().getServletContext().getRealPath("/")+"bakup/";
		String dbName = "mydb";
		String date = TimeManager.getDate2();
		String bakName = path+dbName+date+".bak";
		if(FileUtil.checkFile(bakName)){
			request.setAttribute("hasFile", 1);
			request.setAttribute("date", date);
		}else{
			request.setAttribute("hasFile", 0);
			request.setAttribute("date", date);
		}
		return mapping.findForward("backup");
	}
	/** 
	 * doBackup方法
	 * 备份数据库
	 */
public void doBackup(){
	System.out.println("do bakcup");
}
public ActionForward doBackups(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getSession().getServletContext().getRealPath("/")+"bakup/";
		String dbName = "mydb";
		//获取当前日期(格式yyyy-MM-dd)
		String date = TimeManager.getDate2();
		String bakName = path+dbName+date+".bak";
		boolean flag = true;
		if(FileUtil.checkFile(bakName)){
			//删除原有备份
			flag = FileUtil.deleteFile(bakName);
		}
		if(flag){
			//新备份
			if(operateDbBiz.backupDb(dbName, bakName)==1){
				request.setAttribute("msg", "数据库备份成功！");
			}else{
				request.setAttribute("msg", "数据库备份失败！");
			}
		}
		request.getRequestDispatcher("../operateDb/toBackup.do").forward(request, response);
		return null;
	}
	/** 
	 * toRestore方法
	 * 加载还原数据库界面
	 */
public ActionForward toRestore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		//读取备份文件夹里面的备份信息，获取该数据库的备份文件名，封装后展示在页面上供用户选择
		//数据库名称
		String dbName = "mydb";
		request.setAttribute("dbName", dbName);
		//数据库备份存放路径
		String path = request.getSession().getServletContext().getRealPath("/")+"bakup";//暂时将备份存放bakup文件夹中
		//设置过滤器
		List<String> filterName = new ArrayList<String>();
		filterName.add("bak");
		List<String> list = FileUtil.getFileList(path, filterName);
		if(list==null || list.size()==0){
			request.setAttribute("nodb", "没有找到数据库备份文件！");
		}else{
			//去掉list里面的扩展名和数据库名
			int dbLength = dbName.length();
			for(int j=0;j<list.size();j++){
				String temp = list.get(j);
				list.set(j, (temp.substring(dbLength,temp.length()-4)));
			}
			//保存到request中
			request.setAttribute("list", list);
		}
		return mapping.findForward("restore");
	}
	/** 
	 * doRestore方法
	 * 还原数据库
	 * @throws IOException 
	 * @throws ServletException 
	 */
public ActionForward doRestore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取参数
		String dbName = request.getParameter("txtDbName");//数据库名(针对一个使用单一数据库的程序来说可以写成固定值)
		String bakTime = request.getParameter("txtTime"); //备份的名称中的时间
		String path = request.getSession().getServletContext().getRealPath("/")+"bakup/";
		String bakPath = path+dbName+bakTime+".bak";
		if(operateDbBiz.restoreDb(dbName, bakPath)==1){
			request.setAttribute("msg", "数据库还原成功！");
		}else{
			request.setAttribute("msg", "数据库还原失败！");
		}
		request.getRequestDispatcher("../operateDb/toRestore.do").forward(request, response);
		return null;
	}
}
