package cn.com.uangel.adsys.entity;

import java.io.Serializable;
import java.util.List;

public class MSObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private int count;
	private List<MSBook> books;
	private String lastDate;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<MSBook> getBooks() {
		return books;
	}

	public void setBooks(List<MSBook> books) {
		this.books = books;
	}

	public String getLastDate() {
		return lastDate;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

}
