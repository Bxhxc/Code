package com.hgc.code.service.scheduled;

import java.util.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hgc.code.task.TimmerTask;

@Service
public class Timmer {

	@Autowired
	private TimmerTask task;
	@Autowired
	private Timer time;
	
	public void schedule(){
		long delay1 = 1 * 1000; 
		long period1 = 1000; 
		time.schedule(task, delay1, period1); 
	}
}
