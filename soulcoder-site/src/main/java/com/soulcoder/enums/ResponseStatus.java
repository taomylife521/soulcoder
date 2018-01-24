package com.soulcoder.enums;


/**
 * The enum Response status.
 */
public enum ResponseStatus
{

    /**
     * Failed response status.
     */
    Failed(0),
    /**
    * Sucess response status
    */
    Success(1),
    
    /**
    * Exception response status
    */
    Exception(2);

    private int index;
    private ResponseStatus( int index ){
        this.index = index ;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
}
