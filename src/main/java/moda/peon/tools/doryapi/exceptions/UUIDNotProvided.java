package moda.peon.tools.doryapi.exceptions;


public class UUIDNotProvided extends RuntimeException{
    public UUIDNotProvided(){
        super("UUID not provided");
    }

    public UUIDNotProvided(String msg){
        super(msg);
    }
}
