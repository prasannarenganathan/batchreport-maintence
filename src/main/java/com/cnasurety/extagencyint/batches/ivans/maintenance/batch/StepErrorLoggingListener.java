package com.cnasurety.extagencyint.batches.ivans.maintenance.batch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.exception.IvansBatchItemException;

@Component
public class StepErrorLoggingListener implements StepExecutionListener {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void beforeStep(StepExecution stepExecution) {
        // do nothing.
    }

    
    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
    
        List<Throwable> exceptions = stepExecution.getFailureExceptions();
    
        if (exceptions.isEmpty()) {
            return ExitStatus.COMPLETED;
        }

    
        log.info("This step has occurred some exceptions as follow. " +
                "[step-name:{}] [size:{}]",
                stepExecution.getStepName(), exceptions.size());
        
        exceptions.forEach(th -> 
        {
        	if(th instanceof IvansBatchItemException) {
        		IvansBatchItemException exception = (IvansBatchItemException) th;
        		 log.error("Exception Occured in {}: Step Execution Job Id: {} : Reason: {}",stepExecution.getStepName(), stepExecution.getId(), exception.getMessage());	
        		 log.error("Error: ",exception);
        	}
        	
        });
        
       
        return ExitStatus.FAILED;
    }

}
