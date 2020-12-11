package board;

import java.sql.Date;

public class Board {

	private int num;
	private String writer;
	private String pwd;
	private String title;
	private Date board_date;
	private String content;

	public Board() {
		super();
	}

	public Board(int num, String writer, String pwd, String title, Date board_date, String content) {
		super();
		this.num = num;
		this.writer = writer;
		this.pwd = pwd;
		this.title = title;
		this.board_date = board_date;
		this.content = content;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getBoard_date() {
		return board_date;
	}

	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Board [num=" + num + ", writer=" + writer + ", pwd=" + pwd + ", title=" + title + ", board_date="
				+ board_date + ", content=" + content + "]";
	}
}
