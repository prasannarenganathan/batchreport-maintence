package com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.writer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.exception.IvansBatchItemException;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.model.IvansMessage;


@Service
public class IvansMessageItemWriter implements ItemWriter<IvansMessage> {

	@Autowired
	WriteProcessor writeProcessor;
	
	  private static final String IVANS_MESSAGE_TABLE = "IVANSMESSAGETABLE_";
	
	@Override
	public void write(List<? extends IvansMessage> ivansMessages) throws IvansBatchItemException {
		 List<String[]> data = new ArrayList<String[]>();
 		 try {
 		for (IvansMessage e : ivansMessages) {
            data.add(e.getEntityDataString());
        }
 		writeProcessor.writeFile(data, IVANS_MESSAGE_TABLE);
 		
	}catch(Exception exception){ 			
		 throw new IvansBatchItemException("Error in IvansMessageItemWriter",exception);
	}
	}

}
