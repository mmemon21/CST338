// Author: Mustafa Memon
// File name: Student.java
// Code purpose: This code is a class called Student to work with other classes for Project1 CST338.

import java.util.*;
public class Student{
	int id;
	String name;
	ArrayList<CourseScore> score_card=new ArrayList<CourseScore>();
	Student(int id,String name){
		this.id=id;
		this.name=name;
	}
	
	public String toString(){
		String s="";
		double sum=0;
		double avg=0;
		for(CourseScore c:score_card){
			s+=c.course.ctitle+"\n";
			if(c.score!=-1){
				sum+=c.score;
			}
		}
		if(score_card.size()!=0)
			avg=sum/score_card.size();
		

		return String.format("Stduent Number: "+this.id+"\nName: "+this.name+"\nCourses Enrolled: \n"+s+"Course Average: "+avg+"\n");
	}
}