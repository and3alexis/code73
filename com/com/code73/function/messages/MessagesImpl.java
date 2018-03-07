package com.code73.function.messages;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.code73.function.exceptionmapper.AppException;

@Component
public class MessagesImpl implements Messages{

    private final String BUNDLE_NAME = "messages";
    private final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
    
    @Override
    public String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
        	throw new AppException(e);
        }
    }
    
    
    public String getString(String key, Object[] params) {
        try {
            return MessageFormat.format(RESOURCE_BUNDLE.getString(key), params);
        } catch (MissingResourceException e) {
            throw new AppException(e);
        }
    }

}
