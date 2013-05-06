package test;

public class OperateDbAction extends MappingDispatchAction {
	private OperateDbBiz operateDbBiz = new OperateDbBizImpl();//ʵ�������ݿ�����ӿ�

	/** 
	 * toBackup����
	 * ���ر������ݿ����
	 */
public ActionForward toBackup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		//�����������������壬�ȼ������Ƿ��б��ݣ�����еĻ���ʾ�Ѿ����ݹ��ˣ������Ҫ���±�����ɾ��ԭ���ı��ݺ����±���
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
	 * doBackup����
	 * �������ݿ�
	 */
public void doBackup(){
	System.out.println("do bakcup");
}
public ActionForward doBackups(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getSession().getServletContext().getRealPath("/")+"bakup/";
		String dbName = "mydb";
		//��ȡ��ǰ����(��ʽyyyy-MM-dd)
		String date = TimeManager.getDate2();
		String bakName = path+dbName+date+".bak";
		boolean flag = true;
		if(FileUtil.checkFile(bakName)){
			//ɾ��ԭ�б���
			flag = FileUtil.deleteFile(bakName);
		}
		if(flag){
			//�±���
			if(operateDbBiz.backupDb(dbName, bakName)==1){
				request.setAttribute("msg", "���ݿⱸ�ݳɹ���");
			}else{
				request.setAttribute("msg", "���ݿⱸ��ʧ�ܣ�");
			}
		}
		request.getRequestDispatcher("../operateDb/toBackup.do").forward(request, response);
		return null;
	}
	/** 
	 * toRestore����
	 * ���ػ�ԭ���ݿ����
	 */
public ActionForward toRestore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		//��ȡ�����ļ�������ı�����Ϣ����ȡ�����ݿ�ı����ļ�������װ��չʾ��ҳ���Ϲ��û�ѡ��
		//���ݿ�����
		String dbName = "mydb";
		request.setAttribute("dbName", dbName);
		//���ݿⱸ�ݴ��·��
		String path = request.getSession().getServletContext().getRealPath("/")+"bakup";//��ʱ�����ݴ��bakup�ļ�����
		//���ù�����
		List<String> filterName = new ArrayList<String>();
		filterName.add("bak");
		List<String> list = FileUtil.getFileList(path, filterName);
		if(list==null || list.size()==0){
			request.setAttribute("nodb", "û���ҵ����ݿⱸ���ļ���");
		}else{
			//ȥ��list�������չ�������ݿ���
			int dbLength = dbName.length();
			for(int j=0;j<list.size();j++){
				String temp = list.get(j);
				list.set(j, (temp.substring(dbLength,temp.length()-4)));
			}
			//���浽request��
			request.setAttribute("list", list);
		}
		return mapping.findForward("restore");
	}
	/** 
	 * doRestore����
	 * ��ԭ���ݿ�
	 * @throws IOException 
	 * @throws ServletException 
	 */
public ActionForward doRestore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ����
		String dbName = request.getParameter("txtDbName");//���ݿ���(���һ��ʹ�õ�һ���ݿ�ĳ�����˵����д�ɹ̶�ֵ)
		String bakTime = request.getParameter("txtTime"); //���ݵ������е�ʱ��
		String path = request.getSession().getServletContext().getRealPath("/")+"bakup/";
		String bakPath = path+dbName+bakTime+".bak";
		if(operateDbBiz.restoreDb(dbName, bakPath)==1){
			request.setAttribute("msg", "���ݿ⻹ԭ�ɹ���");
		}else{
			request.setAttribute("msg", "���ݿ⻹ԭʧ�ܣ�");
		}
		request.getRequestDispatcher("../operateDb/toRestore.do").forward(request, response);
		return null;
	}
}
