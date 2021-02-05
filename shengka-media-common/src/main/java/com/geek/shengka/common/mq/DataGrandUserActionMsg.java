package com.geek.shengka.common.mq;

public class DataGrandUserActionMsg {
	private String userid;
	private String imei;
	private String itemid;
	private String actionType;
	private String sceneType;
	private String timestamp;
	private String recRequestId;
	private String playTime;
    private String playIntegrity;
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}


	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

    public String getRecRequestId() {
        return recRequestId;
    }

    public void setRecRequestId(String recRequestId) {
        this.recRequestId = recRequestId;
    }

    public String getSceneType() {
		return sceneType;
	}

	public void setSceneType(String sceneType) {
		this.sceneType = sceneType;
	}

	public String getPlayTime() {
		return playTime;
	}

	public void setPlayTime(String playTime) {
		this.playTime = playTime;
	}

    public String getPlayIntegrity() {
        return playIntegrity;
    }

    public void setPlayIntegrity(String playIntegrity) {
        this.playIntegrity = playIntegrity;
    }
}
