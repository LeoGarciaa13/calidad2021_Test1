package com.anahuac.calidad.tareaUnitTest;

public class cuenta {
    
    int balance;
    String holder;
    int zone; 
    alertListener alerts;

    public cuenta(alertListener alerts){ 
        this.alerts = alerts;
    }
    
    public cuenta() {
		// TODO Auto-generated constructor stub
	}
    
    // Setters
    public void setZone(int zone) {
    	this.zone = zone; 
    }
    
    public void setHolder(String holder) {
    	this.holder = holder; 
    }
    
    public void setBalance(int balance) {
    	this.balance = balance; 
    }
    
    // Getters
	public int getBalance() {
        return this.balance;
    }
    
    public int getZone() {
    	if(this.zone == 1 || this.zone == 2 || this.zone == 3 ) {
    		return this.zone;
    	}else {
    		return 0;
    	}
    }
    
    public String getHolder(){
        return this.holder;
    }
    
    // Methods
    void debit(int balance) {
        this.balance -= balance;
        if(this.balance < 100){
            this.alerts.sendAlert(this.holder+", your account balance is below 100");
        }
    }

    void credit(int balance) {
        this.balance += balance;
    }
    
    void setAlertListener(alertListener listener){
        this.alerts = listener;
    }
    
}