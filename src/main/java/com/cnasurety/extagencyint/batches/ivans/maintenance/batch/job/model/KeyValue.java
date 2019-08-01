package com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.model;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"KEY_VALUE_TBL\"", schema = "workflow")
public class KeyValue extends EntityDataReader{
    @Id
    @Column(name = "\"KEY_VALUE_KEY\"")
    private UUID keyValueKey;

    @Column(name = "\"KEY\"")
    private String key;

    @Column(name = "\"VALUE\"")
    private String value;

    @Column(name = "\"LAST_MODIFIED_DATE\"")
    private Timestamp lastModifiedDate;

    @Column(name = "\"KEY_VALUE_PAIR_ID\"")
    private UUID keyValuePairId;

    @Column(name = "\"KEY_VALUE_PAIR_TYPE_CODE\"")
    private String keyValuePairTypeCode;

    private String foriegnKeyId;
    


    public UUID getKeyValueKey() {
        return keyValueKey;
    }

    public void setKeyValueKey(UUID keyValueKey) {
        this.keyValueKey = keyValueKey;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public UUID getKeyValuePairId() {
        return keyValuePairId;
    }

    public void setKeyValuePairId(UUID keyValuePairId) {
        this.keyValuePairId = keyValuePairId;
    }

    public String getKeyValuePairTypeCode() {
        return keyValuePairTypeCode;
    }

    public void setKeyValuePairTypeCode(String keyValuePairTypeCode) {
        this.keyValuePairTypeCode = keyValuePairTypeCode;
    }

    
      public String getForiegnKeyId() { return foriegnKeyId; }
      
      public void setForiegnKeyId(String foriegnKeyId) { this.foriegnKeyId =
      foriegnKeyId; }
     
    
    

}
