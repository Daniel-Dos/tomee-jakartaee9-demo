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
package com.medium.danieldiasjava.jakarta.tomee.exception;

import java.util.ArrayList;
import java.util.Iterator;

import jakarta.inject.Inject;
//import javax.validation.ConstraintViolation;
//import javax.validation.ConstraintViolationException;
//import javax.validation.ValidationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;
//import org.apache.cxf.validation.ResponseConstraintViolationException;
import org.slf4j.Logger;

import com.medium.danieldiasjava.jakarta.tomee.dto.Errors;
import com.medium.danieldiasjava.jakarta.tomee.dto.ResponseDTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;

/**
 * Created by Hyun Woo Son on 1/9/18
 **/
@Provider
public class ValidationExceptionMapperCustom implements ExceptionMapper<ValidationException> {

    @Inject
    private Logger logger;
    
    public Response toResponse(ValidationException exception) {
        
        var errorStatus = Response.Status.INTERNAL_SERVER_ERROR;
        var responseVo = new ResponseDTO();
        
        if (exception instanceof  ConstraintViolationException) {
        	
        	 ConstraintViolationException constraint = (ConstraintViolationException)exception;
             Iterator i$ = constraint.getConstraintViolations().iterator();
            
            var errosLista = new ArrayList<Errors>();

            while (i$.hasNext()) {
                ConstraintViolation<?> violation = (ConstraintViolation<?>) i$.next();
                
              logger.info("error {}", violation.getRootBeanClass().getSimpleName() + "." + violation.getPropertyPath() + ": " + violation.getMessage());
              logger.error("error {}", violation.getRootBeanClass().getSimpleName() + "." + violation.getPropertyPath() + ": " + violation.getMessage());
              logger.debug("error {}", violation.getRootBeanClass().getSimpleName() + "." + violation.getPropertyPath() + ": " + violation.getMessage());
              
              var erros = new Errors(violation.getRootBeanClass().getSimpleName().concat(".")
            		                                                             .concat(violation.getPropertyPath().toString()),
            		                                                                     violation.getMessage());
              errosLista.add(erros);
            }

            if (!(constraint instanceof ResponseExceptionMapper)) {
                errorStatus = Response.Status.BAD_REQUEST;
                responseVo.setStatusCode(Status.BAD_REQUEST.getStatusCode());
            }

            responseVo.setErros(errosLista);
        } else {
            responseVo.setMessage("Unexpected error: ".concat(exception.getMessage()));
            logger.debug("Validation error {}", exception.getMessage(), exception);
            logger.info("Validation error {}", exception.getMessage(), exception);
            logger.error("Validation error {}", exception.getMessage(), exception);
        }
        return Response.status(errorStatus).entity(responseVo).build();
    }
}