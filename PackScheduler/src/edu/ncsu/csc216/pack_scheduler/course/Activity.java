package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Contains information for activities
 * @author josephgrube
 */
public abstract class Activity implements Conflict, Comparable<Course> {

	/**Course title*/
	private String title;
	
	/** Course meeting days*/
	private String meetingDays;
	
	/** Course starting time*/
	private int startTime;
	
	/** Course ending time*/
	private int endTime;

	
	/**
	 * Gets short display array
	 * @return shortDisplayArray
	 */
	public abstract String[] getShortDisplayArray();
	
	/**
	 * Gets long display array
	 * @return longDisplayArray
	 */
	public abstract String[] getLongDisplayArray();
	
	/**
	 * Checks to see if activity is a duplicate of another object
	 * @param activity the object being checked for duplication
	 * @return true if duplicate, false if not
	 */
	public abstract boolean isDuplicate(Activity activity);
	
	/** 
	 * Constructor
	 * @param title title of the activity
	 * @param meetingDays the meeting days of the event
	 * @param startTime the start time of the event
	 * @param endTime the end time of the event
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		setTitle(title);
		setMeetingDays(meetingDays);
        setActivityTime(startTime, endTime);
	}

	/**
	 * Returns the title of the course
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of the course
	 * @param title the title to set
	 * @throws IllegalArgumentException whenever the title is either null or not long enough
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Invalid course title");
		}
		if (title.length() < 1) {
			throw new IllegalArgumentException("Invalid course title");
		}
		this.title = title;
	}

	/**
	 * Returns meeting day's
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Sets meeting day's
	 * @param meetingDays the meetingDays to set
	 */
	public void setMeetingDays(String meetingDays) {
		if(meetingDays == null) {
			throw new IllegalArgumentException("Invalid meeting days");
		}
		this.meetingDays = meetingDays;
	}
 
	/**
	 * Returns the starting time
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the end time
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets the course time
	 * @param startTime start time of the course
	 * @param endTime end time of the course
	 * @throws IllegalArgumentException whenever the parameters to not meet the requirements 
	 * of the method
	 */
	public void setActivityTime(int startTime, int endTime) {
		if(startTime < 0000 || startTime > 2359) {
			throw new IllegalArgumentException("Invalid meeting times");
		}
		if(endTime < 0000 || endTime > 2359) {
			throw new IllegalArgumentException("Invalid meeting times");
		}
		if(endTime < startTime) {
			throw new IllegalArgumentException("Invalid meeting times");
		}
		if(startTime % 100 >= 60 || endTime % 100 >= 60) {
			throw new IllegalArgumentException("Invalid meeting times");
		}
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	/**
	 * Gets the meeting days and time
	 * @return meetingTime the string with the meeting days and times
	 */
	public String getMeetingString() {
		String day = getMeetingDays();
		if(day.equals("A")) {
			return "Arranged";
		}
		String start = "";
		String end = "";
		int startHour = startTime / 100;
		int endHour = endTime / 100;
		int endMin = endTime % 100;
		int startMin = startTime % 100;
		if(startHour < 12) {
			start = "AM";
		}
		if(startHour == 12) {
			start = "PM";
		}
		if(startHour > 12) {
			startHour = startHour - 12;
			if(startHour == 12) {
				start = "AM";
			}
			else {
			    start = "PM";
			}
		    
		}
		if(endHour == 12) {
			end = "PM";
		}
		if(endHour < 12) {
			end = "AM";
		}
		if(endHour > 12) {
			endHour = endHour - 12;
			if(endHour == 12) {
			    end = "AM";
			}
			else {
			end = "PM";
			}
		}
		 if(startMin  < 10){
			start = "" + startHour + ":0" + startMin + start;
		 }

		 else {
		    start = "" + startHour + ":" + startMin + start;
		 }
		 if(endMin < 10) {
			end = "" + endHour + ":0" + endMin + end;
		 }		
		 else{
			end = "" + endHour + ":" + endMin + end;
		 }
		
		String meetingTime = day + " " + start + "-" + end;
		return meetingTime;	
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		
		int sameDays = 0;
		char[] d1 = getMeetingDays().toCharArray(); 
		char[] d2 = possibleConflictingActivity.getMeetingDays().toCharArray();
		for(int i = 0; i < d1.length; i++) {
			for(int j = 0; j < d2.length; j++) {
				if(d1[i] == d2[j] && d1[i] != 'A') {
					sameDays += 1;
				}
			}
		}
		
		if(sameDays > 0){
			
			int timeBegin = possibleConflictingActivity.getStartTime() - getStartTime();
			int timeEnd = possibleConflictingActivity.getEndTime() - getEndTime();
			
			if(timeBegin < 0 && timeEnd > 0 || timeBegin < 0 && timeEnd == 0 ) {
				throw new ConflictException("Invalid course times");
			}
			if(timeBegin > 0 && timeEnd < 0 || timeBegin == 0 && timeEnd < 0) {
				throw new ConflictException("Invalid course times");
			}
			if(getStartTime() < possibleConflictingActivity.getEndTime() && getEndTime() > possibleConflictingActivity.getEndTime()) {
				throw new ConflictException("Invalid course times");
			}
			if(getStartTime() == possibleConflictingActivity.getStartTime() || getEndTime() == possibleConflictingActivity.getEndTime()) {
				throw new ConflictException("Invalid course times");
			}
			if(getStartTime() == possibleConflictingActivity.getEndTime() || getEndTime() == possibleConflictingActivity.getStartTime()) {
				throw new ConflictException("Invalid course times");
			}
			}
		
	}

	
}	
	
	
	

