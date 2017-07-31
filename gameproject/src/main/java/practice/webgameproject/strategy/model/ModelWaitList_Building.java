package practice.webgameproject.strategy.model;

import java.util.Date;

public class ModelWaitList_Building {

		private	Date 	WaitTime 	;	//	WaitTime 	DATETIME NOT NULL,
		private Integer LocationID	;	//	LocationID  INT(11) NULL DEFAULT NULL,
		private Integer Kind	 	;	//	Kind	 	INT(11) NOT NULL,
		private Integer RoomNumber	;	//	RoomNumber  INT(11) NULL DEFAULT NULL,
		public Date getWaitTime() {
			return WaitTime;
		}
		public void setWaitTime(Date waitTime) {
			WaitTime = waitTime;
		}
		
		public Integer getLocationID() {
			return LocationID;
		}
		
		public void setLocationID(Integer locationID) {
			LocationID = locationID;
		}
		
		public Integer getKind() {
			return Kind;
		}
		
		public void setKind(Integer kind) {
			Kind = kind;
		}
		
		public Integer getRoomNumber() {
			return RoomNumber;
		}
		
		public void setRoomNumber(Integer roomNumber) {
			RoomNumber = roomNumber;
		}
		
		public ModelWaitList_Building() {
			super();
		}
		
		public ModelWaitList_Building(Date waitTime, Integer locationID, Integer kind, Integer roomNumber) {
			super();
			WaitTime = waitTime;
			LocationID = locationID;
			Kind = kind;
			RoomNumber = roomNumber;
		}
	
}
