package org.example;

public class NullParameterException extends NullPointerException{
    public NullParameterException(){
        super();
    }

    public NullParameterException(String str){
        super(str);
    }

    @Override
    public String toString(){
        return super.getMessage();
    }
}
