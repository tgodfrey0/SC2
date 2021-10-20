package com.interpreter;

public class Variable {

    private int value;

    public Variable(){}
    
    public Variable(int value){
        this.value = value;
    }

    public void setValue(int value){
        if(value >= 0){
            this.value = value;
        } else {
            System.err.println("Invalid value");
        }
    }

    public int getValue(){
        return this.value;
    }

    public void increment(){
        this.value++;
    }

    public void decrement(){
        this.value--;
        if(this.value < 0) this.value = 0;
    }

    public void clear(){
        this.value = 0;
    }
    
}
