package com.clivern.racter.senders.templates;

import com.clivern.racter.contract.templates.SenderTemplate;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductTemplate implements SenderTemplate {
    
    protected String recipient_id;
    protected String message_string;
    protected ArrayList<HashMap<String, Integer>> elements;
    
    public ArrayList<HashMap<String, Integer>> getElements() {
        return elements;
    }
    
    public Integer setElements(Integer productId) {
        HashMap<String, Integer> element = new HashMap<String, Integer>();
        element.put("id", productId);
        this.elements.add(element);
        return this.elements.size() - 1;
    }
    
    
    public void setRecipientId(String recipient_id) {
        this.recipient_id = recipient_id;
    }
    
    public String getRecipientId() {
        return recipient_id;
    }
    
    /**
     * Build and get message as a string
     *
     * @return String the final message
     */
    public String build() {
        this.message_string = "{";
        
        if (this.recipient_id != null) {
            this.message_string += "\"recipient\": {\"id\": \"" + this.recipient_id + "\"},";
        }
        
        if (!this.elements.isEmpty()) {
            
            this.message_string += "\"message\": {";
            
            this.message_string += "\"attachment\": {";
            
            this.message_string += "\"type\": \"template\",";
            
            this.message_string += "\"payload\": {";
            
            this.message_string += "\"template_type\":\"product\",";
            
            if (!this.elements.isEmpty()) {
                
                this.message_string += "\"elements\":[";
                for (int j = 0; j < this.elements.size(); j++) {
                    HashMap<String, Integer> element = this.elements.get(j);
                    
                    this.message_string += "{";
                    if (!element.get("id").equals("")) {
                        this.message_string += "\"id\":\"" + element.get("id") + "\",";
                    }
                    this.message_string = this.message_string.replaceAll(",$", "");
                    this.message_string += "},";
                }
                
                this.message_string = this.message_string.replaceAll(",$", "");
                this.message_string += "]";
            }
            
            this.message_string += "}";
            
            this.message_string += "}";
            
            this.message_string += "}";
        }
        
        this.message_string = this.message_string.replaceAll(",$", "");
        this.message_string += "}";
        
        return this.message_string;
    }
    
    public void setMessageString(String message_string) {
        this.message_string = message_string;
    }
    
    public String getMessageString() {
        return this.message_string;
    }
}
