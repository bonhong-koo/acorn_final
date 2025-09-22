package com.pcwk.ehr.task;

import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component(value = "pcwkCron")
public class PcwkCron {

	Logger log = LogManager.getLogger(getClass());

	/**
	 * 
	 */
	public PcwkCron() {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ PcwkCron()                                              │");
		log.debug("└─────────────────────────────────────────────────────────┘");
	}

	
	
	//스케줄
	public void runtTask() {
		LocalDateTime  localDateTime =LocalDateTime.now();
		log.debug("┌────────────────────────┐");
		log.debug("│ PcwkCron.runtTask()    │"+localDateTime);
		log.debug("└────────────────────────┘");
	}		

	
}
