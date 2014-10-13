/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cwctravel.hudson.plugins.extended_choice_parameter;

import hudson.Util;
import java.io.IOException;
import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 * A helper class for multi-level parameters.
 * @author Oleg Nenashev <o.v.nenashev@gmail.com>
 */
/*package*/ class MultiLevelSelectHelper {
    
    public static SpecialString getDefaultDelimiter() {
        return SpecialString.TAB;
    }
    
    /**
     * Decodes values like multi-level delimiters.
     * @param encodedValue Input value
     * @return Decoded value. It may me equal to null if nothing has been found
     * @throws IOException Input cannot be converted to char
     */
    public static @CheckForNull Character getRawDelimiter(@Nonnull String encodedValue) throws IOException {
        encodedValue = Util.fixEmptyAndTrim(encodedValue);
        if (encodedValue == null) {
            throw new IOException("The trimmed string is null");
        }
        
        for (SpecialString s : SpecialString.values()) {
            if (s.getEncodedString().equals(encodedValue)) {
                return s.getResultString();
            }
        }
        
        if (encodedValue.length() == 1) {
            return encodedValue.charAt(0);
        } else {
            throw new IOException("");
        }
    }
    
    public static enum SpecialString {
        
        TAB("\\t",'\t'),
        SPACE("\\s",' ');
        
        String encodedString;
        char resultString;

        private SpecialString(String encodedString, char resultString) {
            this.encodedString = encodedString;
            this.resultString = resultString;
        }

        public String getEncodedString() {
            return encodedString;
        }

        public char getResultString() {
            return resultString;
        }        
    }
}
