/** �ۦ���osessionFactory�� servlet
 
      ��1: �ݷf�t : 
       A. classpath���Uhibernate.cfg.xml,
       B. com.emp.model.EmpVO.java
       C. com/emp/model/emp2.hbm.xml
       
      ��2: �`�N:
       A. ���ԲӤ�o�@����
          com.emp.model.EmpVO.java
          com/emp/model/emp2.hbm.xml
                          �P�W�@�����̤��P
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
		
		//��1: Spring�bweb���ε{�����ɮ׬[�c��,�����ϥαM��Servlet�]�p��WebApplicationContext�����iApplicationContext���l�����j,�H���o�w�q�bweb.xml��XML���w�q��
		//��2: �H���d�ҦӨ�, �ثe�O���o�w�q�bweb.xml����48�檺/WEB-INF/model-config.xml
		//��3: �Ѧ�web.xml����42-49��,�O���n���]�w
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		HibernateTemplate hibernateTemplate = (HibernateTemplate) ctx.getBean("hibernateTemplate"); // ���X�b/WEB-INF/model-config.xml����37��ҳ]�w��hibernateTemplate

	
			List<EmpVO> list = hibernateTemplate.find("from EmpVO");
			for (EmpVO aEmp : list) {
				out.print(aEmp.getEmpno() + ",");
				out.print(aEmp.getEname() + ",");
				out.print(aEmp.getJob() + ",");
				out.print(aEmp.getHiredate() + ",");
				out.print(aEmp.getSal() + ",");
				out.print(aEmp.getComm() + ",");
				//out.print(aEmp.getDeptno());
				//�`�N�H�U�T�檺�g�k (�u!)
				out.print(aEmp.getDeptVO().getDeptno() + ",");
				out.print(aEmp.getDeptVO().getDname() + ",");
				out.print(aEmp.getDeptVO().getLoc());
				out.println();
			}


	}
}