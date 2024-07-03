package com.lib.openFeign.decorder;

import com.lib.openFeign.exception.BadRequestException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErrorDecorder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response){
        switch (response.status()){
            case 400:
                return new BadRequestException("400 error");
            default:
                return new Exception("generic exception");
        }
    }
}
