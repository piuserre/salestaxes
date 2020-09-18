package com.lastminute.salestaxes.enums;

public enum BasicBooksEnum {
	
	BOOK("book");
	
	private String bookName;
	
	BasicBooksEnum(String bookName) {
		this.bookName = bookName;
	}
	
	public String getBookName() {
		return this.bookName;
	}

}
