package timetablepuzzle.usecases.solution;

import java.util.ArrayList;
import java.util.List;


import timetablepuzzle.eclipselink.entities.administration.TimeslotPattern;
import timetablepuzzle.eclipselink.entities.inputdata.Class;
import timetablepuzzle.eclipselink.entities.inputdata.Solution;

public class SolutionCreator {
	private List<Class> classes;
	private TimeslotPattern timeslotPattern;
	private Solution solution;
	
	public SolutionCreator(List<Class> classes, TimeslotPattern timeslotPattern)
	{
		this.classes = classes;
		this.timeslotPattern = timeslotPattern;
		this.solution = new Solution(this.classes, timeslotPattern);
		InitializeRoomAssignments();
		InitializeInstructorAssignments();
		InitializeStudentGroupsAssignments();
		InitializeAssignedDayAndTimeSlots();
		InitializeNumberOfRemovals();
	}
	
	public Solution CreateNewSolution()
	{
		return solution;
	}
	
	private void InitializeRoomAssignments() {
		List<Integer> roomsIds = new ArrayList<Integer>();
		for (Class aClass : this.classes) {
			int roomId = aClass.getAssignedRoomId();
			if (!roomsIds.contains(roomId)) {
				roomsIds.add(roomId);
			}
		}
		for(Integer roomId : roomsIds)
		{
			this.solution.AssignWeekToRoom(roomId, timeslotPattern.GenerateFreeWeek());
		}
	}
	
	private void InitializeInstructorAssignments() {
		List<Integer> instructorsIds = new ArrayList<Integer>();
		for (Class aClass : this.classes) {
			int instructorId = aClass.getAssignedInstructorId();
			if (!instructorsIds.contains(instructorId)) {
				instructorsIds.add(instructorId);
			}
		}
		for(Integer instructorId : instructorsIds)
		{
			this.solution.AssignWeekToInstructor(instructorId, timeslotPattern.GenerateFreeWeek());
		}
	}
	
	private void InitializeStudentGroupsAssignments() {
		List<Integer> studentGroupsIds = new ArrayList<Integer>();
		for (Class aClass : this.classes) {
			studentGroupsIds.addAll(aClass.getAssignedStudentGroupsIds());			
		}
		
		for(Integer studentGroupId : studentGroupsIds)
		{
			this.solution.AssignWeekToStudentGroup(studentGroupId, timeslotPattern.GenerateFreeWeek());
		}
	}

	private void InitializeAssignedDayAndTimeSlots() {
		for (Class aClass : this.classes) {
			this.solution.SetAssignedDayAndTimeSlot(aClass.getId(), TimeslotPattern.UnassignedTimeSlot);
		}
	}

	private void InitializeNumberOfRemovals() {
		for (Class aClass : this.classes) {
			this.solution.ResetNrOfRemovals(aClass.getId());
		}
	}
}