package servlet;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;

/**
 * Servlet implementation class GuessServlet
 */
//@WebServlet("/guess")
public class GuessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final int TOTAL_GUESS_TIMES = 5;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuessServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//比较的结果信息，默认是大了
				String msg = "big";
				//从session获取猜的次数
				Integer times = (Integer)request.getSession().getAttribute("times");
				if(null == times) {
					times = 1;
				}else {
					times++;
				}
				System.out.println("times：" + times);
				//把次数放入session
				request.getSession().setAttribute("times",times);
				//从session得到guessNum
				int guessNum = Integer.parseInt(request.getParameter("guessNum"));
				System.out.println("guessNum"+guessNum);
				//产生随机数,session没有随机数时
				Integer resultNum = (Integer)request.getSession().getAttribute("resultNum");
				if(null ==resultNum) {
					//把随机数放入session
					resultNum = getRandomNum();
					request.getSession().setAttribute("resultNum",resultNum);
				}
				//打印要猜的随机数
				System.out.println("resultNum" + resultNum);
				if(guessNum == resultNum) {
					msg = "right";
					//相等就重新开始
					reset(request);
					//表示不相等
				}else {
					if(guessNum < resultNum) {
						msg = "little";
					}
					//不相等超过5次就重新开始
					if(times >= TOTAL_GUESS_TIMES ) {
						reset(request);
					}
				}
				//把比较结果返回game.jsp
				response.sendRedirect("game.jsp?msg="+msg + "&times=" + times +"&resultNum=" +resultNum);
			}
			
			private void reset(HttpServletRequest request){
				request.getSession().removeAttribute("times");
				request.getSession().removeAttribute("resultNum");
			}
			

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private static int getRandomNum() {
		int result = -1;
		Random r = new Random();
		result = r.nextInt(30);
		return result;
	}
	public static void main(String[] args) {
		int i =getRandomNum();
		System.out.println(i);
	}
}
