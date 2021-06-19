/*
 * The MIT License
 * Copyright © 2021 Daniel Dias
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.medium.danieldiasjava.jakarta.tomee.dto;

import java.util.Collection;

import jakarta.json.bind.annotation.JsonbProperty;

public class ResponseDTO {

    @JsonbProperty(value = "message")
    private String message;
    
    @JsonbProperty(value = "data")
    private Object data;
    
    @JsonbProperty(value = "dataCollection")
    private Collection<?> dataCollection;
    
    public ResponseDTO(Collection<?> dataCollection) {
        this.dataCollection = dataCollection;
    }
    
    public ResponseDTO(Object data,String message) {
        this.message = message;
        this.data = data;
    }
  
    public ResponseDTO() {}

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


	public Object getData() {
		return data;
	}


	public void setData(Object data) {
		this.data = data;
	}

	public Collection<?> getDataCollection() {
		return dataCollection;
	}

	public void setDataCollection(Collection<?> dataCollection) {
		this.dataCollection = dataCollection;
	}

	@Override
	public String toString() {
		return "ResponseDTO [message=" + message + ", data=" + data + ", dataCollection=" + dataCollection + "]";
	}
}