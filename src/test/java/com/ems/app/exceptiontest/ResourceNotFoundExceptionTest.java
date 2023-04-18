package com.ems.app.exceptiontest;

import com.ems.app.exception.ResourceNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ResourceNotFoundExceptionTest {

    @Test
    public void testConstructorAndGetters() {
        String resourceName = "Employee";
        String fieldName = "id";
        Object fieldValue = 1;

        ResourceNotFoundException exception = new ResourceNotFoundException(resourceName, fieldName, fieldValue);

//        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals("Employee Not Found with ID : '1' ", exception.getMessage());
        assertEquals(resourceName, exception.getResourceName());
        assertEquals(fieldName, exception.getFieldName());
        assertEquals(fieldValue, exception.getFieldValue());
    }
}
