package com.spring.finalProject.model;

//=== &52. VO 생성하기
//먼저, 오라클에서 tbl_board 테이블을 생성해야 한다.
public class BoardVO {
	
	private String boardSeq; 		// 글번호
	private String fk_bCategorySeq; // 카테고리번호
	private String fk_employeeId; 	// 사원번호
	private String subject; 		// 글제목
	private String content; 		// 글내용
	private String regDate; 		// 작성일자
	private String readCount; 		// 조회수
	private String commentCount; 	// 댓글수

	private String groupno; 		// 답글의 그룹번호
	private String fk_boardSeq; 	// 답글의 원글번호
	private String depthno; 		// 답글의 들여쓰기
	
//	private String fileName; 		// 톰캣저장시 파일명
//	private String orgFilename; 	// 실제파일명
//	private String fileSize; 		// 파일크기
	
	public BoardVO() {}
	
	public BoardVO(String boardSeq, String fk_bCategorySeq, String fk_employeeId, String subject, String content,
			String regDate, String readCount, String commentCount, String groupno, String fk_boardSeq, String depthno) {
		// super();
		this.boardSeq = boardSeq;
		this.fk_bCategorySeq = fk_bCategorySeq;
		this.fk_employeeId = fk_employeeId;
		this.subject = subject;
		this.content = content;
		this.regDate = regDate;
		this.readCount = readCount;
		this.commentCount = commentCount;
		this.groupno = groupno;
		this.fk_boardSeq = fk_boardSeq;
		this.depthno = depthno;
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

	public String getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
	}

	public String getGroupno() {
		return groupno;
	}

	public void setGroupno(String groupno) {
		this.groupno = groupno;
	}

	public String getFk_boardSeq() {
		return fk_boardSeq;
	}

	public void setFk_boardSeq(String fk_boardSeq) {
		this.fk_boardSeq = fk_boardSeq;
	}

	public String getDepthno() {
		return depthno;
	}

	public void setDepthno(String depthno) {
		this.depthno = depthno;
	}
	///////////////////////////////////////////////////////
	
	
	
	
	
	
}
