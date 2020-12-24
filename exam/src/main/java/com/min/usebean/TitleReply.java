package com.min.usebean;

public class TitleReply {

	public String title(String title,String depth) {
		String temp = "";
		int dep = Integer.parseInt(depth);
		if(dep >0 ) {
			for (int i = 0; i < dep; i++) {
				temp = "&nbsp;&nbsp;&nbsp;&nbsp;";
			}
			 title = temp+ "답글:" + title;
		} 
		return title;
	}
}
