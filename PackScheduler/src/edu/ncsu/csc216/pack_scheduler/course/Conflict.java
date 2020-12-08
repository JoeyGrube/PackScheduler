/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Checks for schedule conflicts between events
 * @author josephgrube
 */
public interface Conflict {

	/**
	 *  Checks for schedule conflicts
	 * @param possibleConflictingActivity activity being checked for causing a conflict
	 * @throws ConflictException when a conflict in the schedule is spotted
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
}
