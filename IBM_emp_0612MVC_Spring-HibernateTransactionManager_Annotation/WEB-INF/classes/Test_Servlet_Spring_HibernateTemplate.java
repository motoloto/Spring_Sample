/** 自行取得sessionFactory的 servlet
 
      註1: 需搭配 : 
       A. classpath底下hibernate.cfg.xml,
       B. com.emp.model.EmpVO.java
       C. com/emp/model/emp2.hbm.xml
       
      註2: 注意:
       A. 應詳細比這一版的
          com.emp.model.EmpVO.java
          com/emp/model/emp2.hbm.xml
                          與上一版哪裡不同
 */
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.util.*;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.emp.model.EmpVO;

public class Test_Servlet_Spring_HibernateTemplate extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("text/plain; charset=Big5");
		PrintWriter out = res.getWriter();
		
		//註1: Spring在web應用程式的檔案架構中,必須使用專為Servlet設計的WebApplicationContext介面【ApplicationContext的子介面】,以取得定義在web.xml的XML的定義檔
		//註2: 以此範例而言, 目前是取得定義在web.xml內第48行的/WEB-INF/model-config.xml
		//註3: 參考web.xml內的42-49行,是必要的設定
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		HibernateTemplate hibernateTemplate = (HibernateTemplate) ctx.getBean("hibernateTemplate"); // 取出在/WEB-INF/model-config.xml內第37行所設定的hibernateTemplate

	
			List<EmpVO> list = hibernateTemplate.find("from EmpVO");
			for (EmpVO aEmp : list) {
				out.print(aEmp.getEmpno() + ",");
				out.print(aEmp.getEname() + ",");
				out.print(aEmp.getJob() + ",");
				out.print(aEmp.getHiredate() + ",");
				out.print(aEmp.getSal() + ",");
				out.print(aEmp.getComm() + ",");
				//out.print(aEmp.getDeptno());
				//注意以下三行的寫法 (優!)
				out.print(aEmp.getDeptVO().getDeptno() + ",");
				out.print(aEmp.getDeptVO().getDname() + ",");
				out.print(aEmp.getDeptVO().getLoc());
				out.println();
			}


	}
}