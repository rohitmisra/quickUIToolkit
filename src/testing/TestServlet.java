package testing;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import sql.SqlService;
@org.springframework.stereotype.Controller
public class TestServlet {
	
	private SqlService sqlService;

    public void setSqlService(final SqlService sqlService) {
        this.sqlService = sqlService;
    }
	
	
	public SqlService getSqlService() {
		return sqlService;
	}


	@RequestMapping(value = {"/getJS/get"})
    public void settings(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
    	response.setCharacterEncoding("UTF-8");
        response.setContentType("text/javascript");
        response.setHeader("Access-Control-Allow-Origin", "*");
        final String username = request.getParameter("username");
        final String password = request.getParameter("password");
        final PrintWriter out = response.getWriter();
        
        
        //System.out.println(sqlService.queryForList("select * from dev_users where username ='"+username+"' and password ='"+password+"'").get(0)[1]);
        
        if(!sqlService.queryForList("select * from dev_users where username ='"+username+"' and password ='"+password+"'").isEmpty()){
            response.setStatus(HttpServletResponse.SC_OK);
            out.write("function sessionInfo() {  var result = {'status' : 'OK', 'type' :'info', 'reason' : 'Login successful!'}; return result;}");
            //response.request.getRequestURL()
            //out.write("logged in");
        }else{
            response.setStatus(HttpServletResponse.SC_OK);
            out.write("function sessionInfo() {  var result = {'status' : 'NOK', 'type' :'error', 'reason' : 'Login unsuccessful!'}; return result;}");
            //response.request.getRequestURL()
            //out.write("access denied");
        }
   	 	out.close();
        out.flush();
    }	
	
	
	
		
}
