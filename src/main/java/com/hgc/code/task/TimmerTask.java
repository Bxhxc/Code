package com.hgc.code.task;

import java.util.Date;
import java.util.TimerTask;

public class TimmerTask extends TimerTask{

	private String taskname;
	
	public TimmerTask(String taskname){
		this.taskname=taskname;
	}
	public String getTaskname() {
		return taskname;
	}
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}
	@Override
	public void run() {
		System.out.println(taskname+new Date().getTime());
	}

}
