package com.spring.finalProject.model;

//=== &52. VO 생성하기
//먼저, 오라클에서 tbl_board 테이블을 생성해야 한다.
public class BoardVO_OHJ {
	
	private String boardSeq; 		// 글번호
	private String fk_bCategorySeq; // 카테고리번호
	private String fk_employeeId; 	// 사원번호
	private String subject; 		// 글제목
	private String content; 		// 글내용
	private String regDate; 		// 작성일자
	private String readCount; 		// 조회수
/*	
	private String commentCount; 	// 댓글수

	private String groupno; 		// 답글의 그룹번호
	private String fk_boardSeq; 	// 답글의 원글번호
	private String depthno; 		// 답글의 들여쓰기
	
	private String fileName; 		// 톰캣저장시 파일명
	private String orgFilename; 	// 실제파일명
	private String fileSize; 		// 파일크기
*/
	
	////////////////////////////////////////////
	private String name; 			// select용. 글쓴이 -> ohhj.xml에서 join한 결과를 자동으로 set해서 담는 용도임.
	private String positionName; 	// select용. 직급명
	private String bCategoryName; 	// select용. 게시판 카테고리명
	////////////////////////////////////////////
	
	public BoardVO_OHJ() {}
	
	public BoardVO_OHJ(String boardSeq, String fk_bCategorySeq, String fk_employeeId, String subject, String content,
			String regDate, String readCount) {
		//super();
		this.boardSeq = boardSeq;
		this.fk_bCategorySeq = fk_bCategorySeq;
		this.fk_employeeId = fk_employeeId;
		this.subject = subject;
		this.content = content;
		this.regDate = regDate;
		this.readCount = readCount;
	}

	
	public String getBoardSeq() {
		return boardSeq;
	}

	public void setBoardSeq(String boardSeq) {
		this.boardSeq = boardSeq;
	}

	public String getFk_bCategorySeq() {
		return fk_bCategorySeq;
	}

	public void setFk_bCategorySeq(String fk_bCategorySeq) {
		this.fk_bCategorySeq = fk_bCategorySeq;
	}

	public String getFk_employeeId() {
		return fk_employeeId;
	}

	public void setFk_employeeId(String fk_employeeId) {
		this.fk_employeeId = fk_employeeId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getReadCount() {
		return readCount;
	}

	public void setReadCount(String readCount) {
		this.readCount = readCount;
	}

	///////////////////////////////////////////////////////
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getbCategoryName() {
		return bCategoryName;
	}

	public void setbCategoryName(String bCategoryName) {
		this.bCategoryName = bCategoryName;
	}	
	
	
	
	
	
}
