// Author: Mustafa Memon
// File name: Course.java
// Code purpose: This code is a class called Course to work with other classes for Project1 CST338.

import java.util.*;
public class Course{
	int cnum;
	String ctitle;
	int capacity;
	String class_loc;
	Instructor instructor;
	ArrayList<CourseScore> registered_students=new ArrayList<CourseScore>();

	Course(int cnum,String ctitle,int  capacity,String class_loc){
		this.cnum=cnum;
		this.ctitle=ctitle;
		this.capacity=capacity;
		this.class_loc=class_loc;
	}
	void updateLocation(String loc){
		this.class_loc=loc;
	}
	CourseScore getCSBySid(int id){
		for(CourseScore cs:registered_students){
			if(cs.sid==id){
				return cs;
			}
		}
		return null;
	}
	public String toString(){
		double sum=0;
		for(CourseScore cs:registered_students){
			if(cs.score!=-1){
				sum+=cs.score;
			}
		}
		double avg=0;
		if(registered_students.size()!=0)
			avg=sum/registered_students.size();
		return String.format("Course Number: "+this.cnum+"\nInstructor: "+this.instructor.name+"\nCourse title: "+this.ctitle+"\nRoom: "+this.class_loc+"\nTotal Enrolled: "+registered_students.size()+"\nAverage: "+avg);
	}
}
