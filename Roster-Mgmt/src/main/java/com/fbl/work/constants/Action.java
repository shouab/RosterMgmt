package com.fbl.work.constants;

public enum Action {
	
	ADD("A"),
	DELETE("D");
	private String actionCode;
	
	private Action(String actionCode) {
		this.actionCode = actionCode;
	}
	
	/**
	 * Get an enum instance given the matching code.
	 * 
	 * @param code
	 * @return the enum instance
	 */
	public static Action fromCode(String code) {
		for(Action enumObj : values()){
			if(enumObj.getActionCode().equals(code)) {
				return enumObj;
			}
		}
		return null;
	}
	
	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
}
