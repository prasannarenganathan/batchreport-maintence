package com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.writer;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.config.ApplicationConfig;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.config.CloudConfig;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.exception.IvansBatchItemException;
import com.google.cloud.WriteChannel;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.common.io.ByteStreams;


@Service
public class GCloudWriter {

	 private static final String CONTENT_TYPE= "text/csv";
    
	@Autowired
	 private CloudConfig cloudConfig;
	
	@Value("${gcp.cna.sur.extagcy.ivans.documents.bucket.name}")
	String bucketName;
	
	@Autowired
    ApplicationConfig applicationConfig;
	
	public String writeFile(FileSystemResource resource) throws IvansBatchItemException  {
		
		 String storageSuccesslink = null;
		 try {
         final InputStream targetStream = new DataInputStream(new FileInputStream(resource.getFile()));
         
         byte[] targetArray = ByteStreams.toByteArray(targetStream);
         
             
                 BlobId blobId = BlobId.of(bucketName, resource.getFilename());
                 BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(CONTENT_TYPE).build();
                 try (WriteChannel writer = cloudConfig.getStorage().writer(blobInfo)) {
                   try {
                     writer.write(ByteBuffer.wrap(targetArray, 0, targetArray.length));
                   } catch (Exception ex) {
                     throw ex;
                   }
                 }
                 storageSuccesslink= blobInfo.getMediaLink();
		 }catch(Exception e) {
			 throw new IvansBatchItemException("Error in Writing file to GCloud:"+resource.getFilename(), e) ;
		 }
         
		return storageSuccesslink;
	}
		
}
