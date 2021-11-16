package com.spring.finalProject.controller;

import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.approval.model.ApcategoryVO;
import com.spring.finalProject.common.FileManager;
import com.spring.finalProject.model.DepartmentVO_KGH;
import com.spring.finalProject.service.InterWHCService;

//==== #30. 컨트롤러 선언 ====
@Component
@Controller
public class WHCContoller {
	
	@RequestMapping(value = "/index.gw")
	public String index(HttpServletRequest request) {
		
		return "main/index.tiles1";
		//	/WEB-INF/views/tiles1/main/index.jsp 파일을 생성한다.
	}
	
	@Autowired
	private InterWHCService service;
	// === #155. 파일업로드 및 다운로드를 해주는 FileManager 클래스 의존객체 주입하기(DI : Dependency Injection) === //

	@Autowired
	private FileManager fileManager;
	
	// === 전자결재 메인 페이지 === //
	@RequestMapping(value="/approval.gw")
	public String approvalMain(HttpServletRequest request) {
		
		return "approval/approvalMain.tiles_WHC";
		//  /WEB-INF/views/tiles_WHC/approval/approvalMain.jsp 파일을 생성한다.
	}
	
	// === 기안하기 버튼 클릭시 기안양식 페이지 === //
	@RequestMapping(value="/approvalForm.gw")
	public ModelAndView approvalForm(HttpServletRequest request, ModelAndView mav) {

		// 기안양식 카테고리 얻어오기
		List<ApcategoryVO> apcList = service.getApcategoryList();
		
		mav.addObject("apcList", apcList);
		mav.setViewName("approval/approvalForm.tiles_WHC");
		
		return mav;
		
	}
	
	// === 기안양식을 골랐을때 기안작성하는 페이지 === //
	@RequestMapping(value="/addApproval.gw")
	public ModelAndView addApproval(HttpServletRequest request, ModelAndView mav) {
		
		// 선택한 기안양식에 맞는 form을 보여주기 위해 받아온 카테고리 값 넘겨주기
		String apcano = request.getParameter("apcano");
		String apcaname = request.getParameter("apcaname");
		mav.addObject("apcano", apcano);
		mav.addObject("apcaname", apcaname);
		
		// 결제선에 이용할 부서명, 부서번호 받아오기
		List<DepartmentVO_KGH> departList = service.getDepartment();
		mav.addObject("departList", departList);
		
		mav.setViewName("approval/addApproval.tiles_WHC");
		return mav;
		
	}
	
	// 결재선 설정 시 검색어가 있는 직원 목록 받아오기
	@ResponseBody
	@RequestMapping(value="/approval/getEmployeeList.gw", method={RequestMethod.GET}, produces="text/plain;charset=UTF-8")
	public String getEmployeeList(HttpServletRequest request) {
		
		String searchWord = request.getParameter("searchWord");
		
		if(searchWord == null || searchWord.trim() == "") {
			searchWord = "";
		}
		
		Map<String, String> paraMap = new HashMap<>();
		
		paraMap.put("searchWord", searchWord);
		
		List<Map<String,String>> empList = service.getEmployeeList(paraMap);
		
		JSONArray jsonArr = new JSONArray(); // []
		
		if(empList != null) {
			for(Map<String,String> map: empList) {
				JSONObject jsonObj = new JSONObject();
				
				jsonObj.put("employeeid", map.get("employeeid"));
				jsonObj.put("name", map.get("name"));
				jsonObj.put("departno", map.get("departno"));
				jsonObj.put("departmentname", map.get("departmentname"));
				jsonObj.put("positionno", map.get("positionno"));
				jsonObj.put("positionname", map.get("positionname"));
				
				jsonArr.put(jsonObj);
			}
		}
		
		return jsonArr.toString();
	}
	
	
	// === 스마트에디터. 드래그앤드롭을 사용한 다중 사진 파일 업로드 === //
	@RequestMapping(value="/image/multiplePhotoUpload.gw", method={RequestMethod.POST})
	public void multiplePhotoUpload(HttpServletRequest request, HttpServletResponse response) {
		
		/*
	      1. 사용자가 보낸 파일을 WAS(톰캣)의 특정 폴더에 저장해주어야 한다.
	      >>>> 파일이 업로드 되어질 특정 경로(폴더)지정해주기
	           우리는 WAS 의 webapp/resources/photo_upload 라는 폴더로 지정해준다.
	    */
		
		// WAS 의 webapp 의 절대경로를 알아와야 한다. 
		HttpSession session = request.getSession();
		String root = session.getServletContext().getRealPath("/"); 
		String path = root + "resources"+File.separator+"photo_upload";
		// path 가 첨부파일들을 저장할 WAS(톰캣)의 폴더가 된다. 
	     
		// System.out.println(">>>> 확인용 path ==> " + path); 
		// >>>> 확인용 path ==> C:\NCS\workspace(spring)\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\finalProject\resources\photo_upload    
	     
		File dir = new File(path);
		if(!dir.exists())
	      dir.mkdirs();
		
		String strURL = "";
		
		try {
			String filename = request.getHeader("file-name"); // 파일명을 받는다 - 일반 원본파일명
	        // 네이버 스마트에디터를 사용한 파일업로드시 싱글파일업로드와는 다르게 멀티파일업로드는 파일명이 header 속에 담겨져 넘어오게 되어있다. 
			
			/*
		        [참고]
		        HttpServletRequest의 getHeader() 메소드를 통해 클라이언트 사용자의 정보를 알아올 수 있다. 
		
		       request.getHeader("referer");           // 접속 경로(이전 URL)
		       request.getHeader("user-agent");        // 클라이언트 사용자의 시스템 정보
		       request.getHeader("User-Agent");        // 클라이언트 브라우저 정보 
		       request.getHeader("X-Forwarded-For");   // 클라이언트 ip 주소 
		       request.getHeader("host");              // Host 네임  예: 로컬 환경일 경우 ==> localhost:9090    
		    */
			
			InputStream is = request.getInputStream(); // is 는 네이버 스마트 에디터를 사용하여 사진첨부하기 된 이미지 파일임.
			// java.io 를 import
	        /*
				요청 헤더의 content-type이 application/json 이거나 multipart/form-data 형식일 때,
	            name(이름) 없이 값만 전달될 때 이 값은 요청 헤더가 아닌 바디를 통해 전달된다. 
				이러한 형태의 값을 'payload body'라고 하는데 요청 바디에 직접 쓰여진다 하여 'request body post data'라고도 한다.
				
	           	서블릿에서 payload body 의 값을 읽어오려면 request.getParameter()를 사용하는 것이 아니라 
	            request.getInputStream() 또는 request.getReader()를 사용하여 body를 직접 읽는 방식으로 가져온다.   request.getReader() 는 text 파일 request.getInputStream() 은 이진 파일
	        */
			
			String newFilename = fileManager.doFileUpload(is, filename, path);
			
			String ctxPath = request.getContextPath(); // board
            
            strURL += "&bNewLine=true&sFileName=" + newFilename; 
            strURL += "&sWidth=";
            strURL += "&sFileURL="+ctxPath+"/resources/photo_upload/"+newFilename;
            
         // === 웹브라우저상에 사진 이미지를 쓰기 === //
            PrintWriter out = response.getWriter();
            out.print(strURL);
            
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
}
